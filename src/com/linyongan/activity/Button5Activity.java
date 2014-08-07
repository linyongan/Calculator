package com.linyongan.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.linyongan.cofig.Constants;
import com.linyongan.sql.CalculateDbManger;

/**
 * ��ȨDeltaֵҳ��
 */
public class Button5Activity extends Activity {
	/** ���ذ�ť */
	private ImageButton backButton;
	/** ��հ�ť */
	private ImageButton cleanButton;
	/** ���㰴ť */
	private Button calculateButton;
	/**
	 * ���1���������� 2����������
	 */
	private int mark2 = 1;
	/** ����ʲ��ļ۸�(�����) */
	private EditText editText1;
	/** ��Ȩ�۸�(�����) */
	private EditText editText2;
	/** ����ʲ��۸񲨶���(�����) */
	private EditText editText3;
	/** ��������(�����) */
	private EditText editText4;
	/** ������(�����) */
	private EditText editText5;
	/** ���(����) */
	private TextView out_tv;
	private CalculateDbManger dbmanger;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.derivative5);
		dbmanger = new CalculateDbManger(this);
		// �ҵ����е�TextView
		out_tv = (TextView) findViewById(R.id.Button5_out_tv);
		// �ҵ����е�EditText
		editText1 = (EditText) findViewById(R.id.Button5_editText1);
		editText2 = (EditText) findViewById(R.id.Button5_editText2);
		editText3 = (EditText) findViewById(R.id.Button5_editText3);
		editText4 = (EditText) findViewById(R.id.Button5_editText4);
		editText5 = (EditText) findViewById(R.id.Button5_editText5);
		// �ҵ����е�Button
		calculateButton = (Button) findViewById(R.id.Button5_calculate_bt);
		calculateButton.setOnClickListener(new ButtonListener());
		backButton = (ImageButton) findViewById(R.id.Button5_back_bn);
		backButton.setOnClickListener(new ButtonListener());
		cleanButton = (ImageButton) findViewById(R.id.Button5_clean_bn);
		cleanButton.setOnClickListener(new ButtonListener());
	}

	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.Button5_calculate_bt:
				// ��ȡ���������֣�������ȡֵ������
				String test1 = editText1.getText().toString();
				String test2 = editText2.getText().toString();
				String test3 = editText3.getText().toString();
				String test4 = editText4.getText().toString();
				String test5 = editText5.getText().toString();

				if (test1.length() != 0 && test2.length() != 0
						&& test3.length() != 0 && test4.length() != 0
						&& test5.length() != 0) {
					mark2 = 1;
					Double a = calculate(Double.parseDouble(test1),
							Double.parseDouble(test2),
							Double.parseDouble(test3),
							Double.parseDouble(test4),
							Double.parseDouble(test5));
					Double b = a - 1;
					if (mark2 == 1) {
						out_tv.setText("��������\n�Ϲ���ȨDeltaֵΪ��"
								+ String.format("%.2f", a-0.005) + "\n�Ϲ���ȨDeltaֵΪ��"
								+ String.format("%.2f", b-0.005));
					} else {
						out_tv.setText("������������������������������룡");
					}

				} else {
					Toast.makeText(Button5Activity.this, "�������ֲ���Ϊ�գ�",
							Toast.LENGTH_SHORT).show();
				}

				break;
			case R.id.Button5_back_bn:
				goBack();
				break;
			case R.id.Button5_clean_bn:
				cleanEditText();
				break;
			}

		}
	}

	/**
	 * ������ȨDeltaֵ
	 * 
	 * @param a
	 *            ����ʲ��ļ۸�
	 * @param b
	 *            ��Ȩ�۸�
	 * @param c
	 *            ����ʲ��۸񲨶���
	 * @param d
	 *            ��������
	 * @param e
	 *            ������
	 * @return
	 */
	private Double calculate(Double a, Double b, Double c, Double d, Double e) {
		Double d1 = (Math.log(a / b) + (e + Math.pow(c, 2) / 2) * d)
				/ (c * Math.sqrt(d));
		System.out.println("--d1:-- " + d1);
		Double result = change(d1);
		return result;
	}

	/**
	 * ��Doubleת��String���� �����߼��жϣ������ݿ���ҵ�N(d1)��ֵ���ٷ��ظ�������
	 * 
	 * @param a
	 * @return
	 */
	private Double change(Double a) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#0.000");
		String string = df.format(a);
		// ���С�������һλ��0����ɾ����
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
			Toast.makeText(Button5Activity.this, "�������󣡣���", Toast.LENGTH_SHORT)
					.show();
		}
		dbmanger.close();
		return a;

	}

	/**
	 * ��պ���
	 */
	private void cleanEditText() {
		editText1.setText("");
		editText2.setText("");
		editText3.setText("");
		editText4.setText("");
		editText5.setText("");
		out_tv.setText("");
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