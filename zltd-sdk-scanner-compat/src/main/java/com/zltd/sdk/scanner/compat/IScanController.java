/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.sdk.scanner.compat;

import android.view.KeyEvent;

/**
 * 扫描控制类接口
 *
 * @author Engine100
 */
public interface IScanController {

    /**
     * 启动扫描应用，在onResume里面
     */
    void openScanner();

    /**
     * 关闭or停止扫描应用，程序最后退出的时候调用，N5000和N7000调用会导致扫描头出错
     */
    void closeScanner();

    /**
     * 设置扫描结果监听
     */
    void setScanResult(IScanResult result);

    /**
     * 移除监听，在onPause里面
     */
    void removeScanResult();

    /**
     * 获得扫描模式
     */
    @ScanMode.Model
    int getScanMode();

    /**
     * 设置扫描模式
     */
    void setScanMode(@ScanMode.Model int mode);

    /**
     * 单次扫描
     */
    void startSingleScan();

    /**
     * 开启连扫
     */
    void startContinueScan();

    /**
     * 停止连扫
     */
    void stopScan();

    /**
     * Activity里用到
     */
    boolean onKeyDown(int keyCode, KeyEvent event);

    /**
     * Activity里用到
     */
    boolean onKeyUp(int keyCode, KeyEvent event);

    /**
     *
     */
    boolean dispatchKeyEvent(KeyEvent event);
}
