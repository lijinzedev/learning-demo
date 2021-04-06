package com.curiosity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;

/**
 * @author: Curiosity
 * @Date: 2021/4/6 22:37
 * @Description:
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 授权请求
        http.authorizeRequests()
                // 放行login.html
                .antMatchers("/login.html").permitAll()
                // 所有请求都必须被认证
                .anyRequest().authenticated()
                .and()
                .formLogin()
              //  .loginPage("/login")
                // 自定义登录逻辑
                .loginProcessingUrl("/dologin")
                .usernameParameter("name")
                .passwordParameter("pwd")
                // 登录成功跳转
                .successHandler((resq, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = resp.getWriter();
                    Object principal1 = authentication.getPrincipal();
                    String json = new ObjectMapper().writeValueAsString(principal1);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }).failureHandler((resq, resp, e) -> {
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.write(new ObjectMapper().writeValueAsString("登录失败"));
            writer.flush();
            writer.close();
        }).and()
                // 关闭防护(防火墙)
                .csrf().disable();

    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
