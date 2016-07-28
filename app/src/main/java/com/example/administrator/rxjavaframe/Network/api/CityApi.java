package com.example.administrator.rxjavaframe.Network.api;

import com.example.administrator.rxjavaframe.model.CityModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface CityApi {
    @GET("getProvinceList.do")
    Observable<CityModel> getCitys(@Query("environmentCode")String environmentCode);
}
