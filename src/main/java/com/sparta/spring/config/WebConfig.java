package com.sparta.spring.config;

import com.sparta.spring.config.auth.additional.AuthUserHandlerMethodArgumentResolver;
import com.sparta.spring.config.auth.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final JwtUtil jwtUtil;

    public WebConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public AuthUserHandlerMethodArgumentResolver getUserHandlerMethodArgumentResolver() {
        return new AuthUserHandlerMethodArgumentResolver(jwtUtil);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(getUserHandlerMethodArgumentResolver());
    }

}
