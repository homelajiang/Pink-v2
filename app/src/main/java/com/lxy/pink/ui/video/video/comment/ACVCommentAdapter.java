package com.lxy.pink.ui.video.video.comment;

import com.airbnb.epoxy.EpoxyAdapter;
import com.lxy.pink.data.model.acfun.ACVideoComment;
import com.lxy.pink.ui.video.models.ACCommentModel;
import com.lxy.pink.ui.video.models.ACCommentModel_;
import com.lxy.pink.ui.video.models.ACLoadingModel_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by homelajiang on 2017/5/3 0003.
 */

public class ACVCommentAdapter extends EpoxyAdapter {
    private final ACLoadingModel_ loadingModel;
    Map<String, ACVideoComment> CommentMap = new HashMap<>();
    private List<Integer> IndexList = new ArrayList<>();

    ACVCommentAdapter() {
        loadingModel = new ACLoadingModel_();
        addModel(loadingModel);
    }

    public void addData(List<Integer> idList, Map<String, ACVideoComment> videoCommentMap) {
        if (loadingModel.isShown()) {
            loadingModel.hide();
        }
        IndexList.addAll(idList);
        CommentMap.putAll(videoCommentMap);

        for (int index : idList) {
            ArrayList<ACVideoComment> comments = new ArrayList<>();
            ACVideoComment lastComment = CommentMap.get("c" + index);
            int quoteId = lastComment.getQuoteId();

            for (ACVideoComment comment; quoteId > 0; quoteId = comment.getQuoteId()) {
                comment = CommentMap.get("c" + quoteId);
                comments.add(comment);
            }
            ACCommentModel commentModel = new ACCommentModel_()
                    .acVideoComment(lastComment)
                    .commentList(comments);
            addModel(commentModel);
        }
    }

}
