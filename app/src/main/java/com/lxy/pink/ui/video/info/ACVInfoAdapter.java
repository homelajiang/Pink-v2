package com.lxy.pink.ui.video.info;

import android.content.Context;

import com.airbnb.epoxy.EpoxyAdapter;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACActionFollow;
import com.lxy.pink.data.model.acfun.ACAuth;
import com.lxy.pink.data.model.acfun.ACBananaCheck;
import com.lxy.pink.data.model.acfun.ACBananaInfo;
import com.lxy.pink.data.model.acfun.ACBananaPostRes;
import com.lxy.pink.data.model.acfun.ACCheckFollow;
import com.lxy.pink.data.model.acfun.ACUserContribute;
import com.lxy.pink.data.model.acfun.ACVideoInfo;
import com.lxy.pink.data.model.acfun.ACVideoMark;
import com.lxy.pink.data.model.acfun.ACVideoSearchLike;
import com.lxy.pink.data.source.PreferenceManager;
import com.lxy.pink.ui.video.models.ACLoadingModel_;
import com.lxy.pink.ui.video.models.ACVActionModel;
import com.lxy.pink.ui.video.models.ACVActionModel_;
import com.lxy.pink.ui.video.models.ACVFlowModel;
import com.lxy.pink.ui.video.models.ACVFlowModel_;
import com.lxy.pink.ui.video.models.ACVHeaderModel;
import com.lxy.pink.ui.video.models.ACVHeaderModel_;
import com.lxy.pink.ui.video.models.ACVInfoModel;
import com.lxy.pink.ui.video.models.ACVInfoModel_;
import com.lxy.pink.ui.video.models.ACVListModel_;
import com.lxy.pink.ui.video.models.ACVPartModel;
import com.lxy.pink.ui.video.models.ACVPartModel_;
import com.lxy.pink.ui.video.models.ACVUserHModel_;
import com.lxy.pink.ui.video.models.ACVUserListModel_;

import java.util.ArrayList;

/**
 * Created by homelajiang on 2017/5/2 0002.
 */

public class ACVInfoAdapter extends EpoxyAdapter implements ACVInfoContract.View {
    private final ACLoadingModel_ loadingModel;
    private final Context context;
    private ACVInfoContract.Presenter presenter;
    private ACVUserHModel_ acvUserHModel;
    private ACVUserListModel_ upListModel;
    private ACVListModel_ recommendListModel;

    public ACVInfoAdapter(Context context) {
        this.context = context;
        loadingModel = new ACLoadingModel_();
        addModel(loadingModel);
    }


    @Override
    public void setPresenter(ACVInfoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void linkStart(ACVideoInfo.DataBean videoInfo) {
        removeModel(loadingModel);
        if (videoInfo == null)
            return;
        new ACVInfoPresenter(this, videoInfo.getContentId()).subscribe();
        ACVInfoModel acvInfoModel = new ACVInfoModel_()
                .videoInfo(videoInfo.getDescription())
                .title(videoInfo.getTitle())
                .videoPlayCount(String.valueOf(videoInfo.getVisit().getViews()))
                .videoDanmuCount(String.valueOf(videoInfo.getVisit().getDanmakuSize()));

        acvUserHModel = new ACVUserHModel_()
                .headIcon(videoInfo.getOwner().getAvatar())
                .userName(videoInfo.getOwner().getName())
                .publishTime(videoInfo.getReleaseDate());

        ACVActionModel acvActionModel = new ACVActionModel_();
        ACVHeaderModel tagsModel = new ACVHeaderModel_()
                .title(context.getString(R.string.refTags));
        upListModel = new ACVUserListModel_()
                .avatar(videoInfo.getOwner().getAvatar())
                .userId(videoInfo.getOwner().getId())
                .name(videoInfo.getOwner().getName());
        ACVHeaderModel recommendTitle = new ACVHeaderModel_()
                .title(context.getString(R.string.refRecommond));
        recommendListModel = new ACVListModel_();
        ACVFlowModel acvFlowModel = new ACVFlowModel_()
                .titles(videoInfo.getTags());
        addModels(
                acvInfoModel,
                acvActionModel
        );
        if (videoInfo.getVideos() != null && videoInfo.getVideos().size() > 1) {
            ACVHeaderModel partTitle = new ACVHeaderModel_()
                    .title(context.getString(R.string.part));
            ACVPartModel partModel = new ACVPartModel_()
                    .videoList(videoInfo.getVideos());
            addModels(partTitle,
                    partModel);
        }
        addModels(acvUserHModel,
                tagsModel,
                acvFlowModel,
                upListModel,
                recommendTitle,
                recommendListModel
        );
        presenter.getVideoRecommend("ac" + videoInfo.getContentId());
        presenter.getUserContributeVideo(videoInfo.getOwner().getId());
        ACAuth acAuth = PreferenceManager.getAcAuth(context);
        if (acAuth != null) {
            presenter.checkMark(videoInfo.getContentId(), videoInfo.getOwner().getId(), acAuth.getAccess_token());
            presenter.checkFollow(videoInfo.getOwner().getId(), acAuth.getAccess_token());
        }
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
    }

    @Override
    public void followSuccess(ACActionFollow acActionFollow) {
    }

    @Override
    public void followFail(Throwable e) {

    }

    @Override
    public void checkMarkSuccess(ACVideoMark acVideoMark) {
//        if (acVideoMark.getStatus() == 200 && acVideoMark.getData().isExist()) {
//            acvDataModel.marked(acVideoMark.getData().isExist() ? 1 : 0);
//        } else {
//            acvDataModel.marked(0);
//        }
//        notifyModelChanged(acvDataModel);
    }

    @Override
    public void checkMarkFail(Throwable e) {
//        acvDataModel.marked(0);
//        notifyModelChanged(acvDataModel);
    }

    @Override
    public void markSuccess(ACVideoMark acVideoMark) {

    }

    @Override
    public void markFail(Throwable e) {

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
