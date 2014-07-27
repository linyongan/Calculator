package com.linyongan.activity;

import java.io.IOException;
import java.io.InputStream;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.linyongan.model.Student;
import com.linyongan.sql.StudentDbManger;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
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

	private StudentDbManger dbmanger;
	/** ��ǣ���һ�����м������� */
	private static boolean mark = true;
	private AssetManager am;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		// ��һ�����м�������
		dbmanger = new StudentDbManger(this);
		am = this.getAssets();
		if (mark) {
			LoadTask load = new LoadTask(MainActivity.this);
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
			pdialog.setMessage("��һ�����г��򣬶�ȡ�����У�����ȴ�...");
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
			InputStream is = null;
			try {
				is = am.open("data.xls");
				Workbook wb = Workbook.getWorkbook(is);
				// ��ȡ������
				Sheet sheet = wb.getSheet(0);
				int row = sheet.getRows();
				dbmanger.open();
				for (int i = 0; i < row; ++i) {
					// ��ȡ��һ��
					Cell cellarea = sheet.getCell(0, i);
					// ��ȡ�ڶ���
					Cell cellschool = sheet.getCell(1, i);
					String x = cellarea.getContents();
					String y = cellschool.getContents();
					System.out.println(x + "--���--:" + y);
					Student student = new Student();
					student.setId(x);
					student.setY(y);
					dbmanger.add(student);
				}
				dbmanger.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			if (event.getAction() == KeyEvent.ACTION_DOWN
					&& event.getRepeatCount() == 0) {
				finish();
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}

}