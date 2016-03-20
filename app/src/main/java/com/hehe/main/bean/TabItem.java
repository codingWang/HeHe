package com.hehe.main.bean;

import android.support.v4.app.Fragment;

/**
 * Created by 杜伟 on 2016/3/18.
 */
public class TabItem {
    private final CharSequence mTitle;
    private final Fragment mFragment;

    public TabItem(CharSequence title, Fragment fragment) {
        this.mTitle = title;
        this.mFragment = fragment;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public CharSequence getTitle() {
        return mTitle;
    }
}
