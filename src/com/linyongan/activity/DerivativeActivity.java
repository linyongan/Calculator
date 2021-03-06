package com.linyongan.activity;

import com.linyongan.view.TitleView1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 期权计算页面
 */
public class DerivativeActivity extends Activity {
	/** 期权理论价格 */
	private Button Button1;
	/** 期权到期收益 */
	private Button Button2;
	/** 期权组合损益分析 */
	private Button Button3;
	/** 保证金 */
	private Button Button4;
	/** 期权Delta值 */
	private Button Button5;
	/** 内在价值&时间价值 */
	private Button Button6;
	/** Delta&杠杆倍数 */
	private Button Button7;
	/** 个人理财 */
	private Button Button8;
	/** 贷款计算 */
	private Button Button9;
	/** 标题 */
	private TitleView1 titleView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.derivative);
		// 获取数据存进数据库
		Button1 = (Button) findViewById(R.id.derivative_Button1);
		Button2 = (Button) findViewById(R.id.derivative_Button2);
		Button3 = (Button) findViewById(R.id.derivative_Button3);
		Button4 = (Button) findViewById(R.id.derivative_Button4);
		Button5 = (Button) findViewById(R.id.derivative_Button5);
		Button6 = (Button) findViewById(R.id.derivative_Button6);
		Button7 = (Button) findViewById(R.id.derivative_Button7);
		Button8 = (Button) findViewById(R.id.derivative_Button8);
		Button9 = (Button) findViewById(R.id.derivative_Button9);
		Button1.setOnClickListener(new ButtonListener());
		Button2.setOnClickListener(new ButtonListener());
		Button3.setOnClickListener(new ButtonListener());
		Button4.setOnClickListener(new ButtonListener());
		Button5.setOnClickListener(new ButtonListener());
		Button6.setOnClickListener(new ButtonListener());
		Button7.setOnClickListener(new ButtonListener());
		Button8.setOnClickListener(new ButtonListener());
		Button9.setOnClickListener(new ButtonListener());
		titleView = (TitleView1) findViewById(R.id.TitleView1);
		titleView.setTitleText("期权计算");
		titleView.setLeftButtonListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goBack();
			}
		});
	}

	private class ButtonListener implements OnClickListener {

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.derivative_Button1:
				Intent intent1 = new Intent(DerivativeActivity.this,
						Button1Activity.class);
				startActivity(intent1);
				break;
			case R.id.derivative_Button2:
				Intent intent2 = new Intent(DerivativeActivity.this,
						Button2Activity.class);
				startActivity(intent2);
				break;
			case R.id.derivative_Button3:
				Intent intent3 = new Intent(DerivativeActivity.this,
						Button3Activity.class);
				startActivity(intent3);
				break;
			case R.id.derivative_Button4:
				Intent intent4 = new Intent(DerivativeActivity.this,
						Button4Activity.class);
				startActivity(intent4);
				break;
			case R.id.derivative_Button5:
				Intent intent5 = new Intent(DerivativeActivity.this,
						Button5Activity.class);
				startActivity(intent5);
				break;
			case R.id.derivative_Button6:
				Intent intent6 = new Intent(DerivativeActivity.this,
						Button6Activity.class);
				startActivity(intent6);
				break;
			case R.id.derivative_Button7:
				Intent intent7 = new Intent(DerivativeActivity.this,
						Button7Activity.class);
				startActivity(intent7);
				break;
			case R.id.derivative_Button8:
				Intent intent8 = new Intent(DerivativeActivity.this,
						FinanceActivity.class);
				startActivity(intent8);
				break;
			case R.id.derivative_Button9:
				Intent intent9 = new Intent(DerivativeActivity.this,
						LoanActivity.class);
				startActivity(intent9);
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
		finish();
	}
}