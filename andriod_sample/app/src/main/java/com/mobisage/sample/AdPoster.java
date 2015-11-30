package com.mobisage.sample;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.mobisage.android.MobiSageAdPoster;
import com.mobisage.android.MobiSageAdPosterListener;

public class AdPoster extends BaseActivity implements OnClickListener {

	private MobiSageAdPoster mPoster;
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poster);
		setLogo();
		Button button = (Button) findViewById(R.id.btn_create);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.btn_show);
		button.setEnabled(false);
		button.setOnClickListener(this);
	}


	private MobiSageAdPosterListener mListener = new MobiSageAdPosterListener() {

	  //插屏广告预加载成功时，此接口被回调，建议在收到此通知后，展示广告，否则影响广告展示的成功率
		@Override
		public void onMobiSagePosterPreShow() {
			Log.i("MobisageSample", "onMobiSagePosterPreShow");
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					findViewById(R.id.btn_show).setEnabled(true);
				}
			});
		}
		
		//插屏广告请求失败时，此接口被回调，多用于聚合中切换广告平台,msg为错误信息
		@Override
		public void onMobiSagePosterError(String msg) {
			Log.i("MobisageSample", "onMobiSagePosterError:" + msg);
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					findViewById(R.id.btn_show).setEnabled(false);
				}
			});
		}
		//插屏广告被点击时，此接口被回调，多用于聚合中统计广告点击数
		@Override
		public void onMobiSagePosterClick() {
			Log.i("MobisageSample", "onMobiSagePosterClick");
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					findViewById(R.id.btn_show).setEnabled(false);
				}
			});
		}

		//插屏广告被关闭或被销毁时，此接口被回调，一般情况下不用处理
		@Override
		public void onMobiSagePosterClose() {
			Log.i("MobisageSample", "onMobiSagePosterClose");
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					findViewById(R.id.btn_show).setEnabled(false);
				}
			});
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_create:
			findViewById(R.id.btn_show).setEnabled(false);
			mPoster = AdManager.getInstance(getApplicationContext()).createPoster(AdPoster.this, mListener);
			break;
		case R.id.btn_show:
			AdManager.getInstance(getApplicationContext()).showPoster(mPoster);
			break;
		default:
			break;
		}
	}
}
