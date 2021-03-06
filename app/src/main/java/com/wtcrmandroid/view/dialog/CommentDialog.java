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
import android.widget.RadioButton;
import android.widget.TextView;

import com.wtcrmandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zxd on 2017/6/22
 */

public class CommentDialog extends Dialog {

    @BindView(R.id.rb_best)
    RadioButton mRbBest;
    @BindView(R.id.rb_better)
    RadioButton mRbBetter;
    @BindView(R.id.rb_ok)
    RadioButton mRbOk;
    @BindView(R.id.rb_bad)
    RadioButton mRbBad;
    @BindView(R.id.et_comment)
    EditText mEtComment;
    @BindView(R.id.tv_cancle)
    TextView mTvCancle;
    @BindView(R.id.tv_sure)
    TextView mTvSure;
    private Context mContext;

    private String level="良好";
    private String context = "";
    private submitListener listener;

    public CommentDialog(@NonNull Context context,submitListener submitListener) {
        super(context, R.style.Dialog);
        this.mContext = context;
        this.listener = submitListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_comment);
        ButterKnife.bind(this);
        initWindowParams();
    }

    @OnClick({R.id.rb_best, R.id.rb_better, R.id.rb_ok, R.id.rb_bad, R.id.tv_cancle, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_best:
                level = "优秀";
                break;
            case R.id.rb_better:
                level = "良好";
                break;
            case R.id.rb_ok:
                level = "一般";
                break;
            case R.id.rb_bad:
                level = "差";
                break;
            case R.id.tv_cancle:
                dismiss();
                break;
            case R.id.tv_sure:
                context = mEtComment.getText().toString();
                listener.clickOk(level,context);
                dismiss();
                break;
        }
    }

    public interface submitListener{
        void clickOk(String leve,String context);
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
}
