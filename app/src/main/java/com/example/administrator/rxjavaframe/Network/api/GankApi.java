package com.example.administrator.rxjavaframe.Network.api;

import com.example.administrator.rxjavaframe.model.GankBeautyResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/7/27.
 */
public interface GankApi {
    @GET("data/福利/{number}/{page}")
    Observable<GankBeautyResult> getBeauties(@Path("number")int number,@Path("page")int page);
}
