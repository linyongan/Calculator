package com.linyongan.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.linyongan.cofig.Constants;
import com.linyongan.sql.QuestionDbManger;

/**
 * ��������ҳ��
 */
public class QuestionActivity extends Activity {
	/** ���ذ�ť */
	private ImageButton backButton;
	private ListView listView;
	private QuestionDbManger dbManger;
	private String[] strings = new String[12];
	private int i = 0;

	private ArrayAdapter<String> adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.question);
		listView = (ListView) findViewById(R.id.question_listview);
		dbManger = new QuestionDbManger(this);
		getQuestion();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				popupView(position);
			}
		});
		backButton = (ImageButton) findViewById(R.id.question_back_bn);
		backButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				goBack();

			}
		});
	}

	/**
	 * ��ȡ����
	 */
	private void getQuestion() {
		dbManger.open();
		Cursor cursor = dbManger.searchQuestion();
		if (cursor.moveToFirst()) {
			do {
				String string1 = cursor.getString(cursor
						.getColumnIndex(Constants.QuestionTable.QUESTION));
				System.out.println("--string:-- " + string1);
				strings[i] = string1;
				i++;
			} while (cursor.moveToNext());

		}
		adapter = new ArrayAdapter<String>(this,
				R.layout.question_item, strings);
		listView.setAdapter(adapter);
		dbManger.close();
	}

	/**
	 * ��������
	 * 
	 * @param position
	 */
	private void popupView(int position) {
		View root = getLayoutInflater().inflate(R.layout.question_popup, null);
		// ����PopupWindow����
		final PopupWindow popup = new PopupWindow(root,
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
		popup.setBackgroundDrawable(new BitmapDrawable());
		// ��PopupWindow��ʾ��ָ��λ��
		popup.showAtLocation(findViewById(R.id.question_listview), Gravity.CENTER, 0,
				0);
		TextView textView;
		textView = (TextView) root.findViewById(R.id.question_show_tv);
		String string = adapter.getItem(position).toString();
		dbManger.open();
		Cursor cursor = dbManger.search(string);
		if (cursor.moveToFirst()) {
			String string1 = cursor.getString(cursor
					.getColumnIndex(Constants.QuestionTable.ANSWER));
			System.out.println("--string1:-- " + string1);
			textView.setText(string1);
		}
		dbManger.close();
		textView.setMovementMethod(ScrollingMovementMethod.getInstance()); 
		// ��ȡPopupWindow�еĹرհ�ť��
		root.findViewById(R.id.question_closeButton).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						// �ر�PopupWindow
						popup.dismiss(); // ��
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
		Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}