package com.lixinxin.imageproject.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;
import com.lixinxin.imageproject.db.entity.User;
import com.lixinxin.imageproject.viewmodel.UserModel;

@Route(path = "/activity/ViewModelActivity")
public class ViewModelActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);
        textView = (TextView) findViewById(R.id.tv_text);
        UserModel userModel = ViewModelProviders.of(this).get(UserModel.class);
        userModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                textView.setText(user.toString());
                user.setName("哈哈哈");
            }
        });

        Log.e("Android", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Android", "onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Android", "onResume");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("Android", "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("Android", "onRestoreInstanceState");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Android", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Android", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("Android", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Android", "onDestroy");
    }

    public void onChangeUI(View view) {

        textView.setText("");
    }

}
