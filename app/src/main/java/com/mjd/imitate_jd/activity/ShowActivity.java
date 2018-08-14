package com.mjd.imitate_jd.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.base.BaseActivity;
import com.mjd.imitate_jd.fragment.Fragment_Car;
import com.mjd.imitate_jd.fragment.Fragment_Class;
import com.mjd.imitate_jd.fragment.Fragment_Find;
import com.mjd.imitate_jd.fragment.Fragment_Home;
import com.mjd.imitate_jd.fragment.Fragment_My;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private FrameLayout fragmelayout;
    private RadioGroup show_group;
    private List<Fragment> mFragments;



    @Override
    protected void initDatas() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragmelayout, mFragments.get(0)).commit();
        show_group.check(R.id.show_group_home);
    }

    @Override
    protected void initListener() {
        show_group.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_show);
        fragmelayout = findViewById(R.id.fragmelayout);
        show_group = findViewById(R.id.show_group);
        mFragments = new ArrayList<>();
        mFragments.add(new Fragment_Home());
        mFragments.add(new Fragment_Class());
        mFragments.add(new Fragment_Find());
        mFragments.add(new Fragment_Car());
        mFragments.add(new Fragment_My());
    }





    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.show_group_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmelayout, mFragments.get(0)).commit();
                break;

            case R.id.show_group_class:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmelayout, mFragments.get(1)).commit();
                break;

            case R.id.show_group_find:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmelayout, mFragments.get(2)).commit();
                break;

            case R.id.show_group_car:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmelayout, mFragments.get(3)).commit();
                break;

            case R.id.show_group_my:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmelayout, mFragments.get(4)).commit();
                break;
        }
    }
}
