//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zltd.industry;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.KeyEvent;

import com.zltd.decoder.IDecoderService;
import com.zltd.decoder.IDecoderService.Stub;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

//import android.os.ServiceManager;

public class ScannerManager {
    public static final String SCANNER_SERVICE = "IScannerService";
    public static final String SCANNER_ACTION = "android.intent.action.SCANNER_SERVICE";
    public static final String GET_SCANDATA = "android.intent.action.RECEIVE_SCANDATA";
    public static final String SCAN_DATA = "android.intent.extra.SCAN_DATA";
    public static final int RETURN_SUCCESS = 257;
    public static final int RETURN_ENGINE_BUSY = 258;
    public static final int RETURN_ENGINE_TYPE_ERR = 259;
    public static final int RETURN_SERIAL_PORT_ERR = 260;
    public static final int RETURN_TRIGGER_DEVICE_ERR = 261;
    public static final int RETURN_NOT_IMPL = 262;
    public static final int LOW_LEVEL = 0;
    public static final int HIGH_LEVEL = 1;
    public static final int SCAN_ENGINE_UNKNOWN = 1000;
    public static final int SCAN_ENGINE_MOTO = 1001;
    public static final int SCAN_ENGINE_HONEYWELL = 1002;
    public static final int SCAN_ENGINE_MINDEO = 1003;
    public static final int SCAN_ENGINE_NEWLAND = 1004;
    public static final int SCAN_SINGLE_MODE = 1;
    public static final int SCAN_CONTINUOUS_MODE = 2;
    public static final int SCAN_KEY_HOLD_MODE = 3;
    public static final int TRANSFER_BY_EDITTEXT = 1;
    public static final int TRANSFER_BY_KEY = 2;
    public static final String DECODER_TIMEOUT = "Decode is interruptted or timeout ...";
    public static final int TRANSFER_BY_API = 3;
    public static final String REQUEST = "request";
    public static final String RESULT = "result";
    private static ScannerManager mInstance = new ScannerManager();
    private HashMap<ScannerManager.IScannerStatusListener, ScannerManager.ScannerCallbackTransport> mScannerStatusListeners = new HashMap();
    private HashMap<ScannerManager.IScannerStatusListenerEx, ScannerManager.ScannerCallbackTransport> mScannerStatusListenersEx = new HashMap();

    private ScannerManager() {
    }

    public static ScannerManager getInstance() {
        return mInstance;
    }

    public int keyShoot(boolean on) {
        try {
            return this.getIDecoderService().keyShoot(on);
        } catch (RemoteException var3) {
            return 5;
        }
    }

    public int quitWithReason(int on) {
        try {
            return this.getIDecoderService().quitWithReason(on);
        } catch (RemoteException var3) {
            return 5;
        }
    }

    public boolean isKeyShootEnabled() {
        try {
            return this.getIDecoderService().isKeyShootEnabled();
        } catch (RemoteException var2) {
            return false;
        }
    }

    public boolean isInKeyShoot() {
        try {
            return this.getIDecoderService().isInKeyShoot();
        } catch (RemoteException var2) {
            return false;
        }
    }

    public int getScanEngineType() {
        int engingType = 260;

        try {
            Bundle out = new Bundle();
            this.getIDecoderService().ioControl(1, (Bundle) null, out);
            engingType = out.getInt("result");
        } catch (RemoteException var3) {
            ;
        }

        return engingType;
    }

    public boolean getContinuousScanning() {
        try {
            return this.getIDecoderService().isContinuousShootState();
        } catch (RemoteException var2) {
            return false;
        }
    }

    public int setScanEngineType(int type) {
        int result = 260;

        try {
            Bundle in = new Bundle();
            in.putInt("request", type);
            Bundle out = new Bundle();
            this.getIDecoderService().ioControl(2, in, out);
            result = out.getInt("result");
        } catch (RemoteException var5) {
            ;
        }

        return result;
    }

    public String getScanEngineInfo() {
        String result = "";

        try {
            Bundle out = new Bundle();
            this.getIDecoderService().ioControl(3, (Bundle) null, out);
            result = out.getString("result");
        } catch (RemoteException var3) {
            ;
        }

        return result;
    }

    public int getLaserTimeout() {
        int result = 260;

        try {
            Bundle out = new Bundle();
            this.getIDecoderService().ioControl(4, (Bundle) null, out);
            result = out.getInt("result");
        } catch (RemoteException var3) {
            ;
        }

        return result;
    }

    public int resetFactory() {
        int result = 260;

        try {
            Bundle out = new Bundle();
            this.getIDecoderService().ioControl(6, (Bundle) null, out);
            result = out.getInt("result");
        } catch (RemoteException var3) {
            ;
        }

        return result;
    }

    public void singleScan() {
        try {
            this.getIDecoderService().singleShoot();
        } catch (RemoteException var2) {
            ;
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    public void cleanUp() {
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void getScannerIsReady() {
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void continuousScan() {
        try {
            this.getIDecoderService().continuousShoot();
        } catch (RemoteException var2) {
            ;
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    public void stopScan() {
        try {
            this.getIDecoderService().stopContinuousShoot();
        } catch (RemoteException var2) {
            ;
        }

    }

    public int startContinuousScan() {
        try {
            return this.getIDecoderService().continuousShoot();
        } catch (RemoteException var2) {
            return 260;
        }
    }

    public int stopContinuousScan() {
        try {
            return this.getIDecoderService().stopContinuousShoot();
        } catch (RemoteException var2) {
            return 260;
        }
    }

    public int startKeyHoldScan() {
        int result = 260;

        try {
            Bundle out = new Bundle();
            this.getIDecoderService().ioControl(7, (Bundle) null, out);
            result = out.getInt("result");
        } catch (RemoteException var3) {
            ;
        }

        return result;
    }

    public int stopKeyHoldScan() {
        int result = 260;

        try {
            Bundle out = new Bundle();
            this.getIDecoderService().ioControl(8, (Bundle) null, out);
            result = out.getInt("result");
        } catch (RemoteException var3) {
            ;
        }

        return result;
    }

    public int triggerLevel(int level) {
        int result = 260;

        try {
            Bundle in = new Bundle();
            in.putInt("request", level);
            Bundle out = new Bundle();
            this.getIDecoderService().ioControl(9, in, out);
            result = out.getInt("result");
        } catch (RemoteException var5) {
            ;
        }

        return result;
    }

    public void sendCommand(byte[] command) {
        try {
            Bundle in = new Bundle();
            in.putByteArray("request", command);
            this.getIDecoderService().ioControl(10, in, (Bundle) null);
        } catch (RemoteException var3) {
            ;
        }

    }

    public int getScanMode() {
        try {
            return this.getIDecoderService().getScanMode();
        } catch (RemoteException var2) {
            return 260;
        }
    }

    public int setScanMode(int scanMode) {
        try {
            this.getIDecoderService().setScanMode(scanMode);
        } catch (RemoteException var3) {
            ;
        }

        return 257;
    }

    public int getDataTransferType() {
        try {
            return this.getIDecoderService().getDataTransferType();
        } catch (RemoteException var2) {
            return 258;
        }
    }

    public int setDataTransferType(int transferType) {
        try {
            this.getIDecoderService().setDataTransferType(transferType);
        } catch (RemoteException var3) {
            ;
        }

        return 257;
    }

    public void dispatchScanKeyEvent(KeyEvent keyEvent) {
        try {
            this.getIDecoderService().dispatchScanKeyEvent(keyEvent);
        } catch (RemoteException var3) {
            ;
        } catch (NullPointerException var4) {
            ;
        }

    }

    public void scannerEnable(boolean enable) {
        try {
            this.getIDecoderService().setScannerEnable(enable);
        } catch (RemoteException var3) {
            ;
        }

    }

    public boolean getScannerEnable() {
        try {
            return this.getIDecoderService().getScannerEnable();
        } catch (RemoteException var2) {
            return false;
        }
    }

    public boolean getScannerSoundEnable() {
        boolean result = false;

        try {
            Bundle out = new Bundle();
            this.getIDecoderService().ioControl(12, (Bundle) null, out);
            result = out.getBoolean("result");
        } catch (RemoteException var3) {
            ;
        }

        return result;
    }

    public void setScannerSoundEnable(boolean enable) {
        try {
            Bundle in = new Bundle();
            in.putBoolean("request", enable);
            this.getIDecoderService().ioControl(11, in, (Bundle) null);
        } catch (RemoteException var3) {
            ;
        }

    }

    public String getScannerExtra() {
        String result = "";

        try {
            Bundle out = new Bundle();
            this.getIDecoderService().ioControl(14, (Bundle) null, out);
            result = out.getString("result");
        } catch (RemoteException var3) {
            ;
        }

        return result;
    }

    public void setScannerExtra(String extra) {
        try {
            Bundle in = new Bundle();
            in.putString("request", extra);
            this.getIDecoderService().ioControl(13, in, (Bundle) null);
        } catch (RemoteException var3) {
            ;
        }

    }

    public boolean getScannerVibratorEnable() {
        boolean result = false;

        try {
            Bundle out = new Bundle();
            this.getIDecoderService().ioControl(16, (Bundle) null, out);
            result = out.getBoolean("result");
        } catch (RemoteException var3) {
            ;
        }

        return result;
    }

    public void setScannerVibratorEnable(boolean enable) {
        try {
            Bundle in = new Bundle();
            in.putBoolean("request", enable);
            this.getIDecoderService().ioControl(15, in, (Bundle) null);
        } catch (RemoteException var3) {
            ;
        }

    }

    public int getDecoderType() {
        try {
            return this.getIDecoderService().getDecoderType();
        } catch (RemoteException var2) {
            return 257;
        }
    }

    public int connectDecoderSRV() {
        try {
            return this.getIDecoderService().connectDecoderSRV();
        } catch (RemoteException var2) {
            return 5;
        }
    }

    public int disconnectDecoderSRV() {
        try {
            return this.getIDecoderService().disconnectDecoderSRV();
        } catch (RemoteException var2) {
            return 5;
        }
    }

    public void enableLight(int type, boolean enable) {
        try {
            this.getIDecoderService().enableLight(type, enable);
        } catch (RemoteException var4) {
            ;
        }

    }

    public int stopShootImmediately() {
        try {
            return this.getIDecoderService().stopShootImmediately();
        } catch (RemoteException var2) {
            return 5;
        }
    }

    public void stopDecode() {
        try {
            this.getIDecoderService().stopDecode();
        } catch (RemoteException var2) {
            ;
        }

    }

    public int getFlashMode() {
        try {
            return this.getIDecoderService().getFlashMode();
        } catch (RemoteException var2) {
            return -1;
        }
    }

    public byte[] getScanImageBytes() {
        byte[] image = null;

        try {
            Bundle out = new Bundle();
            this.getIDecoderService().ioControl(4096, (Bundle) null, out);
            image = out.getByteArray("result");
        } catch (RemoteException var3) {
            ;
        }

        return image;
    }

    public void addScannerStatusListener(ScannerManager.IScannerStatusListener listener) {
        if (this.mScannerStatusListeners.get(listener) == null) {
            ScannerManager.ScannerCallbackTransport transport = new ScannerManager.ScannerCallbackTransport(listener);

            try {
                this.getIDecoderService().registerDecoderCallback(transport);
                this.mScannerStatusListeners.put(listener, transport);
            } catch (Exception var4) {
                ;
            }

        }
    }

    public void removeScannerStatusListener(ScannerManager.IScannerStatusListener listener) {
        ScannerManager.ScannerCallbackTransport transport = (ScannerManager.ScannerCallbackTransport) this.mScannerStatusListeners.remove(listener);
        if (transport != null) {
            try {
                this.getIDecoderService().unregisterDecoderCallback(transport);
            } catch (Exception var4) {
                ;
            }
        }

    }

    public void addScannerStatusListenerEx(ScannerManager.IScannerStatusListenerEx listener) {
        if (this.mScannerStatusListenersEx.get(listener) == null) {
            ScannerManager.ScannerCallbackTransport transport = new ScannerManager.ScannerCallbackTransport(listener);

            try {
                this.getIDecoderService().registerDecoderCallback(transport);
                this.mScannerStatusListenersEx.put(listener, transport);
            } catch (Exception var4) {
                ;
            }

        }
    }

    public void removeScannerStatusListenerEx(ScannerManager.IScannerStatusListenerEx listener) {
        ScannerManager.ScannerCallbackTransport transport = (ScannerManager.ScannerCallbackTransport) this.mScannerStatusListenersEx.remove(listener);
        if (transport != null) {
            try {
                this.getIDecoderService().unregisterDecoderCallback(transport);
            } catch (Exception var4) {
                ;
            }
        }

    }

    private IDecoderService getIDecoderService() {
        try {
            Class<?> ServiceManagerClazz = Class.forName("android.os.ServiceManager");
            Method getService = ServiceManagerClazz.getDeclaredMethod("getService", String.class);
            getService.setAccessible(true);
            IBinder binder = (IBinder) getService.invoke(null, "IDecoderService");
            return Stub.asInterface(binder);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
        //return IDecoderService.Stub.asInterface(ServiceManager.getService("IDecoderService"));
    }

    public boolean isScanConnect() {
        boolean result = false;

        try {
            Bundle out = new Bundle();
            this.getIDecoderService().ioControl(17, (Bundle) null, out);
            result = out.getBoolean("result");
        } catch (RemoteException var3) {
            ;
        }

        return result;
    }

    public void setFlashLightOn(boolean isFlashLightOn) {
        try {
            Bundle e = new Bundle();
            Bundle out = new Bundle();
            e.putBoolean("request", isFlashLightOn);
            this.getIDecoderService().ioControl(18, e, out);
        } catch (RemoteException var4) {
            var4.printStackTrace();
        }

    }

    public interface IScannerStatusListenerEx {
        void onScannerStatusChanage(int var1);

        void onScannerResultChanageEx(String var1, Bundle var2);
    }

    public interface IScannerStatusListener {
        void onScannerStatusChanage(int var1);

        void onScannerResultChanage(byte[] var1);
    }

    private class ScannerCallbackTransport extends com.zltd.decoder.IDecoderStateListener.Stub {
        private ScannerManager.IScannerStatusListener mIScannerStatusListener;
        private ScannerManager.IScannerStatusListenerEx mIScannerStatusListenerEx;

        public ScannerCallbackTransport(ScannerManager.IScannerStatusListener listener) {
            this.mIScannerStatusListener = listener;
        }

        public ScannerCallbackTransport(ScannerManager.IScannerStatusListenerEx listener) {
            this.mIScannerStatusListenerEx = listener;
        }

        public void onSendResult(String result, Bundle output) throws RemoteException {
            if (this.mIScannerStatusListener != null) {
                this.mIScannerStatusListener.onScannerResultChanage(result.getBytes());
            }

            if (this.mIScannerStatusListenerEx != null) {
                this.mIScannerStatusListenerEx.onScannerResultChanageEx(result, output);
            }

        }

        @Override
        public void onSendResult(String result, String time) throws RemoteException {

        }
    }
}
