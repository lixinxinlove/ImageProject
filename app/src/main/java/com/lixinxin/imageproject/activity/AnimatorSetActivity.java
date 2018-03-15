package com.lixinxin.imageproject.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;

@Route(path = "/activity/AnimatorSetActivity")
public class AnimatorSetActivity extends AppCompatActivity implements View.OnClickListener {


    private LinearLayout linearLayout;

    private Button btnShow;

    private boolean isShow = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set);
        linearLayout = (LinearLayout) findViewById(R.id.ll_content);
        btnShow = (Button) findViewById(R.id.btn_show);
        btnShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                if (isShow) {
                    close();
                } else {
                    show();
                }
                isShow = !isShow;
                break;
            default:
                break;
        }
    }


    private AnimatorSet createRightTranslationX(float startX, float endX) {
        AnimatorSet animatorSet = new AnimatorSet();// 组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(linearLayout, "translationX", startX, endX);
        animatorSet.setDuration(300);
        animatorSet.play(scaleX);// 两个动画同时开始
        return animatorSet;
    }

    private void show() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(linearLayout, "translationX", linearLayout.getWidth(), 0);
        scaleX.setDuration(300);
        scaleX.start();
    }

    private void close() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(linearLayout, "translationX", 0, linearLayout.getWidth());
        scaleX.setDuration(300);
        scaleX.start();
    }


}
