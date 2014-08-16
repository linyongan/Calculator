package com.linyongan.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

/**
 * 金融计算器主页面
 */
public class MainActivity extends Activity {

	/** 常见问题按钮 */
	private Button questionButton;
	/** 名词查询按钮 */
	private Button searchButton;
	/** 期权学堂按钮 */
	private Button learnButton;
	/** 期权计算按钮 */
	private Button derivativeButton;
	/** 自我测试按钮 */
	private Button testButton;
	/** 关于我们按钮 */
	private Button aboutButton;

	/** 标记，第一次运行加载数据 */
	private static boolean mark = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		if (mark) {
			LoadTask load = new LoadTask(MainActivity.this);
			load.execute();
			mark = false;
		}
		// 找到所有的button
		questionButton = (Button) findViewById(R.id.questionButton);
		searchButton = (Button) findViewById(R.id.searchButton);
		learnButton = (Button) findViewById(R.id.learnButton);
		derivativeButton = (Button) findViewById(R.id.derivativeButton);
		testButton = (Button) findViewById(R.id.testButton);
		aboutButton = (Button) findViewById(R.id.aboutButton);

		questionButton.setOnClickListener(new ButtonListener());
		searchButton.setOnClickListener(new ButtonListener());
		learnButton.setOnClickListener(new ButtonListener());
		derivativeButton.setOnClickListener(new ButtonListener());
		testButton.setOnClickListener(new ButtonListener());
		aboutButton.setOnClickListener(new ButtonListener());
	}

	/**
	 * button的监听事件
	 * 
	 * @author yongan
	 * 
	 */
	private class ButtonListener implements OnClickListener {

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.questionButton:
				Intent intent2 = new Intent(MainActivity.this,
						QuestionActivity.class);
				startActivity(intent2);
				break;
			case R.id.searchButton:
				Intent intent3 = new Intent(MainActivity.this,
						SearchActivity.class);
				startActivity(intent3);
				break;
			case R.id.learnButton:
				Intent intent4 = new Intent(MainActivity.this,
						LearnActivity.class);
				startActivity(intent4);
				break;
			case R.id.derivativeButton:
				Intent intent5 = new Intent(MainActivity.this,
						DerivativeActivity.class);
				startActivity(intent5);
				break;
			case R.id.testButton:
				Intent intent1 = new Intent(MainActivity.this,
						TestActivity.class);
				startActivity(intent1);
				break;
			case R.id.aboutButton:
				Intent intent6 = new Intent(MainActivity.this,
						AboutActivity.class);
				startActivity(intent6);
				break;
			}

		}

	}

	/**
	 * 加载数据
	 */
	class LoadTask extends AsyncTask<Object, Object, Object> {
		// 可变长的输入参数，与AsyncTask.exucute()对应
		ProgressDialog pdialog;
		Context mContext;

		// 这里的参数可以自定义，需要什么控件就传入什么控件
		public LoadTask(Context ctx) {
			mContext = ctx;
		}

		// 该方法运行在UI线程当中,主要用于进行异步操作之前的UI准备工作
		@Override
		protected void onPreExecute() {
			pdialog = new ProgressDialog(mContext);
			// 设置对话框的标题
			pdialog.setTitle("正在读取数据");
			// 设置对话框 显示的内容
			pdialog.setMessage("第一次运行程序，读取数据中，敬请等待十几秒...");
			// 设置对话框不能用“取消”按钮关闭
			pdialog.setCancelable(false);
			// 设置对话框的进度条是否显示进度
			pdialog.setIndeterminate(false);
			pdialog.show();
		}

		// 该方法并不运行在UI线程当中，所以在该方法当中，不能对UI当中的控件进行设置和修改
		// 主要用于进行异步操作。
		@Override
		protected String doInBackground(Object... params) {
			// com.test.db 是程序的包名，请根据自己的程序调整
			// /data/data/com.test.db/
			// databases 目录是准备放 SQLite 数据库的地方，也是 Android 程序默认的数据库存储目录
			// 数据库名为 test.db
			String DB_PATH = "/data/data/com.linyongan.activity/databases/";
			String DB_NAME = "xy.db";

			// 检查 SQLite 数据库文件是否存在
			if ((new File(DB_PATH + DB_NAME)).exists() == false) {
				// 如 SQLite 数据库文件不存在，再检查一下 database 目录是否存在
				File f = new File(DB_PATH);
				// 如 database 目录不存在，新建该目录
				if (!f.exists()) {
					f.mkdir();
				}
				try {
					// 得到 assets 目录下我们实现准备好的 SQLite 数据库作为输入流
					InputStream is = getBaseContext().getAssets().open(DB_NAME);
					// 输出流
					OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);
					// 文件写入
					byte[] buffer = new byte[1024];
					int length;
					while ((length = is.read(buffer)) > 0) {
						os.write(buffer, 0, length);
					}
					// 关闭文件流
					os.flush();
					os.close();
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		// 在doInBackground方法执行结束之后再运行，并且运行在UI线程当中。
		// 主要用于将异步任务执行的结果展示给客户
		@Override
		protected void onPostExecute(Object result) {
			pdialog.dismiss();
		}

	}

	/** 退出按钮 */
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);  
			mHomeIntent.addCategory(Intent.CATEGORY_HOME);  
			mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK  
			| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);  
			startActivity(mHomeIntent); 
			finish();
		}
		return super.dispatchKeyEvent(event);
	}

}