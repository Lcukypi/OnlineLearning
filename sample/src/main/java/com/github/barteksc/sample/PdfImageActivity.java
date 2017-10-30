package com.github.barteksc.sample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.barteksc.sample.image.ImageActivity;
import com.github.barteksc.sample.pdf.PDFViewActivity;

import java.util.List;

import de.greenrobot.event.EventBus;

public class PdfImageActivity extends AppCompatActivity {

    private String url;
    private Handler handler = new Handler();
    private ListView listView;
    private JsonAdapter adapter;
    private List<File> list;
    String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_image);
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new JsonAdapter(this);

//        SimpleDateFormat formatter=new SimpleDateFormat ("yyyy年MM月dd日    HH:mm:ss     ");
//        Date curDate=new Date(System.currentTimeMillis());//获取当前时间       
//        String currentTime=formatter.format(curDate);

//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.getDefault());
//        currentTime=sdf.format(new Date());
//        Toast.makeText(PdfImageActivity.this, "当前时间为："+currentTime, Toast.LENGTH_LONG).show();

        EventBus.getDefault().register(this);//注册EvenBus

        //url = "http://172.23.114.2:8080/web/OnlineLearning.json";
        url = "http://192.168.43.135:8080/web/FileServlet";
        new HttpJson(url, handler, listView, adapter).start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position = position + 1;
                String showText = "点击第" + position + "项";
                position = position - 1;

                File file = list.get(position);
                String type=file.getType();

                if("pdf".equals(type)){
                    Intent intent= new  Intent(PdfImageActivity.this,PDFViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url",file.getFileURL());
                    bundle.putInt("lastPageView",file.getLastPageView());
                    bundle.putInt("id",file.getId());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if("image".equals(type)){
                    Intent intent= new  Intent(PdfImageActivity.this,ImageActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url",file.getFileURL());
                    bundle.putInt("lastPageView",file.getLastPageView());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                Toast.makeText(PdfImageActivity.this, showText + "\n" + "fileUrl=" + file.getFileURL() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onEventMainThread(FirstEvent event) {
        list=event.getMsg();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
