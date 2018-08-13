package com.hikvision.dms.mapper;

import com.hikvision.dms.model.Device;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DeviceMapper {

    String TABLE_NAME = "device";
    String INSET_FIELDS = " name, index_code, resource_type";
    String SELECT_FIELDS = " id, name, index_code, resource_type";

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{name},#{indexCode},#{resourceType})"})
    int addDevice(Device device);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    int deleteById(int id);

    @Delete({"delete from ", TABLE_NAME, " where name=#{name}"})
    int deleteByName(String name);

    @Update({"update ", TABLE_NAME, "set name=#{name}, index_code=#{indexCode}, resource_type=#{resourceType} where id=#{id}"})
    int updateDevice(Device device);

    @Select({"select * from ", TABLE_NAME, " where id=#{id}"})
    Device selectById(int id);

    @Select({"select * from ", TABLE_NAME, " where name=#{name}"})
    Device selectByName(String name);

}
