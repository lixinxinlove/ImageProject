package com.lixinxin.imageproject.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.lixinxin.imageproject.app.App;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 与文件相关的类,主要负责文件的读写
 */
public final class FileUtil {

    // ------------------------------ 手机系统相关 ------------------------------
    public static final String NEWLINE = System.getProperty("line.separator");// 系统的换行符
    public static final String APPROOT = "App";// 程序的根目录
    public static final String ASSERT_PATH = "file:///android_asset";// apk的assert目录
    public static final String RES_PATH = "file:///android_res";// apk的assert目录

    // ----------------------------------存放文件的路径后缀------------------------------------
    public static final String CACHE_IMAGE_SUFFIX = File.separator + APPROOT + File.separator + "images"
            + File.separator;
    public static final String CACHE_VOICE_SUFFIX = File.separator + APPROOT + File.separator + "voice"
            + File.separator;
    public static final String CACHE_MATERIAL_SUFFIX = File.separator + APPROOT + File.separator + "material"
            + File.separator;
    public static final String LOG_SUFFIX = File.separator + APPROOT + File.separator + "Log" + File.separator;

    public static final String APK_DOWNLOAD = Environment.getExternalStorageDirectory() + "/" + APPROOT + "/apk/";

    public static final String ROOT_PATH = Environment.getExternalStorageDirectory() + "/" + APPROOT + "/";

    // ------------------------------------数据的缓存目录-------------------------------------------------------
    public static String SDCARD_PAHT;// SD卡路径
    public static String LOCAL_PATH;// 本地路径,即/data/data/目录下的程序私有目录
    public static String CURRENT_PATH = "";// 当前的路径,如果有SD卡的时候当前路径为SD卡，如果没有的话则为程序的私有目录

    static {
        init();
    }

    public static void init() {
        SDCARD_PAHT = Environment.getExternalStorageDirectory().getPath();// SD卡路径
        LOCAL_PATH = App.mContext.getFilesDir().getAbsolutePath();// 本地路径,即/data/data/目录下的程序私有目录

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            CURRENT_PATH = SDCARD_PAHT;
        } else {
            CURRENT_PATH = LOCAL_PATH;
        }
    }

    /**
     * 得到与当前存储路径相反的路径(当前为/data/data目录，则返回/sdcard目录;当前为/sdcard，则返回/data/data目录)
     *
     * @return
     */
    public static String getDiffPath() {
        if (CURRENT_PATH.equals(SDCARD_PAHT)) {
            return LOCAL_PATH;
        }
        return SDCARD_PAHT;
    }

    public static String getDiffPath(String pathIn) {
        return pathIn.replace(CURRENT_PATH, getDiffPath());
    }

    // ------------------------------------文件的相关方法--------------------------------------------

    /**
     * 将数据写入一个文件
     *
     * @param destFilePath 要创建的文件的路径
     * @param data         待写入的文件数据
     * @param startPos     起始偏移量
     * @param length       要写入的数据长度
     * @return 成功写入文件返回true, 失败返回false
     */
    public static boolean writeFile(String destFilePath, byte[] data, int startPos, int length) {
        try {
            if (!createFile(destFilePath)) {
                return false;
            }
            FileOutputStream fos = new FileOutputStream(destFilePath);
            fos.write(data, startPos, length);
            fos.flush();
            if (null != fos) {
                fos.close();
                fos = null;
            }
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean writeFile(String fileName, byte[] data) {
        String filePath = ROOT_PATH + fileName;
        if (!createFile(filePath)) {
            return false;
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(filePath);
            fos.write(data);
            fos.flush();
            if (null != fos) {
                fos.close();
                fos = null;
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 从一个输入流里写文件
     *
     * @param destFilePath 要创建的文件的路径
     * @param in           要读取的输入流
     * @return 写入成功返回true, 写入失败返回false
     */
    public static boolean writeFile(String destFilePath, InputStream in) {
        try {
            if (!createFile(destFilePath)) {
                return false;
            }
            FileOutputStream fos = new FileOutputStream(destFilePath);
            int readCount = 0;
            int len = 1024;
            byte[] buffer = new byte[len];
            while ((readCount = in.read(buffer)) != -1) {
                fos.write(buffer, 0, readCount);
            }
            fos.flush();
            if (null != fos) {
                fos.close();
                fos = null;
            }
            if (null != in) {
                in.close();
                in = null;
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean appendFile(String filename, byte[] data, int datapos, int datalength) {
        try {

            createFile(filename);

            RandomAccessFile rf = new RandomAccessFile(filename, "rw");
            rf.seek(rf.length());
            rf.write(data, datapos, datalength);
            if (rf != null) {
                rf.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 读取文件，返回以byte数组形式的数据
     *
     * @param filePath 要读取的文件路径名
     * @return
     */
    public static byte[] readFile(String filePath) {
        try {
            if (isFileExist(filePath)) {
                FileInputStream fi = new FileInputStream(filePath);
                return readInputStream(fi);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从一个数量流里读取数据,返回以byte数组形式的数据。 </br>
     * </br>
     * 需要注意的是，如果这个方法用在从本地文件读取数据时，一般不会遇到问题，
     * 但如果是用于网络操作，就经常会遇到一些麻烦(available()方法的问题)。所以如果是网络流不应该使用这个方法。
     *
     * @param in 要读取的输入流
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream in) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            byte[] b = new byte[in.available()];
            int length = 0;
            while ((length = in.read(b)) != -1) {
                os.write(b, 0, length);
            }

            b = os.toByteArray();

            in.close();
            in = null;

            os.close();
            os = null;

            return b;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 读取网络流
     *
     * @param in
     * @return
     */
    public static byte[] readNetWorkInputStream(InputStream in) {
        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();

            int readCount = 0;
            int len = 1024;
            byte[] buffer = new byte[len];
            while ((readCount = in.read(buffer)) != -1) {
                os.write(buffer, 0, readCount);
            }

            in.close();
            in = null;

            return os.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                os = null;
            }
        }
        return null;
    }

    /**
     * 将一个文件拷贝到另外一个地方
     *
     * @param sourceFile    源文件地址
     * @param destFile      目的地址
     * @param shouldOverlay 是否覆盖
     * @return
     */
    public static boolean copyFiles(String sourceFile, String destFile, boolean shouldOverlay) {
        try {
            if (shouldOverlay) {
                deleteFile(destFile);
            }
            FileInputStream fi = new FileInputStream(sourceFile);
            writeFile(destFile, fi);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 路径名
     * @return
     */
    public static boolean isFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 创建一个文件，创建成功返回true
     *
     * @param filePath
     * @return
     */
    public static boolean createFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                return file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 删除一个文件
     *
     * @param filePath 要删除的文件路径名
     * @return true if this file was deleted, false otherwise
     */
    public static boolean deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除 directoryPath目录下的所有文件，包括删除删除文件夹
     *
     * @param dir
     */
    public static void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] listFiles = dir.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                deleteDirectory(listFiles[i]);
            }
        }
        dir.delete();
    }

    /**
     * 获取目录文件大小
     *
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * 字符串转流
     *
     * @param str
     * @return
     */
    public static InputStream String2InputStream(String str) {
        ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
        return stream;
    }

    /**
     * 流转字符串
     *
     * @param is
     * @return
     */
    public static String inputStream2String(InputStream is) {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";

        try {
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    // 批量更改文件后缀
    public static void reNameSuffix(File dir, String oldSuffix, String newSuffix) {
        if (dir.isDirectory()) {
            File[] listFiles = dir.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                reNameSuffix(listFiles[i], oldSuffix, newSuffix);
            }
        } else {
            dir.renameTo(new File(dir.getPath().replace(oldSuffix, newSuffix)));
        }
    }

    public static void writeImage(Bitmap bitmap, String destPath, int quality) {
        try {
            FileUtil.deleteFile(destPath);
            if (FileUtil.createFile(destPath)) {
                FileOutputStream out = new FileOutputStream(destPath);
                if (bitmap.compress(CompressFormat.JPEG, quality, out)) {
                    out.flush();
                    out.close();
                    out = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getBasePath() throws IOException {
        File basePath = new File(Environment.getExternalStorageDirectory(), APPROOT + "/ImageCache");
        /** 文件是否存在 */
        if (!basePath.exists()) {
            /** 判断创建该路径做包含的子目录与父目录是否成功 */
            if (!basePath.mkdirs()) {
                throw new IOException(String.format("创建文件失败！", basePath.toString()));
            }
        }
        /** 判断该路径对应的是否是一个目录 */
        if (!basePath.isDirectory()) {
            throw new IOException(String.format("没有该路径所包含的目录！", basePath.toString()));
        }
        return basePath;
    }

    public static String getFileNameByMD5(String fileName) {
        String reStr = "temp";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(fileName.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes) {
                int bt = b & 0xff;
                if (bt < 16) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            reStr = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return reStr;
    }

    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
        }
    }

    public static File updateDir = null;
    public static File updateFile = null;

    /**
     * 方法描述：createFile方法
     *
     * @param  app_name
     * @return
     * @see FileUtil
     */
    public static boolean createAPKFile(String app_name) {
        boolean isCreateFileSucess = false;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            isCreateFileSucess = true;

            updateDir = new File(Environment.getExternalStorageDirectory() + "/" + APPROOT + "/");
            updateFile = new File(updateDir + "/" + app_name);

            if (!updateDir.exists()) {
                updateDir.mkdirs();
            }
            if (!updateFile.exists()) {
                try {
                    updateFile.createNewFile();
                } catch (IOException e) {
                    isCreateFileSucess = false;
                    e.printStackTrace();
                }
            }
        } else {
            isCreateFileSucess = false;
        }

        return isCreateFileSucess;
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable) {
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                    drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        } else {
            return null;
        }
    }

    /**
     * 应用程序是否已安装
     *
     * @param context
     * @param packageName 应用程序的包名
     * @return
     */
    public static boolean isInstalled(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 保存到相册
     *
     * @param context
     * @param file
     */
    public static void savePicInfoToMediaStore(Context context, File file) {
        ContentResolver contentResolver = context.getContentResolver();

        ContentValues newValues = new ContentValues(6);
        newValues.put(MediaStore.Images.Media.TITLE, file.getName());
        newValues.put(MediaStore.Images.Media.DISPLAY_NAME, file.getName());
        newValues.put(MediaStore.Images.Media.DATA, file.getPath());
        newValues.put(MediaStore.Images.Media.DATE_MODIFIED, System.currentTimeMillis() / 1000);
        newValues.put(MediaStore.Images.Media.SIZE, file.length());
        newValues.put(MediaStore.Images.Media.MIME_TYPE, "image/png");

        int i = contentResolver.update(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, newValues,
                MediaStore.Images.Media.DATA + " =?", new String[]{file.getPath()});
        if (i == 0) {// 更新失败
            context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, newValues);
        }

        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getParent())));
    }

    public static final int IMAGE_SIZE = 32768;// 微信分享图片大小限制

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int options = 100;
        bmp.compress(CompressFormat.JPEG, options, output);
        while (output.toByteArray().length > IMAGE_SIZE && options != 10) {
            output.reset(); // 清空baos
            bmp.compress(CompressFormat.JPEG, options, output);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;
        }
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static final String ENCODING = "UTF-8";

    // 从assets 文件夹中获取文件并读取数据
    public static String getFromAssets(String fileName) {
        String result = "";
        try {
            InputStream in = App.mContext.getResources().getAssets().open(fileName);
            // 获取文件的字节数
            int lenght = in.available();
            // 创建byte数组
            byte[] buffer = new byte[lenght];
            // 将文件中的数据读到byte数组中
            in.read(buffer);
           // result = EncodingUtils.getString(buffer, ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
