package com.lxy.pink.data.model.file;

import com.lxy.pink.data.model.music.Song;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.lxy.pink.data.db.DaoSession;
import com.lxy.pink.data.db.SongDao;
import com.lxy.pink.data.db.FolderDao;

/**
 * Created by homelajiang on 2016/10/9 0009.
 */

@Entity
public class Folder {

    public static final String COLUMN_NAME = "name";

    @Id
    private int id;

    @Property(nameInDb = COLUMN_NAME)
    private String name;

    @Unique
    private String path;
    private String munOfSongs;

    @ToMany(referencedJoinProperty = "id")
    private List<Song> songs = new ArrayList<>();
    private Date createAt;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 2091473052)
    private transient FolderDao myDao;
    @Generated(hash = 880580247)
    public Folder(int id, String name, String path, String munOfSongs,
            Date createAt) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.munOfSongs = munOfSongs;
        this.createAt = createAt;
    }
    @Generated(hash = 1947132626)
    public Folder() {
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
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getMunOfSongs() {
        return this.munOfSongs;
    }
    public void setMunOfSongs(String munOfSongs) {
        this.munOfSongs = munOfSongs;
    }
    public Date getCreateAt() {
        return this.createAt;
    }
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1479033852)
    public List<Song> getSongs() {
        if (songs == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SongDao targetDao = daoSession.getSongDao();
            List<Song> songsNew = targetDao._queryFolder_Songs(id);
            synchronized (this) {
                if (songs == null) {
                    songs = songsNew;
                }
            }
        }
        return songs;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 432021166)
    public synchronized void resetSongs() {
        songs = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1822270472)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFolderDao() : null;
    }

}
