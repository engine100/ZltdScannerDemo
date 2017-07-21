/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.sdk.scanner.compat;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.CallSuper;
import android.util.Log;
import android.view.KeyEvent;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 扫描控制基类
 *
 * @author Engine100
 */
public abstract class BaseScanController implements IScanController {
    private static final String TAG = "BaseScanController";
    protected static ExecutorService mSingleExecutor = Executors.newSingleThreadExecutor();
    protected static Instrumentation mInstrumentation = new Instrumentation();

    protected Context mContext;
    protected IScanResult mScanResult;
    protected Handler mainHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message msg) {
            if (mScanResult == null) {
                return;
            }
            switch (msg.what) {
                case 0:
                    mScanResult.onScanSuccess(msg.obj.toString());
                    break;
                case 1:
                    mScanResult.onScanError(msg.obj.toString());
                    break;
                default:
                    break;
            }

        }
    };

    public BaseScanController(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 设置结果监听
     */
    @Override
    @CallSuper
    public void setScanResult(IScanResult result) {
        Log.e(TAG, "设置扫描监听");
        mScanResult = result;
    }

    @Override
    @CallSuper
    public void removeScanResult() {
        Log.e(TAG, "移除扫描监听");
        mScanResult = null;
    }

    /**
     * 成功扫描
     *
     * @param code
     */
    protected void scanSuccess(final String code) {
        Message msg = new Message();
        msg.what = 0;
        msg.obj = code;
        mainHandler.sendMessage(msg);
    }

    /**
     * 扫描失败
     *
     * @param error
     */
    protected void scanError(String error) {
        Message msg = new Message();
        msg.what = 1;
        msg.obj = error;
        mainHandler.sendMessage(msg);
    }

    protected boolean hasMethod(Object object, String methodName) {
        if (object == null) return false;

        Class<?> clazz = object.getClass();
        Method method = null;
        try {
            method = clazz.getMethod(methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (method != null);
    }

    protected void invokeMethod(Object object, String methodName) {
        if (object == null) return;

        Class<?> clazz = object.getClass();
        try {
            Method method = clazz.getMethod(methodName);
            if (method != null) method.invoke(object);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return false;
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        return false;
    }

}
