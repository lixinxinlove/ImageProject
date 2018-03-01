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
        for (int i = 0; i < 20; i++) {
            mData.add(String.format("leeee%d", i));
        }
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
                ARouter.getInstance().build("/activity/ARouter1Activity")
                        .withLong("key1", 666L)
                        .withInt("key2",12)
                        .withString("key3", "leeee")
                        .navigation();
                break;
            case 2:
                ARouter.getInstance().build("/activity/TimesSquareActivity").navigation();
                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            default:
                break;
        }

    }
}
