package com.wtcrmandroid.activity;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.HtDayplanDetailsAdapter;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.model.HtDayplanDetailsData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 后台日计划详情activity
 *
 * @author zxd
 * @date 2017/6/9
 */
public class HtDayplanDetails extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.iv_journal_type)
    ImageView mIvJournalType;       //日志类型图标
    @BindView(R.id.tv_journal_type)
    TextView mTvJournalType;        //日志类型图标
    @BindView(R.id.lv_dayplan_details)
    ListView mLvDayplanDetails;     //日志详情列表
    private HtDayplanDetailsAdapter mAdapter;
    private List<HtDayplanDetailsData>mData;

    @Override
    protected int layout() {
        return R.layout.activity_ht_dayplan_details;
    }

    @Override
    protected void initview() {
        mTitlebar.setTitletext("日志详情");
        getData();
        mAdapter = new HtDayplanDetailsAdapter(this,mData);
        mLvDayplanDetails.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


    public void getData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HtDayplanDetailsData htDayplanDetailsData = new HtDayplanDetailsData();
            htDayplanDetailsData.setWorkSort("A");
            htDayplanDetailsData.setWorkPerson("张三");
            htDayplanDetailsData.setWorkPercent("50%");
            htDayplanDetailsData.setWorkContent("就是个这");

            mData.add(htDayplanDetailsData);
        }
    }

    @Override
    public void returnData(int key, Object data) {

    }
}
