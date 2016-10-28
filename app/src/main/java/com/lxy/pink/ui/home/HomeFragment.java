package com.lxy.pink.ui.home;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.pink.R;
import com.lxy.pink.RxBus;
import com.lxy.pink.data.source.PreferenceManager;
import com.lxy.pink.ui.base.BaseFragment;
import com.lxy.pink.ui.service.PinkService;
import com.lxy.pink.ui.service.PinkServiceContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
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

    private View root;
    private PinkService.PinkBinder pinkBinder;
    private HomeAdapter homeAdapter;
    private String cityId;

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
        StaggeredGridLayoutManager staggeredGridLayoutManager
                = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 20;
                outRect.right = 20;
                outRect.bottom = 20;
            }
        });
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

        HomeFragmentPermissionsDispatcher.getTodoInfoWithCheck(this);

        cityId = PreferenceManager.getCityId(getContext());

        if (!TextUtils.isEmpty(cityId)) {
            pinkBinder.getWeatherById(cityId);
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    HomeFragmentPermissionsDispatcher.getLocationWithCheck(HomeFragment.this);
                }
            }, 10000);
        } else {
            HomeFragmentPermissionsDispatcher.getLocationWithCheck(HomeFragment.this);
        }
    }

    @NeedsPermission(Manifest.permission.READ_CALENDAR)
    public void getTodoInfo() {
        if (pinkBinder != null) {
            pinkBinder.getTodoList();
        }
    }

    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    public void getLocation() {
        if (pinkBinder == null)
            return;
        pinkBinder.getWeatherByLocation();
    }


    @OnPermissionDenied(Manifest.permission.READ_CALENDAR)
    public void getTodoPermissionFail() {
        //TODO
    }

    @OnPermissionDenied({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    public void getLocationFail() {
        if (TextUtils.isEmpty(cityId)) {
            homeAdapter.weatherLocationFail();
        }
    }

    @OnShowRationale({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    public void showWhyLocation(final PermissionRequest request) {

    }

    @OnNeverAskAgain({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    public void neverAskLocation() {
        if (TextUtils.isEmpty(cityId)) {
            homeAdapter.weatherLocationFail();
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
        if (serviceConnected){
            getActivity().unbindService(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        HomeFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }


}
