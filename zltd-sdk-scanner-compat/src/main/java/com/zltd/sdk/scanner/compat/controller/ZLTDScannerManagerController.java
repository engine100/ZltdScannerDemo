/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.sdk.scanner.compat.controller;

import android.content.Context;

import com.zltd.industry.ScannerManager;
import com.zltd.sdk.scanner.compat.BaseScanController;
import com.zltd.sdk.scanner.compat.IScanController;
import com.zltd.sdk.scanner.compat.ScanMode;

/**
 * ScannerManager
 * 一般适配机器
 * N2S,N2S000,N5
 *
 * @author Engine100
 */
public class ZLTDScannerManagerController extends BaseScanController implements IScanController {

    private ScannerManager mScannerManager = ScannerManager.getInstance();
    private ScannerManager.IScannerStatusListener mIScannerStatusListener = new ScannerManager.IScannerStatusListener() {

        @Override
        public void onScannerStatusChanage(int status) {
        }

        /**
         * 一旦扫描到结果就会调用
         */
        @Override
        public void onScannerResultChanage(final byte[] result) {
            String resultCode = null;
            try {
                resultCode = new String(result, "UTF-8");
                scanSuccess(resultCode);
            } catch (Exception e) {
                e.printStackTrace();
                scanError(ScannerManager.DECODER_TIMEOUT);
            }

        }
    };

    public ZLTDScannerManagerController(Context context) {
        super(context);
    }

    public ScannerManager getScannerManager() {
        return mScannerManager;
    }

    @Override
    public void openScanner() {
        openScannerManager();
    }

    private void openScannerManager() {

        if (mScannerManager != null) {
            mScannerManager.scannerEnable(true);
            //N2S000 机器里没有这个方法，N5 二维机器需要这个方法打开
            invokeMethod(mScannerManager, "connectDecoderSRV");
            mScannerManager.setScanMode(ScannerManager.SCAN_SINGLE_MODE);
            mScannerManager.setDataTransferType(ScannerManager.TRANSFER_BY_API);
            mScannerManager.removeScannerStatusListener(mIScannerStatusListener);
            mScannerManager.addScannerStatusListener(mIScannerStatusListener);
        }
    }

    @Override
    public void closeScanner() {
        closeScannerManager();
    }

    private void closeScannerManager() {

        if (mScannerManager != null) {
            mScannerManager.removeScannerStatusListener(mIScannerStatusListener);
            mScannerManager.stopContinuousScan();
            mScannerManager.scannerEnable(false);
            invokeMethod(mScannerManager, "stopDecode");
            //N2S000 机器里没有这个方法，N5机器需要这个方法关闭
            invokeMethod(mScannerManager, "disconnectDecoderSRV");
        }
    }

    @Override
    @ScanMode.Model
    public int getScanMode() {
        if (mScannerManager != null) {
            // 单扫
            if (mScannerManager.getScanMode() == ScannerManager.SCAN_SINGLE_MODE) {
                return ScanMode.SCAN_SINGLE_MODE;
            }
            // 连扫
            if (mScannerManager.getScanMode() == ScannerManager.SCAN_CONTINUOUS_MODE) {
                return ScanMode.SCAN_CONTINUOUS_MODE;
            }
            // 按键保持
            if (mScannerManager.getScanMode() == ScannerManager.SCAN_KEY_HOLD_MODE) {
                return ScanMode.SCAN_KEY_HOLD_MODE;
            }
        }

        return ScanMode.SCAN_SINGLE_MODE;

    }

    @Override
    public void setScanMode(@ScanMode.Model int mode) {

        if (mScannerManager != null) {
            // 单扫
            if (mode == ScanMode.SCAN_SINGLE_MODE) {
                mScannerManager.setScanMode(ScannerManager.SCAN_SINGLE_MODE);
            }
            // 连扫
            else if (mode == ScanMode.SCAN_CONTINUOUS_MODE) {
                mScannerManager.setScanMode(ScannerManager.SCAN_CONTINUOUS_MODE);
            }
            // 按键保持
            else if (mode == ScanMode.SCAN_KEY_HOLD_MODE) {
                mScannerManager.setScanMode(ScannerManager.SCAN_KEY_HOLD_MODE);
            }
        }

    }

    @Override
    public void startSingleScan() {
        if (mScannerManager != null) {
            mScannerManager.singleScan();
        }
    }

    @Override
    public void startContinueScan() {
        if (mScannerManager != null) {
            mScannerManager.startContinuousScan();
        }
    }

    @Override
    public void stopScan() {
        if (mScannerManager != null) {
            if (mScannerManager.getScanMode() == ScannerManager.SCAN_CONTINUOUS_MODE) {
                mScannerManager.stopContinuousScan();
            } else {
                invokeMethod(mScannerManager, "stopShootImmediately");
            }
        }
    }

}
