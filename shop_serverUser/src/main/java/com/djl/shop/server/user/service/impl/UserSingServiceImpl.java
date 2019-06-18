package com.djl.shop.server.user.service.impl;

import com.djl.common.config.ProjectConfig;
import com.djl.common.jwt.JwtUtil;
import com.djl.common.model.LoginToken;
import com.djl.common.util.JedisUtil;
import com.djl.common.util.TimeUtil;
import com.djl.common.vo.R;
import com.djl.shop.entity.Points;
import com.djl.shop.entity.UserSign;
import com.djl.shop.server.user.dao.PointsMapper;
import com.djl.shop.server.user.dao.UserPointsMapper;
import com.djl.shop.server.user.dao.UserSignMapper;
import com.djl.shop.server.user.service.UserSignService;
import com.djl.shop.server.user.util.RandomUtil;
import com.djl.shop.server.user.util.UserTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author DJL
 * @create 2019-06-16 16:20
 * @desc 用户签到服务接口实现类
 **/
@Service
public class UserSingServiceImpl implements UserSignService {
    @Autowired(required = false)
    private UserSignMapper userSignMapper;

    @Autowired(required = false)
    private UserPointsMapper userPointsMapper;

    @Autowired(required = false)
    private PointsMapper pointsMapper;

    @Autowired(required = false)
    private JedisUtil jedisUtil;

    @Override
    @Transactional
    public R save(String token) {

        // 解析token
        LoginToken loginToken = UserTokenUtil.parseToken(JwtUtil.parseJWT(token));

        // token解析失败
        if (null == loginToken) {
            return R.setERROR("请先登录");
        }

        // 判断今天是否已经签过到
        if (jedisUtil.exists(ProjectConfig.SIGNKEY) && jedisUtil.sismember(ProjectConfig.SIGNKEY, loginToken.getPhone())) {
            return R.setERROR("今天已签过到");
        }

        // 基础积分
        int baseScore = RandomUtil.createNum(3, 5);
        // 额外奖励积分
        int extraScore = 0;
        // 积分记录信息
        String info = "";
        // 连续签到天数
        Integer days = 1;

        // 判断是否首次签到
        UserSign lastSign = userSignMapper.selectByUidLast(loginToken.getUid());
        if (null == lastSign) {
            // 第一位签到
            if (!jedisUtil.exists(ProjectConfig.SIGNKEY)) {
                // 设置redis签到记录集合 签到数据key的有效期为当天剩余的秒数
                jedisUtil.expire(ProjectConfig.SIGNKEY, TimeUtil.getLastSeconds());
            }

            extraScore = ProjectConfig.NEWSCORE;
            info = "首次签到，基础奖励积分：" + baseScore + "，额外奖励积分：" + extraScore;
        } else {
            // 非首次签到，连续签到
            days = lastSign.getDays() + 1;

            if (TimeUtil.getDistanceDays(lastSign.getCreatetime()) == 1) {
                // 连续签到一年以上
                if (days % 365 == 0) {
                    //连续签到1年 或者2年
                    extraScore = days;
                    info = "恭喜你，连续签到：" + days + "天，特别奖励:" + days + "积分";
                } else if (days % 30 == 0) {
                    extraScore = 50;
                    info = "恭喜你连续签到：" + days + "天，特别奖励:50积分";
                } else if (days % 5 == 0) {
                    extraScore = 10;
                    info = "恭喜你连续签到：" + days + "天，特别奖励:10积分";
                } else if (days % 3 == 0) {
                    extraScore = baseScore;
                    info = "恭喜你连续签到：" + days + "天，特别奖励:" + days + "积分";
                }
            } else {
                // 断签
                info = "你上次是在：" + TimeUtil.getDistanceDays(lastSign.getCreatetime()) + "签到";
            }

        }

        // 保存用户签到数据到签到表
        UserSign userSign = new UserSign();
        userSign.setUid(loginToken.getUid());
        userSign.setDays(days);
        userSign.setScore(baseScore);
        userSign.setExtrascore(extraScore);
        userSignMapper.insert(userSign);

        // 保存积分变动
        Points points = new Points();
        points.setScore(baseScore + extraScore);
        points.setUid(loginToken.getUid());
        points.setStartdate(new Date());
        points.setEnddate(TimeUtil.getDays(ProjectConfig.NEWSCORE));
        pointsMapper.insert(points);

        // 更新用户积分
        userPointsMapper.update(loginToken.getUid(), baseScore + extraScore);

        // redis中保存签到信息
        jedisUtil.sadd(ProjectConfig.SIGNKEY, loginToken.getPhone());

        return R.setOK("签到成功", userSign);
    }

    @Override
    public R findByUid(String token) {
        return R.setOK("OK", userSignMapper.selectByUid(UserTokenUtil.parseToken(JwtUtil.parseJWT(token)).getUid()));
    }

    @Override
    public R findByUidLast(String token) {
        return R.setOK("OK", userSignMapper.selectByUidLast(UserTokenUtil.parseToken(JwtUtil.parseJWT(token)).getUid()));
    }

    @Override
    public R findByUidDays(String token, Integer days) {
        int uid = UserTokenUtil.parseToken(JwtUtil.parseJWT(token)).getUid();
        return R.setOK("OK", userSignMapper.selectByUidDays(uid, days));
    }
}
