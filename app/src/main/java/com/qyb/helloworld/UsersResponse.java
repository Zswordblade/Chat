package com.qyb.helloworld;

public class UsersResponse {
    private String status;
    private String msg;
    private Users data;//data不能改
    private String ok;
    public String getMsg() { return msg; }
    public String getStatus() { return status; }
    public String getOk() { return ok; }
    public Users getData() {
        return data;
    }
    public void setData(Users data) {
        this.data = data;
    }
    public void setOk(String ok) { this.ok = ok; }
    public void setMsg(String msg) { this.msg = msg; }
    public void setStatus(String status) { this.status = status; }
}
