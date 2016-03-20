package com.hehe.picture.m;

import android.os.Handler;
import android.os.Looper;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by 杜伟 on 2016/3/14.
 *
 * 我也不想让Handler存在于Model层的，先这么干把，后期再看看封装一个OKHTTP工具类
 */
public class PictureModelImpl implements IPictureModel {
    private Handler mHandler;

    public PictureModelImpl(){
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void loadPicture(String url, final LoadCallBack mLoadCallBack) {
        //网络请求返回数据
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder().url(url).build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e)
            {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mLoadCallBack.onFailed();//让回调发生在主线程
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException
            {
                final String htmlStr =  response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mLoadCallBack.onSuccess(htmlStr);
                    }
                });

            }
        });
    }
}
