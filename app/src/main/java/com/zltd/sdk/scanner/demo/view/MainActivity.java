/*
 * Perceive World Technology Co.,Ltd.
 *
 *      https://github.com/zltd
 *
 */
package com.zltd.sdk.scanner.demo.view;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zltd.decoder.DecoderManager;
import com.zltd.industry.ScannerManager;
import com.zltd.sdk.scanner.compat.ScanAdapter;
import com.zltd.sdk.scanner.compat.ScanMode;
import com.zltd.sdk.scanner.compat.controller.ZLTDDecoderManagerController;
import com.zltd.sdk.scanner.compat.controller.ZLTDScannerManagerController;
import com.zltd.sdk.scanner.demo.R;
import com.zltd.sdk.scanner.demo.scan.BaseScanActivity;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

/**
 * a demo activity for show scan results.
 *
 * @author Engine100
 */
public class MainActivity extends BaseScanActivity {

    private List<String> mScanDataList = new LinkedList<>();
    private ArrayAdapter<String> mAdapter;
    private ListView mScanListView;
    private TextView mLastShow;
    private TextView mVersionName;
    private RadioGroup modeGroup;
    private final RadioGroup.OnCheckedChangeListener modeChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            onStopScan();
            setScanModeByRadioButton();
        }
    };
    private ImageView mImageView;
    private int mScanSuccessTimes = 0;

    //the scanner sometimes return a simple bytes of image,you can decode it
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLastShow = (TextView) findViewById(R.id.last_show);
        mVersionName = (TextView) findViewById(R.id.version_name);
        mScanListView = (ListView) findViewById(R.id.barlist);
        modeGroup = (RadioGroup) findViewById(R.id.mode_group);
        mImageView = (ImageView) findViewById(R.id.image);
        mVersionName.setText("Version:" + getVersionName());
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mScanDataList);
        mScanListView.setAdapter(mAdapter);
        modeGroup.setOnCheckedChangeListener(modeChangeListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setScanModeByRadioButton();
    }

    private void setScanModeByRadioButton() {
        int modeId = modeGroup.getCheckedRadioButtonId();
        switch (modeId) {
            case R.id.btn_mode_single:
                mScanAdapter.setScanMode(ScanMode.SCAN_SINGLE_MODE);
                break;
            case R.id.btn_mode_continue:
                mScanAdapter.setScanMode(ScanMode.SCAN_CONTINUOUS_MODE);
                break;
            case R.id.btn_mode_keyhold:
                mScanAdapter.setScanMode(ScanMode.SCAN_KEY_HOLD_MODE);
                break;
        }
    }

    @Override
    public void onScannerSuccess(String code) {
        String showData = ++mScanSuccessTimes + ":\n" + code;
        mLastShow.setText("第" + mScanSuccessTimes + "次：" + code);
        mScanDataList.add(0, showData);
        mAdapter.notifyDataSetChanged();
       // decodeBitmap();

    }

    private void decodeBitmap() {

        ScanAdapter mAdapter = ScanAdapter.getInstance();
        //DecoderManager return a bitmap
        if (mAdapter.getController() instanceof ZLTDDecoderManagerController) {
            ZLTDDecoderManagerController controller = (ZLTDDecoderManagerController) mAdapter.getController();
            DecoderManager manager = controller.getDecoderManager();
            byte[] bytes = manager.getScanImageBytes();
            Bitmap stitchBmp = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_4444);
            stitchBmp.copyPixelsFromBuffer(ByteBuffer.wrap(bytes));
            if (mBitmap != null && !mBitmap.isRecycled()) mBitmap.recycle();
            mBitmap = stitchBmp;
            mImageView.setImageBitmap(mBitmap);

//        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
//        mImageView.setImageBitmap(bitmap);
        }
        //ScannerManager return a bitmap
        else if (mAdapter.getController() instanceof ZLTDScannerManagerController) {
            ZLTDScannerManagerController controller = (ZLTDScannerManagerController) mAdapter.getController();
            ScannerManager manager = controller.getScannerManager();
            try {
                //some machines do not have method getScanImageBytes
                if (manager.getClass().getMethod("getScanImageBytes") != null) {
                    byte[] bytes = manager.getScanImageBytes();
                    Bitmap stitchBmp = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_4444);
                    stitchBmp.copyPixelsFromBuffer(ByteBuffer.wrap(bytes));
                    if (mBitmap != null && !mBitmap.isRecycled()) mBitmap.recycle();
                    mBitmap = stitchBmp;
                    mImageView.setImageBitmap(mBitmap);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onScannerError(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void onStopScan(View view) {
        onStopScan();
    }

    public void onStartScan(View view) {
        onStartScan();
    }

    private String getVersionName() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
