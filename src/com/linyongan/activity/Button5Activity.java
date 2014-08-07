package com.linyongan.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.linyongan.cofig.Constants;
import com.linyongan.sql.CalculateDbManger;

/**
 * 期权Delta值页面
 */
public class Button5Activity extends Activity {
	/** 返回按钮 */
	private ImageButton backButton;
	/** 清空按钮 */
	private ImageButton cleanButton;
	/** 计算按钮 */
	private Button calculateButton;
	/**
	 * 标记1：数据正常 2：数据有误
	 */
	private int mark2 = 1;
	/** 标的资产的价格(输入框) */
	private EditText editText1;
	/** 行权价格(输入框) */
	private EditText editText2;
	/** 标的资产价格波动率(输入框) */
	private EditText editText3;
	/** 到期期限(输入框) */
	private EditText editText4;
	/** 年利率(输入框) */
	private EditText editText5;
	/** 结果(文字) */
	private TextView out_tv;
	private CalculateDbManger dbmanger;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.derivative5);
		dbmanger = new CalculateDbManger(this);
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
		backButton = (ImageButton) findViewById(R.id.Button5_back_bn);
		backButton.setOnClickListener(new ButtonListener());
		cleanButton = (ImageButton) findViewById(R.id.Button5_clean_bn);
		cleanButton.setOnClickListener(new ButtonListener());
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
					mark2 = 1;
					Double a = calculate(Double.parseDouble(test1),
							Double.parseDouble(test2),
							Double.parseDouble(test3),
							Double.parseDouble(test4),
							Double.parseDouble(test5));
					Double b = a - 1;
					if (mark2 == 1) {
						out_tv.setText("计算结果：\n认购期权Delta值为："
								+ String.format("%.2f", a-0.005) + "\n认沽期权Delta值为："
								+ String.format("%.2f", b-0.005));
					} else {
						out_tv.setText("计算结果：输入的数据有误，请重新输入！");
					}

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
	 * 计算期权Delta值
	 * 
	 * @param a
	 *            标的资产的价格
	 * @param b
	 *            行权价格
	 * @param c
	 *            标的资产价格波动率
	 * @param d
	 *            到期期限
	 * @param e
	 *            年利率
	 * @return
	 */
	private Double calculate(Double a, Double b, Double c, Double d, Double e) {
		Double d1 = (Math.log(a / b) + (e + Math.pow(c, 2) / 2) * d)
				/ (c * Math.sqrt(d));
		System.out.println("--d1:-- " + d1);
		Double result = change(d1);
		return result;
	}

	/**
	 * 将Double转成String类型 经过逻辑判断，从数据库查找到N(d1)的值，再返回给调用者
	 * 
	 * @param a
	 * @return
	 */
	private Double change(Double a) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#0.000");
		String string = df.format(a);
		// 如果小数点最后一位是0，则删除它
		if (string.endsWith("0")) {
			string = string.substring(0, string.length() - 1);
			if (string.endsWith("0")) {
			string = string.substring(0, string.length() - 1);
			if (string.endsWith("0")) {
				string = string.substring(0, string.length() - 2);
				if (string.endsWith("0")) {
					string = "0";
				}
			}
		}
		}
		System.out.println("--string:-- " + string);
		dbmanger.open();
		Cursor cursor = dbmanger.search(string);
		if (cursor.moveToFirst()) {
			String string1 = cursor.getString(cursor
					.getColumnIndex(Constants.CalculateTable.Y));
			System.out.println("--string1:-- " + string1);
			return Double.parseDouble(string1);
		} else {
			mark2 = 2;
			Toast.makeText(Button5Activity.this, "数据有误！！！", Toast.LENGTH_SHORT)
					.show();
		}
		dbmanger.close();
		return a;

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