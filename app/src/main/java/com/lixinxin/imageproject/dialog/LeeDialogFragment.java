package com.lixinxin.imageproject.dialog;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lixinxin.imageproject.R;

/**
 * Created by android on 2018/3/8.
 */

public class LeeDialogFragment extends BaseDialogFragment implements View.OnClickListener {

    private ImageView imageView;

    @Override
    protected int layoutId() {
        return R.layout.dialog_fragment_lee;
    }

    @Override
    protected void findView() {
        imageView = rootView.findViewById(R.id.iv_lee);
    }

    @Override
    protected void setListener() {
        imageView.setOnClickListener(this);
    }

    @Override
    protected void _onCreateView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_lee:
                Toast.makeText(getContext(), "hahah", Toast.LENGTH_SHORT).show();
                LeeDialogFragment.this.dismiss();
                break;
            default:
                break;
        }
    }
}
