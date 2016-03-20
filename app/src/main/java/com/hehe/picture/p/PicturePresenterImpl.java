package com.hehe.picture.p;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hehe.common.Constant;
import com.hehe.picture.m.IPictureModel;
import com.hehe.picture.m.PictureBean;
import com.hehe.picture.m.PictureModelImpl;
import com.hehe.picture.v.IPictureView;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by 杜伟 on 2016/3/19.
 */
public class PicturePresenterImpl implements IPicturePresenter {

   private IPictureView iPictureView;
    private IPictureModel mIPictureModel;

    public PicturePresenterImpl(IPictureView iPictureView) {
        this.iPictureView = iPictureView;
        mIPictureModel = new PictureModelImpl();
    }

    @Override
    public void onActivityDestory() {
        if (iPictureView != null)
            iPictureView = null;
    }

    @Override
    public void loadData() {
        iPictureView.showLoadingProgress();//显示进度
        mIPictureModel.loadPicture(Constant.pictureUrl, new IPictureModel.LoadCallBack() {
            @Override
            public void onSuccess(String html) {//数据解析是不是应该放在Model层~~
                iPictureView.hideLoadingProgress();
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<PictureBean>>(){}.getType();
                ArrayList<PictureBean> datas = gson.fromJson(html, listType);
               // List<PictureBean> mPicture = datas.getmList();
                iPictureView.displayData(datas);
            }

            @Override
            public void onFailed() {
                iPictureView.hideLoadingProgress();
                iPictureView.showErrorSnakeBar();
            }
        });

    }
}
