package com.wtcrmandroid.fragment.journalmanager;

import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.view.dialog.CommentDialog;
import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.view.listview.MyListView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 部门员工日志 销售某天日志详情
 * Created by zxd on 2017/6/23
 */

public class DepartXsDayjournalFragment extends BaseFragment implements CommentDialog.submitListener {


    @BindView(R.id.tv_date)
    TextView mTvDate;                       //时间显示textview
    @BindView(R.id.lv_dayplan_details)
    MyListView mLvDayplanDetails;           //工作计划列表
    @BindView(R.id.lv_single_analysis)
    MyListView mLvSingleAnalysis;           //预测到单客户列表
    @BindView(R.id.lv_major_genjin)
    MyListView mLvMajorGenjin;              //重点意向客户列表
    @BindView(R.id.lv_daysum_details)
    MyListView mLvDaysumDetails;            //工作总结列表
    @BindView(R.id.tv_phone_count)
    TextView mTvPhoneCount;                 //有效电话数量
    @BindView(R.id.tv_shangmen_count)
    TextView mTvShangmenCount;              //有效上门
    @BindView(R.id.tv_A_count)
    TextView mTvACount;                     //A类客户库存
    @BindView(R.id.tv_B_count)
    TextView mTvBCount;                     //B类客户库存
    @BindView(R.id.tv_addA_count)
    TextView mTvAddACount;                  //新增A类
    @BindView(R.id.tv_addB_count)
    TextView mTvAddBCount;                  //新增B类
    @BindView(R.id.tv_krl_count)
    TextView mTvKrlCount;                   //库容量
    @BindView(R.id.tv_customer_name)
    TextView mTvCustomerName;               //客户名称
    @BindView(R.id.tv_customer_type)
    TextView mTvCustomerType;               //客户类型
    @BindView(R.id.tv_product_type)
    TextView mTvProductType;                //产品类型
    @BindView(R.id.tv_getmoney)
    TextView mTvGetmoney;                   //回款金额
    @BindView(R.id.lv_ifget_single)
    MyListView mLvIfgetSingle;              //预测到单客户是否踩中列表
    @BindView(R.id.lv_add_customer)
    MyListView mLvAddCustomer;              //新增意向客户列表
    @BindView(R.id.lv_comment)
    MyListView mLvComment;                  //评论列表
    Unbinder unbinder;

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int Rlayout() {
        return R.layout.fragment_depart_xsdayjournal;
    }

    @Override
    protected void init() {

    }


    @OnClick({R.id.iv_calender, R.id.tv_write_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_calender:
                break;
            case R.id.tv_write_comment:
                new CommentDialog(getContext(),this).show();
                break;
        }
    }

    //提交评论回调
    @Override
    public void clickOk(String leve, String context) {

    }
}
