/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.sdk.scanner.compat;

import android.content.Context;

import com.zltd.decoder.DecoderManager;
import com.zltd.industry.ScannerManager;
import com.zltd.sdk.scanner.compat.controller.ZLTDBroadcastController;
import com.zltd.sdk.scanner.compat.controller.ZLTDDecoderManagerController;
import com.zltd.sdk.scanner.compat.controller.ZLTDScannerManagerController;

import java.util.Locale;

/**
 * 适配工厂
 *
 * @author Engine100
 */
public class ControllerFactory {
    static String model;
    static String brand;
    static String product;

    static {
        model = android.os.Build.MODEL.toLowerCase(Locale.CHINA);
        brand = android.os.Build.BRAND.toLowerCase(Locale.CHINA);
        product = android.os.Build.PRODUCT.toLowerCase(Locale.CHINA);
        System.err.println("android.os.Build.MODEL:" + model);
        System.err.println("android.os.Build.BRAND:" + brand);
        System.err.println("android.os.Build.PRODUCT:" + product);
    }

    public static IScanController createController(Context mContext) {

        IScanController mController = null;

        // 先适配N5000,N7000,N7000R
        if (product.contains("n7000") || product.contains("n5000")) {
            mController = new ZLTDDecoderManagerController(mContext);
            return mController;
        }

        // 再适配N2S,N2000,N5
        //N5一维，二维,防止和N5000冲突，不可以包括N5000
        if (product.contains("n5") && !product.contains("n5000")) {
            mController = new ZLTDScannerManagerController(mContext);
            return mController;
        }

        //N2S,N2S000
        if ("simphone".equals(model) && "alps".equals(brand) && product.contains("n2s")) {
            mController = new ZLTDScannerManagerController(mContext);
            return mController;
        }

        // 广播
        //N2机器，不是N2S，也不是N2S000
        if ("simphone n2".equals(model) && "qcom".equals(brand) && "tf_w500".equals(product)) {
            mController = new ZLTDBroadcastController(mContext);
            return mController;
        }

        //未知型号的情况下
        //强制尝试获取,判断不为null的前提是jar包里返回null，因为这些类已经在机器里了，如果有就不为null
        if (existClass("com.zltd.industry.ScannerManager")) {
            ScannerManager scannerManager = ScannerManager.getInstance();
            if (scannerManager != null) {
                mController = new ZLTDScannerManagerController(mContext);
                return mController;
            }
        }

        if (existClass("com.zltd.decoder.DecoderManager")) {
            DecoderManager decoderManager = DecoderManager.getInstance();
            if (decoderManager != null) {
                mController = new ZLTDDecoderManagerController(mContext);
                return mController;
            }
        }

        return mController;
    }

    private static boolean existClass(String className) {
        try {
            Class<?> ScannerManager = Class.forName(className);
            return ScannerManager != null;
        } catch (Exception e) {
            //ignore
        }
        return false;
    }
}