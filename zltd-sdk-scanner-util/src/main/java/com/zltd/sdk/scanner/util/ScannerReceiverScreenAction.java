/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.sdk.scanner.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zltd.sdk.scanner.compat.ScanAdapter;

/**
 * 熄屏亮屏广播
 *
 * @author Engine100
 */
class ScannerReceiverScreenAction extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        //熄屏
        if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            ScanAdapter scanAdapter = ScanAdapter.getInstance();
            scanAdapter.closeScanner();
        }

        //点亮
        else if (Intent.ACTION_SCREEN_ON.equals(action)) {
            //empty
            //在activity里onResume里面开启
        }

    }
}
