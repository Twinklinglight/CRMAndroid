package com.wtcrmandroid.main;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.aboutmy.MycardActivity;
import com.wtcrmandroid.model.reponsedata.MineRpData;
import com.wtcrmandroid.model.requestdata.MineRQ;
import com.wtcrmandroid.view.dialog.MyPhoneNumberDialog;
import com.wtcrmandroid.BaseFragment;

import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by 1363655717 on 2017-06-01.
 */

public class MineFragment extends BaseFragment<MinePresenter,List<MineRpData>> implements MyPhoneNumberDialog.MyPhoneNumber {
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

    private String username;
    private String rolename;
    private String phonenumber;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void init() {

        presenter = new MinePresenter(this,getContext());
        MineRQ mineRQ = new MineRQ();
        mineRQ.setUserId(MyApplication.application.getLoginData().getUserID());
        presenter.postData(mineRQ);

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

                new MyPhoneNumberDialog(getContext(),MineFragment.this,"签名",
                        mTvMottoModify.getText().toString(),Motto).show();
            }
        });

        //联系方式
        mTvMyPhonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyPhoneNumberDialog(getContext(),MineFragment.this,"联系方式",
                        mTvMyPhonenumber.getText().toString(),PhoneNumber).show();
            }
        });

        //我的名片
        mTvMyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MycardActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("rolename",rolename);
                intent.putExtra("phonenumber",phonenumber);
                startActivity(intent);
            }
        });

        //我的考勤
        mTvMyAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "暂无接入", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void returnData(int key, List<MineRpData> data) {
        if (data.size()!= 0){
            MineRpData mineRpData = data.get(0);
            username = mineRpData.getUsername();
            rolename = mineRpData.getRoleName();
            phonenumber = mineRpData.getTelPhone();

            mTvName.setText(username);
            mTvMyDepartment.setText(mineRpData.getDepartmentName());
            mTvMyJob.setText(rolename);
            mTvMyPhonenumber.setText(phonenumber);
        }

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
