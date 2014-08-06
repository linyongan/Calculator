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
	/** ��ʾ��Ŀ��ViewPager */
	private ViewPager viewPager;
	/** ������ʾ���� */
	private List<View> content;
	/** �����ҵ�R.layout.testing_item */
	private LayoutInflater inflater;

	private MyPagerAdapter adapter;
	/** ���ذ�ť */
	private ImageButton backButton;
	/** �ղط��ذ�ť */
	private ImageButton collectButton;
	/** ��ʾ�ڼ��� */
	private int i = 1;
	/** �ղذ�ť�ı��� */
	private TextView collect_tv;
	/** ���ݿ������ */
	private TestDbManger dbManger;
	/** ���� */
	private TextView question;
	/** ѡ��1 */
	private RadioButton option1;
	/** ѡ��2 */
	private RadioButton option2;
	/** ѡ��3 */
	private RadioButton option3;
	/** ѡ��4 */
	private RadioButton option4;
	private RadioGroup radioGroup;
	/** ���ڻ��� */
	private boolean isScrolling = false;
	private int lastValue = -1;
	/** ���󻬶� */
	private boolean left = false;
	/** ���һ��� */
	private boolean right = false;
	private int testNum = 0;
	/** �洢��ĿID������ */
	private int[] id = new int[11];
	/** �洢��ȷ�𰸵����� */
	private String[] rightAnswers = new String[11];
	/** �洢�û�ѡ��Ĵ𰸵����� */
	private String[] answers = new String[11];
	/** ��ǣ�ֻ����һ�� */
	private boolean mark = true;
	/** ����ȷ��ʾ���ı� */
	private TextView right_tv;
	/** �𰸴�����ʾ���ı� */
	private TextView wrong_tv;
	/** ��ʾ����ȷ */
	private TextView rightAnswer_tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* ����ȫ�� */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.testing);

		// �����ȡ10����Ŀ��id
		getIdArray();
		// ���ݿ������
		dbManger = new TestDbManger(this);
		// �ҵ����е�ImageButton
		backButton = (ImageButton) findViewById(R.id.testing_back_bn);
		backButton.setOnClickListener(new ButtonListener());
		collectButton = (ImageButton) findViewById(R.id.testing_collect_bn);
		collectButton.setOnClickListener(new ButtonListener());
		// �ҵ����е�TextView
		collect_tv = (TextView) findViewById(R.id.testing_collect_tv);
		// �ҵ����е�ViewPager
		viewPager = (ViewPager) this.findViewById(R.id.viewpager);

		// �����Ӳ���
		inflater = LayoutInflater.from(this);
		// ��һ�α����صĶ���
		content = new ArrayList<View>();
		for (int x = 0; x < 10; x++) {
			newView();
			i++;
		}
		i = i - 10;
		System.out.println("onCreate����---������ɺ��i:" + i);
		adapter = new MyPagerAdapter();
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(new PageListener());
	}

	/**
	 * �����ȡ10����Ŀ��id
	 */
	private void getIdArray() {
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		String s = b.getString("testNum");
		System.out.println("��ȡ����testNum:" + s);
		if (s.length() != 0) {
			testNum = Integer.valueOf(s);
		}
		for (int x = 0; x < 10; x++) {
			if (testNum == 120) {
				Random random = new Random();
				// ��ȡһ�������������ж��Ƿ���֮ǰ����ͬ
				int rand = random.nextInt(testNum) + 1;
				for (int y = 0; y < x; y++) {
					// �������������֮ǰ������һ������ͬ�����ֲ���һ���������y��ֵΪ0.�����ж��Ƿ��ظ�
					if (rand == id[y]) {
						rand = random.nextInt(testNum) + 1;
						y = 0;
					}
				}
				id[x] = rand;
				System.out.println("����׼��----���������idΪ:" + id[x]);
			} else if (testNum == 30) {
				Random random = new Random();
				// ��ȡһ�������������ж��Ƿ���֮ǰ����ͬ
				int rand = random.nextInt(testNum) + 121;
				for (int y = 0; y < x; y++) {
					// �������������֮ǰ������һ������ͬ�����ֲ���һ���������y��ֵΪ0.�����ж��Ƿ��ظ�
					if (rand == id[y]) {
						rand = random.nextInt(testNum) + 121;
						y = 0;
					}
				}
				id[x] = rand;
				System.out.println("����׼��----���������idΪ:" + id[x]);
			} else if (testNum == 90) {
				Random random = new Random();
				// ��ȡһ�������������ж��Ƿ���֮ǰ����ͬ
				int rand = random.nextInt(testNum) + 150;
				for (int y = 0; y < x; y++) {
					// �������������֮ǰ������һ������ͬ�����ֲ���һ���������y��ֵΪ0.�����ж��Ƿ��ظ�
					if (rand == id[y]) {
						rand = random.nextInt(testNum) + 150;
						y = 0;
					}
				}
				id[x] = rand;
				System.out.println("����׼��----���������idΪ:" + id[x]);
			}
		}
	}

	private class PageListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// �ж��Ƿ��ڻ���
			if (arg0 == 1) {
				isScrolling = true;
			} else {
				isScrolling = false;
			}
			// ���������UI
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
							textView.setText("��ϲ�������Ѿ�ͨ�����β��ԣ���һ�������" + grade
									+ "���⣬���ķ�����" + grade * 10
									+ "�֡�������ѡ���ٴβ��Ա��Ѷȣ�������ս��һ���Ѷȡ�");
							textView.setVisibility(View.VISIBLE);
							button1.setVisibility(View.VISIBLE);
							button.setVisibility(View.GONE);
						} else {
							textView.setText("��һ�������"
									+ grade
									+ "���⣬���ķ�����"
									+ grade
									* 10
									+ "�֡����ź�����û��ͨ�����β��ԣ������Ե������İ�ť���鿴���������������ߵ�����ؼ����²��ԡ�");
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
					// �ݼ������Ҳ໬��
					right = true;
					left = false;
				} else if (lastValue < arg2) {
					// �ݼ������Ҳ໬��
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
			// ҳ����ת���֮��ŵ��õķ���
			if (left && i < 11) {
				i++;
				System.out.println("ҳ����ת��----���󻬶������ڵ�iֵ:" + i);
			}
			if (right) {
				i--;
				System.out.println("ҳ����ת��----���һ��������ڵ�iֵ:" + i);
			}

			dbManger.open();
			Cursor cursor = dbManger.getMark(id[i - 1]);
			System.out.println("ҳ����ת��----���ڵ�iֵ:" + i + " id[i - 1]��ֵ��"
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
	 * ��������ͼ
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
					System.out.println("radioGroup������---���ڵ�iֵ:" + i
							+ " ѡ���ѡ���ǣ� " + answers[i - 1]);

					break;
				case R.id.testing_item_radioButton2:
					answers[i - 1] = "B";
					System.out.println("radioGroup������---���ڵ�iֵ:" + i
							+ " ѡ���ѡ���ǣ� " + answers[i - 1]);
					break;
				case R.id.testing_item_radioButton3:
					answers[i - 1] = "C";
					System.out.println("radioGroup������---���ڵ�iֵ:" + i
							+ " ѡ���ѡ���ǣ� " + answers[i - 1]);
					break;
				case R.id.testing_item_radioButton4:
					answers[i - 1] = "D";
					System.out.println("radioGroup������---���ڵ�iֵ:" + i
							+ " ѡ���ѡ���ǣ� " + answers[i - 1]);
					break;
				}
			}
		});
		content.add(view);
	}

	/**
	 * ��ȡ����
	 */
	private void getData() {
		dbManger.open();
		Cursor cursor = dbManger.getTest(id[i - 1]);
		System.out.println("��ȡ����----���ڵ�iֵ:" + i + " id[i - 1]��ֵ��" + id[i - 1]);
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
			question.setText(i + "��" + Squestion);
			option1.setText(Soption1);
			option2.setText(Soption2);
			option3.setText(Soption3);
			option4.setText(Soption4);
			rightAnswers[i - 1] = Sanswer;
		}
		dbManger.close();
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
			switch (v.getId()) {
			case R.id.testing_back_bn:
				goBack();
				break;
			case R.id.testing_collect_bn:
				dbManger.open();
				Cursor cursor = dbManger.getMark(id[i - 1]);
				System.out.println("�ղذ�ť--���ڵ�iֵ:" + i + " id[i - 1]��ֵ��"
						+ id[i - 1]);
				if (cursor.moveToFirst()) {
					String mark = cursor.getString(cursor
							.getColumnIndex(Constants.TestTable.MARK));
					if (mark.equals("0")) {
						Toast.makeText(TestingActivity.this, "�ղسɹ���",
								Toast.LENGTH_SHORT).show();
						collect_tv
								.setBackgroundResource(R.drawable.testing_collect);
						Test test = new Test();
						test.set_id(id[i - 1]);
						test.setMark("1");
						dbManger.updateMark(test);
					} else {
						Toast.makeText(TestingActivity.this, "��ȡ���ղأ�",
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
				rightAnswer_tv.setText("��ȷ����:"
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
