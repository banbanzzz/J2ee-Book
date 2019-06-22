package com.book.service;

import com.book.dao.IBooksinfoDao;
import com.book.model.Booksinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksinfoService {

    @Autowired
    private IBooksinfoDao dao;

    public List<Booksinfo> getAllBooks(){
        return this.dao.getAllBooks();
    }

    public List<Booksinfo> getBooksByName(String book_name){
        return this.dao.getBooksByName(book_name);
    }

    public void book_insert(Booksinfo book){
        this.dao.book_insert(book);
    }

    public Booksinfo getBookById(long book_id){
        return this.dao.getBookById(book_id);
    }

    public void book_edit(Booksinfo book){
        this.dao.book_edit(book);
    }

    public void book_editNum(Integer num, long book_id){
        this.dao.book_editNum(num, book_id);
    }

    public void book_editState(Integer state, long book_id){
        this.dao.book_editState(state, book_id);
    }
}
