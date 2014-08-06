package com.linyongan.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.linyongan.cofig.Constants;
import com.linyongan.model.Test;
import com.linyongan.sql.TestDbManger;

public class TestingActivity extends Activity {
	/** 显示题目的ViewPager */
	private ViewPager viewPager;
	/** 加载显示内容 */
	private List<View> content;
	/** 用于找到R.layout.testing_item */
	private LayoutInflater inflater;

	private MyPagerAdapter adapter;
	/** 返回按钮 */
	private ImageButton backButton;
	/** 收藏返回按钮 */
	private ImageButton collectButton;
	/** 显示第几题 */
	private int i = 1;
	/** 收藏按钮的背景 */
	private TextView collect_tv;
	/** 数据库管理类 */
	private TestDbManger dbManger;
	/** 问题 */
	private TextView question;
	/** 选项1 */
	private RadioButton option1;
	/** 选项2 */
	private RadioButton option2;
	/** 选项3 */
	private RadioButton option3;
	/** 选项4 */
	private RadioButton option4;
	private RadioGroup radioGroup;
	/** 正在滑动 */
	private boolean isScrolling = false;
	private int lastValue = -1;
	/** 向左滑动 */
	private boolean left = false;
	/** 向右滑动 */
	private boolean right = false;
	private int testNum = 0;
	/** 存储题目ID的数组 */
	private int[] id = new int[11];
	/** 存储正确答案的数组 */
	private String[] rightAnswers = new String[11];
	/** 存储用户选择的答案的数组 */
	private String[] answers = new String[11];
	/** 标记，只加载一次 */
	private boolean mark = true;
	/** 答案正确显示的文本 */
	private TextView right_tv;
	/** 答案错误显示的文本 */
	private TextView wrong_tv;
	/** 显示答案正确 */
	private TextView rightAnswer_tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.testing);

		// 随机获取10道题目的id
		getIdArray();
		// 数据库管理类
		dbManger = new TestDbManger(this);
		// 找到所有的ImageButton
		backButton = (ImageButton) findViewById(R.id.testing_back_bn);
		backButton.setOnClickListener(new ButtonListener());
		collectButton = (ImageButton) findViewById(R.id.testing_collect_bn);
		collectButton.setOnClickListener(new ButtonListener());
		// 找到所有的TextView
		collect_tv = (TextView) findViewById(R.id.testing_collect_tv);
		// 找到所有的ViewPager
		viewPager = (ViewPager) this.findViewById(R.id.viewpager);

		// 加载子布局
		inflater = LayoutInflater.from(this);
		// 第一次被加载的对象
		content = new ArrayList<View>();
		for (int x = 0; x < 10; x++) {
			newView();
			i++;
		}
		i = i - 10;
		System.out.println("onCreate（）---加载完成后的i:" + i);
		adapter = new MyPagerAdapter();
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(new PageListener());
	}

	/**
	 * 随机获取10道题目的id
	 */
	private void getIdArray() {
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		String s = b.getString("testNum");
		System.out.println("获取到的testNum:" + s);
		if (s.length() != 0) {
			testNum = Integer.valueOf(s);
		}
		for (int x = 0; x < 10; x++) {
			if (testNum == 120) {
				Random random = new Random();
				// 获取一个数据数，先判断是否与之前的相同
				int rand = random.nextInt(testNum) + 1;
				for (int y = 0; y < x; y++) {
					// 如果这个随机数与之前的任意一个数相同，则又产生一个随机数，y的值为0.重新判断是否重复
					if (rand == id[y]) {
						rand = random.nextInt(testNum) + 1;
						y = 0;
					}
				}
				id[x] = rand;
				System.out.println("数据准备----随机产生的id为:" + id[x]);
			} else if (testNum == 30) {
				Random random = new Random();
				// 获取一个数据数，先判断是否与之前的相同
				int rand = random.nextInt(testNum) + 121;
				for (int y = 0; y < x; y++) {
					// 如果这个随机数与之前的任意一个数相同，则又产生一个随机数，y的值为0.重新判断是否重复
					if (rand == id[y]) {
						rand = random.nextInt(testNum) + 121;
						y = 0;
					}
				}
				id[x] = rand;
				System.out.println("数据准备----随机产生的id为:" + id[x]);
			} else if (testNum == 90) {
				Random random = new Random();
				// 获取一个数据数，先判断是否与之前的相同
				int rand = random.nextInt(testNum) + 150;
				for (int y = 0; y < x; y++) {
					// 如果这个随机数与之前的任意一个数相同，则又产生一个随机数，y的值为0.重新判断是否重复
					if (rand == id[y]) {
						rand = random.nextInt(testNum) + 150;
						y = 0;
					}
				}
				id[x] = rand;
				System.out.println("数据准备----随机产生的id为:" + id[x]);
			}
		}
	}

	private class PageListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// 判断是否在滑动
			if (arg0 == 1) {
				isScrolling = true;
			} else {
				isScrolling = false;
			}
			// 在这里更新UI
			if (i < 10 && arg0 == 1) {
				adapter.notifyDataSetChanged();
			}
			if (i == 10 && mark) {
				mark = false;
				View view = inflater.inflate(R.layout.testing_item1, null);
				collect_tv
				.setBackgroundResource(R.drawable.testing_discollect);
				final Button button = (Button) view
						.findViewById(R.id.testing_item1_btn);
				final TextView textView = (TextView) view
						.findViewById(R.id.testing_item1_tv);
				final Button button1 = (Button) view
						.findViewById(R.id.testing_item1_btn1);
				button.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int grade = changeView();
						if (grade >= 6) {
							textView.setText("恭喜您！您已经通过本次测试，您一共答对了" + grade
									+ "道题，您的分数是" + grade * 10
									+ "分。您可以选择再次测试本难度，或者挑战下一个难度。");
							textView.setVisibility(View.VISIBLE);
							button1.setVisibility(View.VISIBLE);
							button.setVisibility(View.GONE);
						} else {
							textView.setText("您一共答对了"
									+ grade
									+ "道题，您的分数是"
									+ grade
									* 10
									+ "分。很遗憾，您没有通过本次测试，您可以点击下面的按钮，查看具体答题情况，或者点击返回键重新测试。");
							textView.setVisibility(View.VISIBLE);
							button1.setVisibility(View.VISIBLE);
							button.setVisibility(View.GONE);
						}
					}
				});
				button1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						viewPager.setCurrentItem(0);
						i= i-10;
					}
				});
				content.add(view);
				adapter.notifyDataSetChanged();
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			if (isScrolling) {
				if (lastValue > arg2) {
					// 递减，向右侧滑动
					right = true;
					left = false;
				} else if (lastValue < arg2) {
					// 递减，向右侧滑动
					right = false;
					left = true;
				} else if (lastValue == arg2) {
					right = left = false;
				}
			}
			lastValue = arg2;

		}

		@Override
		public void onPageSelected(int arg0) {
			// 页面跳转完成之后才调用的方法
			if (left && i < 11) {
				i++;
				System.out.println("页面跳转后----向左滑动，现在的i值:" + i);
			}
			if (right) {
				i--;
				System.out.println("页面跳转后----向右滑动，现在的i值:" + i);
			}

			dbManger.open();
			Cursor cursor = dbManger.getMark(id[i - 1]);
			System.out.println("页面跳转后----现在的i值:" + i + " id[i - 1]的值："
					+ id[i - 1]);
			if (cursor.moveToFirst()) {
				String mark = cursor.getString(cursor
						.getColumnIndex(Constants.TestTable.MARK));
				if (mark.equals("0")) {
					collect_tv
							.setBackgroundResource(R.drawable.testing_discollect);
				} else {
					collect_tv
							.setBackgroundResource(R.drawable.testing_collect);
				}
			}
			dbManger.close();

		}

	}

	/**
	 * 创建新视图
	 */
	private void newView() {
		View view = inflater.inflate(R.layout.testing_item, null);
		question = (TextView) view.findViewById(R.id.testing_item_textview1);
		option1 = (RadioButton) view
				.findViewById(R.id.testing_item_radioButton1);
		option2 = (RadioButton) view
				.findViewById(R.id.testing_item_radioButton2);
		option3 = (RadioButton) view
				.findViewById(R.id.testing_item_radioButton3);
		option4 = (RadioButton) view
				.findViewById(R.id.testing_item_radioButton4);
		radioGroup = (RadioGroup) view
				.findViewById(R.id.testing_item_radioGroup);
		getData();
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.testing_item_radioButton1:
					answers[i - 1] = "A";
					System.out.println("radioGroup监听器---现在的i值:" + i
							+ " 选择的选项是： " + answers[i - 1]);

					break;
				case R.id.testing_item_radioButton2:
					answers[i - 1] = "B";
					System.out.println("radioGroup监听器---现在的i值:" + i
							+ " 选择的选项是： " + answers[i - 1]);
					break;
				case R.id.testing_item_radioButton3:
					answers[i - 1] = "C";
					System.out.println("radioGroup监听器---现在的i值:" + i
							+ " 选择的选项是： " + answers[i - 1]);
					break;
				case R.id.testing_item_radioButton4:
					answers[i - 1] = "D";
					System.out.println("radioGroup监听器---现在的i值:" + i
							+ " 选择的选项是： " + answers[i - 1]);
					break;
				}
			}
		});
		content.add(view);
	}

	/**
	 * 获取数据
	 */
	private void getData() {
		dbManger.open();
		Cursor cursor = dbManger.getTest(id[i - 1]);
		System.out.println("获取数据----现在的i值:" + i + " id[i - 1]的值：" + id[i - 1]);
		if (cursor.moveToFirst()) {
			String Squestion = cursor.getString(cursor
					.getColumnIndex(Constants.TestTable.QUESTION));
			String Soption1 = cursor.getString(cursor
					.getColumnIndex(Constants.TestTable.OPTION1));
			String Soption2 = cursor.getString(cursor
					.getColumnIndex(Constants.TestTable.OPTION2));
			String Soption3 = cursor.getString(cursor
					.getColumnIndex(Constants.TestTable.OPTION3));
			String Soption4 = cursor.getString(cursor
					.getColumnIndex(Constants.TestTable.OPTION4));
			String Sanswer = cursor.getString(cursor
					.getColumnIndex(Constants.TestTable.ANSWER));
			System.out.println("--question:-- " + Squestion);
			System.out.println("--option1:-- " + Soption1);
			System.out.println("--option2:-- " + Soption2);
			System.out.println("--option3:-- " + Soption3);
			System.out.println("--option4:-- " + Soption4);
			question.setText(i + "、" + Squestion);
			option1.setText(Soption1);
			option2.setText(Soption2);
			option3.setText(Soption3);
			option4.setText(Soption4);
			rightAnswers[i - 1] = Sanswer;
		}
		dbManger.close();
	}

	/**
	 * button的监听事件
	 * 
	 * @author yongan
	 * 
	 */
	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.testing_back_bn:
				goBack();
				break;
			case R.id.testing_collect_bn:
				dbManger.open();
				Cursor cursor = dbManger.getMark(id[i - 1]);
				System.out.println("收藏按钮--现在的i值:" + i + " id[i - 1]的值："
						+ id[i - 1]);
				if (cursor.moveToFirst()) {
					String mark = cursor.getString(cursor
							.getColumnIndex(Constants.TestTable.MARK));
					if (mark.equals("0")) {
						Toast.makeText(TestingActivity.this, "收藏成功！",
								Toast.LENGTH_SHORT).show();
						collect_tv
								.setBackgroundResource(R.drawable.testing_collect);
						Test test = new Test();
						test.set_id(id[i - 1]);
						test.setMark("1");
						dbManger.updateMark(test);
					} else {
						Toast.makeText(TestingActivity.this, "已取消收藏！",
								Toast.LENGTH_SHORT).show();
						collect_tv
								.setBackgroundResource(R.drawable.testing_discollect);
						Test test = new Test();
						test.set_id(id[i - 1]);
						test.setMark("0");
						dbManger.updateMark(test);
					}
				}
				dbManger.close();
				break;
			}
		}
	}

	private int changeView() {
		int result = 0;
		for (int x = 0; x < 10; x++) {
			View view = content.get(x);
			content.remove(x);
			right_tv = (TextView) view.findViewById(R.id.testing_item_right);
			wrong_tv = (TextView) view.findViewById(R.id.testing_item_wrong);
			rightAnswer_tv = (TextView) view
					.findViewById(R.id.testing_item_rightAnswer);
			if (answers[x].equals(rightAnswers[x].substring(0, 1))) {
				right_tv.setVisibility(View.VISIBLE);
				result++;
			} else {
				wrong_tv.setVisibility(View.VISIBLE);
				rightAnswer_tv.setText("正确答案是:"
						+ rightAnswers[x].substring(0, 1));
				rightAnswer_tv.setVisibility(View.VISIBLE);
			}
			content.add(x, view);
		}
		return result;
	}

	public class MyPagerAdapter extends PagerAdapter {

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((ViewPager) container).addView(content.get(position));
			return content.get(position);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return content.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return (arg0 == arg1);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			// super.destroyItem(container, position, object);
			((ViewPager) container).removeView(content.get(position));
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
		Intent intent = new Intent(TestingActivity.this, TestActivity.class);
		startActivity(intent);
		finish();
	}
}
