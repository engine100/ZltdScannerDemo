package com.zltd.scanner.n1000;

import java.util.ArrayList;

import com.zltd.scanner.impl.ScanEngine;
import com.zltd.scanner.impl.honeywell.HoneywellScanEngine;
import com.zltd.scanner.impl.moto.MotoScanEngine;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Build.VERSION;
import android.util.Log;

// import android.os.ServiceManager;

public class ScannerManager {
	private static final String TAG = "scanner";
	/**
	 * Scanner Service description.
	 * 
	 * @hide
	 */
	public static final String SCANNER_SERVICE = "IScannerService";
	/**
	 * If ScannerService is a independent app, startup service using the action.
	 * 
	 * @hide
	 */
	public static final String SCANNER_ACTION = "android.intent.action.SCANNER_SERVICE";
	/**
	 * Broadcast Action: receive the scan result from scanner. The data contains
	 * a String.
	 * <p class="note">
	 * This is a protected intent that can only be sent by the scanner service.
	 * </p>
	 */
	public static final String GET_SCANDATA = "android.intent.action.GET_SCANDATA";
	/**
	 * Broadcast GET_SCANDATA of data.
	 */
	public static final String SCAN_DATA = "android.intent.extra.SCAN_DATA";

	/**
	 * return the value if the function call is successful .
	 */
	public static final int RETURN_SUCCESS = 0x101;
	/**
	 * return the value if the scan engine is busy .
	 */
	public static final int RETURN_ENGINE_BUSY = 0x102;
	/**
	 * return the value if the scan engine is unknown .
	 */
	public static final int RETURN_ENGINE_TYPE_ERR = 0x103;
	/**
	 * return the value if scan engine driver has a error .
	 */
	public static final int RETURN_SERIAL_PORT_ERR = 0x104;
	/**
	 * return the value if scan trigger driver has a error .
	 */
	public static final int RETURN_TRIGGER_DEVICE_ERR = 0x105;
	/**
	 * return the value if the function isnot implement .
	 */
	public static final int RETURN_NOT_IMPL = 0x106;

	/**
	 * Param of {@link #triggerLevel(int)} function, LOW_LEVEL will trigger on
	 * scanner.
	 */
	public static final int LOW_LEVEL = 0x00;
	/**
	 * Param of {@link #triggerLevel(int)} function, HIGH_LEVEL will trigger off
	 * scanner.
	 */
	public static final int HIGH_LEVEL = 0x01;

	/**
	 * Return value of {@link #getScanEngineType()} function,
	 * SCAN_ENGINE_UNKNOWN is a unknown scanner.
	 */
	public static final int SCAN_ENGINE_UNKNOWN = 1000;
	/**
	 * Return value of {@link #getScanEngineType()} function, SCAN_ENGINE_MOTO
	 * is a Moto scanner.
	 */
	public static final int SCAN_ENGINE_MOTO = 1001;
	/**
	 * Return value of {@link #getScanEngineType()} function,
	 * SCAN_ENGINE_HONEYWELL is a Honeywell scanner.
	 */
	public static final int SCAN_ENGINE_HONEYWELL = 1002;
	/**
	 * Return value of {@link #getScanEngineType()} function, SCAN_ENGINE_MINDEO
	 * is a Mindeo scanner.
	 */
	public static final int SCAN_ENGINE_MINDEO = 1003;
	/**
	 * Return value of {@link #getScanEngineType()} function,
	 * SCAN_ENGINE_NEWLAND is a Newland scanner.
	 */
	public static final int SCAN_ENGINE_NEWLAND = 1004;

	/**
	 * Param of {@link #setScanMode(int)} function, or return value of
	 * {@link #getScanMode()} function, SCAN_SINGLE_MODE is single scan mode.
	 */
	public static final int SCAN_SINGLE_MODE = 0x01;
	/**
	 * Param of {@link #setScanMode(int)} function, or return value of
	 * {@link #getScanMode()} function, SCAN_CONTINUOUS_MODE is continuous scan
	 * mode.
	 */
	public static final int SCAN_CONTINUOUS_MODE = 0x02;
	/**
	 * Param of {@link #setScanMode(int)} function, or return value of
	 * {@link #getScanMode()} function, SCAN_KEY_HOLD_MODE is key hold scan
	 * mode(level mode).
	 */
	public static final int SCAN_KEY_HOLD_MODE = 0x03;

	/**
	 * Param of {@link #setDataTransferType(int)} function, or return value of
	 * {@link #getDataTransferType()} function, TRANSFER_BY_EDITTEXT will
	 * transfer scan result using intent.
	 */
	public static final int TRANSFER_BY_EDITTEXT = 0x01;
	/**
	 * Param of {@link #setDataTransferType(int)} function, or return value of
	 * {@link #getDataTransferType()} function, TRANSFER_BY_KEY will transfer
	 * scan result using KeyEvent.
	 */
	public static final int TRANSFER_BY_KEY = 0x02;

	/**
	 * Param of {@link #setDataTransferType(int)} function, or return value of
	 * {@link #getDataTransferType()} function, TRANSFER_BY_API will transfer
	 * scan result using {@link #IScannerStatusListener}.
	 */
	public static final int TRANSFER_BY_API = 0x03;
	public static final int MESSAGE_WAKE_UP = 0x01;
	public static final int MESSAGE_START_REQUEST = 0x02;
	public static final int MESSAGE_REQUEST_REVISION = 0x03;
	public static final int MESSAGE_REPLY_REVISION = 0x04;
	public static final int MESSAGE_COMMAND = 0x05;
	public static final int MESSAGE_SEND_RESULT = 0x06;
	public static final int MESSAGE_CLOSE_DEVICE = 0x07;
	// public static final int MESSAGE_MONITOR_WAKE_UP = 0x08;

	private int mScannerType = SCAN_ENGINE_MOTO;
	private ScanEngine mCurrentScanEngine;
	private static ScannerManager mInstance;
	private SerialPortManager mSerialPortManager;
	private ScanEngineTrigger mScanEngineTrigger;
	private Looper mSendCommandLooper;
	public Handler mCommandHandler;

	private ArrayList<IScannerStatusListener> mScannerStatusListeners = new ArrayList<IScannerStatusListener>();

	public interface IScannerStatusListener {
		/**
		 * The function will be called when the scanner service state is
		 * changed, but now it is no using .
		 * 
		 * @param The
		 *            state.
		 */
		public void onScannerStatusChanage(int state);

		/**
		 * The function will be called when the scanner service catch a valid
		 * barcode.
		 * 
		 * @param The
		 *            valid barcode string.
		 */
		public void onScannerResultChanage(byte[] result);
	};

	/**
	 * Gets an instance of the scanner manager.
	 * 
	 * @return The scanner manager instance.
	 */
	public static ScannerManager getInstance() {
		synchronized (ScannerManager.class) {
			if (mInstance == null) {
				mInstance = new ScannerManager();
			}
			return mInstance;
		}
	}

	private ScannerManager() {

		// by default it is treated as Moto,
		// this is only because for most of N1000 and N1, we use Moto scanner
		mCurrentScanEngine = new MotoScanEngine(this);
		// mCurrentScanEngine = new HoneywellScanEngine(this);

		openDevice();// open device for later usage

		testScannerType();// check scanner type

	}

	@SuppressLint("HandlerLeak")
	private class SendCommandThread extends Thread {
		public void run() {
			Looper.prepare();
			mSendCommandLooper = Looper.myLooper();
			mCommandHandler = new Handler() {
				public void handleMessage(Message msg) {
					switch (msg.what) {
					case MESSAGE_COMMAND:
						mSerialPortManager.sendCommand((byte[]) msg.obj);
						break;
					case MESSAGE_SEND_RESULT:
						sendResult((String) msg.obj);
						break;
					// case MESSAGE_MONITOR_WAKE_UP:
					// Log.i(TAG, "MONITOR WAKE UP. ");
					// mSerialPortManager.sendCommand((byte[]) msg.obj);
					// Message message;
					// message =
					// mCommandHandler.obtainMessage(MESSAGE_MONITOR_WAKE_UP,
					// ScanEngine.WAKE_UP);
					// mCommandHandler.sendMessageDelayed(message, 1000);
					// break;
					default:
						break;
					}
				}
			};

			Looper.loop();
		}
	}

	public int sendCommand(byte[] command, boolean needWakeUp, int wakeupTimeout) {
		Message msg;
		if (needWakeUp) {
			msg = mCommandHandler.obtainMessage(MESSAGE_COMMAND,
					ScanEngine.WAKE_UP);
			mCommandHandler.sendMessage(msg);
		}
		msg = mCommandHandler.obtainMessage(MESSAGE_COMMAND, command);
		mCommandHandler.sendMessageDelayed(msg, wakeupTimeout);
		return ScannerManager.RETURN_SUCCESS;
	}

	public void testScannerType() {

		new Thread() {
			public void run() {
				String data;
				try {
					Thread.sleep(20);
					StringBuffer buffer = new StringBuffer();
					buffer.append(new String(HoneywellScanEngine.PREFIX));
					buffer.append(HoneywellScanEngine.REQUEST_REVISION);
					sendCommand(buffer.toString().getBytes(), true,
							ScanEngine.WAKE_UP_TIMEOUT);
					for (int i = 0; i < 3; i++) {
						Thread.sleep(100);
						data = SerialPortManager.getInstance().getResult();
						Log.d(TAG, "original data:"+data);
						// if Honeywell scanner is found
						if (data.indexOf("N4300") >= 0) {
							mCurrentScanEngine = new HoneywellScanEngine(
									ScannerManager.this);
							mScannerType = ScannerManager.SCAN_ENGINE_HONEYWELL; // add
							break;
						}
					}

					// for now, there are only Moto and Honey engine for N1000
					// and N1
					// this logic can't be used for N2S, which has Mindeo
					// scanner
					// #####warning######
					if (mScannerType==ScannerManager.SCAN_ENGINE_MOTO){
						sendCommand(MotoScanEngine.SECURITY_LEVEL, false,
								ScanEngine.RETRY_TIMEOUT * 2); // set parameter for
																// Moto Engine						
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void testMotoScannerType() {
		new Thread() {
			public void run() {
				String data;
				try {
					Thread.sleep(20);
					sendCommand(MotoScanEngine.REQUEST_REVISION, true,
							ScanEngine.WAKE_UP_TIMEOUT);
					for (int i = 0; i < 3; i++) {
						Thread.sleep(100);
						data = SerialPortManager.getInstance().getResult();
						if (data.indexOf("NBRSYPAI") >= 0) { // TODO
							mScannerType = ScannerManager.SCAN_ENGINE_MOTO;
						} else if (data.indexOf("uE966") >= 0) {
							mScannerType = ScannerManager.SCAN_ENGINE_MINDEO;
						} else {
							mScannerType = ScannerManager.SCAN_ENGINE_MOTO;
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * Gets scan engine type.
	 * 
	 * @return The scan engine type.
	 */
	public int getScanEngineType() {
		return mScannerType;
	}

	/**
	 * trigger a single scanning.
	 * 
	 * @return RETURN_SUCCESS.
	 */
	public int singleScan() {
		return mCurrentScanEngine.singleScan();
	}

	public boolean isContinousScanning() {
		return mCurrentScanEngine.isContinousScanning();
	}

	/**
	 * trigger on a continuous scanning.
	 * 
	 * @return RETURN_SUCCESS.
	 */
	public int startContinuousScan() {
		if (mScannerType == ScannerManager.SCAN_ENGINE_HONEYWELL
				&& !mCurrentScanEngine.getClass().equals(
						HoneywellScanEngine.class)) {
			mCurrentScanEngine = new HoneywellScanEngine(this);
		}
		return mCurrentScanEngine.startContinuousScan();
	}

	/**
	 * trigger off a continuous scanning.
	 * 
	 * @return RETURN_SUCCESS.
	 */
	public int stopContinuousScan() {
		return mCurrentScanEngine.stopContinuousScan();
	}

	/**
	 * trigger on/off scanner.
	 * 
	 * @return RETURN_SUCCESS.
	 */
	public int triggerLevel(int level) {
		return mCurrentScanEngine.triggerLevel(level);
	}

	/**
	 * send a commond to scan engine.
	 * 
	 * @param The
	 *            command, please make a correct command for it, otherwise
	 *            cannot take effect.
	 * @return RETURN_SUCCESS.
	 */
	public int sendCommand(byte[] command) {
		return mCurrentScanEngine.sendCommandToEngine(command);
	}

	private boolean openDevice() {
		boolean success = true;
		int fd;
		mSerialPortManager = SerialPortManager.getInstance();
		fd = mSerialPortManager.openSerialPort();
		if (fd < 0) {
			success = false;
		}
		mScanEngineTrigger = ScanEngineTrigger.getInstance();
		fd = mScanEngineTrigger.openTrigger();
		if (fd < 0) {
			success = false;
		}
		new SendCommandThread().start();
		return success;
	}

	private boolean closeDevice() {
		// mCommandHandler.removeMessages(MESSAGE_MONITOR_WAKE_UP);
		mSendCommandLooper.quit();
		mSerialPortManager.closeSerialPort();
		mScanEngineTrigger.closeTrigger();
		return true;
	}

	/**
	 * enable scanner.
	 */
	public boolean scannerEnable(boolean enable) {
		Log.i(TAG, "set scanner enable: " + enable);
		if (enable) {
			return openDevice();
		} else {
			return closeDevice();
		}
	}

	/**
	 * get enable value of scanner.
	 * 
	 * @return RETURN_SUCCESS.
	 */

	/**
	 * add scan result listener for scanner.
	 */
	public void addScannerStatusListener(IScannerStatusListener listener) {
		if (mScannerStatusListeners.contains(listener)) {
			return;
		}
		mScannerStatusListeners.add(listener);
	}

	/**
	 * remove scan result listener for scanner.
	 */
	public void removeScannerStatusListener(IScannerStatusListener listener) {
		if (mScannerStatusListeners.contains(listener)) {
			mScannerStatusListeners.remove(listener);
		}
	}

	public void sendResult(String data) {
		for (int i = 0; i < mScannerStatusListeners.size(); i++) {
			mScannerStatusListeners.get(i).onScannerResultChanage(
					data.getBytes());
		}
	}
}
