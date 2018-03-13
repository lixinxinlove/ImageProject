package com.lixinxin.imageproject.db.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lixinxin.imageproject.app.App;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qbc
 *         查询归属数据库,获取电话所在位置
 */
public class CommonNumberDao {
 //   public static String path = "data/data/com.itheima.mobilesafe69/files/commonnum.db";
    public static String path = App.mContext.getFilesDir() + "/commonnum.db";

    //获取classlist表中组数据方法
    public static List<Group> getGroup() {
        List<Group> groupList = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);

        Cursor cursor = db.query("classlist", new String[]{"name", "idx"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Group group = new Group();
            group.name = cursor.getString(0);
            group.idx = cursor.getInt(1);
            group.childList = getChild(group.idx);
            groupList.add(group);
        }
        cursor.close();
        db.close();
        return groupList;
    }

    //获取table1---table8表中的孩子节点数据的方法
    public static List<Child> getChild(int index) {
        List<Child> childList = new ArrayList<Child>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db.rawQuery("select number,name from table" + index + ";", null);
        while (cursor.moveToNext()) {
            Child child = new Child();
            child.number = cursor.getString(0);
            child.name = cursor.getString(1);
            childList.add(child);
        }
        cursor.close();
        db.close();
        return childList;
    }

    public static class Group {
        public String name;
        public int idx;
        public List<Child> childList;
    }

    public static class Child {
        public String number;
        public String name;
    }
}
