package com.guanglian.onlinelearning3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private ImageView imageView;
    private EditText account;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        imageView=(ImageView)findViewById(R.id.iv_background);

        account=(EditText)findViewById(R.id.et_account);
        password=(EditText)findViewById(R.id.et_password);
    }

    public void toMainActivity(View view){
        String acout=account.getText().toString().trim();
        String paw=password.getText().toString().trim();
        if("admin".equals(acout)&&"123".equals(paw)){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this,"输入账号或密码错误，请重新输入",Toast.LENGTH_LONG).show();
        }

    }

    public void toRegistActivity(View view){
        Intent intent=new Intent(this,RegistActivity.class);
        startActivity(intent);

    }

}
