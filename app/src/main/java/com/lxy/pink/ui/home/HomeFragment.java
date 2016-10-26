package com.lxy.pink.ui.home;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.pink.R;
import com.lxy.pink.RxBus;
import com.lxy.pink.ui.base.BaseFragment;
import com.lxy.pink.ui.service.PinkService;

import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by yuan on 2016/10/18.
 */
@RuntimePermissions
public class HomeFragment extends BaseFragment implements ServiceConnection {

    public static final String TAG = "HomeFragment";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public boolean serviceConnected;
    private String cityId = "1886760";

    private View root;
    private PinkService.PinkBinder pinkBinder;
    private HomeAdapter homeAdapter;

    private static String[] READ_CALENDAR = new String[]{
            Manifest.permission.READ_CALENDAR
    };
    private int READ_CALENDAR_CODE = 0x011;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.recyclerview, container, false);
        ButterKnife.bind(this, root);
        initView();
        initData();
        return root;
    }

    private void initData() {
        Intent intent = new Intent(getContext(), PinkService.class);
        getActivity().bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    private void initView() {
        homeAdapter = new HomeAdapter(getContext());
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//        recyclerView.addItemDecoration();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(homeAdapter);
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
    public void onServiceConnected(ComponentName name, IBinder service) {
        serviceConnected = true;
        pinkBinder = (PinkService.PinkBinder) service;
        pinkBinder.getService().registerWeatherCallback(homeAdapter);

        pinkBinder.getWeatherInfo();
        HomeFragmentPermissionsDispatcher.getTodoInfoWithCheck(this);
    }

    @NeedsPermission(Manifest.permission.READ_CALENDAR)
    public void getTodoInfo() {
        if(pinkBinder!=null){
            pinkBinder.getTodoList();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        pinkBinder.getService().unRegisterWeatherCallback();
        serviceConnected = false;
        pinkBinder = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (serviceConnected)
            getActivity().unbindService(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        HomeFragmentPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }


}
