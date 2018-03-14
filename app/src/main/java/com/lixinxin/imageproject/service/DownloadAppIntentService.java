package com.lixinxin.imageproject.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.lixinxin.imageproject.R;
import com.lixinxin.imageproject.activity.MainActivity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 下载 apk 更新包
 */
public class DownloadAppIntentService extends IntentService {

    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;

    private String downloadUrl;

    private boolean isCanceled = false;

    private boolean isPaused = false;

    private int lastProgress;

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading...", progress));
        }

        @Override
        public void onSuccess() {
            // 下载成功时将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Success", -1));
            Toast.makeText(DownloadAppIntentService.this, "Download Success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            // 下载失败时将前台服务通知关闭，并创建一个下载失败的通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Failed", -1));
            Toast.makeText(DownloadAppIntentService.this, "Download Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            Toast.makeText(DownloadAppIntentService.this, "Paused", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            //downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadAppIntentService.this, "Canceled", Toast.LENGTH_SHORT).show();
        }

    };

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress >= 0) {
            // 当progress大于或等于0时才需显示下载进度
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }


    //本地广播数据类型实例。
    private LocalBroadcastManager localBroadcastManager;

    public DownloadAppIntentService() {
        super("DownloadAppIntentService");
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            downloadUrl = intent.getStringExtra("url");
            //开始下载
            int type = downloadApk(downloadUrl, listener);
            if (type == TYPE_SUCCESS) {
                startInstall(this, Uri.parse(file.getAbsolutePath()));
                // sendBroadcast();
            }
        }
    }

    public void sendBroadcast() {
        Intent intent = new Intent("com.lixinxin.imageproject.download");
        intent.putExtra("name", "lee");
        //发送本地广播。
        localBroadcastManager.sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    File file = null;

    private int downloadApk(String url, DownloadListener listener) {
        InputStream is = null;
        RandomAccessFile savedFile = null;

        try {
            long downloadedLength = 0; // 记录已下载的文件长度
            String downloadUrl = url;
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            if (file.exists()) {
                downloadedLength = file.length();
            }
            long contentLength = getContentLength(downloadUrl);
            if (contentLength == 0) {
                return TYPE_FAILED;
            } else if (contentLength == downloadedLength) {
                // 已下载字节和文件总字节相等，说明已经下载完成了
                return TYPE_SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    // 断点下载，指定从哪个字节开始下载
                    .addHeader("RANGE", "bytes=" + downloadedLength + "-")
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();
            if (response != null) {
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadedLength); // 跳过已下载的字节
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;
                        savedFile.write(b, 0, len);
                        // 计算已下载的百分比
                        int progress = (int) ((total + downloadedLength) * 100 / contentLength);
                        listener.onProgress(progress);
                        // publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return TYPE_FAILED;
    }


    //获取服务器上文件的长度
    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(downloadUrl).build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.close();
            return contentLength;
        }
        return 0;
    }


    private boolean startInstall(Context context, Uri uri) {
        File apkFile = new File(uri.getPath());
        if (!apkFile.exists()) {
            return false;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, "com.lixinxin.imageproject" + ".fileProvider", apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
        return true;
    }
}
