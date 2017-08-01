package com.wtcrmandroid.activity.journalmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.cloud.thirdparty.T;
import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.base.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.present.XsWriteDayplanPresenter;
import com.wtcrmandroid.adapter.listview.WriteDayPlanAdapter;
import com.wtcrmandroid.model.reponsedata.MajorCustomerData;
import com.wtcrmandroid.model.reponsedata.SingleCustomerData;
import com.wtcrmandroid.model.reponsedata.WriteDayplanData;
import com.wtcrmandroid.model.requestdata.XsWriteDayplanRQ;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.dialog.CalendarDialog;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 销售写日计划activity
 *
 * @author zxd
 * @date 2017/6/14
 */

public class XsWriteDayplanActivity extends BaseActivity<XsWriteDayplanPresenter, List<T>> implements CalendarDialog.CalendarListener {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_dayplan_date)
    TextView mTvDayplanDate;            //日志日期
    @BindView(R.id.lv_xs_dayplan)
    ListView mLvXsDayplan;              //日计划列表
    private WriteDayPlanAdapter mWriteDayPlanAdapter;
    private List<WriteDayplanData> mDataList;
    private List<SingleCustomerData> mSingleList = new ArrayList<>();
    private List<MajorCustomerData> mMajorList = new ArrayList<>();

    private ViewHolder viewHolder;

    public static final int SINGLE = 0;
    public static final int MAJOR = 1;
    public static final int RESUlT_CODE = 2;
    public static final int MAJOR_CODE = 3;

    private static final String TAG = "XsWriteDayplanActivity";

    private String DateSelect = "";  //显示日期
    private String subTime = "";

    @Override
    protected int layout() {
        return R.layout.activity_xs_write_dayplan;
    }

    @Override
    protected void initView() {

        presenter = new XsWriteDayplanPresenter(this,this);

        Date time = Calendar.getInstance().getTime();
        DateSelect = new SimpleDateFormat("yyyy-MM-dd EEEE").format(time);
        subTime = new SimpleDateFormat("yyyy-MM-dd").format(time);
        SetDateText(DateSelect);

        mTitlebar.setTitletext("写日计划");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XsWriteDayplanActivity.this.finish();
            }
        });
        mDataList = new ArrayList<>();
        WriteDayplanData dayplanData = new WriteDayplanData();
        dayplanData.setWorkSort("");
        mDataList.add(dayplanData);
        mWriteDayPlanAdapter = new WriteDayPlanAdapter(this, mDataList);
        mLvXsDayplan.setAdapter(mWriteDayPlanAdapter);

        View view = LayoutInflater.from(this).inflate(R.layout.item_xs_xrjh_foot, null);
        viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        mLvXsDayplan.addFooterView(view);
        //在增加一项
        viewHolder.mLlDayplanAddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteDayplanData dayplanData1 = new WriteDayplanData();
                dayplanData1.setWorkSort("");
                mDataList.add(dayplanData1);
                mWriteDayPlanAdapter.notifyDataSetChanged();

                Log.e("zxd", mDataList.toString());
            }
        });

        //预测到单客户情况分析
        viewHolder.mRlDayplanAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XsWriteDayplanActivity.this,
                        SingleCustomer.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("single",(Serializable) mSingleList);
                intent.putExtras(bundle);
                startActivityForResult(intent, SINGLE);
            }
        });

        //重点意向客户跟进情况
        viewHolder.mRlDayplanGenjin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XsWriteDayplanActivity.this,
                        MajorCustomerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("major",(Serializable)mMajorList);
                intent.putExtras(bundle);
                startActivityForResult(intent, MAJOR);
            }
        });
    }

    //返回结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SINGLE && resultCode == RESUlT_CODE) {

            mSingleList = (List<SingleCustomerData>)data.getSerializableExtra("SingleList");
            Log.i(TAG, "onClick: mSingleList = "+mSingleList.toString());
            if (mSingleList != null){
                viewHolder.tvSingle.setText("已完成");
            }else {
                viewHolder.tvSingle.setText("");
            }

        } else if (requestCode == MAJOR && resultCode == MAJOR_CODE) {
            
            mMajorList = (List<MajorCustomerData>)data.getSerializableExtra("MajorList");
            Log.i(TAG, "onViewClicked: mMajorList = "+mMajorList.toString());
            if (mMajorList != null){
                viewHolder.tvMajor.setText("已完成");
            }else {
                viewHolder.tvMajor.setText("");
            }
        }
    }

    @Override
    public void returnData(int key, List<T> data) {

        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @OnClick({R.id.ib_dayplan_calender, R.id.tv_dayplan_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_dayplan_calender:
                new CalendarDialog(XsWriteDayplanActivity.this, XsWriteDayplanActivity.this).show();
                break;
            case R.id.tv_dayplan_submit:
                if (mSingleList == null) {
                    Toast.makeText(this, "预测到单客户情况分析必须填写", Toast.LENGTH_SHORT).show();
                }else if (mMajorList == null){
                    Toast.makeText(this, "重点意向客户跟进情况必须填写", Toast.LENGTH_SHORT).show();
                }else {
                    XsWriteDayplanRQ xsWriteDayplanRQ = new XsWriteDayplanRQ();
                    xsWriteDayplanRQ.setUserId(MyApplication.application.getLoginData().getUserID());
                    xsWriteDayplanRQ.setType("day");
                    xsWriteDayplanRQ.setPlan(true);
                    xsWriteDayplanRQ.setWorkdetail(mDataList);
                    xsWriteDayplanRQ.setWorkdreamorder(mSingleList);
                    xsWriteDayplanRQ.setWorkfocus(mMajorList);
                    xsWriteDayplanRQ.setWorkRecordTime(subTime);
                    xsWriteDayplanRQ.setRoleClass(MyApplication.application.getLoginData().getRoleClass());
                    xsWriteDayplanRQ.setLearningAndReflection("");
                    presenter.postDayplan(xsWriteDayplanRQ);
                }
                break;
        }
    }

    @Override
    public void CalendarSelcet(String datetext, Date date) {
        SetDateText(datetext);
        DateSelect = datetext;
        subTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 返回的日期格式为：2017-6-27 星期二
     * 字符串拆封 合并；
     * @param date
     */
    //设置选中日期
    private void SetDateText(String date) {
        String[] split = date.split("-");
        String DateText = split[0] + "年" + split[1] + "月" +
                split[2].split(" ")[0] + "日" + " " + split[2].split(" ")[1];

        mTvDayplanDate.setText(DateText);
    }

    static class ViewHolder {
        @BindView(R.id.ll_dayplan_addjob)
        LinearLayout mLlDayplanAddjob;
        @BindView(R.id.rl_dayplan_analysis)
        RelativeLayout mRlDayplanAnalysis;
        @BindView(R.id.rl_dayplan_genjin)
        RelativeLayout mRlDayplanGenjin;
        @BindView(R.id.tv_single)
        TextView tvSingle;
        @BindView(R.id.tv_major)
        TextView tvMajor;
        

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
