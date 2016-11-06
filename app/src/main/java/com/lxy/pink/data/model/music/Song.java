package com.lxy.pink.data.model.music;

/**
 * Created by homelajiang on 2016/10/9 0009.
 */

public class Song {
    private long id;
    private String title;
    private String displayName;
    private String artist;
    private String album;
    private long albumId;
    private long duration;
    private long size;
    private String path;
    private boolean favorite;
    public Song(long id, String title, String displayName, String artist,
            String album, long albumId, long duration, long size, String path,
            boolean favorite) {
        this.id = id;
        this.title = title;
        this.displayName = displayName;
        this.artist = artist;
        this.album = album;
        this.albumId = albumId;
        this.duration = duration;
        this.size = size;
        this.path = path;
        this.favorite = favorite;
    }
    public Song() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDisplayName() {
        return this.displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getArtist() {
        return this.artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getAlbum() {
        return this.album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    public long getAlbumId() {
        return this.albumId;
    }
    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }
    public long getDuration() {
        return this.duration;
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }
    public long getSize() {
        return this.size;
    }
    public void setSize(long size) {
        this.size = size;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public boolean getFavorite() {
        return this.favorite;
    }
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
