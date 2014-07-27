package com.linyongan.activity;

import java.io.IOException;
import java.io.InputStream;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.linyongan.model.Student;
import com.linyongan.sql.StudentDbManger;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 金融计算器主页面
 */
public class MainActivity extends Activity {
	/** 个人理财按钮 */
	private Button financeButton;
	/** 贷款计算按钮 */
	private Button loanButton;
	/** 投资指标按钮 */
	private Button investmentButton;
	/** 金融产品定价按钮 */
	private Button identificationButton;
	/** 金融衍生品定价按钮 */
	private Button derivativeButton;

	private StudentDbManger dbmanger;
	/** 标记，第一次运行加载数据 */
	private static boolean mark = true;
	private AssetManager am;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置全屏 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		// 第一次运行加载数据
		dbmanger = new StudentDbManger(this);
		am = this.getAssets();
		if (mark) {
			LoadTask load = new LoadTask(MainActivity.this);
			load.execute();
			mark = false;
		}
		// 找到所有的button
		financeButton = (Button) findViewById(R.id.financeButton);
		loanButton = (Button) findViewById(R.id.loanButton);
		investmentButton = (Button) findViewById(R.id.investmentButton);
		identificationButton = (Button) findViewById(R.id.identificationButton);
		derivativeButton = (Button) findViewById(R.id.derivativeButton);
		financeButton.setOnClickListener(new ButtonListener());
		loanButton.setOnClickListener(new ButtonListener());
		investmentButton.setOnClickListener(new ButtonListener());
		identificationButton.setOnClickListener(new ButtonListener());
		derivativeButton.setOnClickListener(new ButtonListener());
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
			case R.id.financeButton:
				Intent intent1 = new Intent(MainActivity.this,
						FinanceActivity.class);
				startActivity(intent1);
				break;
			case R.id.loanButton:
				Intent intent2 = new Intent(MainActivity.this,
						LoanActivity.class);
				startActivity(intent2);
				break;
			case R.id.investmentButton:
				Intent intent3 = new Intent(MainActivity.this,
						InvestmentActivity.class);
				startActivity(intent3);
				break;
			case R.id.identificationButton:
				Intent intent4 = new Intent(MainActivity.this,
						IdentificationActivity.class);
				startActivity(intent4);
				break;
			case R.id.derivativeButton:
				Intent intent5 = new Intent(MainActivity.this,
						DerivativeActivity.class);
				startActivity(intent5);
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
			pdialog.setMessage("第一次运行程序，读取数据中，敬请等待...");
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
			InputStream is = null;
			try {
				is = am.open("data.xls");
				Workbook wb = Workbook.getWorkbook(is);
				// 获取工作簿
				Sheet sheet = wb.getSheet(0);
				int row = sheet.getRows();
				dbmanger.open();
				for (int i = 0; i < row; ++i) {
					// 获取第一列
					Cell cellarea = sheet.getCell(0, i);
					// 获取第二列
					Cell cellschool = sheet.getCell(1, i);
					String x = cellarea.getContents();
					String y = cellschool.getContents();
					System.out.println(x + "--输出--:" + y);
					Student student = new Student();
					student.setId(x);
					student.setY(y);
					dbmanger.add(student);
				}
				dbmanger.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			if (event.getAction() == KeyEvent.ACTION_DOWN
					&& event.getRepeatCount() == 0) {
				finish();
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}

}