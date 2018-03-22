package com.lixinxin.imageproject.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TabHost;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;
import com.lixinxin.imageproject.fragment.CarFragment;
import com.lixinxin.imageproject.fragment.FindFragment;
import com.lixinxin.imageproject.fragment.HomeFragment;
import com.lixinxin.imageproject.fragment.MyFragment;
import com.lixinxin.imageproject.utils.IconValues;
import com.lixinxin.imageproject.view.TabView;
@Route(path = "/activity/FragmentTabHostActivity")
public class FragmentTabHostActivity extends AppCompatActivity {

    FragmentTabHost mTabHost;
    //定义数组存放Fragment
    private final Class fragmentArray[] = {MyFragment.class, HomeFragment.class, CarFragment.class, FindFragment.class};
    private int mImageViewArray[] = IconValues.homeTabArr;
    private String mTextViewArray[] = IconValues.homeTabTextArr;
    private String mTabIdArray[] = {"home", "lll", "eee", "my"};

//    List<Observer<Action>> listS = new ArrayList<>();
//    Observer<Action> rxSbscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tab_host);
        initFragment();
    }


    /**
     * 初始化Fragment
     */

    private void initFragment() {
        mTabHost = (FragmentTabHost) findViewById(R.id.fragment_tab_host);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_content);//设置替换哪个布局
        mTabHost.getTabWidget().setDividerDrawable(R.color.tr);
        int fragmentCount = fragmentArray.length;

        for (int i = 0; i < fragmentCount; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTabIdArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            Bundle bundle = new Bundle();
            bundle.putString("title", "lee");
            mTabHost.addTab(tabSpec, fragmentArray[i], bundle);
        }
    }

    /**
     * 设置每个Item的布局
     *
     * @return
     */
    private View getTabItemView(int i) {
        TabView view = new TabView(this);
        view.setIv(mImageViewArray[i]);
        view.setTv(mTextViewArray[i]);
        return view;
    }


}
