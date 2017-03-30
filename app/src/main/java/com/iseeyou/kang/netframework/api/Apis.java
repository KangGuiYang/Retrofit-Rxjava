package com.iseeyou.kang.netframework.api;


import com.iseeyou.kang.netframework.bean.GuideBean;
import com.iseeyou.kang.netframework.bean.HttpResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


/**
 * Created by kGod on 2017/2/28.
 * Email 18252032703@163.com
 * Thank you for watching my code
 * 存放所有请求Api
 */

public interface Apis {

    /**
     * 接口根地址
     */
    String ROOT_URL = "http://120.77.251.141:39080/youe/";


    /**
     * 【1】 Post
     * 启动页引导页
     * @param type
     * @param business_type 启动页 引导页 0 启动页 1 引导页
     * @return
     * Baidu Retrofit的注解解释
     * Post请求就这么写
     */
    @FormUrlEncoded
    @POST("commons/getLogonInfo")
    Observable<HttpResult<List<GuideBean.ResBean>>> getGuideListImgs
    (@Field("type") String type, @Field("business_type") String business_type);

    /**
     * GET Query="将字段拼接再RUL上"
     */
//    @GET("commons/getLogonInfo")
//    Observable<HttpResult<List<GuideBean.ResBean>>> getGuideListImgs
//            (@Query("type") String type, @Query("business_type") String business_type);

}