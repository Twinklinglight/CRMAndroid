package com.wtcrmandroid.activity.aboutmy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thinkcool.circletextimageview.CircleTextImageView;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.Const;
import com.wtcrmandroid.R;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.popupwindow.popubwindow_share;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;

/**
 * @author Create by zxd on 2017/6/21
 */

public class MycardActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.scrll)
    ScrollView scrll;
    String path;
    String filename;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_job)
    TextView tvJob;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.Circle_image)
    CircleTextImageView CircleImage;

    @Override
    protected int layout() {
        return R.layout.activity_mycard;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String rolename = intent.getStringExtra("rolename");
        String phonenumber = intent.getStringExtra("phonenumber");
        String imageUrl = intent.getStringExtra("imageUrl");

        Log.i("imagUrl","imageUrl = "+imageUrl);

        tvName.setText(username);
        tvJob.setText(rolename);
        tvPhone.setText(phonenumber);
        Glide.with(this).load(imageUrl).error(R.mipmap.ic_photo_upload).into(CircleImage);

        mTitlebar.setTitletext("我的名片");
        mTitlebar.setrighttext("分享");

        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MycardActivity.this.finish();
            }
        });

        //分享按钮
        mTitlebar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printTest(MycardActivity.this, scrll);
                new popubwindow_share(MycardActivity.this, mTitlebar, path, filename);

            }
        });
    }

    @Override
    public void returnData(int key, Object data) {

    }

    public void printTest(Context context, ScrollView scrollView) {
        String TAG = "Print";
        Bitmap bitmap = getBitmapByView(scrollView);
        if (bitmap == null) {
            Log.d(TAG, "bitmap is null");
            return;
        }

        path = Const.SDCARD_PATH + "share" + File.separatorChar + "image";
        filename = "icon_wutong_card.png";
        try {
            FileOutputStream out = new FileOutputStream(path + File.separatorChar + filename);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            Log.d(TAG, "file" + path + filename + "output done.");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

    //ScrollView 转成bitmap长图
    public static Bitmap getBitmapByView(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;

        Log.i("Print", "getBitmapByView: count = " + scrollView.getChildCount());
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            Log.i("Print", "getBitmapByView: h = " + h);
            scrollView.getChildAt(i).setBackgroundColor(
                    Color.parseColor("#ffffff"));
        }

        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }

}
