package com.xina.soul2.service;

import com.xina.soul2.entity.Dead;
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

    public List<Dead> selectByKeyword(String keyword){
        String finalKey = "%"+keyword+"%";
        List<Dead> deads = deadMapper.selectByKeyword(finalKey);
        return deads;
    }

}
