package com.mjd.imitate_jd.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2018-8-9.
 */

public class RetrofitClient {
    private static final int DEFAULT_TIMEOUT = 5;

    private static Retrofit retrofit;

    private static RetrofitClient mNetworks;

    private static Retrofit_Net mCommentsApi;
    public static RetrofitClient getInstance() {
        if (mNetworks == null) {
            mNetworks = new RetrofitClient();
        }
        return mNetworks;
    }
    public Retrofit_Net getCommonApi() {
        return mCommentsApi == null ? configRetrofit(Retrofit_Net.class) : mCommentsApi;
    }

    private <T> T configRetrofit(Class<T> service) {
        retrofit = new Retrofit.Builder()
                .baseUrl(NewApi.LOGIN_REG)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }

    private OkHttpClient configClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return okHttpClient.build();
    }
}
