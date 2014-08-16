package com.linyongan.activity;

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
 * ��������Ʒ����ҳ��
 */
public class DerivativeActivity extends Activity {
	/** ��Ȩ���ۼ۸� */
	private Button Button1;
	/** ��Ȩ�������� */
	private Button Button2;
	/** ��Ȩ���������� */
	private Button Button3;
	/** ��֤�� */
	private Button Button4;
	/** ��ȨDeltaֵ */
	private Button Button5;
	/** ���ڼ�ֵ&ʱ���ֵ */
	private Button Button6;
	/** Delta&�ܸ˱��� */
	private Button Button7;
	/** ������� */
	private Button Button8;
	/** ������� */
	private Button Button9;
	/** ���ذ�ť */
	private ImageButton backButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.derivative);
		// ��ȡ���ݴ�����ݿ�
		Button1 = (Button) findViewById(R.id.derivative_Button1);
		Button2 = (Button) findViewById(R.id.derivative_Button2);
		Button3 = (Button) findViewById(R.id.derivative_Button3);
		Button4 = (Button) findViewById(R.id.derivative_Button4);
		Button5 = (Button) findViewById(R.id.derivative_Button5);
		Button6 = (Button) findViewById(R.id.derivative_Button6);
		Button7 = (Button) findViewById(R.id.derivative_Button7);
		Button8 = (Button) findViewById(R.id.derivative_Button8);
		Button9 = (Button) findViewById(R.id.derivative_Button9);
		backButton = (ImageButton) findViewById(R.id.derivative_back_bn);
		Button1.setOnClickListener(new ButtonListener());
		Button2.setOnClickListener(new ButtonListener());
		Button3.setOnClickListener(new ButtonListener());
		Button4.setOnClickListener(new ButtonListener());
		Button5.setOnClickListener(new ButtonListener());
		Button6.setOnClickListener(new ButtonListener());
		Button7.setOnClickListener(new ButtonListener());
		Button8.setOnClickListener(new ButtonListener());
		Button9.setOnClickListener(new ButtonListener());
		backButton.setOnClickListener(new ButtonListener());
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
			case R.id.derivative_back_bn:
				goBack();
				break;
			}

		}

	}

	/** �˳���ť */
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

	/** ������һ������ */
	private void goBack() {
		finish();
	}
}