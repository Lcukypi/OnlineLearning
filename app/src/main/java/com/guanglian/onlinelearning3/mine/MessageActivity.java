package com.guanglian.onlinelearning3.mine;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.guanglian.onlinelearning3.R;

public class MessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }

    public void messageBack(View v){

        MessageActivity.this.finish();
    }
}

