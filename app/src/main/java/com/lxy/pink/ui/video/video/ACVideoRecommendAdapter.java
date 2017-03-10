package com.lxy.pink.ui.video.video;

import com.airbnb.epoxy.EpoxyAdapter;
import com.lxy.pink.data.model.acfun.ACVideoSearchLike;
import com.lxy.pink.ui.video.models.ACLoadingModel;
import com.lxy.pink.ui.video.models.ACLoadingModel_;
import com.lxy.pink.ui.video.models.ACVLiteModel;
import com.lxy.pink.ui.video.models.ACVLiteModel_;

import java.util.List;

/**
 * Created by homelajiang on 2017/3/9 0009.
 */

public class ACVideoRecommendAdapter extends EpoxyAdapter {

    public ACVideoRecommendAdapter(List<ACVideoSearchLike.DataEntity.PageEntity.ListEntity> recommendList) {
        if (recommendList == null) {
            addModels(new ACLoadingModel_());
            return;
        }

        if (recommendList.size() == 0)
            return;
        for (ACVideoSearchLike.DataEntity.PageEntity.ListEntity entity : recommendList) {
            ACVLiteModel liteModel = new ACVLiteModel_()
                    .contentId(Integer.parseInt(entity.getContentId().replace("ac", "")))
                    .coverUrl(entity.getTitleImg())
                    .name(entity.getTitle());
            addModel(liteModel);
        }
    }
}
