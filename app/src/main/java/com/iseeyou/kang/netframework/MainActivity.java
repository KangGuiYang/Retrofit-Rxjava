package com.iseeyou.kang.netframework;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.iseeyou.kang.netframework.bean.GuideBean;
import com.iseeyou.kang.netframework.net.RetrofitHelper;
import com.iseeyou.kang.netframework.rx.RxManager;
import com.iseeyou.kang.netframework.rx.RxSubscriber;
import com.iseeyou.kang.netframework.util.GlideUtil;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

/**
 * 未重构未封装 搭配Mvp使用再Activity只需要关注两个回调方法
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv1)  ImageView iv1;
    @BindView(R.id.iv2)  ImageView iv2;
    @BindView(R.id.iv3)  ImageView iv3;
    @BindView(R.id.tv)  TextView tv;

    private ProgressDialog mDialog = null;
    private Subscription mSubscription = null;// 用于回收资源防止内存泄漏就是回收订阅

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("正在加载中...");
        // 获取引导页数据
        getGuideImgFormNet();
    }

    /**
     * 调接口  Retrofit调接口获得Observer再通过RxJava处理接口数据回调
     * 自定义回调接口RxSubscriber
     */
    private void getGuideImgFormNet() {
        mDialog.show();
        mSubscription = RxManager.getInstance().doSubscribe1(RetrofitHelper.getInstance().create()
                .getGuideListImgs("1", "0"), new RxSubscriber<List<GuideBean.ResBean>>(this) {
            @Override
            protected void _onNext(List<GuideBean.ResBean> resBeen) {
                // 数据获取成功
                iv1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDialog.dismiss();
                    }
                },1500);
                // 数据是三张图片所以直接设置
                String url1 =resBeen.get(0).getUrl();
                String url2 = resBeen.get(1).getUrl();
                String url3 = resBeen.get(2).getUrl();
                if(url1!=null)
                    GlideUtil.load(MainActivity.this,url1,iv1);
                if(url2!=null)
                    GlideUtil.load(MainActivity.this,url2,iv2);
                if(url3!=null)
                    GlideUtil.load(MainActivity.this,url3,iv3);
                tv.setText("Bean:\n" + resBeen.toString());
            }

            @Override
            protected void _onError(String message) {
                // 数据获取失败
                iv1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDialog.dismiss();
                    }
                },1500);
                Toast.makeText(MainActivity.this, "message=" + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 统一管理点击事件
     * @param view
     */
    @OnClick({R.id.iv1,R.id.iv2,R.id.iv3})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.iv1:
                tip("Don't");
                break;
            case R.id.iv2:
                tip("Touch");
                break;
            case R.id.iv3:
                tip("Me Ok?");
                break;
        }
    }

    private void tip(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mSubscription!=null&&!mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();//解除订阅防止内存泄漏
    }

}
