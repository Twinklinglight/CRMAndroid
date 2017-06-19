package com.wtcrmandroid.dialog;

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

/**
 * Created by zxd on 2017/6/16
 */

public class MyPhoneNumberDialog extends Dialog {

    @BindView(R.id.et_phone_number)
    EditText mEtPhoneNumber;
    @BindView(R.id.tv_cancle)
    TextView mTvCancle;
    @BindView(R.id.tv_sure)
    TextView mTvSure;
    private Context mContext;
    private MyPhoneNumber mPhoneNumber;

    public MyPhoneNumberDialog(@NonNull Context context,MyPhoneNumber number) {
        super(context, R.style.WorkSortDialog);
        this.mContext = context;
        this.mPhoneNumber = number;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_my_phonenumber);
        ButterKnife.bind(this);
        initWindowParams();

        //点击确定
        mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_number = mEtPhoneNumber.getText().toString();
                if (phone_number != null){
                    mPhoneNumber.getPhoneNumber(phone_number);
                    dismiss();
                }
            }
        });

        //点击取消
        mTvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
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

    public interface MyPhoneNumber{
        void getPhoneNumber(String phonenumber);
    }


}
