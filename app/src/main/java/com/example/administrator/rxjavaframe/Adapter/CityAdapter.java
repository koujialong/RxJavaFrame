package com.example.administrator.rxjavaframe.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.rxjavaframe.R;
import com.example.administrator.rxjavaframe.model.CityModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/28.
 */
public class CityAdapter extends RecyclerView.Adapter {
    List<CityModel.ItemBean.ProvinceListBean> citys;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        CityModel.ItemBean.ProvinceListBean bean=citys.get(position);
        viewHolder.name.setText(bean.getCodename());
        viewHolder.code.setText(bean.getCodeid());
    }

    @Override
    public int getItemCount() {
        return citys==null?0:citys.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.code)
        TextView code;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setCitys(List<CityModel.ItemBean.ProvinceListBean> citys){
        this.citys=citys;
        notifyDataSetChanged();
    }
}
