package com.linyongan.test;


import com.linyongan.activity.MainActivity;
import com.linyongan.activity.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * �������ҳ��
 */
public class LoanActivity extends Activity {
	/** ���ذ�ť */
	private ImageButton backButton;
	/** ��հ�ť */
	private ImageButton cleanButton;
	/** ���㰴ť */
	private Button calculateButton;
	/** �����(�����) */
	private EditText principal_et;
	/** ��������(�����) */
	private EditText month_et;
	/** ������(�����) */
	private EditText monthRate_et;
	/** ���(����) */
	private TextView out_tv;
	/** ���ʽ */
	private RadioGroup radioGroup;
	/** �������� */
	private int month;
	/** ����� */
	private Double principal;
	/** ������ */
	private Double monthRate;
	/**
	 * ���1���ȶϢ 2���ȶ��
	 */
	private int mark = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.loan);
		// �ҵ����е�Button
		calculateButton = (Button) findViewById(R.id.loan_calculate_bt);
		calculateButton.setOnClickListener(new ButtonListener());
		backButton = (ImageButton) findViewById(R.id.loan_back_bn);
		backButton.setOnClickListener(new ButtonListener());
		cleanButton = (ImageButton) findViewById(R.id.loan_clean_bn);
		cleanButton.setOnClickListener(new ButtonListener());
		// �ҵ����е�EditText
		principal_et = (EditText) findViewById(R.id.loan_principal_et);
		month_et = (EditText) findViewById(R.id.loan_month_et);
		monthRate_et = (EditText) findViewById(R.id.loan_yearRate_et);
		// �ҵ����е�TextView
		out_tv = (TextView) findViewById(R.id.loan_out_tv);
		// �ҵ����е�RadioGroup
		radioGroup = (RadioGroup) findViewById(R.id.loan_RadioGroup);
		radioGroup.setOnCheckedChangeListener(new radioGroupListener());
	}

	private class ButtonListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.loan_calculate_bt:
				// ��ȡ���������֣�������ȡֵ������
				String Sprincipal = principal_et.getText().toString();
				String Smonth = month_et.getText().toString();
				String SmonthRate = monthRate_et.getText().toString();

				if (mark == 1 && Sprincipal.length() != 0
						&& Smonth.length() != 0 && SmonthRate.length() != 0) {
					month = Integer.parseInt(Smonth);
					principal = Double.parseDouble(Sprincipal);
					monthRate = Double.parseDouble(SmonthRate);
					monthRate = monthRate / 12;
					Double a = calculateRepayment(principal, month, monthRate);
					Double b = calculateInterest(a, month, principal);
					out_tv.setText("��������\nÿ�»���" + String.format("%.2f", a)+"Ԫ"
							+ "\n�ۼ���Ϣ��" + String.format("%.2f", b)+"Ԫ");
				} else if (mark == 2 && Sprincipal.length() != 0
						&& Smonth.length() != 0 && SmonthRate.length() != 0) {
					month = Integer.parseInt(Smonth);
					principal = Double.parseDouble(Sprincipal);
					monthRate = Double.parseDouble(SmonthRate);
					monthRate = monthRate / 12;
					Double[] a1 = calculateRepayment2(principal, month,
							monthRate);
					Double b = calculateInterest2(principal, a1, month);
					String c = "";
					for (int i = 0; i < month; i++) {
						c = c + "��" + (i + 1) + "���»���"
								+ String.format("%.2f", a1[i]) + "Ԫ\n";
					}
					out_tv.setText("��������\n" + "�ۼ���Ϣ��"
							+ String.format("%.2f", b) + "Ԫ\n" + c);
				} else {
					Toast.makeText(LoanActivity.this, "�������ֲ���Ϊ�գ�",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.loan_back_bn:
				goBack();
				break;
			case R.id.loan_clean_bn:
				cleanEditText();
				break;

			}
		}

	}

	/** ѡ��������͵�RadioGroup�ļ����¼� */
	private class radioGroupListener implements
			RadioGroup.OnCheckedChangeListener {

		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.loan_RadioButton1:
				out_tv.setText("");
				mark = 1;
				break;
			case R.id.loan_RadioButton2:
				out_tv.setText("");
				mark = 2;
				break;
			}

		}

	}

	/**
	 * �ȶϢ,����ÿ�»����
	 * 
	 * @param principal
	 * @param month
	 * @param monthRate
	 * @return
	 */
	private Double calculateRepayment(Double principal, int month,
			Double monthRate) {
		Double result, a;
		a = Math.pow(1 + monthRate, month);
		result = principal * a * monthRate / (a - 1);
		return result;
	}

	/**
	 * �ȶϢ,�����ۼ���Ϣ
	 * 
	 * @param repayment
	 * @param month
	 * @param principal
	 * @return
	 */
	private Double calculateInterest(Double repayment, int month,
			Double principal) {
		Double result;
		result = repayment * month - principal;
		return result;
	}

	/**
	 * �ȶ��,����ÿ�»����
	 * 
	 * @param principal
	 * @param month
	 * @param monthRate
	 * @return
	 */
	private Double[] calculateRepayment2(Double principal, int month,
			Double monthRate) {
		Double[] result = new Double[month];
		for (int i = 0; i < month; i++) {
			result[i] = principal / month + monthRate
					* (principal - principal * i / month);
		}
		return result;
	}

	/**
	 * �ȶ��,�����ۼ���Ϣ
	 * 
	 * @param repayment
	 * @param a
	 * @param month
	 * @return
	 */
	private Double calculateInterest2(Double principal, Double[] a, int month) {
		Double b = 0.0;
		for (int i = 0; i < month; i++) {
			b = b + a[i];
		}
		return b - principal;
	}

	/**
	 * ��պ���
	 */
	private void cleanEditText() {
		principal_et.setText("");
		month_et.setText("");
		monthRate_et.setText("");
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
		Intent intent = new Intent(LoanActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}