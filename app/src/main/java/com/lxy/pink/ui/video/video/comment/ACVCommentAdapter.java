package com.lxy.pink.ui.video.video.comment;

import com.airbnb.epoxy.EpoxyAdapter;
import com.lxy.pink.ui.video.models.ACLoadingModel_;

/**
 * Created by homelajiang on 2017/5/3 0003.
 */

public class ACVCommentAdapter extends EpoxyAdapter {
    private final ACLoadingModel_ loadingModel;

    ACVCommentAdapter() {
        loadingModel = new ACLoadingModel_();
        addModel(loadingModel);
    }
}
