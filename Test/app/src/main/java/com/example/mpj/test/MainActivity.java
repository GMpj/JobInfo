package com.example.mpj.test;


import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.widget.AdapterView.OnItemClickListener;



public class MainActivity extends Activity {


    public List<String> title = new ArrayList<String>();
    public List<String> net = new ArrayList<String>();
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Utils util = new Utils();
        try {
            util.getData("http://sou.zhaopin.com/jobs/searchresult.ashx?bj=160000&in=210500%3B160400&jl=%E5%8D%97%E4%BA%AC&sm=0&el=4&isfilter=1&p=1&we=0000");
        } catch (Exception E) {

            Log.e("error", "nullpoint");
        }
        listView = (ListView) findViewById(R.id.lv);


        Log.e("长度", util.getTitle().size() + "");
        //Log.w("ccccc",title.get(0));
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, util.getTitle()));


        listView.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView <?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub


                Intent intent=new Intent();

                intent.putExtra("net", util.getNet().get(arg2));

                intent.setClass(MainActivity.this, DetailedActivity.class);

                startActivity(intent);


            }

        });
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


}





