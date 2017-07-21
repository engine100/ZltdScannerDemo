package com.zltd.scanner.impl.honeywell;

import android.util.Log;

import com.zltd.scanner.impl.ScanEngine;
import com.zltd.scanner.impl.moto.MotoScanEngine;
import com.zltd.scanner.n1000.ScanEngineTrigger;
import com.zltd.scanner.n1000.ScannerManager;
import com.zltd.scanner.n1000.SerialPortManager;

public class HoneywellScanEngine extends ScanEngine {
	public static final byte[] PREFIX = { 0x16, 0x4d, 0x0d };
	public static final String REQUEST_REVISION = "REVINF!";
	public static final String CONTINUOUS_SCAN = "PAPPM3!";
	public static final String RESET_DEFAULT = "AOSMEN1,OEN0,MRT0,MPT1700,MGD1,CGD1!";// "AOSDFT,MPT1700!";
	public static final String RESET_FACTORY = "AOSDFT.";
	public static final String LONG_TIMEOUT = "AOSMEN1,OEN0,MRT0,MPT65525,MGD1,CGD1.";// "AOSMPT65525.";
	public static final String SHORT_TIMEOUT = "AOSMPT1700.";
	public static final String INIT_PARAM = "pwrmod2,lpt600;AOSMPT1700;PAPLS1;DLYRRD260;BEPBEP0.";
	public static final String INIT_PARAM_2 = "pwrmod2,lpt600;AOSMPT65525;PAPLS1;DLYRRD260;BEPBEP0.";

	public HoneywellScanEngine(ScannerManager context) {
		super(context);
		mScanEngineType = ScannerManager.SCAN_ENGINE_HONEYWELL;
		// TODO Auto-generated constructor stub
	}

	public int initializeEngine(String revision, int scanMode) {
		super.initializeEngine(revision, scanMode);
		setScanEngineType(ScannerManager.SCAN_ENGINE_HONEYWELL);

		if (scanMode == ScannerManager.SCAN_KEY_HOLD_MODE) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(new String(PREFIX));
			buffer.append(INIT_PARAM_2);
			mContext.sendCommand(buffer.toString().getBytes(), true,
					ScanEngine.WAKE_UP_TIMEOUT);
		} else {
			StringBuffer buffer = new StringBuffer();
			buffer.append(new String(PREFIX));
			buffer.append(INIT_PARAM);
			mContext.sendCommand(buffer.toString().getBytes(), true,
					ScanEngine.WAKE_UP_TIMEOUT);
		}
		return ScannerManager.RETURN_SUCCESS;
	}

	public int setScanMode(int scanMode) {
		super.setScanMode(scanMode);

		if (scanMode == ScannerManager.SCAN_KEY_HOLD_MODE) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(new String(PREFIX));
			buffer.append(LONG_TIMEOUT);
			mContext.sendCommand(buffer.toString().getBytes(), true,
					ScanEngine.WAKE_UP_TIMEOUT);
		}
		if (mPrevScanMode == ScannerManager.SCAN_KEY_HOLD_MODE) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(new String(PREFIX));
			buffer.append(SHORT_TIMEOUT);
			mContext.sendCommand(buffer.toString().getBytes(), true,
					ScanEngine.WAKE_UP_TIMEOUT);
		}
		return ScannerManager.RETURN_SUCCESS;
	}

	public int resetFactory() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(new String(PREFIX));
		buffer.append(RESET_FACTORY);
		return mContext.sendCommand(buffer.toString().getBytes(), true,
				ScanEngine.WAKE_UP_TIMEOUT);
	}

	public int startContinuousScan() {
		super.startContinuousScan();
		StringBuffer buffer = new StringBuffer();
		buffer.append(new String(PREFIX));
		buffer.append(CONTINUOUS_SCAN);
		mContext.sendCommand(buffer.toString().getBytes(), true,
				ScanEngine.WAKE_UP_TIMEOUT);
		return ScannerManager.RETURN_SUCCESS;
	}

	public int stopContinuousScan() {
		super.stopContinuousScan();
		StringBuffer buffer = new StringBuffer();
		buffer.append(new String(PREFIX));
		buffer.append(RESET_DEFAULT);
		// mContext.sendCommand(buffer.toString().getBytes(), true,
		// ScanEngine.WAKE_UP_TIMEOUT);
		sendCommandToEngine(buffer.toString().getBytes());
		return ScannerManager.RETURN_SUCCESS;
	}

	@Override
	public int singleScan() {
		if (mMonitorSingleThread != null
				&& !mMonitorSingleThread.isInterrupted()) {
			mMonitorSingleThread.interrupt();
		}
		mMonitorSingleThread = new Thread() {
			public void run() {
				Log.i(TAG, "single Scan");
				ScanEngineTrigger.getInstance().openTrigger();
				// SerialPortManager.getInstance().openSerialPort();
				SerialPortManager.getInstance().flush();
				String data;
				boolean isWorking = false;
				int count = 0;
				try {
					// triggerLevel(ScannerManager.HIGH_LEVEL);
					// SerialPortManager.getInstance().sendCommand(ScanEngine.WAKE_UP);
					Thread.sleep(25);
					triggerLevel(ScannerManager.LOW_LEVEL);

					StringBuffer buffer = new StringBuffer();
					buffer.append(new String(HoneywellScanEngine.PREFIX));
					buffer.append("BEPBEP0!");
					SerialPortManager.getInstance().sendCommand(
							buffer.toString().getBytes());

					for (int i = 0; i < 280; i++) {
						Thread.sleep(10);
						data = SerialPortManager.getInstance().getResult();
						if (data.length() > 0) {
							Log.i(TAG, "get result is: " + data);
							SerialPortManager.getInstance().flush();
						}

						if (count < 35 && !isWorking) {
							if (data.contains("BEPB")) {
								Log.i(TAG, "scanner is working");
								isWorking = true;
								Log.i(TAG, "data lenth" + data.length());
								data = data.substring(9, data.length());
								Log.i(TAG, "data = " + data);
//								continue;
								
							}
						} else if (count >= 35 && !isWorking) {
							Log.i(TAG, "scanner is not working, reopen it.");
							mContext.scannerEnable(false);
							mContext.scannerEnable(true);
							break;
						}

						String str = StringFilter(data);
						if (str != null && !str.equals("")) {
							Log.i(TAG, "scan result: " + str);
							mContext.sendResult(str);
							break;
						}
						count++;
					}
				} catch (InterruptedException e) {
					Log.d(TAG, "It's lang time for monitor, interrupt it.");
				} finally {
					triggerLevel(ScannerManager.HIGH_LEVEL);
				}
			}
		};
		mMonitorSingleThread.start();
		return ScannerManager.RETURN_SUCCESS;
	}
}
