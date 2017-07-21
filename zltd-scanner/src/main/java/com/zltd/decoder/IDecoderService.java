//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zltd.decoder;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.KeyEvent;

public interface IDecoderService extends IInterface {
    int singleShoot() throws RemoteException;

    int stopShootImmediately() throws RemoteException;

    int continuousShoot() throws RemoteException;

    int stopContinuousShoot() throws RemoteException;

    boolean isContinuousShootState() throws RemoteException;

    int getScanMode() throws RemoteException;

    void setScanMode(int var1) throws RemoteException;

    int getDataTransferType() throws RemoteException;

    void setDataTransferType(int var1) throws RemoteException;

    void dispatchScanKeyEvent(KeyEvent var1) throws RemoteException;

    void registerDecoderCallback(IDecoderStateListener var1) throws RemoteException;

    void unregisterDecoderCallback(IDecoderStateListener var1) throws RemoteException;

    int connectDecoderSRV() throws RemoteException;

    int disconnectDecoderSRV() throws RemoteException;

    void stopDecode() throws RemoteException;

    int getFlashMode() throws RemoteException;

    void setFlashMode(int var1) throws RemoteException;

    void enableLight(int var1, boolean var2) throws RemoteException;

    void setScannerEnable(boolean var1) throws RemoteException;

    boolean getScannerEnable() throws RemoteException;

    int getScanSceneMode() throws RemoteException;

    void setScanSceneMode(int var1) throws RemoteException;

    int getScanEfficientMode() throws RemoteException;

    void setScanEfficientMode(int var1) throws RemoteException;

    void ioControl(int var1, Bundle var2, Bundle var3) throws RemoteException;

    int getDecoderType() throws RemoteException;

    int keyShoot(boolean var1) throws RemoteException;

    int quitWithReason(int var1) throws RemoteException;

    boolean isKeyShootEnabled() throws RemoteException;

    boolean isInKeyShoot() throws RemoteException;

    public abstract static class Stub extends Binder implements IDecoderService {
        private static final String DESCRIPTOR = "com.zltd.decoder.IDecoderService";
        static final int TRANSACTION_singleShoot = 1;
        static final int TRANSACTION_stopShootImmediately = 2;
        static final int TRANSACTION_continuousShoot = 3;
        static final int TRANSACTION_stopContinuousShoot = 4;
        static final int TRANSACTION_isContinuousShootState = 5;
        static final int TRANSACTION_getScanMode = 6;
        static final int TRANSACTION_setScanMode = 7;
        static final int TRANSACTION_getDataTransferType = 8;
        static final int TRANSACTION_setDataTransferType = 9;
        static final int TRANSACTION_dispatchScanKeyEvent = 10;
        static final int TRANSACTION_registerDecoderCallback = 11;
        static final int TRANSACTION_unregisterDecoderCallback = 12;
        static final int TRANSACTION_connectDecoderSRV = 13;
        static final int TRANSACTION_disconnectDecoderSRV = 14;
        static final int TRANSACTION_stopDecode = 15;
        static final int TRANSACTION_getFlashMode = 16;
        static final int TRANSACTION_setFlashMode = 17;
        static final int TRANSACTION_enableLight = 18;
        static final int TRANSACTION_setScannerEnable = 19;
        static final int TRANSACTION_getScannerEnable = 20;
        static final int TRANSACTION_getScanSceneMode = 21;
        static final int TRANSACTION_setScanSceneMode = 22;
        static final int TRANSACTION_getScanEfficientMode = 23;
        static final int TRANSACTION_setScanEfficientMode = 24;
        static final int TRANSACTION_ioControl = 25;

        public Stub() {
            this.attachInterface(this, "com.zltd.decoder.IDecoderService");
        }

        public static IDecoderService asInterface(IBinder obj) {
            if(obj == null) {
                return null;
            } else {
                IInterface iin = obj.queryLocalInterface("com.zltd.decoder.IDecoderService");
                return (IDecoderService)(iin != null && iin instanceof IDecoderService?(IDecoderService)iin:new IDecoderService.Stub.Proxy(obj));
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int _arg0;
            boolean _arg01;
            IDecoderStateListener _arg02;
            switch(code) {
            case 1:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = this.singleShoot();
                reply.writeNoException();
                reply.writeInt(_arg0);
                return true;
            case 2:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = this.stopShootImmediately();
                reply.writeNoException();
                reply.writeInt(_arg0);
                return true;
            case 3:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = this.continuousShoot();
                reply.writeNoException();
                reply.writeInt(_arg0);
                return true;
            case 4:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = this.stopContinuousShoot();
                reply.writeNoException();
                reply.writeInt(_arg0);
                return true;
            case 5:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg01 = this.isContinuousShootState();
                reply.writeNoException();
                reply.writeInt(_arg01?1:0);
                return true;
            case 6:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = this.getScanMode();
                reply.writeNoException();
                reply.writeInt(_arg0);
                return true;
            case 7:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = data.readInt();
                this.setScanMode(_arg0);
                reply.writeNoException();
                return true;
            case 8:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = this.getDataTransferType();
                reply.writeNoException();
                reply.writeInt(_arg0);
                return true;
            case 9:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = data.readInt();
                this.setDataTransferType(_arg0);
                reply.writeNoException();
                return true;
            case 10:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                KeyEvent _arg03;
                if(0 != data.readInt()) {
                    _arg03 = (KeyEvent)KeyEvent.CREATOR.createFromParcel(data);
                } else {
                    _arg03 = null;
                }

                this.dispatchScanKeyEvent(_arg03);
                reply.writeNoException();
                return true;
            case 11:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg02 = com.zltd.decoder.IDecoderStateListener.Stub.asInterface(data.readStrongBinder());
                this.registerDecoderCallback(_arg02);
                reply.writeNoException();
                return true;
            case 12:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg02 = com.zltd.decoder.IDecoderStateListener.Stub.asInterface(data.readStrongBinder());
                this.unregisterDecoderCallback(_arg02);
                reply.writeNoException();
                return true;
            case 13:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = this.connectDecoderSRV();
                reply.writeNoException();
                reply.writeInt(_arg0);
                return true;
            case 14:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = this.disconnectDecoderSRV();
                reply.writeNoException();
                reply.writeInt(_arg0);
                return true;
            case 15:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                this.stopDecode();
                reply.writeNoException();
                return true;
            case 16:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = this.getFlashMode();
                reply.writeNoException();
                reply.writeInt(_arg0);
                return true;
            case 17:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = data.readInt();
                this.setFlashMode(_arg0);
                reply.writeNoException();
                return true;
            case 18:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = data.readInt();
                boolean _arg11 = 0 != data.readInt();
                this.enableLight(_arg0, _arg11);
                reply.writeNoException();
                return true;
            case 19:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg01 = 0 != data.readInt();
                this.setScannerEnable(_arg01);
                reply.writeNoException();
                return true;
            case 20:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg01 = this.getScannerEnable();
                reply.writeNoException();
                reply.writeInt(_arg01?1:0);
                return true;
            case 21:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = this.getScanSceneMode();
                reply.writeNoException();
                reply.writeInt(_arg0);
                return true;
            case 22:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = data.readInt();
                this.setScanSceneMode(_arg0);
                reply.writeNoException();
                return true;
            case 23:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = this.getScanEfficientMode();
                reply.writeNoException();
                reply.writeInt(_arg0);
                return true;
            case 24:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = data.readInt();
                this.setScanEfficientMode(_arg0);
                reply.writeNoException();
                return true;
            case 25:
                data.enforceInterface("com.zltd.decoder.IDecoderService");
                _arg0 = data.readInt();
                Bundle _arg1;
                if(0 != data.readInt()) {
                    _arg1 = (Bundle)Bundle.CREATOR.createFromParcel(data);
                } else {
                    _arg1 = null;
                }

                Bundle _arg2 = new Bundle();
                this.ioControl(_arg0, _arg1, _arg2);
                reply.writeNoException();
                if(_arg2 != null) {
                    reply.writeInt(1);
                    _arg2.writeToParcel(reply, 1);
                } else {
                    reply.writeInt(0);
                }

                return true;
            case 1598968902:
                reply.writeString("com.zltd.decoder.IDecoderService");
                return true;
            default:
                return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IDecoderService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "com.zltd.decoder.IDecoderService";
            }

            public int singleShoot() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                int _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public int stopShootImmediately() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                int _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public int continuousShoot() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                int _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public int stopContinuousShoot() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                int _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public boolean isContinuousShootState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                boolean _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    _result = 0 != _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public int getScanMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                int _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public void setScanMode(int scanMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    _data.writeInt(scanMode);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public int getDataTransferType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                int _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public void setDataTransferType(int transferType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    _data.writeInt(transferType);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void dispatchScanKeyEvent(KeyEvent keyEvent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    if(keyEvent != null) {
                        _data.writeInt(1);
                        keyEvent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void registerDecoderCallback(IDecoderStateListener scb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    _data.writeStrongBinder(scb != null?scb.asBinder():null);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void unregisterDecoderCallback(IDecoderStateListener scb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    _data.writeStrongBinder(scb != null?scb.asBinder():null);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public int connectDecoderSRV() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                int _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public int disconnectDecoderSRV() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                int _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public void stopDecode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public int getFlashMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                int _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public void setFlashMode(int flashMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    _data.writeInt(flashMode);
                    this.mRemote.transact(17, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void enableLight(int type, boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    _data.writeInt(type);
                    _data.writeInt(enable?1:0);
                    this.mRemote.transact(18, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void setScannerEnable(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    _data.writeInt(enable?1:0);
                    this.mRemote.transact(19, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public boolean getScannerEnable() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                boolean _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(20, _data, _reply, 0);
                    _reply.readException();
                    _result = 0 != _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public int getScanSceneMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                int _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(21, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public void setScanSceneMode(int sceneMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    _data.writeInt(sceneMode);
                    this.mRemote.transact(22, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public int getScanEfficientMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                int _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(23, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public void setScanEfficientMode(int sceneMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    _data.writeInt(sceneMode);
                    this.mRemote.transact(24, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void ioControl(int command, Bundle input, Bundle output) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    _data.writeInt(command);
                    if(input != null) {
                        _data.writeInt(1);
                        input.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    this.mRemote.transact(25, _data, _reply, 0);
                    _reply.readException();
                    if(0 != _reply.readInt()) {
                        output.readFromParcel(_reply);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }
            public int getDecoderType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                int _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(26, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public int keyShoot(boolean on) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                int _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    _data.writeInt(on?1:0);
                    this.mRemote.transact(27, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public int quitWithReason(int on) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                int _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    _data.writeInt(on);
                    this.mRemote.transact(28, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public boolean isKeyShootEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                boolean _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(29, _data, _reply, 0);
                    _reply.readException();
                    _result = 0 != _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public boolean isInKeyShoot() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                boolean _result;
                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderService");
                    this.mRemote.transact(30, _data, _reply, 0);
                    _reply.readException();
                    _result = 0 != _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }
        }
    }
}
