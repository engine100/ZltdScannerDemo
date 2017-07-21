
package com.zltd.scanner.impl.mindeo;

import com.zltd.scanner.impl.ScanEngine;
import com.zltd.scanner.impl.ScanEngineFactory;
import com.zltd.scanner.n1000.ScannerManager;

public class MindeoScanEngineFactory extends ScanEngineFactory {
    public ScanEngine createScanEngine(ScannerManager context) {
        return new MindeoScanEngine(context);
    }
}
