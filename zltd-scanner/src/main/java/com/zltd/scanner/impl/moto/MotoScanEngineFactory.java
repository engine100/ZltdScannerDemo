package com.zltd.scanner.impl.moto;

import com.zltd.scanner.impl.ScanEngine;
import com.zltd.scanner.impl.ScanEngineFactory;
import com.zltd.scanner.n1000.ScannerManager;

public class MotoScanEngineFactory extends ScanEngineFactory {
    public ScanEngine createScanEngine(ScannerManager context) {
        return new MotoScanEngine(context);
    }
}
