
package com.mobisage.sample;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mobisage.android.MobiSageAdNative;
import com.mobisage.android.MobiSageAdNativeFactory;
import com.mobisage.android.MobiSageAdNativeFactoryListener;
import com.mobisage.android.MobiSageAdStatusListener;
import com.mobisage.sample.view.Joke;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class NativeDiyOne extends BaseActivity {
    private MobiSageAdNativeFactory mMobiSageAdNativeFactory;
    private ListView mListView;
    private DiyOneAdapter mDiyOneAdapter;
    private float mDensity;
    private ArrayList<Joke> mJokes = new ArrayList<Joke>();
    private DisplayImageOptions mOptions;
    private DisplayImageOptions mOptions1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.native_diy_one);
        initView();
        int mAdWidth = (int) (getResources().getDisplayMetrics().widthPixels - getResources()
                .getDisplayMetrics().density * 20);
        mMobiSageAdNativeFactory = AdManager.getInstance(getApplicationContext()).createNative(
                false, null, mAdWidth, mAdWidth / 9 * 16, new MobiSageAdNativeFactoryListener() {
                    // 广告请求成功返回时通知
                    @Override
                    public void onMobiSageNativeGroupSuccess(
                            MobiSageAdNativeFactory mobiSageAdNativeFactory) {
                        // TODO Auto-generated method stub
                        mDiyOneAdapter.notifyDataSetChanged();

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
        mTitle.setText("自定义广告之一");
        mBackBtn.setOnClickListener(this);
        mOptions = new DisplayImageOptions.Builder().showImageOnLoading(null)
                .showImageForEmptyUri(null).showImageOnFail(null)
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(20)).build();
        mOptions1 = new DisplayImageOptions.Builder().showImageOnLoading(null)
                .showImageForEmptyUri(null).showImageOnFail(null)
                .cacheInMemory(true).cacheOnDisk(true)
                .build();
        mDensity = getResources().getDisplayMetrics().density;
        mBottomText = (TextView) findViewById(R.id.bottom_bar_text);
        mBottomText.setText("适合内容流类APP");
        mListView = (ListView) findViewById(R.id.native_diy_one_listview);
        mListView.setDividerHeight(0);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                mJokes.add(new Joke(Joke.NAMES[j], Joke.CONTENTS[j],
                        Joke.LOGOS[j], ""));
            }
        }

        mDiyOneAdapter = new DiyOneAdapter(this, mJokes);
        mListView.setAdapter(mDiyOneAdapter);
    }

    private String convertStatus(int status) {
        String strStatus = "";
        switch (status) {
            case 0:
                strStatus = "点击重试";
                break;
            case 2:
                strStatus = "下载中";
                break;
            case 3:
                strStatus = "点击安装";
                break;
            case 4:
                strStatus = "点击启动";
                break;
            case 1:
            default:
                strStatus = "下载";
        }
        return strStatus;
    }

    private class DiyOneAdapter extends ArrayAdapter<Joke> {

        public DiyOneAdapter(Context context, List<Joke> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getCount() {
            if (AdManager.getInstance(getApplicationContext())
                    .getNativeViews(mMobiSageAdNativeFactory).size() == 0) {
                return mJokes.size();
            } else {
                return mJokes.size() + 5;
            }
        }

        @Override
        public Joke getItem(int position) {
            if (AdManager.getInstance(getApplicationContext())
                    .getNativeViews(mMobiSageAdNativeFactory).size() == 0) {
                return mJokes.get(position);
            } else {
                return mJokes.get(position - position / 6);
            }
        }

        @Override
        public View getView(final int position, View convertView,
                ViewGroup parent) {
            View item = null;
            int type = 1;
            if ((position + 1) % 6 == 0 && position != 0) {
                type = 2;
            }

            if (mMobiSageAdNativeFactory.getAdNatives().size() == 0
                    || type == 1) {
                item = getLayoutInflater().inflate(
                        R.layout.native_classic_item, null);
                ImageLoader.getInstance().displayImage(
                        getItem(position).getLogo(),
                        ((ImageView) item
                                .findViewById(R.id.native_classic_item_logo)),
                        mOptions, null);
                ((TextView) item.findViewById(R.id.native_classic_item_cotent)).setText(getItem(
                        position).getContent());
                ((TextView) item.findViewById(R.id.native_classic_item_title)).setText(getItem(
                        position).getTitle());

            } else {
                item = getLayoutInflater().inflate(
                        R.layout.native_diy_one_ad_item, null);
                final MobiSageAdNative nativead;
                if (AdManager.getInstance(getApplicationContext())
                        .getNativeViews(mMobiSageAdNativeFactory).size() < 5) {
                    nativead = AdManager.getInstance(getApplicationContext()).getNativeView(
                            mMobiSageAdNativeFactory, 0);
                } else {
                    nativead = AdManager.getInstance(getApplicationContext()).getNativeView(
                            mMobiSageAdNativeFactory, (position - 5) / 6);
                }

                ImageLoader.getInstance().displayImage(
                        nativead.getLogo(),
                        ((ImageView) item
                                .findViewById(R.id.native_sample_item_lgo)),
                        mOptions, null);

                ImageLoader.getInstance().displayImage(nativead.getImage(),
                        ((ImageView) item.findViewById(R.id.native_sample_item_image)), mOptions1);

                ((TextView) item.findViewById(R.id.native_sample_item_title)).setText(nativead
                        .getTitle());

                double stars = nativead.getStar();
                int zheng = (int) stars;
                stars = nativead.getStar();
                ImageView one;
                ImageView half = new ImageView(NativeDiyOne.this);
                ImageView none;
                LayoutParams lay = new LayoutParams((int) (10 * mDensity),
                        (int) (10 * mDensity));
                half.setLayoutParams(lay);
                half.setBackgroundResource(R.drawable.star_2);
                LinearLayout starLayout = (LinearLayout) item
                        .findViewById(R.id.native_sample_item_stars);
                for (int i = 0; i < zheng; i++) {
                    one = new ImageView(NativeDiyOne.this);
                    one.setLayoutParams(lay);
                    one.setBackgroundResource(R.drawable.star);
                    starLayout.addView(one);
                }
                if (zheng != stars) {
                    starLayout.addView(half);

                }
                if (zheng == 0) {
                    for (int i = 0; i < 5; i++) {
                        none = new ImageView(NativeDiyOne.this);
                        none.setLayoutParams(lay);
                        none.setBackgroundResource(R.drawable.star_none);
                        starLayout.addView(none);
                    }
                } else {
                    for (int i = 0; i < 4 - zheng; i++) {
                        none = new ImageView(NativeDiyOne.this);
                        none.setLayoutParams(lay);
                        none.setBackgroundResource(R.drawable.star_2);
                        starLayout.addView(none);
                    }
                }

                ((TextView) item.findViewById(R.id.native_sample_item_people)).setText(
                        "有" + nativead.getPeople() + "人正在玩");
                Button download = (Button) item
                        .findViewById(R.id.native_sample_item_downlaod_btn);
                download.setText(convertStatus((Integer) nativead.getState()));
                final View item1 = item;
                nativead.setMobiSageAdStatusListener(new MobiSageAdStatusListener() {

                    @Override
                    public void updateAdStatus(int id, int status) {
                        ((TextView) item1.findViewById(R.id.native_sample_item_downlaod_btn))
                                .setText(
                                convertStatus(status));
                    }
                });
                nativead.attachToView((ViewGroup) item);
            }

            return item;
        }
    }

}
