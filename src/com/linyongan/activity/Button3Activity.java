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
import android.widget.Toast;

/**
 * ��Ȩ����������ҳ��
 */
public class Button3Activity extends Activity {
	/** ÿһ������ĳ��� */
	private static final int LENGTH = 50;
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
	/** ��һ�еļ����� */
	private double[] result1;
	/** �ڶ��еļ����� */
	private double[] result2;
	/** �����еļ����� */
	private double[] result3;
	/** �����еļ����� */
	private double[] result4;
	/** �ܵ����� */
	private String test1;
	private String test4;
	private String test7;
	private String test10;
	private String test3;
	private String test6;
	private String test9;
	private String test12;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.derivative13);

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
				showButton.setVisibility(View.VISIBLE);

				// ������߼��ж�
				String string1 = checkInput(mark1, test1, test2, test3);
				if (string1.equals("��������")) {
					String string2 = checkInput(mark3, test4, test5, test6);
					if (string2.equals("��������")) {
						String string3 = checkInput(mark5, test7, test8, test9);
						if (string3.equals("��������")) {
							String string4 = checkInput(mark7, test10, test11,
									test12);
							if (string4.equals("��������")) {

								// ��ʽ��ʼ����
								result1 = getResult(test1, test2, test3, mark1,
										mark2);
								result2 = getResult(test4, test5, test6, mark3,
										mark4);
								result3 = getResult(test7, test8, test9, mark5,
										mark6);
								result4 = getResult(test10, test11, test12,
										mark7, mark8);
								// ������ʾ�Ľ��
								String out = "";
								out = "��Ϲ����ɱ�Ϊ��"
										+ String.format(
												"%.2f",
												(someArryAll(0) - someArryAll(1)))
										+ "Ԫ\n";
								double max = arrayMax(All());
								out = out + "��������ʧΪ��"
										+ String.format("%.2f", max) + "Ԫ\n";
								double min = arrayMin(All());
								out = out + "����������Ϊ��"
										+ String.format("%.2f", min) + "Ԫ\n";
								out = out + "���ӯ��ƽ���Ϊ��" + arraychange(All());
								out_tv.setText("��������\n" + out);

							} else {
								Toast.makeText(Button3Activity.this,
										"�����У�" + string4, Toast.LENGTH_SHORT)
										.show();
								out_tv.setText("��������\n" + "��һ���У�" + string4);
							}
						} else {
							Toast.makeText(Button3Activity.this,
									"�����У�" + string3, Toast.LENGTH_SHORT)
									.show();
							out_tv.setText("��������\n" + "�����У�" + string3);
						}

					} else {
						Toast.makeText(Button3Activity.this, "�ڶ��У�" + string2,
								Toast.LENGTH_SHORT).show();
						out_tv.setText("��������\n" + "�ڶ��У�" + string2);
					}
				} else {
					Toast.makeText(Button3Activity.this, "��һ�У�" + string1,
							Toast.LENGTH_SHORT).show();
					out_tv.setText("��������\n" + "��һ�У�" + string1);
				}

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
	 * ������߼��ж�
	 * 
	 * @param mark
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	private String checkInput(int mark, String a, String b, String c) {
		if (mark == 0) {
			if (a.length() != 0 || b.length() != 0 || c.length() != 0) {
				return "�����Ʒ����ѡ���˿գ�\n��������е����ݡ�";
			}
		}
		if (mark == 1 || mark == 2) {
			if (a.length() == 0) {
				return "��������Ȩ�ۡ�";
			}
			if (b.length() == 0) {
				return "������Ȩ���";
			}
			if (c.length() != 0) {
				return "��������еĹ�Ʊ�۸�";
			}
		}
		if (mark == 3) {
			if (a.length() != 0) {
				return "��������е���Ȩ�ۡ�";
			}
			if (b.length() != 0) {
				return "��������е�Ȩ���";
			}
			if (c.length() == 0) {
				return "�������Ʊ�۸�";
			}
		}
		return "��������";

	}

	/**
	 * ����ÿһ�е�ֵ��3X2��6����ϣ����ж�����������Ƿ�Ϊ�գ�
	 * 
	 * @param a
	 *            ��һ��������ֵ
	 * @param b
	 *            �ڶ���������ֵ
	 * @param c
	 *            ������������ֵ
	 * @param mark1
	 *            ��һ��Spinner��ֵ
	 * @param mark2
	 *            �ڶ���Spinner��ֵ
	 * @return ���������룬�򷵻ؼ����Ľ����û�����룬�򷵻�null
	 */
	private double[] getResult(String a, String b, String c, int mark1,
			int mark2) {
		double[] result = new double[LENGTH];
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
	 * ����һ��double[]����ĸ���֮�ͣ����ж�����������Ƿ�Ϊ�գ�
	 * 
	 * @return ���������룬�򷵻ؼ����Ľ����û�����룬�򷵻�0.0
	 */
	private double arrayAll(double[] a) {
		if (a != null) {
			double result = 0.0;
			for (int i = 0; i < LENGTH; i++) {
				result = result + a[i];
			}
			return result;
		} else {
			return 0.0;
		}
	}

	/**
	 * ������������棨���ж�����������Ƿ�Ϊ�գ�
	 */
	private double[] All() {
		// �ǵó�ʼ�������򱨿�ָ��
		double[] result = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		if (result1 != null) {
			for (int i = 0; i < LENGTH; i++) {
				result[i] = result[i] + result1[i];
			}
		}
		if (result2 != null) {
			for (int i = 0; i < LENGTH; i++) {
				result[i] = result[i] + result2[i];
			}
		}
		if (result3 != null) {
			for (int i = 0; i < LENGTH; i++) {
				result[i] = result[i] + result3[i];
			}
		}
		if (result4 != null) {
			for (int i = 0; i < LENGTH; i++) {
				result[i] = result[i] + result4[i];
			}
		}
		return result;

	}

	/**
	 * ����һ��double[]����������һ��
	 * 
	 * @return �������ֵ
	 */
	private double arrayMax(double[] a) {
		double result = a[0];
		for (int i = 1; i < a.length; i++) {
			if (result < a[i])
				result = a[i];
		}
		return result;
	}

	/**
	 * ����һ��double[]��������С��һ��
	 * 
	 * @return ������Сֵ
	 */
	private double arrayMin(double[] a) {
		double result = a[0];
		for (int i = 1; i < a.length; i++) {
			if (result > a[i])
				result = a[i];
		}
		return result;
	}

	/**
	 * ����һ��double[]�����еĸ����������,Ȼ�����Y��СʱX��ֵ (δ��õģ���������)
	 * 
	 */
	private int arraychange(double[] a) {
		double[] result = new double[LENGTH];
		for (int i = 0; i < LENGTH; i++) {
			if (a[i] > 0) {
				result[i] = a[i];
			} else {
				result[i] = -a[i];
			}
		}
		double y = a[0];
		int x = 0;
		for (int i = 1; i < LENGTH; i++) {
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
	 * @param e
	 *            e =0�������ͷ�� e=1�������ͷ
	 */
	private double someArryAll(int e) {
		double result = 0.0;
		if (mark1 != 0 && mark2 == e) {
			result = result + arrayAll(result1);
		}
		if (mark3 != 0 && mark4 == e) {
			result = result + arrayAll(result2);
		}
		if (mark5 != 0 && mark5 == e) {
			result = result + arrayAll(result3);
		}
		if (mark7 != 0 && mark6 == e) {
			result = result + arrayAll(result4);
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
	 */
	private double[] calculate1(double b, double c) {
		double[] result = new double[LENGTH];
		double a = 0.0;
		for (int i = 0; i < LENGTH; i++) {
			result[i] = Math.max((a - b), 0) - c;
			a = a + 2.5 * b / LENGTH;
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
	 */
	private double[] calculate2(double b, double c) {
		double[] result = new double[LENGTH];
		double a = 0.0;
		for (int i = 0; i < LENGTH; i++) {
			result[i] = c - Math.max((a - b), 0);
			a = a + 2.5 * b / LENGTH;
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
	 */
	private double[] calculate3(double b, double c) {
		double[] result = new double[LENGTH];
		double a = 0.0;
		for (int i = 0; i < LENGTH; i++) {
			result[i] = Math.max((b - a), 0) - c;
			a = a + 2.5 * b / LENGTH;
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
	 */
	private double[] calculate4(double b, double c) {
		double[] result = new double[LENGTH];
		double a = 0.0;
		for (int i = 0; i < LENGTH; i++) {
			result[i] = c - Math.max((b - a), 0);
			a = a + 2.5 * b / LENGTH;
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
	 */
	private double[] calculate5(double c) {
		double[] result = new double[LENGTH];
		double a = 0.0;
		for (int i = 0; i < LENGTH; i++) {
			result[i] = a - c;
			a = a + 2.5 * c / LENGTH;
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
	 */
	private double[] calculate6(double c) {
		double[] result = new double[LENGTH];
		double a = 0.0;
		for (int i = 0; i < LENGTH; i++) {
			result[i] = c - a;
			a = a + 2.5 * c / LENGTH;
		}
		return result;
	}

	/** ��1�е�һ��Spinner�ļ����� */
	class SpinnerListener1 implements OnItemSelectedListener {
		// ���û�ѡ����һ����Ŀʱ���ͻ���ø÷���
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			mark1 = ListennerControl(position, mark1, editText1, editText2,
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
			mark3 = ListennerControl(position, mark3, editText4, editText5,
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
			mark5 = ListennerControl(position, mark5, editText7, editText8,
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
			mark7 = ListennerControl(position, mark7, editText10, editText11,
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
	 *            �������
	 * @param mark
	 *            �������������
	 * @param editText1
	 *            ����editText�Ƿ��������
	 * @param editText2
	 *            ����editText�Ƿ��������
	 * @param editText3
	 *            ����editText�Ƿ��������
	 * @return mark�ı���ֵ������
	 */
	private int ListennerControl(int position, int mark, EditText editText1,
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

	/**
	 * ������ͷ��ͷSpinner���������
	 * 
	 * @param position
	 *            �������
	 * @param mark
	 *            �������������
	 * @return mark�ı���ֵ������
	 */
	private int ListennerControl2(int position, int mark) {
		switch (position) {
		case 0:
			mark = 0;
			showButton.setVisibility(View.GONE);
			break;
		case 1:
			mark = 1;
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
			mark2 = ListennerControl2(position, mark2);
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
			mark4 = ListennerControl2(position, mark4);
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
			mark6 = ListennerControl2(position, mark6);
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
			mark8 = ListennerControl2(position, mark8);
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
		finish();
	}
}