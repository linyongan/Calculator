package com.linyongan.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.linyongan.cofig.Constants;
import com.linyongan.sortlistview.CharacterParser;
import com.linyongan.sortlistview.ClearEditText;
import com.linyongan.sortlistview.PinyinComparator;
import com.linyongan.sortlistview.SideBar;
import com.linyongan.sortlistview.SideBar.OnTouchingLetterChangedListener;
import com.linyongan.sortlistview.SortAdapter;
import com.linyongan.sortlistview.SortModel;
import com.linyongan.sql.NounDbManger;

/**
 * ���ʲ�ѯҳ��
 */
public class SearchActivity extends Activity {
	/** ���ذ�ť */
	private ImageButton backButton;
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortAdapter adapter;
	private ClearEditText mClearEditText;

	/**
	 * ����ת����ƴ������
	 */
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;

	/**
	 * ����ƴ��������ListView�����������
	 */
	private PinyinComparator pinyinComparator;

	private NounDbManger nounDbManger;
	private TextView textView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search);
		nounDbManger = new NounDbManger(this);
		initViews();

		backButton = (ImageButton) findViewById(R.id.search_back_bn);
		backButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				goBack();

			}
		});
	}

	private void initViews() {
		// ʵ��������תƴ����
		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);

		// �����Ҳഥ������
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			public void onTouchingLetterChanged(String s) {
				// ����ĸ�״γ��ֵ�λ��
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					sortListView.setSelection(position);
				}

			}
		});

		sortListView = (ListView) findViewById(R.id.country_lvcountry);
		sortListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// ����Ҫ����adapter.getItem(position)����ȡ��ǰposition����Ӧ�Ķ���
				// Toast.makeText(getApplication(),
				// ((SortModel) adapter.getItem(position)).getName(),
				// Toast.LENGTH_SHORT).show();
				// װ��R.layout.popup��Ӧ�Ľ��沼��
				View root = getLayoutInflater().inflate(R.layout.popup, null);
				// ����PopupWindow����
				final PopupWindow popup = new PopupWindow(root,
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,
						true);
				popup.setBackgroundDrawable(new BitmapDrawable());
				// ��PopupWindow��ʾ��ָ��λ��
				popup.showAtLocation(findViewById(R.id.filter_edit),
						Gravity.CENTER, 0, 0);
				textView = (TextView) root.findViewById(R.id.search_show_tv);
				String string = ((SortModel) adapter.getItem(position))
						.getName();
				nounDbManger.open();
				Cursor cursor = nounDbManger.search(string);
				if (cursor.moveToFirst()) {
					String string1 = cursor.getString(cursor
							.getColumnIndex(Constants.NounTable.VALUE));
					System.out.println("--string:-- " + string1);
					textView.setText(string1);
				}
				nounDbManger.close();
				// ��ȡPopupWindow�еĹرհ�ť��
				root.findViewById(R.id.search_closeButton).setOnClickListener(
						new View.OnClickListener() {
							public void onClick(View v) {
								// �ر�PopupWindow
								popup.dismiss(); // ��
							}
						});

			}
		});

		SourceDateList = filledData(getResources().getStringArray(R.array.date));

		// ����a-z��������Դ����
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortAdapter(this, SourceDateList);
		sortListView.setAdapter(adapter);

		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

		// �������������ֵ�ĸı�����������
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// ������������ֵΪ�գ�����Ϊԭ�����б�����Ϊ���������б�
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	/**
	 * ΪListView�������
	 * 
	 * @param date
	 * @return
	 */
	private List<SortModel> filledData(String[] date) {
		List<SortModel> mSortList = new ArrayList<SortModel>();

		for (int i = 0; i < date.length; i++) {
			SortModel sortModel = new SortModel();
			sortModel.setName(date[i]);
			// ����ת����ƴ��
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}

			mSortList.add(sortModel);
		}
		return mSortList;

	}

	/**
	 * ����������е�ֵ���������ݲ�����ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<SortModel> filterDateList = new ArrayList<SortModel>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = SourceDateList;
		} else {
			filterDateList.clear();
			for (SortModel sortModel : SourceDateList) {
				String name = sortModel.getName();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// ����a-z��������
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
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
		Intent intent = new Intent(SearchActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}