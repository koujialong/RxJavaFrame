package com.example.administrator.rxjavaframe.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.rxjavaframe.R;
import com.example.administrator.rxjavaframe.model.Item;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/27.
 */
public class ItemListAdapter extends RecyclerView.Adapter {
    List<Item> items;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        Item item=items.get(position);
        Glide.with(holder.itemView.getContext()).load(item.imageUrl).into(viewHolder.imageIv);
        viewHolder.descriptionTv.setText(item.description);
    }

    @Override
    public int getItemCount() {
        return items==null?0:items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.imageIv)
        ImageView imageIv;
        @Bind(R.id.descriptionTv)
        TextView descriptionTv;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setItems(List<Item> items){
        this.items=items;
        notifyDataSetChanged();
    }
}
