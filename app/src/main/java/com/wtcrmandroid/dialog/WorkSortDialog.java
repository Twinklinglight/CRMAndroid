package com.wtcrmandroid.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wtcrmandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择工作类型：A类、B类、C类、D类
 * Created by zxd on 2017/6/2.
 */

public class WorkSortDialog extends Dialog {

    @BindView(R.id.work_sort_rg)
    RadioGroup mWorkSortRg;
    @BindView(R.id.bt_worksort_dialog_sure)
    Button mBtWorksortDialogSure;
    @BindView(R.id.tv_A)
    TextView mTvA;
    @BindView(R.id.tv_B)
    TextView mTvB;
    @BindView(R.id.tv_C)
    TextView mTvC;
    @BindView(R.id.tv_D)
    TextView mTvD;
    @BindView(R.id.tv_hint_A)
    TextView mTvHintA;
    @BindView(R.id.tv_hint_B)
    TextView mTvHintB;
    @BindView(R.id.tv_hint_C)
    TextView mTvHintC;
    @BindView(R.id.tv_hint_D)
    TextView mTvHintD;
    @BindView(R.id.rb_A)
    RadioButton mRbA;
    @BindView(R.id.rb_B)
    RadioButton mRbB;
    @BindView(R.id.rb_C)
    RadioButton mRbC;
    @BindView(R.id.rb_D)
    RadioButton mRbD;
    private Context mContext;

    public WorkSortDialog(Context context) {
        super(context, R.style.WorkSortDialog);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_work_sort);
        ButterKnife.bind(this);
        initWindowParams();
        mWorkSortRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_A:
                        Toast.makeText(mContext, "选A", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_B:
                        Toast.makeText(mContext, "选B", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_C:
                        Toast.makeText(mContext, "选C", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_D:
                        Toast.makeText(mContext, "选D", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

        //A改变
        mRbA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    mTvA.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
                    mTvHintA.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimary));

                }else {

                    mTvA.setTextColor(ContextCompat.getColor(mContext,R.color.colorIconText));
                    mTvHintA.setTextColor(ContextCompat.getColor(mContext,R.color.colorContext));

                }
            }
        });

        //B改变
        mRbB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    mTvB.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
                    mTvHintB.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimary));

                }else {

                    mTvB.setTextColor(ContextCompat.getColor(mContext,R.color.colorIconText));
                    mTvHintB.setTextColor(ContextCompat.getColor(mContext,R.color.colorContext));

                }
            }
        });

        //C改变
        mRbC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    mTvC.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
                    mTvHintC.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimary));

                }else {

                    mTvC.setTextColor(ContextCompat.getColor(mContext,R.color.colorIconText));
                    mTvHintC.setTextColor(ContextCompat.getColor(mContext,R.color.colorContext));

                }
            }
        });

        //D改变
        mRbD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    mTvD.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
                    mTvHintD.setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimary));

                }else {

                    mTvD.setTextColor(ContextCompat.getColor(mContext,R.color.colorIconText));
                    mTvHintD.setTextColor(ContextCompat.getColor(mContext,R.color.colorContext));

                }
            }
        });

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

    @OnClick({R.id.bt_worksort_dialog_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_worksort_dialog_sure:
                Toast.makeText(mContext, "确定", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
        }
    }

}
