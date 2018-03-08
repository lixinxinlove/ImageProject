package com.lixinxin.imageproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;

/**
 * 转场动画
 */
@Route(path = "/activity/TransitionsActivity")
public class TransitionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1 分解
       // getWindow().setEnterTransition(new Explode().setDuration(2000));
       // getWindow().setExitTransition(new Explode().setDuration(2000));

        //2 滑动进入
        //getWindow().setEnterTransition(new Slide().setDuration(2000));
       // getWindow().setExitTransition(new Slide().setDuration(2000));

        //3 淡入淡出
        getWindow().setEnterTransition(new Fade().setDuration(2000));
        getWindow().setExitTransition(new Fade().setDuration(2000));

        setContentView(R.layout.activity_transitions);
    }
}
