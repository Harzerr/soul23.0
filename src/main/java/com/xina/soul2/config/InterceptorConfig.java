package com.xina.soul2.config;

import com.xina.soul2.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login", "/**/register", "/admin/**", "/**/upload/**", "/**/update", "/**/pagerSelect",
                        "/**/getCount");
    }

    @Bean
    public JWTInterceptor jwtInterceptor(){
        return new JWTInterceptor();
    }
}
