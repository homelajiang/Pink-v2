package com.lxy.pink.ui.video.comment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.airbnb.epoxy.EpoxyAdapter;
import com.lxy.pink.data.model.acfun.ACComment;
import com.lxy.pink.ui.video.models.ACCommentModel;
import com.lxy.pink.ui.video.models.ACCommentModel_;
import com.lxy.pink.ui.video.models.ACLoadingModel_;
import com.lxy.pink.widget.FloorsView.CommentFloor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by homelajiang on 2017/5/3 0003.
 */

public class ACCommentAdapter extends EpoxyAdapter {
    private final ACLoadingModel_ loadingModel;
    private final Context context;
    Map<String, ACComment> CommentMap = new HashMap<>();
    private List<Integer> IndexList = new ArrayList<>();

    private List<CommentFloor> commentFloorList = new ArrayList<>();

    public ACCommentAdapter(Context context) {
        this.context = context;
        loadingModel = new ACLoadingModel_();
        addModel(loadingModel);
    }

    public void addData(List<Integer> idList, Map<String, ACComment> videoCommentMap) {
        if (loadingModel.isShown()) {
            loadingModel.hide();
        }
        IndexList.addAll(idList);
        CommentMap.putAll(videoCommentMap);
        Set<Integer> floorSet = new HashSet<>();

        for (int index : idList) {
//            ArrayList<ACComment> comments = new ArrayList<>();
            ACComment lastComment = CommentMap.get("c" + index);
            List<ACComment> acCommentList1 = new ArrayList<>();
            List<ACComment> acCommentList2 = new ArrayList<>();

            int quoteId = lastComment.getQuoteId();

            for (ACComment comment; quoteId > 0; quoteId = comment.getQuoteId()) {
                comment = CommentMap.get("c" + quoteId);
//                comments.add(CommentMap.get("c" + quoteId));
                if (floorSet.contains(Integer.valueOf(comment.getFloor()))) {
                    acCommentList2.add(comment);
                } else {
                    acCommentList1.add(comment);
                    floorSet.add(Integer.valueOf(comment.getFloor()));
                }
            }
            CommentFloor commentFloor = new CommentFloor();
            commentFloor.setAcComment(lastComment);
            commentFloor.setDuplicateQuote(acCommentList2);
            commentFloor.setNoDuplicateQuote(acCommentList1);
            if (acCommentList1.size() == 0) {
                commentFloor.setUnduplicatedQuoteVisible(false);
            }
            if (acCommentList2.size() == 0) {
                commentFloor.setDuplicateQuoteVisible(false);
            }

            ACCommentModel commentModel = new ACCommentModel_()
                    .commentFloor(commentFloor);
            addModel(commentModel);
        }
    }

}
