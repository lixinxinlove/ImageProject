package com.lixinxin.imageproject.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;

@Route(path = "/activity/AnimActivity")
public class AnimActivity extends AppCompatActivity {


    private ImageView imageView1;
    private ImageView imageView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        imageView1 = findViewById(R.id.iv1);
        imageView2 = findViewById(R.id.iv2);

        AnimatorSet set1 = createTranslationXY(imageView1, imageView1.getX(), imageView1.getX() + 60, imageView1.getY(), imageView1.getY() - 60, 0);
        set1.start();
        AnimatorSet set2 = createTranslationXY(imageView2, imageView2.getX(), imageView2.getX() + 60, imageView2.getY(), imageView2.getY() - 60, 1050);
        set2.start();

    }


    private AnimatorSet createTranslationXY(final View view, float startX, float endX, float startY, float endY, long currentTime) {
        AnimatorSet animatorSet = new AnimatorSet();// 组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "translationX", startX, endX);
       // ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "translationY", startY, endY);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1f);
        animatorSet.setDuration(1000);
        animatorSet.setStartDelay(currentTime);
        animatorSet.play(alpha);
     //   animatorSet.play(scaleY);
        animatorSet.play(scaleX);// 两个动画同时开始
        return animatorSet;
    }

}
