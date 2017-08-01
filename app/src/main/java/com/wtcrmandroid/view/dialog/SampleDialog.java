package com.wtcrmandroid.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.wtcrmandroid.R;


/**
 *简单提示dialog
 * Created by wutongS on 2016/5/10.
 */
public class SampleDialog extends Dialog {

    private String title;
    private String msg;
    private TextView tvTitle;
    private TextView tvMsg;
    private Button btnLeft;
    private Button btnRight;
    private int type;

    public SampleDialog(Context context, String title, String msg, int type){
        super(context);
        this.title = title;
        this.msg = msg;
        this.type = type;
    }

    public SampleDialog(Context context) {
        super(context,0);
    }

    public SampleDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SampleDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView( R.layout.dialog_base);
        tvTitle = (TextView) findViewById(R.id.tv_dialog_title);
        tvMsg = (TextView) findViewById(R.id.tv_dialog_msg);
        btnLeft = (Button) findViewById(R.id.btn_dialog_left);
        btnRight = (Button) findViewById(R.id.btn_dialog_right);

        WindowManager windowManager = getWindow().getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams attributes = dialogWindow.getAttributes();
        attributes.width = (int) (defaultDisplay.getWidth() * 0.846);
        dialogWindow.setAttributes(attributes);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener != null){
                    onClickListener.onNegative();
                }
            }
        });



        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onPositive();
            }
        });

        switch (type){
            case 0:
                initNoTitleDialog();
                break;
            case 1 :
                initTitleDialog();
                break;
        }
    }

    private void initTitleDialog() {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
        tvMsg.setText(msg);
    }


    private void initNoTitleDialog() {
        tvTitle.setVisibility(View.GONE);
        tvMsg.setText(msg);
    }

    private OnClickListener onClickListener;


    public interface OnClickListener{
        void onPositive();
        void onNegative();
    }

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }


    /**方法调用应在show之后*/
    public void setButtonText(String leftText,String rightText){
        btnLeft.setText(leftText);
        btnRight.setText(rightText);
    }
}
