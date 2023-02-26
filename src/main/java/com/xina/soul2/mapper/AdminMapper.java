package com.xina.soul2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xina.soul2.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    @Update("update admin set aphone = #{aphone}, apassword = #{apassword} where id = #{id}")
    int updateAdmin(@Param("aphone") String aphone, @Param("apassword") String apassword, @Param("id") Integer id);

    @Delete("delete from admin where id = #{id}")
    int myDeleteById(@Param("id") Integer id);

    @Select("select * from admin")
    List<Admin> findAll();

    @Select("select * from admin limit #{index} , #{pageSize}")
    List<Admin> pagerSelect(@Param("index") int index, @Param("pageSize") int pageSize);
    @Select("select * from admin where aphone = #{aphone} and apassword = #{apassword}")
    Admin selectByPhoneAndPassword(@Param("aphone") String aphone, @Param("apassword") String apassword);
}
