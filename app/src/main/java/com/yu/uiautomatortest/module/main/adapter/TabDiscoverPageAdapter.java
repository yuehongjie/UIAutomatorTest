package com.yu.uiautomatortest.module.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yu.uiautomatortest.app.BaseLazyLoadFragment;
import com.yu.uiautomatortest.module.main.fragment.discover.ArticleFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-6-23.
 * 发现页的 子界面 适配器
 */

public class TabDiscoverPageAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> mTabTitleList;//页面标题
    private ArrayList<BaseLazyLoadFragment> mTabFragmentList; //发现详情页列表

    public TabDiscoverPageAdapter(FragmentManager fm, ArrayList<String> tabTitleList, ArrayList<BaseLazyLoadFragment> tabFragmentList) {
        super(fm);
        this.mTabFragmentList = tabFragmentList;
        this.mTabTitleList = tabTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return mTabFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mTabTitleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitleList.get(position);
    }
}
