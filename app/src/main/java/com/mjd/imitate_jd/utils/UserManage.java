package com.mjd.imitate_jd.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.mjd.imitate_jd.bean.UserInfo;

/**
 * Created by admin on 2018-8-16.
 */

public class UserManage {
    private static UserManage sUserManage;
    private UserManage(){

    }
    public static UserManage getInstances(){
        if (sUserManage == null){
            sUserManage = new UserManage();
        }
        return sUserManage;
    }

    public void saveUserInfo(Context context, String mobile, String password,String uid) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);//Context.MODE_PRIVATE表示SharePrefences的数据只有自己应用程序能访问。
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("USER_MODBILE", mobile);
        editor.putString("USER_PASSWORD", password);
        editor.putString("USER_UID", uid);
        editor.commit();
    }

    public UserInfo getUserInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(sp.getString("USER_MODBILE", ""));
        userInfo.setPassword(sp.getString("USER_PASSWORD", ""));
        userInfo.setUid(sp.getString("USER_UID", ""));
        return userInfo;
    }

    public boolean hasUserInfo(Context context) {
        UserInfo userInfo = getUserInfo(context);
        if (userInfo != null) {
            if ((!TextUtils.isEmpty(userInfo.getUserName())) && (!TextUtils.isEmpty(userInfo.getPassword()))) {//有数据
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
