package com.lxy.pink.ui.music.playList;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxy.pink.R;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.ui.base.adapter.IAdapterView;
import com.lxy.pink.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2016/11/3 0003.
 */

public class PlayListItemView extends RelativeLayout implements IAdapterView<PlayList> {
    @BindView(R.id.image_view_album)
    AppCompatImageView imageViewAlbum;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.text_view_info)
    TextView textViewInfo;
    @BindView(R.id.image_button_action)
    AppCompatImageView imageButtonAction;
    @BindView(R.id.layout_action)
    FrameLayout buttonAction;

    public PlayListItemView(Context context) {
        super(context);
        View.inflate(context, R.layout.item_play_list, this);
        ButterKnife.bind(this);
    }

    @Override
    public void bind(PlayList item, int position) {
        imageViewAlbum.setImageDrawable(ViewUtils.generateAlbumDrawable(getContext(),item.getName()));
        textViewName.setText(item.getName());
        textViewInfo.setText(getResources().getQuantityString(
                R.plurals.pink_play_list_items_formatter,item.getItemCount(),item.getItemCount()
        ));
    }
}
