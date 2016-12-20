package com.lxy.pink.ui.video;

import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

/**
 * Created by yuan on 2016/12/21.
 */

public class VideoContract {
    interface View extends BaseView<Presenter> {
        void recommendLoad(ACRecommend acRecommend);

        void recommendError(Throwable e);
    }

    interface Presenter extends BasePresenter {
        void getRecommend();
    }
}
