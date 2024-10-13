package com.atguigu.config;

import com.atguigu.interceptors.LoginProtectedInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Zephyrtoria
 * @CreateTime: 2024-10-13
 * @Description:
 * @Version: 1.0
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Autowired
    private LoginProtectedInterceptors loginProtectedInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginProtectedInterceptors).addPathPatterns("/headline/**");
    }
}
