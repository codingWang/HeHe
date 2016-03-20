package com.hehe.splash.presenter;

import android.os.Handler;
import android.os.Message;

import com.hehe.splash.view.ISplashView;

/**
 * Created by 杜伟 on 2016/3/14.
 * 在这里进行业务逻辑处理
 */
public class SplashPresenterImpl implements ISplashPresenter, Handler.Callback{
    private ISplashView splashView;
    private Handler mHandler;
    private static final int DOING_SPLASH = 1;

    public SplashPresenterImpl(ISplashView splashView){
        this.splashView = splashView;
        mHandler = new Handler(this);
    }


    @Override
    public void onActivityDestroy() {
        if (splashView != null)
            splashView = null;
    }

    @Override
    public void doingSplash() {
        if (mHandler != null) {
            mHandler.sendEmptyMessageDelayed(DOING_SPLASH, 2000);
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.what == DOING_SPLASH){
            splashView.jump2Main();
        }
        return false;
    }

}
