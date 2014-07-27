package org.crazyit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 金融计算器主页面
 */
public class MainActivity extends Activity {
	/** 个人理财按钮 */
	private Button financeButton;
	/** 贷款计算按钮 */
	private Button loanButton;
	/** 投资指标按钮 */
	private Button investmentButton;
	/** 金融产品定价按钮 */
	private Button identificationButton;
	/** 金融衍生品定价按钮 */
	private Button derivativeButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		financeButton = (Button) findViewById(R.id.financeButton);
		loanButton = (Button) findViewById(R.id.loanButton);
		investmentButton = (Button) findViewById(R.id.investmentButton);
		identificationButton = (Button) findViewById(R.id.identificationButton);
		derivativeButton = (Button) findViewById(R.id.derivativeButton);
		financeButton.setOnClickListener(new ButtonListener());
		loanButton.setOnClickListener(new ButtonListener());
		investmentButton.setOnClickListener(new ButtonListener());
		identificationButton.setOnClickListener(new ButtonListener());
		derivativeButton.setOnClickListener(new ButtonListener());
	}

	private class ButtonListener implements OnClickListener {

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.financeButton:
				Intent intent1 = new Intent(MainActivity.this,
						FinanceActivity.class);
				startActivity(intent1);
				break;
			case R.id.loanButton:
				Intent intent2 = new Intent(MainActivity.this,
						LoanActivity.class);
				startActivity(intent2);
				break;
			case R.id.investmentButton:
				Intent intent3 = new Intent(MainActivity.this,
						InvestmentActivity.class);
				startActivity(intent3);
				break;
			case R.id.identificationButton:
				Intent intent4 = new Intent(MainActivity.this,
						IdentificationActivity.class);
				startActivity(intent4);
				break;
			case R.id.derivativeButton:
				Intent intent5 = new Intent(MainActivity.this,
						DerivativeActivity.class);
				startActivity(intent5);
				break;
			}

		}

	}

	/** 退出按钮 */
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			if (event.getAction() == KeyEvent.ACTION_DOWN
					&& event.getRepeatCount() == 0) {
				finish();
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}

}