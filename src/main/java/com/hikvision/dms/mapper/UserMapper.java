package com.hikvision.dms.mapper;

import com.hikvision.dms.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    String TABLE_NAME = "user";
    String INSET_FIELDS = " name, password, salt";
    String SELECT_FIELDS = " id, name, password, salt";
    String VIEW_FIELDS = " id, name, password";


    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{name},#{password},#{salt})"})
    int addUser(User user);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
    User selectByName(String name);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    User selectById(int id);

    @Select({"select ", VIEW_FIELDS, " from ", TABLE_NAME})
    List<User> selectAllUser();

    @Update({"update ", TABLE_NAME, " set password=#{password} where id=#{id}"})
    void updatePassword(User user);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);
}
