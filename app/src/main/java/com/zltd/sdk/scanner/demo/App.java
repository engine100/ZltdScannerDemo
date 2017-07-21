/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.sdk.scanner.demo;

import android.app.Application;

import com.zltd.sdk.scanner.compat.ScanAdapter;

/**
 * App for scanner demo
 *
 * @author Engine100
 */
public class App extends Application {

    private static App sInstance;

    public static App getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //when open app, you should close scanner first, sometimes,the scanner may used by other apps.
        ScanAdapter.getInstance().closeScanner();
    }
}
