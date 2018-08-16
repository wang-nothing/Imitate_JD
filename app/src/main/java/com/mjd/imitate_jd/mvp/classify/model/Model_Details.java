package com.mjd.imitate_jd.mvp.classify.model;

import com.mjd.imitate_jd.api.RetrofitClient;
import com.mjd.imitate_jd.bean.DetailsBean;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2018-8-15.
 */

public class Model_Details {
    public void getdetails(String pid, final Imodel_details imodel_details){
        RetrofitClient
                .getInstance()
                .getCommonApi()
                .getDetails(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        imodel_details.modelSuccess(detailsBean);
                    }
                });
    }
}
