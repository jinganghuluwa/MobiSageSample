
package com.mobisage.sample;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobisage.android.MobiSageAdNative;
import com.mobisage.android.MobiSageAdNativeFactory;
import com.mobisage.android.MobiSageAdNativeFactoryListener;
import com.mobisage.sample.view.Joke;
import com.mobisage.sample.view.MultiColumnListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class NativeDiyTwo extends BaseActivity {
    private MultiColumnListView mMultiColumnListView;
    private DiyTwoAdapter mDiyTwoAdapter;
    private MobiSageAdNativeFactory mMobiSageAdNativeFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.native_diy_two);
        initView();
        int mAdWidth = (int) (getResources().getDisplayMetrics().widthPixels / 2);
        mMobiSageAdNativeFactory = AdManager.getInstance(getApplicationContext()).createNative(
                false,
                null, mAdWidth, mAdWidth / 9 * 16, new MobiSageAdNativeFactoryListener() {
                    // 广告请求成功返回时通知
                    @Override
                    public void onMobiSageNativeGroupSuccess(
                            MobiSageAdNativeFactory mobiSageAdNativeFactory) {
                        // TODO Auto-generated method stub
                        mDiyTwoAdapter.notifyDataSetChanged();
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
        mTitle.setText("自定义广告之二");
        mBackBtn.setOnClickListener(this);
        mBottomText = (TextView) findViewById(R.id.bottom_bar_text);
        mBottomText.setText("适合图片流类APP");
        mMultiColumnListView = (MultiColumnListView) findViewById(R.id.native_diytwo_list);
        ArrayList<Joke> jokes = new ArrayList<Joke>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                jokes.add(new Joke(Joke.NAMES[j], "", Joke.LOGOS[j],
                        Joke.IMAGES[j]));
            }
        }

        mDiyTwoAdapter = new DiyTwoAdapter(this, jokes);
        mMultiColumnListView.setAdapter(mDiyTwoAdapter);
    }

    private class DiyTwoAdapter extends ArrayAdapter<Joke> {

        public DiyTwoAdapter(Context context, List<Joke> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }

        @Override
        public View getView(final int position, View convertView,
                ViewGroup parent) {
            View view = convertView;

            DisplayImageOptions mOptions = new DisplayImageOptions.Builder()
                    .showImageOnLoading(null)
                    .showImageForEmptyUri(null)
                    .showImageOnFail(null)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .displayer(
                            new RoundedBitmapDisplayer((int) (5 * getResources()
                                    .getDisplayMetrics().density))).build();

            DisplayImageOptions mOptions1 = new DisplayImageOptions.Builder()
                    .showImageOnLoading(null)
                    .showImageForEmptyUri(null).showImageOnFail(null)
                    .cacheInMemory(true).cacheOnDisk(true)
                    .build();
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.native_diytwo_item,
                        null);
            }
            if (AdManager.getInstance(NativeDiyTwo.this).getNativeViews(mMobiSageAdNativeFactory)
                    .size() == 0
                    || (position + 1) % 5 != 0) {

                ImageLoader.getInstance().displayImage(getItem(position).getImage(),
                        ((ImageView) view.findViewById(R.id.native_diy_two_image)), mOptions1);

                ImageLoader.getInstance().displayImage(
                        getItem(position).getLogo(),
                        ((ImageView) view
                                .findViewById(R.id.native_diy_two_logo)), mOptions, null);

                ((TextView) view.findViewById(R.id.native_diy_two_name)).setText(
                        getItem(position).getTitle());

            } else {
                MobiSageAdNative nativead;
                if (AdManager.getInstance(getApplicationContext())
                        .getNativeViews(mMobiSageAdNativeFactory).size() < 5) {
                    nativead = AdManager.getInstance(getApplicationContext()).getNativeView(
                            mMobiSageAdNativeFactory, 0);
                } else {
                    nativead = AdManager.getInstance(getApplicationContext()).getNativeView(
                            mMobiSageAdNativeFactory, (position - 4) / 5);
                }

                ImageLoader.getInstance().displayImage(nativead.getImage(),
                        ((ImageView) view.findViewById(R.id.native_diy_two_image)), mOptions1);

                ImageLoader.getInstance().displayImage(
                        nativead.getLogo(),
                        ((ImageView) view
                                .findViewById(R.id.native_diy_two_logo)), mOptions, null);

                ((TextView) view.findViewById(R.id.native_diy_two_name)).setText(nativead
                        .getTitle());

                nativead.attachToView((ViewGroup) view);
            }
            return view;
        }
    }

}
