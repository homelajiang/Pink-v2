package com.lxy.pink.ui.video.models;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACComment;
import com.lxy.pink.utils.EmojiParser;
import com.lxy.pink.utils.FrescoUtils;
import com.lxy.pink.utils.FuzzyDateFormatter;
import com.lxy.pink.utils.UBBUtil;
import com.lxy.pink.widget.FloorsView.CommentFloor;
import com.lxy.pink.widget.FloorsView.FloorsView;
import com.lxy.pink.widget.FloorsView.FrescoHtmlTextView;
import com.lxy.pink.widget.FloorsView.FrescoImageGetter;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

/**
 * Created by homelajiang on 2017/5/5 0005.
 */
@EpoxyModelClass(layout = R.layout.ac_comment)
public abstract class ACCommentModel extends EpoxyModelWithHolder<ACCommentModel.ACCommentModelHolder> {
    @EpoxyAttribute
    CommentFloor commentFloor;

    @Override
    public void bind(ACCommentModelHolder holder) {
        super.bind(holder);
        holder.setData(commentFloor);
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    static class ACCommentModelHolder extends EpoxyHolder {
        private View itemView;
        @BindView(R.id.duplicate_quote_floors_view)
        FloorsView mDuplicateQuoteFloorsView;
        @BindView(R.id.duplicate_quote_info)
        TextView mDuplicateQuoteInfo;
        @BindView(R.id.duplicate_quote_status)
        TextView mDuplicateQuoteStatus;
        @BindView(R.id.duplicate_quote_icon)
        AppCompatImageView mDuplicateQuoteIcon;
        @BindView(R.id.duplicate_quote_layout)
        LinearLayout mDuplicateQuoteLayout;
        @BindView(R.id.no_duplicate_quote_floors_view)
        FloorsView mNoDuplicateQuoteFloorsView;
        @BindView(R.id.no_duplicate_quote_info)
        TextView mNoDuplicateQuoteInfo;
        @BindView(R.id.no_duplicate_quote_status)
        TextView mNoDuplicateQuoteStatus;
        @BindView(R.id.no_duplicate_quote_icon)
        AppCompatImageView mNoDuplicateQuoteIcon;
        @BindView(R.id.no_duplicate_quote_layout)
        LinearLayout mNoDuplicateQuoteLayout;
        @BindView(R.id.simpleDraweeView)
        SimpleDraweeView mSimpleDraweeView;
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.publish_time)
        TextView mPublishTime;
        @BindView(R.id.floor)
        TextView mFloor;
        @BindView(R.id.content)
        FrescoHtmlTextView mContent;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }

        public void setData(CommentFloor commentFloor) {
            ACComment comment = commentFloor.getAcComment();
            mName.setText(comment.getUsername());
            int nameColor = comment.getNameRed() == 0 ? android.R.color.holo_red_dark : R.color.black;
            mName.setTextColor(ContextCompat.getColor(itemView.getContext(), nameColor));
            FrescoUtils.setImage(comment.getAvatar(), mSimpleDraweeView);
            FrescoImageGetter imageGetter = new FrescoImageGetter(itemView.getContext());
            imageGetter.putTextView(mContent);
            mContent.setText(Html.fromHtml(UBBUtil.a(EmojiParser.b(comment.getContent())), imageGetter, null));

            mFloor.setText(String.valueOf("#" + comment.getFloor()));
            mPublishTime.setText(FuzzyDateFormatter.getTimeAgo(this.itemView.getContext(), new Date(comment.getTime())));

            if (commentFloor.isDuplicateQuoteVisible()) {
                if (commentFloor.isDuplicateQuoteExpand()) {
                    mDuplicateQuoteFloorsView.setQuoteList(commentFloor.getDuplicateQuote());
                    mDuplicateQuoteInfo.setText(itemView.getContext().getString(R.string.duplicate_quote_expand));
                    mDuplicateQuoteIcon.setImageResource(R.drawable.ic_expand_less_black_24dp);
                } else {
                    mDuplicateQuoteInfo.setText(itemView.getContext().getString(R.string.duplicate_quote_un_expand));
                    mDuplicateQuoteFloorsView.setQuoteList(null);
                    mDuplicateQuoteIcon.setImageResource(R.drawable.ic_expand_more_black_24dp);
                }
                mDuplicateQuoteLayout.setVisibility(View.VISIBLE);
            } else {
                mDuplicateQuoteFloorsView.setQuoteList(null);
                mDuplicateQuoteLayout.setVisibility(View.GONE);
            }

            if (commentFloor.isUnduplicatedQuoteVisible()) {
                if (commentFloor.isUnduplicatedQuoteExpand()) {
                    mNoDuplicateQuoteFloorsView.setQuoteList(commentFloor.getNoDuplicateQuote());
                    mNoDuplicateQuoteInfo.setText(String.valueOf(commentFloor.getTotalSize() + itemView.getContext().getString(R.string.no_duplicate_quote_expand)));
                    mDuplicateQuoteIcon.setImageResource(R.drawable.ic_expand_less_black_24dp);
                } else {
                    mNoDuplicateQuoteInfo.setText(String.valueOf(commentFloor.getTotalSize() + itemView.getContext().getString(R.string.no_duplicate_quote_un_expand)));
                    mNoDuplicateQuoteFloorsView.setQuoteList(null);
                    mDuplicateQuoteIcon.setImageResource(R.drawable.ic_expand_more_black_24dp);
                }
                mNoDuplicateQuoteLayout.setVisibility(View.VISIBLE);
            } else {
                mNoDuplicateQuoteFloorsView.setQuoteList(null);
                mNoDuplicateQuoteLayout.setVisibility(View.GONE);
            }
        }
    }
}
