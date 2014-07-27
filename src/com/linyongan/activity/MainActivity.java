package com.linyongan.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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

	/** ��ǣ���һ�����м������� */
	private static boolean mark = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		if (mark) {
			LoadTask load = new LoadTask();
			load.execute();
			mark = false;
		}
		// �ҵ����е�button
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

	/**
	 * button�ļ����¼�
	 * 
	 * @author yongan
	 * 
	 */
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

	/**
	 * ��������
	 */
	class LoadTask extends AsyncTask<Object, Object, Object> {

		// �÷�������������UI�̵߳��У������ڸ÷������У����ܶ�UI���еĿؼ��������ú��޸�
		// ��Ҫ���ڽ����첽������
		@Override
		protected String doInBackground(Object... params) {
			// com.test.db �ǳ���İ�����������Լ��ĳ������
			// /data/data/com.test.db/
			// databases Ŀ¼��׼���� SQLite ���ݿ�ĵط���Ҳ�� Android ����Ĭ�ϵ����ݿ�洢Ŀ¼
			// ���ݿ���Ϊ test.db
			String DB_PATH = "/data/data/com.linyongan.activity/databases/";
			String DB_NAME = "xy.db";

			// ��� SQLite ���ݿ��ļ��Ƿ����
			if ((new File(DB_PATH + DB_NAME)).exists() == false) {
				// �� SQLite ���ݿ��ļ������ڣ��ټ��һ�� database Ŀ¼�Ƿ����
				File f = new File(DB_PATH);
				// �� database Ŀ¼�����ڣ��½���Ŀ¼
				if (!f.exists()) {
					f.mkdir();
				}
				try {
					// �õ� assets Ŀ¼������ʵ��׼���õ� SQLite ���ݿ���Ϊ������
					InputStream is = getBaseContext().getAssets().open(DB_NAME);
					// �����
					OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);
					// �ļ�д��
					byte[] buffer = new byte[1024];
					int length;
					while ((length = is.read(buffer)) > 0) {
						os.write(buffer, 0, length);
					}
					// �ر��ļ���
					os.flush();
					os.close();
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
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