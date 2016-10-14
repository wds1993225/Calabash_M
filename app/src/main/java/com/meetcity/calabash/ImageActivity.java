package com.meetcity.calabash;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.image.ImageInfo;
import com.meetcity.calabash.util.SeeUtil;
import com.meetcity.calabash.util.FrescoUtil;

import me.relex.photodraweeview.OnPhotoTapListener;
import me.relex.photodraweeview.PhotoDraweeView;

/**
 * Created by wds1993225 on 2016/5/15.
 */
public class ImageActivity extends Activity implements View.OnClickListener{

    private PhotoDraweeView imageView;
    private RelativeLayout layout;
    private ImageView close;
    private ImageView save;
    private ImageView share;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


        initView();
        initData();

    }
    private void initView(){
        close = (ImageView)findViewById(R.id.image_close);
        save = (ImageView)findViewById(R.id.image_save);
        share = (ImageView)findViewById(R.id.image_share);
        imageView = (PhotoDraweeView)findViewById(R.id.image_pic);
        layout = (RelativeLayout)findViewById(R.id.image_ll);
        close.setOnClickListener(this);
        save.setOnClickListener(this);
        share.setOnClickListener(this);
        layout.setOnClickListener(this);


        imageView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                finish();
            }
        });

    }

    private void initData(){
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        Uri uri = Uri.parse(url);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri).setAutoPlayAnimations(true)
                .setTapToRetryEnabled(true)
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(
                            String id,
                            ImageInfo imageInfo,
                            Animatable anim) {
                        if(anim!=null){
                            anim.start();
                        }
                        imageView.update(imageInfo.getWidth(),imageInfo.getHeight());
                    }
                }).build();

        imageView.setController(controller);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_share:
                share();
                break;
            case R.id.image_save:
                save();
                break;
            case R.id.image_close:
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                break;
            case R.id.image_ll:
                finish();
                break;
            default:
                break;
        }
    }

    private void save(){
        if(!TextUtils.isEmpty(url)){
            FrescoUtil.savePicture(url,this,true);
        }
    }
    private void share(){
        SeeUtil.togoShare(this,url,"");
    }
}
