package com.wtcrmandroid.view;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wtcrmandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择工作类型：A类、B类、C类
 * Created by zxd on 2017/6/2.
 */

public class WorkSortDialog extends Dialog {

    @BindView(R.id.work_sort_rg)
    RadioGroup mWorkSortRg;
    @BindView(R.id.bt_worksort_dialog_sure)
    Button mBtWorksortDialogSure;
    @BindView(R.id.bt_worksort_dialog_cancle)
    Button mBtWorksortDialogCancle;
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
                switch(checkedId){
                    case R.id.rb_A:
                        Toast.makeText(mContext, "选A", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_B:
                        Toast.makeText(mContext, "选B", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_C:
                        Toast.makeText(mContext, "选C", Toast.LENGTH_SHORT).show();

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

    @OnClick({R.id.bt_worksort_dialog_sure, R.id.bt_worksort_dialog_cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_worksort_dialog_sure:
                Toast.makeText(mContext, "确定", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.bt_worksort_dialog_cancle:
                Toast.makeText(mContext, "取消", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
        }
    }

}
