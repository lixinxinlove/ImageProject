package com.lixinxin.imageproject.task;

import android.util.Log;

/**
 * Created by android on 2018/3/2.
 */

public class RunTask implements Runnable {
    @Override
    public void run() {
        Log.e("lee", "任务");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
