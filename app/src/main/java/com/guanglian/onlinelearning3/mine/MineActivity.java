package com.guanglian.onlinelearning3.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.guanglian.onlinelearning3.R;
import com.guanglian.onlinelearning3.dicovery.DiscoveryActivity;
import com.guanglian.onlinelearning3.exam.ExamActivity;

public class MineActivity extends AppCompatActivity {
    private ImageView titleMessage;
    private TextView titleContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_mine);

        //进入消息事件
        titleMessage=(ImageView)findViewById(R.id.title_message);
        titleMessage.setVisibility(View.INVISIBLE);

        titleContent=(TextView)findViewById(R.id.title_text);
        titleContent.setText("我的");
    }
    public void onClickUserinfo(View v){
        Intent intent = new Intent();
        intent.setClass(MineActivity.this, AccountActivity.class);
        MineActivity.this.startActivity(intent);
    }

    public void onCilckMessage(View v){
        Intent intent = new Intent();
        intent.setClass(MineActivity.this, MessageActivity.class);
        MineActivity.this.startActivity(intent);
    }

}
