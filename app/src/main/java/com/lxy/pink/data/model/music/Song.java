package com.lxy.pink.data.model.music;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by homelajiang on 2016/10/9 0009.
 */

@Entity//@Entity：将我们的java普通类变为一个能够被greenDAO识别的数据库类型的实体类
public class Song {
    @Id//@Id：通过这个注解标记的字段必须是Long类型的，这个字段在数据库中表示它就是主键，并且它默认就是自增的
    //@Transient：表明这个字段不会被写入数据库，只是作为一个普通的java类字段，用来临时存储数据的，不会被持久化
    //@Property(nameInDb = "USERNAME")
    //@NotNull
    private long id;
    private String title;
    private String displayName;
    private String artist;
    private String album;
    private long albumId;
    private long duration;
    private long size;
    @Unique //    @Unique 唯一
    private String path;
    private boolean favorite;
    @Generated(hash = 997640937)
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
    @Generated(hash = 87031450)
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
