package com.atguigu.service.impl;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.MD5Util;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lenovo
 * @description 针对表【news_user】的数据库操作Service实现
 * @createDate 2024-10-11 16:25:32
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 1. 根据账号，查询用户对象
     * 2. 如果用户对象为null，查询失败-账号错误，返回501
     * 3. 找到对象，对比密码，若密码错误，返回503
     * 4. 根据用户id生成一个token，封装入result返回即可
     *
     * @param user
     * @return
     */
    @Override
    public Result login(User user) {
        Result result;
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        User selectOne = userMapper.selectOne(wrapper);

        if (selectOne == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        if (selectOne.getUserPwd() != null && !selectOne.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd()))) {
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        }

        Map data = new HashMap();
        data.put("token", jwtHelper.createToken((long) selectOne.getUid()));
        return Result.ok(data);
    }

    /**
     * 1. 判断token是否过期，过期则返回504
     * 2. 根据token复原uid，根据uid查找用户信息
     * 3. 去掉密码，封装result结果返回
     *
     * @param token
     * @return
     */
    @Override
    public Result getUserInfo(String token) {
        if (token == null || jwtHelper.isExpiration(token)) {
            return Result.build(null, ResultCodeEnum.NOT_LOGIN);
        }

        int userId = jwtHelper.getUserId(token).intValue();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUid, userId);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            return Result.build(null, ResultCodeEnum.NOT_LOGIN);
        }
        user.setUserPwd("");

        Map data = new HashMap<>();
        data.put("loginUser", user);
        return Result.ok(data);
    }

    /**
     * 1. 查询用户名是否存在
     * 2. 根据结果返回
     *
     * @param username
     * @return
     */
    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        Long count = userMapper.selectCount(wrapper);

        if (count != 0) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        return Result.ok(null);
    }

    /**
     * 1. 判断用户名是否被占用，如果被占用返回505
     * 2. 插入新的用户信息进入数据库，注意密码要通过MD5处理
     *
     * @param user
     * @return
     */
    @Override
    public Result register(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        Long count = userMapper.selectCount(wrapper);
        if (count != 0) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        int rows = userMapper.insert(user);
        if (rows > 0) {
            return Result.ok(null);
        } else {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
    }

    /**
     * 在用户进入隐私页面前进行拦截，根据token验证登录状态
     * 检查token是否过期
     *
     * @param token
     * @return
     */
    @Override
    public Result checkLogin(String token) {
        if (jwtHelper.isExpiration(token)) {
            return Result.build(null, ResultCodeEnum.NOT_LOGIN);
        }
        return Result.ok(null);
    }
}