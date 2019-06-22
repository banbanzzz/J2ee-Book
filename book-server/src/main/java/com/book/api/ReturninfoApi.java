package com.book.api;

import com.book.model.Booksinfo;
import com.book.model.Borrowinfo;
import com.book.model.Returninfo;
import com.book.service.BooksinfoService;
import com.book.service.BorrowinfoService;
import com.book.service.ReturninfoService;
import com.book.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/return")
public class ReturninfoApi {

    @Autowired
    private ReturninfoService service;

    @Autowired
    private BooksinfoService book_service;

    @Autowired
    private UserinfoService user_service;

    @Autowired
    private BorrowinfoService borrow_service;

    @RequestMapping("/list")
    public List<Returninfo> getAllReturninfos(){
        return this.service.getAllReturninfos();
    }

    @RequestMapping("/queryByUser")
    public List<Returninfo> getReturninfosByUser(String user_name){
        return this.service.getReturninfosByUser(user_name);
    }


    @RequestMapping("adminReturn")
    public ResponseModel ReturnByAdmin(long borrow_id,String borrow_user, String admin_name){
        try {
            long book_id = this.borrow_service.getBorrowinfoById(borrow_id).getBook_id();
            Booksinfo book = this.book_service.getBookById(book_id);
            //从borrow表删除  加入return表  库存+1
            this.book_service.book_editNum(-1, book_id);
            this.user_service.user_editNum(-1, borrow_user);
            Returninfo returninfo = new Returninfo();
            returninfo.setBook_id(book_id);
            returninfo.setOperator(admin_name);
            returninfo.setBorrow_date(this.borrow_service.getBorrowinfoById(borrow_id).getBorrow_date());
            returninfo.setReturn_date(new Date());
            returninfo.setReturn_user(borrow_user);
            returninfo.setReturn_book(book);
            this.borrow_service.borrow_delete(borrow_id);
            this.service.return_insert(returninfo);
            return new ResponseModel("还书成功！");
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseModel(false,"还书失败," + ex.getMessage());
        }
    }

}
