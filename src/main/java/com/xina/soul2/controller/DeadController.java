package com.xina.soul2.controller;

import com.xina.soul2.entity.Dead;
import com.xina.soul2.entity.DeadPager;
import com.xina.soul2.entity.SoulPager;
import com.xina.soul2.entity.User;
import com.xina.soul2.service.DeadSeivice;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/dead")
public class DeadController {
    @Resource
    private DeadSeivice deadSeivice;

    @GetMapping("/getCount")
    public int getCount(){
        int count = deadSeivice.getCount();
        return count;
    }

    @PostMapping("/pagerSelect")
    public List<Dead> getPageData(@RequestBody SoulPager soulPager){
        int index = (soulPager.getIndex() - 1) * soulPager.getPageSize();
        List<Dead> deads = deadSeivice.selectByPager(index, soulPager.getPageSize());
        return deads;
    }

    @PostMapping("/delete")
    public boolean deleteById(@RequestBody Dead dead){
        int i = deadSeivice.deleteById(dead.getId());
        if (i > 0){
            String property = System.getProperty("user.dir");
            String dphoto = dead.getDphoto();
            if(!"/img/defaultimg.png".equals(dphoto)){
                String path = property + "\\src\\main\\resources\\static" + dphoto;
                File file = new File(path);
                // 删除照片
                if (file.exists()){
                    file.delete();
                }

            }
            return true;
        }
        return false;
    }

    @PostMapping("/update")
    public boolean updateDead(HttpServletRequest httpServletRequest){

        String dname = httpServletRequest.getParameter("dname");
        String dsex = httpServletRequest.getParameter("dsex");
        String id = httpServletRequest.getParameter("id");

        return deadSeivice.updateDead(dname, dsex, Integer.valueOf(id));
    }

    @GetMapping("/search")
    public DeadPager search(HttpServletRequest httpServletRequest){
        String keyword = httpServletRequest.getParameter("keyword");
        String pageSize = httpServletRequest.getParameter("pageSize");
        String pageIndex = httpServletRequest.getParameter("pageIndex");
        return deadSeivice.selectByKeyword(keyword, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
    }

}
