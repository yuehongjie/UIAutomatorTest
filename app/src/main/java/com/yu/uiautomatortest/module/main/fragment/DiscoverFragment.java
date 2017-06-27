package com.yu.uiautomatortest.module.main.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yu.uiautomatortest.R;
import com.yu.uiautomatortest.app.BaseLazyLoadFragment;
import com.yu.uiautomatortest.module.main.adapter.TabDiscoverPageAdapter;
import com.yu.uiautomatortest.module.main.fragment.discover.ArticleFragment;
import com.yu.uiautomatortest.module.main.fragment.discover.ImageFragment;
import com.yu.uiautomatortest.util.LogUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 发现界面
 */
public class DiscoverFragment extends Fragment {

    private static final String ARG_TITLE = "param_title";

    @BindView(R.id.tab_discover)
    TabLayout mTabLayout;
    @BindView(R.id.vp_discover)
    ViewPager mViewPager;
    Unbinder unbinder;

    private ArrayList<String> mTabTitleList;
    private ArrayList<BaseLazyLoadFragment> mTabFragmentList;
    private TabDiscoverPageAdapter mAdapter;

    public DiscoverFragment() {
    }

    /**
     * @param title 界面名字
     * @return DiscoverFragment 实例.
     */

    public static DiscoverFragment newInstance(String title) {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String title = getArguments().getString(ARG_TITLE);
            LogUtils.e("create " + title);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View discoverView = inflater.inflate(R.layout.fragment_discover, container, false);
        unbinder = ButterKnife.bind(this, discoverView);

        init();

        return discoverView;
    }

    private void init() {
        if (mTabTitleList == null || mTabFragmentList == null) {
            mTabTitleList = new ArrayList<>();
            mTabFragmentList = new ArrayList<>();

            mTabTitleList.add("Android");
            mTabFragmentList.add(ArticleFragment.newInstance("Android"));

            mTabTitleList.add("IOS");
            mTabFragmentList.add(ArticleFragment.newInstance("IOS"));

            mTabTitleList.add("福利");
            mTabFragmentList.add(ImageFragment.newInstance("福利"));

        }

        if (mAdapter == null) {
            mAdapter = new TabDiscoverPageAdapter(getChildFragmentManager(), mTabTitleList, mTabFragmentList);
            mViewPager.setAdapter(mAdapter);
            //mViewPager.setOffscreenPageLimit(3);//缓存3个界面  但，如果界面很多怎么办...  所以 http://www.cnblogs.com/itpepe/p/4932540.html 方案 3 更好些
            mTabLayout.setupWithViewPager(mViewPager);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
