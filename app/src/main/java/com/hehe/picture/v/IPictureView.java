package com.hehe.picture.v;

import com.hehe.picture.m.PictureBean;

import java.util.List;

/**
 * Created by 杜伟 on 2016/3/19.
 */
public interface IPictureView {
    void showLoadingProgress();

    void hideLoadingProgress();

    void displayData(List<PictureBean> pictures);

    void showErrorSnakeBar();


}
