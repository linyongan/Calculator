package com.linyongan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * ��֤��ҳ��
 */
public class Button4Activity extends Activity {
	/** ���ذ�ť */
	private ImageButton backButton;
	/** ��հ�ť */
	private ImageButton cleanButton;
	/** ���㰴ť */
	private Button calculateButton;
	private RadioGroup radioGroup1;
	private RadioGroup radioGroup2;
	/**
	 * ���1����Ʊ��Ȩ����ֺ�Լά�ֱ�֤�� 2��ETF��Ȩ�����Լά�ֱ�֤��
	 */
	private int mark = 1;
	/**
	 * ���1���Ϲ���Ȩ����ֲֳ�ά�ֱ�֤�� 2���Ϲ���Ȩ����ֲֳ�ά�ֱ�֤��
	 */
	private int mark2 = 1;
	/** �����(�����) */
	private EditText editText1;
	/** ��Լ������̼�(�����) */
	private EditText editText2;
	/** ��Ȩ��(�����) */
	private EditText editText3;
	/** ��Լ��λ(�����) */
	private EditText editText4;
	/** ���(����) */
	private TextView out_tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.derivative4);
		// �ҵ����е�TextView
		out_tv = (TextView) findViewById(R.id.Button4_out_tv);
		// �ҵ����е�EditText
		editText1 = (EditText) findViewById(R.id.Button4_editText1);
		editText2 = (EditText) findViewById(R.id.Button4_editText2);
		editText3 = (EditText) findViewById(R.id.Button4_editText3);
		editText4 = (EditText) findViewById(R.id.Button4_editText4);
		// �ҵ����е�RadioGroup
		radioGroup1 = (RadioGroup) findViewById(R.id.Button4_radioGroup1);
		radioGroup1.setOnCheckedChangeListener(new radioGroupListener());
		radioGroup2 = (RadioGroup) findViewById(R.id.Button4_radioGroup2);
		radioGroup2.setOnCheckedChangeListener(new radioGroupListener());
		// �ҵ����е�Button
		calculateButton = (Button) findViewById(R.id.Button4_calculate_bt);
		calculateButton.setOnClickListener(new ButtonListener());
		backButton = (ImageButton) findViewById(R.id.Button4_back_bn);
		backButton.setOnClickListener(new ButtonListener());
		cleanButton = (ImageButton) findViewById(R.id.Button4_clean_bn);
		cleanButton.setOnClickListener(new ButtonListener());
	}

	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.Button4_calculate_bt:
				// ��ȡ���������֣�������ȡֵ������
				String test1 = editText1.getText().toString();
				String test2 = editText2.getText().toString();
				String test3 = editText3.getText().toString();
				String test4 = editText4.getText().toString();

				if (mark == 1 && mark2 == 1 && test1.length() != 0
						&& test2.length() != 0 && test3.length() != 0
						&& test4.length() != 0) {
					Double a = calculate1(Double.parseDouble(test1),
							Double.parseDouble(test2),
							Double.parseDouble(test3),
							Double.parseDouble(test4));
					System.out.println("--a:-- " + a);
					out_tv.setText("��������\n��֤��Ϊ��" + String.format("%.2f", a));
				} else if (mark == 1 && mark2 == 2 && test1.length() != 0
						&& test2.length() != 0 && test3.length() != 0
						&& test4.length() != 0) {
					Double a = calculate2(Double.parseDouble(test1),
							Double.parseDouble(test2),
							Double.parseDouble(test3),
							Double.parseDouble(test4));
					System.out.println("--a:-- " + a);
					out_tv.setText("��������\n��֤��Ϊ��" + String.format("%.2f", a));
				} else if (mark == 2 && mark2 == 1 && test1.length() != 0
						&& test2.length() != 0 && test3.length() != 0
						&& test4.length() != 0) {
					Double a = calculate3(Double.parseDouble(test1),
							Double.parseDouble(test2),
							Double.parseDouble(test3),
							Double.parseDouble(test4));
					System.out.println("--a:-- " + a);
					out_tv.setText("��������\n��֤��Ϊ��" + String.format("%.2f", a));
				} else if (mark == 2 && mark2 == 2 && test1.length() != 0
						&& test2.length() != 0 && test3.length() != 0
						&& test4.length() != 0) {
					Double a = calculate4(Double.parseDouble(test1),
							Double.parseDouble(test2),
							Double.parseDouble(test3),
							Double.parseDouble(test4));
					System.out.println("--a:-- " + a);
					out_tv.setText("��������\n��֤��Ϊ��" + String.format("%.2f", a));
				}

				break;
			case R.id.Button4_back_bn:
				goBack();
				break;
			case R.id.Button4_clean_bn:
				cleanEditText();
				break;
			}

		}
	}

	/** RadioGroup�ļ����¼� */
	private class radioGroupListener implements
			RadioGroup.OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.Button4_radioButton1:
				mark = 1;
				break;
			case R.id.Button4_radioButton2:
				mark = 2;
				break;
			case R.id.Button4_radioButton3:
				mark2 = 1;
				break;
			case R.id.Button4_radioButton4:
				mark2 = 2;
				break;
			}
		}
	}

	/**
	 * ��Ʊ �Ϲ���Ȩ����ֲֳ�ά�ֱ�֤��
	 * 
	 * @param a
	 *            �����
	 * @param b
	 *            ��Լ������̼�
	 * @param c
	 *            ��Ȩ��
	 * @param d
	 *            ��Լ��λ
	 * @return
	 */
	private Double calculate1(Double a, Double b, Double c, Double d) {
		Double result = (a + Math.max(0.21 * b - Math.max((c - b), 0), 0.1 * c))
				* d;
		return result;
	}

	/**
	 * ��Ʊ �Ϲ���Ȩ����ֲֳ�ά�ֱ�֤��
	 * 
	 * @param a
	 *            �����
	 * @param b
	 *            ��Լ������̼�
	 * @param c
	 *            ��Ȩ��
	 * @param d
	 *            ��Լ��λ
	 * @return
	 */
	private Double calculate2(Double a, Double b, Double c, Double d) {
		Double result = Math.min(
				(a + Math.max((0.19 * b - Math.max((b - c), 0)), 0.1 * c)), c)
				* d;
		return result;
	}

	/**
	 * ETF �Ϲ���Ȩ����ֲֳ�ά�ֱ�֤��
	 * 
	 * @param a
	 *            �����
	 * @param b
	 *            ��Լ������̼�
	 * @param c
	 *            ��Ȩ��
	 * @param d
	 *            ��Լ��λ
	 * @return
	 */
	private Double calculate3(Double a, Double b, Double c, Double d) {
		Double result = (a + Math.max((0.15 * b - Math.max((c - b), 0)),
				0.07 * c)) * d;
		return result;
	}

	/**
	 * ETF �Ϲ���Ȩ����ֲֳ�ά�ֱ�֤��
	 * 
	 * @param a
	 *            �����
	 * @param b
	 *            ��Լ������̼�
	 * @param c
	 *            ��Ȩ��
	 * @param d
	 *            ��Լ��λ
	 * @return
	 */
	private Double calculate4(Double a, Double b, Double c, Double d) {
		Double result = Math.min(
				(a + Math.max((0.15 * b - Math.max((b - c), 0)), 0.07 * c)), c)
				* d;
		return result;
	}

	/**
	 * ��պ���
	 */
	private void cleanEditText() {
		editText1.setText("");
		editText2.setText("");
		editText3.setText("");
		editText4.setText("");
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
		Intent intent = new Intent(Button4Activity.this,
				DerivativeActivity.class);
		startActivity(intent);
		finish();
	}
}