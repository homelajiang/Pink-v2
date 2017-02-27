package com.lxy.pink.ui.video;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lxy.pink.R;
import com.lxy.pink.RxBus;
import com.lxy.pink.data.model.acfun.ACAuth;
import com.lxy.pink.data.model.acfun.ACAuthRes;
import com.lxy.pink.data.model.acfun.ACBaseModel;
import com.lxy.pink.data.model.acfun.ACProfile;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.data.model.acfun.ACSign;
import com.lxy.pink.data.source.PreferenceManager;
import com.lxy.pink.event.ACLoginEvent;
import com.lxy.pink.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by yuan on 2016/10/18.
 */

public class VideoFragment extends BaseFragment implements VideoContract.View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private VideoContract.Presenter presenter;
    private VideoFragmentAdapter videoFragmentAdapter;
    private ProgressDialog pd;
    private ACLoginDialogFragment acLoginDialogFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recyclerview_with_refresh, container, false);
        ButterKnife.bind(this, root);

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.pink));
        mSwipeRefreshLayout.setOnRefreshListener(this);

        int spanCount = getSpanCount();
        videoFragmentAdapter = new VideoFragmentAdapter(getContext(), this);
        videoFragmentAdapter.setSpanCount(spanCount);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        gridLayoutManager.setSpanSizeLookup(videoFragmentAdapter.getSpanSizeLookup());

        mRecyclerView.addItemDecoration(new VerticalGridCardSpacingDecoration());
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(videoFragmentAdapter);
        pd = new ProgressDialog(getContext());
        pd.setMessage(getString(R.string.pink_waiting));
        pd.setCancelable(false);
        isPrepared = true;
        loadData();
        acLoginDialogFragment = new ACLoginDialogFragment()
                .setLoginListener(new ACLoginDialogFragment.AcLoginListener() {
                    @Override
                    public void login(String username, String password) {
                        presenter.login(username, password);
                    }
                });
        return root;
    }

    @Override
    protected void loadData() {
        if (!isPrepared || !isVisible || !isFirstInit) {
            return;
        }
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                new VideoFragmentPresenter(getContext(), VideoFragment.this).subscribe();
            }
        }, 500);
    }

    private int getSpanCount() {
//        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
//        return (int) (dpWidth / 100);
        return 6;
    }

    //登录或者签到
    public void loginOrSign() {

        ACAuth acAuth = PreferenceManager.getAcAuth(getContext());
        if (acAuth != null) {
            presenter.sign(acAuth.getAccess_token());
        } else {
            acLoginDialogFragment.show(getActivity().getSupportFragmentManager(), "login");
        }
    }


    @Override
    public void recommendLoaded(ACRecommend acRecommend) {
        isFirstInit = false;
        if (acRecommend == null) {
            Toast.makeText(getContext(), R.string.pink_error_indescribable, Toast.LENGTH_SHORT).show();
        } else if (acRecommend.getCode() == 200) {
            videoFragmentAdapter.setData(acRecommend);
        } else {
            if (TextUtils.isEmpty(acRecommend.getMessage())) {
                Toast.makeText(getContext(), R.string.pink_error_indescribable, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), acRecommend.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        stopRefresh();
        //获取数据完成后开始检查用户是否登录
        ACAuth acAuth = PreferenceManager.getAcAuth(getContext());
        if (acAuth != null)
            presenter.checkSign(String.valueOf(acAuth.getAccess_token()));
    }

    @Override
    public void recommendError(Throwable e) {
        isFirstInit = false;
    }

    @Override
    public void loginStart() {
        pd.show();
    }

    @Override
    public void loginLoaded(ACAuthRes acAuthRes) {
        pd.dismiss();
        if (acAuthRes.getStatus() == 200 && acAuthRes.isSuccess()) {
            ACAuth acAuth = acAuthRes.getData();
            if (acAuth == null) {
                return;
            }
            //存储acAuth
            PreferenceManager.setAcAuth(getContext(), acAuth);
            RxBus.getInstance().post(new ACLoginEvent());
            presenter.checkSign(String.valueOf(acAuth.getAccess_token()));
            acLoginDialogFragment.dismiss();
        } else {
            Toast.makeText(getContext(),
                    TextUtils.isEmpty(acAuthRes.getInfo()) ? getString(R.string.pink_error_indescribable) : acAuthRes.getInfo(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loginError(Throwable e) {
        pd.dismiss();
        Toast.makeText(getContext(), R.string.pink_error_network, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void acProfileLoaded(ACProfile acProfile) {
        if (acProfile.getStatus() != 200 || !acProfile.isSuccess())
            return;
        RxBus.getInstance().post(new ACLoginEvent(acProfile));
    }

    @Override
    public void checkSignLoaded(ACBaseModel signRes) {
        if (signRes.getCode() == 200) {
            boolean sign;
            try {
                sign = Boolean.parseBoolean(signRes.getData());
            } catch (Throwable e) {
                e.printStackTrace();
                return;
            }
            RxBus.getInstance().post(new ACLoginEvent(sign));
            ACAuth acAuth = PreferenceManager.getAcAuth(getContext());
            if (acAuth != null)
                //重新请求用户资料
                presenter.getProfile(String.valueOf(acAuth.getUserId()));
        } else {
            Toast.makeText(getContext(), signRes.getMessage(), Toast.LENGTH_SHORT).show();
            PreferenceManager.clearAcAuth(getContext());
            RxBus.getInstance().post(new ACLoginEvent());
        }
    }

    @Override
    public void signLoaded(ACSign acSign) {
        ACAuth acAuth = PreferenceManager.getAcAuth(getContext());
        if (acSign.getCode() == 200) {
            Toast.makeText(getContext(), acSign.getData().getMsg(), Toast.LENGTH_SHORT).show();
            //通知签到成功，使签到按钮不可用
            RxBus.getInstance().post(new ACLoginEvent(true));
            if (acAuth != null)
                //重新请求用户资料
                presenter.getProfile(String.valueOf(acAuth.getUserId()));
        } else {
            Toast.makeText(getContext(),
                    TextUtils.isEmpty(acSign.getMessage()) ? getString(R.string.ac_sign_fail) : acSign.getMessage()
                    , Toast.LENGTH_SHORT).show();
        }

        if (acAuth != null)
            //重新检查签到状态
            presenter.sign(acAuth.getAccess_token());
    }

    @Override
    public void signError(Throwable e) {

    }

    public void stopRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void setPresenter(VideoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getRecommend();
            }
        }, 500);
    }

    @Override
    protected Subscription subscribeEvents() {
        return RxBus.getInstance().toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (o instanceof ACLoginEvent) {
                            updateAcUserInfo((ACLoginEvent) o);
                        }
                    }
                })
                .subscribe(RxBus.defaultSubscriber());
    }

    //更新用户信息
    private void updateAcUserInfo(ACLoginEvent event) {
        videoFragmentAdapter.updateLoginModel(event);
    }
}
