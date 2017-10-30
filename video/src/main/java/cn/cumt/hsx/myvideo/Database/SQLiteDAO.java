package cn.cumt.hsx.myvideo.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class SQLiteDAO {
    private SQLiteHelper mSQLiteHelper;

    public SQLiteDAO(Context context){
        this.mSQLiteHelper = new SQLiteHelper(context,"ol.db",null,1);
    }

    public void insert(String name, int time){
        SQLiteDatabase db = mSQLiteHelper.getWritableDatabase();
        db.execSQL("insert into rcd_play(name,time) values(?,?)", new Object[]{
                name, time
        });
        db.close();
    }

    public void delete(){

    }

    public void update(String name, int time){
        SQLiteDatabase db = mSQLiteHelper.getWritableDatabase();
        db.execSQL("update rcd_play set time=? where name=?", new Object[]{
                time, name
        });
    }

    public int find(String name){
        SQLiteDatabase db = mSQLiteHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from rcd_play where name=?", new String[]{
                name
        });
        int time = 0;
        if(cursor.moveToFirst()){
            time = cursor.getInt(cursor.getColumnIndex("time"));
        }
        db.close();
        return time;
    }
}
