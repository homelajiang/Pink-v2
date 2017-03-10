package com.lxy.pink.ui.video.video;

import android.widget.Toast;

import com.airbnb.epoxy.EpoxyAdapter;
import com.facebook.common.internal.Throwables;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACActionFollow;
import com.lxy.pink.data.model.acfun.ACBananaCheck;
import com.lxy.pink.data.model.acfun.ACBananaInfo;
import com.lxy.pink.data.model.acfun.ACBananaPostRes;
import com.lxy.pink.data.model.acfun.ACCheckFollow;
import com.lxy.pink.data.model.acfun.ACUserContribute;
import com.lxy.pink.data.model.acfun.ACVideoCommentData;
import com.lxy.pink.data.model.acfun.ACVideoCommentRes;
import com.lxy.pink.data.model.acfun.ACVideoInfo;
import com.lxy.pink.data.model.acfun.ACVideoMark;
import com.lxy.pink.data.model.acfun.ACVideoSearchLike;
import com.lxy.pink.ui.video.models.ACVActionModel;
import com.lxy.pink.ui.video.models.ACVActionModel_;
import com.lxy.pink.ui.video.models.ACVDataModel;
import com.lxy.pink.ui.video.models.ACVDataModel_;
import com.lxy.pink.ui.video.models.ACVHeaderModel;
import com.lxy.pink.ui.video.models.ACVHeaderModel_;
import com.lxy.pink.ui.video.models.ACVIntroModel;
import com.lxy.pink.ui.video.models.ACVIntroModel_;
import com.lxy.pink.ui.video.models.ACVListModel;
import com.lxy.pink.ui.video.models.ACVListModel_;
import com.lxy.pink.ui.video.models.ACVTitleModel;
import com.lxy.pink.ui.video.models.ACVTitleModel_;
import com.lxy.pink.ui.video.models.ACVUserHModel;
import com.lxy.pink.ui.video.models.ACVUserHModel_;
import com.lxy.pink.ui.video.models.ACVUserListModel;
import com.lxy.pink.ui.video.models.ACVUserListModel_;
import com.lxy.pink.ui.video.models.ACVUserVModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by homelajiang on 2017/3/1 0001.
 */

public class ACVideoAdapter extends EpoxyAdapter implements ACVideoContract.View {

    private final ACVideoActivity context;
    private final int contentId;
    private ACVideoContract.Presenter presenter;
    private ACVideoInfo.DataBean videoInfo;
    private ACVUserHModel_ acvUserHModel;
    private ACVUserListModel_ upListModel;
    private ACVDataModel_ acvDataModel;
    private ACVListModel_ recommendListModel;

    ACVideoAdapter(ACVideoActivity activity, int contentId) {
        this.context = activity;
        this.contentId = contentId;
        new ACVideoPresenter(activity, this, contentId).subscribe();
    }

    @Override
    public void setPresenter(ACVideoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getVideoInfoSuccess(ACVideoInfo info) {
        if (info.getCode() != 200) {
            Toast.makeText(context, info.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
        videoInfo = info.getData();
        ACVTitleModel acvTitleModel = new ACVTitleModel_()
                .title(videoInfo.getTitle());
        acvUserHModel = new ACVUserHModel_()
                .headIcon(videoInfo.getOwner().getAvatar())
                .userName(videoInfo.getOwner().getName())
                .publishTime(videoInfo.getReleaseDate());
        acvDataModel = new ACVDataModel_()
                .visitBean(videoInfo.getVisit());
        ACVIntroModel acvIntroModel = new ACVIntroModel_()
                .introduction(videoInfo.getDescription());
        ACVActionModel acvActionModel = new ACVActionModel_();

        upListModel = new ACVUserListModel_()
                .avatar(videoInfo.getOwner().getAvatar())
                .userId(videoInfo.getOwner().getId())
                .name(videoInfo.getOwner().getName());
        ACVHeaderModel videoHeaderModel = new ACVHeaderModel_()
                .title("相关推荐");
        recommendListModel = new ACVListModel_();

        addModels(
                acvTitleModel,
                acvUserHModel,
                acvDataModel,
                acvIntroModel,
                acvActionModel,
                upListModel,
                videoHeaderModel,
                recommendListModel
        );
    }

    @Override
    public void getVideoInfoFail(Throwable e) {
        Toast.makeText(context, R.string.pink_error_network, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkFollowSuccess(ACCheckFollow acCheckFollow) {
        if (acCheckFollow.isSuccess()) {
            acvUserHModel.followed(acCheckFollow.isIsFollowing() ? 1 : 0);
        } else {
            acvUserHModel.followed(0);
        }
        notifyModelChanged(acvUserHModel);
    }

    @Override
    public void checkFollowFail(Throwable e) {
        acvUserHModel.followed(0);
        notifyModelChanged(acvUserHModel);
    }

    @Override
    public void actionFollowSuccess(ACActionFollow acActionFollow) {

    }

    @Override
    public void actionFollowFail(Throwable e) {

    }

    @Override
    public void checkMarkSuccess(ACVideoMark acVideoMark) {
        if (acVideoMark.getStatus() == 200 && acVideoMark.getData().isExist()) {
            acvDataModel.marked(acVideoMark.getData().isExist() ? 1 : 0);
        } else {
            acvDataModel.marked(0);
        }
        notifyModelChanged(acvDataModel);
    }

    @Override
    public void checkMarkFail(Throwable e) {
        acvDataModel.marked(0);
        notifyModelChanged(acvDataModel);
    }

    @Override
    public void actionMarkSuccess(ACVideoMark acVideoMark) {

    }

    @Override
    public void actionMarkFail(Throwable e) {

    }

    @Override
    public void getBananaInfoSuccess(ACBananaInfo acBananaInfo) {

    }

    @Override
    public void getBananaInfoFail(Throwable e) {

    }

    @Override
    public void checkBananaSuccess(ACBananaCheck acBananaCheck) {

    }

    @Override
    public void checkBananaFail(Throwable e) {

    }

    @Override
    public void sendBananaSuccess(ACBananaPostRes res) {

    }

    @Override
    public void sendBananaFail(Throwable e) {

    }

    @Override
    public void getDanmukuSuccess(String danmuku) {

    }

    @Override
    public void getDanmukuFail(Throwable e) {

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

    @Override
    public void getVideoRecommendSuccess(ACVideoSearchLike like) {
        if (like.getStatus() == 200) {
            recommendListModel.recommendList(like.getData().getPage().getList());
        } else {
            recommendListModel.recommendList(new ArrayList<ACVideoSearchLike.DataEntity.PageEntity.ListEntity>());
        }
        notifyModelChanged(recommendListModel);
    }

    @Override
    public void getVideoRecommendFail(Throwable e) {
        recommendListModel.recommendList(new ArrayList<ACVideoSearchLike.DataEntity.PageEntity.ListEntity>());
        notifyModelChanged(recommendListModel);
    }

    @Override
    public void getUserContributeVideoSuccess(ACUserContribute contribute) {
        if (contribute.getStatus() == 200) {
            upListModel.upContribuctionList(contribute.getData().getPage().getList());
        } else {
            upListModel.upContribuctionList(new ArrayList<ACUserContribute.DataEntity.PageEntity.ListEntity>());
        }
        notifyModelChanged(upListModel);
    }

    @Override
    public void getUserContributeVideoFail(Throwable e) {
        upListModel.upContribuctionList(new ArrayList<ACUserContribute.DataEntity.PageEntity.ListEntity>());
        notifyModelChanged(upListModel);
    }

    public void onDestroy() {
        presenter.unSubscribe();
    }
}
