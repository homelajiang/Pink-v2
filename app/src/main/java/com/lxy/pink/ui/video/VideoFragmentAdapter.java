package com.lxy.pink.ui.video;

import com.airbnb.epoxy.EpoxyAdapter;
import com.lxy.pink.ui.video.models.ACLoginModel;
import com.lxy.pink.ui.video.models.ACLoginModel_;
import com.lxy.pink.ui.video.models.ACVideoModel;
import com.lxy.pink.ui.video.models.ACVideoModel_;

/**
 * Created by homelajiang on 2016/12/20 0020.
 */

public class VideoFragmentAdapter extends EpoxyAdapter {

    VideoFragmentAdapter(){
        enableDiffing();
        ACLoginModel acLoginModel = new ACLoginModel_();
        ACVideoModel acVideoModel = new ACVideoModel_();
        addModels(acLoginModel,acVideoModel);
    }
}
