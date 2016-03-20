package com.hehe.main.v;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hehe.R;
import com.hehe.common.BaseFragment;
import com.hehe.joke.v.JokeFragment;
import com.hehe.main.adapter.ViewPagerAdapter;
import com.hehe.main.bean.TabItem;
import com.hehe.picture.v.PictureFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杜伟 on 2016/3/19.
 */
public class MainFragment extends BaseFragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ViewPagerAdapter mAdapter;
    private List<TabItem> mTabs = new ArrayList<>();


    public static MainFragment newInstance(String name){
        MainFragment fragment = new MainFragment();

        Bundle bundle = new Bundle();
        bundle.putString("MainFragment",name);

        fragment.setArguments(bundle);

        return fragment;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke_pic,container,false);
        mViewPager = (ViewPager) view.findViewById(R.id.fragment_main_viewpager);
        mTabLayout = (TabLayout) view.findViewById(R.id.fragment_main_tab);

        //Adapter---可动态添加Fragment
        mTabs.add(new TabItem("趣图", PictureFragment.newInstance("趣图")));
        mTabs.add(new TabItem("笑话", JokeFragment.newInstance("笑话")));

        mAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(),mTabs);
        mViewPager.setAdapter(mAdapter);

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
        return view;
    }
}
