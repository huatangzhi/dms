package com.hikvision.dms;

import com.hikvision.dms.mapper.DeviceMapper;
import com.hikvision.dms.mapper.LoginTicketMapper;
import com.hikvision.dms.mapper.UserDeviceMapper;
import com.hikvision.dms.mapper.UserMapper;
import com.hikvision.dms.model.Device;
import com.hikvision.dms.model.User;
import com.hikvision.dms.model.UserDeviceRecord;
import com.hikvision.dms.service.DeviceService;
import com.hikvision.dms.service.UserService;
import com.hikvision.dms.util.SHA256Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DmsApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(DmsApplication.class);

    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;


    @Autowired
    private UserDeviceMapper userDeviceMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void UserServiceTest(){

        String userName = "root2";
        String password = "123456";

        Map<String, Object> result = new HashMap<>();

        result = userService.updateUserPassword(userName, password);

        logger.info(result.toString());

    }

    @Test
    public void DeviceServiceTest() {
        int id = 1234;
        int indexCode = 1234;
        String resourceType = "CAMERA";

        Map<String, Object> result = new HashMap<>();
//        result = deviceService.addDevice(id, indexCode, resourceType);
//        result = deviceService.getDeviceById(1234);
       deviceService.deleteByName("2", 27);

    }

    @Test
    public void UserDeviceTest(){
//        UserDeviceRecord userDeviceRecord = new UserDeviceRecord();
//        userDeviceRecord.setDeviceId(1236);
//        userDeviceRecord.setUserId(1234);
//        userDeviceMapper.addUserDeviceRecord(userDeviceRecord);
//        List<Integer> resultList = userDeviceMapper.getUserDevicesId(1234);
//        logger.info(resultList.toString());

    }

    @Test
    public  void LoginTicketTest() {
        loginTicketMapper.delTicket(28);
    }

    @Test
    public void DeviceMapper() {
        List<Integer> list = new ArrayList<>();
        list.add(1234);
        list.add(1235);
        list.add(1236);

        int result  = deviceMapper.deleteByName("root1");

        logger.info("result" + String.valueOf(result));

    }

    @Test
    public void testBuilder() {
        UserBuild userBuild = new UserBuild.UserBuilder().build();

        User user = new User();
        for (int i = 5; i < 100; i++){
            user.setName(userBuild.getName() + i);
            user.setSalt(userBuild.getSalt());
            user.setPassword(SHA256Util.SHA256(user.getPassword() + user.getSalt()));
            userMapper.addUser(user);
        }

    }




}
