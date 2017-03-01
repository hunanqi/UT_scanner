package cn.hnq.utsoft.ut_scanner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

import cn.hnq.utsoft.mylibrary.ToastUtils;
import cn.utsoft.commons.cropper.CropResultListener;
import cn.utsoft.commons.cropper.UTCropManager;
import cn.utsoft.commons.imgbrowser.helper.UTImgBrowserHelper;
import cn.utsoft.commons.qrscanner.ScanResultListener;
import cn.utsoft.commons.qrscanner.UTScannerManager;

/**
 * Created by 胡楠启 on 2017/2/28 13:49
 * Function: 测试公共模块
 * Desc:
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtn_scanner;//二维码扫描
    private Button mBtn_image;//图片裁剪
    private Button mBtn_toast;//弱提示框
    private ImageView mIm_bigImage;//大图查看
    private int mFlag = 1;//弱提示框按钮的状态。
    private UTCropManager cropManager;//创建UTCropManager对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn_scanner = (Button) findViewById(R.id.btn_scanner);
        mBtn_image = (Button) findViewById(R.id.btn_image);
        mBtn_toast = (Button) findViewById(R.id.btn_toast);
        mIm_bigImage = (ImageView) findViewById(R.id.im_bigImage);
        mBtn_scanner.setOnClickListener(this);
        mBtn_image.setOnClickListener(this);
        mBtn_toast.setOnClickListener(this);
        mIm_bigImage.setOnClickListener(this);
        cropManager = new UTCropManager(this);

    }

    //图片裁剪
    private void imagge() {
        //调用剪裁方法
        cropManager.crop(MainActivity.this, null, UTCropManager.SourceType.GALLERY, false, new CropResultListener() {
            @Override
            public void onSuccess(Uri uri) {
                File file = new File(URI.create(uri.toString()));
                Log.i("sss", file.getAbsolutePath());
            }

            @Override
            public void onFailure(String reason) {

            }
        });
    }

    //二维码扫描
    private void scanner() {
        UTScannerManager.getIns().toScanActivity(this, new ScanResultListener() {
            //扫描结果回调
            @Override
            public void onSuccess(String result) {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "扫描失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "扫描取消", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scanner://二维码扫描
                scanner();
                break;
            case R.id.btn_image://图片裁剪
                imagge();
                break;
            case R.id.btn_toast://弱提示框
                ToastUtils.show();
         /*       if (mFlag == 1) {
                    UTToastUtil.success("成功");
                    mFlag++;
                } else if (mFlag == 2) {
                    UTToastUtil.fail("失败");
                    mFlag++;
                } else {
                    UTToastUtil.warn("错误");
                    mFlag = 1;
                }*/
                break;
            case R.id.im_bigImage://大图查看
                bigImage();
                break;
        }
    }

    //大图查看
    private void bigImage() {
        UTImgBrowserHelper helper = new UTImgBrowserHelper(MainActivity.this);
        helper.addImageView(mIm_bigImage, "https://www.baidu.com/img/bd_logo1.png");
        helper.addImageView(mIm_bigImage, "https://gss0.baidu" +
                ".com/-fo3dSag_xI4khGko9WTAnF6hhy/lbs/pic/item/7dd98d1001e939010f5ffd0672ec54e736d196ac.jpg");
        helper.startPreActivity(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cropManager.handleCropResult(requestCode, resultCode, data);
    }
}
