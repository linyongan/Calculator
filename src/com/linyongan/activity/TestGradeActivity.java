package com.linyongan.activity;

import com.linyongan.cofig.Constants;
import com.linyongan.sql.GradeDbManger;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * ���Գɼ�����
 */
public class TestGradeActivity extends Activity {
	/** ���ذ�ť */
	private ImageButton backButton;

	private GradeDbManger dbManger;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test_grade);

		dbManger = new GradeDbManger(this);
		ListView listView = (ListView) findViewById(R.id.test_grade_listview);
		dbManger.open();
		Cursor cursor = dbManger.searchGrade();
		ListAdapter listAdapter = new SimpleCursorAdapter(this,
				R.layout.test_grade_item, cursor, new String[] {
						Constants.GradeTable.TIME, Constants.GradeTable.NAME,
						Constants.GradeTable.GRADE }, new int[] {
						R.id.test_grade_item_time, R.id.test_grade_item_name,
						R.id.test_grade_item_grade });
		listView.setAdapter(listAdapter);
		dbManger.close();

		backButton = (ImageButton) findViewById(R.id.test_grade_back_bn);
		backButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				goBack();

			}
		});
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
		Intent intent = new Intent(TestGradeActivity.this, TestActivity.class);
		startActivity(intent);
		finish();
	}
}