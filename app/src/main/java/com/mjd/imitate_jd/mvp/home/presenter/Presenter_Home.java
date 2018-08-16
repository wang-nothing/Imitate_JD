package com.mjd.imitate_jd.mvp.home.presenter;

import com.mjd.imitate_jd.activity.SearchActivity;
import com.mjd.imitate_jd.bean.HomeBean;
import com.mjd.imitate_jd.bean.SearchBean;
import com.mjd.imitate_jd.mvp.home.model.Imodel_home;
import com.mjd.imitate_jd.mvp.home.model.Imodel_search;
import com.mjd.imitate_jd.mvp.home.model.Model_Home;
import com.mjd.imitate_jd.mvp.home.model.Model_Search;
import com.mjd.imitate_jd.mvp.home.view.Iview_home;
import com.mjd.imitate_jd.mvp.home.view.Iview_search;

/**
 * Created by admin on 2018-8-11.
 */

public class Presenter_Home implements Ipresenter_home {
    private Iview_home mIview_home;
    private Model_Home mModel_home;

    public Presenter_Home(Iview_home iview_home) {
        mIview_home = iview_home;
        mModel_home = new Model_Home();
    }

    public void getDatas(){
        mModel_home.getda(new Imodel_home() {
            @Override
            public void modelSuccess(HomeBean homeBean) {
                mIview_home.viewSuccess(homeBean);
            }
        });
    }

    @Override
    public void Destroys() {
        if (mIview_home != null){
            mIview_home = null;
        }
    }
}
