package com.mjd.imitate_jd.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.bean.HomeBean;
import com.mjd.imitate_jd.holder.BannerHolder;
import com.mjd.imitate_jd.holder.ClassHolder;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018-8-13.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {
    private Context mContext;

    private HomeBean.DataBean mData;
    private List<String> mImages;
    private List<String> mText;

    public RecyclerAdapter(Context context, HomeBean.DataBean data) {
        mContext = context;
        mData = data;
    }

    public static final int ONE_BANNER = 0;
    public static final int TWO_CLASS = 1;
    public static final int THREE = 2;
    public static final int FOUR = 3;


    private ImageView[] ivPoints;//小圆点图片集合
    private int totalPage;//总的页数
    private int mPageSize = 10;//每页显示的最大数量
    //private List<ProductBean> listDatas;//总的数据源
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    private int currentPage;//当前页
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ONE_BANNER){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_banner, null);
            return new BannerHolder(view);
        }else if (viewType == TWO_CLASS){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_class, null);
            return new ClassHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerHolder){
            mImages = new ArrayList<>();
            mImages.add(mData.getBanner().get(0).getIcon());
            mImages.add(mData.getBanner().get(1).getIcon());
            mImages.add(mData.getBanner().get(2).getIcon());
            mImages.add(mData.getBanner().get(3).getIcon());

            mText = new ArrayList<>();
            mText.add(mData.getBanner().get(0).getTitle());
            mText.add(mData.getBanner().get(1).getTitle());
            mText.add(mData.getBanner().get(2).getTitle());
            mText.add(mData.getBanner().get(3).getTitle());
            XBanner item_home_xbanner = ((BannerHolder) holder).item_home_xbanner;
            item_home_xbanner.setData(mImages, mText);
            item_home_xbanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, View view, int position) {
                    Glide.with(mContext).load(mImages.get(position)).into((ImageView) view);
                }
            });
            item_home_xbanner.setPageTransformer(Transformer.Default);
            item_home_xbanner.setPageChangeDuration(1000);
        }else if (holder instanceof ClassHolder){


       /*     listDatas = new ArrayList<>();
            for (int i = 0; i < mData.getFenlei().size(); i++) {
                listDatas.add(new HomeBean(mData.getFenlei().get(i).getName(), mData.getFenlei().get(i).getIcon()));
            }*/



            LayoutInflater inflater = LayoutInflater.from(mContext);
            //总的页数，取整（这里有三种类型：Math.ceil(3.5)=4:向上取整，只要有小数都+1  Math.floor(3.5)=3：向下取整  Math.round(3.5)=4:四舍五入）
            totalPage = (int) Math.ceil(mData.getFenlei().size() * 1.0 / mPageSize);
            viewPagerList = new ArrayList<>();
            for (int i = 0; i < totalPage; i++) {

                //每个页面都是inflate出一个新实例
                GridView gridView = (GridView) inflater.inflate(R.layout.home_viewpager_grid, ((ClassHolder) holder).home_viewpager, false);
                gridView.setAdapter(new MyGridViewAdapter(mContext, mData.getFenlei(), i, mPageSize));
                viewPagerList.add(gridView);
            }
            ((ClassHolder) holder).home_viewpager.setAdapter(new MyViewPagerAdapter(viewPagerList));
            //小圆点指示器
            ivPoints = new ImageView[totalPage];
            for (int i = 0; i < ivPoints.length; i++) {
                ImageView imageView = new ImageView(mContext);
                //设置图片的宽高
                imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
//                if(i == 0){
//                    imageView.setBackgroundResource(R.drawable.page__selected_indicator);
//                }else{
//                    imageView.setBackgroundResource(R.drawable.page__normal_indicator);
//                }
                ivPoints[i] = imageView;
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                layoutParams.leftMargin = 20;//设置点点点view的左边距
                layoutParams.rightMargin = 20;//设置点点点view的右边距
                ((ClassHolder) holder).home_viewPager_points.addView(imageView, layoutParams);
            }
            //设置ViewPager滑动监听
            ((ClassHolder) holder).home_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
//                    setImageBackground(i);
                    currentPage = i;
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return ONE_BANNER;
        }else if (position == 1){
            return TWO_CLASS;
        }
        return super.getItemViewType(position);
    }
}
