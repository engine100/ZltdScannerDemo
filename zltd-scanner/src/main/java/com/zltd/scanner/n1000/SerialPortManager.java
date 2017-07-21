
package com.zltd.scanner.n1000;

import android.util.Log;

public class SerialPortManager {
    private static final String TAG = "scanner";

    static {
        System.loadLibrary("scan_engine_control_jni");
    }

    private int mFd;
    private static SerialPortManager mInstance;

    private SerialPortManager() {

    }

    public static SerialPortManager getInstance() {
        if (mInstance == null) {
            mInstance = new SerialPortManager();
        }
        return mInstance;
    }

    public int openSerialPort() {
        if (mFd == 0) {
            mFd = open();
            Log.i(TAG, "open serial: " + mFd);
        }
        return mFd;
    }

    public int closeSerialPort() {
        if (mFd > 0) {
            int result = close(mFd);
            Log.i(TAG, "close serial: " + result);
            mFd = 0;
        }
        return mFd;
    }

    public int sendCommand(byte[] command) {
        // StringBuffer comm = new StringBuffer();
        // for (int i = 0; i < command.length; i++) {
        // comm.append(String.format("%#x ", command[i]));
        // }
        // Log.i(TAG, "send data is: " + comm);

        if (mFd > 0) {
            int result = send(mFd, command);
            Log.i(TAG, "send command result: " + result);
            return result;
        }
        return -1;
    }

    private native int open();

    private native int close(int fd);

    private native int send(int fd, byte[] command);

    public native String getResult();

    public native void flush();

}
