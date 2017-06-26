package com.wtcrmandroid.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wtcrmandroid.R;
import com.wtcrmandroid.utils.DensityUtils;

/**
 * Created by 1363655717 on 2017-06-03.
 * 选择工作分类对话框
 *
 */
public class SelectionJobCategoriesDialog extends Dialog {
    public Context context;
    public SelectionJobCategoriesDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public SelectionJobCategoriesDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected SelectionJobCategoriesDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.dialog_selection_job_categories, null);

        setContentView(view);

        setCanceledOnTouchOutside(false);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
//        lp.height = DensityUtils.dp2px(context, 200);
        lp.width = DensityUtils.dp2px(context, 300);
        win.setAttributes(lp);
    }
}
