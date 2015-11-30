package com.mobisage.sample.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobisage.sample.BaseActivity;
import com.mobisage.sample.NativeClassic;
import com.mobisage.sample.NativeDiyOne;
import com.mobisage.sample.NativeDiyThree;
import com.mobisage.sample.NativeDiyTwo;
import com.mobisage.sample.NativeFocus;
import com.mobisage.sample.NativeSample;
import com.mobisage.sample.R;
import com.mobisage.sample.R.drawable;
import com.mobisage.sample.R.id;
import com.mobisage.sample.R.layout;

public class AdNativeLayout extends BaseActivity {

	private LogoButton mClassicButton;
	private LogoButton mSampleOneButton;
	private LogoButton mFocusButton;
	private LogoButton mDiyOneButton;
	private LogoButton mDiyTwoButton;
	private LogoButton mDiyThreeButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.native_layout);
		initView();

	}

	private void initView() {
		setLogo();
		mClassicButton = (LogoButton) findViewById(R.id.native_classic_button);
		mClassicButton.setData(R.drawable.icon_1, "经典类示例");
		mSampleOneButton = (LogoButton) findViewById(R.id.native_sample_button);
		mSampleOneButton.setData(R.drawable.icon_2, "简单类实例");
		mFocusButton = (LogoButton) findViewById(R.id.native_focus_button);
		mFocusButton.setData(R.drawable.icon_3, "焦点图示例");
		mDiyOneButton = (LogoButton) findViewById(R.id.native_diy_one_button);
		mDiyOneButton.setData(R.drawable.icon_4, "自定义广告之一");
		mDiyTwoButton = (LogoButton) findViewById(R.id.native_diy_two_button);
		mDiyTwoButton.setData(R.drawable.icon_5, "自定义广告之二");
		mDiyThreeButton = (LogoButton) findViewById(R.id.native_diy_three_button);
		mDiyThreeButton.setData(R.drawable.icon_6, "自定义广告之三");
		mClassicButton.setOnClickListener(this);
		mSampleOneButton.setOnClickListener(this);
		mFocusButton.setOnClickListener(this);
		mDiyOneButton.setOnClickListener(this);
		mDiyTwoButton.setOnClickListener(this);
		mDiyThreeButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		int id = v.getId();
		switch (id) {
		case R.id.native_classic_button:
			intent.setClass(this, NativeClassic.class);
			break;
		case R.id.native_sample_button:
			intent.setClass(this, NativeSample.class);
			break;
		case R.id.native_focus_button:
			intent.setClass(this, NativeFocus.class);
			break;
		case R.id.native_diy_one_button:
			intent.setClass(this, NativeDiyOne.class);
			break;
		case R.id.native_diy_two_button:
			intent.setClass(this, NativeDiyTwo.class);
			break;
		case R.id.native_diy_three_button:
			intent.setClass(this, NativeDiyThree.class);
			break;

		default:
			break;
		}
		startActivity(intent);

	}

}
