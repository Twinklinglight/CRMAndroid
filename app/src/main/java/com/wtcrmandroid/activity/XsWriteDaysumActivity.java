package com.wtcrmandroid.activity;

import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.http.retrofit2.data.BaseData;

import butterknife.BindView;
/**
*  销售写日总结
*  @author zxd
*  @date 2017/6/13
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

    @Override
    protected int layout() {
        return R.layout.activity_xs_write_daysum;
    }

    @Override
    protected void initview() {

    }

    @Override
    public void returnData(int key, BaseData data) {

    }
}
