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
 * 期权学堂页面
 */
public class LearnActivity extends ExpandableListActivity {
	/** 返回按钮 */
	private ImageButton backButton;
	private int[] imageResId; // 图片ID
	private int[] dots; // 图片标题正文的那些点
	// 配置中间课展开列表需要的信息
	// 定义省份数组
	private String[] item = new String[] { "基础课程", "进阶课程(一)", "进阶课程(二)" };
	private String[][] subItem = new String[][] {
			{ "第一节 期权的历史与现状", "第二节 期权的基本概念", "第三节 认购期权的基本概念", "第四节 认沽期权的基本概念",
					"第五节 个股期权的实值、平值和虚值", "第六节 个股期权的内在价值与时间价值",
					"第七节 个股期权的基本特征及重要术语", "第八节 个股期权与权证、期货的主要区别", "第九节 个股期权的功能",
					"第十节 个股期权合约的基本要素", "第十一节 个股期权价值变动的影响因素", "第十二节 个股期权的风险" },
			{ "第一节 备兑开仓策略介绍及运作原理", "第二节 备兑开仓策略应用指南", "第三节 备兑开仓的风险",
					"第四节 买入股票期权的简单交易策略", "第五节 以风险对冲为目的的基本策略介绍，损益及风险" },
			{ "第一节 卖出开仓的运作原理", "第二节 卖出股票期权的简单交易策略", "第三节 牛市行情期权交易策略",
					"第四节 熊市行情期权交易策略", "第五节 盘整行情期权交易策略" } };

	private ExpandableListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.learn);

		adapter = new ExpandableListAdapter(LearnActivity.this, item, subItem);
		// 设置该窗口显示列表
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
		Intent intent = new Intent(LearnActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}