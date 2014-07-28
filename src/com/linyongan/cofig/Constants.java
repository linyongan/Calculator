package com.linyongan.cofig;

/**
 * @desprition 数据库配置常量类
 * 
 */
public class Constants {
	/** 数据库名字 */
	public static final String DATABASE_NAME = "xy.db";
	/** 数据库版本 */
	public static final int Version = 1;

	public static class StudentTable {
		/** 表名 */
		public static final String TABLE_NAME = "xy";
		/** X*/
		public static final String ID = "_id";
		/** Y */
		public static final String Y = "y";
	}
	public static class NounTable {
		/** 表名 */
		public static final String TABLE_NAME = "noun";
		/** name */
		public static final String NAME = "name";
		/** value */
		public static final String VALUE = "value";
	}
}
