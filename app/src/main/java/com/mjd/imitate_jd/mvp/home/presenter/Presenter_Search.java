package com.mjd.imitate_jd.mvp.home.presenter;

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

public class Presenter_Search implements Ipresenter_home {
    private Iview_search mIview_search;
    private Model_Search mModel_search;

    public Presenter_Search(Iview_search iview_search) {
        mIview_search = iview_search;
        mModel_search = new Model_Search();
    }

    public void getsear(String keywords, int page, String sort){
        mModel_search.getSear(keywords, page, sort, new Imodel_search() {
            @Override
            public void modelSuccess(SearchBean searchBean) {
                mIview_search.viewSuccess(searchBean);
            }
        });
    }
    @Override
    public void Destroys() {
        if (mIview_search != null){
            mIview_search = null;
        }
    }
}
