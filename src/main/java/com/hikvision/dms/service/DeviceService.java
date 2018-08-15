package com.hikvision.dms.service;

import com.hikvision.dms.mapper.DeviceMapper;
import com.hikvision.dms.mapper.UserDeviceMapper;
import com.hikvision.dms.model.Device;
import com.hikvision.dms.model.UserDeviceRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
public class DeviceService {

    private final static Logger log = LoggerFactory.getLogger(DeviceService.class);

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private UserDeviceMapper userDeviceMapper;

    public Device selectDeviceByName(String name) {
        return deviceMapper.selectByName(name);
    }

    public Map<String, Object> addDevice( String name, int indexCode, String resourceType, int userId) {
        Map<String, Object> resultMap = new HashMap<>();


        if (StringUtils.isEmpty(String.valueOf(indexCode))) {
            resultMap.put("msg", "设备类型编号为空");
            return resultMap;
        }

        if (StringUtils.isBlank(resourceType)) {
            resultMap.put("msg", "设备类型为空");
            return resultMap;
        }

        if (selectDeviceByName(name) != null) {
            resultMap.put("msg", "该设备已存在");
            return resultMap;
        } else {

            Device device = new Device();
            device.setName(name);
            device.setIndexCode(indexCode);
            device.setResourceType(resourceType);
            deviceMapper.addDevice(device);

            UserDeviceRecord userDeviceRecord = new UserDeviceRecord();
            userDeviceRecord.setUserId(userId);
            userDeviceRecord.setDeviceId(device.getId());

            userDeviceMapper.addUserDeviceRecord(userDeviceRecord);
            resultMap.put("msg", "设备添加成功");

        }
        return resultMap;
    }

    public Map<String, Object> updateDeviceByName(String name, int indexCode, String resourceType) {

        Map<String, Object> resultMap = new HashMap<>();
        if (deviceMapper.deleteByName(name) != 0){
            Device device = new Device();
            device.setName(name);
            device.setIndexCode(indexCode);
            device.setResourceType(resourceType);
            deviceMapper.addDevice(device);
            resultMap.put("msg", "设备更新成功");

        } else {
            resultMap.put("msg", "设备更新失败");
        }
        return resultMap;
    }

    public Map<String, Object> getDeviceByName(String name) {
        Map<String,Object> reslutMap = new HashMap<String, Object>();
        Device device = selectDeviceByName(name);
        if ( device == null) {
            reslutMap.put("msg", "没有该设备");
            return reslutMap;
        } else {
            reslutMap.put("device", device);
        }
        return reslutMap;
    }

    public Map<String, Object> deleteByName(String deviceName, int userId) {
        Map<String,Object> reslutMap = new HashMap<String, Object>();

        Device device = selectDeviceByName(deviceName);
        if ( device == null) {
            reslutMap.put("msg", "没有该设备");
            return reslutMap;
        } else {
            deviceMapper.deleteByName(deviceName);
            userDeviceMapper.deleteDeviceById(userId, device.getId());
            reslutMap.put("msg", "删除设备成功");
        }
        return reslutMap;
    }

    public List<Device> getDeviceByUserId(int userId) {
        List<Integer> deviceIds = userDeviceMapper.getUserDevicesById(userId);
        if (!deviceIds.isEmpty()) {
            List<Device> deviceList = deviceMapper.selectByIds(deviceIds);
            return deviceList;
        } else {
            return null;
        }

    }
}
