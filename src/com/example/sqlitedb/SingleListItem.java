package com.example.sqlitedb;

import org.apache.cordova.example.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleListItem extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.contact_info);
        
        TextView txtContact = (TextView) findViewById(R.id.contact_label);
        
        Intent i = getIntent();
        // getting attached intent data
        String contact = i.getStringExtra("contact");
        // displaying selected product name
        txtContact.setText(contact);
        
	}
}