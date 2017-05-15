package com.lxy.pink.ui.video.detail;

import com.airbnb.epoxy.EpoxyAdapter;
import com.lxy.pink.data.model.acfun.ACVideoSearchLike;
import com.lxy.pink.ui.video.models.ACBananaVideoModel;
import com.lxy.pink.ui.video.models.ACBananaVideoModel_;
import com.lxy.pink.ui.video.models.ACLoadingModel_;

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
            ACBananaVideoModel videoModel = new ACBananaVideoModel_()
                    .searchEntry(entity);
            addModel(videoModel);
        }
    }
}
