package com.lxy.pink.widget.FloorsView;

import com.lxy.pink.data.model.acfun.ACComment;

import java.util.List;

/**
 * Created by homelajiang on 2017/5/12 0012.
 */

public class CommentFloor {
    private ACComment acComment;
    private List<ACComment> duplicateQuote;
    private List<ACComment> noDuplicateQuote;
    private boolean unduplicatedQuoteVisible = true;
    private boolean duplicateQuoteVisible = true;

    private boolean unduplicatedQuoteExpand = true;
    private boolean duplicateQuoteExpand = false;

    public ACComment getAcComment() {
        return acComment;
    }

    public void setAcComment(ACComment acComment) {
        this.acComment = acComment;
    }

    public List<ACComment> getDuplicateQuote() {
        return duplicateQuote;
    }

    public void setDuplicateQuote(List<ACComment> duplicateQuote) {
        this.duplicateQuote = duplicateQuote;
    }

    public List<ACComment> getNoDuplicateQuote() {
        return noDuplicateQuote;
    }

    public void setNoDuplicateQuote(List<ACComment> noDuplicateQuote) {
        this.noDuplicateQuote = noDuplicateQuote;
    }

    public boolean isUnduplicatedQuoteVisible() {
        return unduplicatedQuoteVisible;
    }

    public void setUnduplicatedQuoteVisible(boolean unduplicatedQuoteVisible) {
        this.unduplicatedQuoteVisible = unduplicatedQuoteVisible;
    }

    public boolean isDuplicateQuoteVisible() {
        return duplicateQuoteVisible;
    }

    public void setDuplicateQuoteVisible(boolean duplicateQuoteVisible) {
        this.duplicateQuoteVisible = duplicateQuoteVisible;
    }

    public boolean isUnduplicatedQuoteExpand() {
        return unduplicatedQuoteExpand;
    }

    public void setUnduplicatedQuoteExpand(boolean unduplicatedQuoteExpand) {
        this.unduplicatedQuoteExpand = unduplicatedQuoteExpand;
    }

    public boolean isDuplicateQuoteExpand() {
        return duplicateQuoteExpand;
    }

    public void setDuplicateQuoteExpand(boolean duplicateQuoteExpand) {
        this.duplicateQuoteExpand = duplicateQuoteExpand;
    }
}
