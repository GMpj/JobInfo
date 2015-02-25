package com.example.mpj.test;


import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import android.os.Bundle;
import android.content.Intent;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;




public class MainActivity extends Activity {


    public List<String> title = new ArrayList<String>();
    public List<String> net = new ArrayList<String>();
    private ListView listView;
    private TextView textView;
    private ProgressBar progressBar;
    private String url;
    private Utils util = new Utils();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lv);
        textView= (TextView) findViewById(R.id.textView3);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);


        url=this.getIntent().getExtras().getString("URL");

        //设置progressbar的显示状态
        progressBar.setVisibility(View.VISIBLE);

        //启动进程
        MyThread m = new MyThread();
        new Thread(m).start();



    }

    //创建handler
    Handler updateBarHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            //修改progressbar的状态
            progressBar.setVisibility(View.INVISIBLE);

            //判断是否有数据
            if(util.getTitle().size()==0){
                textView.setText("暂无数据");
            }
            else {
                //绑定listview的数据和事件
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.listview_style, util.getTitle()));
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
        }
    };




    //使用线程进程数据处理
    class MyThread implements Runnable {
        public void run() {

            try {
                util.getData(url);

            } catch (Exception E) {

                Log.e("error", "nullpoint");
            }
            // 向Handler发送消息,更新UI
            MainActivity.this.updateBarHandler.sendMessage(new Message());

        }
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





