package com.yu.uiautomatortest.util;

import android.widget.Toast;

import com.yu.uiautomatortest.app.BaseApplication;

/**
 * Created by Administrator on 2017-6-19.
 * 吐司
 */

public class ToastUtils {
    public static void showToast(String msg) {
        Toast.makeText(BaseApplication.mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
