package com.lixinxin.imageproject.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;
import com.lixinxin.imageproject.service.DownloadAppIntentService;

@Route(path = "/activity/UpdateApkActivity")
public class UpdateApkActivity extends AppCompatActivity {


    private String url = "http://download.evente.cn/sporte/SporteApp_v_1.0.6.apk";

    private Button btnDown;
    private TextView textView;


    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;
    //本地广播数据类型实例。
    private LocalBroadcastManager localBroadcastManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_apk);
        btnDown = (Button) findViewById(R.id.btn_down);
        textView = (TextView) findViewById(R.id.tv_info);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        //新建intentFilter并给其action标签赋值。
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.lixinxin.imageproject.download");
        //创建广播接收器实例，并注册。将其接收器与action标签进行绑定。
        localReceiver = new LocalReceiver();

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UpdateApkActivity.this, DownloadAppIntentService.class);
                intent.putExtra("url", url);
                startService(intent);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            textView.setText("收到");
            if (intent != null) {

                Toast.makeText(context, intent.getStringExtra("name"), Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(context, "这是本地广播接收器", Toast.LENGTH_SHORT).show();
        }
    }

}
