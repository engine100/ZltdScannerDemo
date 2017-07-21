
package com.zltd.scanner.impl.newland;

import com.zltd.scanner.impl.ScanEngine;
import com.zltd.scanner.impl.ScanEngineFactory;
import com.zltd.scanner.n1000.ScannerManager;

public class NewLandScanEngineFactory extends ScanEngineFactory {
    public ScanEngine createScanEngine(ScannerManager context) {
        return new NewLandScanEngine(context);
    }
}
