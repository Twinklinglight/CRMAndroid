package com.wtcrmandroid.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wtcrmandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zxd on 2017/7/26.
 */

public class PhotoDialog extends Dialog {

    private Context mContext;
    private PhotoListener listener;

    public PhotoDialog(@NonNull Context context, PhotoListener listener) {
        super(context, R.style.Dialog);
        this.mContext = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_photo);
        ButterKnife.bind(this);
        initWindowParams();
    }

    private void initWindowParams() {
        Window dialogWindow = getWindow();
        // 获取屏幕宽、高用
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        int width3 = dm.widthPixels;

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (width3 * 0.85); // 宽度设置为屏幕的0.85

        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);
    }

    @OnClick({R.id.tv_camear, R.id.tv_picture,R.id.tv_cancle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_camear:
                listener.takePhoto();
                dismiss();
                break;
            case R.id.tv_picture:
                listener.chosePicture();
                dismiss();
                break;
            case R.id.tv_cancle:
                dismiss();
                break;
        }
    }


    public interface PhotoListener {
        void takePhoto();

        void chosePicture();
    }
}
