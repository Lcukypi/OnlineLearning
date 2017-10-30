package com.github.barteksc.sample;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell on 2017/8/15.
 */

public class JsonAdapter extends BaseAdapter {

    private List<File> list;
    private Context context;
    private LayoutInflater inflater;
    private Handler handler=new Handler();

    public JsonAdapter(List<File> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    public JsonAdapter(Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    public void setData(List<File> data){
        this.list=data;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold viewHold=null;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item,null);
            viewHold=new ViewHold(convertView);
            convertView.setTag(viewHold);
        } else{
            viewHold=(ViewHold) convertView.getTag();
        }

        File file=list.get(position);
        viewHold.tvName.setText(file.getName());
        viewHold.tvTime.setText(file.getTime());
        viewHold.tvSize.setText(file.getSize());
        viewHold.tvPage.setText(""+file.getLastPageView());
        //viewHold.fileURL.setText(file.getFileURL());

        String type=file.getType();

        switch (type){
            case "pdf":
                viewHold.imageView.setImageResource(R.drawable.icon_pdf);
                break;
            case "image":
                viewHold.imageView.setImageResource(R.drawable.icon_image);
                break;
            default:
                viewHold.imageView.setImageResource(R.drawable.icon_pdf);
                break;
        }

        return convertView;
    }
    class ViewHold{
        private ImageView imageView;
        private TextView tvName,tvTime,tvSize,tvPage, fileURL;

        public ViewHold(View view){
            imageView=(ImageView)view.findViewById(R.id.iv_icon);
            tvName=(TextView)view.findViewById(R.id.tv_name);
            tvTime=(TextView)view.findViewById(R.id.tv_time);
            tvSize=(TextView)view.findViewById(R.id.tv_size);
            tvPage=(TextView)view.findViewById(R.id.tv_page);
            //fileURL =(TextView)view.findViewById(R.id.fileURL);

        }


    }
}
