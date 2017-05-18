package com.lxy.pink.ui.video.detail;

import android.widget.Toast;

import com.airbnb.epoxy.EpoxyAdapter;
import com.lxy.pink.Injection;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACVideoInfo;
import com.lxy.pink.data.source.AppRepository;
import com.lxy.pink.ui.video.models.ACVActionModel;
import com.lxy.pink.ui.video.models.ACVActionModel_;
import com.lxy.pink.ui.video.models.ACVHeaderModel;
import com.lxy.pink.ui.video.models.ACVHeaderModel_;
import com.lxy.pink.ui.video.models.ACVInfoModel;
import com.lxy.pink.ui.video.models.ACVInfoModel_;
import com.lxy.pink.ui.video.models.ACVListModel_;
import com.lxy.pink.ui.video.models.ACVUserHModel_;
import com.lxy.pink.ui.video.models.ACVUserListModel_;
import com.lxy.pink.utils.schedulers.SchedulerProvider;

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
    private ACVListModel_ recommendListModel;

    ACVideoAdapter(ACVideoActivity activity, int contentId) {
        this.context = activity;
        this.contentId = contentId;
        new ACVideoPresenter(this, AppRepository.getInstance(), Injection.provideSchedulerProvider(), contentId).subscribe();
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
                .title("标签相关");
        upListModel = new ACVUserListModel_()
                .avatar(videoInfo.getOwner().getAvatar())
                .userId(videoInfo.getOwner().getId())
                .name(videoInfo.getOwner().getName());
        ACVHeaderModel recommendTitle = new ACVHeaderModel_()
                .title("相关推荐");
        recommendListModel = new ACVListModel_();

        addModels(
                acvInfoModel,
                acvUserHModel,
                acvActionModel,
                tagsModel,
                upListModel,
                recommendTitle,
                recommendListModel
        );
    }

    @Override
    public void getVideoInfoFail(Throwable e) {
        Toast.makeText(context, R.string.pink_error_network, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDanmukuSuccess(String danmuku) {

    }

    @Override
    public void getDanmukuFail(Throwable e) {

    }

    public void onDestroy() {
        presenter.unSubscribe();
    }
}
