package com.xina.soul2.service;

import com.xina.soul2.entity.Funeral;
import com.xina.soul2.mapper.FuneralMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FuneralService {
    @Resource
    private FuneralMapper funeralMapper;

    public int getCount(){
        List<Funeral> all = funeralMapper.findAll();
        return all.size();
    }

    public int deleteById(Integer id){
        return funeralMapper.myDeleteById(id);
    }
    public List<Funeral> selectByPager(int index, int pageSize){
        List<Funeral> funerals = funeralMapper.pagerSelect(index, pageSize);
        return funerals;
    }

    public boolean updateFuneral(String fname, Integer fnumber, String fphone, String faddress, Integer id){
        try {
            int i = funeralMapper.updateFuneral(fname, fnumber, fphone, faddress, id);
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
