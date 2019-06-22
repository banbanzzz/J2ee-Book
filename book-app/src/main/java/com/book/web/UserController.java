package com.book.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/booklist")
    public String getBooklist(){
        return "user/UserUi";
    }

    @GetMapping("/user/borrowlist")
    public String getBorrowlist(){
        return "user/borrowlist";
    }

    @GetMapping("/user/returnlist")
    public String getReturnlist(){
        return "user/returnlist";
    }
}
