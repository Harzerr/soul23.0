package com.xina.soul2.controller;

import com.xina.soul2.common.JWTokenUtils;
import com.xina.soul2.entity.SoulPager;
import com.xina.soul2.entity.User;
import com.xina.soul2.entity.UserReturnFront;
import com.xina.soul2.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/all")
    public List<User> all() {

        List<User> allUser = userService.getAllUser();
        return allUser;
    }

    @PostMapping("/login")
    public UserReturnFront Login(@RequestBody User loginform, HttpSession httpSession) {

        User user = userService.LoginCheck(loginform);

        if (user != null) {

            String token = JWTokenUtils.createToken(String.valueOf(user.getId()), user.getUpassword());

            UserReturnFront userReturnFront = new UserReturnFront(user.getId(), user.getUphone(), user.getUpassword(), user.getUemail(), user.getUheadimg(), user.getUnickname(), token);
            return userReturnFront;
        }

        return null;

    }

    @PostMapping("/register")
    public boolean Register(@RequestBody User registerform) {

        return userService.Register(registerform);

    }

    @GetMapping("/getCount")
    public int getCount() {

        List<User> allUser = userService.getAllUser();

        return allUser.size();
    }


    @PostMapping("/pagerSelect")
    public List<User> getPageData(@RequestBody SoulPager soulPager) {

        int index = (soulPager.getIndex() - 1) * soulPager.getPageSize();

        List<User> users = userService.selectByPager(index, soulPager.getPageSize());
        return users;
    }

    @PostMapping("/delete")
    public boolean deleteById(@RequestBody User user) {
        Integer id = user.getId();
        int flag = userService.deleteById(id);

        if (flag > 0) {
            String property = System.getProperty("user.dir");
            String uheadimg = user.getUheadimg();
            if (!"/img/defaultimg.png".equals(uheadimg)) {
                String path = property + "\\src\\main\\resources\\static" + uheadimg;
                File file = new File(path);
                // 删除头像
                if (file.exists()) {
                    file.delete();
                }

            }
            return true;
        }
        return false;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/update")
    public boolean update(HttpServletRequest httpServletRequest) {

        String unickname = httpServletRequest.getParameter("unickname");
        String uphone = httpServletRequest.getParameter("uphone");
        String uemail = httpServletRequest.getParameter("uemail");
        String id = httpServletRequest.getParameter("id");

        boolean b = userService.updateUser(unickname, uphone, uemail, Integer.valueOf(id));

        return b;
    }
}
