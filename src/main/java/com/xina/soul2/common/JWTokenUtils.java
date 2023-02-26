package com.xina.soul2.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.xina.soul2.entity.User;
import com.xina.soul2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Component
public class JWTokenUtils {

    @Autowired
    private UserService userService;

    private static UserService staticUserService;

    @PostConstruct
    public void setService(){
        staticUserService = userService;
    }

    /**
     * 生成token
     * 生效时间2小时
     * 通过用户id作为载荷
     * 用户密码作为签证
     */
    public static String createToken(String id, String password) {

        Calendar instance = Calendar.getInstance();

        instance.add(Calendar.HOUR, 2);

        String token = JWT.create()
                .withAudience(id)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(password));

        return token;
    }

    /**
     * 获取当前登录的用户
     *
     * @return
     */
    public static User getCurrentUser() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String token = request.getHeader("token");

        if ("".equals(token)) {
            try {
                String userId = JWT.decode(token).getAudience().get(0);
                User user = staticUserService.getOneById(Integer.valueOf(userId));
                return user;
            } catch (JWTDecodeException e) {
                return null;
            }
        }

        return null;
    }
}
