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
	 * �ر����ݿ�
	 */
	public void close() {
		db.close();
	}

	/**
	 * �����ݿ�
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
	 * ���
	 * 
	 * @return long ������������ʾ���ӳɹ�����֮���ɹ�
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
	 * ���ұ��м�¼
	 * 
	 * @return Cursor
	 */
	public Cursor search(String x) {
		/**
		 * ��ѯ����
		 * 
		 * @param table
		 *            ����
		 * @param columns
		 *            Ҫ��ѯ������
		 * @param selection
		 *            ��ѯ���� �磺( id=?)
		 * @param selectionArgs
		 *            ������Ĳ����������滻"?"
		 * @param orderBy
		 *            ���� �磺id desc
		 * @return ����Cursor
		 */
		Cursor c = db.query(Constants.StudentTable.TABLE_NAME,
				new String[] { Constants.StudentTable.Y },
				Constants.StudentTable.ID + " =?", new String[] { x }, null, null, null);
		return c;
	}

}
