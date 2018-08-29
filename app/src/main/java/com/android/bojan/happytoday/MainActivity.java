package com.android.bojan.happytoday;

import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.android.bojan.base.base.BaseActivity;
import com.android.bojan.happytoday.contract.MainViewContract;
import com.android.bojan.happytoday.presenter.MainViewPresenter;
import com.android.bojan.happytoday.view.JokeFragment;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainViewPresenter> implements MainViewContract.View {
    @BindView(R.id.fl_content_activity_main)
    FrameLayout mFlContent;
    @BindView(R.id.nv_left)
    NavigationView mNvLeft;
    @BindView(R.id.dl_activity_main)
    DrawerLayout mDlActivityMain;
    @BindView(R.id.bottom_bar)
    BottomBar mBottomBar;
    private JokeFragment mJokeFragment;
    private Fragment mCurrentFragment;

    @Override
    protected void initView() {
        SPUtils spUtils = SPUtils.getInstance("theme_style");
        int theme_style = spUtils.getInt("theme_style", R.style.AppTheme);
        mPresenter.start();
        setTheme(theme_style);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        retrieveFragment();
        setBottomBar();
        setLeftMenuClickEvent();
    }

    private void setLeftMenuClickEvent() {
        mNvLeft.setCheckedItem(R.id.nv_news);
        mNvLeft.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mNvLeft.setCheckedItem(item.getItemId());
                mDlActivityMain.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nv_news:
                        mBottomBar.selectTabAtPosition(0, true);
                        break;
                    case R.id.nv_duanzi:
                        mBottomBar.selectTabAtPosition(1, true);
                        break;
                    case R.id.nv_today_of_history:
                        mBottomBar.selectTabAtPosition(2, true);
                        break;
                    case R.id.nv_fun_pic:
                        mBottomBar.selectTabAtPosition(3, true);
                        break;
                    case R.id.nv_about:
                        mBottomBar.selectTabAtPosition(4, true);
                        break;
                    case R.id.nv_clear_cache:
                        mPresenter.clearCache();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void setBottomBar() {

        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_news:
                        if (mJokeFragment == null) mJokeFragment = new JokeFragment();
                        switchFragment(mJokeFragment);
                        mNvLeft.setCheckedItem(R.id.nv_news);
                        closeDrawerLayout();
                        break;
                    case R.id.tab_joke:
                        if (mJokeFragment == null) mJokeFragment = new JokeFragment();
                        switchFragment(mJokeFragment);
                        mNvLeft.setCheckedItem(R.id.nv_news);
                        closeDrawerLayout();
                        break;
                    case R.id.tab_today:
                        if (mJokeFragment == null) mJokeFragment = new JokeFragment();
                        switchFragment(mJokeFragment);
                        mNvLeft.setCheckedItem(R.id.nv_news);
                        closeDrawerLayout();
                        break;
                    case R.id.tab_pic:
                        if (mJokeFragment == null) mJokeFragment = new JokeFragment();
                        switchFragment(mJokeFragment);
                        mNvLeft.setCheckedItem(R.id.nv_news);
                        closeDrawerLayout();
                        break;
                    case R.id.tab_about:
                        if (mJokeFragment == null) mJokeFragment = new JokeFragment();
                        switchFragment(mJokeFragment);
                        mNvLeft.setCheckedItem(R.id.nv_news);
                        closeDrawerLayout();
                        break;
                }
            }
        });
    }

    private void closeDrawerLayout() {
        if (mDlActivityMain.isDrawerOpen(Gravity.LEFT)) {
            mDlActivityMain.closeDrawers();
        }
    }

    private void retrieveFragment() {
        FragmentManager manager = getSupportFragmentManager();

        mJokeFragment = (JokeFragment) manager.findFragmentByTag("JokeFragment");

    }


    @Override
    public MainViewPresenter initPresenter() {
        return new MainViewPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }



    public void switchFragment(Fragment target) {

        // 如果当前的fragment 就是要替换的fragment 就直接return
        if (mCurrentFragment == target) return;

        // 获得 Fragment 事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // 如果当前Fragment不为空，则隐藏当前的Fragment
        if (mCurrentFragment != null) {
            transaction.hide(mCurrentFragment);
        }

        // 如果要显示的Fragment 已经添加了，那么直接 show
        if (target.isAdded()) {
            transaction.show(target);
        } else {
            // 如果要显示的Fragment没有添加，就添加进去
            transaction.add(R.id.fl_content_activity_main, target, target.getClass().getName());
        }

        // 事务进行提交
        transaction.commit();

        //并将要显示的Fragment 设为当前的 Fragment
        mCurrentFragment = target;
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}