package com.lixinxin.imageproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;

@Route(path = "/activity/ARouterActivity")
public class ARouterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter);
    }
}
