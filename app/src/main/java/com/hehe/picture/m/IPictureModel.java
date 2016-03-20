package com.hehe.picture.m;


/**
 * Created by 杜伟 on 2016/3/14.
 */
public interface IPictureModel {

    void loadPicture(String url,LoadCallBack mLoadCallBack);



    public interface LoadCallBack{
        void onSuccess(String html);
        void onFailed();

    }

}
