package com.mjd.imitate_jd.mvp.home.model;

import com.mjd.imitate_jd.api.RetrofitClient;
import com.mjd.imitate_jd.bean.HomeBean;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2018-8-10.
 */

public class Model_Home {
    public void getda(final Imodel_home imodel_home){
        RetrofitClient
                .getInstance()
                .getCommonApi()
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        imodel_home.modelSuccess(homeBean);
                    }
                });

    }
}
