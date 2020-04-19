package com.cargosmart.ita.demo2.config;

import com.cargosmart.ita.demo2.properties.SecurityConstants;
import com.cargosmart.ita.demo2.properties.SecurityProperties;
import com.cargosmart.ita.demo2.security.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private FormAuthenticationConfig formAuthenticationConfig;
    @Resource
    private SecurityProperties securityProperties;
    @Resource(name = "myUserDetailService")
    private MyUserDetailsService myUserDetailsService;
    @Resource
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        formAuthenticationConfig.configure(http);

        http.authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_PAGE_URL,
                        SecurityConstants.DEFAULT_LOGIN_PAGE_URL,
                        securityProperties.getLogin().getLoginErrorUrl())
                .permitAll()
                .and()
                .authorizeRequests()
                    .anyRequest().authenticated()
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getLogin().getRememberMeSeconds())
                .userDetailsService(myUserDetailsService)
                .and()
                .csrf().disable();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
