package com.android.bojan.happytoday.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.bojan.base.base.BaseActivity;
import com.android.bojan.base.base.BasePresenter;
import com.android.bojan.happytoday.MainActivity;
import com.android.bojan.happytoday.R;
import com.blankj.utilcode.util.SPUtils;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.iv_splash)
    ImageView mIv_splash;

    private AnimatorSet mAnimatorSet;

    @Override
    protected void initView() {
        SPUtils spUtils = SPUtils.getInstance("theme_style");
        setTheme(spUtils.getInt("theme_style", R.style.AppTheme));
        mAnimatorSet = new AnimatorSet();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(mIv_splash, "translationX", 300, 0);
        ObjectAnimator translationY = ObjectAnimator
                .ofFloat(mIv_splash, "translationY", -90, 80, -70, 80, -50, 40);

        mAnimatorSet.playTogether(translationX, translationY);
        mAnimatorSet.setDuration(2000);
        addListener();
    }

    private void addListener() {
        mAnimatorSet.start();
        mAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                try {
                    Thread.sleep(500);
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
