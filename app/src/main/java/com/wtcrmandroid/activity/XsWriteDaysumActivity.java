package com.wtcrmandroid.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.WriteDaySumAdapter;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.model.WriteDaysumData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 销售写日总结
 *
 * @author zxd
 * @date 2017/6/13
 */

public class XsWriteDaysumActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_daypsum_date)
    TextView mTvDaypsumDate;            //日志日期
    @BindView(R.id.ib_daypsum_calender)
    ImageButton mIbDaypsumCalender;     //选择日期的按钮
    @BindView(R.id.lv_xs_daypsum)
    ListView mLvXsDaypsum;              //日志列表
    @BindView(R.id.tv_daysum_submit)
    TextView mTvDaysumSubmit;           //提交按钮
    private WriteDaySumAdapter mDaySumAdapter;
    private List<WriteDaysumData> mDataList;

    public static final int ADDCUSTOMER = 0;
    public static final int BACKMONEY = 1;
    public static final int WORKLOAD = 2;
    public static final int GETSINGLE = 3;

    @Override
    protected int layout() {
        return R.layout.activity_xs_write_daysum;
    }

    @Override
    protected void initview() {

        mTitlebar.setTitletext("写日总结");
        mDataList = new ArrayList<>();
        WriteDaysumData daysumData = new WriteDaysumData();
        daysumData.setWorkSort("A类");
        mDataList.add(daysumData);
        mDaySumAdapter = new WriteDaySumAdapter(this, mDataList);
        mLvXsDaypsum.setAdapter(mDaySumAdapter);

        View view = LayoutInflater.from(this).inflate(R.layout.item_xs_xrzj_foot, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        mLvXsDaypsum.addFooterView(view);

        //再增加一项
        viewHolder.mLlDaysumAddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteDaysumData writeDaysumData = new WriteDaysumData();
                writeDaysumData.setWorkSort("B类");
                mDataList.add(writeDaysumData);
                mDaySumAdapter.notifyDataSetChanged();
            }
        });

        //新增意向客户
        viewHolder.mRlAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(XsWriteDaysumActivity.this,
                        AddPurposeCustomerActivity.class),ADDCUSTOMER);
            }
        });

        //今日工作量
        viewHolder.mRlDaysumWorkload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(XsWriteDaysumActivity.this,
                        TodayWorkLoadActivity.class),WORKLOAD);
            }
        });

        //回款到单情况
        viewHolder.mRlMoneyAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(XsWriteDaysumActivity.this,
                        GetMoneyActivity.class),BACKMONEY);
            }
        });

        //预测单客户踩中
        viewHolder.mRlSingleAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(XsWriteDaysumActivity.this,
                        GetSingleCustomerActivity.class),GETSINGLE);
            }
        });

    }

    //返回值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case WORKLOAD:
                break;
            case BACKMONEY:
                break;
            case GETSINGLE:
                break;
            case ADDCUSTOMER:
                break;
            default:
                break;
        }
    }

    @Override
    public void returnData(int key, Object data) {

    }

    static class ViewHolder {
        @BindView(R.id.ll_daysum_addjob)
        LinearLayout mLlDaysumAddjob;
        @BindView(R.id.rl_daysum_workload)
        RelativeLayout mRlDaysumWorkload;
        @BindView(R.id.rl_money_analysis)
        RelativeLayout mRlMoneyAnalysis;
        @BindView(R.id.rl_single_analysis)
        RelativeLayout mRlSingleAnalysis;
        @BindView(R.id.rl_add_customer)
        RelativeLayout mRlAddCustomer;
        @BindView(R.id.et_daysum_sum)
        EditText mEtDaysumSum;
        @BindView(R.id.ib_daysum_sumyuyin)
        ImageButton mIbDaysumSumyuyin;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
