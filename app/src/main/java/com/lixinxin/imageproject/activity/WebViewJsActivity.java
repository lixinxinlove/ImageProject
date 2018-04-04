package com.lixinxin.imageproject.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;

@Route(path = "/activity/WebViewJsActivity")
public class WebViewJsActivity extends AppCompatActivity {

    private WebView myWebView = null;
    private Button button = null;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_js);

        myWebView = findViewById(R.id.myWebView);
        myWebView.addJavascriptInterface(new WebAppInterface(this), "android");
        myWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        WebSettings settings = myWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        myWebView.loadUrl("file:///android_asset/new_file.html");

        button =  findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.loadUrl("javascript:showAndroidToast('waawo')");
            }
        });
    }


    public class WebAppInterface {
        Context context = null;

        public WebAppInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void showToast(String string) {
            Toast.makeText(context, string, Toast.LENGTH_LONG).show();
        }
    }
}
