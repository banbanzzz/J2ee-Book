package com.book.dao;

import com.book.model.Borrowinfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface IBorrowinfoDao {

    @Select("select * from borrowinfo")
    public List<Borrowinfo> getAllBorrowinfos();

    @Select("select * from borrowinfo where borrow_user = #{user_name}")
    public List<Borrowinfo> getBorrowinfosByUser(String user_name);

    @Select("select * from borrowinfo where borrow_id = #{borrow_id}")
    public Borrowinfo getBorrowinfoById(long borrwor_id);

    @Delete("delete from borrowinfo where borrow_id = #{borrow_id}")
    public void borrow_delete(long borrow_id);

    @Delete("delete from borrowinfo where book_id = #{book_id}")
    public void borrow_deleteByBookId(long book_id);

    @Insert("insert into borrowinfo(borrow_user, book_id, borrow_date, operator) values(#{borrow_user}, #{book_id}, #{borrow_date}, #{operator})")
    public void borrow_insert(Borrowinfo borrowinfo);

}
