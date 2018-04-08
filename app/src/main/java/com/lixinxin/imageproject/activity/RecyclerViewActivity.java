package com.lixinxin.imageproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lixinxin.imageproject.R;
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
        mData.add("6-转场动画");
        mData.add("7-ROOM");
        mData.add("8-Permission");
        mData.add("9-SQLite读取数据库文件（拷贝数据库文件）");
        mData.add("10-下载apk安装包");
        mData.add("11-属性动画");
        mData.add("12-自定义键盘");
        mData.add("13-ViewModel");
        mData.add("14-分享");
        mData.add("15-Sensor");
        mData.add("16-FragmentTabHost");
        mData.add("17-播放声音");
        mData.add("18-WebViewJs");
        mData.add("19-scheme");
        mData.add("20-AnimActivity");
        mData.add("21-TimePickerDialogActivity");
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
            case 6:
                // startOptionsActivity(this, view.findViewById(R.id.iv));
                // ARouter.getInstance().build("/activity/ScrollViewActivity").navigation();
                startOptionsActivity(this);
                break;
            case 7:
                ARouter.getInstance().build("/activity/RoomActivity").navigation();
                break;
            case 8:
                ARouter.getInstance().build("/activity/PermissionActivity").navigation();
                break;
            case 9:
                ARouter.getInstance().build("/activity/SqliteActivity").navigation();
                break;
            case 10:
                ARouter.getInstance().build("/activity/UpdateApkActivity").navigation();
                break;
            case 11:
                ARouter.getInstance().build("/activity/AnimatorSetActivity").navigation();
                break;
            case 12:
                ARouter.getInstance().build("/activity/KeyboardViewActivity").navigation();
                break;
            case 13:
                ARouter.getInstance().build("/activity/ViewModelActivity").navigation();
                break;
            case 14:
                ARouter.getInstance().build("/activity/ShareActivity").navigation();
                break;
            case 15:
                ARouter.getInstance().build("/activity/SensorActivity").navigation();
                break;
            case 16:
                ARouter.getInstance().build("/activity/FragmentTabHostActivity").navigation();
                break;
            case 17:
                ARouter.getInstance().build("/activity/SoundPoolActivity").navigation();
                break;
            case 18:
                ARouter.getInstance().build("/activity/WebViewJsActivity").navigation();
                break;
            case 19:
                ARouter.getInstance().build("/activity/WebViewActivity").navigation();
                break;
            case 20:
                ARouter.getInstance().build("/activity/AnimActivity").navigation();
                break;
            case 21:
                ARouter.getInstance().build("/activity/TimePickerDialogActivity").navigation();
                break;
            default:
                break;
        }
    }

    private static final String OPTION_IMAGE = "imageView";

    //
    public static void startOptionsActivity(AppCompatActivity activity, View transitionView) {
        Intent intent = new Intent(activity, TransitionsActivity.class);
        // 这里指定了共享的视图元素
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionView, OPTION_IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    public static void startOptionsActivity(AppCompatActivity activity) {
        Intent intent = new Intent(activity, TransitionsActivity.class);
        ActivityCompat.startActivity(activity, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }


}
