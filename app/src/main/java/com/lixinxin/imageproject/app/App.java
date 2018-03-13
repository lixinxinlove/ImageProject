package com.lixinxin.imageproject.app;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.stetho.Stetho;
import com.lixinxin.imageproject.db.AppDataBase;
import com.lixinxin.imageproject.db.dao.UserDao;

/**
 * Created by android on 2018/2/27.
 */

public class App extends Application {

    public static Context mContext;

    public static AppDataBase db;

    public static UserDao userDao;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //初始化数据库调试
        Stetho.initializeWithDefaults(this);
        initARouter();
        initStetho();
        initRoom();
    }

    private void initARouter() {

        if (true) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }


    private void initStetho() {
        Stetho.initializeWithDefaults(this);
    }

    private void initRoom() {
        db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "database-user").fallbackToDestructiveMigration().build();
        userDao = db.userDao();
    }

}
