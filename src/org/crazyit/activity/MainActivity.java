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
 * ���ڼ�������ҳ��
 */
public class MainActivity extends Activity {
	/** ������ư�ť */
	private Button financeButton;
	/** ������㰴ť */
	private Button loanButton;
	/** Ͷ��ָ�갴ť */
	private Button investmentButton;
	/** ���ڲ�Ʒ���۰�ť */
	private Button identificationButton;
	/** ��������Ʒ���۰�ť */
	private Button derivativeButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
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

	/** �˳���ť */
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