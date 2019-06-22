package com.book.api;

import com.book.model.Booksinfo;
import com.book.model.Borrowinfo;
import com.book.model.Returninfo;
import com.book.model.Userinfo;
import com.book.service.BooksinfoService;
import com.book.service.BorrowinfoService;
import com.book.service.ReturninfoService;
import com.book.service.UserinfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BooksinfoApi {

    @Autowired
    private BooksinfoService service;

    @Autowired
    private UserinfoService user_service;

    @Autowired
    private BorrowinfoService borrow_service;

    @Autowired
    private ReturninfoService return_service;

    @RequestMapping("/list")
    public List<Booksinfo> getAllBooks() {
        return this.service.getAllBooks();
    }

    @RequestMapping("/queryByName")
    public List<Booksinfo> getBooksByName(String book_name) {
        return this.service.getBooksByName(book_name);
    }

    @RequestMapping("/add")
    public ResponseModel book_insert(Booksinfo book){
        try{
            this.service.book_insert(book);
            return new ResponseModel("录入成功！");
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseModel(false,"录入失败！" + ex.getMessage());
        }
    }

    @RequestMapping("/edit")
    public ResponseModel book_edit(Booksinfo book){
        try{
            this.service.book_edit(book);
            return new ResponseModel("修改成功！");
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseModel(false,"修改失败！" + ex.getMessage());
        }
    }

    @RequestMapping("/stop")
    public ResponseModel book_stop(long book_id){
        try{
            this.service.book_editState(0, book_id);
            return new ResponseModel("设置成功！");
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseModel(false,"设置失败！" + ex.getMessage());
        }
    }

    @RequestMapping("/activate")
    public ResponseModel book_activite(long book_id){
        try{
            this.service.book_editState(1, book_id);
            return new ResponseModel("设置成功！");
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseModel(false,"设置失败！" + ex.getMessage());
        }
    }

    /**
     * 处理用户借书请求
     * @param book_id
     * @param user_name
     * @return
     */
    @RequestMapping("/borrow")
    public ResponseModel book_borrow(@Param("book_id") long book_id,@Param("user_name") String user_name){
            Booksinfo book = this.service.getBookById(book_id);
            if(this.user_service.getUserByname(user_name).getUser_status() == 1) {
                if ((this.user_service.getUserByname(user_name)).getUser_num() > 0) {
                    if (book.getBook_status() == 1 && book.getBook_num() > 0) {
                        this.service.book_editNum(1, book_id);
                        this.user_service.user_editNum(1, user_name);
                        Borrowinfo borrowinfo = new Borrowinfo();
                        borrowinfo.setBook_id(book_id);
                        borrowinfo.setBorrow_user(user_name);
                        borrowinfo.setBorrow_date(new Date());
                        borrowinfo.setOperator(user_name);
                        borrowinfo.setBorrow_book(book);
                        this.borrow_service.borrow_insert(borrowinfo);
                        return new ResponseModel("借书成功！");
                    } else {
                        return new ResponseModel(false, "该书不可借，请联系管理员！");
                    }
                } else {
                    return new ResponseModel(false, "您已超过借书的最大数量，请联系管理员！");
                }
            }else{
                return new ResponseModel(false,"该用户已注销，请联系管理员！");
            }
    }

    /**
     * 处理用户还书请求
     * @param borrow_id
     * @param user_name
     * @return
     */
    @RequestMapping("/return")
    public ResponseModel book_return(@Param("borrow_id") long borrow_id,@Param("user_name") String user_name){
        try {
            long book_id = this.borrow_service.getBorrowinfoById(borrow_id).getBook_id();
            Booksinfo book = this.service.getBookById(book_id);
            //从borrow表删除  加入return表  库存+1
            this.service.book_editNum(-1, book_id);
            this.user_service.user_editNum(-1, user_name);
            Returninfo returninfo = new Returninfo();
            returninfo.setBook_id(book_id);
            returninfo.setOperator(user_name);
            returninfo.setBorrow_date(this.borrow_service.getBorrowinfoById(borrow_id).getBorrow_date());
            returninfo.setReturn_date(new Date());
            returninfo.setReturn_user(user_name);
            returninfo.setReturn_book(book);
            this.borrow_service.borrow_delete(borrow_id);
            this.return_service.return_insert(returninfo);
            return new ResponseModel("还书成功！");
        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseModel(false,"还书失败，请联系管理员！");
        }
    }


}
