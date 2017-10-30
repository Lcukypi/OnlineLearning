package com.guanglian.onlinelearning3.study;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.sample.PdfImageActivity;
import com.guanglian.onlinelearning3.R;
import com.guanglian.onlinelearning3.mine.MessageActivity;
import com.guanglian.onlinelearning3.study.recyclebanner.RecyclerBannerViewActivity;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.cumt.hsx.myvideo.Activity.VideoActivity;


public class StudyActivity extends AppCompatActivity {
    private final static int CHANGE_UI[]=new int[]{0,1,2,3,4,5,6,7,8};
    private final static int ERROR=1;

    String url[]={"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2596429667,132488892&fm=26&gp=0.jpg",
    "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2186252255,2361891605&fm=26&gp=0.jpg",
    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504179346774&di=cdb504931fca27b6e50f91e05e018e66&imgtype=0&src=http%3A%2F%2Fpic.nen.com.cn%2F500%2F15%2F58%2F76%2F15587655_559279.jpg",
    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504178815394&di=800122ca082c76b95903c6f6043ec24a&imgtype=0&src=http%3A%2F%2Fimg01.taopic.com%2F150306%2F318768-150306103S238.jpg",
    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504177917169&di=12cfb33f99c7e1be347b0001ceb0b0e2&imgtype=0&src=http%3A%2F%2Fimg.yanj.cn%2Fstore%2Fgoods%2F5196%2F5196_6da1a5f2abd8bb2ff0cc5af1bbb2bf87.png_max.png",
    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504177903098&di=4aaeeaf4e5b09cb10df9bcf1b535665c&imgtype=0&src=http%3A%2F%2Fa2.att.hudong.com%2F08%2F36%2F01300533969256134129365215554.png",
    "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2418848427,901280495&fm=26&gp=0.jpg",
    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504177635434&di=efbf02f78fc7f8a6891ce4de54da4d84&imgtype=0&src=http%3A%2F%2Fimages.qudao.com%2Fbrandimgs%2F2014-12-05%2F54816bec8d566_520_430.jpg"};


    private ImageView course1,course2,course3,course4,course5,course6,course7,course8;

    //悬浮按钮部分
    private PopupWindow mMenuPop;
    private ImageView mImageView;
    private int PopWidth;
    private int PopHeight;

    private ImageView titleMessage;

    //轮播图
    RecyclerBannerViewActivity pager;
    private List<RecyclerBannerViewActivity.BannerEntity> urls = new ArrayList<>();

    //课程图片
    private Handler handler = new Handler(){
        Bitmap bitmap[]=new Bitmap[10];
        @Override
        public void handleMessage(Message msg) {
            for(int i=0;i<8;i++){
                if(msg.what==CHANGE_UI[i]) {
                    //for(int i=0;i<8;i++){
                    bitmap[i] = (Bitmap) msg.obj;
                }
                    //}
            }
            course1.setImageBitmap(bitmap[0]);
            course2.setImageBitmap(bitmap[1]);
            course3.setImageBitmap(bitmap[2]);
            course4.setImageBitmap(bitmap[3]);
            course5.setImageBitmap(bitmap[4]);
            course6.setImageBitmap(bitmap[5]);
            course7.setImageBitmap(bitmap[6]);
            course8.setImageBitmap(bitmap[7]);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_study);

        course1=(ImageView)findViewById(R.id.iv_course1);
        course2=(ImageView)findViewById(R.id.iv_course2);
        course3=(ImageView)findViewById(R.id.iv_course3);
        course4=(ImageView)findViewById(R.id.iv_course4);
        course5=(ImageView)findViewById(R.id.iv_course5);
        course6=(ImageView)findViewById(R.id.iv_course6);
        course7=(ImageView)findViewById(R.id.iv_course7);
        course8=(ImageView)findViewById(R.id.iv_course8);

        pager = (RecyclerBannerViewActivity)findViewById(R.id.r);

        urls.add(new Entity("http://pic.58pic.com/58pic/12/46/13/03B58PICXxE.jpg"));
        urls.add(new Entity("http://www.jitu5.com/uploads/allimg/121120/260529-121120232T546.jpg"));
        urls.add(new Entity("http://pic34.nipic.com/20131025/2531170_132447503000_2.jpg"));
        urls.add(new Entity("http://img5.imgtn.bdimg.com/it/u=3462610901,3870573928&fm=206&gp=0.jpg"));
        pager.setDatas(urls);

        //悬浮菜单
        mImageView = (ImageView) findViewById(R.id.iv_menu);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLevitateMenu();
            }
        });

        new Thread(){
            private HttpURLConnection conn;
            private Bitmap bitmap[]=new Bitmap[10];
            private Message msg;
            @Override
            public void run() {
                for(int i=0;i<8;i++){
                    try {
                        URL httpUrl=new URL(url[i]);
                        conn=(HttpURLConnection)httpUrl.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setReadTimeout(5000);
                        int code=conn.getResponseCode();
                        if(code==200){
                            InputStream in=conn.getInputStream();
                            bitmap[i]= BitmapFactory.decodeStream(in);
                            msg=new Message();
                            msg.what=CHANGE_UI[i];
                            msg.obj=bitmap[i];
                            handler.sendMessage(msg);
                        }else{
                            msg=new Message();
                            msg.what=ERROR;
                            handler.sendMessage(msg);
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        msg=new Message();
                        msg.what=ERROR;
                        handler.sendMessage(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                        msg=new Message();
                        msg.what=ERROR;
                        handler.sendMessage(msg);
                    }
                    conn.disconnect();
                }



            }
        }.start();

        //进入消息事件
        titleMessage=(ImageView)findViewById(R.id.title_message);
        titleMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudyActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });


    }

    public void toVedio(View view){
        Intent intent = new Intent(StudyActivity.this,VideoActivity.class);
        startActivity(intent);
    }

    public void toPdf(View view){
        Intent intent = new Intent(StudyActivity.this,PdfImageActivity.class);
        startActivity(intent);
    }


    //搜素界面
    public void toSearchActivity(View view){
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }

    //开启二维码扫描
    public void toCaptureActivity(View view){
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivity(intent);
    }

    //使用recycleview
    private class Entity implements RecyclerBannerViewActivity.BannerEntity {

        String url;

        public Entity(String url) {
            this.url = url;
        }

        @Override
        public String getUrl() {
            return url;
        }
    }

    /**
     * 显示悬浮菜单
     */
    private void showLevitateMenu(){

        //动画
        mRotate(mImageView);

        //创建popwindow
        getPopMenu();

        //获取ImageView控件在手机屏幕的位置
        int[] location = new int[2];
        mImageView.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];

        /**
         * popwindow显示的位置
         * 参数一：基于某控件，一般在popupWindow.showAsDropDown()中比较有用，该处作用不大
         * 参数二：见名知意，写默认即可
         * 参数三：popupWindow在屏幕上显示位置的x坐标
         * 参数四：popupWindow在屏幕上显示位置的y左边
         */
        mMenuPop.showAtLocation(mImageView, Gravity.NO_GRAVITY,
                mImageView.getLeft()-PopWidth+mImageView.getWidth()/4, y+mImageView.getHeight()/2-PopHeight/2);

    }

    private int rotate = 0;
    private int rotation = 225;
    private boolean rotateDirection = true;

    /**
     * 悬浮菜单动画效果
     * @param v
     */
    private void mRotate(View v) {

        ObjectAnimator animator;

        //判断是顺时针旋转还是逆时针旋转
        if(rotateDirection){
            animator = ObjectAnimator.ofFloat(v, "rotation", rotate,rotate-rotation);
            rotate = rotate+rotation;

        }else{
            animator = ObjectAnimator.ofFloat(v, "rotation", rotate,rotate+rotation);
            rotate = rotate-rotation;
        }

        //持续时间
        animator.setDuration(350);
        animator.start();
        rotateDirection = !rotateDirection;
    }


    /**
     * 获取PopupWindow实例 .分类
     */
    private void getPopMenu() {

        if (null != mMenuPop) {

            //动画
            mRotate(mImageView);
            //关闭
            mMenuPop.dismiss();
            mMenuPop = null;
            return;
        } else {
            //初始化popupWindow弹窗
            initMenuPop();
        }
    }


    /**
     * 初始化popWindow
     */
    private void initMenuPop() {
        // 获取自定义布局文件pop.xml的视图
        View view = View.inflate(StudyActivity.this, R.layout.item_pop_levitate_menu, null);

        //测量view的宽高，由于popupwindow没有测量的方法，只能测量内部view的宽高
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        PopWidth = view.getMeasuredWidth();
        PopHeight = view.getMeasuredHeight();

        //下面这两个必须有！！
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        // PopupWindow(布局，宽度，高度) 注意，此处宽高应为-2也就是wrap_content
        mMenuPop = new PopupWindow(view, -2, -2, true);

        // 重写onKeyListener,按返回键消失
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    mRotate(mImageView);
                    mMenuPop.dismiss();
                    mMenuPop = null;
                    return true;
                }
                return false;
            }
        });

        //点击其他地方消失
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mMenuPop != null && mMenuPop.isShowing()) {
                    mRotate(mImageView);
                    mMenuPop.dismiss();
                    mMenuPop = null;
                    return true;
                }
                return false;
            }
        });

        final TextView tv_newClue = (TextView) view.findViewById(R.id.tv_newClue);
        //final TextView tv_Edit = (TextView) view.findViewById(R.id.tv_edit);

        //新建线索
        tv_newClue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(StudyActivity.this,"您点击了我的课程", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(StudyActivity.this,VideoActivity.class);
//                startActivity(intent);

                mRotate(mImageView);
                mMenuPop.dismiss();
                mMenuPop = null;

            }
        });

//        //编辑
//        tv_Edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //Toast.makeText(StudyActivity.this,"点击了"+tv_Edit.getText().toString(),Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(StudyActivity.this,PdfImageActivity.class);
////                startActivity(intent);
//
//                mRotate(mImageView);
//                mMenuPop.dismiss();
//                mMenuPop = null;
//
//            }
//        });

    }

}
