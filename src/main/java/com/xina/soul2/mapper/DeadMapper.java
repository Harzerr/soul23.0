package com.xina.soul2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xina.soul2.entity.Dead;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeadMapper extends BaseMapper<Dead> {

    @Select("select * from dead where dname like #{keyword} or dsex like #{keyword} or ddescribe like #{keyword} or daddress like #{keyword} limit #{i}, #{pageSize}")
    List<Dead> selectByKeyword(@Param("keyword") String keyword, @Param("i") int i, @Param("pageSize") int pageSize);

    @Select("select count(*) from dead where dname like #{keyword} or dsex like #{keyword} or ddescribe like #{keyword} or daddress like #{keyword}")
    int getCount(@Param("keyword") String keyword);

    @Update("update dead set dname = #{dname}, dsex = #{dsex} where id = #{id}")
    int updateDead(@Param("dname") String dname, @Param("dsex") String dsex, @Param("id") Integer id);

    @Update("update dead set dphoto = #{path} where id = #{id}")
    int updateImage(@Param("path") String path, @Param("id") Integer id);

    @Delete("delete from dead where id = #{id}")
    int myDeleteById(@Param("id") Integer id);

    @Select("select * from dead")
    List<Dead> findAll();

    @Select("select * from dead limit #{index}, #{pageSize}")
    List<Dead> pagerSelect(@Param("index") int index, @Param("pageSize") int pageSize);

}
