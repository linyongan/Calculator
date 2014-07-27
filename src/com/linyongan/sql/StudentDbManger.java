package com.linyongan.sql;


import com.linyongan.cofig.Constants;
import com.linyongan.model.Student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class StudentDbManger {

	private static final String TAG = "StudentDbManger";
	private static SQLiteDatabase db;
	private static DBHelper helper;

	public StudentDbManger(Context c) {
		helper = new DBHelper(c, Constants.DATABASE_NAME, null,
				Constants.Version);
	}

	/**
	 * 关闭数据库
	 */
	public void close() {
		db.close();
	}

	/**
	 * 打开数据库
	 */
	public void open() throws SQLException {
		try {
			db = helper.getWritableDatabase();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			db = helper.getReadableDatabase();
		}

	}

	/**
	 * 添加
	 * 
	 * @return long 如果是正数则表示增加成功，反之不成功
	 */
	public long add(Student student) {
		try {
			ContentValues values = new ContentValues();
			values.put(Constants.StudentTable.ID, student.getId());
			values.put(Constants.StudentTable.Y, student.getY());
			return db.insert(Constants.StudentTable.TABLE_NAME, null, values);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			return -1;
		}

	}

	/**
	 * 查找表中记录
	 * 
	 * @return Cursor
	 */
	public Cursor search(String x) {
		/**
		 * 查询数据
		 * 
		 * @param table
		 *            表名
		 * @param columns
		 *            要查询的列名
		 * @param selection
		 *            查询条件 如：( id=?)
		 * @param selectionArgs
		 *            条件里的参数，用来替换"?"
		 * @param orderBy
		 *            排序 如：id desc
		 * @return 返回Cursor
		 */
		Cursor c = db.query(Constants.StudentTable.TABLE_NAME,
				new String[] { Constants.StudentTable.Y },
				Constants.StudentTable.ID + " =?", new String[] { x }, null, null, null);
		return c;
	}

}
