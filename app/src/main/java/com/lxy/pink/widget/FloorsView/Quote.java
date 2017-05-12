package com.lxy.pink.widget.FloorsView;

import com.lxy.pink.data.model.acfun.ACComment;

import java.util.List;

/**
 * Created by homelajiang on 2017/5/12 0012.
 */

public class Quote {
    public static final int MAX_DEPth = 5;
    public static final int QUOTE_TYPE_BODY = 2;
    public static final int QUOTE_TYPE_HEADER = 1;
    public static final int QUOTE_TYPE_TAIL = 3;
    private List<ACComment> quotedComments;
    private Status status = Status.NONE;

    public enum Status {
        NONE,
        LOADING,
        LOADED
    }

    public List<ACComment> getQuotedComments() {
        return quotedComments;
    }

    public void setQuotedComments(List<ACComment> quotedComments) {
        this.quotedComments = quotedComments;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static List<ACComment> prepareQuote(List<ACComment> list) {
        return null;
    }
}
