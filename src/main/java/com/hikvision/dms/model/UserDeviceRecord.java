package com.hikvision.dms.model;

public class UserDeviceRecord {

    private int userId;

    private int deviceId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "UserDeviceRecord{" +
                "userId=" + userId +
                ", deviceId=" + deviceId +
                '}';
    }
}
