package com.hehe.joke.v;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hehe.R;
import com.hehe.common.BaseFragment;

/**
 * Created by 杜伟 on 2016/3/14.
 */
public class JokeFragment extends BaseFragment implements IJokeView{

    private static final String JOKE = "JOKE";

    public static JokeFragment newInstance(String name){
        JokeFragment jokeFragment = new JokeFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(JOKE,name);
        jokeFragment.setArguments(mBundle);
        return jokeFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_joke,container,false);
    }
}
