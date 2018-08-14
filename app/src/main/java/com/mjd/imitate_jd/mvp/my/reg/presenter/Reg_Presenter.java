package com.mjd.imitate_jd.mvp.my.reg.presenter;

import com.mjd.imitate_jd.bean.LoginBean;
import com.mjd.imitate_jd.bean.RegBean;
import com.mjd.imitate_jd.mvp.my.login.model.Imodel_login;
import com.mjd.imitate_jd.mvp.my.login.model.Login_Model;
import com.mjd.imitate_jd.mvp.my.login.presenter.Ipresenter_login;
import com.mjd.imitate_jd.mvp.my.login.view.Iview_login;
import com.mjd.imitate_jd.mvp.my.reg.model.Imodel_reg;
import com.mjd.imitate_jd.mvp.my.reg.model.Reg_Model;
import com.mjd.imitate_jd.mvp.my.reg.view.Iview_reg;

/**
 * Created by admin on 2018-8-8.
 */

public class Reg_Presenter implements Ipresenter_login {
    private Iview_reg mIview_reg;
    private Reg_Model mReg_model;

    public Reg_Presenter(Iview_reg iview_reg) {
        mIview_reg = iview_reg;
        mReg_model = new Reg_Model();
    }

    public void getdas(String mobile, String passowrd){
        mReg_model.getDa(mobile, passowrd, new Imodel_reg() {
            @Override
            public void modelSuccess(RegBean regBean) {
                mIview_reg.viewSuccess(regBean);
            }
        });
    }
    @Override
    public void Destroys() {
        if (mIview_reg != null){
            mIview_reg = null;
        }
    }
}
