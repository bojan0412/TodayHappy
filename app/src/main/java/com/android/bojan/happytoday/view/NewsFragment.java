package com.android.bojan.happytoday.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.android.bojan.base.base.BaseFragment;
import com.android.bojan.base.base.BasePresenter;
import com.android.bojan.base.utils.ActivityUtils;
import com.android.bojan.happytoday.R;
import com.blankj.utilcode.util.AppUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import butterknife.BindView;

/**
 * Create by bojan
 * on 2018/8/29
 */
public class NewsFragment extends BaseFragment {
    @BindView(R.id.main_viewpager)
    ViewPager mMainViewpager;

    private String[] mTypes;         //顶部 tab 英文内容数组
    private String[] mTypesCN;       //顶部 tab 中文内容数组
    private NewsViewPagerAadpter mNewsViewPagerAadpter;    //ViewPager 适配器

    private class NewsViewPagerAadpter extends FragmentStatePagerAdapter {

        public NewsViewPagerAadpter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //根据位置创建相应的fragment
            return new NewsDetailFragment(mTypes[position]);
        }

        @Override
        public int getCount() {
            return mTypes.length;
        }

    }


    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void setLayoutView(ViewGroup container, View view) {
        mTypes = getResources().getStringArray(R.array.news_type_en);
        mTypesCN = getResources().getStringArray(R.array.news_type_cn);
        if (ActivityUtils.activityIsAlive(getActivity()))
            mNewsViewPagerAadpter = new NewsViewPagerAadpter(getActivity().getSupportFragmentManager());
        mMainViewpager.setAdapter(mNewsViewPagerAadpter);
        initHeaderIndicator(view);
    }

    private void initHeaderIndicator(View view) {
        MagicIndicator magicIndicator = view.findViewById(R.id.magic_indicator);
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTypesCN == null ? 0 : mTypesCN.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView
                        = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.BLACK);
                colorTransitionPagerTitleView.setSelectedColor(Color.RED);
                colorTransitionPagerTitleView.setText(mTypesCN[index]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mMainViewpager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mMainViewpager);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_news;
    }

    @Override
    protected void fetchData() {

    }


    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
