/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.sdk.scanner.compat;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 扫描模式
 *
 * @author Engine100
 */
public final class ScanMode {
    /**
     * 单扫
     */
    public static final int SCAN_SINGLE_MODE = 100;
    /**
     * 连扫
     */
    public static final int SCAN_CONTINUOUS_MODE = 200;
    /**
     * 按键保持
     */
    public static final int SCAN_KEY_HOLD_MODE = 300;

    // 扫描模式约束接口
    @IntDef({ScanMode.SCAN_SINGLE_MODE, ScanMode.SCAN_CONTINUOUS_MODE,
            ScanMode.SCAN_KEY_HOLD_MODE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Model {
    }
}
