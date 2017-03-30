package com.iseeyou.kang.netframework.rx;

/**
 * Created by My Fair Lady on 2016/9/26.
 */

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;

import rx.Subscriber;

/**
 * RxJava 观察者RxSubscriber统一网络接口回调
 * @param <T>
 */
public abstract class RxSubscriber<T> extends Subscriber<T>{

    private Context mContext;
    public RxSubscriber(Context context){
        mContext =context;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        //统一处理请求异常的情况
        if (e instanceof IOException) {
            Toast.makeText(mContext, "网络连接异常.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        _onError(e.getMessage());
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }


    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);
}
