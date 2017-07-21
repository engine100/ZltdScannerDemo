
package com.zltd.scanner.impl.mindeo;

import com.zltd.scanner.impl.ScanEngine;
import com.zltd.scanner.impl.moto.MotoScanEngine;
import com.zltd.scanner.n1000.ScannerManager;

public class MindeoScanEngine extends ScanEngine {
    public static final byte[] KEY_HOLD_SCAN_PERMANENT = {
            0x07, (byte) 0xc6, 0x04, 0x08, (byte) 0xff, (byte) 0x8a, 0x03, (byte) 0xfd, (byte) 0x9b
    };

    // public static final byte[] RESET_FACTORY = {};
    // public static final byte[] INIT_PARAM = {};

    public MindeoScanEngine(ScannerManager context) {
        super(context);
        mScanEngineType = ScannerManager.SCAN_ENGINE_MINDEO;
        // TODO Auto-generated constructor stub
    }

    public int initializeEngine(String revision, int scanMode) {
        super.initializeEngine(revision, scanMode);
        setScanEngineType(ScannerManager.SCAN_ENGINE_MINDEO);
        if (scanMode == ScannerManager.SCAN_KEY_HOLD_MODE) {
            mContext.sendCommand(KEY_HOLD_SCAN_PERMANENT, true, ScanEngine.WAKE_UP_TIMEOUT);
        } else {
            mContext.sendCommand(MotoScanEngine.LASER_SHORT_TIMEOUT, true,
                    ScanEngine.WAKE_UP_TIMEOUT);
            mContext.sendCommand(MotoScanEngine.PULSE_SCAN_PERMANENT, false,
                    ScanEngine.RETRY_TIMEOUT);
        }
        return ScannerManager.RETURN_SUCCESS;
    }

    public int setScanMode(int scanMode) {
        super.setScanMode(scanMode);

        if (scanMode == ScannerManager.SCAN_KEY_HOLD_MODE) {
            mContext.sendCommand(KEY_HOLD_SCAN_PERMANENT, true, ScanEngine.WAKE_UP_TIMEOUT);
        }
        if (mPrevScanMode == ScannerManager.SCAN_KEY_HOLD_MODE) {
            mContext.sendCommand(MotoScanEngine.PULSE_SCAN_PERMANENT, true,
                    ScanEngine.WAKE_UP_TIMEOUT);
            mContext.sendCommand(MotoScanEngine.LASER_SHORT_TIMEOUT, false,
                    ScanEngine.RETRY_TIMEOUT);
        }
        return ScannerManager.RETURN_SUCCESS;
    }

    public int resetFactory() {
        return 0;
    }

    public int startContinuousScan() {
        mContext.sendCommand(MotoScanEngine.CONTINUOUS_SCAN_TEMP, true,
                ScanEngine.WAKE_UP_TIMEOUT);
        return super.startContinuousScan();
    }

    public int stopContinuousScan() {
        mContext.sendCommand(MotoScanEngine.PULSE_SCAN_TEMP, true,
                ScanEngine.WAKE_UP_TIMEOUT);
        return super.stopContinuousScan();
    }

}
