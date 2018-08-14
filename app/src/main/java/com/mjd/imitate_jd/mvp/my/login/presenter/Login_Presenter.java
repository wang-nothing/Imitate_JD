package com.mjd.imitate_jd.mvp.my.login.presenter;

import com.mjd.imitate_jd.bean.LoginBean;
import com.mjd.imitate_jd.mvp.my.login.model.Imodel_login;
import com.mjd.imitate_jd.mvp.my.login.model.Login_Model;
import com.mjd.imitate_jd.mvp.my.login.view.Iview_login;

/**
 * Created by admin on 2018-8-8.
 */

public class Login_Presenter implements Ipresenter_login {
    private Iview_login mIview_login;
    private Login_Model mLogin_model;

    public Login_Presenter(Iview_login iview_login) {
        mIview_login = iview_login;
        mLogin_model = new Login_Model();
    }

    public void getdas(String mobile, String passowrd){
        mLogin_model.getDa(mobile, passowrd, new Imodel_login() {
            @Override
            public void modelSuccess(LoginBean loginBean) {
                mIview_login.viewSuccess(loginBean);
            }
        });
    }
    @Override
    public void Destroys() {
        if (mIview_login != null){
            mIview_login = null;
        }
    }
}
