package com.atguigu.service;

import com.atguigu.pojo.User;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author Lenovo
 * @description 针对表【news_user】的数据库操作Service
 * @createDate 2024-10-11 16:25:32
 */
public interface UserService extends IService<User> {
    /**
     * 登录业务
     *
     * @param user
     * @return
     */
    Result login(User user);

    /**
     * 根据token返回用户信息
     *
     * @param token
     * @return
     */
    Result getUserInfo(String token);

    /**
     * 根据传入的用户名查询数据库中是否已经存在该用户
     *
     * @param username
     * @return
     */
    Result checkUserName(String username);

    /**
     * 根据传入用户信息进行注册
     *
     * @param user
     * @return
     */
    Result register(User user);

    /**
     * 登录验证
     *
     * @param token
     * @return
     */
    Result checkLogin(String token);
}
