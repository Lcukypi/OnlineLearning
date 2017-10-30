package com.guanglian.onlinelearning3.study;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.guanglian.onlinelearning3.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);
    }
}
