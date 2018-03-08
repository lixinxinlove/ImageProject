package com.lixinxin.imageproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lixinxin.imageproject.adapter.ListAdapter;
import com.lixinxin.imageproject.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener {

    private ListAdapter adapter;

    private RecyclerView recyclerView;

    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        initData();

        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add("0-路由跳转");
        mData.add("1-路由跳转传值");
        mData.add("2-日历");
        mData.add("3-线程池");
        mData.add("4-DialogFragment");
        mData.add("5-ScrollView监听滑动");

        adapter = new ListAdapter(mData);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0:
                ARouter.getInstance().build("/activity/ARouterActivity").navigation();
                break;
            case 1:
                ARouter.getInstance().build("/activity/ARouter1Activity").withString("key3", "leeee").navigation();
                break;
            case 2:
                ARouter.getInstance().build("/activity/TimesSquareActivity").navigation();
                break;
            case 3:
                ARouter.getInstance().build("/activity/ThreadExecutorActivity").navigation();
                break;
            case 4:
                ARouter.getInstance().build("/activity/DialogFragmentActivity").navigation();
                break;
            case 5:
                ARouter.getInstance().build("/activity/ScrollViewActivity").navigation();
                break;
            default:
                break;
        }
    }
}
