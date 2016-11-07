package com.lxy.pink.ui.music.musicList;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxy.pink.R;
import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.ui.base.adapter.IAdapterView;
import com.lxy.pink.utils.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2016/11/3 0003.
 */

public class SongListItemView extends RelativeLayout implements IAdapterView<Song> {

    @BindView(R.id.text_view_index)
    TextView textViewIndex;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.text_view_info)
    TextView textViewInfo;
    @BindView(R.id.image_button_action)
    AppCompatImageView imageButtonAction;
    @BindView(R.id.layout_action)
    FrameLayout layoutAction;

    public SongListItemView(Context context) {
        super(context);
        View.inflate(context, R.layout.item_play_list_detail_song, this);
        ButterKnife.bind(this);
    }

    @Override
    public void bind(Song song, int position) {
        textViewName.setText(song.getTitle());
        textViewIndex.setText(String.valueOf(position+1));
        textViewInfo.setText(String.format("%s | %s", TimeUtils
                .formatDuration((int) song.getDuration()),String.valueOf(song.getArtist())));
    }
}
