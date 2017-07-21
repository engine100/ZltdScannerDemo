package com.zltd.scanner.impl.honeywell;


import com.zltd.scanner.impl.ScanEngine;
import com.zltd.scanner.impl.ScanEngineFactory;
import com.zltd.scanner.n1000.ScannerManager;

public class HoneywellScanEngineFactory extends ScanEngineFactory {
    public ScanEngine createScanEngine(ScannerManager context) {
        return new HoneywellScanEngine(context);
    }
}
