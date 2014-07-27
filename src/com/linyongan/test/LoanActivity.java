package com.linyongan.test;


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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 贷款计算页面
 */
public class LoanActivity extends Activity {
	/** 返回按钮 */
	private ImageButton backButton;
	/** 清空按钮 */
	private ImageButton cleanButton;
	/** 计算按钮 */
	private Button calculateButton;
	/** 贷款本金(输入框) */
	private EditText principal_et;
	/** 还款月数(输入框) */
	private EditText month_et;
	/** 月利率(输入框) */
	private EditText monthRate_et;
	/** 结果(文字) */
	private TextView out_tv;
	/** 还款方式 */
	private RadioGroup radioGroup;
	/** 还款月数 */
	private int month;
	/** 贷款本金 */
	private Double principal;
	/** 月利率 */
	private Double monthRate;
	/**
	 * 标记1：等额本息 2：等额本金
	 */
	private int mark = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.loan);
		// 找到所有的Button
		calculateButton = (Button) findViewById(R.id.loan_calculate_bt);
		calculateButton.setOnClickListener(new ButtonListener());
		backButton = (ImageButton) findViewById(R.id.loan_back_bn);
		backButton.setOnClickListener(new ButtonListener());
		cleanButton = (ImageButton) findViewById(R.id.loan_clean_bn);
		cleanButton.setOnClickListener(new ButtonListener());
		// 找到所有的EditText
		principal_et = (EditText) findViewById(R.id.loan_principal_et);
		month_et = (EditText) findViewById(R.id.loan_month_et);
		monthRate_et = (EditText) findViewById(R.id.loan_yearRate_et);
		// 找到所有的TextView
		out_tv = (TextView) findViewById(R.id.loan_out_tv);
		// 找到所有的RadioGroup
		radioGroup = (RadioGroup) findViewById(R.id.loan_RadioGroup);
		radioGroup.setOnCheckedChangeListener(new radioGroupListener());
	}

	private class ButtonListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.loan_calculate_bt:
				// 获取输入框的数字，必须先取值！！！
				String Sprincipal = principal_et.getText().toString();
				String Smonth = month_et.getText().toString();
				String SmonthRate = monthRate_et.getText().toString();

				if (mark == 1 && Sprincipal.length() != 0
						&& Smonth.length() != 0 && SmonthRate.length() != 0) {
					month = Integer.parseInt(Smonth);
					principal = Double.parseDouble(Sprincipal);
					monthRate = Double.parseDouble(SmonthRate);
					monthRate = monthRate / 12;
					Double a = calculateRepayment(principal, month, monthRate);
					Double b = calculateInterest(a, month, principal);
					out_tv.setText("计算结果：\n每月还款额：" + String.format("%.2f", a)+"元"
							+ "\n累计利息：" + String.format("%.2f", b)+"元");
				} else if (mark == 2 && Sprincipal.length() != 0
						&& Smonth.length() != 0 && SmonthRate.length() != 0) {
					month = Integer.parseInt(Smonth);
					principal = Double.parseDouble(Sprincipal);
					monthRate = Double.parseDouble(SmonthRate);
					monthRate = monthRate / 12;
					Double[] a1 = calculateRepayment2(principal, month,
							monthRate);
					Double b = calculateInterest2(principal, a1, month);
					String c = "";
					for (int i = 0; i < month; i++) {
						c = c + "第" + (i + 1) + "个月还款额："
								+ String.format("%.2f", a1[i]) + "元\n";
					}
					out_tv.setText("计算结果：\n" + "累计利息："
							+ String.format("%.2f", b) + "元\n" + c);
				} else {
					Toast.makeText(LoanActivity.this, "输入数字不能为空！",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.loan_back_bn:
				goBack();
				break;
			case R.id.loan_clean_bn:
				cleanEditText();
				break;

			}
		}

	}

	/** 选择计算类型的RadioGroup的监听事件 */
	private class radioGroupListener implements
			RadioGroup.OnCheckedChangeListener {

		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.loan_RadioButton1:
				out_tv.setText("");
				mark = 1;
				break;
			case R.id.loan_RadioButton2:
				out_tv.setText("");
				mark = 2;
				break;
			}

		}

	}

	/**
	 * 等额本息,计算每月还款额
	 * 
	 * @param principal
	 * @param month
	 * @param monthRate
	 * @return
	 */
	private Double calculateRepayment(Double principal, int month,
			Double monthRate) {
		Double result, a;
		a = Math.pow(1 + monthRate, month);
		result = principal * a * monthRate / (a - 1);
		return result;
	}

	/**
	 * 等额本息,计算累计利息
	 * 
	 * @param repayment
	 * @param month
	 * @param principal
	 * @return
	 */
	private Double calculateInterest(Double repayment, int month,
			Double principal) {
		Double result;
		result = repayment * month - principal;
		return result;
	}

	/**
	 * 等额本金,计算每月还款额
	 * 
	 * @param principal
	 * @param month
	 * @param monthRate
	 * @return
	 */
	private Double[] calculateRepayment2(Double principal, int month,
			Double monthRate) {
		Double[] result = new Double[month];
		for (int i = 0; i < month; i++) {
			result[i] = principal / month + monthRate
					* (principal - principal * i / month);
		}
		return result;
	}

	/**
	 * 等额本金,计算累计利息
	 * 
	 * @param repayment
	 * @param a
	 * @param month
	 * @return
	 */
	private Double calculateInterest2(Double principal, Double[] a, int month) {
		Double b = 0.0;
		for (int i = 0; i < month; i++) {
			b = b + a[i];
		}
		return b - principal;
	}

	/**
	 * 清空函数
	 */
	private void cleanEditText() {
		principal_et.setText("");
		month_et.setText("");
		monthRate_et.setText("");
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
		Intent intent = new Intent(LoanActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}