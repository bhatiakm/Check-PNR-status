package com.example.pnr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class LoadData extends AsyncTask<String,Void,ArrayList<String>>
{
Context c;
TextView tv;
TableLayout table;
public LoadData(Context c,TableLayout table,TextView tv) {
	// TODO Auto-generated constructor stub
	this.c=c;
	this.table=table;
	this.tv=tv;
}
protected ArrayList<String> doInBackground(String... urls) {
	
	ArrayList<String> array=new ArrayList<String>();
    String pnr_string;
	URL url;
	try {
		url = new URL(urls[0]);
	StringBuilder builder=new StringBuilder();
    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
    Log.d("some","abc");
    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    pnr_string= reader.readLine();
    JSONObject json=new JSONObject(pnr_string);
    Boolean chart=json.getBoolean("chart_prepared");
    if(chart)
    	array.add("Chart prepared");
    else
    	array.add("Chart not prepared");
    JSONArray passenger = json.getJSONArray("passengers");
    for(int i=0;i<passenger.length();i++){
    	JSONObject obj=passenger.getJSONObject(i);
    	builder.append(obj.getString("booking_status"));
    	builder.append("*");
    	builder.append(obj.getString("current_status"));
    	array.add(builder.toString());
    	Log.d("pnr",builder.toString());
    }
	}
	catch(Exception e){
		Log.e("ERROR",e.getMessage(),e);
	}
	return array;
	}
@Override
	protected void onPostExecute(ArrayList<String> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		tv.setText(result.get(0));
		for(int i=1;i<result.size();i++){
			TableRow row=new TableRow(c);
			TextView col1=new TextView(c);
			TextView col2=new TextView(c);
			String sub1=result.get(i).substring(0, result.get(i).indexOf("*")-1);
			String sub2=result.get(i).substring( result.get(i).indexOf("*")+1,result.get(i).length());
			TextView col3=new TextView(c);
			col1.setText(sub1);
			col2.setText(sub2);
			row.addView(col1);
			row.addView(col2);
			table.addView(row);
			
		}
		
	}
}