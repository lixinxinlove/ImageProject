package com.lixinxin.imageproject.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;

@Route(path = "/activity/AnimActivity")
public class AnimActivity extends AppCompatActivity {


    private ImageView imageView1;
    private ImageView imageView2;


    AnimatorSet set1;
    AnimatorSet set2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        imageView1 = findViewById(R.id.iv1);
        imageView2 = findViewById(R.id.iv2);

        set1 = createTranslationXY(imageView1, imageView1.getX() - 60, imageView1.getX(), imageView1.getY(), imageView1.getY() - 60, 0);
        set2 = createTranslationXY(imageView2, imageView2.getX() - 60, imageView2.getX(), imageView2.getY(), imageView2.getY() - 60, 1050);
        set1.start();
        set2.start();

        //普通动画
        //AnimationSet set11 = createAnim(imageView1, 0);
        //AnimationSet set12 = createAnim(imageView1, 1050);
        // imageView1.startAnimation(set11);
        //imageView2.startAnimation(set12);

    }


    private AnimatorSet createTranslationXY(final View view, float startX, float endX, float startY, float endY, long currentTime) {
        AnimatorSet animatorSet = new AnimatorSet();// 组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "translationX", startX, endX);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1f);
        animatorSet.setDuration(1000);
        animatorSet.setStartDelay(currentTime);
        animatorSet.play(alpha);
        animatorSet.play(scaleX);// 两个动画同时开始
        return animatorSet;
    }

    public void start(View view) {


    }


    private AnimationSet createAnim(View view, long time) {
        AnimationSet set = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(view.getX(), view.getX() + 60, 0, 0);
        AlphaAnimation alpha = new AlphaAnimation(0.0f, 1.0f);
        set.addAnimation(translateAnimation);
        set.addAnimation(alpha);
        set.setDuration(1000);
        set.setStartTime(time);
        set.setFillAfter(true);
        return set;
    }


}
