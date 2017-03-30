package com.iseeyou.kang.netframework.rx;

import android.text.TextUtils;

import com.iseeyou.kang.netframework.api.ApiException;
import com.iseeyou.kang.netframework.bean.HttpResult;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by kGod on 2017/2/28.
 * Email 18252032703@163.com
 * Thank you for watching my code
 * 关于Rx的相关操作
 */

public class RxManager {

    private RxManager() {

    }

    // 单例模式
    public static RxManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RxManager INSTANCE = new RxManager();
    }


    /**
     * 统一线程处理 compose 操作符
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 判断错误码转换
     * @param observable
     * @param subscriber
     * @param <T>
     * @return
     */
    public <T> Subscription doSubscribe1(Observable<HttpResult<T>> observable, Subscriber<T> subscriber) {
        return observable.compose(RxManager.<T>handleResult())
                .compose(RxManager.<T>rxSchedulerHelper())
                .subscribe(subscriber);
    }



    /**
     * 根据自己请求接口 判断接口是否调成功
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<HttpResult<T>, T> handleResult() {   //compose判断结果
        return new Observable.Transformer<HttpResult<T>, T>() {
            @Override
            public Observable<T> call(Observable<HttpResult<T>> httpResponseObservable) {
                return httpResponseObservable.flatMap(new Func1<HttpResult<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(HttpResult<T> videoHttpResponse) {
                        if (videoHttpResponse.getCode().equals("ok")) {
                            return createData(videoHttpResponse.getRes());
                        } else if (!TextUtils.isEmpty(videoHttpResponse.getMessage())) {
                            return Observable.error
                                    (new ApiException(videoHttpResponse.getMessage()));
                        } else {
                            return Observable.error(new ApiException("*" + "服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Observable
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }


}
