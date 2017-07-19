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

import com.wtcrmandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zxd on 2017/6/8
 */

public class IfCaiDianDialog extends Dialog {

    @BindView(R.id.rb_yes)
    RadioButton rbYes;
    @BindView(R.id.rb_no)
    RadioButton rbNo;
    private Context mContext;
    private CaiListener listener;
    private int position;
    private int tag;

    public IfCaiDianDialog(@NonNull Context context,CaiListener caiListener,int position,int tag) {
        super(context);
        this.mContext = context;
        this.listener = caiListener;
        this.position = position;
        this.tag = tag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_if_caizhong);
        initWindowParams();
        ButterKnife.bind(this);
        switch (tag){
            case 1:
                rbYes.setChecked(true);
                break;
            case 2:
                rbNo.setChecked(true);
                break;
        }
    }



    @OnClick({R.id.rb_yes, R.id.rb_no, R.id.tv_cancle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_yes:
                listener.ChooseClick("是",position);
                dismiss();
                break;
            case R.id.rb_no:
                listener.ChooseClick("否",position);
                dismiss();
                break;
            case R.id.tv_cancle:
                dismiss();
                break;
        }
    }

    public interface CaiListener{
        void ChooseClick(String text,int position);
    }

    private void initWindowParams() {
        Window dialogWindow = getWindow();
        // 获取屏幕宽、高用
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width3 = dm.widthPixels;

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (width3 * 0.85); // 宽度设置为屏幕的0.85

        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);
    }
}
