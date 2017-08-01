package com.wtcrmandroid.base;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 数据库管理类单例
 * Created by Mr-Zhang on 2016/3/24.
 */
public class WTDataBaseManager {
    private WTDataBaseManager() {
    }

    public String filePath = "data/data/com.wtcrmandroid/db_peihuo.db";
    public String pathStr = "data/data/com.wtcrmandroid";
    public String dbName = "db_peihuo.db";
    private static WTDataBaseManager mInstance = null;
    private SQLiteDatabase dbPeiHuo;

    public static WTDataBaseManager getsInstance() {
        if (mInstance == null) {
            synchronized (WTDataBaseManager.class) {
                if (mInstance == null) {
                    mInstance = new WTDataBaseManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化
     * 第一次启动时将assets中的数据库拷贝到/data/data/com.wutong.asproject.wutongphxxb/db_peihuo.db
     * 如果已经存在则无需再拷贝直接使用即可
     *
     * @param context 复制书库据文件必须的
     */
    public void initDatabase(Context context) {
        File wtPath = new File(filePath);
        if (wtPath.exists()) {
            if (WtHeader.versionDif(context)) {
                wtPath.deleteOnExit();
                File path = new File(pathStr);
                if (!path.exists()) {
                    path.mkdir();
                }
                try {
                    AssetManager am = context.getAssets();
                    InputStream inputStream = am.open(dbName);
                    FileOutputStream fileOutputStream = new FileOutputStream(wtPath);
                    byte[] buffer = new byte[1024];
                    int count;
                    while ((count = inputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, count);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        } else {
            File path = new File(pathStr);
            if (!path.exists()) {
                path.mkdir();
            }
            try {
                AssetManager am = context.getAssets();
                InputStream inputStream = am.open(dbName);
                FileOutputStream fileOutputStream = new FileOutputStream(wtPath);
                byte[] buffer = new byte[1024];
                int count;
                while ((count = inputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, count);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            initDatabase(context);
            return;
        }
        dbPeiHuo = SQLiteDatabase.openDatabase(filePath, null, 0);
    }

    /**
     * 返回一个只读的数据库
     *
     * @return db_peihuo.db
     */
    public SQLiteDatabase getDbPeiHuoReadOnly() {
        //只读状态打开数据库
        return SQLiteDatabase.openDatabase(filePath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    /**
     * 推出程序时统一关闭数据库
     */
    public void closeDb() {
        if (dbPeiHuo!=null){
            dbPeiHuo.close();
        }
    }

    /**
     * 返回一个可读写的数据库
     *
     * @return db_peihuo.db
     */
    public SQLiteDatabase getDbPeiHuoReadWrite() {
        return SQLiteDatabase.openDatabase(filePath, null, SQLiteDatabase.OPEN_READWRITE);
    }
}
