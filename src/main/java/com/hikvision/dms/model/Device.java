package com.hikvision.dms.model;


public class Device {

    int id;
    String name;
    int indexCode;
    String resourceType;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(int indexCode) {
        this.indexCode = indexCode;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", indexCode=" + indexCode +
                ", resourceType='" + resourceType + '\'' +
                '}';
    }


}
