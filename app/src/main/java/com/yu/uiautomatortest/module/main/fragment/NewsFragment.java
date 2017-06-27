package com.yu.uiautomatortest.module.main.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yu.uiautomatortest.R;

/**
 * 消息界面
 */
public class NewsFragment extends Fragment {

    private static final String ARG_TITLE = "param_title";

    private String mTitle;

    public NewsFragment() {}

    /**
     * @param title 界面名字
     * @return NewsFragment 实例.
     */

    public static NewsFragment newInstance(String title) {
        NewsFragment fragment = new NewsFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View newsView = inflater.inflate(R.layout.fragment_news, container, false);
        initView(newsView);
        return newsView;
    }

    private void initView(View newsView) {
        TextView tvNews = (TextView) newsView.findViewById(R.id.tv_news);
        tvNews.setText(mTitle);
    }


}
