package com.hikvision.dms.mapper;

import com.hikvision.dms.model.Device;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Delete("<script>"+
            "delete from "+ TABLE_NAME + " where id in"
            + "<foreach item='item' index='index' collection='deviceIds' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    int deleteByIds(@Param("deviceIds") List<Integer> deviceIds);

    @Delete({"delete from ", TABLE_NAME, " where name=#{name}"})
    int deleteByName(String name);


    @Update({"update ", TABLE_NAME, "set name=#{name}, index_code=#{indexCode}, resource_type=#{resourceType} where id=#{id}"})
    int updateDevice(Device device);

    @Select({"deltefrom ", TABLE_NAME, " where id=#{id}"})
    Device selectById(int id);

    @Select({"select * from ", TABLE_NAME, " where name=#{name}"})
    Device selectByName(String name);

    @Select("<script>"
            + "select" + SELECT_FIELDS +   " from " + TABLE_NAME + " where id in "
            + "<foreach item='item' index='index' collection='deviceList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<Device> selectByIds(@Param("deviceList") List<Integer> deviceList);

}
