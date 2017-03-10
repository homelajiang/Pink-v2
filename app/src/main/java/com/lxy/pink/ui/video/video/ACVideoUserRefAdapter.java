package com.lxy.pink.ui.video.video;

import com.airbnb.epoxy.EpoxyAdapter;
import com.lxy.pink.data.model.acfun.ACUserContribute;
import com.lxy.pink.ui.video.models.ACLoadingModel;
import com.lxy.pink.ui.video.models.ACLoadingModel_;
import com.lxy.pink.ui.video.models.ACVLiteModel;
import com.lxy.pink.ui.video.models.ACVLiteModel_;
import com.lxy.pink.ui.video.models.ACVUserVModel;
import com.lxy.pink.ui.video.models.ACVUserVModel_;

import java.util.List;

/**
 * Created by homelajiang on 2017/3/9 0009.
 */

public class ACVideoUserRefAdapter extends EpoxyAdapter {


    public ACVideoUserRefAdapter(String avatar, int userId, String name,
                                 List<ACUserContribute.DataEntity.PageEntity.ListEntity> data) {
        ACVUserVModel acvUserVModel = new ACVUserVModel_()
                .userId(userId)
                .avatar(avatar)
                .name(name);
        ACLoadingModel_ loadingModel = new ACLoadingModel_();
        addModel(acvUserVModel);
        if (data == null) {
            addModel(loadingModel);
            return;
        }
        if (data.size() == 0)
            return;
        for (ACUserContribute.DataEntity.PageEntity.ListEntity e : data) {
            ACVLiteModel m = new ACVLiteModel_()
                    .contentId(e.getContentId())
                    .coverUrl(e.getCover())
                    .name(e.getTitle());
            addModel(m);
        }
    }
}
