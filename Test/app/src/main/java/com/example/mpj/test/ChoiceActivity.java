package com.example.mpj.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ChoiceActivity extends Activity {


        private static final String[] kind={"互联网/电子商务","计算机软件","IT服务(系统/数据/维护)","通信/电信/网络设备",
                "通信/电信运营、增值服务","电子技术/半导体/集成电路","银行","房地产/建筑/建材/工程","专业服务/咨询(财会/法律/人力资源等)"
                ,"广告/会展/公关","外包服务","贸易/进出口","教育/培训/院校","汽车/摩托车","大型设备/机电设备/重工业","医药/生物工程","交通/运输"
                ,"酒店/餐饮","媒体/出版/影视/文化传播","能源/矿产/采掘/冶炼","石油/石化/化工","电气/电力/水利","环保"};
        private static final String[] url1={"in=210500","in=160400","in=160000","in=300100","in=160100","in=160100","in=300500"
                ,"in=140000","in=200300","in=200302","in=300300","in=170500","in=201100","in=121000","in=129900","in=121300","in=150000"
                ,"in=200600","in=210300","in=130000","in=120500","in=130100","in=201200"};
        private static final String[] city={"南京","北京","上海","杭州","广州","深圳","天津","武汉","西安","成都","大连","青岛",
                "苏州","无锡","宁波","重庆","郑州","长沙","福州","厦门","哈尔滨","石家庄","合肥","长春"};
        private static final String[] url2={"%E5%8D%97%E4%BA%AC","%E5%8C%97%E4%BA%AC","%E4%B8%8A%E6%B5%B7",
                "%E6%9D%AD%E5%B7%9E","%E5%B9%BF%E5%B7%9E","%E6%B7%B1%E5%9C%B3","%E5%A4%A9%E6%B4%A5","%E6%AD%A6%E6%B1%89",
                "%E8%A5%BF%E5%AE%89","%E6%88%90%E9%83%BD","%E5%A4%A7%E8%BF%9E","%E9%9D%92%E5%B2%9B","%E8%8B%8F%E5%B7%9E",
                "%E6%97%A0%E9%94%A1","%E5%AE%81%E6%B3%A2","%E9%87%8D%E5%BA%86","%E9%83%91%E5%B7%9E","%E9%95%BF%E6%B2%99",
                "%E7%A6%8F%E5%B7%9E","%E5%8E%A6%E9%97%A8","%E5%93%88%E5%B0%94%E6%BB%A8","%E7%9F%B3%E5%AE%B6%E5%BA%84",
                "%E5%90%88%E8%82%A5","%E9%95%BF%E6%98%A5"};


    private String URL;
    private String JOB;
    private String CITY;
    private Spinner spinner1;
    private Spinner spinner2;
    private Button button;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);


        spinner1 = (Spinner) findViewById(R.id.spinner1);
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,kind);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinner1.setAdapter(adapter);
        //添加事件Spinner事件监听
        spinner1.setOnItemSelectedListener(new Spinner1SelectedListener());
        //设置默认值
        spinner1.setVisibility(View.VISIBLE);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,city);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinner2.setAdapter(adapter);
        //添加事件Spinner事件监听
        spinner2.setOnItemSelectedListener(new Spinner2SelectedListener());
        //设置默认值
        //spinner2.setVisibility(View.VISIBLE);

        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                Intent intent=new Intent();

                URL="http://sou.zhaopin.com/jobs/searchresult.ashx?"+JOB+"&jl="+CITY+"&isadv=0&el=4&isfilter=1&p=1&we=0000";
                Log.e("ERROR",URL);
                intent.putExtra("URL", URL);

                intent.setClass(ChoiceActivity.this, MainActivity.class);

                startActivity(intent);

            }

        });

        //Log.e("ERROR",URL);
    }

    //使用数组形式操作
    class Spinner1SelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {

            JOB=url1[arg2];


        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    class Spinner2SelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {


            CITY=url2[arg2];

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_choice, menu);
//        return true;
//    }
//
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
