package com.lxy.pink.ui.music;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.ui.base.adapter.OnItemClickListener;

/**
 * Created by homelajiang on 2016/11/3 0003.
 */

public class SongListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private PlayList playList;
    private View mHeaderView;
    private TextView textViewSummary;
    private AddPlayListCallback mPlayListCallback;
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


    public void addPlayListCallback(AddPlayListCallback callback) {
        mPlayListCallback = callback;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder holder;
        if (viewType == HEADER) {
            holder = new RecyclerView.ViewHolder(new SongListItemHeaderView(mContext)) {
            };
        } else {
            holder = new RecyclerView.ViewHolder(new SongListItemView(mContext)) {
            };
            if (mPlayListCallback != null) {
                ((SongListItemView) holder
                        .itemView).layoutAction
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPlayListCallback.onAction(v,holder.getAdapterPosition()-1);
                            }
                        });
            }
        }
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(position - 1);
                    }
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == HEADER) {
            ((SongListItemHeaderView) holder.itemView).bind(playList, position);
        } else {
            ((SongListItemView) holder.itemView).bind(playList.getSong(position - 1), position - 1);
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
        if (playList == null) {
            return 0;
        }
        return playList.getItemCount() + 1;
    }

    interface AddPlayListCallback {
        void onAction(View actionView, int position);
    }
}
