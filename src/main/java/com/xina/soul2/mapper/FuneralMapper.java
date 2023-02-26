package com.xina.soul2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xina.soul2.entity.Funeral;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FuneralMapper extends BaseMapper<Funeral> {

    @Update("update funeral set fname = #{fname}, fnumber = #{fnumber}, fphone = #{fphone}, faddress = #{faddress} where id = #{id}")
    int updateFuneral(@Param("fname") String fname, @Param("fnumber") Integer fnumber, @Param("fphone") String fphone, @Param("faddress") String faddress, @Param("id") Integer id);

    @Delete("delete from funeral where id = #{id}")
    int myDeleteById(@Param("id") Integer id);

    @Select("select * from funeral")
    List<Funeral> findAll();

    @Select("select * from funeral limit #{index}, #{pageSize}")
    List<Funeral> pagerSelect(@Param("index") int index, @Param("pageSize") int pageSize);


}
