package com.example.sqlitedb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnHoverListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import org.apache.cordova.example.R;

public class ContactList extends Activity implements OnClickListener {

	private static LinearLayout linearLayout = null;
	DataBaseHandler dbHandler;
	SQLiteDatabase db;
	ListView listView;
    private String[] contacts ;
    List list;
    Context context;
    private CheckBox chkBox;
    private boolean mShowInvisible;
    CheckBox checkBox;
    TableLayout tableLayout;
    LinearLayout linLayout;
    String contact;
    String name;
	String phone;
	
	ArrayAdapter adapter;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact);
		dbHandler = new DataBaseHandler(this);
		try {
			dbHandler.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list = dbHandler.getAllContacts();
		listView=(ListView)this.findViewById(R.id.listview1);
		chkBox=(CheckBox)this.findViewById(R.id.chkBox);
		
		//LinearLayout ll = (LinearLayout)findViewById(R.id.ttable);
		
		//ll.addView(listView);
		
		
		 for(int i = 0; i <list.size(); i++) {
			
	            CheckBox cb = new CheckBox(this);
	            cb.setText("Dynamic Checkbox " + i);
	            cb.setId(i);
	            cb.setOnClickListener(this);
	            //listView.addView(cb);
	            //ll.addView(ll2);
	        }
	
		  adapter = new ArrayAdapter(this,
			       android.R.layout.simple_list_item_1,list);
				
			    listView.setAdapter(adapter);
			    
			    chkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		                Log.d("List View", "mShowInvisibleControl changed: " + isChecked);
		                mShowInvisible = isChecked;
		                if(isChecked)
		                {
		                	 listView.setAdapter(adapter);
		                	 isChecked=true;
		                }
		                else
		                {
		                	listView.setAdapter(null);
		                	isChecked=false;
		                }
		               	 
		              
		    
		            }
		        }); 	
			    
			    listView.setOnItemClickListener(new OnItemClickListener() 
				{

				        public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
						{
								
								
								
								contact = ((TextView) view).getText().toString();
								
							
								view.setBackgroundColor(Color.GREEN);
								
								
								for(int i=0;i<list.size()-1;i++)
								{
									String listItem = list.get(i).toString();
									
									Log.w("List item is ",listItem);
									if(listItem==contact)
									{
										//phone = (String)list.get(list.indexOf(name)+1);
										phone=list.get(i+1).toString();
										name=listItem;
										Log.w("..........", "..........");
										break;
									}
									
								}

								Log.w("You have clicked", contact);
								//Intent i = new Intent(getApplicationContext(), SingleListItem.class);
								// sending data to new activity
					        	 // i.putExtra("contact", contact);
					        	 // startActivity(i);
								
								
				        }
				       
				      
				});
			   
	}
		    
		   

	public void onClick(View view)
	{
		switch(view.getId())
		{
			case R.id.btnAdd:
			{
				
				Toast.makeText(this, "Add Contact", Toast.LENGTH_SHORT).show();
				
				//dbHandler.addContact("Khan", "8652664408");
				//Toast.makeText(this, "Contact added Successfully", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(ContactList.this, NewContact.class);
      	        startActivity(intent);
				break;

			}
			case R.id.btnDelete:
			{
				Toast.makeText(this, "Delete Contact", Toast.LENGTH_SHORT).show();
				if(contact==null)
				{
					Toast.makeText(this, "Please choose Contact to Delete", Toast.LENGTH_SHORT).show();
				}
				else
				{
					
					
					int count = adapter.getCount();
					for (int i = 0; i < count; i++) {
					
					adapter.remove(phone);
					 adapter.remove(contact);
					
					// int pos = adapter.getPosition(contact);
					// pos = pos+1;
				//	 adapter.remove(adapter.getItem(pos));
					 
					}
					Toast.makeText(this, "Deleting Contact", Toast.LENGTH_SHORT).show();
					dbHandler.deleteContact(name, phone);
					Toast.makeText(this, "Contact Deleted Successfully", Toast.LENGTH_SHORT).show();
					
				}
				break;

			}
			case R.id.btnUpdate:
			{
				
					//Toast.makeText(this, "Please choose Contact to Update", Toast.LENGTH_SHORT).show();
				Toast.makeText(this, "Update Contact", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(ContactList.this, UpdateContact.class);
				intent.putExtra("name", name);
				intent.putExtra("phone", phone);
				
				//Log.w("The valued to update is ",name +" " +phone);
			
				
				 startActivity(intent);
				
				
				break;

			}
			case R.id.btnLogOut:
			{
				Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(ContactList.this, MainActivity.class);
      	        startActivity(intent);
      	        break;

			}
				
		}
	}




}
