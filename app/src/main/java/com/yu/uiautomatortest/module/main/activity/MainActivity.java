package com.yu.uiautomatortest.module.main.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yu.uiautomatortest.R;
import com.yu.uiautomatortest.module.main.fragment.DiscoverFragment;
import com.yu.uiautomatortest.module.main.fragment.MeFragment;
import com.yu.uiautomatortest.module.main.fragment.NewsFragment;
import com.yu.uiautomatortest.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_select)
    TextView tvSelect;
    @BindView(R.id.main_menu)
    BottomNavigationView bottomMainMenu;
    @BindView(R.id.fl_main)
    FrameLayout flMain;

    //三个主要界面
    NewsFragment newsFragment; // 消息界面
    DiscoverFragment discoverFragment; //发现界面
    MeFragment meFragment; //我界面

    //当前界面
    Fragment mCurrFragment;

    private static final String TAG = "MainActivity";
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    /**
     * 初始化显示的内容
     */
    private void initView() {

        mFragmentManager = getSupportFragmentManager();

        bottomMainMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_news: //消息
                        go2News();
                        break;
                    case R.id.action_discover: //发现
                        go2Discover();
                        break;
                    case R.id.action_me: // 我
                        go2Me();
                        break;
                }
                return true;
            }
        });

        //设置默认选中第一个
        bottomMainMenu.setSelectedItemId(R.id.action_news);
        //设置为选中状态
        bottomMainMenu.getMenu().getItem(0).setChecked(true);
    }

    /**
     * 切换到 我 界面
     */
    private void go2Me() {
        if (meFragment == null) {
            meFragment = MeFragment.newInstance("我");
        }

        addOrShowFragment(meFragment);
    }

    /**
     * 切换到 发现 界面
     */
    private void go2Discover() {
        if (discoverFragment == null) {
            discoverFragment = DiscoverFragment.newInstance("发现");
        }

        addOrShowFragment(discoverFragment);
    }

    /**
     * 切换到 消息 界面
     */
    private void go2News() {
        if (newsFragment == null) {
            newsFragment = NewsFragment.newInstance("消息");
        }

        addOrShowFragment(newsFragment);
    }

    /**
     * 添加或者显示 fragment
     * @param fragment 要显示或者添加的 fragment
     */
    private void addOrShowFragment(Fragment fragment) {

        if (mCurrFragment == null) { // 还没有添加过任何的 fragment
            mFragmentManager.beginTransaction().add(R.id.fl_main, fragment).commit();
            mCurrFragment = fragment;
            return;
        }

        // 刷新？ 应该不需要再这里做 可以在 Fragment 的 onHiddenChanged(boolean hidden) 方法中进行
        if (mCurrFragment == fragment) {
            LogUtils.d(TAG, "do refresh ?");
            return;
        }

        if (!fragment.isAdded()) {// 如果 fragment 未被添加，则添加到 Fragment 管理器中
            mFragmentManager.beginTransaction().hide(mCurrFragment).add(R.id.fl_main, fragment).commit();
        }else {// 如果 fragment 已添加则显示
            mFragmentManager.beginTransaction().hide(mCurrFragment).show(fragment).commit();
        }

        mCurrFragment = fragment;
    }


}
