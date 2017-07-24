package com.zltd.sdk.scanner.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.zltd.sdk.scanner.compat.IScanResult;
import com.zltd.sdk.scanner.compat.ScanAdapter;
import com.zltd.sdk.scanner.util.ScanAdapterUtils;

/**
 * 继承普通Activity
 * 和AppCompatActivity 的实现是一样的
 *
 * @author Engine100
 */
public abstract class ZLTDScanActivity extends Activity {
    protected ScanAdapter mScanAdapter = ScanAdapter.getInstance();

    //不用activity直接实现接口，
    // 是因为onScanSuccess这个名字可以让子activity用onScannerSuccess来拦截后比如加个声音什么的，
    // 再用onScanSuccess作为抽象方法，也可以不这样，但是。。。我喜欢。>.<
    private IScanResult mIScanResult = new IScanResult() {
        @Override
        public void onScanSuccess(String code) {
            if (beforeScanSuccess(code)) {
                onScannerSuccess(code);
            }
        }

        @Override
        public void onScanError(String msg) {
            if (beforeScanError(msg)) {
                onScannerError(msg);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScanAdapterUtils.registerScreenActionReceiver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScanAdapter.openBySingleModel(this, mIScanResult);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // removeScanResult方法如果不调用的话，
        // 在其他界面如果不重新设置scanResult，扫描会继续回调结果
        mScanAdapter.removeScanResult();

        //不需要close，可以在最后退出程序的时候close，打开关闭扫描头费时间
        //mScanAdapter.close();
    }

    protected boolean beforeScanSuccess(String code) {
        return true;
    }

    protected boolean beforeScanError(String msg) {
        return true;
    }

    /**
     * 扫描成功后调用这个方法
     */
    protected abstract void onScannerSuccess(String code);

    /**
     * 扫描异常后调用这个方法，比如超时或者转换失败
     */
    protected void onScannerError(String msg) {

    }

    protected void onStartScan() {
        ScanAdapterUtils.startScan();
    }

    protected void onStopScan() {
        mScanAdapter.stopScan();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mScanAdapter.onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (mScanAdapter.onKeyUp(keyCode, event)) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (mScanAdapter.dispatchKeyEvent(event)) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

}
