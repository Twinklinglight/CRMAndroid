package com.wtcrmandroid.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.wtcrmandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zxd on 2017/6/6
 */

public class CompleteDetailsDialog extends Dialog {

    @BindView(R.id.et_complete_details)
    EditText etCompleteDetails;
    @BindView(R.id.tv_worksort_dialog_sure)
    TextView tvWorksortDialogSure;
    private Context mContext;

    private CompleteListener listener;

    public CompleteDetailsDialog(@NonNull Context context,CompleteListener listener) {
        super(context, R.style.WorkSortDialog);
        this.mContext = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_complete_details);
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

    @OnClick(R.id.tv_worksort_dialog_sure)
    public void onClick() {
        String text = etCompleteDetails.getText().toString();
        listener.CompleteReason(text);
    }

    public interface CompleteListener{
        void CompleteReason(String text);
    }
}
