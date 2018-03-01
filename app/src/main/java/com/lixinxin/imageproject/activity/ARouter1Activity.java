package com.lixinxin.imageproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;

@Route(path = "/activity/ARouter1Activity")
public class ARouter1Activity extends AppCompatActivity {

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter1);
        textView = (TextView) findViewById(R.id.tv);

        String str = getIntent().getStringExtra("key3");
        textView.setText(str);

    }
}
