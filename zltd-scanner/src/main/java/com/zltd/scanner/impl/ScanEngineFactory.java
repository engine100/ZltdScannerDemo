
package com.zltd.scanner.impl;

import com.zltd.scanner.n1000.ScannerManager;


public class ScanEngineFactory {

    public ScanEngine createScanEngine(ScannerManager context) {
        return new ScanEngine(context);
    }

}
