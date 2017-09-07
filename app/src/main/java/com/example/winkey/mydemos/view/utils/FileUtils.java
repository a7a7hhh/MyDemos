package com.example.winkey.mydemos.view.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;


import com.example.winkey.mydemos.App;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.text.DecimalFormat;

import okhttp3.ResponseBody;

/**
 * @author : xupg
 * @date:2017/4/7
 * @description: TODO
 */

public class FileUtils {

    /**
     * 升级文件名字
     * @param context
     * @return
     */
    public static String getUpdateFileName(Context context){
        StringBuffer buffer = new StringBuffer();
        buffer.append("ky_client_");
        buffer.append(SystemTool.getAppVersionCode(context));
        buffer.append(".apk");
        return buffer.toString();
    }

    /**
     * 获取指定目录剩余空间
     * @param path
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long getDirAvailableSize(String path) {
        StatFs stat = new StatFs(path);
        long totalBlocks = stat.getBlockCount();
        return totalBlocks;
    }

    public static File getAppDataDir(Context context) {
        final String appDir = "/Android/data/" + context.getPackageName();
        File file = new File(Environment.getExternalStorageDirectory()
                .getPath() + appDir);
        return file;
    }

    public static File getCacheDir(Context context) {
        File file = new File(getAppDataDir(context), "cache");
        return file;
    }

    public static String getCacheDirPath(Context context) {
        return getCacheDir(context).getAbsolutePath();
    }

    public static File getImageDir(Context context) {
        File file = new File(getAppDataDir(context), "images");
        return file;
    }

    public static String getImageDirPath(Context context) {
        return getImageDir(context).getAbsolutePath();
    }

    public static File getDownloadDir(Context context) {
        File file = new File(getAppDataDir(context), "download");
        return file;
    }

    public static String getDownloadDirPath(Context context) {
        return getDownloadDir(context).getAbsolutePath();
    }

    public static File getPictureDir(Context context) {
        File file = new File(getAppDataDir(context), "picture");
        return file;
    }

    public static String getPictureDirPath(Context context) {
        return getPictureDir(context).getAbsolutePath();
    }

    public static String getUpdateAPKDirPath(Context context, String fileName){
        return getDownloadDir(context).getAbsolutePath()+"/"+fileName;
    }
    public static File getLogDir(Context context) {
        File file = new File(getAppDataDir(context), "log");
        return file;
    }

    public static String getLogDirPath(Context context) {
        return getLogDir(context).getAbsolutePath();
    }

    /**
     * 照片
     * @param context
     * @return
     */
    public static File getPhotoDir(Context context) {
        File file = new File(getAppDataDir(context), "photo");
        return file;
    }

    public static String getPhotoDirPath(Context context) {
        return getPhotoDir(context).getAbsolutePath();
    }

//    /**
//     * 获得照片路径
//     *
//     * @return
//     */
//    public static String getPhotoPath() {
//        return Environment.getExternalStorageDirectory() + "/DCIM/";
//    }

    /**
     * 转换文件大小
     */
    public static String formetFileSize(long size) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (size < 1024) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1048576) {
            fileSizeString = df.format((double) size / 1024) + "K";
        } else if (size < 1073741824) {
            fileSizeString = df.format((double) size / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) size / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static File writeData(File file, byte[] data) {
        if (data == null || data.length <= 0) {
            return null;
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(file.length());
            raf.write(data);
            raf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;

    }

    /**
     *
     * @param aty
     * @param uri
     * @return
     * @throws URISyntaxException
     */
    public static File uri2File(Activity aty, Uri uri) throws URISyntaxException {
        String[] projection;
        if(SystemTool.getSDKVersion() < 11) {
            projection = new String[]{MediaStore.Images.Media.DATA};
            Cursor loader1 = aty.managedQuery(uri, projection, (String)null, (String[])null, (String)null);
            int cursor1 = loader1.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            loader1.moveToFirst();
            String column_index1 = loader1.getString(cursor1);
            return new File(column_index1);
        } else {
//            projection = new String[]{MediaStore.Images.Media.DATA};
//            CursorLoader loader = new CursorLoader(aty, uri, projection, (String)null, (String[])null, (String)null);
//            Cursor cursor = loader.loadInBackground();
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            cursor.moveToFirst();
//            return new File(cursor.getString(column_index));
            File file = new File(new URI(uri.toString()));
            return file;
        }
    }

    /**
     *
     * @param context
     * @param file
     * @return
     */
    public static Uri fileToUri(Context context, File file) {
        String filePath = file.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Images.Media._ID },
                MediaStore.Images.Media.DATA + "=? ",
                new String[] { filePath }, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (file.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    /**
     * 获取图片文件
     * @param bitmap
     * @param filePath
     * @return
     */
    public static boolean bitmapToFile(Bitmap bitmap, String filePath) {
        boolean isSuccess = false;
        if(bitmap == null) {
            return isSuccess;
        } else {
            File file = new File(filePath.substring(0, filePath.lastIndexOf(File.separator)));
            if(!file.exists()) {
                file.mkdirs();
            }

            BufferedOutputStream out = null;

            try {
                out = new BufferedOutputStream(new FileOutputStream(filePath), 8192);
                isSuccess = bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            } catch (FileNotFoundException var9) {
                var9.printStackTrace();
            } finally {
                closeIO(new Closeable[]{out});
            }

            return isSuccess;
        }
    }

    public static void closeIO(Closeable... closeables) {
        if(closeables != null && closeables.length > 0) {
            Closeable[] var4 = closeables;
            int var3 = closeables.length;

            for(int var2 = 0; var2 < var3; ++var2) {
                Closeable cb = var4[var2];

                try {
                    if(cb != null) {
                        cb.close();
                    }
                } catch (IOException var6) {
                    throw new RuntimeException(FileUtils.class.getClass().getName(), var6);
                }
            }

        }
    }

    /**
     * @param folderPath
     * @param fileNmae
     * @return
     */
    public static File getSaveFile(String folderPath, String fileNmae) {
        File file = new File(getSavePath(folderPath) + File.separator + fileNmae);

        try {
            file.createNewFile();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return file;
    }

    public static String getSavePath(String folderName) {
        return getSaveFolder(folderName).getAbsolutePath();
    }

    public static File getSaveFolder(String folderName) {
        File file = new File(getSDCardPath() + File.separator + folderName + File.separator);
        file.mkdirs();
        return file;
    }

    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * @param file
     */
    public static void RecursionDeleteFile(File file){
        if(file.isFile()){
            file.delete();
            return;
        }
        if(file.isDirectory()){
            File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0){
                file.delete();
                return;
            }
            for(File f : childFile){
                RecursionDeleteFile(f);
            }
            file.delete();
        }
    }


    /**
     * 文件保存
     *
     * @param body
     * @return
     */
    public static File writeResponseBodyToDisk(ResponseBody body, String fileName) {
        File file = null;
        try {
            String path = FileUtils.getDownloadDirPath(App.getInstance());
            File files = new File(path);
            if (!files.exists()) {
                files.mkdirs();
            }
            file = new File(path, fileName);
            InputStream is = body.byteStream();
            FileOutputStream fos = null;
            BufferedInputStream bis = null;
            try {
                fos = new FileOutputStream(file);
                bis = new BufferedInputStream(is);
                byte[] buffer = new byte[4096];
                int len;
                while ((len = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                fos.flush();

            } catch (Exception e) {
                e.printStackTrace();
                return file;
            } finally {
                fos.close();
                bis.close();
                is.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return file;
        }

        return file;
    }

    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bytesToHexString(digest.digest());
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static String compressImage(String filePath, String fileName, int  quality){
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        String path = FileUtils.getPictureDirPath(App.getInstance());
        File files = new File(path);
        if (!files.exists()) {
            files.mkdirs();
        }
        File outfile = new File(path, fileName);
        try {
            FileOutputStream out = new FileOutputStream(outfile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            return outfile.getPath();
        }
    }
}
