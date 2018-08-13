package com.hikvision.dms.mapper;

import com.hikvision.dms.model.UserDeviceRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDeviceMapper {

    String TABLE_NAME = "user_device_record";
    String INSET_FIELDS = " user_id, device_id";
    String SELECT_FIELDS = " device_id";

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{userId},#{deviceId})"})
    int addUserDeviceRecord(UserDeviceRecord userDeviceRecord);


    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, "where user_id=#{userId}"})
    List<Integer> getUserDevicesId(int userId);

    @Delete({"delete from ", TABLE_NAME , " where user_id=#{userId} and device_id=#{deviceId}"})
    int deleteDeviceById(@Param("userId")int userId, @Param("deviceId")int deviceId);
}
