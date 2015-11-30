package com.mobisage.sample;

import com.mobisage.android.MobiSageAdNativeFactory;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BaseActivity extends FragmentActivity implements OnClickListener,
		OnTouchListener {

	protected RelativeLayout mBackBtn;
	protected ImageView mBigLogo;
	protected TextView mTitle;
	protected ImageView mButterflyBtn;
	protected TextView mBottomText;
	protected ImageView mBackImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow()
				.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
	}

	
	protected void initview() {
		mBackBtn = (RelativeLayout) findViewById(R.id.titlebar_back_btn);
		mTitle = (TextView) findViewById(R.id.titlebar_title_txt);
		mButterflyBtn = (ImageView) findViewById(R.id.titlebar_rightlogo_image);
		mBigLogo = (ImageView) findViewById(R.id.titlebar_centerlogo_image);
		mBackImage = (ImageView) findViewById(R.id.titlebar_back_image);
		mBackBtn.setOnTouchListener(this);
	}

	protected void setLogo() {
		initview();
		mBackBtn.setVisibility(View.INVISIBLE);
		mBigLogo.setVisibility(View.VISIBLE);
		mTitle.setVisibility(View.INVISIBLE);
		mButterflyBtn.setVisibility(View.INVISIBLE);
	}

	protected void setText() {
		initview();
		mBackBtn.setVisibility(View.VISIBLE);
		mBigLogo.setVisibility(View.INVISIBLE);
		mTitle.setVisibility(View.VISIBLE);
		mButterflyBtn.setVisibility(View.VISIBLE);
	}

	@Override
	public void onClick(View v) {
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int id = v.getId();
		switch (id) {
		case R.id.titlebar_back_btn:
			int eventId = event.getAction();
			switch (eventId) {
			case MotionEvent.ACTION_DOWN:
				mBackImage.setBackgroundResource(R.drawable.jthui);
				break;

			case MotionEvent.ACTION_UP:
				finish();
				break;
			}

			break;
		default:
			break;
		}
		return true;
	}

}
