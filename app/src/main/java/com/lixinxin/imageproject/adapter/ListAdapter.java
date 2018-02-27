package com.lixinxin.imageproject.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lixinxin.imageproject.R;

import java.util.List;

/**
 * Created by android on 2018/2/27.
 */

public class ListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ListAdapter(@Nullable List<String> data) {
        super(R.layout.item_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv1, item);
    }
}
