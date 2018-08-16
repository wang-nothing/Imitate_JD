package com.mjd.imitate_jd.mvp.car.model;

import com.mjd.imitate_jd.api.RetrofitClient;
import com.mjd.imitate_jd.bean.CarBean;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2018-8-16.
 */

public class Model_Car {
    public void getdatacar(String uid, final Imodel_car imodel_car){
        RetrofitClient
                .getInstance()
                .getCommonApi()
                .getCar(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CarBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CarBean carBean) {
                        imodel_car.modelSuccess(carBean);
                    }
                });
    }
}
