package com.book.model;

import java.util.Date;

/**
 * 借书表实体对象
 * @author Wxj
 */
public class Borrowinfo {
    long borrow_id;

    String borrow_user;

    long book_id; //书籍id 通过id找到数据信息

    Date borrow_date;

    String operator; //操作者

    Booksinfo borrow_book; //所借书籍对象

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public long getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(long borrow_id) {
        this.borrow_id = borrow_id;
    }

    public String getBorrow_user() {
        return borrow_user;
    }

    public void setBorrow_user(String borrow_user) {
        this.borrow_user = borrow_user;
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public Date getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    public Booksinfo getBorrow_book() {
        return borrow_book;
    }

    public void setBorrow_book(Booksinfo borrow_book) {
        this.borrow_book = borrow_book;
    }
}





