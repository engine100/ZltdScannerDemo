//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zltd.decoder;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.KeyEvent;

import com.zltd.decoder.IDecoderService.Stub;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class DecoderManager {
    protected static final String DECODER_SERVICE = "IDecoderService";
    public static final String DECODER_TIMEOUT = "Decode is interruptted or timeout ...";
    protected static final String GET_SCANDATA = "android.intent.action.RECEIVE_SCANDATA";
    protected static final String SCAN_DATA = "android.intent.extra.SCAN_DATA";
    private static DecoderManager mInstance = new DecoderManager();
    private HashMap<DecoderManager.IDecoderStatusListener, DecoderManager.DecoderCallbackTransport> mDecoderStatusListeners = new HashMap();

    public static DecoderManager getInstance() {
        return mInstance;
    }

    private DecoderManager() {
    }

    public int singleShoot() {
        try {
            return this.getIDecoderService().singleShoot();
        } catch (RemoteException var2) {
            return 5;
        }
    }

    public int stopShootImmediately() {
        try {
            return this.getIDecoderService().stopShootImmediately();
        } catch (RemoteException var2) {
            return 5;
        }
    }

    public int continuousShoot() {
        try {
            return this.getIDecoderService().continuousShoot();
        } catch (RemoteException var2) {
            return 5;
        }
    }

    public int stopContinuousShoot() {
        try {
            return this.getIDecoderService().stopContinuousShoot();
        } catch (RemoteException var2) {
            return 5;
        }
    }

    public boolean isContinuousShootState() {
        try {
            return this.getIDecoderService().isContinuousShootState();
        } catch (RemoteException var2) {
            return false;
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

    public void setFlashMode(int mode) {
        try {
            this.getIDecoderService().setFlashMode(mode);
        } catch (RemoteException var3) {
            ;
        }

    }

    public void enableLight(int type, boolean enable) {
        try {
            this.getIDecoderService().enableLight(type, enable);
        } catch (RemoteException var4) {
            ;
        }

    }

    public int getDataTransferType() {
        try {
            return this.getIDecoderService().getDataTransferType();
        } catch (RemoteException var2) {
            return -1;
        }
    }

    public void setDataTransferType(int transferType) {
        try {
            this.getIDecoderService().setDataTransferType(transferType);
        } catch (RemoteException var3) {
            ;
        }

    }

    public void dispatchScanKeyEvent(KeyEvent keyEvent) {
        try {
            this.getIDecoderService().dispatchScanKeyEvent(keyEvent);
        } catch (RemoteException var3) {
            ;
        }

    }

    public void setScannerEnable(boolean enable) {
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
            return true;
        }
    }

    public int getScanMode() {
        try {
            return this.getIDecoderService().getScanMode();
        } catch (RemoteException var2) {
            return -1;
        }
    }

    public void setScanMode(int mode) {
        try {
            this.getIDecoderService().setScanMode(mode);
        } catch (RemoteException var3) {
            ;
        }

    }

    public int getScanSceneMode() {
        try {
            return this.getIDecoderService().getScanSceneMode();
        } catch (RemoteException var2) {
            return -1;
        }
    }

    public void setScanSceneMode(int mode) {
        try {
            this.getIDecoderService().setScanSceneMode(mode);
        } catch (RemoteException var3) {
            ;
        }

    }

    public int getScanEfficientMode() {
        try {
            return this.getIDecoderService().getScanEfficientMode();
        } catch (RemoteException var2) {
            return -1;
        }
    }

    public void setScanEfficientMode(int mode) {
        try {
            this.getIDecoderService().setScanEfficientMode(mode);
        } catch (RemoteException var3) {
            ;
        }

    }

    public byte[] getScanImageBytes() {
        byte[] image = null;

        try {
            Bundle e = new Bundle();
            this.getIDecoderService().ioControl(4096, (Bundle)null, e);
            image = e.getByteArray("result");
        } catch (RemoteException var3) {
            ;
        }

        return image;
    }

    public void addDecoderStatusListener(DecoderManager.IDecoderStatusListener listener) {
        if(this.mDecoderStatusListeners.get(listener) == null) {
            DecoderManager.DecoderCallbackTransport transport = new DecoderManager.DecoderCallbackTransport(listener);

            try {
                this.getIDecoderService().registerDecoderCallback(transport);
                this.mDecoderStatusListeners.put(listener, transport);
            } catch (Exception var4) {
                ;
            }

        }
    }

    public void removeDecoderStatusListener(DecoderManager.IDecoderStatusListener listener) {
        DecoderManager.DecoderCallbackTransport transport = (DecoderManager.DecoderCallbackTransport)this.mDecoderStatusListeners.remove(listener);
        if(transport != null) {
            try {
                this.getIDecoderService().unregisterDecoderCallback(transport);
            } catch (Exception var4) {
                ;
            }
        }

    }

    private IDecoderService getIDecoderService() {
        try {
            Class<?> ServiceManagerClazz =Class.forName("android.os.ServiceManager");
            Method getService =ServiceManagerClazz.getDeclaredMethod("getService",String.class);
            getService.setAccessible(true);
            IBinder binder= (IBinder) getService.invoke(null,"IDecoderService");
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

    private class DecoderCallbackTransport extends com.zltd.decoder.IDecoderStateListener.Stub {
        private DecoderManager.IDecoderStatusListener mIDecoderStatusListener;

        public DecoderCallbackTransport(DecoderManager.IDecoderStatusListener listener) {
            this.mIDecoderStatusListener = listener;
        }

        public void onSendResult(String result, String time) throws RemoteException {
            this.mIDecoderStatusListener.onDecoderResultChanage(result, time);
        }
    }

    public interface IDecoderStatusListener {
        void onDecoderStatusChanage(int var1);

        void onDecoderResultChanage(String var1, String var2);
    }
}
