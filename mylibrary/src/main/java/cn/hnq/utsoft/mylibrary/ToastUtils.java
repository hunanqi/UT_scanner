package cn.hnq.utsoft.mylibrary;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 胡楠启 on 2017/3/1.
 * Function：
 * Desc：
 */

public class ToastUtils {
    private static Context mContext;
    private static Toast mToast;

    public static void init(Context context) {
        mContext = context;
        mToast = new Toast(mContext);
    }

    public static void show() {
        if (mToast == null)
            return;
        LayoutInflater inflate = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(Resources.getSystem().getIdentifier("transient_notification", "layout", "android"),
                null);
        TextView tv = (TextView) v.findViewById(Resources.getSystem().getIdentifier("message", "id", "android"));
        tv.setText("测试");
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(v);
        mToast.show();
    }
}
