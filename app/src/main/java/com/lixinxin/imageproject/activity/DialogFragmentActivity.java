package com.lixinxin.imageproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;
import com.lixinxin.imageproject.dialog.LeeDialogFragment;

@Route(path = "/activity/DialogFragmentActivity")
public class DialogFragmentActivity extends AppCompatActivity {


    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPromptDialog("leee");
            }
        });
    }

    public void showPromptDialog(String text) {
        LeeDialogFragment dialogFragment = new LeeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", "李鑫鑫");
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getSupportFragmentManager(), text);
    }
}
