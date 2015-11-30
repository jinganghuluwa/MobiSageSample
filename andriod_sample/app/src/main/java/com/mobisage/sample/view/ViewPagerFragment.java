package com.mobisage.sample.view;

import android.R.raw;
import android.os.Bundle;
import android.provider.MediaStore.Images.ImageColumns;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobisage.android.MobiSageAdNative;
import com.mobisage.sample.R;
import com.mobisage.sample.R.drawable;
import com.mobisage.sample.R.id;
import com.mobisage.sample.R.layout;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public final class ViewPagerFragment extends Fragment {
	public MobiSageAdNative mMobiSageAdNative = null;

	public static ViewPagerFragment newInstance(
			MobiSageAdNative mobiSageAdNative) {
		ViewPagerFragment fragment = new ViewPagerFragment();

		fragment.mMobiSageAdNative = mobiSageAdNative;

		return fragment;
	}

	public void setMobiSageAd(MobiSageAdNative mobiSageAdNative) {
		mMobiSageAdNative = mobiSageAdNative;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.native_viewpager, null);
		if (mMobiSageAdNative != null) {
			DisplayImageOptions mOptions = new DisplayImageOptions.Builder().showImageOnLoading(null)
	                .showImageForEmptyUri(null).showImageOnFail(null)
	                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
	                .displayer(new RoundedBitmapDisplayer(20)).build();
			ImageLoader.getInstance().displayImage(
			        (String) mMobiSageAdNative.getImage(),
                    ((ImageView) view
                            .findViewById(R.id.vpager_image)),
                    mOptions, null);
			
			
			
			((TextView)view.findViewById(R.id.vpager_text)).setText((String) mMobiSageAdNative.getTitle());
			mMobiSageAdNative.attachToView((ViewGroup) view);
		} else {
			
			((ImageView)view.findViewById(R.id.vpager_image)).setImageResource(R.drawable.noneh);
			((TextView)view.findViewById(R.id.vpager_text)).setText("");
		}
		return view;
	}

}
