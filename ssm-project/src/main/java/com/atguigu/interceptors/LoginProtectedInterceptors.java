package com.atguigu.interceptors;

import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author: Zephyrtoria
 * @CreateTime: 2024-10-13
 * @Description: 登录保护拦截器，检查请求头中是否包含有效token。有则放行；无则返回504
 * @Version: 1.0
 */
@Component
public class LoginProtectedInterceptors implements HandlerInterceptor {
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!jwtHelper.isExpiration(request.getHeader("token"))) {
            return true;
        }
        // 返回504
        Result result = Result.build(null, ResultCodeEnum.NOT_LOGIN);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);
        response.getWriter().print(json);

        return false;
    }
}
