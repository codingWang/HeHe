package com.hehe.main.p;

import com.hehe.main.v.IMainView;

/**
 * Created by 杜伟 on 2016/3/18.
 */
public class MainPresenterImpl implements IMainPresenter {
    IMainView iMainView;

    public MainPresenterImpl(IMainView iMainView){
        this.iMainView = iMainView;
    }





    @Override
    public void onActivityDestroy() {
        if (iMainView != null)
            iMainView = null;
    }
}
