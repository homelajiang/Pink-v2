package com.lxy.pink.ui.video.video;

import com.lxy.pink.data.model.acfun.ACActionFollow;
import com.lxy.pink.data.model.acfun.ACBananaCheck;
import com.lxy.pink.data.model.acfun.ACBananaInfo;
import com.lxy.pink.data.model.acfun.ACBananaPostRes;
import com.lxy.pink.data.model.acfun.ACCheckFollow;
import com.lxy.pink.data.model.acfun.ACUserContribute;
import com.lxy.pink.data.model.acfun.ACVideoCommentData;
import com.lxy.pink.data.model.acfun.ACVideoCommentRes;
import com.lxy.pink.data.model.acfun.ACVideoInfo;
import com.lxy.pink.data.model.acfun.ACVideoMark;
import com.lxy.pink.data.model.acfun.ACVideoSearchLike;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

/**
 * Created by homelajiang on 2017/3/1 0001.
 */

public class ACVideoContract {
    interface View extends BaseView<Presenter> {
        void getVideoInfoSuccess(ACVideoInfo info);

        void getVideoInfoFail(Throwable e);

        void checkFollowSuccess(ACCheckFollow acCheckFollow);

        void checkFollowFail(Throwable e);

        void actionFollowSuccess(ACActionFollow acActionFollow);

        void actionFollowFail(Throwable e);

        void checkMarkSuccess(ACVideoMark acVideoMark);

        void checkMarkFail(Throwable e);

        void actionMarkSuccess(ACVideoMark acVideoMark);

        void actionMarkFail(Throwable e);

        void getBananaInfoSuccess(ACBananaInfo acBananaInfo);

        void getBananaInfoFail(Throwable e);

        void checkBananaSuccess(ACBananaCheck acBananaCheck);

        void checkBananaFail(Throwable e);

        void sendBananaSuccess(ACBananaPostRes res);

        void sendBananaFail(Throwable e);

        void getDanmukuSuccess(String danmuku);

        void getDanmukuFail(Throwable e);

        void getVideoCommentSuccess(ACVideoCommentData acVideoCommentData);

        void getVideoCommentFail(Throwable e);

        void sendCommentSuccess(ACVideoCommentRes res);

        void sendCommentFail(Throwable e);

        void getVideoRecommendSuccess(ACVideoSearchLike like);

        void getVideoRecommendFail(Throwable e);

        void getUserContributeVideoSuccess(ACUserContribute contribute);

        void getUserContributeVideoFail(Throwable e);
    }

    interface Presenter extends BasePresenter {
        void getVideoInfo(int contentId);

        void checkFollow(int userId, String access_token);

        void actionFollow(String name, int userId, String accessToken);

        void checkMark(int contentId, int userId, String accessToken);

        void actionMark(String name, int userId, int contentId, String accessToken);

        void getBananaInfo(String accessToken);

        void getBananaCheck(String accessToken);

        void sendBanana(String accessToken, int userId, int count, int contentId);

        void getDanmuku(int danmukuId);

        void getVideoComment(int contentId);

        void sendComment(String text, int quoteId, int contentId, String accessToken, int userId, String captcha);

        void getVideoRecommend(String id);

        void getUserContributeVideo(int userId);
    }
}
