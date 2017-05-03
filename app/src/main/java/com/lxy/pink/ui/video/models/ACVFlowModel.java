package com.lxy.pink.ui.video.models;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyModelClass;
import com.lxy.pink.R;
import com.lxy.pink.widget.FlowLayout.FlowLayout;

import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by homelajiang on 2017/5/3 0003.
 */
@EpoxyModelClass(layout = R.layout.flow_layout)
public abstract class ACVFlowModel extends EpoxyModel<FlowLayout> {

    @EpoxyAttribute
    String[] titles;

    @Override
    public void bind(FlowLayout view) {
        if (view.getChildCount() == 0 && titles != null) {
            for (int i = 0; i < titles.length; i++) {
                TextView tv = new TextView(view.getContext());
                tv.setTextColor(ContextCompat.getColor(view.getContext(),R.color.black));
                tv.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.tag_bg));
                tv.setTextSize(14);
                tv.setGravity(Gravity.CENTER);
                tv.setPadding(40,20,40,20);
                tv.setText(String.valueOf(titles[i]));
                view.addView(tv);
            }
        }
    }
}
