package com.lxy.pink.ui.music.musicList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxy.pink.R;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.ui.base.adapter.OnItemClickListener;
import com.lxy.pink.ui.common.AbstractFooterAdapter;

import java.util.List;

/**
 * Created by homelajiang on 2016/11/3 0003.
 */

public class SongListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private PlayList playList;
    private View mHeaderView;
    private TextView textViewSummary;
    private AddPlayListCallback mAddPlayListCallback;
    private final int HEADER = 1;
    private final int NORMAL = 0;
    private OnItemClickListener onItemClickListener;

    public SongListAdapter(Context context, PlayList playList) {
        this.mContext = context;
        this.playList = playList;
        registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
            }
        });
    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
    }


    public void setAddPlayListCallback(AddPlayListCallback callback) {
        mAddPlayListCallback = callback;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER) {
            return new RecyclerView.ViewHolder(new SongListItemHeaderView(mContext)) {
            };
        }
        return new RecyclerView.ViewHolder(new SongListItemView(mContext)) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == HEADER) {
            ((SongListItemHeaderView) holder.itemView).bind(playList, position);
        } else {
            ((SongListItemView) holder.itemView).bind(playList.getSong(position - 1), position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position - 1);
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        }
        return NORMAL;
    }

    @Override
    public int getItemCount() {
        if(playList == null){
            return 0;
        }
        return playList.getItemCount() + 1;
    }

    interface AddPlayListCallback {
        void onAction(View actionView, int position);
    }
}
