package com.yu.uiautomatortest.util;

import com.yu.uiautomatortest.common.GlobalConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017-3-10.
 * 获取 Retrofit 实例
 */

public class RetrofitUtils {

    private static Retrofit mRetrofit;
    private static OkHttpClient mClient;

    private static void createOkHttpClient(){
        if (mClient == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);// 日志级别 HEADERS 和 BODY ，这两个有泄露敏感信息的可能性，所以正是线上产品慎用
            mClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();
        }
    }

    public static OkHttpClient getOkHttpClient(){
        //createOkHttpClient();
        return mClient;
    }


    public static Retrofit getRetrofit(){

        if (mRetrofit == null) {

            if (mClient == null){
                createOkHttpClient();
            }

            mRetrofit = new Retrofit.Builder()
                    .client(mClient)
                    .baseUrl(GlobalConfig.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return mRetrofit;
    }
}
