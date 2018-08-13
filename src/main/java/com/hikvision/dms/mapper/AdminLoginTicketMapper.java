package com.hikvision.dms.mapper;

import com.hikvision.dms.model.AdminLoginTicket;
import com.hikvision.dms.model.LoginTicket;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AdminLoginTicketMapper {
    String TABLE_NAME = "admin_login_ticket";
    String INSERT_FIELDS = " user_id, expired, status, ticket ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{userId},#{expired},#{status},#{ticket})"})
    int addTicket(AdminLoginTicket ticket);

    @Delete({"delete from ", TABLE_NAME, "where user_id=#{userID}"})
    int delTicket(int userId);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where ticket=#{ticket}"})
    AdminLoginTicket selectByTicket(String ticket);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where user_id=#{userId}"})
    AdminLoginTicket selectByUserId(int userId);

    @Update({"update ", TABLE_NAME, " set status=#{status} where ticket=#{ticket}"})
    void updateStatus(@Param("ticket") String ticket, @Param("status") int status);
}
