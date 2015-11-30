
package com.mobisage.sample;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobisage.android.MobiSageAdNative;
import com.mobisage.android.MobiSageAdNativeFactory;
import com.mobisage.android.MobiSageAdNativeFactoryListener;
import com.mobisage.sample.view.Joke;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NativeClassic extends BaseActivity {

    private MobiSageAdNativeFactory mMobiSageAdNativeFactory;
    private ListView mListView;
    private ClassicAdapter mClassicAdapter;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.native_classic);
        initView();
        int mAdWidth = (int)
                (getResources().getDisplayMetrics().widthPixels - getResources()
                        .getDisplayMetrics().density * 10);
        int mAdHeight = MobiSageAdNativeFactory.NONE;
        mMobiSageAdNativeFactory = AdManager.getInstance(getApplicationContext()).createNative(true,MobiSageAdNativeFactory.CLASSIC,mAdWidth,
                mAdHeight, new MobiSageAdNativeFactoryListener() {
                    //广告请求失败时通知,message为失败信息
                    @Override
                    public void onMobiSageNativeGroupError(MobiSageAdNativeFactory arg0,
                            String arg1) {
                    }
                    //广告请求成功返回时通知
                    @Override
                    public void onMobiSageNativeGroupSuccess(MobiSageAdNativeFactory arg0) {
                    }
                    //广告加载成功时调用
                    @Override
                    public void onMobiSageNativeGroupLoadSuccess(
                            MobiSageAdNativeFactory mobiSageAdNativeFactory) {
                        mClassicAdapter.notifyDataSetChanged();

                    }
                });

    }

    private void initView() {
        setText();
        mTitle.setText("经典类示例");

        mBackBtn.setOnClickListener(this);
        mBottomText = (TextView) findViewById(R.id.bottom_bar_text);
        mBottomText.setText("适合有内容流类APP");
        mListView = (ListView) findViewById(R.id.native_classic_listview);
        mListView.setDividerHeight(0);
        ArrayList<Joke> jokes = new ArrayList<Joke>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                jokes.add(new Joke(Joke.NAMES[j], Joke.CONTENTS[j],
                        Joke.LOGOS[j], ""));
            }
        }

        mClassicAdapter = new ClassicAdapter(this, jokes);
        mListView.setAdapter(mClassicAdapter);
    }

    private class ClassicAdapter extends ArrayAdapter<Joke> {

        public ClassicAdapter(Context context, List<Joke> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View item = null;
            if (item == null) {
                item = getLayoutInflater().inflate(
                        R.layout.native_classic_item, null);
            }

            if ((position + 1) % 5 == 0 && position != 0
                    && mMobiSageAdNativeFactory.getAdNatives().size() != 0
                    && mMobiSageAdNativeFactory.getAdNatives().size() > (position - 4) / 5) {

                LinearLayout ll_native_classic_item_ad = (LinearLayout) item
                        .findViewById(R.id.ll_native_classic_item_ad);
                ll_native_classic_item_ad.setVisibility(View.VISIBLE);
                RelativeLayout rl_native_classic_item = (RelativeLayout) item
                        .findViewById(R.id.rl_native_classic_item);
                TextView native_classic_item_cotent = (TextView) item
                        .findViewById(R.id.native_classic_item_cotent);
                rl_native_classic_item.setVisibility(View.GONE);
                native_classic_item_cotent.setVisibility(View.GONE);
                ll_native_classic_item_ad.addView(AdManager.getInstance(getApplicationContext())
                        .getNativeView(mMobiSageAdNativeFactory,
                                (position - 4) / 5));

            } else {
                LinearLayout ll_native_classic_item_ad = (LinearLayout) item
                        .findViewById(R.id.ll_native_classic_item_ad);
                ll_native_classic_item_ad.setVisibility(View.GONE);
                RelativeLayout rl_native_classic_item = (RelativeLayout) item
                        .findViewById(R.id.rl_native_classic_item);
                TextView native_classic_item_cotent = (TextView) item
                        .findViewById(R.id.native_classic_item_cotent);
                rl_native_classic_item.setVisibility(View.VISIBLE);
                native_classic_item_cotent.setVisibility(View.VISIBLE);
                
                
                DisplayImageOptions mOptions = new DisplayImageOptions.Builder().showImageOnLoading(null)
                        .showImageForEmptyUri(null).showImageOnFail(null)
                        .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
                        .displayer(new RoundedBitmapDisplayer((int) (5 * getResources().getDisplayMetrics().density))).build();
                
                
                ImageLoader.getInstance().displayImage(getItem(position).getLogo(),
                        ((ImageView) item
                                .findViewById(R.id.native_classic_item_logo)),mOptions,null);
                
                ((TextView)item.findViewById(R.id.native_classic_item_cotent)).setText(getItem(position).getContent());
                ((TextView)item.findViewById(R.id.native_classic_item_title)).setText(getItem(position).getTitle());
                
            }

            return item;
        }

    }

}
