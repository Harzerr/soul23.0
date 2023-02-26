package com.xina.soul2.service;

import com.xina.soul2.entity.Admin;
import com.xina.soul2.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;

    public List<Admin> getAllAdmin(){
        List<Admin> all = adminMapper.findAll();
        return all;
    }
    public int deleteById(Integer id){
        int flag = adminMapper.myDeleteById(id);
        return flag;
    }
    public List<Admin> selectByPager(int index, int pageSize){
        List<Admin> admins = adminMapper.pagerSelect(index, pageSize);
        return admins;
    }

    public Admin selectAdminByPhoneAndPassword(String phone, String password){
        return adminMapper.selectByPhoneAndPassword(phone, password);
    }

    public boolean updateAdmin(String phone, String password, Integer id){

        try {
            int i = adminMapper.updateAdmin(phone, password, id);
            if (i > 0){
                return true;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
