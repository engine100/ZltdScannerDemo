/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.sdk.scanner.demo.scan;

import com.zltd.sdk.scanner.app.ZLTDScanCompatActivity;
import com.zltd.sdk.scanner.demo.util.VibratorUtils;

/**
 * the base activity for scan
 * 也可以继承ZLTDScanActivity
 * 或者自定义扫描基类，参照ZLTDScanActivity方式
 * 直接使用core库，可以自定义扫描头的打开方式
 *
 * @author Engine100
 */
public class BaseScanActivity extends ZLTDScanCompatActivity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 一般的跳转界面不要关闭，onDestroy方法执行的时间不确定，
        // 在最后程序退出的时候再关闭
        mScanAdapter.closeScanner();
    }

    @Override
    protected boolean beforeScanSuccess(String code) {
        VibratorUtils.vibrate();
        return true;
    }

    @Override
    protected boolean beforeScanError(String msg) {
        return true;
    }

    @Override
    protected void onScannerSuccess(String code) {

    }

    @Override
    protected void onScannerError(String msg) {

    }
}
