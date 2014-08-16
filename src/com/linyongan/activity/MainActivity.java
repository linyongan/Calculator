package com.linyongan.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

/**
 * ���ڼ�������ҳ��
 */
public class MainActivity extends Activity {

	/** �������ⰴť */
	private Button questionButton;
	/** ���ʲ�ѯ��ť */
	private Button searchButton;
	/** ��Ȩѧ�ð�ť */
	private Button learnButton;
	/** ��Ȩ���㰴ť */
	private Button derivativeButton;
	/** ���Ҳ��԰�ť */
	private Button testButton;
	/** �������ǰ�ť */
	private Button aboutButton;

	/** ��ǣ���һ�����м������� */
	private static boolean mark = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		if (mark) {
			LoadTask load = new LoadTask(MainActivity.this);
			load.execute();
			mark = false;
		}
		// �ҵ����е�button
		questionButton = (Button) findViewById(R.id.questionButton);
		searchButton = (Button) findViewById(R.id.searchButton);
		learnButton = (Button) findViewById(R.id.learnButton);
		derivativeButton = (Button) findViewById(R.id.derivativeButton);
		testButton = (Button) findViewById(R.id.testButton);
		aboutButton = (Button) findViewById(R.id.aboutButton);

		questionButton.setOnClickListener(new ButtonListener());
		searchButton.setOnClickListener(new ButtonListener());
		learnButton.setOnClickListener(new ButtonListener());
		derivativeButton.setOnClickListener(new ButtonListener());
		testButton.setOnClickListener(new ButtonListener());
		aboutButton.setOnClickListener(new ButtonListener());
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
			case R.id.questionButton:
				Intent intent2 = new Intent(MainActivity.this,
						QuestionActivity.class);
				startActivity(intent2);
				break;
			case R.id.searchButton:
				Intent intent3 = new Intent(MainActivity.this,
						SearchActivity.class);
				startActivity(intent3);
				break;
			case R.id.learnButton:
				Intent intent4 = new Intent(MainActivity.this,
						LearnActivity.class);
				startActivity(intent4);
				break;
			case R.id.derivativeButton:
				Intent intent5 = new Intent(MainActivity.this,
						DerivativeActivity.class);
				startActivity(intent5);
				break;
			case R.id.testButton:
				Intent intent1 = new Intent(MainActivity.this,
						TestActivity.class);
				startActivity(intent1);
				break;
			case R.id.aboutButton:
				Intent intent6 = new Intent(MainActivity.this,
						AboutActivity.class);
				startActivity(intent6);
				break;
			}

		}

	}

	/**
	 * ��������
	 */
	class LoadTask extends AsyncTask<Object, Object, Object> {
		// �ɱ䳤�������������AsyncTask.exucute()��Ӧ
		ProgressDialog pdialog;
		Context mContext;

		// ����Ĳ��������Զ��壬��Ҫʲô�ؼ��ʹ���ʲô�ؼ�
		public LoadTask(Context ctx) {
			mContext = ctx;
		}

		// �÷���������UI�̵߳���,��Ҫ���ڽ����첽����֮ǰ��UI׼������
		@Override
		protected void onPreExecute() {
			pdialog = new ProgressDialog(mContext);
			// ���öԻ���ı���
			pdialog.setTitle("���ڶ�ȡ����");
			// ���öԻ��� ��ʾ������
			pdialog.setMessage("��һ�����г��򣬶�ȡ�����У�����ȴ�ʮ����...");
			// ���öԻ������á�ȡ������ť�ر�
			pdialog.setCancelable(false);
			// ���öԻ���Ľ������Ƿ���ʾ����
			pdialog.setIndeterminate(false);
			pdialog.show();
		}

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

		// ��doInBackground����ִ�н���֮�������У�����������UI�̵߳��С�
		// ��Ҫ���ڽ��첽����ִ�еĽ��չʾ���ͻ�
		@Override
		protected void onPostExecute(Object result) {
			pdialog.dismiss();
		}

	}

	/** �˳���ť */
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);  
			mHomeIntent.addCategory(Intent.CATEGORY_HOME);  
			mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK  
			| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);  
			startActivity(mHomeIntent); 
			finish();
		}
		return super.dispatchKeyEvent(event);
	}

}