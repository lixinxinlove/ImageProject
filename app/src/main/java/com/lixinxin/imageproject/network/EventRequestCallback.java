package com.lixinxin.imageproject.network;

import okhttp3.Call;

public abstract class EventRequestCallback {

    private Call mCall;

    /**
     * 请求结果回调
     *
     * @param res
     */
    public abstract void _RequestCallback(EventResponseEntity res);

    /**
     * 请求取消
     */
    public void _onCancelled() {

    }

    /**
     * 请求加载中
     *
     * @param total
     * @param current
     * @param isUploading
     */
    public void _onLoading(long total, long current, boolean isUploading) {

    }

    /**
     * 请求开始
     */
    public void _onStart() {

    }


    /**
     * 重新登录
     */
    public void reLogin() {
      //  Toast.makeText(EventApp.mContext, "账号过期，请重新登录", Toast.LENGTH_LONG).show();
      //  Intent intent = new Intent(EventApp.mContext, EventLoginActivity.class);
     //   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
       // EventApp.mContext.startActivity(intent);
    }


    public Call getmCall() {
        return mCall;
    }

    public void setmCall(Call mCall) {
        this.mCall = mCall;
    }
}
