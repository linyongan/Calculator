package com.linyongan.activity;

import com.linyongan.cofig.Constants;
import com.linyongan.sql.GradeDbManger;
import com.linyongan.view.MyListView;
import com.linyongan.view.TitleView1;
import com.linyongan.view.MyListView.DelButtonClickListener;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * ���Գɼ�����
 */
public class TestGradeActivity extends Activity {
	/** ���� */
	private TitleView1 titleView;

	private GradeDbManger dbManger;
	private CursorAdapter listAdapter;
	private MyListView listView;
	private Cursor cursor;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test_grade);

		dbManger = new GradeDbManger(this);
		listView = (MyListView) findViewById(R.id.test_grade_listview);
		listView.setDelButtonClickListener(new DelButtonClickListener() {
			@Override
			public void clickHappend(int position) {
				// ���ҵ���ǰ��item
				View view = listView.getChildAt(position);
				TextView textview = (TextView) view
						.findViewById(R.id.test_grade_item_time);
				// ���ҵ���ǰitem��ʱ��
				String string = textview.getText().toString();
				// �������ݿ�����ݣ�ɾ���ɼ�
				dbManger.open();
				dbManger.delete(string);
				// �����ݿ����»�ȡ���ݣ�����listview
				cursor = dbManger.searchGrade();
				listAdapter.changeCursor(cursor);
				dbManger.close();
			}

		});

		dbManger.open();
		cursor = dbManger.searchGrade();
		listAdapter = new SimpleCursorAdapter(
				this,
				R.layout.test_grade_item,
				cursor,
				new String[] { Constants.GradeTable.TIME,
						Constants.GradeTable.NAME, Constants.GradeTable.GRADE },
				new int[] { R.id.test_grade_item_time,
						R.id.test_grade_item_name, R.id.test_grade_item_grade });
		listView.setAdapter(listAdapter);
		dbManger.close();

		titleView = (TitleView1) findViewById(R.id.TitleView1);
		titleView.setTitleText("�ɼ���ѯ");
		titleView.setLeftButtonListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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