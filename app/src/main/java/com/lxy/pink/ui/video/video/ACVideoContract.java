package com.lxy.pink.ui.video.video;

import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

/**
 * Created by homelajiang on 2017/3/1 0001.
 */

public class ACVideoContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
        void getVideoInfo(int contentId);

        void checkFollow();

        void actionFollow();

        void checkMark();

        void actionMark();

        void getBananaInfo();

        void sendBanana();

        void getVideoUrl();

        void getDanmuku();

        void sendDanmu();

        void getVideoComment();

        void sendComment();

        void getVideoRecommend();
    }
}
