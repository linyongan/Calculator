package com.linyongan.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * 期权组合损益分析页面
 */
public class Button3Activity extends Activity {
	/** 返回按钮 */
	private ImageButton backButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.derivative3);

		backButton = (ImageButton) findViewById(R.id.Button3_back_bn);
		backButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				goBack();

			}
		});
	}


	/** 退出按钮 */
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			if (event.getAction() == KeyEvent.ACTION_DOWN
					&& event.getRepeatCount() == 0) {
				goBack();
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}

	/** 返回上一个界面 */
	private void goBack() {
		Intent intent = new Intent(Button3Activity.this,
				DerivativeActivity.class);
		startActivity(intent);
		finish();
	}
}