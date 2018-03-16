package com.lixinxin.imageproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;
import com.lixinxin.imageproject.view.NumKeyView;

@Route(path = "/activity/KeyboardViewActivity")
public class KeyboardViewActivity extends AppCompatActivity {
    private NumKeyView mKeyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard_view);
        mKeyView = (NumKeyView) findViewById(R.id.nkv);
        mKeyView.setVisibility(View.VISIBLE);
    }
}
