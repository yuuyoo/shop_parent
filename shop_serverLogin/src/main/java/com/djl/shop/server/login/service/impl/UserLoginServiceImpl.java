package com.djl.shop.server.login.service.impl;

import com.alibaba.fastjson.JSON;
import com.djl.common.config.ProjectConfig;
import com.djl.common.jwt.JwtUtil;
import com.djl.common.util.EncryptionUtil;
import com.djl.common.util.IdGenerator;
import com.djl.common.util.JedisUtil;
import com.djl.common.vo.R;
import com.djl.shop.entity.User;
import com.djl.shop.server.login.dao.UserDao;
import com.djl.shop.server.login.dao.UserLogDao;
import com.djl.shop.server.login.model.LoginToken;
import com.djl.shop.server.login.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

/**
 * @author DJL
 * @create 2019-06-16 11:27
 * @desc 用户登录服务接口的实现类
 **/
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired(required = false)
    private UserDao userDao;

    @Autowired(required = false)
    private UserLogDao userLogDao;

    @Autowired(required = false)
    private JedisUtil jedisUtil;

    @Autowired(required = false)
    private IdGenerator idGenerator;

    @Override
    public R login(String phone, String password) {
        User user = userDao.selectByPhone(phone);
        // 账号是否存在
        if (null == user) {
            return R.setERROR("用户不存在");
        }
        // 账号被系统禁用
        if (user.getFlag() != 1) {
            return R.setERROR("用户已被系统禁用");
        }

        // 账号被临时锁定
        String key = ProjectConfig.USERSD + phone;
        if (jedisUtil.exists(key)) {
            return R.setERROR("账号被临时锁定，请" + jedisUtil.ttl(key) + "秒后再试");
        }

        // 判断密码是否相等
        if (!Objects.equals(user.getPassword(), EncryptionUtil.RSAEnc(ProjectConfig.PASSRSAPRI, password))) {
            // redis更新失败次数
            String loginLog = ProjectConfig.USERLOGINCOUNT + phone;
            jedisUtil.setex(loginLog + "_" + System.currentTimeMillis(), 600, "登录失败");
            // 获取失败总次数
            Set<String> loginCount = jedisUtil.keys(loginLog + "*");
            if (loginCount.size() == 3) {
                // 冻结当前账号1小时
                jedisUtil.setex(key, 3600, "账号已冻结");
            }
            // 记录失败日志
            userLogDao.save(user.getId(), "登录失败");

            return R.setERROR("账号或密码错误");
        }


        // 登录成功生成token
        LoginToken loginToken = new LoginToken();
        loginToken.setPhone(phone);
        loginToken.setUid(user.getId());
        loginToken.setId(idGenerator.nextId() + "");
        String token = JwtUtil.createJWT(loginToken.getId(), JSON.toJSONString(loginToken));

        // redis更新
        jedisUtil.setex(ProjectConfig.TOKENPHONE + phone, 1800, token);
        jedisUtil.setex(ProjectConfig.TOKENJWT + token, 1800, JSON.toJSONString(user));

        // 记录成功日志
        userLogDao.save(user.getId(), "登录成功");
        // 打印token
        System.out.println(ProjectConfig.TOKENHEAD + phone + "：" + token);

        return R.setOK("登录成功");
    }

    @Override
    public R checkLogin(String token) {
        // 验证token有效性
        if (JwtUtil.checkJWT(token)) {
            // 解析令牌
            LoginToken loginToken = JSON.parseObject(JwtUtil.parseJWT(token), LoginToken.class);
            // 获取redis中保存的令牌
            String redisToken = jedisUtil.get(ProjectConfig.TOKENPHONE + loginToken.getPhone());
            if (Objects.equals(redisToken, token)) {
                // 验证成功
                return R.setOK("token有效", token);
            } else {
                return R.setERROR("已在其他地方登录了");
            }
        } else {
            return R.setERROR("token已失效");
        }
    }

    @Override
    public R logout(String token) {
        // 验证token有效性
        if (JwtUtil.checkJWT(token)) {
            // 解析令牌
            LoginToken loginToken = JSON.parseObject(JwtUtil.parseJWT(token), LoginToken.class);
            // 获取redis中保存的令牌
            String redisToken = jedisUtil.get(ProjectConfig.TOKENPHONE + loginToken.getPhone());
            if (Objects.equals(redisToken, token)) {
                // 清除token
                jedisUtil.del(ProjectConfig.TOKENJWT + token);
                jedisUtil.del(ProjectConfig.TOKENPHONE + loginToken.getPhone());

                // 退出成功
                return R.setOK("退出成功", token);
            } else {
                return R.setERROR("已在其他地方登录了");
            }
        } else {
            return R.setERROR("token已失效");
        }
    }
}