package com.lizhidu.androidapparchitecturedesign.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.LayoutAnimationController;
import android.widget.ListView;

import com.lizhidu.androidapparchitecturedesign.MyApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 工具类
 */
public class JudgeUtil {
    public final static String TAG = "";
    public static SimpleDateFormat sdfL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat sdfS = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat sdfS1 = new SimpleDateFormat("yyyy.MM.dd");
    public static SimpleDateFormat sdfS2 = new SimpleDateFormat("MM.dd HH:mm:ss");
    public static boolean networkAvailable = false;

    public static int SCANNER_WIDT = 0;

    public static void debug(String msg) {
        Log.d(TAG, msg);
    }

    public static void info(String msg) {
        Log.i(TAG, msg);
    }

    public static void warn(String msg) {
        Log.w(TAG, msg);
    }

    public static void verbose(String msg) {
        Log.v(TAG, msg);
    }

    public static void error(String msg) {
        Log.e(TAG, msg);
    }

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str)
                || "NULL".equals(str.toUpperCase());
    }

    /**
     * 判断String是否是由字母和数字组成
     */
    public static boolean isAlphanumeric(String str) {
        if (str == null || str.length() > 14) {
            return false;
        }
        boolean flag = false;
        String check = "^[A-Za-z0-9_]+$";// 数组和字母混排
        Pattern pattern = Pattern.compile(check);
        flag = pattern.matcher(str).matches();
        return flag;
    }

    public static boolean isAlphanumeric2(String str) {
        if (str == null || str.length() < 6 || str.length() > 20) {
            return false;
        }
        boolean flag = false;
        String check = "^[A-Za-z0-9_]+$";// 数组和字母混排
        Pattern pattern = Pattern.compile(check);
        flag = pattern.matcher(str).matches();
        return flag;
    }


    /**
     * 判断String是否是手机号
     */
    public static boolean isMobile(String str) {
        boolean flag = false;
        String check = "^1(([3][0123456789])|([4][012356789])|([5][012356789])|([7][012356789])|([8][0256789]))[0-9]{8}$";// 手机号
        Pattern pattern = Pattern.compile(check);
        flag = pattern.matcher(str).matches();
        return flag;
    }

    /**
     * 判断String是否是邮箱
     */
    public static boolean isEmail(String str) {
        boolean flag = false;
        String check = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern pattern = Pattern.compile(check);
        flag = pattern.matcher(str).matches();
        return flag;
    }

    /**
     * 判断String是否是身份证号
     */
    public static boolean isCardId(String str) {
        boolean flag = false;
//		 /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
        String check = "(^\\d{18}$)|(^\\d{15}$)|(^\\d{17}(\\d|X|x)$)";
        Pattern pattern = Pattern.compile(check);
        flag = pattern.matcher(str).matches();
        return flag;
    }

    /**
     * 判断String是否为空
     */
    public static boolean isBlank(String str) {
        boolean flag = false;
        if (str == null || str.length() == 0) {
            flag = true;
        }
        return flag;
    }

    public static String getShortTime(String time) {
        if (!JudgeUtil.isBlank(time)) {
            if ((time.substring(0, 10)).trim().equalsIgnoreCase(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).trim())) {
                return "今天 " + time.substring(11, 16);
            } else {
                return time.substring(5, 16);
            }
        } else {
            return "";
        }

    }

    public static String getShortTime2(String time) {
        if (!JudgeUtil.isBlank(time)) {
            if ((time.substring(0, 10)).trim().equalsIgnoreCase(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).trim())) {
                return "最新 " + time.substring(11, 16);
            } else {
                return "最新 " + time.substring(5, 10);
            }
        } else {
            return "";
        }

    }

    public static File getCacheDirectory(Context context) {
        File appCacheDir = null;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            appCacheDir = getExternalCacheDir(context);
        }
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }
        return appCacheDir;
    }

    /**
     * 得到外部缓存路径
     *
     * @param context
     * @return
     */
    private static File getExternalCacheDir(Context context) {
        File dataDir = new File(new File(
                Environment.getExternalStorageDirectory(), "sdf"), "data");
        File appCacheDir = new File(
                new File(dataDir, context.getPackageName()), "cache");
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                //log.warning("Unable to create external cache directory");
                return null;
            }
            try {
                new File(appCacheDir, ".nomedia").createNewFile();
            } catch (IOException e) {
                //log.error("Can't create \".nomedia\" file in application external cache directory");
            }
        }
        return appCacheDir;
    }

    /**
     * 把View绘制到Bitmap上
     *
     * @param view   需要绘制的View
     * @param width  该View的宽度
     * @param height 该View的高度
     * @return 返回Bitmap对象
     */
    public static Bitmap getBitmapFromView(View view, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    public static String imgToBase64(String imgPath) {
        Bitmap bitmap = null;
        if (imgPath != null && imgPath.length() > 0) {
            bitmap = readBitmap(imgPath);
        }
        if (bitmap == null) {
            //bitmap not found!!  
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();

            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Bitmap readBitmap(String imgPath) {
        try {
            return BitmapFactory.decodeFile(imgPath);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * @param base64Data
     * @param imgName
     * @param imgFormat  图片格式
     */
    public static void base64ToBitmap(String base64Data, String imgName, String imgFormat) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        File myCaptureFile = new File("/sdcard/", imgName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myCaptureFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean isTu = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        if (isTu) {
            // fos.notifyAll();  
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setAnimListView(ListView listView) {
        AlphaAnimation animation = new AlphaAnimation(0, 1f);
        animation.setDuration(4000);
        LayoutAnimationController controller = new LayoutAnimationController(animation, 0.5f);
        listView.setLayoutAnimation(controller);
    }

    public static void setScannerWidth(Activity ac) {
        DisplayMetrics dm = new DisplayMetrics();
        ac.getWindowManager().getDefaultDisplay().getMetrics(dm);
        SCANNER_WIDT = dm.widthPixels;
    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) MyApplication.mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                if ((b & 0xFF) < 0x10) hex.append("0");
                hex.append(Integer.toHexString(b & 0xFF));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return string;
    }
}
