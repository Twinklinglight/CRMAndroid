package com.wtcrmandroid.fragment.main;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.aboutmy.MycardActivity;
import com.wtcrmandroid.dialog.MyPhoneNumberDialog;
import com.wtcrmandroid.fragment.BaseFragmengt;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by 1363655717 on 2017-06-01.
 */

public class MyFragment extends BaseFragmengt implements MyPhoneNumberDialog.MyPhoneNumber {
    @BindView(R.id.ib_head_picture)
    ImageView mIbHeadPicture;         //头像按钮
    @BindView(R.id.tv_name)
    TextView mTvName;                   //姓名
    @BindView(R.id.tv_motto_modify)
    TextView mTvMottoModify;            //座右铭
    @BindView(R.id.tv_my_phonenumber)
    TextView mTvMyPhonenumber;          //联系方式
    @BindView(R.id.tv_my_department)
    TextView mTvMyDepartment;           //所在部门
    @BindView(R.id.tv_my_job)
    TextView mTvMyJob;                  //现任职位
    @BindView(R.id.tv_my_card)
    TextView mTvMyCard;                 //我的名片
    @BindView(R.id.tv_my_attendance)
    TextView mTvMyAttendance;           //我的出勤

    private int PhoneNumber = 1;
    private int Motto = 2;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void init() {

        //圆形图片
        Glide.with(this).load(R.mipmap.ic_my_headpicture)
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(mIbHeadPicture);

        //点击我的头像
        mIbHeadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //座右铭修改
        mTvMottoModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MyPhoneNumberDialog(getContext(),MyFragment.this,"签名",
                        mTvMottoModify.getText().toString(),Motto).show();
            }
        });

        //联系方式
        mTvMyPhonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyPhoneNumberDialog(getContext(),MyFragment.this,"联系方式",
                        mTvMyPhonenumber.getText().toString(),PhoneNumber).show();
            }
        });

        //我的名片
        mTvMyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MycardActivity.class));
            }
        });

        //我的考勤
        mTvMyAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public void returnData(int key, Object data) {

    }


    @Override
    public void getPhoneNumber(String phonenumber, int Type) {

        switch (Type){
            case 1:
                mTvMyPhonenumber.setText(phonenumber);
                break;
            case 2:
                mTvMottoModify.setText(phonenumber);
                break;
        }
    }
}
