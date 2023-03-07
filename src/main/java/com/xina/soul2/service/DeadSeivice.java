package com.xina.soul2.service;

import com.xina.soul2.entity.Dead;
import com.xina.soul2.entity.DeadPager;
import com.xina.soul2.mapper.DeadMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeadSeivice {
    @Resource
    private DeadMapper deadMapper;

    public List<Dead> getAllDead(){
        List<Dead> all = deadMapper.findAll();
        return all;
    }

    public int deleteById(Integer id){
        return deadMapper.myDeleteById(id);
    }
    public int getCount(){
        List<Dead> all = deadMapper.findAll();
        return all.size();
    }

    public List<Dead> selectByPager(int index, int pageSize){
        List<Dead> deads = deadMapper.pagerSelect(index, pageSize);
        return deads;
    }

    public boolean updateDead(String name, String sex, Integer id){
        try {
            int i = deadMapper.updateDead(name, sex, id);
            if (i > 0){
                return true;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateImage(String path, Integer id){
        try {
            int i = deadMapper.updateImage(path, id);
            if (i > 0){
                return true;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public DeadPager selectByKeyword(String keyword, int pageIndex, int pageSize){
        String finalKey = "%"+keyword+"%";
        int i = (pageIndex - 1) * pageSize;
        List<Dead> deads = deadMapper.selectByKeyword(finalKey, i, pageSize);
        int count = deadMapper.getCount(finalKey);
        DeadPager deadPager = new DeadPager(deads, count);
        return deadPager;
    }

}
