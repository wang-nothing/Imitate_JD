package com.mjd.imitate_jd.mvp.my.reg.model;

import com.mjd.imitate_jd.api.RetrofitClient;
import com.mjd.imitate_jd.bean.LoginBean;
import com.mjd.imitate_jd.bean.RegBean;
import com.mjd.imitate_jd.mvp.my.login.model.Imodel_login;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2018-8-8.
 */

public class Reg_Model {
    public void getDa(String mobile, String password, final Imodel_reg imodel_reg){
        RetrofitClient
                .getInstance()
                .getCommonApi()
                .getreg(mobile, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RegBean regBean) {
                        imodel_reg.modelSuccess(regBean);
                    }
                });
    }
}
