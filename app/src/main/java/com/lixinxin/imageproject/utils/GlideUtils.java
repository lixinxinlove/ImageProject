package com.lixinxin.imageproject.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lixinxin.imageproject.R;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by android on 2018/2/9.
 */

public class GlideUtils {

    RequestOptions options = new RequestOptions().override(200, 100);  //设置图片大小

    public static void load(Context context,
                            String url,
                            ImageView imageView,
                            RequestOptions options) {

        Glide.with(context)
                .load(url)
                .apply(options.placeholder(R.mipmap.ic_launcher))  //添加默认站位图
                .into(imageView);
    }

    public static void load(Context context,
                            String url,
                            ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }


    public static String getCocheSize() {

        return "";
    }



    private static final double GB = 1024L * 1024L * 1024L;// 定义GB的计算常量
    private static final double MB = 1024L * 1024L;// 定义MB的计算常量
    private static final double KB = 1024L;// 定义KB的计算常量

    public static String byteConversionGBMBKB(double kSize) {
        DecimalFormat df = new DecimalFormat("#.00");
        double temp = 0;
        if (kSize / GB >= 1) {
            temp = kSize / GB;
            return df.format(temp) + "GB";
        } else if (kSize / MB >= 1) {
            temp = kSize / MB;
            return df.format(temp) + "MB";
        } else if (kSize / KB >= 1) {
            temp = kSize / KB;
            return df.format(temp) + "KB";
        }
        return kSize + "B";
    }


    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }
}
