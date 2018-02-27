package com.lixinxin.imageproject;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lixinxin.imageproject.utils.GlideUtils;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class MainActivity extends AppCompatActivity {

    private static final String IMG_URL = "http://guolin.tech/test.gif";

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.iv_header);


//        HandlerThread mHandlerThread = new HandlerThread("handlerThread");
//        mHandlerThread.start();
//
//        Handler workHadler = new Handler(mHandlerThread.getLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//
//                super.handleMessage(msg);
//            }
//        };


        File cacheDir = Glide.getPhotoCacheDir(this);
        File parentFile = cacheDir.getParentFile();
        long size = GlideUtils.getDirSize(parentFile);
        String sizeText = GlideUtils.byteConversionGBMBKB(size);
        Log.e("glide_size", sizeText);


        Glide.with(this)
                .load(IMG_URL)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(20)))
                .into(mImageView);


        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(MainActivity.this).clearDiskCache();
            }
        }).start();
    }


    SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
        @Override
        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
            mImageView.setImageDrawable(resource);
        }
    };
}
