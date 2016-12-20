package com.lxy.pink.ui.video.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxy.pink.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/12/20.
 */

public class ACArticleViewH extends CardView {
    @BindView(R.id.from)
    TextView from;
    @BindView(R.id.comment_count)
    TextView commentCount;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @BindView(R.id.article_title)
    TextView articleTitle;
    @BindView(R.id.article_up)
    TextView articleUp;
    @BindView(R.id.article_view_count)
    TextView articleViewCount;

    public ACArticleViewH(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.ac_article_h_view, this);
        ButterKnife.bind(this);
    }
}
