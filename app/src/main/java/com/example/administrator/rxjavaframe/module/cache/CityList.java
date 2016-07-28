package com.example.administrator.rxjavaframe.module.cache;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.rxjavaframe.Adapter.CityAdapter;
import com.example.administrator.rxjavaframe.BaseFragment;
import com.example.administrator.rxjavaframe.Network.NetWork;
import com.example.administrator.rxjavaframe.R;
import com.example.administrator.rxjavaframe.model.CityModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/7/28.
 */
public class CityList extends BaseFragment {

    @Bind(R.id.list)
    RecyclerView list;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    CityAdapter adapter=new CityAdapter();

    Observer<List<CityModel.ItemBean.ProvinceListBean>> observer=new Observer<List<CityModel.ItemBean.ProvinceListBean>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(),"加载失败",Toast.LENGTH_SHORT);
        }

        @Override
        public void onNext(List<CityModel.ItemBean.ProvinceListBean> list) {
            swipeRefreshLayout.setRefreshing(false);
            adapter.setCitys(list);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_citylist, container, false);
        ButterKnife.bind(this, view);
        //list.setLayoutManager(new GridLayoutManager(container.getContext(),2));
        list.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        list.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        swipeRefreshLayout.setEnabled(true);
        return view;
    }

    @Override
    protected int getDialogRes() {
        return 0;
    }

    @Override
    protected int getTitleRes() {
        return 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.load)
    public void onClick() {
        swipeRefreshLayout.setRefreshing(true);
        subscription= NetWork.getCityApi()
                .getCitys("ctrade1")
                .map(new Func1<CityModel, List<CityModel.ItemBean.ProvinceListBean>>() {
                    @Override
                    public List<CityModel.ItemBean.ProvinceListBean> call(CityModel cityModel) {
                        return cityModel.getItem().getProvinceList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
