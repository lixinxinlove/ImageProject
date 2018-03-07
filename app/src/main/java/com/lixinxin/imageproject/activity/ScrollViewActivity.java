package com.lixinxin.imageproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ScrollView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;
import com.lixinxin.imageproject.view.ChimelongScrollView;

@Route(path = "/activity/ScrollViewActivity")
public class ScrollViewActivity extends AppCompatActivity {

    ChimelongScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        scrollView = (ChimelongScrollView) findViewById(R.id.csv);
        scrollView.setScrollListener(scrollListener);
    }

    private ChimelongScrollView.ScrollViewListener scrollListener = new ChimelongScrollView.ScrollViewListener() {

        @Override
        public void onScrollChanged(ScrollView scrollView, int x, int y, int oldx, int oldy) {
            if (y > 5) {
                //int bannerHeight = convenientBanner.getHeight();
                // int headHeight = homeHeadLayout.getHeight() * 2;
                //  float f = (float) y / (float) (bannerHeight - headHeight);
                //  f = f > HEAD_ALPHA ? HEAD_ALPHA : f;
                //  homeHeadBgLayout.setAlpha(f);
                //  if (!isChanged) {
                //      afterSlide();
                //  }

                Log.e("lee", y + "");

            } else {
                // homeHeadBgLayout.setAlpha(1);
                //  beforeSlide();
                Log.e("lee-", y + "");
            }
        }

    };

}
