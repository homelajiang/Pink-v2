package com.lxy.pink.ui.video.info;

import com.lxy.pink.data.model.acfun.ACActionFollow;
import com.lxy.pink.data.model.acfun.ACBananaCheck;
import com.lxy.pink.data.model.acfun.ACBananaInfo;
import com.lxy.pink.data.model.acfun.ACBananaPostRes;
import com.lxy.pink.data.model.acfun.ACCheckFollow;
import com.lxy.pink.data.model.acfun.ACUserContribute;
import com.lxy.pink.data.model.acfun.ACVideoMark;
import com.lxy.pink.data.model.acfun.ACVideoSearchLike;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

/**
 * Created by homelajiang on 2017/5/2 0002.
 */

public class ACVInfoContract {

    interface View extends BaseView<Presenter> {
        void checkFollowSuccess(ACCheckFollow acCheckFollow);

        void checkFollowFail(Throwable e);

        void followSuccess(ACActionFollow acActionFollow);

        void followFail(Throwable e);

        void checkMarkSuccess(ACVideoMark acVideoMark);

        void checkMarkFail(Throwable e);

        void markSuccess(ACVideoMark acVideoMark);

        void markFail(Throwable e);

        void getBananaInfoSuccess(ACBananaInfo acBananaInfo);

        void getBananaInfoFail(Throwable e);

        void checkBananaSuccess(ACBananaCheck acBananaCheck);

        void checkBananaFail(Throwable e);

        void sendBananaSuccess(ACBananaPostRes res);

        void sendBananaFail(Throwable e);

        void getVideoRecommendSuccess(ACVideoSearchLike like);

        void getVideoRecommendFail(Throwable e);

        void getUserContributeVideoSuccess(ACUserContribute contribute);

        void getUserContributeVideoFail(Throwable e);
    }

    interface Presenter extends BasePresenter {
        void checkFollow(int userId, String access_token);

        void follow(String name, int userId, String accessToken);

        void checkMark(int contentId, int userId, String accessToken);

        void mark(String name, int userId, int contentId, String accessToken);

        void getBananaInfo(String accessToken);

        void checkBanana(String accessToken);

        void sendBanana(String accessToken, int userId, int count, int contentId);

        void getVideoRecommend(String id);

        void getUserContributeVideo(int userId);
    }
}
