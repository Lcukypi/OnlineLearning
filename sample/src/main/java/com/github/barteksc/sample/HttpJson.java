package com.github.barteksc.sample;

import android.os.Handler;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.greenrobot.event.EventBus;

/**
 * Created by dell on 2017/8/15.
 */

public class HttpJson extends Thread{
    private String url;
    private Handler handler;
    private ListView listView;
    private JsonAdapter adapter;
    private List<File> files;

    public HttpJson(String url, Handler handler, ListView listView, JsonAdapter adapter) {
        this.url = url;
        this.handler = handler;
        this.listView = listView;
        this.adapter = adapter;
    }

    @Override
    public void run() {
        try {
            URL httpUrl=new URL(url);
            HttpURLConnection conn=(HttpURLConnection)httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb=new StringBuffer();
            String str;
            while((str=reader.readLine())!=null){
                sb.append(str);
            }
            final List<File> data=parseJson(sb.toString());

            EventBus.getDefault().post(new FirstEvent(data));//使用EvenBus发送data数据

            handler.post(new Runnable() {
                @Override
                public void run() {
                    adapter.setData(data);
                    listView.setAdapter(adapter);
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<File> parseJson(String json){
        try {
            JSONObject object = new JSONObject(json);
            files = new ArrayList<>();

            JSONArray fileData = object.getJSONArray("file");

            for(int i=0;i<fileData.length();i++){
                File file=new File();
                files.add(file);

                JSONObject fileObject=fileData.getJSONObject(i);
                int id=fileObject.getInt("id");
                String name=fileObject.getString("name");
                String size=fileObject.getString("size");
                String type=fileObject.getString("type");
                int page=fileObject.getInt("lastPageView");
                String fileURL=fileObject.getString("fileURL");

                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.getDefault());
                String currentTime=sdf.format(new Date());

                file.setId(id);
                file.setName(name);
                file.setTime(currentTime);
                file.setSize(size);
                file.setType(type);
                file.setLastPageView(page);
                file.setFileURL(fileURL);
            }
            return files;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }//    }
}
