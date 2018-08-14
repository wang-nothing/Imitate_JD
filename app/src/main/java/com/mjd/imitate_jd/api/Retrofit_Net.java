package com.mjd.imitate_jd.api;


import com.mjd.imitate_jd.bean.HomeBean;
import com.mjd.imitate_jd.bean.LoginBean;
import com.mjd.imitate_jd.bean.RegBean;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by admin on 2018-8-7.
 */

public interface Retrofit_Net {
    //注册
    @POST("user/reg")
    Observable<RegBean> getreg(@Query("mobile") String mobile, @Query("password") String password);

    //登录
    @POST("user/login")
    Observable<LoginBean> getlogin(@Query("mobile") String mobile, @Query("password") String password);

    //首页广告
    @GET("home/getHome")
    Observable<HomeBean> getData();
}
