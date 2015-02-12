package com.example.mpj.test;

import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DetailedActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        String net = this.getIntent().getExtras().getString("net");

        Log.e("net", net);
        final Utils util=new Utils();
        try {
            final JobInfo job=util.getDetailed(net);
//            TextView textView= (TextView) findViewById(R.id.textView);
//
//            textView.setText("职位:"+job.getTitle()+"公司："+job.getCompany()+"福利:"+job.getBenefits()+"职位月薪:"+job.getPay()+
//            "工作地点："+job.getAddress()+"发布时间："+job.getData()+"工作性质:"+job.getNature()+"工作经验:"+job.getExperience()+
//            "最低学历:"+job.getMinedu()+"招聘人数:"+job.getNumpeople()+"职位类别:"+job.getJobkind()+"职位描述："+job.getDecription()+
//             "公司简介："+job.getIntroduction());
            //textView.setMovementMethod(new ScrollingMovementMethod());
            TextView jobname= (TextView) findViewById(R.id.jobname);
            jobname.setText("职位:"+job.getTitle());
            TextView company= (TextView) findViewById(R.id.company);
            company.setText("公司："+job.getCompany());
            TextView benefits= (TextView) findViewById(R.id.benefits);
            benefits.setText("福利:"+job.getBenefits());

            TextView pay= (TextView) findViewById(R.id.pay);
            pay.setText("职位月薪:"+job.getPay());
            TextView address= (TextView) findViewById(R.id.address);
            address.setText("工作地点："+job.getAddress());
            TextView data= (TextView) findViewById(R.id.data);
            data.setText("发布时间："+job.getData());
            TextView nature= (TextView) findViewById(R.id.nature);
            nature.setText("工作性质:"+job.getNature());
            TextView experience= (TextView) findViewById(R.id.experience);
            experience.setText("工作经验:"+job.getExperience());
            TextView minedu= (TextView) findViewById(R.id.minedu);
            minedu.setText("最低学历:"+job.getMinedu());
            TextView numpeople= (TextView) findViewById(R.id.numpeople);
            numpeople.setText("招聘人数:"+job.getNumpeople());
            TextView jobkind= (TextView) findViewById(R.id.jobkind);
            jobkind.setText("职位类别:"+job.getJobkind());


            final String str1=job.getDecription();
            final String str2=job.getIntroduction();

            Button decription= (Button) findViewById(R.id.decription);
            Button intruction= (Button) findViewById(R.id.intruction);


            decription.setOnClickListener(new View.OnClickListener(){

                public void onClick(View v){

                    Intent intent=new Intent();

                    intent.putExtra("decription", str1);

                    intent.setClass(DetailedActivity.this, Decription.class);

                    startActivity(intent);

                }

            });

            intruction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();

                    intent.putExtra("intruction", str2);

                    intent.setClass(DetailedActivity.this, Introduction.class);

                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }



    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_deatiled, menu);
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
