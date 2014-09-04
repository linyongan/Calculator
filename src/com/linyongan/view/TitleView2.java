package com.linyongan.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.linyongan.activity.R;

/**
 * 计算器页面的标题
 * 
 * @author yongan
 * 
 */
public class TitleView2 extends FrameLayout {

	private ImageButton leftButton;
	private ImageButton cleanButton;
	private TextView titleText;

	public TitleView2(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.titleview2, this);
		titleText = (TextView) findViewById(R.id.titleview2_textView1);
		leftButton = (ImageButton) findViewById(R.id.titleview2_back_bn);
		cleanButton = (ImageButton) findViewById(R.id.titleview2_clean_bn);
	}

	public void setTitleText(String text) {
		titleText.setText(text);
	}

	public void setLeftButtonListener(OnClickListener l) {
		leftButton.setOnClickListener(l);
	}

	public void setCleanButtonListener(OnClickListener l) {
		cleanButton.setOnClickListener(l);
	}

}