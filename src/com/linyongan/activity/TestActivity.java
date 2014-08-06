package com.linyongan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * 自我测试页面
 */
public class TestActivity extends Activity {
	/** 返回按钮 */
	private ImageButton backButton;
	/** 基础测试按钮 */
	private Button baseButton;
	/** 进阶测试1按钮 */
	private Button advanceButton1;
	/** 进阶测试2按钮 */
	private Button advanceButton2;
	/** 难题收藏按钮 */
	private Button collectButton;
	/** 考试成绩按钮 */
	private Button gradeButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test_main);
		backButton = (ImageButton) findViewById(R.id.test_back_bn);
		baseButton = (Button) findViewById(R.id.test_baseButton);
		advanceButton1 = (Button) findViewById(R.id.test_advanceButton1);
		advanceButton2 = (Button) findViewById(R.id.test_advanceButton2);
		collectButton = (Button) findViewById(R.id.test_collectButton);
		gradeButton = (Button) findViewById(R.id.test_gradeButton);

		backButton.setOnClickListener(new ButtonListener());
		baseButton.setOnClickListener(new ButtonListener());
		advanceButton1.setOnClickListener(new ButtonListener());
		advanceButton2.setOnClickListener(new ButtonListener());
		collectButton.setOnClickListener(new ButtonListener());
		gradeButton.setOnClickListener(new ButtonListener());
	}

	/**
	 * button的监听事件
	 * 
	 * @author yongan
	 * 
	 */
	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.test_back_bn:
				goBack();
				break;
			case R.id.test_baseButton:
				Intent intent2 = new Intent(TestActivity.this,
						TestingActivity.class);
				int testNum2 = 120;
				// 记得发送字符串！！！！！
				intent2.putExtra("testNum", testNum2 + "");
				startActivity(intent2);
				break;
			case R.id.test_advanceButton1:
				Intent intent3 = new Intent(TestActivity.this,
						TestingActivity.class);
				int testNum3 = 30;
				// 记得发送字符串！！！！！
				intent3.putExtra("testNum", testNum3 + "");
				startActivity(intent3);
				break;
			case R.id.test_advanceButton2:
				Intent intent4 = new Intent(TestActivity.this,
						TestingActivity.class);
				int testNum4 = 90;
				// 记得发送字符串！！！！！
				intent4.putExtra("testNum", testNum4 + "");
				startActivity(intent4);
				break;
			case R.id.test_collectButton:
				Intent intent5 = new Intent(TestActivity.this,
						TestCollectActivity.class);
				startActivity(intent5);
				break;
			case R.id.test_gradeButton:
				Intent intent6 = new Intent(TestActivity.this,
						TestGradeActivity.class);
				startActivity(intent6);
				break;
			}
		}

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
		Intent intent = new Intent(TestActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}