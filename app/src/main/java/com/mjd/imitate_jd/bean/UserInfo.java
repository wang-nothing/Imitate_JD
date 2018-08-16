package com.mjd.imitate_jd.bean;

/**
 * Created by admin on 2018-8-16.
 */

public class UserInfo {
    private String userName;
    private String password;
    private String uid;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {

        return uid;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {

        return userName;
    }

    public String getPassword() {
        return password;
    }


}
