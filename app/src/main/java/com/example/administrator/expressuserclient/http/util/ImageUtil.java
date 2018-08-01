package com.example.administrator.expressuserclient.http.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/4/7 0007.
 * 对图片的处理
 */

public class ImageUtil {


    /**
     * 图片的压缩（尺寸压缩和质量压缩）
     *
     * @param srcPath   源文件的路径
     * @param desPath   压缩后的保存路径
     * @param maxWidth  最大的高度
     * @param maxHeight 最大的宽度
     * @param quality   不压缩百分比（quality表示不压缩的量 100表示不压缩）
     * @throws IOException
     */
    public static void compressPicture(String srcPath, String desPath, int maxWidth, int maxHeight, int quality) {
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            //开始读入图片，此时把options.inJustDecodeBounds 设回true了
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(srcPath, opts);
            int w = opts.outWidth;
            int h = opts.outHeight;
            int inSampleSize = 1;
            if (w > h && w > maxWidth) {
                inSampleSize = w / maxWidth;
            } else if (w < h && h > maxHeight) {
                inSampleSize = h / maxHeight;
            }
            opts.inSampleSize = inSampleSize;
            opts.inJustDecodeBounds = false;
            Bitmap bm = BitmapFactory.decodeFile(srcPath, opts);
            OutputStream out = new FileOutputStream(desPath);
            bm.compress(Bitmap.CompressFormat.JPEG, quality, out);
            out.close();
            bm.recycle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 缩放图片
     *
     * @param srcPath   原路径
     * @param desPath   缩放后的保存路径
     * @param newWidth  缩放后的宽度
     * @param newHeight 缩放后的高度
     */
    public static void zoomImage(String srcPath, String desPath, int newWidth, int newHeight) {
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(srcPath);
            //获取这个图片的宽和高
            float width = bitmap.getWidth();
            float height = bitmap.getHeight();
            //计算宽高缩放率
            float scaleWidth = newWidth / width;
            float scaleHeight = newHeight / height;
            //创建操作图片用的matrix
            Matrix matrix = new Matrix();
            //缩放图片动作
            matrix.postScale(scaleWidth, scaleHeight);
            Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, (int) width, (int) height, matrix, true);
            FileOutputStream out = new FileOutputStream(desPath);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
