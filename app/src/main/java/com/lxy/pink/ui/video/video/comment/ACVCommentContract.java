package com.lxy.pink.ui.video.video.comment;

import com.lxy.pink.data.model.acfun.ACVideoCommentData;
import com.lxy.pink.data.model.acfun.ACVideoCommentRes;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

/**
 * Created by homelajiang on 2017/5/2 0002.
 */

public class ACVCommentContract {
    interface View extends BaseView<Presenter> {
        void getVideoCommentSuccess(ACVideoCommentData acVideoCommentData);

        void getVideoCommentFail(Throwable e);

        void sendCommentSuccess(ACVideoCommentRes res);

        void sendCommentFail(Throwable e);
    }

    interface Presenter extends BasePresenter {
        void getVideoComment(int contentId);

        void sendComment(String text, int quoteId, int contentId, String accessToken, int userId, String captcha);
    }
}
