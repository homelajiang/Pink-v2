package com.lxy.pink.ui.video.detail;

import com.lxy.pink.data.model.acfun.ACVideoInfo;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

/**
 * Created by homelajiang on 2017/3/1 0001.
 */

public class ACVideoContract {
    interface View extends BaseView<Presenter> {
        void getVideoInfoSuccess(ACVideoInfo info);

        void getVideoInfoFail(Throwable e);

        void getDanmukuSuccess(String danmuku);

        void getDanmukuFail(Throwable e);
    }

    interface Presenter extends BasePresenter {
        void getVideoInfo(int contentId);

        void getDanmuku(int danmukuId);

    }
}
