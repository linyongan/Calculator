package com.linyongan.activity;

import com.linyongan.cofig.Constants;
import com.linyongan.sql.TestDbManger;

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
 * 测试难题收集界面
 */
public class TestCollectActivity extends Activity {
	/** 返回按钮 */
	private ImageButton backButton;
	private TestDbManger dbManger;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test_collect);
		dbManger = new TestDbManger(this);
		ListView listView = (ListView)findViewById(R.id.test_collect_listview);
		dbManger.open();
		Cursor cursor = dbManger.getCollect();
		ListAdapter listAdapter = new SimpleCursorAdapter(this,
				R.layout.test_collect_item, cursor, new String[] {
						Constants.TestTable.ID, Constants.TestTable.QUESTION,
						Constants.TestTable.ANSWER }, new int[] {
						R.id.test_collect_item_id,
						R.id.test_collect_item_question,
						R.id.test_collect_item_answer });
		listView.setAdapter(listAdapter);
		dbManger.close();

		backButton = (ImageButton) findViewById(R.id.test_collect_back_bn);
		backButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				goBack();
			}
		});
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
		Intent intent = new Intent(TestCollectActivity.this, TestActivity.class);
		startActivity(intent);
		finish();
	}
}