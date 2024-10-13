package com.atguigu.test;

import com.atguigu.utils.JwtHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Zephyrtoria
 * @CreateTime: 2024-10-11
 * @Description:
 * @Version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtTest {
    @Autowired
    private JwtHelper jwtHelper;

    @Test
    public void test() {
        //生成 传入用户标识
        String token = jwtHelper.createToken(1L);
        System.out.println("token = " + token);

        //解析用户标识
        int userId = jwtHelper.getUserId(token).intValue();
        System.out.println("userId = " + userId);

        //校验是否到期! false 未到期 true到期
        boolean expiration = jwtHelper.isExpiration(token);
        System.out.println("expiration = " + expiration);
    }
}
