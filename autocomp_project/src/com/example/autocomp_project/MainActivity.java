package com.example.autocomp_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.R.array;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	ArrayList<String> item = new ArrayList<String>();
	SQLiteDatabase db;
	AutoCompleteTextView tv;
	ArrayAdapter<String> adapter;
	Context c;
	String trains[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c=this;
        item.add("Please search...");
       
		db=openOrCreateDatabase("mydb",MODE_APPEND,null);
		db.execSQL("CREATE TABLE IF NOT EXISTS trains5 (train TEXT)");
		Cursor cur=db.query("trains5", null,null,null,null,null,null);
		 
		try {
		if(cur.getCount()==0){
			 InputStream is;
		        String json;
		        byte[] buffer = null;
				
					is = getAssets().open("allTrainsList.txt");
				
		        int size = is.available();
		        buffer = new byte[size];
		        is.read(buffer);
		        is.close();
		        json = new String(buffer, "UTF-8");
		        
		        trains=json.split(",");
		        
		        Log.d("size",Integer.toString(trains.length));
		        
		        
				
			
		for(int i=0;i<trains.length;i++){
			db.execSQL("INSERT INTO trains5 VALUES('"+trains[i]+"')");
			item.add(trains[i]);
				}
		
		}
		/* c=db.query("stations_2",null,null,null,null,null,null);
		Log.d("tag",Integer.toString(c.getCount()));
		while(c.moveToNext()){
			Log.d("my","xyz");
			Toast.makeText(getApplicationContext(),c.getString(0), Toast.LENGTH_SHORT).show();
		}
		}catch(Exception e){
			Log.e("ERROR",e.getMessage(), e);
		}*/
	tv=(AutoCompleteTextView) findViewById(R.id.a);
		tv.setThreshold(3);
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
		tv.setAdapter(adapter);
		Log.d("size2",trains[1]);
		tv.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				ArrayList<String> item2 = new ArrayList<String>();
				Log.d("tag",s.toString());
				Cursor cursor=db.query("trains5",null,"train like '"+s.toString()+"%'",null,null,null,null);
				while(cursor.moveToNext()){
					item2.add(cursor.getString(0));
				}
				item=item2;
				adapter.notifyDataSetChanged();
				 adapter = new ArrayAdapter<String>(c, android.R.layout.simple_dropdown_item_1line,item);
			        tv.setAdapter(adapter);
			         
				//array=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line);
		}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		}catch(Exception e){
			Log.e("ERROR",e.getMessage(), e);
		}
	
		
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
