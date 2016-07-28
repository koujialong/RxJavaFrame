package com.example.administrator.rxjavaframe.Network;

import com.example.administrator.rxjavaframe.Network.api.CityApi;
import com.example.administrator.rxjavaframe.Network.api.FakeApi;
import com.example.administrator.rxjavaframe.Network.api.GankApi;
import com.example.administrator.rxjavaframe.Network.api.ZhuangbiApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/7/27.
 */
public class NetWork {
    private static ZhuangbiApi zhuangbiApi;
    private static GankApi gankApi;
    private static FakeApi fakeApi;
    private static CityApi cityApi;
    private static OkHttpClient okHttpClient=new OkHttpClient();
    private static Converter.Factory gsonConverterFactory=GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory=RxJavaCallAdapterFactory.create();

    public static ZhuangbiApi getZhuangbiApi(){
        if (zhuangbiApi==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://zhuangbi.info/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            zhuangbiApi=retrofit.create(ZhuangbiApi.class);
        }
        return zhuangbiApi;
    }

    public static GankApi getGankApi(){
        if (gankApi==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            gankApi=retrofit.create(GankApi.class);
        }
        return gankApi;
    }

    public static CityApi getCityApi(){
        if (cityApi==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://wp.500win.cn/ctrade/member/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            cityApi=retrofit.create(CityApi.class);
        }
        return cityApi;
    }

    public static FakeApi getFakeApi(){
        if (fakeApi==null){
            fakeApi=new FakeApi();
        }
        return fakeApi;
    }
}
