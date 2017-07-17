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
import android.widget.EditText;
import android.widget.TextView;

import com.wtcrmandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zxd on 2017/7/17.
 */

public class DocApplyDialog extends Dialog {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_complete_details)
    EditText etCompleteDetails;

    private Context context;
    private String titleString;
    private DocapplyListener listener;

    public DocApplyDialog(@NonNull Context context, String titleString, DocapplyListener listener) {
        super(context, R.style.Dialog);
        this.context = context;
        this.titleString = titleString;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_docapply);
        initWindowParams();
        ButterKnife.bind(this);
        tvTitle.setText(titleString);
    }

    @OnClick({R.id.tv_cancle, R.id.tv_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancle:
                dismiss();
                break;
            case R.id.tv_sure:
                listener.ClickApply(titleString,etCompleteDetails.getText().toString());
                break;
        }
    }

    public interface DocapplyListener {

        void ClickApply(String state,String reson);

    }

    private void initWindowParams() {
        Window dialogWindow = getWindow();
        // 获取屏幕宽、高用
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        int width3 = dm.widthPixels;

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (width3 * 0.85); // 宽度设置为屏幕的0.85
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);
    }
}
