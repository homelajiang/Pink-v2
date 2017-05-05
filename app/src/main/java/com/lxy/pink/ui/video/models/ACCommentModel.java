package com.lxy.pink.ui.video.models;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACVideoComment;
import com.lxy.pink.utils.FrescoUtils;
import com.lxy.pink.utils.FuzzyDateFormatter;
import com.lxy.pink.utils.TimeUtils;
import com.lxy.pink.widget.FloorsView.FloorsView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2017/5/5 0005.
 */
@EpoxyModelClass(layout = R.layout.ac_comment)
public abstract class ACCommentModel extends EpoxyModelWithHolder<ACCommentModel.ACCommentModelHolder> {

    @EpoxyAttribute
    List<ACVideoComment> commentList;
    @EpoxyAttribute
    ACVideoComment acVideoComment;

    @Override
    public void bind(ACCommentModelHolder holder) {
        super.bind(holder);
        holder.setData(acVideoComment);
        List<View> commentViewList = new ArrayList<>();
        if (commentList != null && !commentList.isEmpty()) {
            for (ACVideoComment comment : commentList) {
                RelativeLayout quoteLayout =
                        (RelativeLayout) LayoutInflater.from(holder.itemView.getContext())
                                .inflate(R.layout.ac_comment_quote, null);
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) quoteLayout.findViewById(R.id.simpleDraweeView);
                TextView name = (TextView) quoteLayout.findViewById(R.id.name);
                TextView publishName = (TextView) quoteLayout.findViewById(R.id.publish_time);
                TextView floorCount = (TextView) quoteLayout.findViewById(R.id.floor);
                TextView content = (TextView) quoteLayout.findViewById(R.id.content);

                name.setText(comment.getUsername());
                int nameColor = comment.getNameRed() == 0 ? android.R.color.holo_red_dark : R.color.black;
                name.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), nameColor));
                FrescoUtils.setImage(comment.getAvatar(), simpleDraweeView);
                content.setText(comment.getContent());
                floorCount.setText(String.valueOf("#" + comment.getFloor()));
                publishName.setText(FuzzyDateFormatter.getTimeAgo(holder.itemView.getContext(), new Date(comment.getTime())));

                commentViewList.add(quoteLayout);
            }
            holder.quoteFrame.setViewList(commentViewList);
        }
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    static class ACCommentModelHolder extends EpoxyHolder {
        private View itemView;
        @BindView(R.id.FloorsView)
        FloorsView quoteFrame;
        @BindView(R.id.simpleDraweeView)
        SimpleDraweeView mSimpleDraweeView;
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.publish_time)
        TextView mPublishTime;
        @BindView(R.id.floor)
        TextView mFloor;
        @BindView(R.id.content)
        TextView mContent;

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }

        void setData(ACVideoComment comment) {
            mName.setText(comment.getUsername());
            int nameColor = comment.getNameRed() == 0 ? android.R.color.holo_red_dark : R.color.black;
            mName.setTextColor(ContextCompat.getColor(itemView.getContext(), nameColor));
            FrescoUtils.setImage(comment.getAvatar(), mSimpleDraweeView);
            mContent.setText(comment.getContent());
            mFloor.setText(String.valueOf("#" + comment.getFloor()));
            mPublishTime.setText(FuzzyDateFormatter.getTimeAgo(this.itemView.getContext(), new Date(comment.getTime())));
        }
    }
}
