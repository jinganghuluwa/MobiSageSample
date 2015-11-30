
package com.mobisage.sample;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;

import com.mobisage.android.MobiSageAdBanner;
import com.mobisage.android.MobiSageAdBannerListener;
import com.mobisage.android.MobiSageAdNative;
import com.mobisage.android.MobiSageAdNativeFactory;
import com.mobisage.android.MobiSageAdNativeFactoryListener;
import com.mobisage.android.MobiSageAdPoster;
import com.mobisage.android.MobiSageAdPosterListener;
import com.mobisage.android.MobiSageManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  广告管理类
 * @author hw
 *
 */
public class AdManager {

    public static AdManager adManager;
    private Context context;

    /**
     * 单例模式获取管理者对象
     *
     */
    public static AdManager getInstance(Context context) {
        if (adManager == null) {
            adManager = new AdManager(context);
        }
        return adManager;
    }
    /**
     * 构造器，需要上下文context
     *
     */
    private AdManager(Context context) {
        this.context = context;
        MobiSageManager.getInstance().initMobiSageManager(context,
                "raytfsI5ILeduIv3Kg==");
    }

    /**
     * 创建 Banner广告
     * @param mBannerListener  Banner广告 监听器
     * @return 返回banner广告对象
     */
    public MobiSageAdBanner createBanner(MobiSageAdBannerListener mBannerListener) {
        MobiSageAdBanner mBanner = new MobiSageAdBanner(context, "Y2JjsAz07nlTdkU55PC4Xu9F");
        mBanner.setMobiSageAdBannerListener(mBannerListener);
        return mBanner;
    }

    /**
     * 销毁Banner广告
     * @param mBanner  要销毁的广告对象
     */
    public void destroyBanner(MobiSageAdBanner mBanner) {
        // 销毁广告
        if (mBanner != null) {
            mBanner.destroyAdView();
            mBanner=null;
        }
    }

    /**
     * 创建插屏广告
     * @param activity  要显示插屏广告的activity对象
     * @param adPosterListener 插屏广告监听器
     * @return 返回插屏广告对象
     */
    public MobiSageAdPoster createPoster(Activity activity,
            MobiSageAdPosterListener adPosterListener) {
        // 创建一个自适应屏幕的插屏广告
        MobiSageAdPoster mPoster = new MobiSageAdPoster(activity, "l5aXRPgAGo2ngrHNEARMqhuw",
                MobiSageAdPoster.SIZE_300X250);
        // // 设置广告监听
        mPoster.setMobiSageAdPosterListener(adPosterListener);
        return mPoster;
    }
    /**
     * 显示插屏广告
     * @param mPoster
     */
    public void showPoster(MobiSageAdPoster mPoster) {
        // 展示广告
        if (mPoster != null) {
            mPoster.show();
        }
    }
/**
 * 创建信息流广告
 * @param isModule  是否使用模板模式，true 是使用模板，false是是使用自定义模式
 * @param mModuleMode 使用模板模式时战士的样式   如：MobiSageAdNativeFactory。SIMPLE2
 * @param mAdWidth 广告宽
 * @param mAdHeight 广告高
 * @param mNativeFatoryListener 信息流广告的监听器
 * @return 返回信息流广告工厂
 */
    public MobiSageAdNativeFactory createNative(boolean isModule,String mModuleMode,int mAdWidth, int mAdHeight,
            MobiSageAdNativeFactoryListener mNativeFatoryListener) {

        // 实例化成组信息流广告,hashmap为高级选项,模板采用classic形式
        MobiSageAdNativeFactory mMobiSageAdNativeFactory = new MobiSageAdNativeFactory(context,
                "bWxtvgL64HddeEs36v62UOFD", mAdWidth,
                mAdHeight);

      //设置展示模式，展示有四种模式：CLASSIC，IMG，SIMPLE1，SIMPLE2；开发者自行选择
        if (!TextUtils.isEmpty(mModuleMode)) {
            mMobiSageAdNativeFactory.setModuleMode(mModuleMode);
        }
        //请求广告类型标签
        String[] tag = {
                "ts", "ss", "aa"
        };
        //标志便签是正选还是反选，0是正选，1是反选。
        int mInver = 0;
        //请求广告类型的标签（选填
        mMobiSageAdNativeFactory.setTags(mInver,tag);
        // 设置customtag标签 （选填）
        mMobiSageAdNativeFactory.setCustomtag(getCustomtag());
        //设置模板模式，true是模板模式，false是自定义模式
        mMobiSageAdNativeFactory.setIsModule(isModule);
        //设置广告工厂监听
        mMobiSageAdNativeFactory.setMobiSageAdNativeGroupListener(mNativeFatoryListener);
        //设置请求广告个数，一次最多请求10个
        mMobiSageAdNativeFactory.LoadAds(5);
        return mMobiSageAdNativeFactory;
    }

    /**
     * 拼装 customtag
     * 
     * @return
     */
    private HashMap<String, Object> getCustomtag() {
        HashMap<String, Object> customtag = new HashMap<String, Object>();
        HashMap<String, Object> usertag = new HashMap<String, Object>();
        HashMap<String, Object> appinfo = new HashMap<String, Object>();
        usertag.put("country", "中国");
        usertag.put("province", "北京");
        usertag.put("city", "北京");
        usertag.put("address", "xx路xx号");
        usertag.put("addname", "北京大学");
        usertag.put("addtag", "[\"大学\",\"教育\"]");
        usertag.put("bcircle", "[\"abc\",\"efg\"]");
        usertag.put("lon", 100);
        usertag.put("lat", 100);
        JSONArray channel = new JSONArray();
        JSONObject cone = new JSONObject();
        JSONObject ctwo = new JSONObject();
        try {
            cone.put("f", "新闻");
            cone.put("s", "手机");
            cone.put("t", "android");
            ctwo.put("f", "科技");
            ctwo.put("s", "平板");
            ctwo.put("t", "aPad");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        channel.put(cone);
        channel.put(ctwo);
        appinfo.put("channel", channel);
        JSONArray content = new JSONArray();
        content.put("aa");
        content.put("bb");
        appinfo.put("content", content);
        customtag.put("usertag", usertag);
        customtag.put("appinfo", appinfo);
        return customtag;
    }
    /**
     * 获取信息流广告工厂的的所有 信息流广告 对象
     * @param mNativeFactory 信息流广告工厂
     * @return 信息流广告组，如果信息流广告工厂为空，则返回空array
     */
    public ArrayList<MobiSageAdNative> getNativeViews(MobiSageAdNativeFactory mNativeFactory){
        if (mNativeFactory!=null) {
            return mNativeFactory.getAdNatives();
        }else {
            return new ArrayList<MobiSageAdNative>();
        }
    }

    /**
     * 获取信息流广告工厂的指定信息流广告对象
     * @param mNativeFactory 信息流广告工厂对象
     * @param position 要获取的信息流广告对象的下标
     * @return 信息流广告，如果没有则为空
     */
    public MobiSageAdNative getNativeView(MobiSageAdNativeFactory mNativeFactory,int position){
        if (getNativeViews(mNativeFactory).size()>position) {
            return  getNativeViews(mNativeFactory).get(position);
        }else {
            return null;
        }
    }
}
