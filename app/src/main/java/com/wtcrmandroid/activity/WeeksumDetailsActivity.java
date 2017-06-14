package com.wtcrmandroid.activity;

import android.widget.ListView;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.WeeksumDetailsAdapter;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.model.WeeksumDetailsData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
/**
 *
** 周总计详情
*  @author zxd
*  @date 2017/6/12
*/

public class WeeksumDetailsActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_weeksum)
    TextView mTvWeeksum;            //详情日期
    @BindView(R.id.lv_weeksum)
    ListView mLvWeeksum;
    private WeeksumDetailsAdapter mAdapter;
    private List<WeeksumDetailsData>mDetailsDatas;

    @Override
    protected int layout() {
        return R.layout.activity_weeksum_details;
    }

    @Override
    protected void initview() {
        mDetailsDatas = new ArrayList<>();
        mAdapter = new WeeksumDetailsAdapter(this,mDetailsDatas);
        mLvWeeksum.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void returnData(int key, Object data) {

    }
}
