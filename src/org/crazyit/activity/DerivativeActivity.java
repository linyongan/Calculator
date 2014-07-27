package org.crazyit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * ��������Ʒ����ҳ��
 */
public class DerivativeActivity extends Activity {
	/** ���ذ�ť */
	private ImageButton backButton;
	private Spinner spinner1;
	/** ������Ȩ���ۼ۸�Ĳ��� */
	private LinearLayout layout1;
	/** ������Ȩ��������Ĳ��� */
	private LinearLayout layout2;
	/** �ּۣ����֣�(������Ȩ���ۼ۸�) */
	private TextView presentValue_tv1;
	/** �ּۣ������(������Ȩ���ۼ۸�) */
	private EditText presentValue_et1;
	private RadioGroup radioGroup1;
	private RadioGroup radioGroup2;
    private TableRow tableRow1;
    private TableRow tableRow2;
    private TableRow tableRow3;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.derivative);
		tableRow1 = (TableRow) findViewById(R.id.derivative_tableRow1);
		tableRow2 = (TableRow) findViewById(R.id.derivative_tableRow2);
		tableRow3 = (TableRow) findViewById(R.id.derivative_tableRow3);
		// �ҵ����е�TextView
		radioGroup1 = (RadioGroup) findViewById(R.id.derivative_radioGroup1);
		radioGroup2 = (RadioGroup) findViewById(R.id.derivative_radioGroup2);
		radioGroup2.setOnCheckedChangeListener(new radioGroupListener());
		// �ҵ����е�TextView
		presentValue_tv1 = (TextView) findViewById(R.id.derivative_presentValue_tv1);
		// �ҵ����е�EditText
		presentValue_et1 = (EditText) findViewById(R.id.derivative_presentValue_et1);
		// �ҵ����е�spinner
		layout1 = (LinearLayout) findViewById(R.id.derivative_layout1);
		layout2 = (LinearLayout) findViewById(R.id.derivative_layout2);
		// �ҵ����е�spinner
		spinner1 = (Spinner) findViewById(R.id.derivative_spinner1);
		spinner1.setOnItemSelectedListener(new SpinnerListener());
		// �ҵ����е�button
		backButton = (ImageButton) findViewById(R.id.derivative_back_bn);
		backButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				goBack();

			}
		});
	}

	private class SpinnerListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			switch (position) {
			case 0:
				layout2.setVisibility(View.GONE);
				layout1.setVisibility(View.VISIBLE);
				presentValue_tv1.setVisibility(View.GONE);
				presentValue_et1.setVisibility(View.GONE);
				break;
			case 1:
				layout1.setVisibility(View.GONE);
				layout2.setVisibility(View.VISIBLE);
				break;
			case 2:
				layout1.setVisibility(View.GONE);
				layout2.setVisibility(View.GONE);
				break;
			case 3:
				layout1.setVisibility(View.GONE);
				layout2.setVisibility(View.GONE);
				break;
			case 4:
				layout2.setVisibility(View.GONE);
				layout1.setVisibility(View.VISIBLE);
				radioGroup1.setVisibility(View.GONE);
				presentValue_tv1.setVisibility(View.VISIBLE);
				presentValue_et1.setVisibility(View.VISIBLE);
				break;
			}

		}

		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}

	}

	/** ѡ�������͵�RadioGroup�ļ����¼� */
	private class radioGroupListener implements
			RadioGroup.OnCheckedChangeListener {

		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.derivative_radioButton1:
				tableRow2.setVisibility(View.GONE);
				tableRow1.setVisibility(View.VISIBLE);
				tableRow3.setVisibility(View.VISIBLE);
				break;
			case R.id.derivative_radioButton2:
				tableRow2.setVisibility(View.GONE);
				tableRow1.setVisibility(View.VISIBLE);
				tableRow3.setVisibility(View.VISIBLE);
				break;
			case R.id.derivative_radioButton3:
				tableRow2.setVisibility(View.VISIBLE);
				tableRow1.setVisibility(View.GONE);
				tableRow3.setVisibility(View.GONE);
				break;
			}
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
		Intent intent = new Intent(DerivativeActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}