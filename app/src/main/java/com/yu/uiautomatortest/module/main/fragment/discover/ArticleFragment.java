package com.yu.uiautomatortest.module.main.fragment.discover;


import android.os.Bundle;
import android.widget.TextView;

import com.yu.uiautomatortest.R;
import com.yu.uiautomatortest.app.BaseLazyLoadFragment;
import com.yu.uiautomatortest.util.LogUtils;

/**
 * 文章类的界面
 */
public class ArticleFragment extends BaseLazyLoadFragment {

    private TextView tvTitle;

    public ArticleFragment() {
        // 空的构造函数
    }

    public static ArticleFragment newInstance(String param1) {
        ArticleFragment fragment = new ArticleFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_TITLE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_article;
    }

    @Override
    protected void initView() {
        tvTitle = (TextView) mRootView.findViewById(R.id.tv_tab_discover);
    }

    protected void startLoadData() {
        LogUtils.e(mTitle + " 进行数据的懒加载 ");
        tvTitle.setText("" + mTitle + " 已加载");
    }


}
