package com.lixinxin.imageproject.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.lixinxin.imageproject.db.entity.User;

import java.util.List;

/**
 * Created by android on 2018/3/9.
 */

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertUser(User... users);

    @Delete
    int deleteUser(User... users);

    @Update
    int updateUser(User... users);

    @Query("SELECT * FROM user")
    List<User> query();

    @Query("SELECT * FROM user WHERE userId = :userId")
    List<User> queryUserId(int userId);

    @Query("SELECT * FROM user WHERE name LIKE :name")
    List<User> queryName(String name);

    @Query("SELECT * FROM user WHERE address LIKE :address")
    List<User> queryAddress(String address);

    @Query("SELECT count(*) FROM user")
    Long queryCount();
}
