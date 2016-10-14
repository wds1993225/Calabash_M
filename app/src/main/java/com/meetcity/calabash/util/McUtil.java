package com.meetcity.calabash.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v7.graphics.Palette;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.meetcity.calabash.R;

import java.io.File;
import java.lang.reflect.Type;

/**
 * Created by wds1993225 on 2016/9/20.
 */
public class McUtil {


    /**
     * 转换十六进制unicode
     * */
    public String convert(String utfString){
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while((i=utfString.indexOf("\\u", pos)) != -1){
            sb.append(utfString.substring(pos, i));
            if(i+5 < utfString.length()){
                pos = i+6;
                sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));
            }
        }

        return sb.toString();
    }
    /**
     * 新建目录
     *
     * @param directoryName
     * @return
     */
    public static boolean createDirectory(String directoryName) {
        boolean status;
        if (!directoryName.equals("")) {
            File path = Environment.getExternalStorageDirectory();
            File newPath = new File(path.toString() + directoryName);
            status = newPath.mkdir();
            status = true;
        } else
            status = false;
        return status;
    }


    /**
     * 防止button连续两次点击
     * */
        private static long lastClickTime;
        public synchronized static boolean isFastClick() {
            long time = System.currentTimeMillis();
            if ( time - lastClickTime < 500) {
                return true;
            }
            lastClickTime = time;
            return false;
        }

    /**
     * Roboto-Condensed.ttf
     * SegoeUI.ttf
     * 更改字体
     * */
    public static void changeFont(Context context, TextView textView){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/SegoeUI.ttf");
        textView.setTypeface(typeface);
    }

    public static void changeFont(Context context, TextView textView,String name){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/"+name);
        textView.setTypeface(typeface);
    }

    /**
     * 处理图像
     *
     * @param bitmap
     *            原图
     * @param degrees
     *            色相值
     * @param sat
     *            饱和度值
     * @param lum
     *            明度值
     * @return 处理后的图像
     *
     */
    public static Bitmap handleImageEffect(Bitmap bitmap, float degrees, float sat, float lum) {

        Bitmap temp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(temp);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // 设置色相
        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, degrees);
        hueMatrix.setRotate(1, degrees);
        hueMatrix.setRotate(2, degrees);

        // 设置饱和度
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(sat);

        // 设置明度
        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        // 融合
        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(lumMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(hueMatrix);

        // 给paint设置颜色属性
        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));

        // 绘制
        canvas.drawBitmap(bitmap, 0, 0, paint);

        return temp;

    }

    public static int changeColor(Context context,int i){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),i);
        Palette palette = Palette.from(bitmap).generate();
        //1.活力颜色
        Palette.Swatch a = palette.getVibrantSwatch();
        //2.亮的活力颜色
        Palette.Swatch b=palette.getLightVibrantSwatch();
        //3.暗的活力颜色
        Palette.Swatch c = palette.getDarkVibrantSwatch();
        //4.柔色
        Palette.Swatch d = palette.getMutedSwatch();
        //5.亮的柔色
        Palette.Swatch e = palette.getLightMutedSwatch();
        //6.暗的柔色
        Palette.Swatch f = palette.getDarkMutedSwatch();

        int color = R.color.trans_success_stroke_color;
        if(b != null){
            color = b.getRgb(); //rgb颜色
            b.getTitleTextColor();//文本颜色
        }
        return color;
    }




}
