package com.xina.soul2.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.xina.soul2.entity.User;
import com.xina.soul2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    private Integer id;
    private User user;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        String token = request.getHeader("token");

        if ("".equals(token)){
            throw new RuntimeException("token验证失败，请登录");
        }
        try {
            String userId = JWT.decode(token).getAudience().get(0);
            id = Integer.valueOf(userId);

        } catch (JWTDecodeException e){
            throw new RuntimeException("token验证失败，请登录");
        }
        user = userService.getOneById(id);
        if (user == null){
            throw new RuntimeException("token验证失败，请登录");
        }

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUpassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e){
            throw new RuntimeException("token验证失败，请登录");
        }

        return true;
    }
}
