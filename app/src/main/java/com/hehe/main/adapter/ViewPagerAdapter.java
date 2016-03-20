package com.hehe.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hehe.main.bean.TabItem;

import java.util.List;

/**
 * Created by 杜伟 on 2016/3/18.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<TabItem> mTabItems;

    public ViewPagerAdapter(FragmentManager fm, List<TabItem> tabItems) {
        super(fm);
        this.mTabItems = tabItems;

    }
    public void setDatasource(List<TabItem> datasource){
        mTabItems = datasource;
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int position) {
        return mTabItems.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return mTabItems.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabItems.get(position).getTitle();
    }
}
