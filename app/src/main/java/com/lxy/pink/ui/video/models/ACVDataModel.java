package com.lxy.pink.ui.video.models;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACVideoInfo;
import com.lxy.pink.ui.video.views.ACVDataView;

/**
 * Created by homelajiang on 2017/2/28 0028.
 */

@EpoxyModelClass(layout = R.layout.ac_video_info_data_model)
public abstract class ACVDataModel extends EpoxyModel<ACVDataView> {
@EpoxyAttribute
    ACVideoInfo.DataBean.VisitBean visitBean;

    @Override
    public void bind(ACVDataView view) {
        view.setVisit(visitBean);
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
