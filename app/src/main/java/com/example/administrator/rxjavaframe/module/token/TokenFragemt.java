package com.example.administrator.rxjavaframe.module.token;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.rxjavaframe.BaseFragment;
import com.example.administrator.rxjavaframe.Network.NetWork;
import com.example.administrator.rxjavaframe.Network.api.FakeApi;
import com.example.administrator.rxjavaframe.R;
import com.example.administrator.rxjavaframe.model.FakeThing;
import com.example.administrator.rxjavaframe.model.FakeToken;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/7/27.
 */
public class TokenFragemt extends BaseFragment {

    @Bind(R.id.tokenTv)
    TextView tokenTv;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_token, container, false);
        ButterKnife.bind(this, view);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);
        return view;
    }

    @Override
    protected int getDialogRes() {
        return R.layout.dialog_token;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_token;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.requestBt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.requestBt:
                swipeRefreshLayout.setRefreshing(true);
                unsubscribe();
                final FakeApi fakeApi= NetWork.getFakeApi();
                subscription=fakeApi.getFakeToken("fake_auth_code")
                        .flatMap(new Func1<FakeToken, Observable<FakeThing>>() {
                            @Override
                            public Observable<FakeThing> call(FakeToken fakeToken) {
                                return fakeApi.getFakeData(fakeToken);
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<FakeThing>() {
                            @Override
                            public void call(FakeThing fakeThing) {
                                swipeRefreshLayout.setRefreshing(false);
                                tokenTv.setText(getString(R.string.got_data, fakeThing.id, fakeThing.name));
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                swipeRefreshLayout.setRefreshing(false);
                                Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
                            }
                        });

                break;
        }
    }
}
