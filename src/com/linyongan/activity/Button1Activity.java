package com.linyongan.activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.linyongan.cofig.Constants;
import com.linyongan.sql.CalculateDbManger;
import com.linyongan.view.TitleView2;

/**
 * 期权理论价格页面
 */
public class Button1Activity extends Activity {
	/** 标题 */
	private TitleView2 titleView;
	/** 计算按钮 */
	private Button calculateButton;
	private RadioGroup radioGroup;
	/**
	 * 标记1：认购期权理论价格 2：认沽期权理论价格
	 */
	private int mark = 1;
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
	/** 数据库操作类 */
	private CalculateDbManger dbmanger;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.derivative1);
		dbmanger = new CalculateDbManger(this);
		// 找到所有的TextView
		out_tv = (TextView) findViewById(R.id.Button1_out_tv);
		// 找到所有的EditText
		editText1 = (EditText) findViewById(R.id.Button1_editText1);
		editText2 = (EditText) findViewById(R.id.Button1_editText2);
		editText3 = (EditText) findViewById(R.id.Button1_editText3);
		editText4 = (EditText) findViewById(R.id.Button1_editText4);
		editText5 = (EditText) findViewById(R.id.Button1_editText5);
		// 找到所有的RadioGroup
		radioGroup = (RadioGroup) findViewById(R.id.Button1_radioGroup);
		radioGroup.setOnCheckedChangeListener(new radioGroupListener());
		// 找到所有的Button
		calculateButton = (Button) findViewById(R.id.Button1_calculate_bt);
		calculateButton.setOnClickListener(new ButtonListener());
		titleView = (TitleView2) findViewById(R.id.TitleView2);
		titleView.setTitleText("期权理论价格");
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

	/** RadioGroup的监听事件 */
	private class radioGroupListener implements
			RadioGroup.OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.Button1_radioButton1:
				mark = 1;
				break;
			case R.id.Button1_radioButton2:
				mark = 2;
				break;
			}
		}

	}

	private class ButtonListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.Button1_calculate_bt:
				// 获取输入框的数字，必须先取值！！！
				String test1 = editText1.getText().toString();
				String test2 = editText2.getText().toString();
				String test3 = editText3.getText().toString();
				String test4 = editText4.getText().toString();
				String test5 = editText5.getText().toString();

				if (mark == 1 && test1.length() != 0 && test2.length() != 0
						&& test3.length() != 0 && test4.length() != 0
						&& test5.length() != 0) {
					mark2 = 1;
					Double a = calculate1(Double.parseDouble(test1),
							Double.parseDouble(test2),
							Double.parseDouble(test3),
							Double.parseDouble(test4),
							Double.parseDouble(test5));
					if (mark2 == 1) {
						out_tv.setText("计算结果：\n认购期权理论价格为："
								+ String.format("%.2f", a - 0.005));
					} else {
						out_tv.setText("计算结果：输入的数据有误，请重新输入！");
					}

				} else if (mark == 2 && test1.length() != 0
						&& test2.length() != 0 && test3.length() != 0
						&& test4.length() != 0 && test5.length() != 0) {
					mark2 = 1;
					Double a = calculate2(Double.parseDouble(test1),
							Double.parseDouble(test2),
							Double.parseDouble(test3),
							Double.parseDouble(test4),
							Double.parseDouble(test5));
					if (mark2 == 1) {
						out_tv.setText("计算结果：\n认沽期权理论价格为："
								+ String.format("%.2f", a - 0.005));
					} else {
						out_tv.setText("计算结果：输入的数据有误，请重新输入！");
					}
				} else {
					Toast.makeText(Button1Activity.this, "输入数字不能为空！",
							Toast.LENGTH_SHORT).show();
				}
				break;

			}

		}
	}

	/**
	 * 计算认购期权理论价格
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
	private Double calculate1(Double a, Double b, Double c, Double d, Double e) {
		Double d1 = (Math.log(a / b) + (e + Math.pow(c, 2) * 0.5) * d)
				/ (c * Math.sqrt(d));
		System.out.println("--d1:-- " + d1);
		Double d2 = d1 - c * Math.sqrt(d);
		System.out.println("--d2:-- " + d2);
		Double result = a * change(d1) - b * Math.pow(2.71, (-e * d))
				* change(d2);
		return result;
	}

	/**
	 * 
	 * 计算认沽期权理论价格
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
	private Double calculate2(Double a, Double b, Double c, Double d, Double e) {
		Double d1 = (Math.log(a / b) + (e + Math.pow(c, 2) * 0.5) * d)
				/ (c * Math.sqrt(d));
		System.out.println("--d1:-- " + d1);
		Double d2 = d1 - c * Math.sqrt(d);
		System.out.println("--d2:-- " + d2);
		Double result = b * Math.pow(2.718, -e * d) * change(-d2) - a
				* change(-d1);
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
			Toast.makeText(Button1Activity.this, "数据有误！！！", Toast.LENGTH_SHORT)
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