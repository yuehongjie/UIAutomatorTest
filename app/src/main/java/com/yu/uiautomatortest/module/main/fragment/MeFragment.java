package com.yu.uiautomatortest.module.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yu.uiautomatortest.R;
import com.yu.uiautomatortest.util.LogUtils;

/**
 * 消息界面
 */
public class MeFragment extends Fragment {

    private static final String ARG_TITLE = "param_title";

    private String mTitle;

    public MeFragment() {}

    /**
     * @param title 界面名字
     * @return NewsFragment 实例.
     */

    public static MeFragment newInstance(String title) {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
        }

        LogUtils.e("onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View meView = inflater.inflate(R.layout.fragment_me, container, false);
        initView(meView);
        LogUtils.e("onCreateView");
        return meView;
    }

    private void initView(View meView) {
        TextView tvMe = (TextView) meView.findViewById(R.id.tv_me);
        tvMe.setText(mTitle);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.e("onAttach");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.e("onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.e("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e("onResume");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.e("onSaveInstanceState");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.e("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.e("onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.e("onDetach");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtils.e("onHiddenChanged: " + hidden);
    }
}
