package com.github.barteksc.sample.image;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.github.barteksc.sample.R;

public class ImageActivity extends AppCompatActivity {

    private ImageView imageView;
    private String url;
    private Handler handler=new Handler();
    private int start=0,end=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageView= (ImageView) findViewById(R.id.iv_image);


        Bundle bundle = getIntent().getExtras();
        url = bundle.getString("url");
        setTitle(getFileNameFromUrl(url));
        //url="http://172.23.114.2:8080/web/image.jpg";
        new HttpImage(url, handler, imageView).start();
    }

    public String getFileNameFromUrl(String url){
        //url="http://172.23.114.2:8080/web/image.jpg";
        for(int i=(url.length()-1);i>=0;i--) {
            end=url.length();
            if ('/' == (url.charAt(i))) {
                start = i + 1;
                break;
            }
        }
        return url.substring(start,end);
    }
}
