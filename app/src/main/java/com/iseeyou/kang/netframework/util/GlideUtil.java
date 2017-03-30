package com.iseeyou.kang.netframework.util;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import static com.bumptech.glide.Glide.with;

public class GlideUtil {

    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
    public static void load(Context context, String url, ImageView iv) {
                Glide
                .with(context)
                        .load(url)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(iv);
    }

    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
    public static void load(Activity activity, String url, ImageView iv) {
            with(activity)
                    .load(url)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv);
    }

    //不缓存，全部从网络加载
    public static void loadAll(Context context, String url, ImageView iv) {
        with(context)
                .load(url)
                .crossFade()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
    }

    //不缓存，全部从网络加载
    public static void loadAll(Activity activity, String url, ImageView iv) {
            with(activity)
                    .load(url)
                    .crossFade()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
    }
}
