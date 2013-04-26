package com.example.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper{

	public DataBaseHelper(Context context, String dbname, CursorFactory factory,
			int version) {
		super(context, dbname, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DataBaseHandler.DATABASE_CREATE);
		db.execSQL(DataBaseHandler.CONTACT_TABLE);
		db.execSQL("INSERT INTO login VALUES ('admin','admin')");
		//db.execSQL("INSERT INTO Contact_info VALUES('Anil',855266440)");
		//db.execSQL("INSERT INTO Contact_info VALUES('Akash',9939882554)");
		//db.execSQL("INSERT INTO Contact_info VALUES('Rajesh',9954214512)");
		//db.execSQL("INSERT INTO Contact_info VALUES('Jack',8792545671)");
		//db.execSQL("INSERT INTO Contact_info VALUES('Sunil',7752413215)");
		//db.execSQL("INSERT INTO Contact_info VALUES('John',88888888888)");
		//db.execSQL("INSERT INTO Contact_info VALUES('Shawn',7777777777)");
		//db.execSQL("INSERT INTO Contact_info VALUES('Rahul',5664564644)");
		//db.execSQL("INSERT INTO Contact_info VALUES('Dipak',5422121212)");
		//db.execSQL("INSERT INTO Contact_info VALUES('John',88887541212)");
		Log.e(getClass().getSimpleName(), "Data Inserted successfully");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("TaskDBAdapter", "Upgrading from version " 
				+oldVersion + " to " +newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS "+DataBaseHandler.DATABASE_CREATE);
		onCreate(db);
	}
	
	

}
