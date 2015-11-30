package com.mobisage.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.mobisage.android.MobiSageManager;

public class SplashScreen extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		AdManager.getInstance(getApplicationContext());
		jump();
	}

	private void jump() {
		// 跳转到主界面
		Intent intent = new Intent(this, MobiSageSample.class);
		startActivity(intent);
		finish();
	}
}
