package com.example.mpj.test;

/**
 * Created by mpj on 15-1-24.
 */

import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private List<String> title=new ArrayList<String>();
    private List<String> net=new ArrayList<String>();


    /**
     * 获取职位信息列表
     * @param strURL 职位信息来源网址
     */
    public void getData(String strURL)  {
        //String strURL="http://www.yingjiesheng.com/major/jisuanji/nanjing/";
        // 网络链接
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        try {
            //连接网络并判断是否成功连接
            URL url = new URL(strURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            if (httpConn.getResponseCode() != 200)
            Log.w("error","net error");

            //网页的输入流，设置编码格式
            InputStream in=httpConn.getInputStream();
            InputStreamReader input = new InputStreamReader(in, "utf-8");
            BufferedReader bufReader = new BufferedReader(input);
            String line = "";
            StringBuilder contentBuf = new StringBuilder();

            //追加文本
            int i=0;
            while ((line = bufReader.readLine()) != null) {
                contentBuf.append(line);
            }

            String buf = contentBuf.toString();
            String regex = "<a\\x20style=\\x22font-weight: bold\\x22.*?/a>";
            //获取超链接语句
            Pattern pt = Pattern.compile(regex);
            Matcher mt = pt.matcher(buf);
            while (mt.find()) {
            String s2 = ">.+</a>";//标题部分
            String s3 = "href=\\x22[^\\x22]*[^\\x22]*\\x22";

            Pattern pt2 = Pattern.compile(s2);
            Matcher mt2 = pt2.matcher(mt.group());
            while (mt2.find()) {
                String str1 = mt2.group().replaceAll(">|</a>", "");
                title.add(str1);
//                Log.w("content",title.get(i));

            }

            Pattern pt3 = Pattern.compile(s3);
            Matcher mt3 = pt3.matcher(mt.group());
            while (mt3.find()) {
                String str2 = mt3.group().replaceAll("href=\\x22", "");
                str2 = str2.replaceAll("\\x22", "");
                net.add(str2);
                Log.w("content",net.get(i));
                i++;
            }
        }

            Log.e("length",title.size()+"");
            MainActivity main=new MainActivity();
            //main.setTitle(title);
           // main.setNet(net);
            main.title=title;
            main.net=net;
        }
        catch(Exception e){
            Log.e("error","zhe?");
        }
    }

    /**
     * 标号1是将所用信息截取出来，标号2是将信息内部的多余信息剔除掉
     * @param strURL  查询的网址
     * @return
     * @throws Exception
     */
    public JobInfo getDetailed (String strURL) throws Exception {

        String title_1="<h1>.*?</h1>";//标题部分
        String title_2="<h1>|</h1>";
        String company_1="<h2><a.*?</a></h2>";
        String company_2="<.*?>";
        String benefits_1=" <div\\x20style=\\x22width:683px;\\x22\\x20class=\\x22welfare-tab-box\\x22>.*? </div>";
        String bendfits_2=" <div\\x20style=\\x22width:683px;\\x22\\x20class=\\x22welfare-tab-box\\x22>|<span>|</span>|</div>";
        String pay_1="职位月薪.*?</strong>";
        String pay_2="职位月薪：</span><strong>|</strong>";
        String address_1="工作地点.*?</strong>";
        String address_2="工作地点：</span><strong>|<a.*?>|</a>|</strong>";
        String data_1="发布日期.*?</strong>";
        String data_2="发布日期：</span><strong><span.*?>|</span></strong>";
        String nature_1= "工作性质.*?</strong>";
        String nature_2="工作性质：</span><strong>|</strong>";
        String experience_1= "工作经验.*?</strong>";
        String experience_2="工作经验：</span><strong>|</strong>";
        String minedu_1= "最低学历.*?</strong>";
        String minedu_2="最低学历：</span><strong>|</strong>";
        String numpeople_1= "招聘人数.*?</strong>";
        String numpeople_2="招聘人数：</span><strong>|</strong>";
        String jobkind_1="职位类别.*?</a>?";
        String jobkind_2="职位类别：</span><strong><a.*?><a.*?>|</a>";
        String description_1=" <!-- SWSStringCutStart -->.*?<!-- SWSStringCutEnd -->";
        String description_2="<.*?>|&nbsp;";
        String introduction_1="<h5>.*?</h3>";
        String introduction_2="<.*?>|&nbsp;|(该公司其他职位)";
         String title;
         String company;
         String benefits;
         String pay;
         String address;
         String data;
         String nature;
         String experience;
         String minedu;
         String numpeople;
         String jobkind;
         String decription;
         String introduction;

        URL url = new URL(strURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        InputStreamReader input = new InputStreamReader(httpConn
                .getInputStream(), "utf-8");
        BufferedReader bufReader = new BufferedReader(input);
        String line = "";
        StringBuilder contentBuf = new StringBuilder();
        while ((line = bufReader.readLine()) != null) {
            contentBuf.append(line);
        }
        String buf = contentBuf.toString();
//        buf=buf.replaceAll("<BR>","\n");
//        buf=buf.replaceAll("<br>","\n");
//        buf=buf.replaceAll("<br/>","\n");
       // Log.e("",buf);
        String regex="<!--20140610liuhuili-->.*?<!--相似职位推荐-->";
        Pattern pt=Pattern.compile(regex);
        Matcher mt=pt.matcher(buf);
        while(mt.find()) {
             title=getInformation(title_1,title_2,mt);
             company=getInformation(company_1,company_2,mt);
             benefits=getInformation(benefits_1,bendfits_2,mt);
             pay=getInformation(pay_1, pay_2,mt);
             address=getInformation(address_1, address_2,mt);
             data=getInformation(data_1, data_2,mt);
             nature=getInformation(nature_1, nature_2,mt);
             experience=getInformation(experience_1, experience_2,mt);
             minedu=getInformation(minedu_1, minedu_2,mt);
             numpeople=getInformation(numpeople_1, numpeople_2,mt);
             jobkind=getInformation(jobkind_1, jobkind_2,mt);
             decription=getStyleInformation(description_1, description_2, mt);
             introduction=getStyleInformation(introduction_1, introduction_2, mt);
            JobInfo job=new JobInfo();
            job.setAddress(address);
            job.setBenefits(benefits);
            job.setCompany(company);
            job.setData(data);
            job.setDecription(decription);
            job.setExperience(experience);
            job.setIntroduction(introduction);
            job.setJobkind(jobkind);
            job.setMinedu(minedu);
            job.setNature(nature);
            job.setNumpeople(numpeople);
            job.setPay(pay);
            job.setTitle(title);
            return job;
        }


        return null;
    }

    /**
     * 将信息修改得到自己需要的信息
     * @param str1 曾则表达式获取相关信息
     * @param str2 正则表达式将多余的信息删除
     * @param mt
     * @return
     * @throws IOException
     */
    public String getInformation(String str1,String str2,Matcher mt) throws IOException {


        Pattern pt1=Pattern.compile(str1);
        Matcher mt1=pt1.matcher(mt.group());
        while(mt1.find())
        {

            str1=mt1.group().replaceAll(str2," ");

            System.out.println(str1);
            break;
        }
        //str1=str1.replaceAll("\\s*?", " ");
        return str1;
    }

    public String getStyleInformation(String str1,String str2,Matcher mt)throws IOException{


            Pattern pt1 = Pattern.compile(str1);
            Matcher mt1 = pt1.matcher(mt.group());

        //Log.e("",mt.group().replaceAll("<BR>|<br>|<br/>","\n"));
         //  mt1.group().replaceAll("<BR>|<br>|<br/>","\n");
            while (mt1.find()) {
                str1 = mt1.group().replaceAll("<BR>|<br>|<br/>|</p>","\n");
                str1=str1.replaceAll(str2,"");
                break;
            }
            //str1=str1.replaceAll("\\s*?", " ");
            return str1;
        }

    public List<String> getNet() {
        return net;
    }

    public List<String> getTitle() {
        return title;
    }

}


