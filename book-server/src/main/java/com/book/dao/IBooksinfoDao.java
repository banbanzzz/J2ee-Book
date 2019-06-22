package com.book.dao;

import com.book.model.Booksinfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface IBooksinfoDao {

    @Select("select * from booksinfo")
    public List<Booksinfo> getAllBooks();

    @Select("select * from booksinfo where book_name like concat('%',#{book_name},'%')")
    public List<Booksinfo> getBooksByName(String book_name);

    @Select("select * from booksinfo where book_id = #{book_id}")
    public Booksinfo getBookById(long book_id);

    @Insert("insert into booksinfo(book_name, book_author, book_price, book_press, book_num) values(#{book_name}, #{book_author}, #{book_price}, #{book_press}, #{book_num})")
    public void book_insert(Booksinfo book);

    @Update("update booksinfo set book_name = #{book_name}, book_author = #{book_author}, book_price = #{book_price}, book_press = #{book_press}, book_num = #{book_num} where book_id = #{book_id}")
    public void book_edit(Booksinfo book);

    @Update("update booksinfo set book_num = book_num - #{num} where book_id = #{book_id}")
    public void book_editNum(@Param("num") Integer num, @Param("book_id") long book_id);

    @Update("update booksinfo set book_status = #{state} where book_id = #{book_id}")
    public void book_editState(@Param("state") Integer state, @Param("book_id") long book_id);

}
