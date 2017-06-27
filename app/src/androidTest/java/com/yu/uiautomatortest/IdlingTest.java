package com.yu.uiautomatortest;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jakewharton.espresso.OkHttp3IdlingResource;
import com.yu.uiautomatortest.idling.SleepIdlingResource;
import com.yu.uiautomatortest.module.login.LoginActivity;
import com.yu.uiautomatortest.module.main.activity.MainActivity;
import com.yu.uiautomatortest.util.LogUtils;
import com.yu.uiautomatortest.util.RetrofitUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Administrator on 2017-6-26.
 * 异步测试
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class IdlingTest {

    private String name;
    private String passwd;

    private ArrayList<Integer> menuIdList;

    @Rule
    public ActivityTestRule<LoginActivity> mActivity = new ActivityTestRule<>(LoginActivity.class);
/*

    @Rule
    public ActivityTestRule<MainActivity> mMainActivity = new ActivityTestRule<>(MainActivity.class);
*/

    @Before
    public void initData(){
        //登录输入参数
        name = "yu";
        passwd = "001002";

        //底部导航栏
        menuIdList = new ArrayList<>();
        menuIdList.add(R.id.action_news); // 消息按钮 id
        menuIdList.add(R.id.action_discover); // 发现按钮 id
        menuIdList.add(R.id.action_me); // 我按钮 id

    }

    @Test
    public void startTest(){
        // 先登录 进入首页
        checkLogin();
        // 从首页后 进入到 “发现” 界面
        checkBottom();
        // 检查异步操作
        checkMeizhi();

    }

    //先进行登录，进入到主界面
    public void checkLogin(){
        //输入用户名
        onView(withId(R.id.et_username)).perform(typeText(name));
        //输入密码
        onView(withId(R.id.et_password)).perform(typeText(passwd));
        //点击登录按钮
        onView(withId(R.id.btn_login)).perform(click());
    }

    //测试底部导航栏
    public void checkBottom(){
        closeSoftKeyboard();//隐藏虚拟键盘 否则会遮挡住底部导航栏而报错
        onView(withId(menuIdList.get(0))).perform(click());//测试 消息 界面
        onView(withId(menuIdList.get(2))).perform(click());//测试 我 界面
        onView(withId(menuIdList.get(1))).perform(click());//测试 发现 界面
    }

    public void checkMeizhi(){
        //根据 id 找到 ViewPager 页面,并判断是否可见
        ViewInteraction appCompatViewPager = onView(allOf(withId(R.id.vp_discover), isDisplayed()));

        //左滑到“福利”界面
        appCompatViewPager.perform(swipeLeft()).perform(swipeLeft());

        //sleep some time 之后再进行后面的验证
        IdlingResource waitingResource = new SleepIdlingResource(1000 *2);

        Espresso.registerIdlingResources(waitingResource);

        // 通过下面两句打印的日志可以看出 log 没有被阻塞 但是 onView 被阻塞了，
        // 如果没有onView 这句，不会阻塞， 那么之后的 RetrofitUtils.getOkHttpClient() 可能会报 client 空指针
        // 即使不是空指针，client 也不正确
        LogUtils.e("sleeping finish");
        onView(withId(menuIdList.get(1))).perform(click());//测试 发现 界面

        //---------------------------------------------------------------------------

        // 等待网络访问
        IdlingResource idlingResource = OkHttp3IdlingResource.create("idling_test", RetrofitUtils.getOkHttpClient());

        Espresso.registerIdlingResources(idlingResource);

        //异步加载结束,校验
        //onView(withId(menuIdList.get(0))).perform(click());//测试 消息 界面

        //下面两句都会在 上面的 onView 之后调用
        LogUtils.e("idling_test finish");
        onView(allOf(withId(R.id.tv_result), isDisplayed())).check(matches(withText("true")));


        Espresso.unregisterIdlingResources(idlingResource);

        Espresso.unregisterIdlingResources(waitingResource);
    }

    /*public void checkMeizhi(){

        //根据 id 找到 ViewPager 页面,并判断是否可见
        ViewInteraction appCompatViewPager = onView(allOf(withId(R.id.vp_discover), isDisplayed()));
        //左滑到“福利”界面
        appCompatViewPager.perform(swipeLeft()).perform(swipeLeft());

        //设置异步超时时间
        IdlingPolicies.setMasterPolicyTimeout(1000*10, TimeUnit.MILLISECONDS);
        IdlingPolicies.setIdlingResourceTimeout(1000*10, TimeUnit.MILLISECONDS);

        TextView tvResult = (TextView) mMainActivity.getActivity().findViewById(R.id.tv_result);
        IdlingResource idlingResource = new RecyclerAdaterIdlingResource(tvResult);

        //等待异步操作
        Espresso.registerIdlingResources(idlingResource);

        onView(withId(R.id.tv_result)).check(matches(withText(tvResult.getText().toString()))).withFailureHandler(new FailureHandler() {
            @Override
            public void handle(Throwable error, Matcher<View> viewMatcher) {

            }
        });

        //释放异步
        Espresso.unregisterIdlingResources(idlingResource);

        pressBack();

    }*/
}
