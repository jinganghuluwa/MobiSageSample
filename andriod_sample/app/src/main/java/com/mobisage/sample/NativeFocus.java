
package com.mobisage.sample;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mobisage.android.MobiSageAdNative;
import com.mobisage.android.MobiSageAdNativeFactory;
import com.mobisage.android.MobiSageAdNativeFactoryListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NativeFocus extends BaseActivity {
    private int mAdWidth;

    private MobiSageAdNativeFactory mMobiSageAdNativeFactory;
    private FocusAdapter mFocusAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.native_focus);
        initView();
        mAdWidth = (int) (getResources().getDisplayMetrics().widthPixels);
        // 实例化成组信息流广告,hashmap为高级选项,模板采用image_slide形式,为轮播模板,此时mMobiSageAdNativeFactory.getAdNatives()的长度为1,获取里面的第0个元素即可
        mMobiSageAdNativeFactory = AdManager.getInstance(getApplicationContext()).createNative(
                true, MobiSageAdNativeFactory.SIMPLE1, mAdWidth, mAdWidth * 9 / 16,
                new MobiSageAdNativeFactoryListener() {
                    // 广告请求成功返回时通知
                    @Override
                    public void onMobiSageNativeGroupSuccess(
                            MobiSageAdNativeFactory mobiSageAdNativeFactory) {
                        // TODO Auto-generated method stub

                    }

                    // 广告加载成功时调用
                    @Override
                    public void onMobiSageNativeGroupLoadSuccess(
                            MobiSageAdNativeFactory mobiSageAdNativeFactory) {
                        // TODO Auto-generated method stub
                        mListView.addHeaderView(mobiSageAdNativeFactory.getAdNatives().get(0));
                    }

                    // 广告请求失败时通知,message为失败信息
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
        mTitle.setText("焦点类示例");
        mBackBtn.setOnClickListener(this);
        mBottomText = (TextView) findViewById(R.id.bottom_bar_text);
        mBottomText.setText("适合内容流类APP");
        mListView = (ListView) findViewById(R.id.native_focus_list);
        mFocusAdapter = new FocusAdapter(this, new ArrayList<String>());
        mFocusAdapter.add("度假热播");
        mFocusAdapter.add("芒果综艺");
        mFocusAdapter.add("卡通动漫");
        mFocusAdapter.add("美国大片");
        mListView.setAdapter(mFocusAdapter);
    }

    private class FocusAdapter extends ArrayAdapter<String> {

        public FocusAdapter(Context context, List<String> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (convertView == null) {

                view = getLayoutInflater().inflate(R.layout.native_focus_item,
                        null);
            }
            ((TextView) view.findViewById(R.id.native_focus_item_text))
                    .setText(getItem(position));

            return view;
        }

    }
}
