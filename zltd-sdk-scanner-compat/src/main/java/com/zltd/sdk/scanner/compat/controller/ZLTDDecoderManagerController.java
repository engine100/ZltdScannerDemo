/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.sdk.scanner.compat.controller;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;

import com.zltd.decoder.Constants;
import com.zltd.decoder.DecoderManager;
import com.zltd.decoder.DecoderManager.IDecoderStatusListener;
import com.zltd.sdk.scanner.compat.BaseScanController;
import com.zltd.sdk.scanner.compat.IScanController;
import com.zltd.sdk.scanner.compat.ScanMode;

/**
 * DecoderManager
 * 一般适配机器N5000,N7000
 *
 * @author Engine100
 */
public class ZLTDDecoderManagerController extends BaseScanController implements IScanController {

    public static int KEY_SCAN = 96;
    private DecoderManager mDecoderManager = DecoderManager.getInstance();

    private IDecoderStatusListener mIDecoderStatusListener = new IDecoderStatusListener() {

        @Override
        public void onDecoderStatusChanage(int arg0) {

        }

        public void onDecoderResultChanage(String result, String time) {
            onResult(result);
        }

        public void onDecoderResultChanage(String result, Bundle paramBundle) {
            //onResult(result);
        }

        private void onResult(String result) {
            // Decode is interruptted or timeout ...
            if (result == null) {
                scanError("Decode is null");
            }
            if (result.startsWith("Decode is")) {
                scanError(result);
            } else {
                scanSuccess(result);
            }
        }

    };

    public ZLTDDecoderManagerController(Context context) {
        super(context);
    }

    public DecoderManager getDecoderManager() {
        return mDecoderManager;
    }

    @Override
    public void openScanner() {
        openDecoderManager();
    }

    private void openDecoderManager() {
        if (mDecoderManager != null) {

            mDecoderManager.connectDecoderSRV();
            mDecoderManager.setScanMode(Constants.SINGLE_SHOOT_MODE);
            mDecoderManager.setDataTransferType(Constants.TRANSFER_BY_API);
            mDecoderManager.removeDecoderStatusListener(mIDecoderStatusListener);
            mDecoderManager.addDecoderStatusListener(mIDecoderStatusListener);

           /* mDecoderManager.enableLight(Constants.LOCATION_LIGHT, true);
            if (mDecoderManager.getFlashMode() == Constants.FLASH_ALWAYS_ON_MODE) {
                mDecoderManager.enableLight(Constants.FLASH_LIGHT, true);
                mDecoderManager.enableLight(Constants.FLOOD_LIGHT, true);
            }*/
        }
    }

    @Override
    public void closeScanner() {
        closeDecoderManager();
    }

    private void closeDecoderManager() {
        if (mDecoderManager != null) {
            mDecoderManager.removeDecoderStatusListener(mIDecoderStatusListener);
            mDecoderManager.stopDecode();
            mDecoderManager.disconnectDecoderSRV();
        }
    }

    @Override
    @ScanMode.Model
    public int getScanMode() {

        if (mDecoderManager != null) {
            // 单扫
            if (mDecoderManager.getScanMode() == Constants.SINGLE_SHOOT_MODE) {
                return ScanMode.SCAN_SINGLE_MODE;
            }
            // 连扫
            if (mDecoderManager.getScanMode() == Constants.CONTINUOUS_SHOOT_MODE) {
                return ScanMode.SCAN_CONTINUOUS_MODE;
            }
            // 按键保持
            if (mDecoderManager.getScanMode() == Constants.HOLD_SHOOT_MODE) {
                return ScanMode.SCAN_KEY_HOLD_MODE;
            }
        }

        return ScanMode.SCAN_SINGLE_MODE;

    }

    @Override
    public void setScanMode(@ScanMode.Model int mode) {

        if (mDecoderManager != null) {
            // 单扫
            if (mode == ScanMode.SCAN_SINGLE_MODE) {
                mDecoderManager.setScanMode(Constants.SINGLE_SHOOT_MODE);
            }
            // 连扫
            else if (mode == ScanMode.SCAN_CONTINUOUS_MODE) {
                mDecoderManager.setScanMode(Constants.CONTINUOUS_SHOOT_MODE);
            }
            // 按键保持
            else if (mode == ScanMode.SCAN_KEY_HOLD_MODE) {
                mDecoderManager.setScanMode(Constants.HOLD_SHOOT_MODE);
            }
        }
    }

    @Override
    public void startSingleScan() {

        if (mDecoderManager != null) {
            mDecoderManager.singleShoot();
        }
    }

    @Override
    public void startContinueScan() {
        if (mDecoderManager != null) {
            if (mDecoderManager.getScanMode() == Constants.CONTINUOUS_SHOOT_MODE) {
                mDecoderManager.continuousShoot();
            }
        }
    }

    @Override
    public void stopScan() {

        if (mDecoderManager != null) {
            if (mDecoderManager.getScanMode() == Constants.CONTINUOUS_SHOOT_MODE && mDecoderManager.isContinuousShootState()) {
                mDecoderManager.stopContinuousShoot();
            } else {
                mDecoderManager.stopShootImmediately();
            }
        }

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mDecoderManager != null) {
            if (keyCode == KEY_SCAN && event.getRepeatCount() == 0 && mDecoderManager.getScanMode() == Constants.HOLD_SHOOT_MODE) {
                mDecoderManager.dispatchScanKeyEvent(event);
            }
        }

        return false;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (mDecoderManager != null) {
            if (keyCode == KEY_SCAN && event.getRepeatCount() == 0 && mDecoderManager.getScanMode() == Constants.HOLD_SHOOT_MODE) {
                mDecoderManager.dispatchScanKeyEvent(event);
            }
        }

        return false;
    }

    public boolean dispatchKeyEvent(KeyEvent event) {

        if (mDecoderManager != null) {
            if (event.getKeyCode() == KEY_SCAN && event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                if (mDecoderManager.getScanMode() == Constants.SINGLE_SHOOT_MODE) {
                    startSingleScan();
                    return true;
                } else if (mDecoderManager.getScanMode() == Constants.CONTINUOUS_SHOOT_MODE) {
                    startContinueScan();
                    return true;
                }
            }
        }

        return false;
    }

}
