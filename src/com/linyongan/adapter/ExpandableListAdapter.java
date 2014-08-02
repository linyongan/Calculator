package com.linyongan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context context;
	/** ��Ŀ */
	private String[] item = new String[] {};
	/** ����Ŀ */
	private String[][] subItem = new String[][] {};

	public ExpandableListAdapter(Context context, String[] item,
			String[][] subItem) {
		this.context = context;
		this.item = item;
		this.subItem = subItem;
	}

	// ��ȡָ����λ�á�ָ�����б�������б�������

	public Object getChild(int groupPosition, int childPosition) {
		return subItem[groupPosition][childPosition];
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public int getChildrenCount(int groupPosition) {
		return subItem[groupPosition].length;
	}

	private TextView getTextView() {
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 60);
		TextView textView = new TextView(context);
		textView.setLayoutParams(lp);
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		textView.setPadding(10, 0, 0, 0);
		textView.setTextColor(Color.BLACK);
		textView.setTextSize(16);
		return textView;
	}

	// �÷�������ÿ����ѡ������
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		TextView textView = getTextView();
		textView.setText(getChild(groupPosition, childPosition).toString());
		return textView;
	}

	// ��ȡָ����λ�ô���������
	public Object getGroup(int groupPosition) {
		return item[groupPosition];
	}

	public int getGroupCount() {
		return item.length;
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	// �÷�������ÿ����ѡ������
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		LinearLayout ll = new LinearLayout(context);
		ll.setOrientation(0);
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 50);
		TextView textView = new TextView(context);
		textView.setLayoutParams(lp);
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		textView.setPadding(50, 0, 0, 0);
		textView.setTextColor(Color.BLACK);
		textView.setTextSize(16);
		textView.setText(getGroup(groupPosition).toString());
		textView.setBackgroundColor(Color.LTGRAY);
		ll.addView(textView);
		return ll;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public boolean hasStableIds() {
		return true;
	}
}
