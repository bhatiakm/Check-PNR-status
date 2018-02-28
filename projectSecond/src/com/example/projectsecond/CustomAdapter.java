package com.example.projectsecond;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter{
	

	Context c;
	ArrayList<MyClass> entries=new ArrayList<MyClass>();
		LayoutInflater mLayoutInflater;  
		public CustomAdapter(Context context, int resource,
				List<MyClass> objects) {
			entries=(ArrayList<MyClass>) objects;
			c=context;
			mLayoutInflater=(LayoutInflater) c
	                 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// TODO Auto-generated constructor stub
		}
		
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return entries.size();
	}

	@Override
	public MyClass getItem(int position) {
		// TODO Auto-generated method stub
		return entries.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout listview;
		                                     
            listview = (LinearLayout) mLayoutInflater.inflate(
                     R.layout.listlayout, parent, false);

        
		
		MyClass obj=getItem(position);
		TextView tname=(TextView) listview.findViewById(R.id.tname);
		tname.setText(obj.tname);
		TextView tno=(TextView) listview.findViewById(R.id.tno);
		tno.setText(obj.tno);
		TextView dep=(TextView) listview.findViewById(R.id.src_dept);
		dep.setText(obj.dep);
		TextView arr=(TextView) listview.findViewById(R.id.arr_time);
		arr.setText(obj.arr);
		Log.d("my","egyde");
		TableLayout days=(TableLayout) listview.findViewById(R.id.days);
		TableRow row=new TableRow(c);
		TextView col1=new TextView(c);
		col1.setText("Running days");
		row.addView(col1);
		final Button alt=(Button) listview.findViewById(R.id.altertive);
		alt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(c,alt.getText(),Toast.LENGTH_SHORT).show();
				
			}
		});
		for(int i=0;i<obj.days.size();i++){
			TextView col=new TextView(c);
			col.setText(obj.days.get(i)+"  ");
			row.addView(col);
		}
		days.addView(row);
		LinearLayout classes=(LinearLayout) listview.findViewById(R.id.classes);
		for(int i=0;i<obj.classes.size();i++){
			final Button col=new Button(c);
			col.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(c,col.getText(),Toast.LENGTH_SHORT).show();
				}
			});
			col.setText(obj.classes.get(i)+"  ");
			classes.addView(col);
		}/////////CHANGEEEEEEEEEE
		TextView t=(TextView) listview.findViewById(R.id.availability);
		t.setText(obj.availability);
		return listview;
		
		
		
	}

	
	

}
