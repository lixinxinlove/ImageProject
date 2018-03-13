package com.lixinxin.imageproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 数据库复制等
 */

@Route(path = "/activity/SqliteActivity")
public class SqliteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        File files = getFilesDir();
        initDB();
    }

    private void initDB() {
        //1,归属地数据拷贝过程
        copeDB("address.db");
        //2,常用号码数据库拷贝过程
        copeDB("commonnum.db");
        //3,拷贝病毒数据库
        copeDB("antivirus.db");
    }

    /**
     * 拷贝数据库值files文件夹下
     *
     * @param dbName 数据库名称
     */
    private void copeDB(String dbName) {
        //1,在files文件夹下创建同名dbName数据库文件过程
        File files = getFilesDir();// /data/data/com.lixinxin.imageproject/files
        Log.e("file", files.getAbsolutePath());
        File file = new File(files, dbName);
        if (file.exists()) {
            return;
        }
        InputStream stream = null;
        FileOutputStream fos = null;
        //2,输入流读取第三方资产目录下的文件
        try {
            stream = getAssets().open(dbName);
            //3,将读取的内容写入到指定文件夹的文件中去
            fos = new FileOutputStream(file);
            //4,每次的读取内容大小
            byte[] bs = new byte[1024];
            int temp = -1;
            while ((temp = stream.read(bs)) != -1) {
                fos.write(bs, 0, temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null && fos != null) {
                try {
                    stream.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
