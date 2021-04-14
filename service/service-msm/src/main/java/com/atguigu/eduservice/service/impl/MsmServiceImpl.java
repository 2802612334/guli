package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.service.MsmService;
import com.atguigu.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class MsmServiceImpl implements MsmService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void sendMobileCode(String mobile) {
        // 1.生成随机的六位数字验证码
        String code = RandomUtil.getSixBitRandom();

        // 2.短信发送验证码 TODO

        // 3.将验证码存入redis，key为手机号，value为验证码，过期时间为15分钟
        redisTemplate.opsForValue().set(mobile,code,15, TimeUnit.MINUTES);
    }
}
