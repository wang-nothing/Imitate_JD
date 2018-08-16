package com.mjd.imitate_jd.mvp.classify.presenter;

import com.mjd.imitate_jd.bean.DetailsBean;
import com.mjd.imitate_jd.mvp.classify.model.Imodel_details;
import com.mjd.imitate_jd.mvp.classify.model.Model_Details;
import com.mjd.imitate_jd.mvp.classify.view.Iview_details;

/**
 * Created by admin on 2018-8-15.
 */

public class Presenter_details implements Ipresenter_details {
    private Iview_details mIview_details;
    private Model_Details mModel_details;

    public Presenter_details(Iview_details iview_details) {
        mIview_details = iview_details;
        mModel_details = new Model_Details();
    }
    public void getdeta(String pid){
        mModel_details.getdetails(pid, new Imodel_details() {
            @Override
            public void modelSuccess(DetailsBean detailsBean) {
                mIview_details.viewSuccess(detailsBean);
            }
        });
    }
    @Override
    public void Destroys() {
        if (mIview_details != null){
            mIview_details = null;
        }
    }
}
