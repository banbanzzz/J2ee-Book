package com.book.api;

import com.book.model.Booksinfo;
import com.book.model.Borrowinfo;
import com.book.model.Userinfo;
import com.book.service.BooksinfoService;
import com.book.service.BorrowinfoService;
import com.book.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowinfoApi {

    @Autowired
    private BorrowinfoService service;

    @Autowired
    private BooksinfoService book_service;

    @Autowired
    private UserinfoService user_service;

    @RequestMapping("/list")
    public List<Borrowinfo> getAllBorrowinfos() {
        return this.service.getAllBorrowinfos();
    }

    @RequestMapping("/queryByUser")
    public List<Borrowinfo> getBorrowinfosByUser(String user_name){
        return this.service.getBorrowinfosByUser(user_name);
    }

    /**
     * 管理员办理借书业务
     * @return
     */
    @RequestMapping("/adminBorrow")
    public ResponseModel BorrowByAdmin(long book_id, String user_name, String admin_name) {
        Booksinfo book = this.book_service.getBookById(book_id);
        Userinfo user = this.user_service.getUserByname(user_name);
        if (user != null) {
            if (user.getUser_status() == 1) {
                if (user.getUser_num() > 0) {
                    if (book.getBook_status() == 1 && book.getBook_num() > 0) {
                        this.book_service.book_editNum(1, book_id);
                        this.user_service.user_editNum(1, user_name);
                        Borrowinfo borrowinfo = new Borrowinfo();
                        borrowinfo.setBook_id(book_id);
                        borrowinfo.setBorrow_user(user_name);
                        borrowinfo.setBorrow_date(new Date());
                        borrowinfo.setOperator(admin_name);
                        borrowinfo.setBorrow_book(book);
                        this.service.borrow_insert(borrowinfo);
                        return new ResponseModel("借书成功！");
                    } else {
                        return new ResponseModel(false, "该书不可借，借书失败！");
                    }
                } else {
                    return new ResponseModel(false, "该用户已超过借书的最大数量，借书失败！");
                }
            } else {
                return new ResponseModel(false, "该用户已注销，借书失败！");
            }
        }else{
            return new ResponseModel(false,"无此用户！借书失败！");
        }
    }

}
