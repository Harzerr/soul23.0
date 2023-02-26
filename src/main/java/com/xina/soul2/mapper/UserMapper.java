package com.xina.soul2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xina.soul2.entity.User;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User>{

    @Update("update user set unickname = #{unickname}, uphone = #{uphone}, uemail = #{uemail} where id = #{id}")
    int userUpdate(@Param("unickname") String unickname,@Param("uphone") String uphone,@Param("uemail") String uemail,@Param("id") Integer id);

    @Update("update user set uheadimg = #{path} where id = #{id}")
    int imageUrlUpdate(@Param("path") String path, @Param("id") Integer id);

    @Delete("delete from user where id = #{id}")
    int myDeleteById(@Param("id") Integer id);

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user limit #{index} , #{pageSize}")
    List<User> pagerSelect(@Param("index") int index, @Param("pageSize") int pageSize);

    @Select("select * from user where uemail = #{uemail} and upassword = #{upassword}")
    User selectUserByEmail(@Param("uemail") String uemail, @Param("upassword") String upassword);

    @Select("select * from user where uphone = #{uphone} and upassword = #{upassword}")
    User selectUserByPhone(@Param("uphone") String uphone, @Param("upassword") String upassword);

    @Select("select * from user where uphone = #{uphone} or uemail = #{uemail}")
    User selectUser(@Param("uphone") String uphone, @Param("uemail") String uemail);

    @Insert("insert into user(uphone, uemail, upassword) values(#{uphone}, #{uemail}, #{upassword})")
    int insertUserNoNickname(User user);

    @Insert("insert into user(uphone, uemail, upassword, unickname) values(#{uphone}, #{uemail}, #{upassword}, #{unickname})")
    int insertUserHaveNickname(User user);

    @Select("select * from user where id = #{id}")
    User getOneById(@Param("id") Integer id);

}
