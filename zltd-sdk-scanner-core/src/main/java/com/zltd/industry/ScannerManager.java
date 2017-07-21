/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.industry;

import android.view.KeyEvent;

public class ScannerManager {
    public static final String SCANNER_SERVICE = "IScannerService";
    public static final String SCANNER_ACTION = "android.intent.action.SCANNER_SERVICE";
    public static final String GET_SCANDATA = "android.intent.action.RECEIVE_SCANDATA";
    public static final String SCAN_DATA = "android.intent.extra.SCAN_DATA";
    public static final int RETURN_SUCCESS = 257;
    public static final int RETURN_ENGINE_BUSY = 258;
    public static final int RETURN_ENGINE_TYPE_ERR = 259;
    public static final int RETURN_SERIAL_PORT_ERR = 260;
    public static final int RETURN_TRIGGER_DEVICE_ERR = 261;
    public static final int RETURN_NOT_IMPL = 262;
    public static final int LOW_LEVEL = 0;
    public static final int HIGH_LEVEL = 1;
    public static final int SCAN_ENGINE_UNKNOWN = 1000;
    public static final int SCAN_ENGINE_MOTO = 1001;
    public static final int SCAN_ENGINE_HONEYWELL = 1002;
    public static final int SCAN_ENGINE_MINDEO = 1003;
    public static final int SCAN_ENGINE_NEWLAND = 1004;
    public static final int SCAN_SINGLE_MODE = 1;
    public static final int SCAN_CONTINUOUS_MODE = 2;
    public static final int SCAN_KEY_HOLD_MODE = 3;
    public static final int TRANSFER_BY_EDITTEXT = 1;
    public static final int TRANSFER_BY_KEY = 2;
    public static final int TRANSFER_BY_API = 3;
    public static final String DECODER_TIMEOUT = "Decode is interruptted or timeout ...";
    public static final String REQUEST = "request";
    public static final String RESULT = "result";

    private ScannerManager() {
    }

    public static ScannerManager getInstance() {
        return null;
    }

    public int keyShoot(boolean on) {
        throw new RuntimeException("Stub!");
    }

    public int quitWithReason(int on) {
        throw new RuntimeException("Stub!");
    }

    public boolean isKeyShootEnabled() {
        throw new RuntimeException("Stub!");
    }

    public boolean isInKeyShoot() {
        throw new RuntimeException("Stub!");
    }

    public int getScanEngineType() {
        throw new RuntimeException("Stub!");
    }

    public boolean getContinuousScanning() {
        throw new RuntimeException("Stub!");
    }

    public int setScanEngineType(int type) {
        throw new RuntimeException("Stub!");
    }

    public String getScanEngineInfo() {
        throw new RuntimeException("Stub!");
    }

    public int getLaserTimeout() {
        throw new RuntimeException("Stub!");
    }

    public int resetFactory() {
        throw new RuntimeException("Stub!");
    }

    public void singleScan() {
        throw new RuntimeException("Stub!");
    }
//
//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public void cleanUp() {
//    }
//
//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public void getScannerIsReady() {
//    }
//
//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public void continuousScan() {
//        throw new RuntimeException("Stub!");
//    }
//
//    /**
//     * @deprecated
//     */
//    @Deprecated
//    public void stopScan() {
//        throw new RuntimeException("Stub!");
//    }

    public int startContinuousScan() {
        throw new RuntimeException("Stub!");
    }

    public int stopContinuousScan() {
        throw new RuntimeException("Stub!");
    }

    public int startKeyHoldScan() {
        throw new RuntimeException("Stub!");
    }

    public int stopKeyHoldScan() {
        throw new RuntimeException("Stub!");
    }

    public int triggerLevel(int level) {
        throw new RuntimeException("Stub!");
    }

    public void sendCommand(byte[] command) {
        throw new RuntimeException("Stub!");
    }

    public int getScanMode() {
        throw new RuntimeException("Stub!");
    }

    public int setScanMode(int scanMode) {
        throw new RuntimeException("Stub!");
    }

    public int getDataTransferType() {
        throw new RuntimeException("Stub!");
    }

    public int setDataTransferType(int transferType) {
        throw new RuntimeException("Stub!");
    }

    public void dispatchScanKeyEvent(KeyEvent keyEvent) {
        throw new RuntimeException("Stub!");
    }

    public void scannerEnable(boolean enable) {
        throw new RuntimeException("Stub!");
    }

    public boolean getScannerEnable() {
        throw new RuntimeException("Stub!");
    }

    public boolean getScannerSoundEnable() {
        throw new RuntimeException("Stub!");
    }

    public void setScannerSoundEnable(boolean enable) {
        throw new RuntimeException("Stub!");
    }

    public String getScannerExtra() {
        throw new RuntimeException("Stub!");
    }

    public void setScannerExtra(String extra) {
        throw new RuntimeException("Stub!");
    }

    public boolean getScannerVibratorEnable() {
        throw new RuntimeException("Stub!");
    }

    public void setScannerVibratorEnable(boolean enable) {
        throw new RuntimeException("Stub!");
    }

    public int getDecoderType() {
        throw new RuntimeException("Stub!");
    }

    public int connectDecoderSRV() {
        throw new RuntimeException("Stub!");
    }

    public int disconnectDecoderSRV() {
        throw new RuntimeException("Stub!");
    }

    public void enableLight(int type, boolean enable) {
        throw new RuntimeException("Stub!");
    }

    public int stopShootImmediately() {
        throw new RuntimeException("Stub!");
    }

    public void stopDecode() {
        throw new RuntimeException("Stub!");
    }

    public int getFlashMode() {
        throw new RuntimeException("Stub!");
    }

    public byte[] getScanImageBytes() {
        throw new RuntimeException("Stub!");
    }

    public void addScannerStatusListener(ScannerManager.IScannerStatusListener listener) {
        throw new RuntimeException("Stub!");
    }

    public void removeScannerStatusListener(ScannerManager.IScannerStatusListener listener) {
        throw new RuntimeException("Stub!");
    }

    public boolean isScanConnect() {
        throw new RuntimeException("Stub!");
    }

    public void setFlashLightOn(boolean isFlashLightOn) {
        throw new RuntimeException("Stub!");
    }

    public interface IScannerStatusListener {
        void onScannerStatusChanage(int status);

        void onScannerResultChanage(byte[] result);
    }
}
