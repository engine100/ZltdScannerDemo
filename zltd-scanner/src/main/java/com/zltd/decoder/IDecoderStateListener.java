//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zltd.decoder;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDecoderStateListener extends IInterface {
    void onSendResult(String var1, String var2) throws RemoteException;

    public abstract static class Stub extends Binder implements IDecoderStateListener {
        private static final String DESCRIPTOR = "com.zltd.decoder.IDecoderStateListener";
        static final int TRANSACTION_onSendResult = 1;

        public Stub() {
            this.attachInterface(this, "com.zltd.decoder.IDecoderStateListener");
        }

        public static IDecoderStateListener asInterface(IBinder obj) {
            if(obj == null) {
                return null;
            } else {
                IInterface iin = obj.queryLocalInterface("com.zltd.decoder.IDecoderStateListener");
                return (IDecoderStateListener)(iin != null && iin instanceof IDecoderStateListener?(IDecoderStateListener)iin:new IDecoderStateListener.Stub.Proxy(obj));
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch(code) {
            case 1:
                data.enforceInterface("com.zltd.decoder.IDecoderStateListener");
                String _arg0 = data.readString();
                String _arg1 = data.readString();
                this.onSendResult(_arg0, _arg1);
                reply.writeNoException();
                return true;
            case 1598968902:
                reply.writeString("com.zltd.decoder.IDecoderStateListener");
                return true;
            default:
                return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IDecoderStateListener {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "com.zltd.decoder.IDecoderStateListener";
            }

            public void onSendResult(String result, String time) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("com.zltd.decoder.IDecoderStateListener");
                    _data.writeString(result);
                    _data.writeString(time);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }
        }
    }
}
