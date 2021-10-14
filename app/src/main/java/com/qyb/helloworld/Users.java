package com.qyb.helloworld;

public class Users {
    private String id;
    private String phonenumber;
    private String username;
    private String password;
    private String thumbnailfaceimage;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getThumbnailfaceimage() {
        return thumbnailfaceimage;
    }

    public void setThumbnailfaceimage(String thumbnailfaceimage) {
        this.thumbnailfaceimage = thumbnailfaceimage;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", thumbnailfaceimage='" + thumbnailfaceimage + '\'' +
                '}';
    }
}
