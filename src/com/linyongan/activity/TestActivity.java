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
 * ���Ҳ���ҳ��
 */
public class TestActivity extends Activity {
	/** ���ذ�ť */
	private ImageButton backButton;
	/** �������԰�ť */
	private Button baseButton;
	/** ���ײ���1��ť */
	private Button advanceButton1;
	/** ���ײ���2��ť */
	private Button advanceButton2;
	/** �����ղذ�ť */
	private Button collectButton;
	/** ���Գɼ���ť */
	private Button gradeButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
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
	 * button�ļ����¼�
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
				// �ǵ÷����ַ�������������
				intent2.putExtra("testNum", testNum2 + "");
				startActivity(intent2);
				break;
			case R.id.test_advanceButton1:
				Intent intent3 = new Intent(TestActivity.this,
						TestingActivity.class);
				int testNum3 = 30;
				// �ǵ÷����ַ�������������
				intent3.putExtra("testNum", testNum3 + "");
				startActivity(intent3);
				break;
			case R.id.test_advanceButton2:
				Intent intent4 = new Intent(TestActivity.this,
						TestingActivity.class);
				int testNum4 = 90;
				// �ǵ÷����ַ�������������
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
		Intent intent = new Intent(TestActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}