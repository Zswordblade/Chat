package com.qyb.helloworld;

public class UserVo {
    private String id;
    private String phonenumber;
    private String username;
    private String thumbnailfaceimage;
    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return phoneNumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * @param phonenumber
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * @return userName
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return thumbnailFaceImage
     */
    public String getThumbnailfaceimage() {
        return thumbnailfaceimage;
    }

    /**
     * @param thumbnailfaceimage
     */
    public void setThumbnailfaceimage(String thumbnailfaceimage) {
        this.thumbnailfaceimage = thumbnailfaceimage;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "id='" + id + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", username='" + username + '\'' +
                ", thumbnailfaceimage='" + thumbnailfaceimage + '\'' +
                '}';
    }

}
