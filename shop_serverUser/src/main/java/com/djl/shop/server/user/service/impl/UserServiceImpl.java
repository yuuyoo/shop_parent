package com.djl.shop.server.user.service.impl;

import com.djl.common.config.ProjectConfig;
import com.djl.common.exception.UserException;
import com.djl.common.util.EncryptionUtil;
import com.djl.common.util.TimeUtil;
import com.djl.common.vo.R;
import com.djl.shop.entity.*;
import com.djl.shop.server.user.dao.*;
import com.djl.shop.server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author DJL
 * @create 2019-06-13 22:16
 * @desc 用户相关服务实现类
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private UserDetailMapper userDetailMapper;

    @Autowired(required = false)
    private PointsMapper pointsMapper;

    @Autowired(required = false)
    private UserPointsMapper userPointsMapper;

    @Autowired(required = false)
    private CartMapper cartMapper;

    @Autowired(required = false)
    private UserLogMapper userLogMapper;

    @Override
    @Transactional(rollbackFor = {UserException.class}) // 开启事务
    public R save(User user) throws UserException {

        try {
            // 新增用户 并将自增id在user对象中
            // 密码使用RSA私钥加密
            user.setPassword(EncryptionUtil.RSAEnc(ProjectConfig.PASSRSAPRI, user.getPassword()));
            userMapper.insert(user);

            // 初始化用户详情
            UserDetail userDetail = new UserDetail();
            userDetail.setUid(user.getId());
            userDetailMapper.insert(userDetail);

            // 记录用户积分变动 新用户注册奖励积分
            Points points = new Points();
            points.setUid(user.getId());
            points.setEnddate(TimeUtil.getDays(ProjectConfig.INITNEWSEXPIRE));
            points.setScore(ProjectConfig.INITNEWSCORE);
            points.setInfo("新增用户奖励" + ProjectConfig.INITNEWSCORE + "积分");
            pointsMapper.insert(points);

            // 初始化用户积分
            UserPoints userPoints = new UserPoints();
            userPoints.setUid(user.getId());
            userPoints.setTotalscore(ProjectConfig.INITNEWSCORE);
            userPoints.setCurrscore(ProjectConfig.INITNEWSCORE);
            userPointsMapper.insert(userPoints);

            // 初始化购物车
            Cart cart = new Cart();
            cart.setUid(user.getId());
            cart.setMaxcount(ProjectConfig.CARTMAXGOODS);
            cart.setCurrcount(0);
            cartMapper.insert(cart);

            // 记录用户日志
            UserLog userLog = new UserLog();
            userLog.setUid(user.getId());
            // 用户注册
            userLog.setFlag(1);
            userLog.setContent("新用户注册");
            userLogMapper.insert(userLog);

            return R.setOK("用户注册成功", null);

        } catch (Exception e) {
            throw new UserException("用户注册失败异常");
        }
    }

    @Override
    public R findByPhone(String phone, Integer flag) {

        User user = userMapper.selectByPhone(phone, flag);
        if (user == null) {
            return R.setERROR("未查询到用户");
        }

        return R.setOK("OK", user);
    }

    @Override
    public R checkPhone(String phone) {
        User user=userMapper.selectByPhone(phone, null);
        if(user!=null){
            return R.setERROR("手机号已经注册过");
        }else {
            return R.setOK();
        }
    }

}
