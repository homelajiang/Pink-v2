package com.lxy.pink.data.source.db;

import com.lxy.pink.Injection;
import com.lxy.pink.data.db.DaoMaster;
import com.lxy.pink.data.db.DaoSession;
import com.lxy.pink.utils.Config;

/**
 * Created by homelajiang on 2016/10/9 0009.
 */

public class DaoMasterHelper {
    private static DaoSession daoSession;

    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            DaoMaster.DevOpenHelper devOpenHelper =
                    new DaoMaster.DevOpenHelper(Injection.provideContext(), Config.DB_NAME);
            DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
