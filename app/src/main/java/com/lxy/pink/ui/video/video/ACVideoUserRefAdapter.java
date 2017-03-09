package com.lxy.pink.ui.video.video;

import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.data.model.acfun.ACVideoSearchLike;
import com.lxy.pink.ui.video.models.ACVListModel;
import com.lxy.pink.ui.video.models.ACVListModel_;
import com.lxy.pink.ui.video.models.ACVLiteModel;
import com.lxy.pink.ui.video.models.ACVLiteModel_;
import com.lxy.pink.ui.video.models.ACVUserVModel;
import com.lxy.pink.ui.video.models.ACVUserVModel_;

import java.util.List;

/**
 * Created by homelajiang on 2017/3/9 0009.
 */

public class ACVideoUserRefAdapter extends EpoxyAdapter {


    public ACVideoUserRefAdapter(String avatar, int userId, String name) {
        ACVUserVModel acvUserVModel = new ACVUserVModel_()
                .userId(userId)
                .avatar(avatar)
                .name(name);
        addModels(
                acvUserVModel
        );
    }

    public void setRecommendList(List<ACVideoSearchLike.DataEntity.PageEntity.ListEntity> recommendList) {
        if (recommendList == null)
            return;
        for (ACVideoSearchLike.DataEntity.PageEntity.ListEntity entity : recommendList) {
            ACVLiteModel liteModel = new ACVLiteModel_()
                    .contentId(entity.getContentId())
                    .coverUrl(entity.getTitleImg())
                    .name(entity.getTitle());
            addModel(liteModel);
        }
    }
}
