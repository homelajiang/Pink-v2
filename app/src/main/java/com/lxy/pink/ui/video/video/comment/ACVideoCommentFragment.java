package com.lxy.pink.ui.video.video.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.pink.data.model.acfun.ACVideoCommentData;
import com.lxy.pink.data.model.acfun.ACVideoCommentRes;
import com.lxy.pink.ui.base.BaseFragment;

/**
 * Created by homelajiang on 2017/5/2 0002.
 */

public class ACVideoCommentFragment extends BaseFragment implements ACVCommentContract.View{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setPresenter(ACVCommentContract.Presenter presenter) {

    }

    @Override
    public void getVideoCommentSuccess(ACVideoCommentData acVideoCommentData) {

    }

    @Override
    public void getVideoCommentFail(Throwable e) {

    }

    @Override
    public void sendCommentSuccess(ACVideoCommentRes res) {

    }

    @Override
    public void sendCommentFail(Throwable e) {

    }
}
