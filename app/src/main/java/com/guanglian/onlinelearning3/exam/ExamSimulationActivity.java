package com.guanglian.onlinelearning3.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.answer.AnalogyExaminationActivity;
import com.guanglian.onlinelearning3.R;

public class ExamSimulationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_exam_simulation);
    }

    public void toAnalogyExaminationActivity(View view){
        Intent intent=new Intent(ExamSimulationActivity.this,AnalogyExaminationActivity.class);
        startActivity(intent);
    }
}
