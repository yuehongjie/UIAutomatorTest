package com.yu.uiautomatortest.idling;

import android.support.test.espresso.IdlingResource;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-6-26.
 * 异步操作
 */

public class RecyclerAdaterIdlingResource implements IdlingResource{

    private ResourceCallback mCallback;
    private TextView tvResult;

    public RecyclerAdaterIdlingResource(TextView tvResult) {
        this.tvResult = tvResult;
    }

    @Override
    public String getName() {
        return "RecyclerAdaterIdlingResource";
    }

    // 此方法通常用来通知主线程，其异步操作的完成，好让主线程更新UI，返回true便通知主线程去更新UI线程
    @Override
    public boolean isIdleNow() {
        if (mCallback != null) {
            if (tvResult != null && "true".equals(tvResult.getText())) {//当成功获取数据
                return true;
            }
        }else {
            return true;
        }
        return false;
    }

    //注册回调
    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }
}
