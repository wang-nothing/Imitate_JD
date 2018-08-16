package com.mjd.imitate_jd.mvp.car.presenter;

import com.mjd.imitate_jd.bean.CarBean;
import com.mjd.imitate_jd.mvp.car.model.Imodel_car;
import com.mjd.imitate_jd.mvp.car.model.Model_Car;
import com.mjd.imitate_jd.mvp.car.view.Iview_car;

/**
 * Created by admin on 2018-8-16.
 */

public class Presenter_Car implements Ipresenter_car {
    private Iview_car mIview_car;
    private Model_Car mModel_car;

    public Presenter_Car(Iview_car iview_car) {
        mIview_car = iview_car;
        mModel_car = new Model_Car();
    }

    public void getdatacars(String uid){
        mModel_car.getdatacar(uid, new Imodel_car() {
            @Override
            public void modelSuccess(CarBean carBean) {
                mIview_car.modelSuccess(carBean);
            }
        });
    }

    @Override
    public void Destroys() {
        if (mIview_car != null){
            mIview_car = null;
        }
    }
}
