package com.qyb.helloworld;
public class LocMsg {
    private String recordid;
    private String groupid;
    private String userid;
    private Double latitude;
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getGroupid() {
        return groupid;
    }

    public String getRecordid() {
        return recordid;
    }

    public String getUserid() {
        return userid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
