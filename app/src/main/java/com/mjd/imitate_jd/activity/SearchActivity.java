package com.mjd.imitate_jd.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.adapter.SearchAdapter;
import com.mjd.imitate_jd.base.BaseActivity;
import com.mjd.imitate_jd.bean.SearchBean;
import com.mjd.imitate_jd.mvp.home.presenter.Presenter_Home;
import com.mjd.imitate_jd.mvp.home.presenter.Presenter_Search;
import com.mjd.imitate_jd.mvp.home.view.Iview_search;

public class SearchActivity extends BaseActivity implements Iview_search, View.OnClickListener {
    private ImageView item_zxing, item_ss, item_close, item_xx;
    private EditText item_ed;
    private RecyclerView search_recyclerview;
    private String mS;
    private Presenter_Search mSearch;
    private Button search_btn_zonghe,search_btn_xiaoliang,search_btn_jiage;

    private String keywords ;
    private int page = 1;
    private String sort = "0";

    @Override
    protected void initDatas() {
        item_ed.setText(mS);
        mSearch = new Presenter_Search(this);
        mSearch.getsear(keywords, page, sort);
    }

    @Override
    protected void initListener() {
        item_zxing.setOnClickListener(this);
        item_ss.setOnClickListener(this);
        item_close.setOnClickListener(this);
        item_xx.setOnClickListener(this);
        search_btn_zonghe.setOnClickListener(this);
        search_btn_xiaoliang.setOnClickListener(this);
        search_btn_jiage.setOnClickListener(this);

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        mS = intent.getStringExtra("s");
        keywords = mS;
        item_zxing = findViewById(R.id.item_zxing);
        item_ss = findViewById(R.id.item_ss);
        item_close = findViewById(R.id.item_close);
        item_xx = findViewById(R.id.item_xx);
        item_ed = findViewById(R.id.item_ed);
        search_btn_zonghe = findViewById(R.id.search_btn_zonghe);
        search_btn_xiaoliang = findViewById(R.id.search_btn_xiaoliang);
        search_btn_jiage = findViewById(R.id.search_btn_jiage);
        search_recyclerview = findViewById(R.id.search_recyclerview);
        search_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        search_recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        item_ed.setText("笔记本");
    }

    @Override
    public void viewSuccess(SearchBean searchBean) {
        SearchAdapter searchAdapter = new SearchAdapter(this, searchBean);
        search_recyclerview.setAdapter(searchAdapter);
        searchAdapter.setItemonclick(new SearchAdapter.Itemonclick() {
            @Override
            public void getpid(String pid) {
                Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
                intent.putExtra("pid", pid);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_zxing:
                //Toast.makeText(SearchActivity.this, "1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item_ss:
                //Toast.makeText(SearchActivity.this, "2", Toast.LENGTH_SHORT).show();
                String c = item_ed.getText().toString();
                keywords = c;
                mSearch.getsear(keywords,page,sort);
                break;

            case R.id.item_close:
                //Toast.makeText(SearchActivity.this, "3", Toast.LENGTH_SHORT).show();
                item_ed.getText().clear();
                break;

            case R.id.item_xx:
                //Toast.makeText(SearchActivity.this, "4", Toast.LENGTH_SHORT).show();
                break;

            case R.id.search_btn_zonghe:
                //Toast.makeText(SearchActivity.this, "4", Toast.LENGTH_SHORT).show();
                sort = "0";
                mSearch.getsear(keywords,page,sort);
                break;

            case R.id.search_btn_xiaoliang:
                //Toast.makeText(SearchActivity.this, "4", Toast.LENGTH_SHORT).show();
                sort = "1";
                mSearch.getsear(keywords,page,sort);
                break;

            case R.id.search_btn_jiage:
                //Toast.makeText(SearchActivity.this, "4", Toast.LENGTH_SHORT).show();
                sort = "2";
                mSearch.getsear(keywords,page,sort);
                break;
        }
    }
}
