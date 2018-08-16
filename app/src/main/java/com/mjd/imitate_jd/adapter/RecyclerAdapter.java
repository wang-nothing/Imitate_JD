package com.mjd.imitate_jd.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.activity.WebActivity;
import com.mjd.imitate_jd.bean.HomeBean;
import com.mjd.imitate_jd.holder.BannerHolder;
import com.mjd.imitate_jd.holder.ClassHolder;
import com.mjd.imitate_jd.holder.MiaoShaHolder;
import com.mjd.imitate_jd.holder.TuijianHolder;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

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
        }else if (viewType == THREE){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_miaosha, null);
            return new MiaoShaHolder(view);
        }else if (viewType == FOUR){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_tuijian, null);
            return new TuijianHolder(view);
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
            /*for (int i = 0; i < mData.getBanner().size(); i++) {
                mImages.add(mData.getBanner().get(position).getIcon());
                mText.add(mData.getBanner().get(position).getTitle());
            }*/
            Banner item_home_xbanner = ((BannerHolder) holder).item_home_xbanner;
            item_home_xbanner.setImageLoader(new MyLoader());
            item_home_xbanner.setImages(mImages);
            //设置轮播图的标题集合
            item_home_xbanner.setBannerTitles(mText);
            item_home_xbanner.setDelayTime(3000);
            //设置是否为自动轮播，默认是“是”。
            item_home_xbanner.isAutoPlay(true);
            //设置指示器的位置，小点点，左中右。
            item_home_xbanner.setIndicatorGravity(BannerConfig.CENTER)
                    //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            Log.i("tag", "你点了第"+position+"张轮播图");
                        }
                    })
                    //必须最后调用的方法，启动轮播图。
                    .start();

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
        }else if (holder instanceof MiaoShaHolder){
            ((MiaoShaHolder) holder).miaosha_recyclerview.setLayoutManager(new GridLayoutManager(mContext,2));
            HomeBean.DataBean.MiaoshaBean miaosha = mData.getMiaosha();
            GridcycAdapter gridcycAdapter = new GridcycAdapter(miaosha, mContext);
            ((MiaoShaHolder) holder).miaosha_recyclerview.setAdapter(gridcycAdapter);
            ((MiaoShaHolder) holder).miaosha_tv.setText("————————"+mData.getMiaosha().getName()+"————————");
            gridcycAdapter.setItemClickListener(new GridcycAdapter.OnItemClickListener2() {
                @Override
                public void onItemClick(String pid, String detailUrl) {
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra("pid", pid);
                    intent.putExtra("detailUrl", detailUrl);
                    mContext.startActivity(intent);
                }
            });
        }
        else if (holder instanceof TuijianHolder){
            ((TuijianHolder) holder).tuijian_tv.setText("————————"+mData.getTuijian().getName()+"————————");
            ((TuijianHolder) holder).tuijian_recyclerview.setLayoutManager(new GridLayoutManager(mContext,2));
            HomeBean.DataBean.TuijianBean tuijian = mData.getTuijian();
            TuijiancAdapter tuijiancAdapter = new TuijiancAdapter(tuijian, mContext);
            ((TuijianHolder) holder).tuijian_recyclerview.setAdapter(tuijiancAdapter);
            tuijiancAdapter.setItemClickListener(new TuijiancAdapter.OnItemClickListener1() {
                @Override
                public void onItemClick(String pid, String detailUrl) {
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra("pid", pid);
                    intent.putExtra("detailUrl", detailUrl);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return ONE_BANNER;
        }else if (position == 1){
            return TWO_CLASS;
        }else if (position == 2){
            return THREE;
        }else if (position == 3){
            return FOUR;
        }
        return super.getItemViewType(position);
    }
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
}
