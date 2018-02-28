package com.example.projectsecond;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv=(ListView) findViewById(R.id.list);

		ArrayList<MyClass> list = null;
        new LoadData(this,lv).execute("https://api.railwayapi.com/v2/between/source/PUNE/dest/NGP/date/15-12-2017/apikey/3deiffhd1m/","http://api.railwayapi.com/v2/check-seat/train/11039/source/PUNE/dest/NGP/date/15-12-2017/class/3A/quota/GN/apikey/3deiffhd1m/");
        
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
