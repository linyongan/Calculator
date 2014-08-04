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
	// ������ʾ����
	private List<View> content;
	// ������ʾ����
	private List<String> title;

	private LayoutInflater inflater;

	private MyPagerAdapter adapter;
	/** ���ذ�ť */
	private ImageButton backButton;
	/** �ղط��ذ�ť */
	private ImageButton collectButton;
	private int i = 2;
	private TextView textView;
	private Boolean mark = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.testing);
		backButton = (ImageButton) findViewById(R.id.testing_back_bn);
		backButton.setOnClickListener(new ButtonListener());
		collectButton = (ImageButton) findViewById(R.id.testing_collect_bn);
		collectButton.setOnClickListener(new ButtonListener());

		textView = (TextView) findViewById(R.id.testing_collect_tv);

		viewPager = (ViewPager) this.findViewById(R.id.viewpager);
		// ���ز���
		inflater = LayoutInflater.from(this);
		// ��һ�α����صĶ���
		View view = inflater.inflate(R.layout.testing_item, null);
		content = new ArrayList<View>();
		content.add(view);
		title = new ArrayList<String>();
		title.add("��1��");
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
				// ���������UI
				if (i < 11) {
					View view = inflater.inflate(R.layout.testing_item, null);
					content.add(view);
					title.add("��" + (i++) + "��");
					adapter.notifyDataSetChanged();
					textView.setBackgroundResource(R.drawable.testing_discollect);
					mark = false;
				} 
				if (i == 11) {
					View view = inflater.inflate(R.layout.testing_item1, null);
					content.add(view);
					title.add("�ύ��");
					adapter.notifyDataSetChanged();
					textView.setBackgroundResource(R.drawable.testing_discollect);
					mark = false;
					i++;
				}
			}
		});

	}

	/**
	 * button�ļ����¼�
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
					Toast.makeText(TestingActivity.this, "�ղسɹ���",
							Toast.LENGTH_SHORT).show();
					textView.setBackgroundResource(R.drawable.testing_collect);
					mark = !mark;
				} else {
					Toast.makeText(TestingActivity.this, "��ȡ���ղأ�",
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
		Intent intent = new Intent(TestingActivity.this, TestActivity.class);
		startActivity(intent);
		finish();
	}
}
