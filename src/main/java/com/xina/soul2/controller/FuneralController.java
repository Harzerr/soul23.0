package com.xina.soul2.controller;

import com.xina.soul2.entity.Funeral;
import com.xina.soul2.entity.SoulPager;
import com.xina.soul2.service.FuneralService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/funeral")
public class FuneralController {
    @Resource
    private FuneralService funeralService;

    @GetMapping("/getCount")
    public int getCount(){
        return funeralService.getCount();
    }

    @PostMapping("/pagerSelect")
    public List<Funeral> getPageData(@RequestBody SoulPager soulPager){
        int index = (soulPager.getIndex() - 1) * soulPager.getPageSize();
        List<Funeral> funerals = funeralService.selectByPager(index, soulPager.getPageSize());
        return funerals;
    }
    @PostMapping("/delete")
    public boolean deleteById(@RequestBody Funeral funeral){
        int i = funeralService.deleteById(funeral.getId());
        if (i > 0){
            return true;
        }
        return false;
    }

    @PostMapping("/update")
    public boolean update(HttpServletRequest httpServletRequest){
        String fname = httpServletRequest.getParameter("fname");
        String fnumber = httpServletRequest.getParameter("fnumber");
        String fphone = httpServletRequest.getParameter("fphone");
        String faddress = httpServletRequest.getParameter("faddress");
        String id = httpServletRequest.getParameter("id");

        return funeralService.updateFuneral(fname, Integer.valueOf(fnumber), fphone, faddress, Integer.valueOf(id));
    }

}
