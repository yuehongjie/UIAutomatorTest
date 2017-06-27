package com.yu.uiautomatortest.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yu.uiautomatortest.util.LogUtils;

/**
 * Created by Administrator on 2017-6-26.
 * 懒加载基类
 */

public abstract class BaseLazyLoadFragment extends Fragment {

    protected static final String PARAM_TITLE = "param_title";

    protected String mTitle;

    //由于 setUserVisibleHint() 是可能在 onCreateView 之前运行，也可能在切换页面的时候运行，
    //所以需要一些标志，确定界面是否已经加载，再初始化数据，否则可能会报 空指针
    private boolean isViewCreated; // 标识界面是否已经加载，ViewPager 预加载界面的 onCreateView  setUserVisibleHint 之前调用
    private boolean hasLoadOnce; // 标识是否已经加载过一次
    private boolean isNeedInit; // 标识是否需要进行初始化，ViewPager 中第一个界面的 setUserVisibleHint 在 onCreateView 之前调用

    protected View mRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(PARAM_TITLE);
        }

        LogUtils.e(mTitle + " onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // mRootView == null 这个不能注释掉 否则当 ViewPager 不再缓存当前 Fragment 时会被回收，
        // 然后再次声明周期重新来一遍，重新初始化界面，但由于已经进行过懒加载了，就不会再加载数据了，新的界面就不显示数据，那不就是 Bug 么..

        if (mRootView == null) {
            mRootView = inflater.inflate(getRootViewId(), container, false);
            initView();
            LogUtils.e(mTitle + " onCreateView");

            if (isNeedInit) { //需要初始化 加载数据
                lazyLoadData();
                isNeedInit = false;
            }
            isViewCreated = true;
        }

        return mRootView;
    }

    /**
     * 初始化布局
     */
    protected abstract void initView();

    //加载 布局
    protected abstract int getRootViewId();

    //这个方法由 ViewPager 自动调用，可以在这里实现懒加载，避免 ViewPager 的预加载机制
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        LogUtils.e("isVisibleToUser: " + isVisibleToUser);
        if (isVisibleToUser && !hasLoadOnce) {//已对用户可见、且没有加载过数据，进行下一步判断
            if (isViewCreated) { // 如果 onCreateView 已执行，控件已经初始化，可以加载数据
                lazyLoadData();
            }else { // setUserVisibleHint 在 onCreateView 之前执行，设置标记，在 onCreateView 中加载数据
                isNeedInit = true;
            }
        }
    }

    /**
     * 懒加载
     */
    private void lazyLoadData() {
        startLoadData();
        hasLoadOnce = true;
    }

    /**
     * 开始加载数据
     */
    protected abstract void startLoadData();

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.e(mTitle + " onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e(mTitle + " onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.e(mTitle + " onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.e(mTitle + " onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        LogUtils.e(mTitle + " onDestroyView");

        if (mRootView != null) { //移除 RootView ，之后会在 onCreateView 重新显示
            LogUtils.e(mTitle + " mRootView not null");
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
    }
}
