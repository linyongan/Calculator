package com.linyongan.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TestingActivity extends Activity {

	private ViewPager viewPager;
	// 加载显示内容
	private List<View> content;
	// 加载显示标题
	private List<String> title;

	private LayoutInflater inflater;

	private MyPagerAdapter adapter;
	/** 返回按钮 */
	private ImageButton backButton;
	/** 收藏返回按钮 */
	private ImageButton collectButton;
	private int i = 2;
	private TextView textView;
	private Boolean mark = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.testing);
		backButton = (ImageButton) findViewById(R.id.testing_back_bn);
		backButton.setOnClickListener(new ButtonListener());
		collectButton = (ImageButton) findViewById(R.id.testing_collect_bn);
		collectButton.setOnClickListener(new ButtonListener());

		textView = (TextView) findViewById(R.id.testing_collect_tv);

		viewPager = (ViewPager) this.findViewById(R.id.viewpager);
		// 加载布局
		inflater = LayoutInflater.from(this);
		// 第一次被加载的对象
		View view = inflater.inflate(R.layout.testing_item, null);
		content = new ArrayList<View>();
		content.add(view);
		title = new ArrayList<String>();
		title.add("第1题");
		adapter = new MyPagerAdapter();
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// 在这里更新UI
				if (i < 11) {
					View view = inflater.inflate(R.layout.testing_item, null);
					content.add(view);
					title.add("第" + (i++) + "题");
					adapter.notifyDataSetChanged();
					textView.setBackgroundResource(R.drawable.testing_discollect);
					mark = false;
				} 
				if (i == 11) {
					View view = inflater.inflate(R.layout.testing_item1, null);
					content.add(view);
					title.add("提交答案");
					adapter.notifyDataSetChanged();
					textView.setBackgroundResource(R.drawable.testing_discollect);
					mark = false;
					i++;
				}
			}
		});

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
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.testing_back_bn:
				goBack();
				break;
			case R.id.testing_collect_bn:
				if (!mark) {
					Toast.makeText(TestingActivity.this, "收藏成功！",
							Toast.LENGTH_SHORT).show();
					textView.setBackgroundResource(R.drawable.testing_collect);
					mark = !mark;
				} else {
					Toast.makeText(TestingActivity.this, "已取消收藏！",
							Toast.LENGTH_SHORT).show();
					textView.setBackgroundResource(R.drawable.testing_discollect);
					mark = !mark;
				}
				break;
			}
		}
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
			return title.get(position);
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
