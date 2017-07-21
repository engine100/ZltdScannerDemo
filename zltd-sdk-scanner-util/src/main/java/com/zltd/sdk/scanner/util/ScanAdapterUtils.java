/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.sdk.scanner.util;

import android.content.Context;

import com.zltd.sdk.scanner.compat.ScanAdapter;
import com.zltd.sdk.scanner.compat.ScanMode;

/**
 * 一些扫描适配的工具
 *
 * @author Engine100
 */
public class ScanAdapterUtils {
    private static ScanAdapter mScanAdapter = ScanAdapter.getInstance();

    /**
     * 程序退出的时候调用
     */
    public static void exitApplication(Context context) {
        ScanAdapter scanAdapter = ScanAdapter.getInstance();
        ScannerReceiverUtils.unRegisterScreenActionReceiver(context);
        scanAdapter.closeScanner();
    }

    public static void registerScreenActionReceiver(Context context) {
        ScannerReceiverUtils.registerScreenActionReceiver(context);
    }

    public static void unRegisterScreenActionReceiver(Context context) {
        ScannerReceiverUtils.unRegisterScreenActionReceiver(context);
    }

    /**
     * 如果是单扫模式，就开启单扫
     */
    public static void startSingleScan() {
        if (mScanAdapter.getScanMode() == ScanMode.SCAN_SINGLE_MODE) {
            mScanAdapter.startSingleScan();
        }
    }

    /**
     * 如果是连扫模式，就开启连扫
     */
    public static void startContinueScan() {
        if (mScanAdapter.getScanMode() == ScanMode.SCAN_CONTINUOUS_MODE) {
            mScanAdapter.startContinueScan();
        }
    }

    /**
     * 开始扫描，模式与设定有关
     */
    public static void startScan() {

        if (mScanAdapter.getScanMode() == ScanMode.SCAN_SINGLE_MODE) {
            mScanAdapter.startSingleScan();
        } else if (mScanAdapter.getScanMode() == ScanMode.SCAN_CONTINUOUS_MODE) {
            mScanAdapter.startContinueScan();
        }

    }

    /**
     * 停止扫描
     */
    public static void stopScan() {
        if (mScanAdapter.getScanMode() == ScanMode.SCAN_SINGLE_MODE) {
            mScanAdapter.stopScan();
        } else if (mScanAdapter.getScanMode() == ScanMode.SCAN_CONTINUOUS_MODE) {
            mScanAdapter.stopScan();
        }
    }

}
