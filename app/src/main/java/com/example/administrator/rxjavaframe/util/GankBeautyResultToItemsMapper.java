package com.example.administrator.rxjavaframe.util;

import com.example.administrator.rxjavaframe.model.GankBeauty;
import com.example.administrator.rxjavaframe.model.GankBeautyResult;
import com.example.administrator.rxjavaframe.model.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.functions.Func1;

/**
 * Created by Administrator on 2016/7/27.
 */
public class GankBeautyResultToItemsMapper implements Func1<GankBeautyResult,List<Item>>{
    private static GankBeautyResultToItemsMapper INSTANCE =new GankBeautyResultToItemsMapper();

    public GankBeautyResultToItemsMapper() {
    }

    public static GankBeautyResultToItemsMapper getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Item> call(GankBeautyResult gankBeautyResult) {
        List<GankBeauty> gankBeauties=gankBeautyResult.beauties;
        List<Item> items=new ArrayList<>();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        for (GankBeauty gankBeauty : gankBeauties) {
            Item item=new Item();
            try {
                Date data = inputFormat.parse(gankBeauty.createdAt);
                item.description=outputFormat.format(data);
            } catch (ParseException e) {
                e.printStackTrace();
                item.description = "unknown date";
            }
            item.imageUrl=gankBeauty.url;
            items.add(item);
        }
        return items;
    }
}
