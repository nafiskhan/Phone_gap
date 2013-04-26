package com.example.sqlitedb;

import org.apache.cordova.example.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateContact extends Activity {
	
	EditText txtName;
	EditText txtPhone;
	Button btnUpdate;
	Button btnBack;
	TextView textView;
	DataBaseHandler dbHandler;
	String phone;
	String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_contact);
		dbHandler= new DataBaseHandler(this);
       name = getIntent().getExtras().getString("name");
         phone = getIntent().getExtras().getString("phone");
       // Log.e("You have clicked", name + " " +phone);
		//ContactList contacList = new ContactList();
		//String name = contacList.name;
		//String phone = contacList.phone;
		Toast.makeText(this, "Update Contact" +name +" "+phone, Toast.LENGTH_SHORT).show();
		
		txtName =(EditText)this.findViewById(R.id.txtUpdateName);
		txtPhone =(EditText)this.findViewById(R.id.txtUpdatePhone);
		
		textView=(TextView)this.findViewById(R.id.txtContactInfo);
		//dbHandler.updateContact(name, phone, textView.getText().toString());
		
		
		//txtName.setText(name);
		//txtPhone.setText(phone);
		//textView.setText(name +" " + phone);
		txtName.setText(name);
		txtPhone.setText(phone);
	}

	
	public void onClick(View view)
	{
		switch (view.getId()) {
		case R.id.btnUpdateContact:
		{
			
			Toast.makeText(this, "Updating Contacts", Toast.LENGTH_SHORT).show();
			dbHandler.updateContact(txtName.getText().toString(), txtPhone.getText().toString(),name,phone);
			
			Toast.makeText(this, "Deleting Contact", Toast.LENGTH_SHORT).show();
			
			Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent(UpdateContact.this, ContactList.class);
  	        startActivity(intent);
  	      break;
		}
	

		case R.id.btnBack:
		{
			
			Intent intent = new Intent(UpdateContact.this, ContactList.class);
  	        startActivity(intent);
			break;
		}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_contact, menu);
		return true;
	}

}
