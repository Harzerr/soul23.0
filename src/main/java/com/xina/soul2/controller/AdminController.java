package com.xina.soul2.controller;

import com.xina.soul2.entity.Admin;
import com.xina.soul2.entity.SoulPager;
import com.xina.soul2.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping("/login")
    public Admin Login(@RequestBody Admin admin){
        String aphone = admin.getAphone();
        String apassword = admin.getApassword();


        return adminService.selectAdminByPhoneAndPassword(aphone, apassword);
    }

    @GetMapping("/getCount")
    public int getCount(){

        List<Admin> allAdmin = adminService.getAllAdmin();

        return allAdmin.size();
    }


    @PostMapping("/pagerSelect")
    public List<Admin> getPageData(@RequestBody SoulPager soulPager){

        int index = (soulPager.getIndex() - 1) * soulPager.getPageSize();

        List<Admin> admins = adminService.selectByPager(index, soulPager.getPageSize());
        return admins;
    }

    @PostMapping("/delete")
    public boolean deleteById(@RequestBody Admin admin){
        int flag = adminService.deleteById(admin.getId());
        if (flag > 0){
            return true;
        }
        return false;
    }
    @PostMapping("/update")
    public boolean updateAdmin(HttpServletRequest httpServletRequest){
        String aphone = httpServletRequest.getParameter("aphone");
        String apassword = httpServletRequest.getParameter("apassword");
        String id = httpServletRequest.getParameter("id");

        return adminService.updateAdmin(aphone, apassword, Integer.valueOf(id));
    }

}
