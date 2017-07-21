/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.sdk.scanner.compat;

/**
 * 扫描结果接口
 *
 * @author Engine100
 */
public interface IScanResult {

    /**
     * 成功扫描后回调
     */
    void onScanSuccess(String code);

    /**
     * 扫描失败后回调
     */
    void onScanError(String msg);

}
