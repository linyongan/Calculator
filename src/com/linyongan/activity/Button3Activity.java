package com.linyongan.activity;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.linyongan.achartengine.AbstractDemoChart;
import com.linyongan.achartengine.IDemoChart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
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
	/** ��ʾͼ��ť */
	private Button showButton;
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
	private double[] result1;
	private double[] result2;
	private double[] result3;
	private double[] result4;
	private double[] all;
	String test1;
	String test4;
	String test7;
	String test10;
	String test3;
	String test6;
	String test9;
	String test12;

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
		showButton = (Button) findViewById(R.id.Button3_show_bt);
		showButton.setOnClickListener(new ButtonListener());
	}

	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.Button3_calculate_bt:
				// ��ȡ���������֣�������ȡֵ������
				test1 = editText1.getText().toString();
				String test2 = editText2.getText().toString();
				test3 = editText3.getText().toString();
				test4 = editText4.getText().toString();
				String test5 = editText5.getText().toString();
				test6 = editText6.getText().toString();
				test7 = editText7.getText().toString();
				String test8 = editText8.getText().toString();
				test9 = editText9.getText().toString();
				test10 = editText10.getText().toString();
				String test11 = editText11.getText().toString();
				test12 = editText12.getText().toString();

				result1 = getResult(test1, test2, test3, mark1, mark2);
				result2 = getResult(test4, test5, test6, mark3, mark4);
				result3 = getResult(test7, test8, test9, mark5, mark6);
				result4 = getResult(test10, test11, test12, mark7, mark8);
				// ������ʾ�Ľ��
				String out = "";
				out = "��Ϲ����ɱ�Ϊ��"
						+ String.format(
								"%.2f",
								(someArryAll(result1, result2, result3,
										result4, 0) - someArryAll(result1,
										result2, result3, result4, 1))) + "Ԫ\n";
				all = All(result1, result2, result3, result4);
				double max = arrayMax(all);
				out = out + "��������ʧΪ��" + String.format("%.2f", max) + "Ԫ\n";
				double min = arrayMin(all);
				out = out + "����������Ϊ��" + String.format("%.2f", min) + "Ԫ\n";
				out = out + "���ӯ��ƽ���Ϊ��" + arraychange(all);
				out_tv.setText("��������\n" + out);
				showButton.setVisibility(View.VISIBLE);
				break;
			case R.id.Button3_back_bn:
				goBack();
				break;
			case R.id.Button3_clean_bn:
				cleanEditText();
				break;
			case R.id.Button3_show_bt:
				IDemoChart[] mCharts = new IDemoChart[] { new LineChar() };
				Intent timeIntent = mCharts[0].execute(Button3Activity.this);
				startActivity(timeIntent);
				break;
			}
		}
	}

	/**
	 * ����ÿһ��
	 */
	private double[] getResult(String a, String b, String c, int mark1,
			int mark2) {
		double[] result = new double[50];
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
		} else if (mark1 == 3 && mark2 == 0 && c.length() != 0) {
			result = calculate5(Double.parseDouble(c));
		} else if (mark1 == 3 && mark2 == 1 && c.length() != 0) {
			result = calculate6(Double.parseDouble(c));
		} else {
			return null;
		}
		return result;
	}

	/**
	 * ��ȡÿһ�е�����
	 */
	private String getAllName(int mark1, int mark2, String string,
			String string1) {

		String result = "";
		if (mark1 == 1 && mark2 == 0 && string.length() != 0) {
			result = "(��ͷ)��Ȩ��Ϊ" + Double.parseDouble(string) + "���Ϲ���Ȩ";
		} else if (mark1 == 1 && mark2 == 1 && string.length() != 0) {
			result = "(��ͷ)��Ȩ��Ϊ" + Double.parseDouble(string) + "���Ϲ���Ȩ";
		} else if (mark1 == 2 && mark2 == 0 && string.length() != 0) {
			result = "(��ͷ)��Ȩ��Ϊ" + Double.parseDouble(string) + "���Ϲ���Ȩ";
		} else if (mark1 == 2 && mark2 == 1 && string.length() != 0) {
			result = "(��ͷ)��Ȩ��Ϊ" + Double.parseDouble(string) + "���Ϲ���Ȩ";
		} else if (mark1 == 3 && mark2 == 0 && string1.length() != 0) {
			result = "(��ͷ)�۸�Ϊ" + Double.parseDouble(string1) + "�Ĺ�Ʊ";
		} else if (mark1 == 3 && mark2 == 1 && string1.length() != 0) {
			result = "(��ͷ)�۸�Ϊ" + Double.parseDouble(string1) + "�Ĺ�Ʊ";
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
	private double arrayAll(double[] a) {
		if (a != null) {
			double result = 0.0;
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
	private double[] All(double[] a, double[] b, double[] c, double[] d) {
		// �ǵó�ʼ�������򱨿�ָ��
		double[] result = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
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
	private double arrayMax(double[] a) {
		double result = a[0];
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
	private double arrayMin(double[] a) {
		double result = a[0];
		for (int i = 1; i < 50; i++) {
			if (result > a[i])
				result = a[i];
		}
		return result;
	}

	/**
	 * ����һ��Double[]�����еĸ����������,Ȼ�����Y��СʱX��ֵ (δ��õģ���������)
	 * 
	 * @param a
	 * @return
	 */
	private int arraychange(double[] a) {
		double[] result = new double[50];
		for (int i = 0; i < 50; i++) {
			if (a[i] > 0) {
				result[i] = a[i];
			} else {
				result[i] = -a[i];
			}
		}
		double y = a[0];
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
	private double someArryAll(double[] a, double[] b, double[] c, double[] d,
			int e) {
		double result = 0.0;
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
		showButton.setVisibility(View.GONE);
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
	private double[] calculate1(double b, double c) {
		double[] result = new double[50];
		double a = 0.0;
		for (int i = 0; i < 50; i++) {
			result[i] = Math.max((a - b), 0) - c;
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
	private double[] calculate2(double b, double c) {
		double[] result = new double[50];
		double a = 0.0;
		for (int i = 0; i < 50; i++) {
			result[i] = c - Math.max((a - b), 0);
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
	private double[] calculate3(double b, double c) {
		double[] result = new double[50];
		double a = 0.0;
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
	private double[] calculate4(double b, double c) {
		double[] result = new double[50];
		double a = 0.0;
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
	private double[] calculate5(double c) {
		double[] result = new double[50];
		double a = 0.0;
		for (int i = 0; i < 50; i++) {
			result[i] = a - c;
			a = a + c / 20;
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
	private double[] calculate6(double c) {
		double[] result = new double[50];
		double a = 0.0;
		for (int i = 0; i < 50; i++) {
			result[i] = c - a;
			a = a + c / 20;
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
			showButton.setVisibility(View.GONE);
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
			showButton.setVisibility(View.GONE);
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
			showButton.setVisibility(View.GONE);
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
			showButton.setVisibility(View.GONE);
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

	/**
	 * ��ͼ�����������4��������Ϊnull�ĸ���+1
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return
	 */
	private int length(Object a, Object b, Object c, Object d) {
		int i = 0;
		if (a != null) {
			i++;
		}
		if (b != null) {
			i++;
		}
		if (c != null) {
			i++;
		}
		if (d != null) {
			i++;
		}
		return i + 1;

	}

	/**
	 * ��ͼ����ȡtitle�ķ���
	 * 
	 * @return
	 */
	private String[] setTitles() {
		String string1 = getAllName(mark1, mark2, test1, test3);
		String string2 = getAllName(mark3, mark4, test4, test6);
		String string3 = getAllName(mark5, mark6, test7, test9);
		String string4 = getAllName(mark7, mark8, test10, test12);
		// ÿ��item��title
		String[] titles = new String[length(string1, string2, string3, string4)];
		titles[0] = "���������";
		int i = 1;
		if (string1 != null) {
			titles[i] = string1;
			i++;
		}
		if (string2 != null) {
			titles[i] = string2;
			i++;
		}
		if (string3 != null) {
			titles[i] = string3;
			i++;
		}
		if (string4 != null) {
			titles[i] = string4;
			i++;
		}
		return titles;
	}

	/**
	 * ��ͼ����ȡY����Ҫ��ֵ
	 * 
	 * @return
	 */
	private List<double[]> setY() {
		List<double[]> values = new ArrayList<double[]>();
		values.add(all);
		if (result1 != null) {
			values.add(result1);
		}
		if (result2 != null) {
			values.add(result2);
		}
		if (result3 != null) {
			values.add(result3);
		}
		if (result4 != null) {
			values.add(result4);
		}
		return values;
	}

	/**
	 * ��ͼ��Ϊÿ���߶�������ɫ
	 * 
	 * @return
	 */
	private int[] setColor() {
		int[] color = new int[] { Color.BLUE, Color.GREEN, Color.CYAN,
				Color.YELLOW, Color.RED };
		int i = 1;
		int[] result = new int[length(result1, result2, result3, result4)];
		result[0] = color[0];
		if (result1 != null) {
			result[i] = color[i];
			i++;
		}
		if (result2 != null) {
			result[i] = color[i];
			i++;
		}
		if (result3 != null) {
			result[i] = color[i];
			i++;
		}
		if (result4 != null) {
			result[i] = color[i];
			i++;
		}
		return result;
	}

	/**
	 * ��ͼ��Ϊÿ���߶����õ����״
	 * 
	 * @return
	 */
	private PointStyle[] setPoint() {
		int i = 1;
		PointStyle[] result = new PointStyle[length(result1, result2, result3,
				result4)];
		result[0] = PointStyle.POINT;
		if (result1 != null) {
			result[i] = PointStyle.POINT;
			i++;
		}
		if (result2 != null) {
			result[i] = PointStyle.POINT;
			i++;
		}
		if (result3 != null) {
			result[i] = PointStyle.POINT;
			i++;
		}
		if (result4 != null) {
			result[i] = PointStyle.POINT;
			i++;
		}
		return result;
	}

	/**
	 * ��ͼ
	 * 
	 * @author yongan
	 * 
	 */
	public class LineChar extends AbstractDemoChart {
		public String getName() {
			return "��Ȩ�������ֲ�ͼ";
		}

		public String getDesc() {
			return "��Ȩ�������ֲ�ͼ";
		}

		public Intent execute(Context context) {
			// ׼�����ݣ�
			String[] titles = setTitles();
			// x���ֵ
			List<double[]> x = new ArrayList<double[]>();
			for (int i = 0; i < titles.length; i++) {
				x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });// ÿ�������е��X����
			}
			// y���ֵ
			List<double[]> values = setY();
			// ÿ�����е���ɫ����
			int[] colors = setColor();
			// Ϊÿ���߶����õ����״
			PointStyle[] styles = setPoint();

			XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);// ����AbstractDemoChart�еķ�������renderer.
			int length = renderer.getSeriesRendererCount();
			// ���ǿ��Ļ���ʵ��
			for (int i = 0; i < length; i++) {
				((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
						.setFillPoints(true);// ����ͼ�ϵĵ�Ϊʵ��
			}
			// ��������������������ɫ�����ǩ����ɫ
			setChartSettings(renderer, "���������", "��Ȩ�۸�", "����", 0.5,
					12.5, -10, 40, Color.LTGRAY, Color.LTGRAY);
			renderer.setXLabels(12);// ����x����ʾ12����,����setChartSettings�����ֵ����Сֵ�Զ������ļ��
			renderer.setYLabels(10);// ����y����ʾ10����,����setChartSettings�����ֵ����Сֵ�Զ������ļ��
			renderer.setShowGrid(true);// �Ƿ���ʾ����
			// x��y�������ֵķ����෴�ġ�
			renderer.setXLabelsAlign(Align.RIGHT);// �̶�����̶ȱ�ע֮������λ�ù�ϵ
			renderer.setYLabelsAlign(Align.CENTER);// �̶�����̶ȱ�ע֮������λ�ù�ϵ
			renderer.setPanLimits(new double[] { -10, 20, -arrayMax(all)-10, arrayMax(all)+10 }); // �����϶�ʱX��Y����������ֵ��Сֵ.
			Intent intent = ChartFactory.getLineChartIntent(context,
					buildDataset(titles, x, values), renderer, "��Ȩ�������ֲ�ͼ");// ����Intent
			return intent;
		}
	}

}