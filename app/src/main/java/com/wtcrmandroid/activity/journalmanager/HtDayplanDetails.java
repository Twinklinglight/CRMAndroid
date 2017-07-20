package com.wtcrmandroid.activity.journalmanager;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.activity.journalmanager.present.HtDayplanDetailsPresenter;
import com.wtcrmandroid.adapter.listview.HtDayplanDetailsAdapter;
import com.wtcrmandroid.model.requestdata.DayDetailsRQ;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.model.reponsedata.HtDayplanDetailsData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;

/**
 * 后台日计划详情activity
 *
 * @author zxd
 * @date 2017/6/9
 */
public class HtDayplanDetails extends BaseActivity<HtDayplanDetailsPresenter,List<HtDayplanDetailsData>> {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.iv_journal_type)
    ImageView mIvJournalType;       //日志类型图标
    @BindView(R.id.tv_journal_type)
    TextView mTvJournalType;        //日志类型内容
    @BindView(R.id.lv_dayplan_details)
    ListView mLvDayplanDetails;     //日志详情列表
    private HtDayplanDetailsAdapter mAdapter;

    private String DateTime;

    @Override
    protected int layout() {
        return R.layout.activity_ht_dayplan_details;
    }

    @Override
    protected void initView() {
        presenter = new HtDayplanDetailsPresenter(this,this);

        DateTime = getIntent().getStringExtra("dpdate");    //获取日志时间
        mTvJournalType.setText(setTitleString(DateTime));

        DayDetailsRQ dayDetailsRequestData = new DayDetailsRQ();
        dayDetailsRequestData.setNowDate(DateTime);
        dayDetailsRequestData.setUserId(MyApplication.application.getLoginData().getUserID());
        dayDetailsRequestData.setType("day");
        dayDetailsRequestData.setIsPlan("true");
        presenter.getDayPlanData(dayDetailsRequestData);

        mTitlebar.setTitletext("日志详情");

        //返回
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HtDayplanDetails.this.finish();
            }
        });

    }

    @Override
    public void returnData(int key, List<HtDayplanDetailsData> data) {

        mAdapter = new HtDayplanDetailsAdapter(this,data);
        mLvDayplanDetails.setDivider(null);
        mLvDayplanDetails.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    private String setTitleString(String dateTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d日");

        String TitleString = null;
        try {
            TitleString = format.format(simpleDateFormat.parse(dateTime))+"计划";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return TitleString;

    }
}
