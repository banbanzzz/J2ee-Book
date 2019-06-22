package com.book.service;

import com.book.dao.IUserinfoDao;
import com.book.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserinfoService {

    @Autowired
    private IUserinfoDao dao;

    public Userinfo login(String user_name, String user_pwd){
        Userinfo user = this.dao.getUserByname(user_name);
        if(user == null){
            return null;
        }
        if(user.getUser_pwd().equals(user_pwd))
            return user;
        return null;
    }

    public Userinfo getUserByname(String username){
        return this.dao.getUserByname(username);
    }

    public List<Userinfo> getAllUserinfos(){
        return this.dao.getAllUserinfos();
    }

    public Userinfo getUserById(long user_id){
        return this.getUserById(user_id);
    }

    public void user_insert(Userinfo user){
        this.dao.user_insert(user);
    }

    public void user_editNum(Integer num, String user_name){
        this.dao.user_editNum(num, user_name);
    }

    public void user_editstatus(Integer user_status, long user_id){
        this.dao.user_editstatus(user_status, user_id);
    }

    public void user_edit(Userinfo user){
        this.dao.user_edit(user);
    }

    public void user_editpwd(String user_pwd, String user_name){
        this.dao.user_editpwd(user_pwd,user_name);
    }
}




