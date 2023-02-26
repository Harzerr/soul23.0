package com.xina.soul2.service;

import com.xina.soul2.entity.User;
import com.xina.soul2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUser(){

        List<User> all = userMapper.findAll();

        return all;
    }

    public List<User> selectByPager(int index, int pageSize){

        List<User> users = userMapper.pagerSelect(index, pageSize);

        return users;

    }

    public int deleteById(Integer id){
        int flag = userMapper.myDeleteById(id);
        return flag;
    }

    public boolean updateUser(String nickname, String phone, String email, Integer id){

        try {
            int i = userMapper.userUpdate(nickname, phone, email, id);
            if (i > 0){
                return true;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateImagePath(String path, Integer id){
        try {
            int i = userMapper.imageUrlUpdate(path, id);
            if (i > 0){
                return true;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public User LoginCheck(User loginform){
        User user = new User();
        //如果用户用手机号登录
        if (!"".equals(loginform.getUphone())){
            user = userMapper.selectUserByPhone(loginform.getUphone(), loginform.getUpassword());
        }else{
            //用邮箱登录
            user = userMapper.selectUserByEmail(loginform.getUemail(), loginform.getUpassword());
        }

        return user;
    }

    public User getOneById(Integer id){
        return userMapper.getOneById(id);
    }
    public boolean Register(User registerform){
        User user = new User();

        user = userMapper.selectUser(registerform.getUphone(), registerform.getUemail());

        //用户没有存在可以注册
        if(user == null){
            //判断昵称不为空时
            if(!"".equals(registerform.getUnickname())){
                int flag = userMapper.insertUserHaveNickname(registerform);
                if(flag > 0){
                    return true;
                }else {
                    return false;
                }
            }else{ //昵称不存在时 就是为空的嘛
                int flag = userMapper.insertUserNoNickname(registerform);
                if(flag > 0){
                    return true;
                }else {
                    return false;
                }
            }
        }else{
            //用户存在不能注册
            return false;
        }
    }

}
