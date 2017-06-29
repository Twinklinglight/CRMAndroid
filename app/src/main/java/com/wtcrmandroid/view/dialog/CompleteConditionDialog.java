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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zxd on 2017/6/3.
 */

public class CompleteConditionDialog extends Dialog {

    @BindView(R.id.et_nocomplete_reason)
    EditText etNocompleteReason;
    @BindView(R.id.et_nocomplete_time)
    EditText etNocompleteTime;
    @BindView(R.id.ll_undone)
    LinearLayout llUndone;          //未完成布局
    @BindView(R.id.et_complete_details)
    EditText etCompleteDetails;
    @BindView(R.id.ll_done)
    LinearLayout llDone;            //完成布局
    @BindView(R.id.rg_complete)
    RadioGroup rgComplete;          //选择布局
    @BindView(R.id.rl_click)
    RelativeLayout rlClick;         //确定按钮

    private Context mContext;

    private CompeleteListener  completeListener;

    private int position;

    public CompleteConditionDialog(@NonNull Context context, CompeleteListener listener,int position) {
        super(context, R.style.Dialog);
        mContext = context;
        this.completeListener = listener;
        this.position = position;
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


    @OnClick({R.id.rb_done, R.id.rb_undone,R.id.tv_sure,R.id.tv_cancle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_done:
                if (rgComplete.getVisibility() == View.VISIBLE) {
                    llDone.setVisibility(View.VISIBLE);
                    rgComplete.setVisibility(View.INVISIBLE);
                    rlClick.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rb_undone:
                llUndone.setVisibility(View.VISIBLE);
                rgComplete.setVisibility(View.INVISIBLE);
                rlClick.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_sure:

                if (llDone.getVisibility() == View.VISIBLE){
                    String doneText = etCompleteDetails.getText().toString();
                    completeListener.completeCondition(doneText,position);
                }else {
                    String reasonText = etNocompleteReason.getText().toString();
                    String nextTime = etNocompleteTime.getText().toString();
                    completeListener.completeCondition(reasonText +" "+ nextTime,position);
                }
                dismiss();
                break;
            case R.id.tv_cancle:
                dismiss();
                break;
        }
    }

    public interface CompeleteListener{
        void completeCondition(String text,int position);
    }


}
