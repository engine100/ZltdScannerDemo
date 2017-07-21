/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.sdk.scanner.demo.util;

import android.content.Context;
import android.os.Vibrator;

import com.zltd.sdk.scanner.demo.App;

/**
 * a util to use vibrator
 *
 * @author Engine100
 */
public class VibratorUtils {
    private static
    Vibrator sVibrator = (Vibrator) App.getInstance().getSystemService(Context.VIBRATOR_SERVICE);

    public static void vibrate() {
        sVibrator.vibrate(150L);
    }

}
