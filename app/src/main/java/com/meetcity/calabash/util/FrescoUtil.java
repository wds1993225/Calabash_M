package com.meetcity.calabash.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by zhaodaizheng on 16/5/20.
 */
public class FrescoUtil {

    private static final String TAG = "FrescoUtil";
    public static final String IMAGE_PIC_CACHE_DIR = Environment.getExternalStorageDirectory().getPath()+"/Calabash/MC_img/";
    // 默认存放图片的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment.getExternalStorageDirectory().getPath()
            + File.separator
            + "Calabash"
            + File.separator + "MC_img" + File.separator;

    /**
     * 保存图片
     *
     * @param context
     * @param picUrl
     */
    public static void savePicture(String picUrl,Context context,boolean isShow) {
        String DEFAULT_TEMP_FILE_NAME = "calabash"+System.currentTimeMillis();


        File picDir = new File(IMAGE_PIC_CACHE_DIR);

        if (!picDir.exists()) {
            picDir.mkdirs();
        }
        CacheKey cacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(ImageRequest.fromUri(Uri.parse(picUrl)),context.getApplicationContext());
        File cacheFile = getCachedImageOnDisk(cacheKey);

        if (cacheFile == null) {
            downLoadImage(Uri.parse(picUrl),DEFAULT_TEMP_FILE_NAME,context,isShow);
            return;
        } else {
            copyTo(cacheFile,picDir,DEFAULT_TEMP_FILE_NAME,context,isShow,picUrl);
        }
    }

    public static File getCachedImageOnDisk(CacheKey cacheKey) {
        File localFile = null;
        if (cacheKey != null) {
            if (ImagePipelineFactory.getInstance().getMainDiskStorageCache().hasKey(cacheKey)) {
                BinaryResource resource = ImagePipelineFactory.getInstance().getMainDiskStorageCache().getResource(cacheKey);
                localFile = ((FileBinaryResource) resource).getFile();
            } else if (ImagePipelineFactory.getInstance().getSmallImageDiskStorageCache().hasKey(cacheKey)) {
                BinaryResource resource = ImagePipelineFactory.getInstance().getSmallImageDiskStorageCache().getResource(cacheKey);
                localFile = ((FileBinaryResource) resource).getFile();
            }
        }
        return localFile;
    }


    /**
     * 复制文件
     *
     * @param src 源文件
     * @param dir 目标文件
     * @return
     */
    public static boolean copyTo(File src, File dir, String filename,Context context,boolean isShow,String url) {

        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(src);
            in = fi.getChannel();//得到对应的文件通道
            File dst ;
            if(url.contains(".gif")){
                dst = new File(dir, filename + ".gif");
            }else {
                dst = new File(dir, filename + ".jpg");
            }
            fo = new FileOutputStream(dst);
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
            if(isShow){
                Toast.makeText(context,"图片已保存在“/storage/emulated/0/Calabash/MC_img/目录下“",Toast.LENGTH_SHORT).show();
            }
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            try {

                if (fi != null) {
                    fi.close();
                }

                if (in != null) {
                    in.close();
                }

                if (fo != null) {
                    fo.close();
                }

                if (out != null) {
                    out.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

    }


    public static void downLoadImage(final Uri uri, final String filename, final Context context, final boolean isShow) {
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>>
                dataSource = imagePipeline.fetchDecodedImage(imageRequest, context);
        dataSource.subscribe(new BaseBitmapDataSubscriber() {
            @Override
            public void onNewResultImpl(Bitmap bitmap) {
                if (bitmap == null) {
                    Log.e(TAG,"保存图片失败啦,无法下载图片");
                    if(isShow){
                        Toast.makeText(context,"图片保存失败",Toast.LENGTH_SHORT).show();
                    }
                }
                File appDir = new File(IMAGE_PIC_CACHE_DIR);
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                String fileName = "";
                if(uri.toString().contains(".gif")){
                    fileName = filename + ".gif";
                }else {
                    fileName = filename + ".jpg";
                }

                File file = new File(appDir, fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    assert bitmap != null;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                    if(isShow){
                        Toast.makeText(context,"图片已保存在“/storage/emulated/0/Calabash/MC_img/目录下“",Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailureImpl(DataSource dataSource) {
            }
        }, CallerThreadExecutor.getInstance());
    }
}
