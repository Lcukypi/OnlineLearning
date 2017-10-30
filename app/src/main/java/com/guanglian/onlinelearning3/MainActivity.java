package com.guanglian.onlinelearning3;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.guanglian.onlinelearning3.dicovery.DiscoveryActivity;
import com.guanglian.onlinelearning3.exam.ExamActivity;
import com.guanglian.onlinelearning3.mine.MineActivity;
import com.guanglian.onlinelearning3.study.StudyActivity;

public class MainActivity extends TabActivity implements RadioGroup.OnCheckedChangeListener {
    /** Called when the activity is first created. */
    //定义的tabhost对象
    private TabHost mHost;
    //定义RadioGroup对象
    private RadioGroup radioderGroup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent =new Intent(MainActivity.this,StartService.class);
        startService(intent);



        //实例化TabHost
        mHost=this.getTabHost();

        //添加选项卡，并且设置跳转intent
        mHost.addTab(mHost.newTabSpec("ONE").setIndicator("ONE")
                .setContent(new Intent(this,StudyActivity.class)));
        mHost.addTab(mHost.newTabSpec("TWO").setIndicator("TWO")
                .setContent(new Intent(this,ExamActivity.class)));
        mHost.addTab(mHost.newTabSpec("THREE").setIndicator("THREE")
                .setContent(new Intent(this,DiscoveryActivity.class)));
        mHost.addTab(mHost.newTabSpec("FOUR").setIndicator("FOUR")
                .setContent(new Intent(this,MineActivity.class)));
        //得到radioGroup对象
        radioderGroup = (RadioGroup) findViewById(R.id.main_radio);
        //设置radioGroup对象的切换监听器
        radioderGroup.setOnCheckedChangeListener(this);


        //启动消息推送服务
//        Intent intent =new Intent(MainActivity.this,StartService.class);
//        startService(intent);
//        Toast.makeText(MainActivity.this,"服务启动成功",Toast.LENGTH_SHORT).show();
    }

    //实现OnCheckedChangeListener中的RadioGroup的选项切换回调函数
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //根据所选中的RadioGroup的选项id，设置tabhost的选项卡
        switch(checkedId){
            case R.id.rb_study:
                mHost.setCurrentTabByTag("ONE");
                break;
            case R.id.rb_exam:
                mHost.setCurrentTabByTag("TWO");
                break;
            case R.id.rb_discovery:
                mHost.setCurrentTabByTag("THREE");
                break;
            case R.id.rb_mine:
                mHost.setCurrentTabByTag("FOUR");
                break;
        }
    }
}