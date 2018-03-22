package com.lixinxin.imageproject.view;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;

/**
 * Created by android on 2018/3/22.
 */

public class LeeFragmentTabHost extends FragmentTabHost {
    
    private String tab;

    public void setTab(String mTab) {
        this.tab = mTab;
    }

    public LeeFragmentTabHost(Context context) {
        super(context);
    }

    public LeeFragmentTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void onTabChanged(String tabId) {
        if (tabId.equals(tab)) {
        } else {
            super.onTabChanged(tabId);
        }
    }
}
