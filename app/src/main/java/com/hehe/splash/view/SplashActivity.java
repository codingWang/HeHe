package com.hehe.splash.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hehe.R;
import com.hehe.common.BaseActivity;
import com.hehe.splash.presenter.ISplashPresenter;
import com.hehe.splash.presenter.SplashPresenterImpl;
import com.hehe.main.v.MainActivity;

/**
 * Created by 杜伟 on 2016/3/14.
 */
public class SplashActivity extends BaseActivity implements ISplashView {
    private ISplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //全屏
        mSplashPresenter = new SplashPresenterImpl(this);
        setContentView(R.layout.activity_splash);

        TextView splashTextView = $(R.id.activity_splash_tv);
        LinearLayout ll = $(R.id.activity_splash_ll);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(2000);
        set.playTogether(ObjectAnimator.ofFloat(ll,"alpha",0f,1f),
                ObjectAnimator.ofFloat(splashTextView, "translationY", 300, 0));
        set.start();
        mSplashPresenter.doingSplash();
    }

    @Override
    public void jump2Main() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }


    @Override
    protected void onDestroy() {
        mSplashPresenter.onActivityDestroy();
        super.onDestroy();
    }

}
