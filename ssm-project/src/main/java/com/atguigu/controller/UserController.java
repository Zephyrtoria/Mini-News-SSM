package com.atguigu.controller;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: Zephyrtoria
 * @CreateTime: 2024-10-11
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody User user) {
        Result result = userService.login(user);
        System.out.println("result = " + result);
        return result;
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader("token") String token) {
        Result result = userService.getUserInfo(token);
        System.out.println("result = " + result);
        return result;
    }

    @PostMapping("checkUserName")
    public Result checkUserName(@Param("username") String username) {
        Result result = userService.checkUserName(username);
        System.out.println("result = " + result);
        return result;
    }

    @PostMapping("regist")
    public Result regist(@RequestBody User user) {
        Result result = userService.register(user);
        System.out.println("result = " + result);
        return result;
    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader("token") String token) {
        Result result = userService.checkLogin(token);
        System.out.println("result = " + result);
        return result;
    }
}
