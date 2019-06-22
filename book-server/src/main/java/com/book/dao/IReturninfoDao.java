package com.book.dao;

import com.book.model.Returninfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface IReturninfoDao {

    @Select("select * from returninfo")
    public List<Returninfo> getAllReturninfos();

    @Select("select * from returninfo where return_user = #{user_name}")
    public List<Returninfo> getReturninfosByUser(String user_name);

    @Delete("delete from returninfo where return_id = #{return_id}")
    public void return_delete(long return_id);

    @Insert("insert into returninfo(return_user, book_id, borrow_date, return_date, operator) values(#{return_user}, #{book_id},#{borrow_date}, #{return_date}, #{operator})")
    public void return_insert(Returninfo returninfo);
}
