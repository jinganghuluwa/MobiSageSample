
package com.mobisage.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobisage.android.MobiSageAdNativeFactory;
import com.mobisage.android.MobiSageAdNativeFactoryListener;
import com.mobisage.sample.view.CirclePageIndicator;
import com.mobisage.sample.view.PageIndicator;
import com.mobisage.sample.view.ViewPagerFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NativeDiyThree extends BaseActivity {
    private ViewPager mPager;
    private PageIndicator mIndicator;
    private TestFragmentAdapter mAdapter;
    private FocusAdapter mFocusAdapter;
    private ListView mListView;
    private MobiSageAdNativeFactory mMobiSageAdNativeFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.native_diy_three);
        initView();
        int mAdWidth = (int) (getResources().getDisplayMetrics().widthPixels);
        mMobiSageAdNativeFactory = AdManager.getInstance(getApplicationContext()).createNative(
                false, null, mAdWidth, mAdWidth * 9 / 16, new MobiSageAdNativeFactoryListener() {
                    // 广告请求成功返回时通知
                    @Override
                    public void onMobiSageNativeGroupSuccess(
                            MobiSageAdNativeFactory mobiSageAdNativeFactory) {
                        // TODO Auto-generated method stub
                        mAdapter.notifyDataSetChanged();
                    }

                    // 广告加载成功时调用
                    @Override
                    public void onMobiSageNativeGroupLoadSuccess(
                            MobiSageAdNativeFactory mobiSageAdNativeFactory) {
                        // TODO Auto-generated method stub

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
        mTitle.setText("自定义广告之三");
        mBackBtn.setOnClickListener(this);
        mBottomText = (TextView) findViewById(R.id.bottom_bar_text);
        mBottomText.setText("适合内容流类APP");
        ((RelativeLayout) findViewById(R.id.native_ditthree_relative))
                .setLayoutParams(new LinearLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT, getResources()
                                .getDisplayMetrics().widthPixels * 9 / 16));
        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.native_diy_pager);
        mFocusAdapter = new FocusAdapter(this, new ArrayList<String>());
        mFocusAdapter.add("度假热播");
        mFocusAdapter.add("芒果综艺");
        mFocusAdapter.add("卡通动漫");
        mFocusAdapter.add("美国大片");
        mListView = (ListView) findViewById(R.id.native_diy_three_list);
        mListView.setAdapter(mFocusAdapter);
        mIndicator = (CirclePageIndicator) findViewById(R.id.native_diy_indicator);
        mPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mPager);
    }

    private class TestFragmentAdapter extends FragmentPagerAdapter {
        public TestFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        private int mCount = 5;

        @Override
        public Fragment getItem(int position) {
            return ViewPagerFragment
                    .newInstance(mMobiSageAdNativeFactory.getAdNatives().size() == 0 ? null
                            : (mMobiSageAdNativeFactory.getAdNatives().size() == 5 ? mMobiSageAdNativeFactory
                                    .getAdNatives().get(position)
                                    : mMobiSageAdNativeFactory.getAdNatives()
                                            .get(0)));
        }

        @Override
        public int getCount() {
            return mCount;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ViewPagerFragment f = (ViewPagerFragment) super.instantiateItem(
                    container, position);
            f.setMobiSageAd(AdManager.getInstance(getApplicationContext())
                    .getNativeViews(mMobiSageAdNativeFactory).size() == 0 ? null
                    : (AdManager.getInstance(getApplicationContext())
                            .getNativeViews(mMobiSageAdNativeFactory).size() == 5 ?
                            AdManager.getInstance(getApplicationContext()).getNativeView(
                                    mMobiSageAdNativeFactory, position)
                            : AdManager.getInstance(getApplicationContext()).getNativeView(
                                    mMobiSageAdNativeFactory, 0)));
            return f;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

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
