package com.linyongan.view;

import com.linyongan.activity.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 普通页面的标题
 * 
 * @author yongan
 * 
 */
public class TitleView1 extends FrameLayout {

	private ImageButton leftButton;

	private TextView titleText;

	public TitleView1(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.titleview1, this);
		titleText = (TextView) findViewById(R.id.titleview_textView2);
		leftButton = (ImageButton) findViewById(R.id.titleview_back_bn);
	}

	public void setTitleText(String text) {
		titleText.setText(text);
	}

	public void setLeftButtonListener(OnClickListener l) {
		leftButton.setOnClickListener(l);
	}

}