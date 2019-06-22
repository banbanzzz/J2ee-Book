package com.book.model;

/**
 * 书籍实体对象
 * @author Wxj
 */
public class Booksinfo {
    long book_id;

    String book_name;

    String book_author;

    float book_price;

    String book_press;

    Integer book_num;

    Integer book_status; //书籍状态 1可借 0不可借

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public float getBook_price() {
        return book_price;
    }

    public void setBook_price(float book_price) {
        this.book_price = book_price;
    }

    public String getBook_press() {
        return book_press;
    }

    public void setBook_press(String book_press) {
        this.book_press = book_press;
    }

    public Integer getBook_num() {
        return book_num;
    }

    public void setBook_num(Integer book_num) {
        this.book_num = book_num;
    }

    public Integer getBook_status() {
        return book_status;
    }

    public void setBook_status(Integer book_status) {
        this.book_status = book_status;
    }
}
