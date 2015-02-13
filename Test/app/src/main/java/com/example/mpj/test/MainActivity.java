package com.example.mpj.test;


import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import android.content.Intent;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;

import static android.content.Context.CONTEXT_RESTRICTED;


public class MainActivity extends Activity {


    public List<String> title = new ArrayList<String>();
    public List<String> net = new ArrayList<String>();
    private ListView listView;
    private TextView textView;
    private LinearLayout progressBar;
    private String url;
    private Utils util = new Utils();
    Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lv);
        textView= (TextView) findViewById(R.id.textView3);
        progressBar= (LinearLayout) findViewById(R.id.progressBar);


        url=this.getIntent().getExtras().getString("URL");
       //fillDate();


        progressBar.setVisibility(View.INVISIBLE);
        try {
                            util.getData(url);

                        } catch (Exception E) {

                            Log.e("error", "nullpoint");
                        }
        if(util.getTitle().size()==0){
                            textView.setText("暂无数据");
                        }
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.listview_style, util.getTitle()));


        Log.e("长度", util.getTitle().size() + "");

            listView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    // TODO Auto-generated method stub


                    Intent intent = new Intent();

                    intent.putExtra("net", util.getNet().get(arg2));

                    intent.setClass(MainActivity.this, DetailedActivity.class);

                    startActivity(intent);


                }

            });

    }




//    private void fillDate() {
//       // ll_loading.setVisibility(View.VISIBLE);
//        progressBar.setVisibility(View.VISIBLE);
//        new Thread(){
//            public void run(){
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //progressBar.setVisibility(View.VISIBLE);
//
//                        try {
//
//                            util.getData(url);
//
//                        } catch (Exception E) {
//
//                            Log.e("error", "nullpoint");
//                        }
//                try {
//                    sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                        progressBar.setVisibility(View.INVISIBLE);
//                        if(util.getTitle().size()==0){
//                            textView.setText("暂无数据");
//                        }
//                        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.listview_style, util.getTitle()));
//                    }
//                });
//            }
//        }.start();
//
//    }

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





