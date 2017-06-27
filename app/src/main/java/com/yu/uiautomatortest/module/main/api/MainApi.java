package com.yu.uiautomatortest.module.main.api;

import com.yu.uiautomatortest.module.main.bean.MeiziEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017-6-26.
 *
 */

public interface MainApi {

    //获取妹纸数据 http://gank.io/api/data/福利/10/1
    // count : 每页多少数据  ；  page : 第几页
    @GET("data/福利/{count}/{page}")
    Observable<MeiziEntity> getMeizhiData(@Path("count") int count , @Path("page") int page);
}
