package com.cargosmart.ita.demo2.config;


import com.cargosmart.ita.demo2.handler.CustomAuthenticationFailureHandler;
import com.cargosmart.ita.demo2.handler.CustomAuthenticationSuccessHandler;
import com.cargosmart.ita.demo2.properties.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import javax.annotation.Resource;


@Configuration
public class FormAuthenticationConfig {

    @Resource
    private SecurityProperties securityProperties;

    @Resource
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Resource
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //登录页面
                .loginPage(securityProperties.getLogin().getLoginPage())
                // 指定验证凭据的URL（默认为 /login)
                .loginProcessingUrl(securityProperties.getLogin().getLoginUrl())
                //分别设置成功和失败的处理器
                // 成功处理器重构后可支持
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler);
    }

}

