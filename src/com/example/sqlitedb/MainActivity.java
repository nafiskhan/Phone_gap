package com.example.sqlitedb;



import java.sql.SQLException;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.cordova.Config;
import org.apache.cordova.DroidGap;
import org.apache.cordova.example.R;



public class MainActivity extends DroidGap {
	
	EditText txtPassword;
	EditText txtEmail;
	Button btnLogin;
	DataBaseHandler dbHandler;
	public SQLiteDatabase db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		 super.loadUrl(Config.getStartUrl());
		//super.loadUrl("file:///android_asset/www/index.html");

		 
		dbHandler = new DataBaseHandler(this);
		
	/*
		try {
			dbHandler = dbHandler.open();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
*/
		txtEmail =(EditText)this.findViewById(R.id.editTextEmail);
		txtPassword =(EditText)this.findViewById(R.id.editTextPassword);
		btnLogin =(Button)this.findViewById(R.id.btnLogin);
		
		
	}
	
	public void onClick(View view) throws SQLException
	{
		switch(view.getId())
		{
			case R.id.btnLogin:
			{
				dbHandler.insertEntry("admin", "admin");
				boolean flag;
				Log.e(getClass().getSimpleName(), "Pressed Login Button");
      			Toast.makeText(this, "Verifying your Login", Toast.LENGTH_SHORT).show();
   
      			//validate email ID format
      			Log.e(getClass().getSimpleName(), "Validating E-mail ID");
      			//verifyLogin(txtEmail.getText().toString(), txtPassword.getText().toString());
      			dbHandler.open();
      			
      			String tableName ="login";
      			flag = dbHandler.verifyLogin(txtEmail.getText().toString(),txtPassword.getText().toString()
      					,tableName);
      			if(flag==true)
      			{
          			Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show();
          			Intent intent = new Intent(MainActivity.this, ContactList.class);
          	        startActivity(intent);
      			}
      			else
      			{
      				Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
      				Toast.makeText(this, "Invalid E-mail ID or Password", Toast.LENGTH_SHORT).show();
      			
      			}

			}
		}
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
