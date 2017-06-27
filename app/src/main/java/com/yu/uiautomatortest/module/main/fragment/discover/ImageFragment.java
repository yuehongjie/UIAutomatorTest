package com.yu.uiautomatortest.module.main.fragment.discover;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yu.uiautomatortest.R;
import com.yu.uiautomatortest.app.BaseLazyLoadFragment;
import com.yu.uiautomatortest.module.main.api.MainApi;
import com.yu.uiautomatortest.module.main.bean.MeiziEntity;
import com.yu.uiautomatortest.util.LogUtils;
import com.yu.uiautomatortest.util.RetrofitUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * 图片类的界面
 */
public class ImageFragment extends BaseLazyLoadFragment {

    private RecyclerView mRecyclerView;

    private int mPage = 1;
    private MainApi mainApi;
    private TextView tvResult;

    public ImageFragment() {
        // 空的构造函数
    }

    public static ImageFragment newInstance(String title) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_image;
    }

    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.rv_content);
        tvResult = (TextView) mRootView.findViewById(R.id.tv_result);
    }

    protected void startLoadData() {
        LogUtils.e(mTitle + " 进行数据的懒加载 ");

        Retrofit retrofit = RetrofitUtils.getRetrofit();
        mainApi = retrofit.create(MainApi.class);

        load();
    }

    private void load() {

        Observable<MeiziEntity> observable = mainApi.getMeizhiData(10, mPage);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Consumer<MeiziEntity>() {
                    @Override
                    public void accept(@NonNull MeiziEntity meiziEntity) throws Exception {
                        LogUtils.d(meiziEntity);
                        if (!meiziEntity.error) {
                           /* try {
                                Thread.sleep(4000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }*/

                            tvResult.setText("true");
                        }else {
                            tvResult.setText("false");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                        tvResult.setText("false");

                        LogUtils.e("error: " + throwable.getMessage());
                       throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.d("complete");
                    }
                });
    }


}
