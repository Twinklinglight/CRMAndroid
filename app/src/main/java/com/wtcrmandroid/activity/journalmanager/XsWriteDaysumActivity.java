package com.wtcrmandroid.activity.journalmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.cloud.thirdparty.T;
import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.base.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.present.XsWriteDaysumPresenter;
import com.wtcrmandroid.adapter.listview.WriteDaySumAdapter;
import com.wtcrmandroid.model.reponsedata.AddPurpostCtAtData;
import com.wtcrmandroid.model.reponsedata.GetMoneyData;
import com.wtcrmandroid.model.reponsedata.GetSingleCustomerData;
import com.wtcrmandroid.model.reponsedata.WriteDaysumData;
import com.wtcrmandroid.model.requestdata.WorkOrder;
import com.wtcrmandroid.model.requestdata.XsWriteDaysumRQ;
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
 * 销售写日总结
 *
 * @author zxd
 * @date 2017/6/13
 */

public class XsWriteDaysumActivity extends BaseActivity<XsWriteDaysumPresenter, List<T>> implements CalendarDialog.CalendarListener {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_daypsum_date)
    TextView mTvDaypsumDate;            //日志日期
    @BindView(R.id.lv_xs_daypsum)
    ListView mLvXsDaypsum;              //日志列表

    private ViewHolder viewHolder;
    private String DateSelect = "";     //显示日期
    private String subTime = "";

    private WriteDaySumAdapter mDaySumAdapter;
    private List<WriteDaysumData> mDataList = new ArrayList<>();
    private List<AddPurpostCtAtData> addcustinfo = new ArrayList<>();    //新增意向客户
    private List<GetSingleCustomerData> workdream = new ArrayList<>();   //预测单客户是否踩中
    private List<GetMoneyData> workload = new ArrayList<>();             //汇款到单
    private WorkOrder workorder;                    //今日工作量

    public static final int ADDCUSTOMER = 0;
    public static final int BACKMONEY = 1;
    public static final int WORKLOAD = 2;
    public static final int GETSINGLE = 3;
    
    private boolean CanSubmit = false;

    @Override
    protected int layout() {
        return R.layout.activity_xs_write_daysum;
    }

    @Override
    protected void initView() {

        presenter = new XsWriteDaysumPresenter(this, this);

        Date time = Calendar.getInstance().getTime();
        DateSelect = new SimpleDateFormat("yyyy-MM-dd EEEE").format(time);
        subTime = new SimpleDateFormat("yyyy-MM-dd").format(time);
        SetDateText(DateSelect);

        mTitlebar.setTitletext("写日总结");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XsWriteDaysumActivity.this.finish();
            }
        });
        mDataList = new ArrayList<>();
        WriteDaysumData daysumData = new WriteDaysumData();
        daysumData.setWorkSort("");
        mDataList.add(daysumData);
        mDaySumAdapter = new WriteDaySumAdapter(this, mDataList);
        mLvXsDaypsum.setAdapter(mDaySumAdapter);

        View view = LayoutInflater.from(this).inflate(R.layout.item_xs_xrzj_foot, null);
        viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        mLvXsDaypsum.addFooterView(view);

        //再增加一项
        viewHolder.mLlDaysumAddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteDaysumData writeDaysumData = new WriteDaysumData();
                writeDaysumData.setWorkSort("");
                mDataList.add(writeDaysumData);
                mDaySumAdapter.notifyDataSetChanged();
            }
        });

        //新增意向客户
        viewHolder.mRlAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XsWriteDaysumActivity.this,
                        AddPurposeCustomerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("addcustinfo",(Serializable) addcustinfo);
                intent.putExtras(bundle);
                startActivityForResult(intent, ADDCUSTOMER);
            }
        });

        //今日工作量
        viewHolder.mRlDaysumWorkload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XsWriteDaysumActivity.this,
                        TodayWorkLoadActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("workorder",workorder);
                intent.putExtras(bundle);
                startActivityForResult(intent, WORKLOAD);
            }
        });

        //回款到单情况
        viewHolder.mRlMoneyAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XsWriteDaysumActivity.this,
                        GetMoneyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("workload",(Serializable)workload);
                intent.putExtras(bundle);
                startActivityForResult(intent, BACKMONEY);
            }
        });

        //预测单客户踩中
        viewHolder.mRlSingleAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XsWriteDaysumActivity.this,
                        GetSingleCustomerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("workdream",(Serializable)workdream);
                intent.putExtras(bundle);
                startActivityForResult(intent, GETSINGLE);
            }
        });

    }

    //返回值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case WORKLOAD:
                if (resultCode == 1)
                workorder = (WorkOrder) data.getExtras().getSerializable("TodayLoad");
                if (workorder != null){
                    viewHolder.tvWorkOrder.setText("已完成");
                }else {
                    viewHolder.tvWorkOrder.setText("");
                }
                break;
            case BACKMONEY:
                if (resultCode == 1)
                workload = (List<GetMoneyData>)data.getExtras().getSerializable("WorkLoad");
                if (workload.size() > 0){
                    viewHolder.tvWorkLoad.setText("已完成");
                }else {
                    viewHolder.tvWorkLoad.setText("");
                }
                break;
            case GETSINGLE:
                if (resultCode == 1)
                workdream = (List<GetSingleCustomerData>)data.getExtras().getSerializable("Single");
                if (workdream.size()> 0){
                    viewHolder.tvSingle.setText("已完成");
                }else {
                    viewHolder.tvSingle.setText("");
                }
                break;
            case ADDCUSTOMER:
                if (resultCode == 1)
                addcustinfo = ( List<AddPurpostCtAtData>)data.getExtras().getSerializable("NewCustomer");
                if (addcustinfo.size() > 0){
                    viewHolder.tvNewCustomer.setText("已完成");
                }else{
                    viewHolder.tvNewCustomer.setText("");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void returnData(int key, List<T> data) {

        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    //点击事件
    @OnClick({R.id.ib_daypsum_calender, R.id.tv_daysum_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_daypsum_calender:
                new CalendarDialog(XsWriteDaysumActivity.this, XsWriteDaysumActivity.this).show();
                break;
            case R.id.tv_daysum_submit:
                if (workorder == null){
                    CanSubmit = false;
                    Toast.makeText(this, "今日工作量必须填写", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    CanSubmit = true;
                }
                if (workload == null){
                    CanSubmit = false;
                    Toast.makeText(this, "今日回款到单情况必须填写", Toast.LENGTH_SHORT).show();
                }else {
                    CanSubmit = true;
                }
                if (CanSubmit){

                    XsWriteDaysumRQ xsWriteDaysumRQ = new XsWriteDaysumRQ();
                    xsWriteDaysumRQ.setUserId(MyApplication.application.getLoginData().getUserID());
                    xsWriteDaysumRQ.setType("day");
                    xsWriteDaysumRQ.setPlan(false);
                    xsWriteDaysumRQ.setRoleClass(0);
                    xsWriteDaysumRQ.setWorkdetail(mDataList);
                    xsWriteDaysumRQ.setAddcustinfo(addcustinfo);
                    xsWriteDaysumRQ.setWorkload(workload);
                    xsWriteDaysumRQ.setWorkorder(workorder);
                    xsWriteDaysumRQ.setWorkdream(workdream);
                    xsWriteDaysumRQ.setWorkRecordTime(subTime);
                    xsWriteDaysumRQ.setLearningAndReflection(viewHolder.mEtDaysumSum.getText().toString());

                    presenter.postDaysum(xsWriteDaysumRQ);
                }
                break;
        }
    }

    //日期选择的回调
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

        mTvDaypsumDate.setText(DateText);
    }

    static class ViewHolder {
        @BindView(R.id.ll_daysum_addjob)
        LinearLayout mLlDaysumAddjob;
        @BindView(R.id.rl_daysum_workload)
        RelativeLayout mRlDaysumWorkload;
        @BindView(R.id.tv_workorder)
        TextView tvWorkOrder;
        @BindView(R.id.rl_money_analysis)
        RelativeLayout mRlMoneyAnalysis;
        @BindView(R.id.tv_workload)
        TextView tvWorkLoad;
        @BindView(R.id.rl_single_analysis)
        RelativeLayout mRlSingleAnalysis;
        @BindView(R.id.tv_single)
        TextView tvSingle;
        @BindView(R.id.rl_add_customer)
        RelativeLayout mRlAddCustomer;
        @BindView(R.id.tv_newCustomer)
        TextView tvNewCustomer;
        @BindView(R.id.et_daysum_sum)
        EditText mEtDaysumSum;
        @BindView(R.id.ib_daysum_sumyuyin)
        ImageButton mIbDaysumSumyuyin;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
