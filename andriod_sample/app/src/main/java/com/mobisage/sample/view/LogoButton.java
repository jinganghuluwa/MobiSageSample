package com.mobisage.sample.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobisage.sample.R;
import com.mobisage.sample.R.id;
import com.mobisage.sample.R.layout;

public class LogoButton extends RelativeLayout {

	private ImageView mLogo;
	private TextView mName;

	public LogoButton(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater inflater = ((LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
		inflater.inflate(R.layout.logobutton, this, true);
		mLogo = (ImageView) findViewById(R.id.logobutton_logo);
		mName = (TextView) findViewById(R.id.logobutton_text);
	}

	public LogoButton(Context context) {
		super(context);
		((LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.logobutton, this);
		mLogo = (ImageView) findViewById(R.id.logobutton_logo);
		mName = (TextView) findViewById(R.id.logobutton_text);
	}

	public void setData(int res, String text) {
		mLogo.setBackgroundResource(res);
		mName.setText(text);
	}

}
