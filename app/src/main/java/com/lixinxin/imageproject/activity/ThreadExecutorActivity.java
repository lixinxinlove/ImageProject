package com.lixinxin.imageproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;
import com.lixinxin.imageproject.task.RunTask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Route(path = "/activity/ThreadExecutorActivity")
public class ThreadExecutorActivity extends AppCompatActivity {

    private ThreadPoolExecutor executorService;
    private RunTask runTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_executor);
        runTask = new RunTask();
        createThreadPool();
        //  executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.execute(runTask);
            //executorService.remove(runTask);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (executorService != null) {
            if (!executorService.isShutdown()) {
                executorService.shutdown();

            }
        }
    }


    private void createThreadPool() {
        int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        NUMBER_OF_CORES = 1;
        int KEEP_ALIVE_TIME = 1;
        TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

        executorService = new ThreadPoolExecutor(2, 3, 1, TimeUnit.SECONDS, taskQueue);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
