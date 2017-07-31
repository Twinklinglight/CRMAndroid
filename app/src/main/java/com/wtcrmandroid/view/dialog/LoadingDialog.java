package com.wtcrmandroid.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wtcrmandroid.R;

import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/7/4.
 */

public class LoadingDialog extends Dialog {
    private Context mContext;
    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        this.mContext = context;
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context,  R.style.Dialog);
        this.mContext = context;
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        ButterKnife.bind(this);
//        initWindowParams();
    }

    private void initWindowParams() {
        Window dialogWindow = getWindow();
        // 获取屏幕宽、高用
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        int width3 = dm.widthPixels;

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (width3 * 0.3); // 宽度设置为屏幕的0.5
        lp.height=(int) (width3 * 0.3);
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);
    }
}
