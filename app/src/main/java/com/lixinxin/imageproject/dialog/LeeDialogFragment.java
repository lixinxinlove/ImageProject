package com.lixinxin.imageproject.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
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
        getDialog().setCancelable(false);
        //点击外部不消失
        getDialog().setCanceledOnTouchOutside(false);
        //对于点击返回键不消失，需要监听OnKeyListener:
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void _onCreateView() {

    }

    @Override
    protected Dialog _onCreateDialog() {
        return new Dialog(getActivity(), R.style.BottomDialog);
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
