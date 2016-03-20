package com.hehe.common;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 杜伟 on 2016/3/14.
 */
public abstract class BaseActivity  extends AppCompatActivity {

    protected  <T extends View> T $(@IdRes int id) {
        return (T) findViewById(id);
    }

}
