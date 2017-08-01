package com.wtcrmandroid.activity.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.base.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.WriteDayPlanAdapter;
import com.wtcrmandroid.model.reponsedata.WriteDayplanData;
import com.wtcrmandroid.model.requestdata.WDayPlanRQ;
import com.wtcrmandroid.activity.journalmanager.present.WriteDayPlanPresenter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.dialog.CalendarDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 写日计划activity
 *
 * @author zxd
 * @date 2017/6/8
 */

public class WriteDayPlanActivity extends BaseActivity<WriteDayPlanPresenter,Object> implements CalendarDialog.CalendarListener{


    @BindView(R.id.lv_write_dayplan)
    ListView mLvWriteDayplan;       //工作事项列表
    @BindView(R.id.tv_dayplan_date)
    TextView mTvDayplanDate;        //显示选择时间
    @BindView(R.id.ib_dayplan_calender)
    ImageButton mIbDayplanCalender; //日历弹窗按钮
    @BindView(R.id.tv_dayplan_submit)
    TextView mTvDayplanSubmit;      //提交
    @BindView(R.id.titlebar)
    TitleBar mTitlebar;             //标题

    private String DateSelect = "";  //显示日期
    private String subTime = "";

    private WriteDayPlanAdapter mDayPlanAdapter;
    private List<WriteDayplanData> mList;
    private ViewHolder1 mViewHolder1;

    @Override
    protected int layout() {
        return R.layout.activity_write_day_plan;
    }

    @Override
    protected void initView() {

        presenter = new WriteDayPlanPresenter(this,this);
        mTitlebar.setTitletext("写日计划");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteDayPlanActivity.this.finish();
            }
        });
        Date time = Calendar.getInstance().getTime();
        DateSelect = new SimpleDateFormat("yyyy-MM-dd EEEE").format(time);
        subTime = new SimpleDateFormat("yyyy-MM-dd").format(time);
        SetDateText(DateSelect);

        mList = new ArrayList<>();
        WriteDayplanData writeDayplanData = new WriteDayplanData();
        writeDayplanData.setWorkSort("");
        mList.add(writeDayplanData);
        mDayPlanAdapter = new WriteDayPlanAdapter(this, mList);
        mLvWriteDayplan.setAdapter(mDayPlanAdapter);
        mDayPlanAdapter.notifyDataSetChanged();

        if (mList.size() > 0) {

            View footview = LayoutInflater.from(this).inflate(R.layout.item_xrz_foot, null);
            mViewHolder1 = new ViewHolder1(footview);
            mLvWriteDayplan.addFooterView(footview);

            mDayPlanAdapter.notifyDataSetChanged();

            mViewHolder1.mllAddjob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    WriteDayplanData writeDayplanData1 = new WriteDayplanData();
                    writeDayplanData1.setWorkSort("");
                    mList.add(writeDayplanData1);
                    mDayPlanAdapter.notifyDataSetChanged();
                }
            });
        }

        mIbDayplanCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CalendarDialog(WriteDayPlanActivity.this, WriteDayPlanActivity.this).show();
            }
        });
    }

    @Override
    public void returnData(int key, Object data) {

        Toast.makeText(this,"提交成功",Toast.LENGTH_SHORT).show();
        WriteDayPlanActivity.this.finish();

    }

    //日期选择回调
    @Override
    public void CalendarSelcet(String datetext,Date data) {

        SetDateText(datetext);
        DateSelect = datetext;
        subTime = new SimpleDateFormat("yyyy-MM-dd").format(data);
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

    //提交日计划
    @OnClick(R.id.tv_dayplan_submit)
    public void onClick() {

        WDayPlanRQ wDayPlanRequstData = new WDayPlanRQ();
        wDayPlanRequstData.setUserId(MyApplication.application.getLoginData().getUserID());
        wDayPlanRequstData.setType("day");
        wDayPlanRequstData.setWorkRecordTime(subTime);
        wDayPlanRequstData.setLearningAndReflection("");
        wDayPlanRequstData.setPlan(true);
        wDayPlanRequstData.setWork(mList);

        presenter.SubDayPlan(wDayPlanRequstData);
    }

    static class ViewHolder1 {
        @BindView(R.id.rl_addjob)
        LinearLayout mllAddjob;

        ViewHolder1(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
