package com.ci.juniorstrainingtasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.InputStream;

public class ImageExample extends AppCompatActivity {

    private CheckBox cbIsGlide;
    private ImageView ivUrlImage;
    private ImageView ivGlideImage;
    private RadioButton rbIsLoadUri;
    private ProgressBar pbLoading;

    String strUrl = "https://s3.amazonaws.com/koya-dev-videos/kindness/8da807aa-1e1e-413d-bf9b-5bb084646593/medialibrary/9456621508/videos/1eb78337-d569-41bd-95ad-153d9098de03.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_example);
        init();
    }

    private void init() {
        cbIsGlide = findViewById(R.id.cbIsGlide);
        pbLoading = findViewById(R.id.pbLoading);
        ivUrlImage = findViewById(R.id.ivUrlImage);
        ivGlideImage = findViewById(R.id.ivGlideImage);
        rbIsLoadUri = findViewById(R.id.rbIsLoadUri);
        addCheckListener();
        addClickListener();
    }

    private void addClickListener() {
        findViewById(R.id.btnLoadImages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(ImageExample.this)
                        .load(strUrl)
                        .override(150,150)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        }).into(ivGlideImage);
                new DownloadImageTask(ivUrlImage).execute(strUrl);
            }
        });
    }

    private void addCheckListener() {
        cbIsGlide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){

                }else {


                }
            }
        });
        rbIsLoadUri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){

                }else {

                }
            }
        });
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        private ImageView ivImage;

        DownloadImageTask(ImageView ivImage){
            this.ivImage = ivImage;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbLoading.setVisibility(View.VISIBLE);
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            ivImage.setImageBitmap(result);
            pbLoading.setVisibility(View.GONE);
        }
    }
}
