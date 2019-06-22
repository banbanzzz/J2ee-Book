package com.book.service;

import com.book.dao.IAdmininfoDao;
import com.book.model.Admininfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmininfoService {

    @Autowired
    private IAdmininfoDao dao;

    public Admininfo admin_login(String admin_name, String admin_pwd){
        Admininfo admin = this.dao.getAdminByName(admin_name);
        if(admin == null)  return null;
        if(admin.getAdmin_pwd().equals(admin_pwd)) return admin;
        return null;
    }

    public Admininfo getAdmininfoByName(String admin_name){
        return this.dao.getAdminByName(admin_name);
    }

    public void admin_editPwd(String admin_pwd, String admin_name){
        this.dao.admin_editPwd(admin_pwd, admin_name);
    }
}
