package com.yu.uiautomatortest;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.yu.uiautomatortest.module.login.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Administrator on 2017-6-19.
 * 主界面测试
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {
    private String name;
    private String passwd;

    private ArrayList<Integer> menuIdList;
    private ArrayList<String> menuTitleList;

    @Rule
    public ActivityTestRule<LoginActivity> mActivity = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Before
    public void initData(){
        System.out.print("----------------- start -------------------" );
        name = "yu";
        passwd = "123456789";

        //底部导航栏
        menuIdList = new ArrayList<>();
        menuIdList.add(R.id.action_news);
        menuIdList.add(R.id.action_discover);
        menuIdList.add(R.id.action_me);
        menuTitleList = new ArrayList<>();
        menuTitleList.add("消息");
        menuTitleList.add("发现");
        menuTitleList.add("我");
    }

    @Test
    public void startCheck(){
        checkLogin();
        checkBottomMenu();
    }

    //测试登录
    public void checkLogin(){
        //输入用户名
        onView(withId(R.id.et_username)).perform(typeText(name));
        //输入密码
        onView(withId(R.id.et_password)).perform(typeText(passwd));
        //点击登录按钮
        onView(withId(R.id.btn_login)).perform(click());
    }

    //测试底部导航栏
    public void checkBottomMenu() {
        for (int i=0; i<=8; i++) {
            int index = (int) (Math.random() * 100 %3);
            onView(withId(menuIdList.get(index))).perform(click());
            onView(withId(R.id.tv_select)).check(matches(withText(menuTitleList.get(index))));
        }
    }


}
