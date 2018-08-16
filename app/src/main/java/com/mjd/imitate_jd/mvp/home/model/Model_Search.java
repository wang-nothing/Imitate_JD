package com.mjd.imitate_jd.mvp.home.model;

import com.mjd.imitate_jd.api.RetrofitClient;
import com.mjd.imitate_jd.bean.SearchBean;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2018-8-15.
 */

public class Model_Search {
    public void getSear(String keywords, int page, String sort, final Imodel_search imodel_search) {
        RetrofitClient
                .getInstance()
                .getCommonApi()
                .getSearch(keywords, page, sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchBean searchBean) {
                        imodel_search.modelSuccess(searchBean);
                    }
                });
    }
}
