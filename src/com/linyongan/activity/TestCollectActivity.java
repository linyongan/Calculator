package com.linyongan.activity;

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

import com.linyongan.cofig.Constants;
import com.linyongan.model.Test;
import com.linyongan.sql.TestDbManger;
import com.linyongan.view.MyListView;
import com.linyongan.view.TitleView1;
import com.linyongan.view.MyListView.DelButtonClickListener;

/**
 * 测试难题收集界面
 */
public class TestCollectActivity extends Activity {
	/** 标题 */
	private TitleView1 titleView;
	private TestDbManger dbManger;
	private CursorAdapter listAdapter;
	private MyListView listView;
	private Cursor cursor;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.test_collect);
		dbManger = new TestDbManger(this);
		listView = (MyListView) findViewById(R.id.test_collect_listview);
		listView.setDelButtonClickListener(new DelButtonClickListener() {
			@Override
			public void clickHappend(int position) {
				// 查找到当前的item
				View view = listView.getChildAt(position);
				TextView textview = (TextView) view
						.findViewById(R.id.test_collect_item_id);
				// 查找到当前是第几题
				int i = Integer.parseInt(textview.getText().toString());
				// 更改数据库的数据，取消收藏
				dbManger.open();
				Test test = new Test();
				test.set_id(i);
				test.setMark("0");
				dbManger.updateMark(test);
				// 从数据库重新获取数据，更新listview
				cursor = dbManger.getCollect();
				listAdapter.changeCursor(cursor);
				dbManger.close();
			}

		});
		// 从数据库查找数据，显示在listView上
		dbManger.open();
		cursor = dbManger.getCollect();
		listAdapter = new SimpleCursorAdapter(this, R.layout.test_collect_item,
				cursor, new String[] { Constants.TestTable.ID,
						Constants.TestTable.QUESTION,
						Constants.TestTable.ANSWER }, new int[] {
						R.id.test_collect_item_id,
						R.id.test_collect_item_question,
						R.id.test_collect_item_answer });
		listView.setAdapter(listAdapter);
		dbManger.close();

		titleView = (TitleView1) findViewById(R.id.TitleView1);
		titleView.setTitleText("难题收藏");
		titleView.setLeftButtonListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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