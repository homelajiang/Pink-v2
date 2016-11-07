package com.lxy.pink.data.model.music;

import com.lxy.pink.ui.player.PlayMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by homelajiang on 2016/10/9 0009.
 */

public class PlayList {
    public static final int NO_POSITION = -1;
    private int id;
    private String name;
    private int numOfSongs;
    private boolean favorite;
    private Date createdAt;
    private Date updatedAt;
    private List<Song> songs = new ArrayList<>();
    private int playingIndex = -1;

    public PlayList(int id, String name, int numOfSongs, boolean favorite,
                    Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.numOfSongs = numOfSongs;
        this.favorite = favorite;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PlayList() {
    }

    public int getItemCount() {
        return songs == null ? 0 : songs.size();
    }

    public int getPlayingIndex() {
        return playingIndex;
    }

    public void setPlayingIndex(int playingIndex) {
        this.playingIndex = playingIndex;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSongs() {
        return this.numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    /**
     * The current song being played or is playing based on the {@link #playingIndex}
     */
    public Song getCurrentSong() {
        if (playingIndex != NO_POSITION) {
            return songs.get(playingIndex);
        }
        return null;
    }
    public Song getSong(int index) {
        if (songs == null || songs.size() <= index) {
            return null;
        }
        return songs.get(index);
    }
    public List<Song> getSongs() {
        if (songs == null) {
            return new ArrayList<>();
        } else {
            return songs;
        }
    }

    public void setSongs(List songs) {
        this.songs = songs;
    }

    public boolean hasNext(boolean fromComplete, PlayMode playMode) {
        if (songs.isEmpty())
            return false;
        if (fromComplete && playMode == PlayMode.LIST && playingIndex + 1 >= songs.size())
            return false;
        return true;
    }

    public Song next(PlayMode playMode) {
        switch (playMode) {
            case LOOP:
            case LIST:
            case SINGLE:
                int newIndex = playingIndex + 1;
                if (newIndex >= songs.size()) {
                    newIndex = 0;
                }
                playingIndex = newIndex;
                break;
            case SHUFFLE:
                playingIndex = randomPlayIndex();
                break;
        }
        return songs.get(playingIndex);
    }

    private int randomPlayIndex() {
        int randomIndex = new Random().nextInt(songs.size());
        if (songs.size() > 1 && randomIndex == playingIndex) {
            randomPlayIndex();
        }
        return randomIndex;
    }

    public boolean prepare() {
        if (songs.isEmpty())
            return false;
        if (playingIndex == NO_POSITION) {
            playingIndex = 0;
        }
        return true;
    }


    public boolean hasLast() {
        return songs != null && songs.size() != 0;
    }

    public Song last(PlayMode playMode) {
        switch (playMode) {
            case LOOP:
            case LIST:
            case SINGLE:
                int newIndex = playingIndex - 1;
                if (newIndex < 0) {
                    newIndex = songs.size() - 1;
                }
                playingIndex = newIndex;
                break;
            case SHUFFLE:
                playingIndex = randomPlayIndex();
                break;
        }
        return songs.get(playingIndex);
    }


    public boolean getFavorite() {
        return this.favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
