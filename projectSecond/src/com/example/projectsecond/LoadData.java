package com.example.projectsecond;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.ListView;


public class LoadData extends AsyncTask<String,Void,ArrayList<MyClass>>
	{
	CustomAdapter myad;
	ArrayList<MyClass> my_list=new ArrayList<MyClass>();
	Context c;
	ListView lv;
	ProgressDialog pDialog;
	public LoadData(Context c,ListView lv) {
		// TODO Auto-generated constructor stub
		this.c=c;
		this.lv=lv;
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pDialog = new ProgressDialog(c);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

	}
	protected ArrayList<MyClass> doInBackground(String... urls) {
		
		String stations;
        
		URL url;
		try {
			url = new URL(urls[0]);
		
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        Log.d("some","abc");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        stations= reader.readLine();
        JSONObject json=new JSONObject(stations);
        JSONArray trains = json.getJSONArray("train");
        
        // looping through All Contacts
        for (int i = 0; i < trains.length(); i++)
        {
            JSONObject c = trains.getJSONObject(i);
            String name=c.getString("name");
            String number = c.getString("number");
            //  name=name+"-"+number;
            String src_departure_time = c.getString("src_departure_time");

            String dest_arrival_time = c.getString("dest_arrival_time");
            JSONArray classes = c.getJSONArray("classes");
            ArrayList<String> class_=new ArrayList<String>();
            
            for(int k=0;k<classes.length();k++)
            {
                JSONObject cl=classes.getJSONObject(k);
                String available=cl.getString("available");
                String class_code;
                if(available.contains("Y")){
                	class_code=cl.getString("code");
                	class_.add(class_code);
                }
                else if(available.contains("-")){
                	class_.add("3A");
                	class_.add("2A");
                	class_.add("SL");
                	break;
                }
           
            }
            
            JSONArray days = c.getJSONArray("days");
            ArrayList<String> days_=new ArrayList<String>();
            for(int k=0;k<days.length();k++)
            {
                JSONObject da=days.getJSONObject(k);
                

                String runs=da.getString("runs");
                String day_code;
                if(runs.contains("true")){
                	day_code=da.getString("day-code");
                	days_.add(day_code);
                }
            	}///////CHANGEEEEEEEE
    		String availability_json;
                 url=new URL(urls[1]);
                 connection= (HttpURLConnection) url.openConnection();
                 reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                 availability_json = reader.readLine();
                 json=new JSONObject(availability_json);
                 JSONArray availability=json.getJSONArray("availability");
                 JSONObject json_status = (JSONObject) availability.get(0);
                 String status=json_status.getString("status");
            
                 																			/////CHANGEEEEE
            MyClass my=new MyClass(number,name,src_departure_time,dest_arrival_time,days_, class_,status);
            Log.d("my",my.toString());
            my_list.add(my);

        }    	
               
        
            
        
		}
		catch(Exception e){
			Log.e("ERROR",e.getMessage(),e);
		}
		return my_list;
		}
		
	
	
	protected void onPostExecute(ArrayList<MyClass> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pDialog.dismiss();
		lv.setAdapter(new CustomAdapter(c, R.layout.listlayout,result));
		
		Log.d("my",Integer.toString(my_list.size()));
	}
	}