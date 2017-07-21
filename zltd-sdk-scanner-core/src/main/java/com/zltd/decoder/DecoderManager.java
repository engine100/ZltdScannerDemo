/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.decoder;

import android.os.Bundle;
import android.view.KeyEvent;

public class DecoderManager {
    public static final String DECODER_TIMEOUT = "Decode is interruptted or timeout ...";

    private DecoderManager() {

    }

    public static DecoderManager getInstance() {
        return null;
    }

    public int singleShoot() {
        throw new RuntimeException("Stub!");
    }

    public int stopShootImmediately() {
        throw new RuntimeException("Stub!");
    }

    public int continuousShoot() {
        throw new RuntimeException("Stub!");
    }

    public int stopContinuousShoot() {
        throw new RuntimeException("Stub!");
    }

    public boolean isContinuousShootState() {
        throw new RuntimeException("Stub!");
    }

    public int connectDecoderSRV() {
        throw new RuntimeException("Stub!");
    }

    public int disconnectDecoderSRV() {
        throw new RuntimeException("Stub!");
    }

    public void stopDecode() {
        throw new RuntimeException("Stub!");
    }

    public int getFlashMode() {
        throw new RuntimeException("Stub!");
    }

    public void setFlashMode(int mode) {
        throw new RuntimeException("Stub!");
    }

    public void enableLight(int type, boolean enable) {
        throw new RuntimeException("Stub!");
    }

    public int getDataTransferType() {
        throw new RuntimeException("Stub!");
    }

    public void setDataTransferType(int transferType) {
        throw new RuntimeException("Stub!");
    }

    public void dispatchScanKeyEvent(KeyEvent keyEvent) {
        throw new RuntimeException("Stub!");
    }

    public boolean getScannerEnable() {
        throw new RuntimeException("Stub!");
    }

    public void setScannerEnable(boolean enable) {
        throw new RuntimeException("Stub!");
    }

    public int getScanMode() {
        throw new RuntimeException("Stub!");
    }

    public void setScanMode(int mode) {
        throw new RuntimeException("Stub!");
    }

    public int getScanSceneMode() {
        throw new RuntimeException("Stub!");
    }

    public void setScanSceneMode(int mode) {
        throw new RuntimeException("Stub!");
    }

    public int getScanEfficientMode() {
        throw new RuntimeException("Stub!");
    }

    public void setScanEfficientMode(int mode) {
        throw new RuntimeException("Stub!");
    }

    public byte[] getScanImageBytes() {
        throw new RuntimeException("Stub!");
    }

    public void addDecoderStatusListener(DecoderManager.IDecoderStatusListener listener) {
        throw new RuntimeException("Stub!");
    }

    public void removeDecoderStatusListener(DecoderManager.IDecoderStatusListener listener) {
        throw new RuntimeException("Stub!");
    }

    public interface IDecoderStatusListener {
        void onDecoderStatusChanage(int status);

        void onDecoderResultChanage(String result, String time);

        void onDecoderResultChanage(String result, Bundle paramBundle);
    }
}
