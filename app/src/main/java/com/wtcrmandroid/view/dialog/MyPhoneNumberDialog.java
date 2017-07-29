package com.wtcrmandroid.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
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
    EditText mEtPhoneNumber;            //输入框
    @BindView(R.id.tv_cancle)
    TextView mTvCancle;                 //取消
    @BindView(R.id.tv_sure)
    TextView mTvSure;                   //确定
    @BindView(R.id.tv_title)
    TextView mTvTitle;                  //标题


    private Context mContext;
    private MyPhoneNumber mPhoneNumber; //回调接口
    private String mBeginString;        //editText初始值
    private String mTitleText;          //弹窗标题
    private int type;                   //对话框类型，1联系方式、2签名


    public MyPhoneNumberDialog(Context context, MyPhoneNumber number,
                               String titletText,String editText,int Type) {
        super(context, R.style.WorkSortDialog);
        this.mContext = context;
        this.mPhoneNumber = number;
        this.mTitleText = titletText;
        this.mBeginString = editText;
        this.type = Type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_my_phonenumber);
        ButterKnife.bind(this);
        initWindowParams();
        initView();

        //点击确定
        mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_number = mEtPhoneNumber.getText().toString();
                if (phone_number != null) {
                    mPhoneNumber.getPhoneNumber(phone_number,type);
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

    private void initView() {

        mTvTitle.setText(mTitleText);
        mEtPhoneNumber.setText(mBeginString);

    }

    private void initWindowParams() {
        Window dialogWindow = getWindow();
        // 获取屏幕宽、高用
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        int width3 = dm.widthPixels;

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (width3 * 0.85); // 宽度设置为屏幕的0.65

        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);
    }

    public interface MyPhoneNumber {
        void getPhoneNumber(String phonenumber,int Type);
    }


}
