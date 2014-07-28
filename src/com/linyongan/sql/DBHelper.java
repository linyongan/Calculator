package com.linyongan.sql;

import com.linyongan.cofig.Constants;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	private static final String TAG = "DBHelper";
	private final String STUDENT_TABLE = "create table if not exists "
			+ Constants.StudentTable.TABLE_NAME + " ("
			+ Constants.StudentTable.ID + " text , " + Constants.StudentTable.Y
			+ " text)";
	private final String NOUN_TABLE = "create table if not exists "
			+ Constants.NounTable.TABLE_NAME + " (" + Constants.NounTable.NAME
			+ " text, " + Constants.NounTable.VALUE + " text)";

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(STUDENT_TABLE);
			db.execSQL(NOUN_TABLE);
			Log.i(TAG, "创建数据库成功！！！！！");
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
