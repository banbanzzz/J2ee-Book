package com.book.api;

import com.book.model.Admininfo;
//import com.book.redis.RedisPoolUtil;
import com.book.service.AdmininfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdmininfoApi {

    @Autowired
    private AdmininfoService service;

    @RequestMapping("/login")
    public Admininfo admin_login(HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        String admin_name = request.getParameter("admin_name");
        String admin_pwd = request.getParameter("admin_pwd");
        Admininfo admin = this.service.admin_login(admin_name,admin_pwd);
        if(admin == null) {
            Admininfo admininfo = new Admininfo();
            return admininfo;
        }else {
            request.getSession().setAttribute("admin",admin);
           // RedisPoolUtil.setEx("admin_name",admin.getAdmin_name(),60*60);
            Admininfo admininfo = new Admininfo();
            admininfo.setAdmin_name(admin.getAdmin_name());
            return admininfo;
        }
    }

    @RequestMapping("/logout")
    public ResponseModel admin_logout(HttpServletRequest request, @Param("admin_name") String admin_name){
        try{
            HttpSession session = request.getSession();
            session.removeAttribute("admin");
            //RedisPoolUtil.del(admin_name);
            return new ResponseModel("注销成功！");
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseModel(false,"注销失败," + ex.getMessage());
        }
    }

    @RequestMapping("/editPwd")
    public ResponseModel admin_editPwd(HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        String admin_oldpwd = request.getParameter("admin_oldpwd");
        String admin_newpwd = request.getParameter("admin_newpwd");
        String admin_name = request.getParameter("admin_name");
        if((this.service.getAdmininfoByName(admin_name)).getAdmin_pwd().equals(admin_oldpwd)){
            this.service.admin_editPwd(admin_newpwd, admin_name);
            return new ResponseModel("修改成功,请重新登录！");
        }else {
            return new ResponseModel(false,"修改失败，原密码错误！");
        }
    }
}
