package com.book.dao;

import com.book.model.Userinfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface IUserinfoDao {

    @Select("select * from userinfo where user_name = #{username}")
    public Userinfo getUserByname(String username);

    @Select("select * from userinfo where user_id = #{user_id}")
    public Userinfo getUserById(long user_id);

    @Select("select * from userinfo")
    public List<Userinfo> getAllUserinfos();

    @Insert("insert into userinfo(user_name, user_pwd, user_tel, user_num) values(#{user_name}, #{user_pwd}, #{user_tel}, #{user_num})")
    public void user_insert(Userinfo user);

    @Update("update userinfo set user_num = user_num - #{num} where user_name = #{user_name}")
    public void user_editNum(@Param("num") Integer num, @Param("user_name") String user_name);

    @Update("update userinfo set user_status = #{user_status} where user_id = #{user_id}")
    public void user_editstatus(@Param("user_status") Integer user_status,@Param("user_id") long user_id);

    @Update("update userinfo set user_name = #{user_name}, user_pwd = #{user_pwd}, user_tel = #{user_tel}, user_num = #{user_num} where user_id = #{user_id}")
    public void user_edit(Userinfo user);

    @Update("update userinfo set user_pwd = #{user_pwd} where user_name = #{user_name}")
    public void user_editpwd(@Param("user_pwd") String user_pwd,@Param("user_name") String user_name);
}
