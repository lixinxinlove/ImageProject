package com.lixinxin.imageproject.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.lixinxin.imageproject.db.dao.UserDao;
import com.lixinxin.imageproject.db.entity.User;

/**
 * Created by android on 2018/3/9.
 */
@Database(entities = {User.class}, version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDao userDao();
}
