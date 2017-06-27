package com.yu.uiautomatortest.idling;

import android.support.test.espresso.IdlingResource;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017-6-26.
 * 睡眠操作
 */

public class SleepIdlingResource implements IdlingResource{

    private ResourceCallback mCallback;
    private final long waitingTime;
    private final long startTime;

    /**
     * 倒计时
     * @param waitingSeconds 等待时长
     */
    public SleepIdlingResource(long waitingSeconds) {
        this.waitingTime = waitingSeconds;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public String getName() {
        return "SleepIdlingResource";
    }

    // 此方法通常用来通知主线程，其异步操作的完成，好让主线程更新UI，返回true便通知主线程去更新UI线程
    @Override
    public boolean isIdleNow() {
        long elapsed = System.currentTimeMillis() - startTime;
        boolean idle = (elapsed >= waitingTime);
        if (idle) {
            mCallback.onTransitionToIdle();
        }
        return idle;
    }

    //注册回调
    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }
}
