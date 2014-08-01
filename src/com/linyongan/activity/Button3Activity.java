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
 * 期权组合损益分析页面
 */
public class Button3Activity extends Activity {
	/** 每一个数组的长度 */
	private static final int LENGTH = 50;
	/** 返回按钮 */
	private ImageButton backButton;
	/** 清空按钮 */
	private ImageButton cleanButton;
	/** 计算按钮 */
	private Button calculateButton;
	/** 显示图表按钮 */
	private Button showButton;
	/** 单数是认购认沽股票 */
	private Spinner spinner1;
	private Spinner spinner3;
	private Spinner spinner5;
	private Spinner spinner7;
	/** 双数是多头空头 */
	private Spinner spinner2;
	private Spinner spinner4;
	private Spinner spinner6;
	private Spinner spinner8;
	/**
	 * 1~4及倍数分别是行权价，权益金， 股票到期价格，股票价格
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
	/** 结果(文字) */
	private TextView out_tv;
	/** 单数标记 0:空 1:认购 2:认沽 3:股票 */
	private int mark1 = 0;
	private int mark3 = 0;
	private int mark5 = 0;
	private int mark7 = 0;
	/** 双数标记 0：多头 1：空头 */
	private int mark2 = 0;
	private int mark4 = 0;
	private int mark6 = 0;
	private int mark8 = 0;
	/** 第一行的计算结果 */
	private double[] result1;
	/** 第二行的计算结果 */
	private double[] result2;
	/** 第三行的计算结果 */
	private double[] result3;
	/** 第四行的计算结果 */
	private double[] result4;
	/** 总的收益 */
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
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.derivative3);

		// 找到所有的Spinner
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

		// 找到所有的TextView
		out_tv = (TextView) findViewById(R.id.Button3_out_tv);

		// 找到所有的EditText
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

		// 找到所有的Button
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
				// 获取输入框的数字，必须先取值！！！
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

				// 输入的逻辑判断
				String string1 = checkInput(mark1, test1, test2, test3);
				if (string1.equals("输入正常")) {
					String string2 = checkInput(mark3, test4, test5, test6);
					if (string2.equals("输入正常")) {
						String string3 = checkInput(mark5, test7, test8, test9);
						if (string3.equals("输入正常")) {
							String string4 = checkInput(mark7, test10, test11,
									test12);
							if (string4.equals("输入正常")) {

								// 正式开始计算
								result1 = getResult(test1, test2, test3, mark1,
										mark2);
								result2 = getResult(test4, test5, test6, mark3,
										mark4);
								result3 = getResult(test7, test8, test9, mark5,
										mark6);
								result4 = getResult(test10, test11, test12,
										mark7, mark8);
								// 计算显示的结果
								String out = "";
								out = "组合构建成本为："
										+ String.format(
												"%.2f",
												(someArryAll(0) - someArryAll(1)))
										+ "元\n";
								double max = arrayMax(All());
								out = out + "组合最大损失为："
										+ String.format("%.2f", max) + "元\n";
								double min = arrayMin(All());
								out = out + "组合最大收益为："
										+ String.format("%.2f", min) + "元\n";
								out = out + "组合盈亏平衡点为：" + arraychange(All());
								out_tv.setText("计算结果：\n" + out);

							} else {
								Toast.makeText(Button3Activity.this,
										"第四行，" + string4, Toast.LENGTH_SHORT)
										.show();
								out_tv.setText("计算结果：\n" + "第一四行，" + string4);
							}
						} else {
							Toast.makeText(Button3Activity.this,
									"第三行，" + string3, Toast.LENGTH_SHORT)
									.show();
							out_tv.setText("计算结果：\n" + "第三行，" + string3);
						}

					} else {
						Toast.makeText(Button3Activity.this, "第二行，" + string2,
								Toast.LENGTH_SHORT).show();
						out_tv.setText("计算结果：\n" + "第二行，" + string2);
					}
				} else {
					Toast.makeText(Button3Activity.this, "第一行，" + string1,
							Toast.LENGTH_SHORT).show();
					out_tv.setText("计算结果：\n" + "第一行，" + string1);
				}

				break;
			case R.id.Button3_back_bn:
				goBack();
				break;
			case R.id.Button3_clean_bn:
				cleanEditText();
				break;
			case R.id.Button3_show_bt:
				// 显示图表
				IDemoChart[] mCharts = new IDemoChart[] { new LineChar() };
				Intent timeIntent = mCharts[0].execute(Button3Activity.this);
				startActivity(timeIntent);
				break;
			}
		}
	}

	/**
	 * 输入的逻辑判断
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
				return "如果产品类型选择了空，\n请清空这行的数据。";
			}
		}
		if (mark == 1 || mark == 2) {
			if (a.length() == 0) {
				return "请输入行权价。";
			}
			if (b.length() == 0) {
				return "请输入权益金。";
			}
			if (c.length() != 0) {
				return "请清空这行的股票价格。";
			}
		}
		if (mark == 3) {
			if (a.length() != 0) {
				return "请清空这行的行权价。";
			}
			if (b.length() != 0) {
				return "请清空这行的权益金。";
			}
			if (c.length() == 0) {
				return "请输入股票价格。";
			}
		}
		return "输入正常";

	}

	/**
	 * 计算每一行的值，3X2共6种组合（已判断输入的数字是否为空）
	 * 
	 * @param a
	 *            第一个输入框的值
	 * @param b
	 *            第二个输入框的值
	 * @param c
	 *            第三个输入框的值
	 * @param mark1
	 *            第一个Spinner的值
	 * @param mark2
	 *            第二个Spinner的值
	 * @return 这行有输入，则返回计算后的结果；没有输入，则返回null
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
	 * 计算一个double[]数组的各项之和（已判断输入的数字是否为空）
	 * 
	 * @return 这行有输入，则返回计算后的结果；没有输入，则返回0.0
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
	 * 计算组合总收益（已判断输入的数字是否为空）
	 */
	private double[] All() {
		// 记得初始化，否则报空指针
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
	 * 计算一个double[]数组中最大的一个
	 * 
	 * @return 返回最大值
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
	 * 计算一个double[]数组中最小的一个
	 * 
	 * @return 返回最小值
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
	 * 计算一个double[]数组中的负数变成正数,然后求出Y最小时X的值 (未算好的！！！！！)
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
	 * 当e=0，计算多头权益金加总；当e=1，计算空头权益金加总
	 * 
	 * @param e
	 *            e =0，计算多头； e=1，计算空头
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
	 * 清空函数
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
	 * (多头)认购期权到期收益分布
	 * 
	 * @param a
	 *            标的资产到期价格
	 * @param b
	 *            行权价格
	 * @param c
	 *            期权权益金
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
	 * (空头)认购期权到期收益分布
	 * 
	 * @param a
	 *            标的资产到期价格
	 * @param b
	 *            行权价格
	 * @param c
	 *            期权权益金
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
	 * (多头)认沽期权到期收益分布
	 * 
	 * @param a
	 *            标的资产到期价格
	 * @param b
	 *            行权价格
	 * @param c
	 *            期权权益金
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
	 * (空头)认沽期权到期收益分布
	 * 
	 * @param a
	 *            标的资产到期价格
	 * @param b
	 *            行权价格
	 * @param c
	 *            期权权益金
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
	 * (多头)股票到期收益分布
	 * 
	 * @param b
	 *            行权价格
	 * @param c
	 *            股票价格
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
	 * (空头)股票到期收益分布
	 * 
	 * @param b
	 *            行权价格
	 * @param c
	 *            股票价格
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

	/** 第1行第一个Spinner的监听器 */
	class SpinnerListener1 implements OnItemSelectedListener {
		// 当用户选定了一个条目时，就会调用该方法
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			mark1 = ListennerControl(position, mark1, editText1, editText2,
					editText3);
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}
	}

	/** 第2行第一个Spinner的监听器 */
	class SpinnerListener3 implements OnItemSelectedListener {
		// 当用户选定了一个条目时，就会调用该方法
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			mark3 = ListennerControl(position, mark3, editText4, editText5,
					editText6);
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}
	}

	/** 第3行第一个Spinner的监听器 */
	class SpinnerListener5 implements OnItemSelectedListener {
		// 当用户选定了一个条目时，就会调用该方法
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			mark5 = ListennerControl(position, mark5, editText7, editText8,
					editText9);
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}
	}

	/** 第4行第一个Spinner的监听器 */
	class SpinnerListener7 implements OnItemSelectedListener {
		// 当用户选定了一个条目时，就会调用该方法
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
	 * 设置每行的3个editText是否可编辑 传进来的mark是复制品，改变复制品，真品没有变化！！！
	 * 
	 * @param position
	 *            点击的行
	 * @param mark
	 *            监听点击了哪行
	 * @param editText1
	 *            设置editText是否可以输入
	 * @param editText2
	 *            设置editText是否可以输入
	 * @param editText3
	 *            设置editText是否可以输入
	 * @return mark改变后的值！！！
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
	 * 监听多头空头Spinner点击了哪行
	 * 
	 * @param position
	 *            点击的行
	 * @param mark
	 *            监听点击了哪行
	 * @return mark改变后的值！！！
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

	/** 第1行第2个Spinner的监听器 */
	class SpinnerListener2 implements OnItemSelectedListener {
		// 当用户选定了一个条目时，就会调用该方法
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			mark2 = ListennerControl2(position, mark2);
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}
	}

	/** 第2行第2一个Spinner的监听器 */
	class SpinnerListener4 implements OnItemSelectedListener {
		// 当用户选定了一个条目时，就会调用该方法
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			mark4 = ListennerControl2(position, mark4);
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}
	}

	/** 第3行第2个Spinner的监听器 */
	class SpinnerListener6 implements OnItemSelectedListener {
		// 当用户选定了一个条目时，就会调用该方法
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			mark6 = ListennerControl2(position, mark6);
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}
	}

	/** 第4行第2个Spinner的监听器 */
	class SpinnerListener8 implements OnItemSelectedListener {
		// 当用户选定了一个条目时，就会调用该方法
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			mark8 = ListennerControl2(position, mark8);
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
		}
	}

	/** 退出按钮 */
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

	/** 返回上一个界面 */
	private void goBack() {
		Intent intent = new Intent(Button3Activity.this,
				DerivativeActivity.class);
		startActivity(intent);
		finish();
	}

	/**
	 * 画图：计算输入的4个参数不为null的个数+1（已判断输入的数字是否为空）
	 * 
	 * @return 不为null的个数+1
	 */
	private int length(Object a, Object b, Object c, Object d) {
		int i = 1;
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
		return i;

	}

	/**
	 * 画图：获取每一行的名字（已判断输入的数字是否为空）
	 * 
	 * @param mark1
	 *            第一个Spinner的值
	 * @param mark2
	 *            第二个Spinner的值
	 * @param string
	 *            该行的行权价
	 * @param string1
	 *            该行的股票价格
	 * @return 不为空返回该行的名字，否则返回null
	 */
	private String getAllName(int mark1, int mark2, String string,
			String string1) {
		String result = "";
		if (mark1 == 1 && mark2 == 0 && string.length() != 0) {
			result = "(多头)行权价为" + Double.parseDouble(string) + "的认购期权";
		} else if (mark1 == 1 && mark2 == 1 && string.length() != 0) {
			result = "(空头)行权价为" + Double.parseDouble(string) + "的认购期权";
		} else if (mark1 == 2 && mark2 == 0 && string.length() != 0) {
			result = "(多头)行权价为" + Double.parseDouble(string) + "的认沽期权";
		} else if (mark1 == 2 && mark2 == 1 && string.length() != 0) {
			result = "(空头)行权价为" + Double.parseDouble(string) + "的认沽期权";
		} else if (mark1 == 3 && mark2 == 0 && string1.length() != 0) {
			result = "(多头)价格为" + Double.parseDouble(string1) + "的股票";
		} else if (mark1 == 3 && mark2 == 1 && string1.length() != 0) {
			result = "(空头)价格为" + Double.parseDouble(string1) + "的股票";
		} else {
			return null;
		}
		return result;
	}

	/**
	 * 画图：设置title的方法（已判断输入的数字是否为空）
	 * 
	 * @return
	 */
	private String[] setTitles() {
		String string1 = getAllName(mark1, mark2, test1, test3);
		String string2 = getAllName(mark3, mark4, test4, test6);
		String string3 = getAllName(mark5, mark6, test7, test9);
		String string4 = getAllName(mark7, mark8, test10, test12);
		// 每个item的title
		String[] titles = new String[length(string1, string2, string3, string4)];
		titles[0] = "组合总收益";
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
	 * 画图：设置Y轴需要的值（已判断输入的数字是否为空）
	 */
	private List<double[]> setY() {
		List<double[]> values = new ArrayList<double[]>();
		values.add(All());
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
	 * 画图：为每条线段设置颜色（已判断输入的数字是否为空）
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
	 * 画图：为每条线段设置点的形状（已判断输入的数字是否为空）
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
	 * 求Y的范围
	 * 
	 * @return
	 */
	private double maxY() {
		double result = 0.0;
		if (result1 != null) {
			if (result < arrayMax(result1)) {
				result = arrayMax(result1);
			}
		}
		if (result2 != null) {
			if (result < arrayMax(result2)) {
				result = arrayMax(result2);
			}
		}
		if (result3 != null) {
			if (result < arrayMax(result3)) {
				result = arrayMax(result3);
			}
		}
		if (result4 != null) {
			if (result < arrayMax(result4)) {
				result = arrayMax(result4);
			}
		}
		if (result < arrayMax(All())) {
			result = arrayMax(All());
		}
		return result;

	}

	private double[] X() {
		if (test1.length() != 0) {
			double d = Double.parseDouble(test1);
			java.text.DecimalFormat df = new java.text.DecimalFormat("#0.0");
			String string = df.format(d);
			d = Double.parseDouble(string);
			return new double[] { 0.25 * d, 0.5 * d, 0.75 * d, d, 1.25 * d,
					1.5 * d, 1.75 * d, 2 * d, 2.25 * d, 2.5 * d };
		}
		if (test4.length() != 0) {
			double d = Double.parseDouble(test4);
			java.text.DecimalFormat df = new java.text.DecimalFormat("#0.0");
			String string = df.format(d);
			d = Double.parseDouble(string);
			return new double[] { 0.25 * d, 0.5 * d, 0.75 * d, d, 1.25 * d,
					1.5 * d, 1.75 * d, 2 * d, 2.25 * d, 2.5 * d };
		}
		if (test7.length() != 0) {
			double d = Double.parseDouble(test7);
			java.text.DecimalFormat df = new java.text.DecimalFormat("#0.0");
			String string = df.format(d);
			d = Double.parseDouble(string);
			return new double[] { 0.25 * d, 0.5 * d, 0.75 * d, d, 1.25 * d,
					1.5 * d, 1.75 * d, 2 * d, 2.25 * d, 2.5 * d };
		}
		if (test10.length() != 0) {
			double d = Double.parseDouble(test10);
			java.text.DecimalFormat df = new java.text.DecimalFormat("#0.0");
			String string = df.format(d);
			d = Double.parseDouble(string);
			return new double[] { 0.25 * d, 0.5 * d, 0.75 * d, d, 1.25 * d,
					1.5 * d, 1.75 * d, 2 * d, 2.25 * d, 2.5 * d };
		}
		if (test3.length() != 0) {
			double d = Double.parseDouble(test3);
			java.text.DecimalFormat df = new java.text.DecimalFormat("#0.0");
			String string = df.format(d);
			d = Double.parseDouble(string);
			return new double[] { 0.25 * d, 0.5 * d, 0.75 * d, d, 1.25 * d,
					1.5 * d, 1.75 * d, 2 * d, 2.25 * d, 2.5 * d };
		}
		if (test6.length() != 0) {
			double d = Double.parseDouble(test6);
			java.text.DecimalFormat df = new java.text.DecimalFormat("#0.0");
			String string = df.format(d);
			d = Double.parseDouble(string);
			return new double[] { 0.25 * d, 0.5 * d, 0.75 * d, d, 1.25 * d,
					1.5 * d, 1.75 * d, 2 * d, 2.25 * d, 2.5 * d };
		}
		if (test9.length() != 0) {
			double d = Double.parseDouble(test9);
			java.text.DecimalFormat df = new java.text.DecimalFormat("#0.0");
			String string = df.format(d);
			d = Double.parseDouble(string);
			return new double[] { 0.25 * d, 0.5 * d, 0.75 * d, d, 1.25 * d,
					1.5 * d, 1.75 * d, 2 * d, 2.25 * d, 2.5 * d };
		}
		if (test12.length() != 0) {
			double d = Double.parseDouble(test12);
			java.text.DecimalFormat df = new java.text.DecimalFormat("#0.0");
			String string = df.format(d);
			d = Double.parseDouble(string);
			return new double[] { 0.25 * d, 0.5 * d, 0.75 * d, d, 1.25 * d,
					1.5 * d, 1.75 * d, 2 * d, 2.25 * d, 2.5 * d };
		}
		return null;

	}

	/**
	 * 画图函数
	 */
	public class LineChar extends AbstractDemoChart {
		public String getName() {
			return "期权组合损益分布图";
		}

		public String getDesc() {
			return "期权组合损益分布图";
		}

		public Intent execute(Context context) {
			// 准备数据：
			String[] titles = setTitles();
			// x轴的值
			List<double[]> x = new ArrayList<double[]>();
			for (int i = 0; i < titles.length; i++) {
				x.add(X());// 每个序列中点的X坐标
			}
			// y轴的值
			List<double[]> values = setY();
			// 每个序列的颜色设置
			int[] colors = setColor();
			// 为每条线段设置点的形状
			PointStyle[] styles = setPoint();

			XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);// 调用AbstractDemoChart中的方法设置renderer.
			int length = renderer.getSeriesRendererCount();
			// 点是空心还是实心
			for (int i = 0; i < length; i++) {
				((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
						.setFillPoints(true);// 设置图上的点为实心
			}
			// 最后两个参数代表轴的颜色和轴标签的颜色
			setChartSettings(renderer, "期权组合损益分布图", "行权价格", "组合收益", 0.5, 12.5,
					-10, 40, Color.LTGRAY, Color.LTGRAY);
			renderer.setXLabels(12);// 设置x轴显示12个点,根据setChartSettings的最大值和最小值自动计算点的间隔
			renderer.setYLabels(10);// 设置y轴显示10个点,根据setChartSettings的最大值和最小值自动计算点的间隔
			renderer.setShowGrid(true);// 是否显示网格
			// x或y轴上数字的方向，相反的。
			renderer.setXLabelsAlign(Align.RIGHT);// 刻度线与刻度标注之间的相对位置关系
			renderer.setYLabelsAlign(Align.CENTER);// 刻度线与刻度标注之间的相对位置关系、

			renderer.setPanLimits(new double[] { -10, arrayMax(X())+10, -maxY() - 10,
					maxY() + 10 }); // 设置拖动时X轴Y轴允许的最大值最小值.
			Intent intent = ChartFactory.getLineChartIntent(context,
					buildDataset(titles, x, values), renderer, "期权组合损益分布图");// 构建Intent
			return intent;
		}
	}

}