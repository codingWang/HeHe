package com.hehe.picture.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hehe.R;
import com.hehe.picture.m.PictureBean;
import com.hehe.util.ImageLoaderUtils;

import java.util.List;

/**
 * Created by 杜伟 on 2016/3/19.
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ItemViewHolder> {
    private Context mContext;
    private List<PictureBean> mPictures;

    private OnItemClickListener mOnItemClickListener;
    private int mMaxWidth;
    private int mMaxHeight;


    public PictureAdapter(Context mContext){
        this.mContext = mContext;

        mMaxHeight = mContext.getResources().getDisplayMetrics().heightPixels;
        mMaxWidth = mContext.getResources().getDisplayMetrics().widthPixels;
    }


    public void setPictures(List<PictureBean> mPictures){
        this.mPictures = mPictures;
        this.notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//返回一个ViewHolder
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pic_item, parent, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        PictureBean picture =  mPictures.get(position);


        holder.mTitle.setText(picture.getTitle());

        //图片处理+UniversalImageLoader/Glid
        float scale = (float)picture.getWidth() / (float) mMaxWidth;
        int height = (int)(picture.getHeight() / scale);
        if(height > mMaxHeight) {
            height = mMaxHeight;
        }
        holder.mImage.setLayoutParams(new LinearLayout.LayoutParams(mMaxWidth, height));
        ImageLoaderUtils.display(mContext, holder.mImage, picture.getThumburl(),0,0);

    }


    @Override
    public int getItemCount() {
        if (mPictures == null)
            return 0;
        return mPictures.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitle;
        public ImageView mImage;

        public ItemViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.pic_item_tv);
            mImage = (ImageView) v.findViewById(R.id.pic_item_iv);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, this.getPosition());
            }
        }
    }

}
