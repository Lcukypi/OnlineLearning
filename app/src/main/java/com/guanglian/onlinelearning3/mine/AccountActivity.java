package com.guanglian.onlinelearning3.mine;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.guanglian.onlinelearning3.R;

public class AccountActivity extends Activity {


    private EditText et_name;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        et_name = findViewById(R.id.et_name);
        et_name.setFocusable(true);
        et_name.setFocusableInTouchMode(true);
        et_name.setGravity(Gravity.RIGHT);

        et_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    et_name.setCursorVisible(true);
                }
            }
        });

    }



    public void ueserinfoBack(View v){

        AccountActivity.this.finish();

    }


    public void userinfoExit(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("系统提示");//设置标题
        builder.setIcon(R.drawable.warning);//设置图标
        builder.setMessage("确定要退出当前账号吗？");//设置内容

        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(AccountActivity.this,"点击了确定按钮",Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(AccountActivity.this,"点击了取消按钮",Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog=builder.create();//获取dialog
        dialog.show();//显示对话框
    }
}

