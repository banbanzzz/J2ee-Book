package com.book.service;

import com.book.dao.IBooksinfoDao;
import com.book.dao.IReturninfoDao;
import com.book.model.Returninfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturninfoService {

    @Autowired
    private IReturninfoDao return_dao;

    @Autowired
    private IBooksinfoDao book_dao;

    public List<Returninfo> getAllReturninfos(){
        List<Returninfo> lists = this.return_dao.getAllReturninfos();
        for(Returninfo list : lists){
            list.setReturn_book(this.book_dao.getBookById(list.getBook_id()));
        }
        return lists;
    }

    public List<Returninfo> getReturninfosByUser(String user_name){
        List<Returninfo> lists = this.return_dao.getReturninfosByUser(user_name);
        for(Returninfo list : lists){
            list.setReturn_book(this.book_dao.getBookById(list.getBook_id()));
        }
        return lists;
    }

    public void return_delete(long return_id){
        this.return_dao.return_delete(return_id);
    }

    public void return_insert(Returninfo returninfo){
        this.return_dao.return_insert(returninfo);
    }
}
