package com.lixinxin.imageproject.activity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;

import java.util.HashMap;

@Route(path = "/activity/SoundPoolActivity")
public class SoundPoolActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnPlay;

    private SoundPool mSoundPool;

    private HashMap<Integer, Integer> soundID = new HashMap<Integer, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_pool);
        btnPlay = (Button) findViewById(R.id.btn_play);

        SoundPool.Builder builder = new SoundPool.Builder();
        //传入音频数量
        builder.setMaxStreams(2);
        //AudioAttributes是一个封装音频各种属性的方法
        AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
        //设置音频流的合适的属性
        attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);
        //加载一个AudioAttributes
        builder.setAudioAttributes(attrBuilder.build());
        mSoundPool = builder.build();

        soundID.put(1, mSoundPool.load(this, R.raw.epaysdk_notice_blink, 1));
        soundID.put(1, mSoundPool.load(this, R.raw.epaysdk_notice_mouth, 1));
        //soundID.put(2, mSoundPool.load(getAssets().openFd("assets.mp3"), 1));  //需要捕获IO异常


        btnPlay.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_play) {
            mSoundPool.play(soundID.get(1), 1, 1, 0, 0, 1);
        }
    }
}
