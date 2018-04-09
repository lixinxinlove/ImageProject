package com.lixinxin.imageproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;

import java.util.Timer;
import java.util.TimerTask;

@Route(path = "/activity/TimerActivity")
public class TimerActivity extends AppCompatActivity {


    private int sum = 0;

    private Timer mTimer;
    private TimerTask mTimerTask;


    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        textView = findViewById(R.id.tv);
        mTimer = new Timer();

        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                sum++;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(sum + "");
                    }
                });
            }
        };
        mTimer.schedule(mTimerTask, 2000, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }


    public void onLxx(View view) {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

}
