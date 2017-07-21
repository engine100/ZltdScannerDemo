
package com.zltd.scanner.impl.moto;

import com.zltd.scanner.impl.ScanEngine;
import com.zltd.scanner.n1000.ScannerManager;

public class MotoScanEngine extends ScanEngine {
    public static final byte[] REQUEST_REVISION = {
            0x04, (byte) 0xa3, 0x04, 0x00, (byte) 0xff, 0x55
    };
    public static final byte[] CONTINUOUS_SCAN_TEMP = {
            0x07, (byte) 0xc6, 0x04, 0x00, (byte) 0xff, (byte) 0x8a, 0x04, (byte) 0xfd, (byte) 0xa2
            // 0x07, (byte) 0xc6, 0x04, 0x08, (byte) 0xff, (byte) 0x8a, 0x04,
            // (byte) 0xfd, (byte) 0x9a
    };
    public static final byte[] LEVEL_SCAN_PERMANENT = {
            0x07, (byte) 0xc6, 0x04, 0x08, (byte) 0xff, (byte) 0x8a, 0x00, (byte) 0xfd, (byte) 0x9e
    };
    public static final byte[] PULSE_SCAN_PERMANENT = {
            0x07, (byte) 0xc6, 0x04, 0x08, (byte) 0xff, (byte) 0x8a, 0x02, (byte) 0xfd, (byte) 0x9c
    };
    public static final byte[] PULSE_SCAN_TEMP = {
            0x07, (byte) 0xc6, 0x04, 0x00, (byte) 0xff, (byte) 0x8a, 0x02, (byte) 0xfd, (byte) 0xa4
    };
    public static final byte[] LASER_SHORT_TIMEOUT = {  // laser timeout 1700ms
            0x07, (byte) 0xc6, 0x04, 0x08, (byte) 0xff, (byte) 0x88, 0x11, (byte) 0xfd, (byte) 0x8f
    };
    public static final byte[] LASER_LONG_TIMEOUT = {  // laser timeout 25500ms
            0x07, (byte) 0xc6, 0x04, 0x08, (byte) 0xff, (byte) 0x88, (byte) 0xfd, (byte) 0xfc,
            (byte) 0xa3
    };

    public static final byte[] SECURITY_LEVEL = { // set security level to 2
            0x07, (byte) 0xC6, 0x04, 0x08, (byte) 0xff, 0x4e, 0x02, (byte) 0xfd, (byte) 0xd8
    };

    public static final byte[] SAME_SYMBOL_TIMEOUT = {
            //0x07, (byte) 0xc6, 0x04, 0x08, (byte) 0xff, (byte) 0x89, 0x00, (byte) 0xfd, (byte) 0x9f
            0x07, (byte) 0xc6, 0x04, 0x08, (byte) 0xff, (byte) 0x89, 0x10, (byte) 0xfd, (byte) 0x8f
    };

    public MotoScanEngine(ScannerManager context) {
        super(context);
        mScanEngineType = ScannerManager.SCAN_ENGINE_MOTO;
        // TODO Auto-generated constructor stub
    }

    public int initializeEngine(String revision, int scanMode) {
        super.initializeEngine(revision, scanMode);
        setScanEngineType(ScannerManager.SCAN_ENGINE_MOTO);
        if (scanMode == ScannerManager.SCAN_KEY_HOLD_MODE) {
            mContext.sendCommand(LEVEL_SCAN_PERMANENT, true, ScanEngine.WAKE_UP_TIMEOUT);
            mContext.sendCommand(LASER_LONG_TIMEOUT, false, ScanEngine.RETRY_TIMEOUT);
        } else {
            mContext.sendCommand(LASER_SHORT_TIMEOUT, true, ScanEngine.WAKE_UP_TIMEOUT);
            mContext.sendCommand(PULSE_SCAN_PERMANENT, false, ScanEngine.RETRY_TIMEOUT);
        }
        mContext.sendCommand(SECURITY_LEVEL, false, ScanEngine.RETRY_TIMEOUT * 2);

        return ScannerManager.RETURN_SUCCESS;
    }

    public int setScanMode(int scanMode) {
        super.setScanMode(scanMode);

        if (scanMode == ScannerManager.SCAN_KEY_HOLD_MODE) {
            mContext.sendCommand(LEVEL_SCAN_PERMANENT, true, ScanEngine.WAKE_UP_TIMEOUT);
            mContext.sendCommand(LASER_LONG_TIMEOUT, false, ScanEngine.RETRY_TIMEOUT);
        }
        if (mPrevScanMode == ScannerManager.SCAN_KEY_HOLD_MODE) {
            mContext.sendCommand(PULSE_SCAN_PERMANENT, true, ScanEngine.WAKE_UP_TIMEOUT);
            mContext.sendCommand(LASER_SHORT_TIMEOUT, false, ScanEngine.RETRY_TIMEOUT);
        }
        return ScannerManager.RETURN_SUCCESS;
    }

    public int resetFactory() {
        return 0;
    }

    public int startContinuousScan() {
        super.startContinuousScan();
        // triggerLevel(ScannerManager.LOW_LEVEL);
        //mContext.sendCommand(SAME_SYMBOL_TIMEOUT, true, ScanEngine.WAKE_UP_TIMEOUT);
        mContext.sendCommand(CONTINUOUS_SCAN_TEMP, true, ScanEngine.WAKE_UP_TIMEOUT);
        return ScannerManager.RETURN_SUCCESS;
    }

    public int stopContinuousScan() {
        super.stopContinuousScan();
        //mContext.sendCommand(PULSE_SCAN_TEMP, true, ScanEngine.WAKE_UP_TIMEOUT);
        sendCommandToEngine(PULSE_SCAN_TEMP);
        triggerLevel(ScannerManager.LOW_LEVEL);
        triggerLevel(ScannerManager.HIGH_LEVEL);
        return ScannerManager.RETURN_SUCCESS;
    }

}
