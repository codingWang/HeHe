package com.hehe.common;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by 杜伟 on 2016/3/19.
 */
public class BaseFragmentActivity extends FragmentActivity {


    protected  <T extends View> T $(@IdRes int id) {
        return (T) findViewById(id);
    }
}
