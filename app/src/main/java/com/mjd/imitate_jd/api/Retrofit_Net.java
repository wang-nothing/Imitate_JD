package com.mjd.imitate_jd.api;


import com.mjd.imitate_jd.bean.AddcarBean;
import com.mjd.imitate_jd.bean.CarBean;
import com.mjd.imitate_jd.bean.DetailsBean;
import com.mjd.imitate_jd.bean.HomeBean;
import com.mjd.imitate_jd.bean.LoginBean;
import com.mjd.imitate_jd.bean.RegBean;
import com.mjd.imitate_jd.bean.RightBean;
import com.mjd.imitate_jd.bean.SearchBean;

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

    //首页
    @GET("home/getHome")
    Observable<HomeBean> getData();

    //子分类   https://www.zhaoapi.cn/home/getHome
    @POST("product/getProductCatagory")
    Observable<RightBean> getClas(@Query("cid") String cid);

    //搜索    https://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1
    @POST("product/searchProducts")
    Observable<SearchBean> getSearch(@Query("keywords") String keywords, @Query("page") int page, @Query("sort") String sort);

    //详情 https://www.zhaoapi.cn/product/getProductDetail?pid=57
    @POST("product/getProductDetail")
    Observable<DetailsBean> getDetails(@Query("pid") String pid);

    //加入购物车https://www.zhaoapi.cn/product/addCart
    @POST("product/addCart")
    Observable<AddcarBean> getaddcar(@Query("pid")String pid, @Query("uid")String uid);

    //查询购物车https://www.zhaoapi.cn/product/getCarts?uid=16906
    @POST("product/getCarts")
    Observable<CarBean> getCar(@Query("uid") String uid);
}
