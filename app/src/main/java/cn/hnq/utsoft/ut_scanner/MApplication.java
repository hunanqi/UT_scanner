package cn.hnq.utsoft.ut_scanner;

import android.app.Application;

import cn.hnq.utsoft.mylibrary.ToastUtils;
import cn.utsoft.commons.toast.UTToastUtil;

/**
 * Created by 胡楠启 on 2017/2/28.
 * Function：
 * Desc：
 */

public class MApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UTToastUtil.initUTToastUtil(this);
        ToastUtils.init(this);
    }
}
