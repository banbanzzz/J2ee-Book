package com.book.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @GetMapping("/login")
    public String login(){
        return "userlogin";
    }

    @GetMapping("/adminlogin")
    public String admin_login(){
        return "adminlogin";
    }

}
