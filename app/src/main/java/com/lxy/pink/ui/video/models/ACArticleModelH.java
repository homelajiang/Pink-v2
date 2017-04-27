package com.lxy.pink.ui.video.models;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.ui.video.VideoFragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/12/20.
 */
@EpoxyModelClass(layout = R.layout.ac_article_h_view)
public abstract class ACArticleModelH extends EpoxyModelWithHolder<ACArticleModelH.ACArticleViewHHolder> {
    @EpoxyAttribute
    ACRecommend.DataBean.ContentsBean contentBean;
    @EpoxyAttribute
    VideoFragmentAdapter.ACItemClickListener<ACRecommend.DataBean.ContentsBean> acItemClickListener;

    @Override
    public void bind(ACArticleViewHHolder viewHolder) {
        viewHolder.setCommentCount(contentBean.getVisit().getComments());
        viewHolder.setArticleTitle(contentBean.getTitle());
        viewHolder.setArticleUp(contentBean.getOwner().getName());
        viewHolder.setArticleViewCount(contentBean.getVisit().getViews());
        String from = "";
        switch (contentBean.getChannelId()) {
            case 73:
                from = "工作·情感";
                break;
            case 74:
                from = "动漫文化";
                break;
            case 75:
                from = "漫画·小说";
                break;
            case 110:
                from = "综合";
                break;
            case 164:
                from = "游戏";
                break;
            default:
                from = "其他";
                break;

        }
        viewHolder.setFrom(from);

        if (acItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acItemClickListener.onItemClicked(v, contentBean);
                }
            });
        }
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    static class ACArticleViewHHolder extends EpoxyHolder {
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
        private View itemView;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }

        public void setFrom(String from) {
            this.from.setText(from);
        }

        public void setCommentCount(int commentCount) {
            this.commentCount.setText(String.valueOf(commentCount));
        }

        public void setArticleTitle(String articleTitle) {
            this.articleTitle.setText(articleTitle);
        }

        public void setArticleUp(String articleUp) {
            this.articleUp.setText(articleUp);
        }

        public void setArticleViewCount(int articleViewCount) {
            this.articleViewCount.setText(String.valueOf(articleViewCount));
        }
    }
}
