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
import com.lxy.pink.ui.service.PinkService;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by yuan on 2016/10/18.
 */
public class HomeFragment extends BaseFragment implements ServiceConnection {

    public static final String TAG = "HomeFragment";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public boolean serviceConnected;

    private View root;
    private PinkService.PinkBinder pinkBinder;
    private HomeAdapter homeAdapter;
    private String cityId;
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

    /**
     * ★ burst link ★
     */
    private void burstLink() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CALENDAR)) {
            //TODO
            Toast.makeText(getContext(), "calendar", Toast.LENGTH_SHORT).show();
            requestPermissions(PERMISSION_ALL, PERMISSION_CODE_ALL);

        } else if (shouldShowRequestPermissionRationale( Manifest.permission.WRITE_CALENDAR)) {
            //TODO
            Toast.makeText(getContext(), "calendar", Toast.LENGTH_SHORT).show();
            requestPermissions(PERMISSION_ALL, PERMISSION_CODE_ALL);
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
            //TODO
            Toast.makeText(getContext(), "location", Toast.LENGTH_SHORT).show();
            requestPermissions(PERMISSION_ALL, PERMISSION_CODE_ALL);
        } else {
            requestPermissions(PERMISSION_ALL, PERMISSION_CODE_ALL);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pinkBinder.getTodoList();
        }
//        for (int permission : grantResults) {
//
//            if (permission.equals(Manifest.permission.READ_CALENDAR)) {
//                pinkBinder.getTodoList();
//            }
//            if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
//                pinkBinder.getWeather();
//            }
//        }
        Toast.makeText(getContext(), grantResults.length + "", Toast.LENGTH_SHORT).show();
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
        if (serviceConnected) {
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
    public void onDestroy() {
        super.onDestroy();
        homeAdapter.stopClock();
    }
}
