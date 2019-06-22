package com.book.service;

import com.book.dao.IBooksinfoDao;
import com.book.dao.IBorrowinfoDao;
import com.book.model.Booksinfo;
import com.book.model.Borrowinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowinfoService {

    @Autowired
    private IBorrowinfoDao borrow_dao;

    @Autowired
    private IBooksinfoDao books_dao;

    public List<Borrowinfo> getAllBorrowinfos(){
        List<Borrowinfo> lists = this.borrow_dao.getAllBorrowinfos();
        for(Borrowinfo list : lists){
            list.setBorrow_book(this.books_dao.getBookById(list.getBook_id()));
        }
        return lists;
    }

    public List<Borrowinfo> getBorrowinfosByUser(String user_name){
        List<Borrowinfo> lists = this.borrow_dao.getBorrowinfosByUser(user_name);
        for (Borrowinfo list: lists){
            list.setBorrow_book(this.books_dao.getBookById(list.getBook_id()));
        }
        return lists;
    }

    public Borrowinfo getBorrowinfoById(long borrow_id) {
        Borrowinfo borrowinfo = this.borrow_dao.getBorrowinfoById(borrow_id);
        borrowinfo.setBorrow_book(this.books_dao.getBookById(borrowinfo.getBook_id()));
        return borrowinfo;
    }

    public void borrow_delete(long borrow_id){
        this.borrow_dao.borrow_delete(borrow_id);
    }

    public void borrow_deleteByBookId(long book_id){
        this.borrow_dao.borrow_deleteByBookId(book_id);
    }

    public void borrow_insert(Borrowinfo borrowinfo){
        this.borrow_dao.borrow_insert(borrowinfo);
    }
}
