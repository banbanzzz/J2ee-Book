package com.book.dao;

import com.book.model.Admininfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface IAdmininfoDao {

    @Select("select * from admininfo where admin_name = #{admin_name}")
    public Admininfo getAdminByName(String admin_name);

    @Update("update admininfo set admin_pwd = #{admin_pwd} where admin_name = #{admin_name}")
    public void admin_editPwd(@Param("admin_pwd") String admin_pwd,@Param("admin_name") String admin_name);
}
