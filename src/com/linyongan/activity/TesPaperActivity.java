package com.linyongan.activity;

import com.linyongan.activity.MainActivity;
import com.linyongan.activity.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * 选择试卷页面
 */
public class TesPaperActivity extends Activity {
	/** 返回按钮 */
	private ImageButton backButton;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private Button button7;
	private Button button8;
	private Button button9;
	private Button button10;
	private Button button11;
	private Button button12;

	private int mark;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test_paper);
		// 找到所有的button
		button1 = (Button) findViewById(R.id.test_paper_Button1);
		button2 = (Button) findViewById(R.id.test_paper_Button2);
		button3 = (Button) findViewById(R.id.test_paper_Button3);
		button4 = (Button) findViewById(R.id.test_paper_Button4);
		button5 = (Button) findViewById(R.id.test_paper_Button5);
		button6 = (Button) findViewById(R.id.test_paper_Button6);
		button7 = (Button) findViewById(R.id.test_paper_Button7);
		button8 = (Button) findViewById(R.id.test_paper_Button8);
		button9 = (Button) findViewById(R.id.test_paper_Button9);
		button10 = (Button) findViewById(R.id.test_paper_Button10);
		button11 = (Button) findViewById(R.id.test_paper_Button11);
		button12 = (Button) findViewById(R.id.test_paper_Button12);
		backButton = (ImageButton) findViewById(R.id.test_paper_back_bn);

		Button[] buttons = new Button[] { button1, button2, button3, button4,
				button5, button6, button7, button8, button9, button10,
				button11, button12 };
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String string = bundle.getString("mark");
		mark = Integer.valueOf(string);
		for (int i = 0; i < mark; i++) {
			buttons[i].setVisibility(View.VISIBLE);
		}

		button1.setOnClickListener(new ButtonListener());
		button2.setOnClickListener(new ButtonListener());
		button3.setOnClickListener(new ButtonListener());
		button4.setOnClickListener(new ButtonListener());
		button5.setOnClickListener(new ButtonListener());
		button6.setOnClickListener(new ButtonListener());
		button7.setOnClickListener(new ButtonListener());
		button8.setOnClickListener(new ButtonListener());
		button9.setOnClickListener(new ButtonListener());
		button10.setOnClickListener(new ButtonListener());
		button11.setOnClickListener(new ButtonListener());
		button12.setOnClickListener(new ButtonListener());
		backButton.setOnClickListener(new ButtonListener());
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
			case R.id.test_paper_back_bn:
				goBack();
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
		Intent intent = new Intent(TesPaperActivity.this, TestActivity.class);
		startActivity(intent);
		finish();
	}
}