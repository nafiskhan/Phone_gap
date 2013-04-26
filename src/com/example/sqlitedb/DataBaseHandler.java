package com.example.sqlitedb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.Toast;

public class DataBaseHandler  {

	static final String DATABASE_NAME="Contact.db";
	static final int DATABASE_VERSION=1;
	
	static final String DATABASE_CREATE ="CREATE TABLE login(ID TEXT,PASSWORD TEXT)";
	static final String CONTACT_TABLE="CREATE TABLE Contact_info(NAME TEXT,MOBILE INTEGER(10))";
	public SQLiteDatabase db;
	private  Context context;
	private DataBaseHelper dbHelper;
	private DataBaseHandler dbHandler;

	public DataBaseHandler(Context context) {
		//db.execSQL(DATABASE_CREATE);
		dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		
	}
	
	public  DataBaseHandler open() throws SQLException
	{
		db=dbHelper.getWritableDatabase();
		
		return this;
	}
	public void close()
	{
		db.close();
	}
	public SQLiteDatabase getDatabaseInstance()
	{
		return db;
		
	}
	// void insertEntry(String userName,String password)
	void insertEntry(String username, String password)
	{
       ContentValues newValues = new ContentValues();
		// Assign values for each row.
		newValues.put("ID", username);
		newValues.put("PASSWORD",password);
		// Insert the row into your table
		//db.insert("login", null, newValues);
		///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
		Log.e(getClass().getSimpleName(),"Value inserted successfully");
	}
	
	
	public boolean verifyLogin(String username,String password,String tableName) throws SQLException
	{
		boolean flag = false;
		
		
		String query ="select * from " +tableName ;
		String uname;
		String pwd;
	
		
				Log.e(getClass().getSimpleName(), "Validated E-mail Successfully");
				Cursor cursor = db.rawQuery(query, null);
				
				 if(cursor.getCount()>0)
				 {
					 
					 cursor.moveToFirst();
					 do{
						 uname =cursor.getString(cursor.getColumnIndex("ID"));
						 pwd = cursor.getString(cursor.getColumnIndex("PASSWORD"));
						 
						 if(uname.equals(username)&& pwd.equals(password))
						 {
							 Log.e(getClass().getSimpleName(),"Login Success");
							 flag=true;
							 
						 }
			
					 }while(cursor.moveToNext());
					 cursor.close();
					 Log.e(getClass().getSimpleName(),"Login Verified");
					 if(flag==true)
					 {
						  Log.e(getClass().getSimpleName(),"Login Success");
						 
						 flag=true;
						 

					 }
					 else
					 {
						 Log.e(getClass().getSimpleName(),"Invalid emailID or Password");
					
						
						 return flag=false;

					 }
				 }
				 
				return flag;
		
	}
	
	
	
	
	
	public List getAllContacts()
	{
		Cursor cursor = db.rawQuery("SELECT * FROM Contact_info", null);
	       // String results = "";
	        List list = new ArrayList();
	        
	        String name;
	        String phone;	
	        
	        
	        if(cursor!=null)
			{
				if(cursor.moveToFirst())
				do{
					name = cursor.getString(cursor.getColumnIndex("NAME"));
					phone=cursor.getString(cursor.getColumnIndex("MOBILE"));
					//Map map = new TreeMap();
					//map.put(name,phone);
					//List newList = new ArrayList();
					
					  
					//newList.add(name + "\n" +"\t"+phone);
					//newList.add(phone);
					 //for(int i=0;i<newList.size();i++)
						//{
							//Log.e("Reading List", "" +list.get(i));
							//list.add(newList.get(i));
							
					//	}
					
		            list.add(name);
		            list.add(phone);
					//list.add(newList);
					Log.e(getClass().getSimpleName(), "Adding contacts to List");
				}while(cursor.moveToNext());
			}
			
	       
	        return list;

	}

	
	public void addContact(String name,String phone) 
	{
	
		Log.w(getClass().getSimpleName(), "Adding Contact");
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("NAME", name);
		values.put("MOBILE", phone);
		db.insert("Contact_info", null, values);
	    db.close(); // Closing database connection
	}
	  
	public void deleteContact(String name,String mobile)
	{
		Log.w("Deleting","Contact");
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String sql = "DELETE FROM Contact_info WHERE NAME='"+name+"' AND MOBILE = '"+mobile+"'";
		db.execSQL(sql);
		Log.w("Deleting","Contact Deleted Successfully");
		
	}
	public void updateContact(String newName,String newPhone,String name,String phone)
	{
		Log.w("Updating","Contact");
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String sql = "UPDATE Contact_info SET NAME ='" +newName+"',MOBILE='"+newPhone +"' WHERE NAME='"+name+"'" +
				"AND MOBILE = '"+phone+"'";
		db.execSQL(sql);
		Log.w("Updating","Contact Updated Successfully");
	}

	
	
}