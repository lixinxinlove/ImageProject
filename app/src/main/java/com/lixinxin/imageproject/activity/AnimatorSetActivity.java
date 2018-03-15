package com.lixinxin.imageproject.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;

import static android.R.attr.endY;
import static android.R.attr.startY;

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
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(linearLayout, "translationY", startY, endY);
        animatorSet.setDuration(300);

        animatorSet.play(scaleY);
        animatorSet.play(scaleX);// 两个动画同时开始
        return animatorSet;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void show() {
        AnimatorSet animatorSet = new AnimatorSet();// 组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(linearLayout, "translationX", linearLayout.getWidth(), 0);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(linearLayout, "translationY", linearLayout.getHeight(), 0);
        animatorSet.play(scaleX);
        animatorSet.play(scaleY);
        animatorSet.setDuration(3000);
        animatorSet.addListener(new Listener());
        animatorSet.setInterpolator(new AnticipateOvershootInterpolator());
        animatorSet.start();

    }

    private void close() {
        AnimatorSet animatorSet = new AnimatorSet();// 组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(linearLayout, "translationX", 0, linearLayout.getWidth());
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(linearLayout, "translationY", 0, linearLayout.getHeight());
        animatorSet.play(scaleX);
        animatorSet.play(scaleY);
        animatorSet.setDuration(3000);
        animatorSet.setInterpolator(new AnticipateOvershootInterpolator()); //插值器
        animatorSet.start();
    }


    private class Listener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }


}
