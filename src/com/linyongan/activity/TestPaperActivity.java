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
 * ѡ���Ծ�ҳ��
 */
public class TestPaperActivity extends Activity {
	/** ���ذ�ť */
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
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test_paper);
		// �ҵ����е�button
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

		setButtonVisible();

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

	private void setButtonVisible() {
		Button[] buttons = new Button[] { button1, button2, button3, button4,
				button5, button6, button7, button8, button9, button10,
				button11, button12 };
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String string = bundle.getString("mark");
		if (string.length() != 0) {
			mark = Integer.valueOf(string);
			for (int i = 0; i < mark; i++) {
				buttons[i].setVisibility(View.VISIBLE);
			}
		}
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
			case R.id.test_paper_back_bn:
				goBack();
				break;
			case R.id.test_paper_Button1:
				Intent intent1 = new Intent(TestPaperActivity.this,
						TestingActivity.class);
				startActivity(intent1);
				break;
			case R.id.test_paper_Button2:
				Intent intent2 = new Intent(TestPaperActivity.this,
						TestingActivity.class);
				startActivity(intent2);
				break;
			case R.id.test_paper_Button3:
				Intent intent3 = new Intent(TestPaperActivity.this,
						TestingActivity.class);
				startActivity(intent3);
				break;
			case R.id.test_paper_Button4:
				Intent intent4 = new Intent(TestPaperActivity.this,
						TestingActivity.class);
				startActivity(intent4);
				break;
			case R.id.test_paper_Button5:
				Intent intent5 = new Intent(TestPaperActivity.this,
						TestingActivity.class);
				startActivity(intent5);
				break;
			case R.id.test_paper_Button6:
				Intent intent6 = new Intent(TestPaperActivity.this,
						TestingActivity.class);
				startActivity(intent6);
				break;
			case R.id.test_paper_Button7:
				Intent intent7 = new Intent(TestPaperActivity.this,
						TestingActivity.class);
				startActivity(intent7);
				break;
			case R.id.test_paper_Button8:
				Intent intent8 = new Intent(TestPaperActivity.this,
						TestingActivity.class);
				startActivity(intent8);
				break;
			case R.id.test_paper_Button9:
				Intent intent9 = new Intent(TestPaperActivity.this,
						TestingActivity.class);
				startActivity(intent9);
				break;
			case R.id.test_paper_Button10:
				Intent intent10 = new Intent(TestPaperActivity.this,
						TestingActivity.class);
				startActivity(intent10);
				break;
			case R.id.test_paper_Button11:
				Intent intent11 = new Intent(TestPaperActivity.this,
						TestingActivity.class);
				startActivity(intent11);
				break;
			case R.id.test_paper_Button12:
				Intent intent12 = new Intent(TestPaperActivity.this,
						TestingActivity.class);
				startActivity(intent12);
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
		Intent intent = new Intent(TestPaperActivity.this, TestActivity.class);
		startActivity(intent);
		finish();
	}
}