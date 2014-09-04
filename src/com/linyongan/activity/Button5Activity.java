package com.linyongan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.linyongan.view.TitleView2;

/**
 * 平价套利页面
 */
public class Button5Activity extends Activity {
	/** 标题 */
	private TitleView2 titleView;
	/** 计算按钮 */
	private Button calculateButton;
	/** 看涨期权价格(输入框) */
	private EditText editText1;
	/** 看跌期权价格(输入框) */
	private EditText editText2;
	/** 股票现价(输入框) */
	private EditText editText3;
	/** 剩余期限(输入框) */
	private EditText editText4;
	/** 行权价格(输入框) */
	private EditText editText5;
	/** 结果(文字) */
	private TextView out_tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.derivative5);
		// 找到所有的TextView
		out_tv = (TextView) findViewById(R.id.Button5_out_tv);
		// 找到所有的EditText
		editText1 = (EditText) findViewById(R.id.Button5_editText1);
		editText2 = (EditText) findViewById(R.id.Button5_editText2);
		editText3 = (EditText) findViewById(R.id.Button5_editText3);
		editText4 = (EditText) findViewById(R.id.Button5_editText4);
		editText5 = (EditText) findViewById(R.id.Button5_editText5);
		// 找到所有的Button
		calculateButton = (Button) findViewById(R.id.Button5_calculate_bt);
		calculateButton.setOnClickListener(new ButtonListener());
		titleView = (TitleView2) findViewById(R.id.TitleView2);
		titleView.setTitleText("平价套利");
		titleView.setLeftButtonListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goBack();
			}
		});
		titleView.setCleanButtonListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cleanEditText();
			}

		});
	}

	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.Button5_calculate_bt:
				// 获取输入框的数字，必须先取值！！！
				String test1 = editText1.getText().toString();
				String test2 = editText2.getText().toString();
				String test3 = editText3.getText().toString();
				String test4 = editText4.getText().toString();
				String test5 = editText5.getText().toString();

				if (test1.length() != 0 && test2.length() != 0
						&& test3.length() != 0 && test4.length() != 0
						&& test5.length() != 0) {
					String out = "计算结果:\n";
					double b1 = Double.parseDouble(test2)
							+ Double.parseDouble(test3)
							- Double.parseDouble(test1);
					double b2 = Double.parseDouble(test5) / b1 - 1;
					out = out
							+ "初始投资: "
							+ String.format("%.4f", b1)
							+ "\n期末收益: "
							+ String.format("%.4f", Double.parseDouble(test5))
							+ "\n无风险利率: "
							+ String.format("%.3f", b2 * 100)
							+ "%"
							+ "\n年化收益率: "
							+ String.format("%.3f",
									b2 * 36500 / Double.parseDouble(test4))
							+ "%";
					out_tv.setText(out);
				} else {
					Toast.makeText(Button5Activity.this, "输入数字不能为空！",
							Toast.LENGTH_SHORT).show();
				}

				break;
			case R.id.Button5_back_bn:
				goBack();
				break;
			case R.id.Button5_clean_bn:
				cleanEditText();
				break;
			}

		}
	}

	/**
	 * 清空函数
	 */
	private void cleanEditText() {
		editText1.setText("");
		editText2.setText("");
		editText3.setText("");
		editText4.setText("");
		editText5.setText("");
		out_tv.setText("");
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