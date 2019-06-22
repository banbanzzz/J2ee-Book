package com.book.model;

import java.util.Date;

/**
 * 还书表实体对象
 * @author Wxj
 */
public class Returninfo {
    long return_id;

    String return_user; //还书用户  通过user_name找到用户

    Date return_date;

    Date borrow_date;

    long book_id; //书籍id 通过id找到数据信息

    String operator; //操作者

    Booksinfo return_book; //所还书籍对象

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    public long getReturn_id() {
        return return_id;
    }

    public void setReturn_id(long return_id) {
        this.return_id = return_id;
    }

    public String getReturn_user() {
        return return_user;
    }

    public void setReturn_user(String return_user) {
        this.return_user = return_user;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public Booksinfo getReturn_book() {
        return return_book;
    }

    public void setReturn_book(Booksinfo return_book) {
        this.return_book = return_book;
    }
}
