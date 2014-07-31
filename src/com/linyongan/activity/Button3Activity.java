package com.linyongan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * ��Ȩ����������ҳ��
 */
public class Button3Activity extends Activity {
	/** ���ذ�ť */
	private ImageButton backButton;
	/** ��հ�ť */
	private ImageButton cleanButton;
	/** ���㰴ť */
	private Button calculateButton;
	/** �������Ϲ��Ϲ���Ʊ */
	private Spinner spinner1;
	private Spinner spinner3;
	private Spinner spinner5;
	private Spinner spinner7;
	/** ˫���Ƕ�ͷ��ͷ */
	private Spinner spinner2;
	private Spinner spinner4;
	private Spinner spinner6;
	private Spinner spinner8;
	/**
	 * 1~4�������ֱ�����Ȩ�ۣ�Ȩ��� ��Ʊ���ڼ۸񣬹�Ʊ�۸�
	 */
	private EditText editText1;
	private EditText editText2;
	private EditText editText3;
	private EditText editText4;
	private EditText editText5;
	private EditText editText6;
	private EditText editText7;
	private EditText editText8;
	private EditText editText9;
	private EditText editText10;
	private EditText editText11;
	private EditText editText12;
	/** ���(����) */
	private TextView out_tv;
	/** ������� 0:�� 1:�Ϲ� 2:�Ϲ� 3:��Ʊ */
	private int mark1 = 0;
	private int mark3 = 0;
	private int mark5 = 0;
	private int mark7 = 0;
	/** ˫����� 0����ͷ 1����ͷ */
	private int mark2 = 0;
	private int mark4 = 0;
	private int mark6 = 0;
	private int mark8 = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.derivative3);
		// �ҵ����е�Spinner
		spinner1 = (Spinner) findViewById(R.id.Button3_spinner1);
		spinner2 = (Spinner) findViewById(R.id.Button3_spinner2);
		spinner3 = (Spinner) findViewById(R.id.Button3_spinner3);
		spinner4 = (Spinner) findViewById(R.id.Button3_spinner4);
		spinner5 = (Spinner) findViewById(R.id.Button3_spinner5);
		spinner6 = (Spinner) findViewById(R.id.Button3_spinner6);
		spinner7 = (Spinner) findViewById(R.id.Button3_spinner7);
		spinner8 = (Spinner) findViewById(R.id.Button3_spinner8);
		spinner1.setOnItemSelectedListener(new SpinnerListener1());
		spinner2.setOnItemSelectedListener(new SpinnerListener2());
		spinner3.setOnItemSelectedListener(new SpinnerListener3());
		spinner4.setOnItemSelectedListener(new SpinnerListener4());
		spinner5.setOnItemSelectedListener(new SpinnerListener5());
		spinner6.setOnItemSelectedListener(new SpinnerListener6());
		spinner7.setOnItemSelectedListener(new SpinnerListener7());
		spinner8.setOnItemSelectedListener(new SpinnerListener8());
		// �ҵ����е�TextView
		out_tv = (TextView) findViewById(R.id.Button3_out_tv);
		// �ҵ����е�EditText
		editText1 = (EditText) findViewById(R.id.Button3_et1);
		editText2 = (EditText) findViewById(R.id.Button3_et2);
		editText3 = (EditText) findViewById(R.id.Button3_et3);
		editText4 = (EditText) findViewById(R.id.Button3_et4);
		editText5 = (EditText) findViewById(R.id.Button3_et5);
		editText6 = (EditText) findViewById(R.id.Button3_et6);
		editText7 = (EditText) findViewById(R.id.Button3_et7);
		editText8 = (EditText) findViewById(R.id.Button3_et8);
		editText9 = (EditText) findViewById(R.id.Button3_et9);
		editText10 = (EditText) findViewById(R.id.Button3_et10);
		editText11 = (EditText) findViewById(R.id.Button3_et11);
		editText12 = (EditText) findViewById(R.id.Button3_et12);
		// �ҵ����е�Button
		calculateButton = (Button) findViewById(R.id.Button3_calculate_bt);
		calculateButton.setOnClickListener(new ButtonListener());
		backButton = (ImageButton) findViewById(R.id.Button3_back_bn);
		backButton.setOnClickListener(new ButtonListener());
		cleanButton = (ImageButton) findViewById(R.id.Button3_clean_bn);
		cleanButton.setOnClickListener(new ButtonListener());
	}

	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.Button3_calculate_bt:
				// ��ȡ���������֣�������ȡֵ������
				String test1 = editText1.getText().toString();
				String test2 = editText2.getText().toString();
				String test3 = editText3.getText().toString();
				String test4 = editText4.getText().toString();
				String test5 = editText5.getText().toString();
				String test6 = editText6.getText().toString();
				String test7 = editText7.getText().toString();
				String test8 = editText8.getText().toString();
				String test9 = editText9.getText().toString();
				String test10 = editText10.getText().toString();
				String test11 = editText11.getText().toString();
				String test12 = editText12.getText().toString();

				Double[] result1 = getResult(test1, test2, test3, mark1, mark2);
				Double[] result2 = getResult(test4, test5, test6, mark3, mark4);
				Double[] result3 = getResult(test7, test8, test9, mark5, mark6);
				Double[] result4 = getResult(test10, test11, test12, mark7,
						mark8);
				// ������ʾ�Ľ��
				String out = "";
				out = "��Ϲ����ɱ�Ϊ��"
						+ String.format(
								"%.2f",
								(someArryAll(result1, result2, result3,
										result4, 0) - someArryAll(result1,
										result2, result3, result4, 1))) + "Ԫ\n";
				out = out
						+ "��������ʧΪ��"
						+ String.format(
								"%.2f",
								arrayMax(All(result1, result2, result3, result4)))
						+ "Ԫ\n";
				out = out
						+ "����������Ϊ��"
						+ String.format(
								"%.2f",
								arrayMin(All(result1, result2, result3, result4)))
						+ "Ԫ\n";
				out = out
						+ "���ӯ��ƽ���Ϊ��"
						+ arraychange(All(result1, result2,
										result3, result4));
				out_tv.setText("��������\n" + out);
				// ��ʾͼƬ��Ҫ׼��������

				break;
			case R.id.Button3_back_bn:
				goBack();
				break;
			case R.id.Button3_clean_bn:
				cleanEditText();
				break;
			}
		}
	}

	/**
	 * ����ÿһ��
	 */
	private Double[] getResult(String a, String b, String c, int mark1,
			int mark2) {
		System.out.println("first�������յ��� aֵ��" + a);
		System.out.println("first�������յ��� bֵ��" + b);
		System.out.println("first�������յ��� cֵ��" + c);
		System.out.println("first�������յ��� mark1ֵ��" + mark1);
		System.out.println("first�������յ��� mark2ֵ��" + mark2);
		Double[] result = new Double[50];
		if (mark1 == 1 && mark2 == 0 && a.length() != 0 && b.length() != 0) {
			result = calculate1(Double.parseDouble(a), Double.parseDouble(b));
		} else if (mark1 == 1 && mark2 == 1 && a.length() != 0
				&& b.length() != 0) {
			result = calculate2(Double.parseDouble(a), Double.parseDouble(b));
		} else if (mark1 == 2 && mark2 == 0 && a.length() != 0
				&& b.length() != 0) {
			result = calculate3(Double.parseDouble(a), Double.parseDouble(b));
		} else if (mark1 == 2 && mark2 == 1 && a.length() != 0
				&& b.length() != 0) {
			result = calculate4(Double.parseDouble(a), Double.parseDouble(b));
		} else if (mark1 == 3 && mark2 == 0 && c.length() != 0
				&& b.length() != 0) {
			result = calculate5(Double.parseDouble(b), Double.parseDouble(c));
		} else if (mark1 == 3 && mark2 == 1 && c.length() != 0
				&& b.length() != 0) {
			result = calculate6(Double.parseDouble(b), Double.parseDouble(c));
		} else {
			return null;
		}
		return result;
	}

	/**
	 * ��ȡÿһ�е�����
	 */
	private String getName(int mark1, int mark2) {

		String result = "";
		if (mark1 == 1 && mark2 == 0) {
			result = "(��ͷ)��Ȩ��ΪX�Ϲ���Ȩ";
		} else if (mark1 == 1 && mark2 == 1) {
			result = "(��ͷ)��Ȩ��ΪX�Ϲ���Ȩ";
		} else if (mark1 == 2 && mark2 == 0) {
			result = "(��ͷ)��Ȩ��ΪX�Ϲ���Ȩ";
		} else if (mark1 == 2 && mark2 == 1) {
			result = "(��ͷ)��Ȩ��ΪX�Ϲ���Ȩ";
		} else if (mark1 == 3 && mark2 == 0) {
			result = "(��ͷ)��Ʊ";
		} else if (mark1 == 3 && mark2 == 1) {
			result = "(��ͷ)��Ʊ";
		} else {
			return null;
		}
		return result;
	}

	/**
	 * ����һ��Double[]����ĸ���֮��
	 * 
	 * @param a
	 * @return
	 */
	private Double arrayAll(Double[] a) {
		if (a != null) {
			Double result = 0.0;
			for (int i = 0; i < 50; i++) {
				result = result + a[i];
			}
			return result;
		} else {
			return 0.0;
		}
	}

	/**
	 * �������������
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return
	 */
	private Double[] All(Double[] a, Double[] b, Double[] c, Double[] d) {
		// �ǵó�ʼ�������򱨿�ָ��
		Double[] result = new Double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		if (a != null) {
			for (int i = 0; i < 50; i++) {
				result[i] = result[i] + a[i];
			}
		}
		if (b != null) {
			for (int i = 0; i < 50; i++) {
				result[i] = result[i] + b[i];
			}
		}
		if (c != null) {
			for (int i = 0; i < 50; i++) {
				result[i] = result[i] + c[i];
			}
		}
		if (d != null) {
			for (int i = 0; i < 50; i++) {
				result[i] = result[i] + d[i];
			}
		}
		return result;

	}

	/**
	 * ����һ��Double[]����������һ��
	 * 
	 * @param a
	 * @return
	 */
	private Double arrayMax(Double[] a) {
		Double result = a[0];
		for (int i = 1; i < 50; i++) {
			if (result < a[i])
				result = a[i];
		}
		return result;
	}

	/**
	 * ����һ��Double[]��������С��һ��
	 * 
	 * @param a
	 * @return
	 */
	private Double arrayMin(Double[] a) {
		Double result = a[0];
		for (int i = 1; i < 50; i++) {
			if (result > a[i])
				result = a[i];
		}
		return result;
	}

	/**
	 * ����һ��Double[]�����еĸ����������,Ȼ�����Y��СʱX��ֵ
	 * (δ��õģ���������)
	 * @param a
	 * @return
	 */
	private int arraychange(Double[] a) {
		Double[] result = new Double[50];
		for (int i = 0; i < 50; i++) {
			if (a[i] > 0) {
				result[i] = a[i];
			} else {
				result[i] = -a[i];
			}
		}
		Double y = a[0];
		int x = 0;
		for (int i = 1; i < 50; i++) {
			if (y > a[i]) {
				y = a[i];
				x = i;
			}
		}
		return x;
	}

	/**
	 * ��e=0�������ͷȨ�����ܣ���e=1�������ͷȨ������
	 * 
	 * @param a
	 *            ��1�л�ȡ��Double[]
	 * @param b
	 *            ��2�л�ȡ��Double[]
	 * @param c
	 *            ��3�л�ȡ��Double[]
	 * @param d
	 *            ��4�л�ȡ��Double[]
	 * @param e
	 *            e =0�������ͷ��e=1�������ͷ
	 * @return
	 */
	private Double someArryAll(Double[] a, Double[] b, Double[] c, Double[] d,
			int e) {
		Double result = 0.0;
		if (mark1 != 0 && mark2 == e) {
			result = result + arrayAll(a);
		}
		if (mark3 != 0 && mark4 == e) {
			result = result + arrayAll(b);
		}
		if (mark5 != 0 && mark5 == e) {
			result = result + arrayAll(c);
		}
		if (mark7 != 0 && mark6 == e) {
			result = result + arrayAll(d);
		}
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
		editText5.setText("");
		editText6.setText("");
		editText7.setText("");
		editText8.setText("");
		editText9.setText("");
		editText10.setText("");
		editText11.setText("");
		editText12.setText("");
		out_tv.setText("");
	}

	/**
	 * (��ͷ)�Ϲ���Ȩ��������ֲ�
	 * 
	 * @param a
	 *            ����ʲ����ڼ۸�
	 * @param b
	 *            ��Ȩ�۸�
	 * @param c
	 *            ��ȨȨ���
	 * @return
	 */
	private Double[] calculate1(Double b, Double c) {
		Double[] result = new Double[50];
		Double a = 0.0;
		for (int i = 0; i < 50; i++) {
			result[i] = Math.max((a - b), 0) - c;
			System.out.println(result[i] + "   result[i]");
			a = a + b / 20;
		}
		return result;
	}

	/**
	 * (��ͷ)�Ϲ���Ȩ��������ֲ�
	 * 
	 * @param a
	 *            ����ʲ����ڼ۸�
	 * @param b
	 *            ��Ȩ�۸�
	 * @param c
	 *            ��ȨȨ���
	 * @return
	 */
	private Double[] calculate2(Double b, Double c) {
		Double[] result = new Double[50];
		Double a = 0.0;
		for (int i = 0; i < 50; i++) {
			result[i] = c - Math.max((a - b), 0);
			System.out.println(result[i] + "   result[i]");
			a = a + b / 20;
		}
		return result;
	}

	/**
	 * (��ͷ)�Ϲ���Ȩ��������ֲ�
	 * 
	 * @param a
	 *            ����ʲ����ڼ۸�
	 * @param b
	 *            ��Ȩ�۸�
	 * @param c
	 *            ��ȨȨ���
	 * @return
	 */
	private Double[] calculate3(Double b, Double c) {
		Double[] result = new Double[50];
		Double a = 0.0;
		for (int i = 0; i < 50; i++) {
			result[i] = Math.max((b - a), 0) - c;
			a = a + b / 20;
		}
		return result;
	}

	/**
	 * (��ͷ)�Ϲ���Ȩ��������ֲ�
	 * 
	 * @param a
	 *            ����ʲ����ڼ۸�
	 * @param b
	 *            ��Ȩ�۸�
	 * @param c
	 *            ��ȨȨ���
	 * @return
	 */
	private Double[] calculate4(Double b, Double c) {
		Double[] result = new Double[50];
		Double a = 0.0;
		for (int i = 0; i < 50; i++) {
			result[i] = c - Math.max((b - a), 0);
			a = a + b / 20;
		}
		return result;
	}

	/**
	 * (��ͷ)��Ʊ��������ֲ�
	 * 
	 * @param b
	 *            ��Ȩ�۸�
	 * @param c
	 *            ��Ʊ�۸�
	 * @return
	 */
	private Double[] calculate5(Double b, Double c) {
		Double[] result = new Double[50];
		Double a = 0.0;
		for (int i = 0; i < 50; i++) {
			result[i] = a - c;
			a = a + b / 20;
		}
		return result;
	}

	/**
	 * (��ͷ)��Ʊ��������ֲ�
	 * 
	 * @param b
	 *            ��Ȩ�۸�
	 * @param c
	 *            ��Ʊ�۸�
	 * @return
	 */
	private Double[] calculate6(Double b, Double c) {
		Double[] result = new Double[50];
		Double a = 0.0;
		for (int i = 0; i < 50; i++) {
			result[i] = c - a;
			a = a + b / 20;
		}
		return result;
	}

	/** ��1�е�һ��Spinner�ļ����� */
	class SpinnerListener1 implements OnItemSelectedListener {

		// ���û�ѡ����һ����Ŀʱ���ͻ���ø÷���
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			mark1 = editTextConclude(position, mark1, editText1, editText2,
					editText3);
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}
	}

	/** ��2�е�һ��Spinner�ļ����� */
	class SpinnerListener3 implements OnItemSelectedListener {

		// ���û�ѡ����һ����Ŀʱ���ͻ���ø÷���
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			mark3 = editTextConclude(position, mark3, editText4, editText5,
					editText6);
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}
	}

	/** ��3�е�һ��Spinner�ļ����� */
	class SpinnerListener5 implements OnItemSelectedListener {

		// ���û�ѡ����һ����Ŀʱ���ͻ���ø÷���
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			mark5 = editTextConclude(position, mark5, editText7, editText8,
					editText9);
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}
	}

	/** ��4�е�һ��Spinner�ļ����� */
	class SpinnerListener7 implements OnItemSelectedListener {

		// ���û�ѡ����һ����Ŀʱ���ͻ���ø÷���
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			mark7 = editTextConclude(position, mark7, editText10, editText11,
					editText12);
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}
	}

	/**
	 * ����ÿ�е�3��editText�Ƿ�ɱ༭ ��������mark�Ǹ���Ʒ���ı临��Ʒ����Ʒû�б仯������
	 * 
	 * @param position
	 * @param mark1
	 * @param editText1
	 * @param editText2
	 * @param editText3
	 */
	private int editTextConclude(int position, int mark, EditText editText1,
			EditText editText2, EditText editText3) {
		switch (position) {
		case 0:
			mark = 0;
			editText1.setFocusable(false);
			editText1.setFocusableInTouchMode(false);
			editText2.setFocusable(false);
			editText2.setFocusableInTouchMode(false);
			editText3.setFocusable(false);
			editText3.setFocusableInTouchMode(false);
			break;
		case 1:
			mark = 1;
			editText1.setFocusableInTouchMode(true);
			editText1.setFocusable(true);
			editText1.requestFocus();
			editText2.setFocusableInTouchMode(true);
			editText2.setFocusable(true);
			editText3.setFocusable(false);
			editText3.setFocusableInTouchMode(false);
			break;
		case 2:
			mark = 2;
			editText1.setFocusableInTouchMode(true);
			editText1.setFocusable(true);
			editText1.requestFocus();
			editText2.setFocusableInTouchMode(true);
			editText2.setFocusable(true);
			editText3.setFocusable(false);
			editText3.setFocusableInTouchMode(false);
			break;
		case 3:
			mark = 3;
			editText1.setFocusable(false);
			editText1.setFocusableInTouchMode(false);
			editText2.setFocusable(false);
			editText2.setFocusableInTouchMode(false);
			editText3.setFocusableInTouchMode(true);
			editText3.setFocusable(true);
			editText3.requestFocus();
			break;
		}
		return mark;
	}

	/** ��1�е�2��Spinner�ļ����� */
	class SpinnerListener2 implements OnItemSelectedListener {

		// ���û�ѡ����һ����Ŀʱ���ͻ���ø÷���
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			switch (position) {
			case 0:
				mark2 = 0;
				break;
			case 1:
				mark2 = 1;
				break;
			}
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}
	}

	/** ��2�е�2һ��Spinner�ļ����� */
	class SpinnerListener4 implements OnItemSelectedListener {

		// ���û�ѡ����һ����Ŀʱ���ͻ���ø÷���
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			switch (position) {
			case 0:
				mark4 = 0;
				break;
			case 1:
				mark4 = 1;
				break;
			}
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}
	}

	/** ��3�е�2��Spinner�ļ����� */
	class SpinnerListener6 implements OnItemSelectedListener {

		// ���û�ѡ����һ����Ŀʱ���ͻ���ø÷���
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			switch (position) {
			case 0:
				mark6 = 0;
				break;
			case 1:
				mark6 = 1;
				break;
			}
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}
	}

	/** ��4�е�2��Spinner�ļ����� */
	class SpinnerListener8 implements OnItemSelectedListener {

		// ���û�ѡ����һ����Ŀʱ���ͻ���ø÷���
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			switch (position) {
			case 0:
				mark8 = 0;
				break;
			case 1:
				mark8 = 1;
				break;
			}
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
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
		Intent intent = new Intent(Button3Activity.this,
				DerivativeActivity.class);
		startActivity(intent);
		finish();
	}
}