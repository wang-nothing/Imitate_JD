package com.mjd.imitate_jd.mvp.classify.model;

import com.mjd.imitate_jd.api.RetrofitClient;
import com.mjd.imitate_jd.bean.RightBean;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2018-8-14.
 */

public class Model_Class {
    public void getClasda(String cid, final Imodel_class imodel_class) {
        RetrofitClient
                .getInstance()
                .getCommonApi()
                .getClas(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RightBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RightBean rightBean) {
                        imodel_class.modelSuccess(rightBean);
                    }
                });
    }
}
