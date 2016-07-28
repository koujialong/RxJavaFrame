package com.example.administrator.rxjavaframe.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.rxjavaframe.R;
import com.example.administrator.rxjavaframe.model.ZhuangbiImage;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/27.
 */
public class ZhuangbiListAdapter extends RecyclerView.Adapter {
    List<ZhuangbiImage> images;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new ZhuangbiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ZhuangbiViewHolder zhuangbiViewHolder= (ZhuangbiViewHolder) holder;
        ZhuangbiImage image=images.get(position);
        Glide.with(holder.itemView.getContext()).load(image.image_url).into(zhuangbiViewHolder.imageIv);
        zhuangbiViewHolder.descriptionTv.setText(image.description);
    }

    @Override
    public int getItemCount() {
        return images==null?0:images.size();
    }

    public void setImages(List<ZhuangbiImage> images){
        this.images=images;
        notifyDataSetChanged();
    }

    static class ZhuangbiViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.imageIv)
        ImageView imageIv;
        @Bind(R.id.descriptionTv)
        TextView descriptionTv;

        public ZhuangbiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
