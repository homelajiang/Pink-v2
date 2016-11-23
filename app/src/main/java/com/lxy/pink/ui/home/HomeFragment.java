package com.lxy.pink.ui.home;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lxy.pink.R;
import com.lxy.pink.RxBus;
import com.lxy.pink.ui.base.BaseFragment;
import com.lxy.pink.ui.permission.FcPermissions;
import com.lxy.pink.ui.permission.FcPermissionsCallbacks;
import com.lxy.pink.ui.service.PinkService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by yuan on 2016/10/18.
 */
public class HomeFragment extends BaseFragment implements ServiceConnection, FcPermissionsCallbacks {

    public static final String TAG = "HomeFragment";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public boolean serviceConnected;

    private View root;
    private PinkService.PinkBinder pinkBinder;
    private HomeAdapter homeAdapter;
    private String[] PERMISSION_ALL = {
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private int PERMISSION_CODE_ALL = 3;

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

        burstLink();
    }
    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
    /**
     * ★ burst link ★
     */
    private void burstLink() {
        FcPermissions.requestPermissions(this, "开始获取权限", PERMISSION_CODE_ALL, PERMISSION_ALL);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        FcPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        homeAdapter.stopClock();
        if (serviceConnected) {
            pinkBinder.getService().unRegisterWeatherCallback();
            serviceConnected = false;
            pinkBinder = null;
            getActivity().unbindService(this);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        homeAdapter.startClock();
    }

    @Override
    public void onPause() {
        super.onPause();
        homeAdapter.stopClock();
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (perms.contains(Manifest.permission.READ_CALENDAR)) {
            if(pinkBinder!=null){
                pinkBinder.getTodoList();
            }
        }
        if (perms.contains(Manifest.permission.ACCESS_FINE_LOCATION)) {
            if(pinkBinder!=null){
                pinkBinder.getWeather();
            }
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (perms.contains(Manifest.permission.READ_CALENDAR)) {
            FcPermissions.checkDeniedPermissionsNeverAskAgain(this, "需要获取读取日历的权限"
                    , R.string.pink_setting, R.string.pink_cancel, null, perms);
        }
        if (perms.contains(Manifest.permission.ACCESS_FINE_LOCATION)) {
            FcPermissions.checkDeniedPermissionsNeverAskAgain(this, "需要获取地理位置的权限"
                    , R.string.pink_setting, R.string.pink_cancel, null, perms);
        }
    }
}
