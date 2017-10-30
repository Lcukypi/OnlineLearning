package cn.cumt.hsx.myvideo.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import cn.cumt.hsx.myvideo.CustomView.MyJCVideoPlayerStandard;
import cn.cumt.hsx.myvideo.Database.SQLiteDAO;
import cn.cumt.hsx.myvideo.R;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoActivity extends AppCompatActivity {

    private MyJCVideoPlayerStandard myJCVideoPlayerStandard;
    private SQLiteDAO sqLiteDAO;
    private String url;
    private String title;
    private String image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        //initActionBar();
        sqLiteDAO = new SQLiteDAO(this);

        url = "http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4";
        title = url.substring(url.lastIndexOf('/') + 1, url.lastIndexOf('.'));
        image = "http://img4.jiecaojingxuan.com/2016/11/23/00b026e7-b830-4994-bc87-38f4033806a6.jpg@!640_360";

        myJCVideoPlayerStandard = (MyJCVideoPlayerStandard) findViewById(R.id.jc_video);
        myJCVideoPlayerStandard.setUp(url, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, title);
        myJCVideoPlayerStandard.hadPlayedTime = sqLiteDAO.find(title);
        myJCVideoPlayerStandard.seekToInAdvance = myJCVideoPlayerStandard.hadPlayedTime;
        Picasso.with(this)
                .load(image)
                .into(myJCVideoPlayerStandard.thumbImageView);
    }


//    public void initActionBar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.back);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if(JCVideoPlayer.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        String name = title;
        int time = myJCVideoPlayerStandard.hadPlayedTime;
        sqLiteDAO.update(name,time);
        super.onDestroy();
    }

//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            moveTaskToBack(true);
////            Intent intent = new Intent(this,MainActivity.class);
////            startActivity(intent);
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
