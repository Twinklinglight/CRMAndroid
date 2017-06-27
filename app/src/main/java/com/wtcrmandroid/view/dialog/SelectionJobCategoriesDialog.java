package com.wtcrmandroid.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.utils.DensityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017-06-03.
 * 选择工作分类对话框
 */
public class SelectionJobCategoriesDialog extends Dialog {

    @BindView(R.id.tv_hint_A)
    TextView tvHintA;
    @BindView(R.id.tv_hint_B)
    TextView tvHintB;
    @BindView(R.id.tv_hint_C)
    TextView tvHintC;
    @BindView(R.id.tv_hint_D)
    TextView tvHintD;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.rb_A)
    RadioButton rbA;
    @BindView(R.id.rb_B)
    RadioButton rbB;
    @BindView(R.id.rb_C)
    RadioButton rbC;
    @BindView(R.id.rb_D)
    RadioButton rbD;

    public Context context;
    private WorkLinstener listener;
    private int position;
    private int tag;

    public SelectionJobCategoriesDialog(@NonNull Context context,WorkLinstener listener,int position,int tag) {
        super(context,R.style.Dialog);
        this.context = context;
        this.listener = listener;
        this.position = position;
        this.tag = tag;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_selection_job_categories);
        ButterKnife.bind(this);
        initDiaglog();

        switch (tag){
            case 1:
                rbA.setChecked(true);
                tvHintA.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                break;
            case 2:
                rbB.setChecked(true);
                tvHintB.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                break;
            case 3:
                rbC.setChecked(true);
                tvHintC.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                break;
            case 4:
                rbD.setChecked(true);
                tvHintD.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                break;
        }

        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    private void initDiaglog() {

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
//        lp.height = DensityUtils.dp2px(context, 200);
        lp.width = DensityUtils.dp2px(context, 300);
        win.setAttributes(lp);
    }

    @OnClick({R.id.rb_A, R.id.rb_B, R.id.rb_C, R.id.rb_D})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_A:
                listener.WorkSelect("A类 紧急又重要",position);
                dismiss();
                break;
            case R.id.rb_B:
                listener.WorkSelect("B类 较重要",position);
                dismiss();
                break;
            case R.id.rb_C:
                listener.WorkSelect("C类 重要",position);
                dismiss();
                break;
            case R.id.rb_D:
                listener.WorkSelect("D类 次重要",position);
                dismiss();
                break;
        }
    }

    public interface WorkLinstener{
        void WorkSelect(String workSort,int position);
    }
}
