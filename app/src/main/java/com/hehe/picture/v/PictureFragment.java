package com.hehe.picture.v;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hehe.R;
import com.hehe.common.BaseActivity;
import com.hehe.common.BaseFragment;
import com.hehe.main.p.IMainPresenter;
import com.hehe.picture.common.PictureAdapter;
import com.hehe.picture.m.PictureBean;
import com.hehe.picture.p.IPicturePresenter;
import com.hehe.picture.p.PicturePresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杜伟 on 2016/3/14.
 */
public class PictureFragment extends BaseFragment implements IPictureView,SwipeRefreshLayout.OnRefreshListener{

    private static final String PICTURE = "PICTURE";
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private IPicturePresenter iPicturePresenter;
    private List<PictureBean> mPictures;
    private PictureAdapter mAdapter;

    public static PictureFragment newInstance(String name){
        PictureFragment pictureFragment = new PictureFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(PICTURE,name);
        pictureFragment.setArguments(mBundle);
        return pictureFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iPicturePresenter = new PicturePresenterImpl(this);//初始化Presenter层
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture,container,false);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_pic_refresh);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_pic_recycler);
        mRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary, R.color.colorPrimary,
                R.color.light_blue);
        mRecyclerView.setHasFixedSize(true);

        mRefreshLayout.setOnRefreshListener(this);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new PictureAdapter(getActivity().getApplicationContext());

        //mAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnScrollListener(mOnScrollListener);

        onRefresh();
        return view;
    }


    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            //if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()
                 //   && mAdapter.isShowFooter()) {
                //加载更多
               // LogUtils.d(TAG, "loading more data");
               // mNewsPresenter.loadNews(mType, pageIndex + Urls.PAZE_SIZE);
           // }
        }
    };

//    private PictureAdapter.OnItemClickListener mOnItemClickListener = new PictureAdapter.OnItemClickListener() {
//        @Override
//        public void onItemClick(View view, int position) {
//            PictureBean news = mAdapter.getItem(position);
//            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
//            intent.putExtra("news", news);
//
//            View transitionView = view.findViewById(R.id.ivNews);
//            ActivityOptionsCompat options =
//                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
//                            transitionView, getString(R.string.transition_news_img));
//
//            ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
//        }
//    };


    @Override
    public void showLoadingProgress() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoadingProgress() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void displayData(List<PictureBean> pictures) {
        //在recyclerview展示数据

        if (mPictures == null)
            mPictures = new ArrayList<>();

        mPictures.addAll(pictures);
        mAdapter.notifyDataSetChanged();

        mAdapter.setPictures(mPictures);

    }

    @Override
    public void showErrorSnakeBar() {

    }

    @Override
    public void onRefresh() {
        iPicturePresenter.loadData();
    }
}
