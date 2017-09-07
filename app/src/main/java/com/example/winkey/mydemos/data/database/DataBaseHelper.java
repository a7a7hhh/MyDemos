package com.example.winkey.mydemos.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.winkey.mydemos.view.utils.Logger;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 数据库helper
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {


    /**
     * 数据库名字
     *
     */
    private static final String DATABASE_NAME = "city.db";
    private static final String DATABASE_PATH = "/data/data/com.ykx.flm.employee/databases/";
    /**
     * 数据库配置文件名字
     */
    private static final String DATABASE_CONFIG = "TEST.db-journal";


    /**
     * 数据库版本号
     */
    private static final int DATABASE_VERSION = 1;


//    /**
//     *数据库默认路径SDCard
//     */
//    private static String DATABASE_PATH = Environment.getExternalStorageDirectory()
//            + "/"+DATABASE_NAME;
//    /**
//     *数据库配置文件默认路径SDCard
//     */
//    private static String DATABASE_PATH_JOURN = Environment.getExternalStorageDirectory()
//            + "/"+DATABASE_CONFIG;

    private Context mContext;


    public DataBaseHelper(Context context) {
        super(context, DATABASE_PATH + DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
        initDataBase();
    }

    /**
     * 如果没有SDCard 默认存储在项目文件目录下
     */
    private void initDataBase() {
        boolean dbexist = checkdatabase();
        if (!dbexist) {

            // If database did not exist, try copying existing database from assets folder.
            try {
                File dir = new File(DATABASE_PATH);
                dir.mkdirs();
                InputStream myinput = mContext.getAssets().open("db/" + DATABASE_NAME);
                String outfilename = DATABASE_PATH + DATABASE_NAME;
                Log.i(DataBaseHelper.class.getName(), "DB Path : " + outfilename);
                OutputStream myoutput = new FileOutputStream(outfilename);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myinput.read(buffer)) > 0) {
                    myoutput.write(buffer, 0, length);
                }
                myoutput.flush();
                myoutput.close();
                myinput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * Check whether or not database exist
    */
    private boolean checkdatabase() {
        boolean checkdb = false;

        String myPath = DATABASE_PATH + DATABASE_NAME;
        File dbfile = new File(myPath);
        checkdb = dbfile.exists();

        Log.i(DataBaseHelper.class.getName(), "DB Exist : " + checkdb);

        return checkdb;
    }


    /**
     * 创建数据库
     *
     * @param db
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        Logger.debug(DataBaseHelper.class.getName(), "onCreate");
        try {
        } catch (Exception e) {
            Logger.debug(DataBaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }

    }

    /**
     * 数据库升级
     *
     * @param db
     * @param connectionSource
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion,
                          int newVersion) {
        Logger.debug(DataBaseHelper.class.getName(), "onUpgrade");
        try {
//            TableUtils.dropTable(connectionSource, SearchHistoryPO.class, true);
            onCreate(db, connectionSource);
        } catch (Exception e) {
            Logger.debug(DataBaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }


//    /**
//     * 删除数据库
//     */
//    public void deleteDB() {
//        if (mContext != null) {
//            File f = mContext.getDatabasePath(DATABASE_NAME);
//            if (f.exists()) {
//                // mContext.deleteDatabase(DATABASE_NAME);
//                Logger.debug("DB", "---delete SDCard DB---");
//                f.delete();
//            } else {
//                Logger.debug("DB", "---delete App DB---");
//                mContext.deleteDatabase(DATABASE_NAME);
//            }
//
//            File file = mContext.getDatabasePath(DATABASE_PATH);
//            if (file.exists()) {
//                Logger.debug("DB", "---delete SDCard DB 222---");
//                file.delete();
//            }
//
//            File file2 = mContext.getDatabasePath(DATABASE_PATH_JOURN);
//            if (file2.exists()) {
//                Logger.debug("DB", "---delete SDCard DB 333---");
//                file2.delete();
//            }
//        }
//    }


    /**
     * 关闭
     */
    @Override
    public void close() {
        super.close();
    }
}
