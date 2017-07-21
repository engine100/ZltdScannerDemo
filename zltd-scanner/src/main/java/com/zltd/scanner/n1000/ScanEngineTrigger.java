
package com.zltd.scanner.n1000;

import android.util.Log;

public class ScanEngineTrigger {
    private static final String TAG = "scanner";
    private int mFd;

    private static ScanEngineTrigger mInstance;

    private ScanEngineTrigger() {

    }

    public static ScanEngineTrigger getInstance() {
        if (mInstance == null) {
            mInstance = new ScanEngineTrigger();
        }
        return mInstance;
    }

    public int openTrigger() {
        if (mFd == 0) {
            mFd = open();
            Log.i(TAG, "open trigger: " + mFd);
        }
        return mFd;
    }

    public int closeTrigger() {
        if (mFd > 0) {
            int result = close(mFd);
            Log.i(TAG, "close trigger: " + result);
            mFd = 0;
        }
        return mFd;
    }

    public int triggerLevel(int level) {
        if (mFd > 0) {
            int result = trigger(mFd, level);
            Log.i(TAG, "exce trigger level: " + level + ", result is: " + result);
            return result;
        }
        return -1;
    }

    private native int open();

    private native int close(int fd);

    private native int trigger(int fd, int level);

}
