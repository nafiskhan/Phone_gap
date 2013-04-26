package com.example.sqlitedb;

import java.sql.SQLException;

import org.apache.cordova.example.R;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewContact extends Activity {

	DataBaseHandler dbHandler;
	public SQLiteDatabase db;
	EditText name;
	EditText phone;
	Button btnAddContact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_contact);
		dbHandler = new DataBaseHandler(this);
		
		
		name=(EditText) this.findViewById(R.id.txtName);
		phone =(EditText) this.findViewById(R.id.txtPhone);
		
		
		btnAddContact=(Button)this.findViewById(R.id.btnAddContact);
	}
	
	public void onClick(View view) 
	{
		
		switch (view.getId()) {
		case R.id.btnAddContact:
		{
			
			Toast.makeText(this, "Adding Contact", Toast.LENGTH_SHORT).show();
		
				
				dbHandler.addContact(name.getText().toString(), phone.getText().toString());
		
			//db.execSQL("INSERT INTO Contact_info VALUES('A',12345)");
			 Toast.makeText(this, "Contact Added Successfully", Toast.LENGTH_SHORT).show();
			 Intent intent = new Intent(NewContact.this, ContactList.class);
   	       startActivity(intent);
			break;
		}
		case R.id.btnBack:
		{
			Toast.makeText(this, "Contacts", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(NewContact.this, ContactList.class);
  	        startActivity(intent);
			break;
		}

			

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_contact, menu);
		return true;
	}

}
