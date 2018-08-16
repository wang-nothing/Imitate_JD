package com.mjd.imitate_jd.mvp.classify.presenter;

import com.mjd.imitate_jd.bean.RightBean;
import com.mjd.imitate_jd.mvp.classify.model.Imodel_class;
import com.mjd.imitate_jd.mvp.classify.model.Model_Class;
import com.mjd.imitate_jd.mvp.classify.view.Iview_class;

/**
 * Created by admin on 2018-8-14.
 */

public class Presenter_Class implements Ipresenter_class {
    private Iview_class mIview_class;
    private Model_Class mModel_class;

    public Presenter_Class(Iview_class iview_class) {
        mIview_class = iview_class;
        mModel_class = new Model_Class();
    }
    public void getClasdas(String cid){
        mModel_class.getClasda(cid, new Imodel_class() {
            @Override
            public void modelSuccess(RightBean rightBean) {
                mIview_class.modelSuccess(rightBean);
            }
        });
    }
    @Override
    public void Destroys() {
        if (mIview_class != null){
            mIview_class = null;
        }
    }
}
