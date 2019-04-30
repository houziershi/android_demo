package com.example.admin.myapplication_app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.myapplication_app.data.DaoMaster;
import com.example.admin.myapplication_app.data.DaoSession;
import com.facebook.stetho.Stetho;

/**
 * Discription:
 * Created by guokun on 2019/4/30.
 */
public class BaseApplication extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        //配置数据库
        setupDatabase();
    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库shop.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop2.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }
}
