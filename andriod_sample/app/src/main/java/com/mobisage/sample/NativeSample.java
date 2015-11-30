
package com.mobisage.sample;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mobisage.android.MobiSageAdNative;
import com.mobisage.android.MobiSageAdNativeFactory;
import com.mobisage.android.MobiSageAdNativeFactoryListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NativeSample extends BaseActivity {

    private WebView mWebView;
    private ListView mListView;
    private MobiSageAdNativeFactory mMobiSageAdNativeFactory;
    private SampleAdapter mSampleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.native_sample);
        initView();
        int mAdWidth = (int) (getResources().getDisplayMetrics().widthPixels - getResources()
                .getDisplayMetrics().density * 16);
        int mAdHeight = MobiSageAdNativeFactory.NONE;
        mMobiSageAdNativeFactory =  AdManager.getInstance(getApplicationContext()).createNative(true,MobiSageAdNativeFactory.SIMPLE2,mAdWidth, mAdHeight,
                new MobiSageAdNativeFactoryListener() {

                    @Override
                    public void onMobiSageNativeGroupSuccess(
                            MobiSageAdNativeFactory mobiSageAdNativeFactory) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onMobiSageNativeGroupLoadSuccess(
                            MobiSageAdNativeFactory mobiSageAdNativeFactory) {
                        // TODO Auto-generated method stub
                        mSampleAdapter.addAll(AdManager.getInstance(getApplicationContext()).getNativeViews(mMobiSageAdNativeFactory));
                        mSampleAdapter.notifyDataSetChanged();
                        ((LinearLayout) findViewById(R.id.sample_adlayout))
                                .setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onMobiSageNativeGroupError(
                            MobiSageAdNativeFactory mobiSageAdNativeFactory,
                            String message) {
                        // TODO Auto-generated method stub

                    }
                });
    }

    private void initView() {
        setText();
        mTitle.setText("简单类示例");
        mBottomText = (TextView) findViewById(R.id.bottom_bar_text);
        mBottomText.setText("适合较小广告位APP");
        mBackBtn.setOnClickListener(this);
        mWebView = (WebView) findViewById(R.id.native_sample_webview);
        mWebView.setClickable(false);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.loadUrl("file:///android_asset/news.html");
        mListView = (ListView) findViewById(R.id.native_sample_listview);

        mSampleAdapter = new SampleAdapter(this,
                new ArrayList<MobiSageAdNative>());
        mListView.setAdapter(mSampleAdapter);
    }

    private class SampleAdapter extends ArrayAdapter<MobiSageAdNative> {

        public SampleAdapter(Context context, List<MobiSageAdNative> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = AdManager.getInstance(getApplicationContext()).getNativeView(mMobiSageAdNativeFactory, position);
            return view;
        }

    }

}
