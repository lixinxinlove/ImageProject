package com.lixinxin.imageproject.retofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by android on 2017/9/22.
 */

public class RetrofitClient {

    private static final String baseURL = "http://gank.io/api/data/福利/";
    private static OkHttpClient mClient;
    private GankIO mGankIO;

    private static RetrofitClient mRetrofitClient;

    public static RetrofitClient getInstence() {
        if (mRetrofitClient == null) {
            synchronized (RetrofitClient.class) {
                if (mRetrofitClient == null) {
                    mRetrofitClient = new RetrofitClient();
                    getOkHttpClient();
                }
            }
        }
        return mRetrofitClient;
    }


    /**
     * 封装干货API
     */
    public GankIO getGankService() {
        if (mGankIO == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(mClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mGankIO = retrofit.create(GankIO.class);
        }
        return mGankIO;
    }


    private static OkHttpClient getOkHttpClient() {

        if (mClient == null) {
            synchronized (RetrofitClient.class) {
                if (mClient == null) {
                    mClient = new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return mClient;
    }


}
