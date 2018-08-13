package com.hikvision.dms.mapper;

import com.hikvision.dms.model.Admin;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AdminMapper {

    String TABLE_NAME = "admin";
    String INSET_FIELDS = " name, password, salt";
    String SELECT_FIELDS = " id, name, password, salt";

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{name},#{password},#{salt})"})
    int addAdmin(Admin admin);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
    Admin selectByName(String name);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Admin selectById(int id);

    @Update({"update ", TABLE_NAME, " set password=#{password} where id=#{id}"})
    void updatePassword(Admin admin);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);
}
