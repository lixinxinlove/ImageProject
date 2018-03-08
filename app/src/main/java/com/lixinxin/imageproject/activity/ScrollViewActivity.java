package com.lixinxin.imageproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;
import com.lixinxin.imageproject.view.LeeScrollView;

@Route(path = "/activity/ScrollViewActivity")
public class ScrollViewActivity extends AppCompatActivity {

    private LeeScrollView scrollView;

    private RelativeLayout topBar;

    private static final float HEAD_ALPHA = 0.96f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        scrollView = (LeeScrollView) findViewById(R.id.csv);
        topBar = (RelativeLayout) findViewById(R.id.top);
        scrollView.setScrollListener(scrollListener);
    }

    private LeeScrollView.ScrollViewListener scrollListener = new LeeScrollView.ScrollViewListener() {

        @Override
        public void onScrollChanged(ScrollView scrollView, int x, int y, int oldx, int oldy) {
            if (y > 5) {
                int bannerHeight = topBar.getHeight();
                int headHeight = topBar.getHeight() * 2;
                float f = (float) y / (float) (bannerHeight - headHeight);
                f = f > HEAD_ALPHA ? HEAD_ALPHA : f;
                topBar.setAlpha(f);
                //  if (!isChanged) {
                //      afterSlide();
                //  }

                Log.e("lee", y + "");

            } else {
                topBar.setAlpha(1);
                //  beforeSlide();
                Log.e("lee-", y + "");
            }
        }

    };

}
