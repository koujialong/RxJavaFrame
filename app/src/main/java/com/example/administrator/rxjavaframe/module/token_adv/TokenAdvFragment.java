package com.example.administrator.rxjavaframe.module.token_adv;

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
public class TokenAdvFragment extends BaseFragment {

    @Bind(R.id.tokenTv)
    TextView tokenTv;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    final FakeToken cacheFakeToke=new FakeToken(true);
    boolean tokenUpdataed;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_token_advanced, container, false);
        ButterKnife.bind(this, view);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);
        return view;
    }

    @Override
    protected int getDialogRes() {
        return R.layout.dialog_token_advanced;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_token_advanced;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.requestBt, R.id.invalidateTokenBt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.requestBt:
                tokenUpdataed=false;
                swipeRefreshLayout.setRefreshing(true);
                final FakeApi fakeApi= NetWork.getFakeApi();
                subscription= Observable.just(null)
                        .flatMap(new Func1<Object, Observable<FakeThing>>() {
                            @Override
                            public Observable<FakeThing> call(Object o) {
                                return cacheFakeToke.token==null
                                        ?Observable.<FakeThing>error(new NullPointerException("token si null"))
                                        :fakeApi.getFakeData(cacheFakeToke);
                            }
                        })
                        .retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
                            @Override
                            public Observable<?> call(Observable<? extends Throwable> observable) {
                                return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                                    @Override
                                    public Observable<?> call(Throwable throwable) {
                                        if (throwable instanceof IllegalArgumentException || throwable instanceof NullPointerException){
                                            return fakeApi.getFakeToken("fake_auth_code")
                                                    .doOnNext(new Action1<FakeToken>() {
                                                        @Override
                                                        public void call(FakeToken fakeToken) {
                                                            tokenUpdataed=true;
                                                            cacheFakeToke.token=fakeToken.token;
                                                            cacheFakeToke.expired=fakeToken.expired;
                                                        }
                                                    });
                                        }
                                        return Observable.error(throwable);
                                    }
                                });
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<FakeThing>() {
                            @Override
                            public void call(FakeThing fakeThing) {
                                swipeRefreshLayout.setRefreshing(false);
                                String token = cacheFakeToke.token;
                                if (tokenUpdataed) {
                                    token += "(" + getString(R.string.updated) + ")";
                                }
                                tokenTv.setText(getString(R.string.got_token_and_data, token, fakeThing.id, fakeThing.name));
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                swipeRefreshLayout.setRefreshing(false);
                                Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.invalidateTokenBt:
                cacheFakeToke.expired=true;
                Toast.makeText(getActivity(), R.string.token_destroyed, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
