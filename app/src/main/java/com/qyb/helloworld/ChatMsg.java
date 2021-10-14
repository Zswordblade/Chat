package com.qyb.helloworld;
import java.io.Serializable;

public class ChatMsg implements Serializable{
    private static final long serialVersionUID = 3611169682695799175L;

    private String senderId;		// 发送者的用户id
    private String username;
    private String receiverId;		// 接受者的用户id
    private String msg;				// 聊天内容

    public String getSenderId() {
        return senderId;
    }
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    public String getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }
}
