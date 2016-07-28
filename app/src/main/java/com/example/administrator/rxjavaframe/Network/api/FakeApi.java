package com.example.administrator.rxjavaframe.Network.api;

import android.support.annotation.NonNull;

import com.example.administrator.rxjavaframe.model.FakeThing;
import com.example.administrator.rxjavaframe.model.FakeToken;

import java.util.Random;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/7/27.
 */
public class FakeApi {
    Random random=new Random();

    public Observable<FakeToken> getFakeToken(@NonNull String fakeAuth){
        return Observable.just(fakeAuth)
                .map(new Func1<String, FakeToken>() {
                    @Override
                    public FakeToken call(String s) {
                        int fakeNetworkTimeCost=random.nextInt(500)+500;
                        try {
                            Thread.sleep(fakeNetworkTimeCost);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        FakeToken fakeToken=new FakeToken();
                        fakeToken.token=creatToken();
                        return fakeToken;
                    }
                });
    }

    private static String creatToken(){
        return "fake_token:"+System.currentTimeMillis()%1000;
    }

    public Observable<FakeThing> getFakeData(FakeToken fakeToken){
        return Observable.just(fakeToken)
                .map(new Func1<FakeToken, FakeThing>() {
                    @Override
                    public FakeThing call(FakeToken fakeToken) {
                        int fakeNetworkTimeCost = random.nextInt(500) + 500;
                        try {
                            Thread.sleep(fakeNetworkTimeCost);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (fakeToken.expired){
                            throw new IllegalArgumentException("Token expired!");
                        }

                        FakeThing fakeThing=new FakeThing();
                        fakeThing.id=(int)(System.currentTimeMillis()%1000);
                        fakeThing.name="FAKE_USER_" + fakeThing.id;
                        return fakeThing;
                    }
                });

    }
}
