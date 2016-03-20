package com.hehe.main.v;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import com.hehe.R;
import com.hehe.common.BaseActivity;
import com.hehe.common.BaseFragment;
import com.hehe.common.BaseFragmentActivity;
import com.hehe.joke.v.JokeFragment;
import com.hehe.main.adapter.ViewPagerAdapter;
import com.hehe.main.bean.TabItem;
import com.hehe.main.p.IMainPresenter;
import com.hehe.main.p.MainPresenterImpl;
import com.hehe.picture.v.PictureFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements IMainView{
   // private IMainPresenter mainPresenter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     //   mainPresenter = new MainPresenterImpl(this);

        setContentView(R.layout.activity_main);
        mToolbar = $(R.id.common_toolbar);
        setSupportActionBar(mToolbar);

        switcgFragment("JOKE");
    }

    @Override
    public void switcgFragment(String fragment) {
        switch(fragment){
            case "JOKE":
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_main_content, MainFragment.newInstance("MainFragment")).commit();
                break;

            //留着拓展吧
            default:
                break;

        }

    }


    @Override
    protected void onDestroy() {
        //mainPresenter.onActivityDestroy();
        super.onDestroy();
    }
}
