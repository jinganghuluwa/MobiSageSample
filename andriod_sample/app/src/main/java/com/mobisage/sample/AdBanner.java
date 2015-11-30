
package com.mobisage.sample;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.mobisage.android.MobiSageAdBanner;
import com.mobisage.android.MobiSageAdBannerListener;

public class AdBanner extends BaseActivity {

    private MobiSageAdBanner mBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banner);
        setLogo();
        // 创建一个自适应屏幕的横幅广告
        mBanner = AdManager.getInstance(getApplicationContext()).createBanner(
                new MobiSageAdBannerListener() {
                    // 横幅广告请求成功时，此接口被回调，横幅广告可以展示
                    @Override
                    public void onMobiSageBannerSuccess(MobiSageAdBanner adView) {

                    }
                    // 横幅广告展示或轮播成功时，此接口被回调，多用于聚合中统计广告展示数
                    @Override
                    public void onMobiSageBannerShow(MobiSageAdBanner adView) {
                    }
                    // 横幅广告被点击时，此接口被回调，多用于聚合中统计广告点击数
                    @Override
                    public void onMobiSageBannerClick(MobiSageAdBanner adView) {
                    }
                    // 横幅广告展示或轮播失败时，此接口被回调，多用于聚合中切换广告平台,msg为错误信息
                    @Override
                    public void onMobiSageBannerError(MobiSageAdBanner adView, String msg) {
                    }
                }
                );

        RelativeLayout container = (RelativeLayout) findViewById(R.id.ad_container);
        container.addView(mBanner);
    }

    @Override
    protected void onDestroy() {
        AdManager.getInstance(this).destroyBanner(mBanner);
        super.onDestroy();
    }

}
