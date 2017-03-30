package com.iseeyou.kang.netframework.net;


import com.iseeyou.kang.netframework.api.Apis;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kGod on 2017/2/28.
 * Email 18252032703@163.com
 * Thank you for watching my code
 * Description: RetrofitHelper
 */
public class RetrofitHelper {

    // 网络接口超时时间
    private static final int DEFAULT_CONNECT_TIME = 10;
    private static final int DEFAULT_READ_TIME_OUT = 20;
    private static final int DEFAULT_WRITE_TIME_OUT = 20;

    private static OkHttpClient okHttpClient = null;

    public static RetrofitHelper getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public static class SingletonHolder{
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    private RetrofitHelper(){

    }

    // 创建retrofit接口
    public Apis create(){
        Retrofit retrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(Apis.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(Apis.class);
    }

    // 获得OkHttp实例
    private OkHttpClient getOkHttpClient() {
        //配置超时拦截器
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_CONNECT_TIME, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_WRITE_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
        //配置log打印拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }

}
