package com.lixinxin.imageproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixinxin.imageproject.R;


/**
 * Created by android on 2017/3/20.
 */
public class TabView extends RelativeLayout {

    private ImageView iv;
    private TextView tv;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_tab, this);
        iv = (ImageView) findViewById(R.id.iv);
        tv = (TextView) findViewById(R.id.tv);
    }

    public void setIv(int viewId) {
        iv.setImageResource(viewId);
    }

    public void setTv(String text) {
        tv.setText(text);
    }
}
