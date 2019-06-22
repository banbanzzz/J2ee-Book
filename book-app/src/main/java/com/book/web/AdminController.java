package com.book.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping("/book/list")
    public String bookindex(){
        return "admin/bookindex";
    }

    @GetMapping("/user/list")
    public String userindex(){
        return "admin/userindex";
    }

    @GetMapping("/book/borrow")
    public String borrowindex(){
        return "admin/borrowindex";
    }

    @GetMapping("/book/return")
    public String returnindex(){
        return "admin/returnindex";
    }
}
