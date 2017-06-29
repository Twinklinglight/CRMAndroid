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
import android.widget.RadioButton;
import android.widget.TextView;

import com.wtcrmandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zxd on 2017/6/3.
 */

public class CompleteConditionDialog extends Dialog implements CompleteDetailsDialog.CompleteListener {

    @BindView(R.id.rb_done)
    RadioButton rbDone;
    @BindView(R.id.rb_undone)
    RadioButton rbUndone;
    @BindView(R.id.tv_worksort_dialog_sure)
    TextView tvWorksortDialogSure;
    private Context mContext;

    public CompleteConditionDialog(@NonNull Context context) {
        super(context, R.style.WorkSortDialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_complete_condition);
        initWindowParams();
        ButterKnife.bind(this);
    }

    private void initWindowParams() {
        Window dialogWindow = getWindow();
        // 获取屏幕宽、高用
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        int width3 = dm.widthPixels;

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (width3 * 0.65); // 宽度设置为屏幕的0.65

        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);
    }


    @OnClick({R.id.rb_done, R.id.rb_undone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_done:
                new CompleteDetailsDialog(mContext,this).show();
                dismiss();
                break;
            case R.id.rb_undone:
                new NoCompeleteDialog(mContext).show();
                dismiss();
                break;
        }
    }

    //原因回调
    @Override
    public void CompleteReason(String text) {

    }
}
