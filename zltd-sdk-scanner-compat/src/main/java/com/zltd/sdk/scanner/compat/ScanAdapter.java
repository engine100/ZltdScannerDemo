/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.sdk.scanner.compat;

import android.content.Context;
import android.view.KeyEvent;

/**
 * 扫描适配类
 *
 * @author Engine100
 */
public class ScanAdapter {

    private static ScanAdapter instance = new ScanAdapter();
    protected Context mContext;
    protected IScanController mController;

    private ScanAdapter() {

    }

    public static ScanAdapter getInstance() {
        return instance;
    }

    /**
     * 一步初始化,并打开单扫模式
     * mScanAdapter.init(this, true);
     * mScanAdapter.open();
     * mScanAdapter.setScanMode(ScanMode.SCAN_SINGLE_MODE);
     * mScanAdapter.setScanResult(mIScanResult);
     */
    public void openBySingleModel(Context context, IScanResult scanResult) {
        init(context, true);
        openScanner();
        setScanMode(ScanMode.SCAN_SINGLE_MODE);
        setScanResult(scanResult);
    }

    /**
     * 初始化
     *
     * @param context
     * @param useDefaultController true的时候使用默认的controller工厂创建
     */
    public void init(Context context, boolean useDefaultController) {
        if (mContext == null) {
            mContext = context.getApplicationContext();
        }

        if (mController == null) {
            if (useDefaultController) {
                initDefaultController();
            }
        }
    }

    private void initDefaultController() {
        mController = ControllerFactory.createController(mContext);
    }

    /**
     * 自定义controller
     * 如果 isOverride为true，重新设置controller，否则只有当controller为null的时候才设置
     */
    public void initScanController(IScanController controller, boolean isOverride) {
        if (isOverride) {
            mController = controller;
        } else {
            if (mController == null) {
                mController = controller;
            }
        }
    }

    /**
     * 是否支持扫描，controller不为null为true，需要适配器自己判断
     */
    public boolean isSupportedScan() {
        return mController != null;
    }

    /**
     * 打开扫描头，可以扫描了
     */
    public void openScanner() {
        if (mController != null) {
            mController.openScanner();
        }
    }

    /**
     * 关闭扫描头，不可以扫描了
     */
    public void closeScanner() {
        if (mController != null) {
            mController.closeScanner();
        }
        mContext = null;
    }

    /**
     * 设置结果监听
     */
    public void setScanResult(IScanResult result) {
        if (mController != null) {
            mController.setScanResult(result);
        }
    }

    public void removeScanResult() {
        if (mController != null) {
            mController.removeScanResult();
        }
    }

    /**
     * 获得扫描模式
     * <p>
     * {@link ScanMode#SCAN_SINGLE_MODE}
     * {@link ScanMode#SCAN_CONTINUOUS_MODE}
     * {@link ScanMode#SCAN_KEY_HOLD_MODE}
     */
    public int getScanMode() {
        if (mController != null) {
            return mController.getScanMode();
        }
        return ScanMode.SCAN_SINGLE_MODE;
    }

    /**
     * 设置扫描模式
     * <p>
     * {@link ScanMode#SCAN_SINGLE_MODE}
     * {@link ScanMode#SCAN_CONTINUOUS_MODE}
     * {@link ScanMode#SCAN_KEY_HOLD_MODE}
     */
    public void setScanMode(@ScanMode.Model int model) {
        if (mController != null) {
            mController.setScanMode(model);
        }
    }

    /**
     * 开始一次扫描
     */
    public void startSingleScan() {
        if (mController != null) {
            mController.startSingleScan();
        }
    }

    /**
     * 开始连扫，连扫模式才可以
     */
    public void startContinueScan() {
        if (mController != null) {
            mController.startContinueScan();
        }
    }


    /**
     * 停止扫描，如果连扫也停止
     */
    public void stopScan() {
        // 如果是连扫就关闭
        if (mController != null) {
            mController.stopScan();
        }
    }

    /**
     * 获得实际的控制器
     */
    public IScanController getController() {
        return mController;
    }

    /**
     * 在Activity生命周期里调用
     * 只返回false
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mController == null) {
            return false;
        }
        mController.onKeyDown(keyCode, event);
        return false;
    }

    /**
     * 在Activity生命周期里调用
     * 只返回false
     */
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (mController == null) {
            return false;
        }

        mController.onKeyUp(keyCode, event);
        return false;
    }

    /**
     * 在Activity生命周期里调用
     */
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (mController == null) {
            return false;
        }

        if (mController.dispatchKeyEvent(event)) {
            return true;
        }

        return false;
    }

    /**
     * 手动补光
     * N5000或者N7000有这个
     */
    public void enableLight() {

        if (mController == null) {
            return;
        }

    }

    /**
     * 关闭手动补光
     * N5000或者N7000有这个
     */
    public void disableLight() {
        if (mController == null) {
            return;
        }

    }


}
