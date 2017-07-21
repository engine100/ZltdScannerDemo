/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.sdk.scanner.compat.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.zltd.sdk.scanner.compat.BaseScanController;
import com.zltd.sdk.scanner.compat.ScanMode;

import java.io.UnsupportedEncodingException;

/**
 * 通过发送广播传递数据
 *
 * @author Engine100
 */
public class ZLTDBroadcastController extends BaseScanController {

    //注册接受扫描数据的广播
    private static final String RECEIVE_SCANDATA_ACTION = "android.intent.action.GET_SCANDATA";

    //接到扫描返回的广播里携带的数据
    private static final String RECEIVE_SCANDATA_EXTRA = "android.intent.extra.SCAN_DATA";

    //打开或者关闭扫描头action
    private static final String ENABLE_SCANNER_ACTION = "android.intent.action.SCANKEY_SWITCH";

    //携带关闭或打开的flag
    private static final String ENABLE_SCANNER_EXTRA = "android.intent.extra.SCANKEY_SWITCH_DATA";

    //打开1，关闭0
    private static final int ENABLE_SCANNER_EXTRA_ON = 1;
    private static final int ENABLE_SCANNER_EXTRA_OFF = 0;

    private static final String TAG = "ZLTDBroadcastController";

    private BroadcastReceiver mBarcodeReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(RECEIVE_SCANDATA_ACTION)) {

                Bundle bundle = intent.getExtras();
                byte[] scanDataBytes = bundle.getByteArray(RECEIVE_SCANDATA_EXTRA);
                String result = null;
                try {
                    result = new String(scanDataBytes, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
//                scanSuccess(intent.getStringExtra("barcode"));
                if (result != null) {
                    scanSuccess(result);
                } else {
                    scanError("Decode null");
                }
            }
        }
    };

    public ZLTDBroadcastController(Context context) {
        super(context);
    }

    /**
     *
     */
    @Override
    public void openScanner() {
        Intent intent = new Intent();
        intent.setAction(ENABLE_SCANNER_ACTION);
        intent.putExtra(ENABLE_SCANNER_EXTRA, ENABLE_SCANNER_EXTRA_ON);
        mContext.sendBroadcast(intent);

        mContext.registerReceiver(mBarcodeReceiver, new IntentFilter(RECEIVE_SCANDATA_ACTION));
    }

    /**
     *
     */
    @Override
    public void closeScanner() {
        Intent intent = new Intent();
        intent.setAction(ENABLE_SCANNER_ACTION);
        intent.putExtra(ENABLE_SCANNER_EXTRA, ENABLE_SCANNER_EXTRA_OFF);
        mContext.sendBroadcast(intent);

        try {
            //如果没有注册过这个广播会报 IllegalArgumentException
            mContext.unregisterReceiver(mBarcodeReceiver);
        } catch (Exception e) {
            //ignore
        }
    }

    @Override
    @ScanMode.Model
    public int getScanMode() {
        return ScanMode.SCAN_SINGLE_MODE;
    }

    @Override
    public void setScanMode(@ScanMode.Model int mode) {

    }

    /**
     *
     */
    @Override
    public void startSingleScan() {

        //模拟扫描按键扫
        /*scanExecutor.submit(new Runnable() {
            public void run() {
                inst.sendKeyDownUpSync(96);
            }
        });*/

        //发送广播扫
        Intent intent = new Intent();
        intent.setAction("android.intent.action.START_SCAN");
        if (android.os.Build.VERSION.SDK_INT >= 12) {
            //3.1以后的版本后有限制Intent.FLAG_INCLUDE_STOPPED_PACKAGES
            intent.setFlags(32);
        }
        mContext.sendBroadcast(intent);
    }

    /**
     *
     */
    @Override
    public void startContinueScan() {
        startSingleScan();
    }

    /**
     *
     */
    @Override
    public void stopScan() {

    }

}
