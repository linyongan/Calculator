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
import com.linyongan.adapter.ExpandableListAdapter;
import com.linyongan.view.TitleView1;

/**
 * 期权学堂页面
 */
public class LearnActivity extends ExpandableListActivity {
	/** 标题 */
	private TitleView1 titleView;
	/** 图片ID */
	private int[] imageResId;
	/** 图片标题正文的那些点 */
	private int[] dots;
	private int[] Dots = new int[] { R.id.v_dot0, R.id.v_dot1, R.id.v_dot2,
			R.id.v_dot3, R.id.v_dot4, R.id.v_dot5, R.id.v_dot6, R.id.v_dot7,
			R.id.v_dot8, R.id.v_dot9, R.id.v_dot10, R.id.v_dot11, R.id.v_dot12,
			R.id.v_dot13, R.id.v_dot14, R.id.v_dot15, R.id.v_dot16,
			R.id.v_dot17, R.id.v_dot18, R.id.v_dot19 };

	// 配置中间课展开列表需要的信息
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
	private Intent intent;

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
		titleView = (TitleView1) findViewById(R.id.TitleView1);
		titleView.setTitleText("期权学堂");
		titleView.setLeftButtonListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goBack();
			}
		});
	}

	/**
	 * 设置图片的ID，然后调整到相应的页面
	 * 
	 * @author yongan
	 * 
	 */
	private class ExpandableListViewListener implements OnChildClickListener {

		@Override
		public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
			intent = new Intent(LearnActivity.this, ViewPagerActivity.class);
			switch (groupPosition) {
			case 0:
				switch (childPosition) {
				case 0:
					imageResId = new int[] { R.drawable.base0101,
							R.drawable.base0102, R.drawable.base0103,
							R.drawable.base0104, R.drawable.base0105,
							R.drawable.base0106, R.drawable.base0107,
							R.drawable.base0108, R.drawable.base0109,
							R.drawable.base0110, R.drawable.base0111,
							R.drawable.base0112, R.drawable.base0113,
							R.drawable.base0114, R.drawable.base0115,
							R.drawable.base0116, R.drawable.base0117,
							R.drawable.base0118, R.drawable.base0119 };
					break;
				case 1:
					imageResId = new int[] { R.drawable.base0201,
							R.drawable.base0202, R.drawable.base0203,
							R.drawable.base0204, R.drawable.base0205,
							R.drawable.base0206, R.drawable.base0207,
							R.drawable.base0208, R.drawable.base0209,
							R.drawable.base0210, R.drawable.base0211,
							R.drawable.base0212, R.drawable.base0213,
							R.drawable.base0214, R.drawable.base0215,
							R.drawable.base0216 };
					break;
				case 2:
					imageResId = new int[] { R.drawable.base0301,
							R.drawable.base0302, R.drawable.base0303,
							R.drawable.base0304, R.drawable.base0305,
							R.drawable.base0306, R.drawable.base0307,
							R.drawable.base0308, R.drawable.base0309,
							R.drawable.base0310, R.drawable.base0311,
							R.drawable.base0312 };
					break;
				case 3:
					imageResId = new int[] { R.drawable.base0401,
							R.drawable.base0402, R.drawable.base0403,
							R.drawable.base0404, R.drawable.base0405,
							R.drawable.base0406, R.drawable.base0407,
							R.drawable.base0408, R.drawable.base0409,
							R.drawable.base0410, R.drawable.base0411,
							R.drawable.base0412 };
					break;
				case 4:
					imageResId = new int[] { R.drawable.base0501,
							R.drawable.base0502, R.drawable.base0503,
							R.drawable.base0504, R.drawable.base0505,
							R.drawable.base0506, R.drawable.base0507,
							R.drawable.base0508, R.drawable.base0509,
							R.drawable.base0510, R.drawable.base0511,
							R.drawable.base0512 };
					break;
				case 5:
					imageResId = new int[] { R.drawable.base0601,
							R.drawable.base0602, R.drawable.base0603,
							R.drawable.base0604, R.drawable.base0605,
							R.drawable.base0606, R.drawable.base0607,
							R.drawable.base0608, R.drawable.base0609,
							R.drawable.base0610, R.drawable.base0611,
							R.drawable.base0612, R.drawable.base0613,
							R.drawable.base0614, R.drawable.base0615,
							R.drawable.base0616, R.drawable.base0617,
							R.drawable.base0618, R.drawable.base0619 };
					break;
				case 6:
					imageResId = new int[] { R.drawable.base0701,
							R.drawable.base0702, R.drawable.base0703,
							R.drawable.base0704, R.drawable.base0705,
							R.drawable.base0706, R.drawable.base0707,
							R.drawable.base0708, R.drawable.base0709,
							R.drawable.base0710, R.drawable.base0711,
							R.drawable.base0712, R.drawable.base0713,
							R.drawable.base0714, R.drawable.base0715,
							R.drawable.base0716, R.drawable.base0717 };
					break;
				case 7:
					imageResId = new int[] { R.drawable.base0801,
							R.drawable.base0802, R.drawable.base0803,
							R.drawable.base0804, R.drawable.base0805,
							R.drawable.base0806, R.drawable.base0807,
							R.drawable.base0808, R.drawable.base0809,
							R.drawable.base0810, R.drawable.base0811,
							R.drawable.base0812, R.drawable.base0813,
							R.drawable.base0814, R.drawable.base0815 };
					break;
				case 8:
					imageResId = new int[] { R.drawable.base0901,
							R.drawable.base0902, R.drawable.base0903,
							R.drawable.base0904, R.drawable.base0905,
							R.drawable.base0906, R.drawable.base0907,
							R.drawable.base0908, R.drawable.base0909,
							R.drawable.base0910, R.drawable.base0911 };
					break;
				case 9:
					imageResId = new int[] { R.drawable.base1001,
							R.drawable.base1002, R.drawable.base1003,
							R.drawable.base1004, R.drawable.base1005,
							R.drawable.base1006, R.drawable.base1007,
							R.drawable.base1008, R.drawable.base1009,
							R.drawable.base1010, R.drawable.base1011,
							R.drawable.base1012 };
					break;
				case 10:
					imageResId = new int[] { R.drawable.base1101,
							R.drawable.base1102, R.drawable.base1103,
							R.drawable.base1104, R.drawable.base1105,
							R.drawable.base1106, R.drawable.base1107,
							R.drawable.base1108, R.drawable.base1109,
							R.drawable.base1110, R.drawable.base1111,
							R.drawable.base1112, R.drawable.base1113,
							R.drawable.base1114, R.drawable.base1115 };
					break;
				case 11:
					imageResId = new int[] { R.drawable.base1201,
							R.drawable.base1202, R.drawable.base1203,
							R.drawable.base1204, R.drawable.base1205,
							R.drawable.base1206, R.drawable.base1207,
							R.drawable.base1208, R.drawable.base1209,
							R.drawable.base1210, R.drawable.base1211,
							R.drawable.base1212 };
					break;
				}
				break;

			case 1:
				switch (childPosition) {
				case 0:
					imageResId = new int[] { R.drawable.advance0101,
							R.drawable.advance0102, R.drawable.advance0103,
							R.drawable.advance0104, R.drawable.advance0105,
							R.drawable.advance0106, R.drawable.advance0107,
							R.drawable.advance0108, R.drawable.advance0109,
							R.drawable.advance0110, R.drawable.advance0111,
							R.drawable.advance0112, R.drawable.advance0113 };
					break;
				case 1:
					imageResId = new int[] { R.drawable.advance0201,
							R.drawable.advance0202, R.drawable.advance0203,
							R.drawable.advance0204, R.drawable.advance0205,
							R.drawable.advance0206, R.drawable.advance0207,
							R.drawable.advance0208, R.drawable.advance0209,
							R.drawable.advance0210, R.drawable.advance0211,
							R.drawable.advance0212, R.drawable.advance0213,
							R.drawable.advance0214, R.drawable.advance0215 };
					break;
				case 2:
					imageResId = new int[] { R.drawable.advance0301,
							R.drawable.advance0302, R.drawable.advance0303,
							R.drawable.advance0304, R.drawable.advance0305,
							R.drawable.advance0306, R.drawable.advance0307,
							R.drawable.advance0308, R.drawable.advance0309,
							R.drawable.advance0310, R.drawable.advance0311,
							R.drawable.advance0312, R.drawable.advance0313,
							R.drawable.advance0314, R.drawable.advance0315 };
					break;
				case 3:
					imageResId = new int[] { R.drawable.advance0401,
							R.drawable.advance0402, R.drawable.advance0403,
							R.drawable.advance0404, R.drawable.advance0405,
							R.drawable.advance0406, R.drawable.advance0407,
							R.drawable.advance0408, R.drawable.advance0409,
							R.drawable.advance0410, R.drawable.advance0411,
							R.drawable.advance0412, R.drawable.advance0413,
							R.drawable.advance0414, R.drawable.advance0415 };
					break;
				case 4:
					imageResId = new int[] { R.drawable.advance0501,
							R.drawable.advance0502, R.drawable.advance0503,
							R.drawable.advance0504, R.drawable.advance0505,
							R.drawable.advance0506, R.drawable.advance0507,
							R.drawable.advance0508, R.drawable.advance0509,
							R.drawable.advance0510, R.drawable.advance0511,
							R.drawable.advance0512, R.drawable.advance0513,
							R.drawable.advance0514, R.drawable.advance0515,
							R.drawable.advance0516 };
					break;

				}
				break;
			case 2:
				switch (childPosition) {
				case 0:
					imageResId = new int[] { R.drawable.advance0601,
							R.drawable.advance0602, R.drawable.advance0603,
							R.drawable.advance0604, R.drawable.advance0605,
							R.drawable.advance0606, R.drawable.advance0607,
							R.drawable.advance0608, R.drawable.advance0609,
							R.drawable.advance0610, R.drawable.advance0611,
							R.drawable.advance0612, R.drawable.advance0613,
							R.drawable.advance0614, R.drawable.advance0615 };
					break;
				case 1:
					imageResId = new int[] { R.drawable.advance0701,
							R.drawable.advance0702, R.drawable.advance0703,
							R.drawable.advance0704, R.drawable.advance0705,
							R.drawable.advance0706, R.drawable.advance0707,
							R.drawable.advance0708, R.drawable.advance0709,
							R.drawable.advance0710, R.drawable.advance0711 };
					break;
				case 2:
					imageResId = new int[] { R.drawable.advance0801,
							R.drawable.advance0802, R.drawable.advance0803,
							R.drawable.advance0804, R.drawable.advance0805,
							R.drawable.advance0806, R.drawable.advance0807,
							R.drawable.advance0808, R.drawable.advance0809,
							R.drawable.advance0810, R.drawable.advance0811,
							R.drawable.advance0812, R.drawable.advance0813,
							R.drawable.advance0814 };
					break;
				case 3:
					imageResId = new int[] { R.drawable.advance0901,
							R.drawable.advance0902, R.drawable.advance0903,
							R.drawable.advance0904, R.drawable.advance0905,
							R.drawable.advance0906, R.drawable.advance0907,
							R.drawable.advance0908, R.drawable.advance0909,
							R.drawable.advance0910, R.drawable.advance0911,
							R.drawable.advance0912, R.drawable.advance0913,
							R.drawable.advance0914 };
					break;
				case 4:
					imageResId = new int[] { R.drawable.advance1001,
							R.drawable.advance1002, R.drawable.advance1003,
							R.drawable.advance1004, R.drawable.advance1005,
							R.drawable.advance1006, R.drawable.advance1007,
							R.drawable.advance1008, R.drawable.advance1009,
							R.drawable.advance1010, R.drawable.advance1011,
							R.drawable.advance1012, R.drawable.advance1013,
							R.drawable.advance1014 };
					break;
				}
				break;
			}
			dots = new int[imageResId.length];
			for (int i = 0; i < imageResId.length; i++) {
				dots[i] = Dots[i];
			}
			Bundle b = new Bundle();
			b.putIntArray("imageResId", imageResId);
			b.putIntArray("dots", dots);
			intent.putExtras(b);
			startActivity(intent);

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