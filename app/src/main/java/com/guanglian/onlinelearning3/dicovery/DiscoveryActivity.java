package com.guanglian.onlinelearning3.dicovery;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.guanglian.onlinelearning3.R;
import com.guanglian.onlinelearning3.mine.MessageActivity;

public class DiscoveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_discovery);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        TextView textView =(TextView)findViewById(R.id.title_text);


        textView.setText("爱分享");
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        ImageView titleMessage=(ImageView)findViewById(R.id.title_message);
        titleMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiscoveryActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });
    }
}
