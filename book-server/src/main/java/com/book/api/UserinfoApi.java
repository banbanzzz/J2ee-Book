package com.book.api;

import com.book.model.Userinfo;
//import com.book.redis.RedisPoolUtil;
import com.book.service.UserinfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController //返回数据内容
@RequestMapping("/user")
public class UserinfoApi {

    @Autowired
    private UserinfoService user_service;

    /**
     * 处理登录/login
     */
    @RequestMapping("/login")
    public Userinfo user_login(HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("utf-8");
        String user_name = request.getParameter("user_name");
        String user_pwd = request.getParameter("user_pwd");
        Userinfo userinfo = this.user_service.login(user_name,user_pwd);
        if(userinfo == null){
            Userinfo user = new Userinfo();
            return user;
        }else {
            request.getSession().setAttribute("user",userinfo);
           // RedisPoolUtil.setEx(userinfo.getUser_name(),userinfo.getUser_name(),60*60);
            Userinfo user = new Userinfo();
            user.setUser_name(userinfo.getUser_name());
            return user;
        }
    }

    /**
     * 处理注销/logout
     */
    @RequestMapping("/logout")
    public ResponseModel user_logout(HttpServletRequest request,@Param("user_name")String user_name){
        try{
            HttpSession session = request.getSession();
            session.removeAttribute("user");
           // RedisPoolUtil.del(user_name);
            return new ResponseModel("注销成功！");
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseModel(false,"注销失败！"+ ex.getMessage());
        }
    }

    @RequestMapping("/queryUserByName")
    public Userinfo queryUserByName(String user_name){
        return this.user_service.getUserByname(user_name);
    }

    @RequestMapping("/list")
    public List<Userinfo> list(){
        return this.user_service.getAllUserinfos();
    }

    @RequestMapping("/stop")
    public ResponseModel user_stop(long user_id){
        try{
            this.user_service.user_editstatus(0, user_id);
            return new ResponseModel("注销成功！");
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseModel(false,"注销失败" + ex.getMessage());
        }
    }

    @RequestMapping("/activate")
    public ResponseModel user_activate(long user_id){
        try{
            this.user_service.user_editstatus(1, user_id);
            return new ResponseModel("激活成功！");
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseModel(false,"激活失败" + ex.getMessage());
        }
    }

    @RequestMapping("/editPwd")
    public ResponseModel user_editPwd(HttpServletRequest request) throws  Exception{
        request.setCharacterEncoding("utf-8");
        String user_oldpwd = request.getParameter("user_oldpwd");
        String user_newpwd = request.getParameter("user_newpwd");
        String user_name = request.getParameter("user_name");
        if((this.user_service.getUserByname(user_name)).getUser_pwd().equals(user_oldpwd)){
            this.user_service.user_editpwd(user_newpwd, user_name);
            return new ResponseModel("修改成功,请重新登录！");
        }else {
            return new ResponseModel(false,"修改失败，原密码错误！");
        }
    }

    @RequestMapping("/add")
    public ResponseModel user_insert(Userinfo user){
        try{
            if((this.user_service.getUserByname(user.getUser_name())) == null){
                this.user_service.user_insert(user);
                return new ResponseModel("创建成功！");
            }else {
                return new ResponseModel(false,"创建失败，名称重复！");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseModel(false,"创建失败！" + ex.getMessage());
        }
    }

   @RequestMapping("/edit")
   public ResponseModel user_edit(Userinfo user) {
       Userinfo userinfo = this.user_service.getUserByname(user.getUser_name());
       //如果等于空，可修改user_name
       if (userinfo == null) {
           this.user_service.user_edit(user);
           return new ResponseModel("保存成功！");
       } else {
           //有此人 判断是不是本人
           if (userinfo.getUser_id() == user.getUser_id()) {
               this.user_service.user_edit(user);
               return new ResponseModel("保存成功！");
           } else {
               return new ResponseModel(false, "保存失败，名称重复！");
           }
       }
   }
}
