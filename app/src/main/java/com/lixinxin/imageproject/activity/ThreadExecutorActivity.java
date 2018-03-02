package com.lixinxin.imageproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;
import com.lixinxin.imageproject.task.RunTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Route(path = "/activity/ThreadExecutorActivity")
public class ThreadExecutorActivity extends AppCompatActivity {

    private ExecutorService executorService;
    private RunTask runTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_executor);
        runTask = new RunTask();
        executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.execute(runTask);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
