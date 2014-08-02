package com.linyongan.activity;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageButton;

import com.linyongan.adapter.ExpandableListAdapter;

/**
 * ��Ȩѧ��ҳ��
 */
public class LearnActivity extends ExpandableListActivity {
	/** ���ذ�ť */
	private ImageButton backButton;
	private int[] imageResId; // ͼƬID
	private int[] dots; // ͼƬ�������ĵ���Щ��
	// �����м��չ���б���Ҫ����Ϣ
	// ����ʡ������
	private String[] item = new String[] { "�����γ�", "���׿γ�(һ)", "���׿γ�(��)" };
	private String[][] subItem = new String[][] {
			{ "��һ�� ��Ȩ����ʷ����״", "�ڶ��� ��Ȩ�Ļ�������", "������ �Ϲ���Ȩ�Ļ�������", "���Ľ� �Ϲ���Ȩ�Ļ�������",
					"����� ������Ȩ��ʵֵ��ƽֵ����ֵ", "������ ������Ȩ�����ڼ�ֵ��ʱ���ֵ",
					"���߽� ������Ȩ�Ļ�����������Ҫ����", "�ڰ˽� ������Ȩ��Ȩ֤���ڻ�����Ҫ����", "�ھŽ� ������Ȩ�Ĺ���",
					"��ʮ�� ������Ȩ��Լ�Ļ���Ҫ��", "��ʮһ�� ������Ȩ��ֵ�䶯��Ӱ������", "��ʮ���� ������Ȩ�ķ���" },
			{ "��һ�� ���ҿ��ֲ��Խ��ܼ�����ԭ��", "�ڶ��� ���ҿ��ֲ���Ӧ��ָ��", "������ ���ҿ��ֵķ���",
					"���Ľ� �����Ʊ��Ȩ�ļ򵥽��ײ���", "����� �Է��նԳ�ΪĿ�ĵĻ������Խ��ܣ����漰����" },
			{ "��һ�� �������ֵ�����ԭ��", "�ڶ��� ������Ʊ��Ȩ�ļ򵥽��ײ���", "������ ţ��������Ȩ���ײ���",
					"���Ľ� ����������Ȩ���ײ���", "����� ����������Ȩ���ײ���" } };

	private ExpandableListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.learn);

		adapter = new ExpandableListAdapter(LearnActivity.this, item, subItem);
		// ���øô�����ʾ�б�
		setListAdapter(adapter);
		getExpandableListView().setOnChildClickListener(
				new ExpandableListViewListener());

		backButton = (ImageButton) findViewById(R.id.learn_back_bn);
		backButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				goBack();

			}
		});

	}

	private class ExpandableListViewListener implements OnChildClickListener {

		@Override
		public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
			switch (groupPosition) {
			case 0:
				Intent intent = new Intent(LearnActivity.this,
						ViewPagerActivity.class);
				imageResId = new int[] { R.drawable.a, R.drawable.b,
						R.drawable.c, R.drawable.d };
				dots = new int[] { R.id.v_dot0, R.id.v_dot1, R.id.v_dot2,
						R.id.v_dot3 };
				Bundle b = new Bundle();
				b.putIntArray("imageResId", imageResId);
				b.putIntArray("dots", dots);
				intent.putExtras(b);
				startActivity(intent);
				break;
			}
			return false;
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
		Intent intent = new Intent(LearnActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}