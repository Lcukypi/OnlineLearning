package com.guanglian.onlinelearning3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class RegistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_regist);
    }

    public void toLoginActivity(View view){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        Toast.makeText(this,"恭喜您，账号已经注册成功，赶紧开启学习征程吧",Toast.LENGTH_LONG).show();
    }
}
