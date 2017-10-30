package com.guanglian.onlinelearning3.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.guanglian.onlinelearning3.R;
import com.guanglian.onlinelearning3.mine.MessageActivity;
import com.guanglian.onlinelearning3.study.StudyActivity;

import static android.R.id.message;

public class ExamActivity extends AppCompatActivity {

    private TextView title;
    private ImageView titleMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_exam);

        title = (TextView)findViewById(R.id.title_text);
        title.setText("我的考试");

        //进入消息事件
        titleMessage=(ImageView)findViewById(R.id.title_message);
        titleMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExamActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });
    }

    public void toExamSimulation(View view){
        Intent intent = new Intent(this, ExamSimulationActivity.class);
        startActivity(intent);
    }
}
