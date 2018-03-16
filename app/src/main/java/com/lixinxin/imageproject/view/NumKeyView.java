package com.lixinxin.imageproject.view;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;

import com.lixinxin.imageproject.R;

/**
 * Created by android on 2018/3/16.
 * 数字键盘
 */

public class NumKeyView extends KeyboardView implements KeyboardView.OnKeyboardActionListener {


    private Context mContext;

    public NumKeyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NumKeyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public NumKeyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        mContext = context;
        //获取xml中的按键布局
        Keyboard keyboard = new Keyboard(context, R.xml.keyboard_view);
        setKeyboard(keyboard);
        setEnabled(true);
        setPreviewEnabled(false);
        setOnKeyboardActionListener(this);
    }


    @Override
    public void onPress(int primaryCode) {
        Log.e("onPress", primaryCode + "");
    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        Log.e("onKey", primaryCode + "");
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {
        super.swipeLeft();
    }

    @Override
    public void swipeRight() {
        super.swipeRight();
    }

    @Override
    public void swipeDown() {
        super.swipeDown();
    }

    @Override
    public void swipeUp() {
        super.swipeUp();
    }
}
