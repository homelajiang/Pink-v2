package com.lxy.pink.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.pink.R;
import com.lxy.pink.RxBus;
import com.lxy.pink.data.model.weather.Weather;
import com.lxy.pink.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by yuan on 2016/10/18.
 */

public class HomeFragment extends BaseFragment implements WeatherContract.View {

    public static final String TAG = "HomeFragment";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private View root;
    private WeatherContract.Presenter weatherPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.recyclerview, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView() {



        new WeatherPresenter(this).subscribe();
    }

    @Override
    protected Subscription subscribeEvents() {
        return RxBus.getInstance().toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<Object>() {
                    @Override
                    public void call(Object o) {

                    }
                })
                .subscribe(RxBus.defaultSubscriber());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void handleLoadWeatherError(Throwable e) {

    }

    @Override
    public void weatherLoad(Weather weather) {

    }

    @Override
    public void setPresenter(WeatherContract.Presenter presenter) {
        this.weatherPresenter = presenter;
    }
}
