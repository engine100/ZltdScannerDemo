
package com.zltd.scanner.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zltd.scanner.impl.honeywell.HoneywellScanEngine;
import com.zltd.scanner.impl.moto.MotoScanEngine;
import com.zltd.scanner.n1000.ScanEngineTrigger;
import com.zltd.scanner.n1000.ScannerManager;
import com.zltd.scanner.n1000.SerialPortManager;

import android.util.Log;

public class ScanEngine {
    public static final String TAG = "scanner";
    public static final int WAKE_UP_TIMEOUT = 25;  // ms
    public static final int POWER_UP_TIMEOUT = 200;  // ms
    public static final int RETRY_TIMEOUT = 300;  // ms
    public static final byte[] WAKE_UP = {
            0x00, 0x00, 0x00
    };
    private String regEx = "[^ \40-\176]";
    private Pattern p = Pattern.compile(regEx);

    protected String mScanEngineInfo;
    protected int mLaserTimeout;
    protected int mPrevScanMode;
    protected int mScanMode;

    public int mScanEngineType = ScannerManager.SCAN_ENGINE_UNKNOWN;
    private boolean mContinuousScanning;
    public Thread mMonitorSingleThread;
    private Thread mMonitorContinousThread;
    public ScannerManager mContext;
    private boolean isContinousScanning;

    public ScanEngine(ScannerManager context) {
        mContext = context;
    }

    public int initializeEngine(String revision, int scanMode) {
        mScanEngineType = ScannerManager.SCAN_ENGINE_UNKNOWN;
        mScanEngineInfo = revision;
        mScanMode = scanMode;
        mPrevScanMode = mScanMode;
        return ScannerManager.RETURN_SUCCESS;
    }

    public int getScanEngineType() {
        return mScanEngineType;
    }

    public int setScanEngineType(int type) {
        mScanEngineType = type;
        return ScannerManager.RETURN_SUCCESS;
    }

    public String getScanEngineInfo() {
        return mScanEngineInfo;
    }

    public int getLaserTimeout() {
        return ScannerManager.RETURN_NOT_IMPL;
    }

    public int setLaserTimeout(int timeout) {
        return ScannerManager.RETURN_NOT_IMPL;
    }

    public int getScanMode() {
        return mScanMode;
    }

    public int setScanMode(int scanMode) {
        if (mScanMode == scanMode) {
            return mScanMode;
        }
        switch (mScanMode) {
            case ScannerManager.SCAN_CONTINUOUS_MODE:
                if (mContinuousScanning) {
                    if (scanMode != ScannerManager.SCAN_KEY_HOLD_MODE) {
                        stopContinuousScan();
                    }
                    mContinuousScanning = false;
                }
                break;
            case ScannerManager.SCAN_SINGLE_MODE:
            case ScannerManager.SCAN_KEY_HOLD_MODE:
            default:
                break;
        }

        mPrevScanMode = mScanMode;
        mScanMode = scanMode;
        return ScannerManager.RETURN_SUCCESS;
    }

    public int resetFactory() {
        return ScannerManager.RETURN_NOT_IMPL;
    }

    public int singleScan() {

        // try {
        // boolean isWorking = false;
        // SerialPortManager.getInstance().sendCommand(ScanEngine.WAKE_UP);
        // Thread.sleep(25);
        // SerialPortManager.getInstance().sendCommand(MotoScanEngine.PULSE_SCAN_TEMP);
        // for (int i = 0; i < 10; i++) {
        // Thread.sleep(10);
        // String data = SerialPortManager.getInstance().sendResult();
        // if (data.startsWith("\04\208") ||
        // data.startsWith("\05\209")) {
        // Log.i(TAG, "scanner is working");
        // isWorking = true;
        // break;
        // }
        // }
        // if (!isWorking) {
        // Log.i(TAG, "scanner is not working, reopen it.");
        // mContext.scannerEnable(false);
        // mContext.scannerEnable(true);
        // }
        // } catch (InterruptedException e1) {
        // Log.d(TAG,
        // "PULSE_SCAN_TEMP It's lang time for monitor, interrupt it.");
        // }

        if (mMonitorSingleThread != null &&
                !mMonitorSingleThread.isInterrupted()) {
            mMonitorSingleThread.interrupt();
        }
        mMonitorSingleThread = new Thread() {
            public void run() {
                Log.i(TAG, "single Scan");
                ScanEngineTrigger.getInstance().openTrigger();
                // SerialPortManager.getInstance().openSerialPort();
                SerialPortManager.getInstance().flush();
                String data;
                boolean isWorking = false;
                int count = 0;
                try {
//                    triggerLevel(ScannerManager.HIGH_LEVEL);
                    // SerialPortManager.getInstance().sendCommand(ScanEngine.WAKE_UP);
                    Thread.sleep(25);
                    triggerLevel(ScannerManager.LOW_LEVEL);

                    if (mScanEngineType == ScannerManager.SCAN_ENGINE_HONEYWELL) {
                        StringBuffer buffer = new StringBuffer();
                        buffer.append(new String(HoneywellScanEngine.PREFIX));
                        buffer.append("BEPBEP0!");
                        SerialPortManager.getInstance().sendCommand(buffer.toString().getBytes());
                    } else if (mScanEngineType == ScannerManager.SCAN_ENGINE_MOTO) {
                        SerialPortManager.getInstance()
                                .sendCommand(MotoScanEngine.PULSE_SCAN_TEMP);
                    }

                    for (int i = 0; i < 540; i++) {
                        Thread.sleep(5);
                        data = SerialPortManager.getInstance().getResult();
                        if (data.length() > 0) {
                            Log.i(TAG, "get result is: " + data);
                            SerialPortManager.getInstance().flush();
                        }

                        if (count < 40 && !isWorking) {
                            if (data.contains("Ѐ") || data.contains("BEPBEP0")) {
                                Log.i(TAG, "scanner is working");
                                isWorking = true;
                                continue;
                            }
                        } else if (count >= 40 && !isWorking) {
                            Log.i(TAG, "scanner is not working, reopen it.");
                            mContext.scannerEnable(false);
                            mContext.scannerEnable(true);
                            break;
                        }

                        String str = StringFilter(data);
                        if (str != null && !str.equals("")) {
                            Log.i(TAG, "scan result: " + str);
                            mContext.sendResult(str);
                            break;
                        }
                        count++;
                    }
                } catch (InterruptedException e) {
                    Log.d(TAG, "It's lang time for monitor, interrupt it.");
                } finally {
                    triggerLevel(ScannerManager.HIGH_LEVEL);
                }
            }
        };
        mMonitorSingleThread.start();
        return ScannerManager.RETURN_SUCCESS;
    }

    public int startContinuousScan() {
        if (mMonitorContinousThread != null && !mMonitorContinousThread.isInterrupted()) {
            mMonitorContinousThread.interrupt();
        }
        mMonitorContinousThread = new Thread() {
            public void run() {
                Log.i(TAG, "start Continuous Scan");
                // ScanEngineTrigger.getInstance().openTrigger();
                // SerialPortManager.getInstance().openSerialPort();
                SerialPortManager.getInstance().flush();
                isContinousScanning = true;
                String data;
                try {
                    while (isContinousScanning) {
                        Thread.sleep(10);
                        data = StringFilter(SerialPortManager.getInstance().getResult());
                        if (data != null && data.length() > 0) {
                            SerialPortManager.getInstance().flush();
                        }
                        if (data != null && !data.equals("")) {
                            Log.i(TAG, "scan result: " + data);
                            mContext.sendResult(data);
                        }
                    }
                } catch (InterruptedException e) {
                    Log.d(TAG, "It's lang time for monitor, interrupt it.");
                }
            }
        };
        mMonitorContinousThread.start();
        return ScannerManager.RETURN_NOT_IMPL;
    }

    public int stopContinuousScan() {
        Log.i(TAG, "stop continous Scan");
        isContinousScanning = false;
        // if (mMonitorContinousThread != null &&
        // !mMonitorContinousThread.isInterrupted()) {
        // mMonitorContinousThread.interrupt();
        // }
        SerialPortManager.getInstance().flush();
        return ScannerManager.RETURN_NOT_IMPL;
    }

    public int startKeyHoldScan() {
        return triggerLevel(ScannerManager.LOW_LEVEL);
    }

    public int stopKeyHoldScan() {
        return triggerLevel(ScannerManager.HIGH_LEVEL);
    }

    public int triggerLevel(int level) {
        return ScanEngineTrigger.getInstance().triggerLevel(level);
    }

    public int sendCommandToEngine(byte[] command) {
        int result = SerialPortManager.getInstance().sendCommand(command);
        if (result > 0) {
            return ScannerManager.RETURN_SUCCESS;
        } else {
            return ScannerManager.RETURN_SERIAL_PORT_ERR;
        }
    }

    public boolean isContinousScanning() {
        return isContinousScanning;
    }

    public String StringFilter(String str) {
        if (str != null) {
            if (str.indexOf("PAPPM3") >= 0 ||
                    str.indexOf("AOSMEN") >= 0 ||
                    str.indexOf("AOSMPT") >= 0 ||
                    str.indexOf("AOSDFT") >= 0 ||
                    str.indexOf("BEPBEP0") >= 0 ||
                    str.indexOf("Boot Revision") >= 0) {
                return null;
            }

            Matcher m = p.matcher(str);
            return m.replaceAll("").trim();
        }
        return null;
    }
}
