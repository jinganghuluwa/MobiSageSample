package com.mobisage.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobisage.sample.view.AdNativeLayout;
import com.mobisage.sample.view.LogoButton;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class MobiSageSample extends BaseActivity {
	private LogoButton mBannerButton;
	private LogoButton mNativeButton;
	private LogoButton mPosterButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample);
		setLogo();
		initImageLoader(this);
		mBannerButton = (LogoButton) findViewById(R.id.btn_banner);
		mBannerButton.setData(R.drawable.banner, "横幅广告");
		mBannerButton.setOnClickListener(this);
		mNativeButton = (LogoButton) findViewById(R.id.btn_native);
		mNativeButton.setData(R.drawable.nativead, "信息流广告");
		mNativeButton.setOnClickListener(this);
		mPosterButton = (LogoButton) findViewById(R.id.btn_poster);
		mPosterButton.setData(R.drawable.poster, "插屏广告");
		mPosterButton.setOnClickListener(this);
	}

	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
				context);
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
		config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		config.writeDebugLogs();
		ImageLoader.getInstance().init(config.build());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_banner: {
			Intent intent = new Intent(MobiSageSample.this, AdBanner.class);
			startActivity(intent);
		}
			break;
		case R.id.btn_native: {
			Intent intent = new Intent(MobiSageSample.this,
					AdNativeLayout.class);
			startActivity(intent);
		}
			break;
		case R.id.btn_poster: {
			Intent intent = new Intent(MobiSageSample.this, AdPoster.class);
			startActivity(intent);
		}
			break;
		default:
			break;
		}
	}
}
