/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.sdk.scanner.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * 屏幕熄灭的时候停止扫描
 *
 * @author Engine100
 */
class ScannerReceiverUtils {

    private static final ScannerReceiverScreenAction mSwitchReceiver = new ScannerReceiverScreenAction();

    public static void registerScreenActionReceiver(Context context) {
        if (context == null) return;

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        context.getApplicationContext().registerReceiver(mSwitchReceiver, filter);
    }

    public static void unRegisterScreenActionReceiver(Context context) {
        if (context == null) return;

        try {
            context.getApplicationContext().unregisterReceiver(mSwitchReceiver);
        } catch (Exception e) {
            //ignore
        }
    }

}
