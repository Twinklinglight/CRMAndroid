package com.wtcrmandroid.activity.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.adapter.listview.GetMoneyAtAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.model.GetMoneyData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 回款到单
 *
 * @author zxd
 * @date 2017/6/8
 */

public class GetMoneyActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_getmoney_save)
    TextView mTvGetmoneySave;       //完成保存
    @BindView(R.id.lv_getmoney)
    ListView mLvGetmoney;           //列表
    private List<GetMoneyData> mDataList;
    private GetMoneyAtAdapter mMoneyAtAdapter;


    @Override
    protected int layout() {
        return R.layout.activity_get_money;
    }

    @Override
    protected void initview() {
        mTitlebar.setTitletext("回款到单");
        mDataList = new ArrayList<>();
        GetMoneyData getMoneyData = new GetMoneyData();
        getMoneyData.setCustomerName("张三");
        mDataList.add(getMoneyData);

        mMoneyAtAdapter = new GetMoneyAtAdapter(this, mDataList);
        mLvGetmoney.setAdapter(mMoneyAtAdapter);

        View footview = LayoutInflater.from(this).inflate(R.layout.item_xrz_foot, null);
        ViewHolder viewHolder = new ViewHolder(footview);
        footview.setTag(footview);
        mLvGetmoney.addFooterView(footview);

        viewHolder.mRlAddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetMoneyData getMoneyData1 = new GetMoneyData();
                mDataList.add(getMoneyData1);
                mMoneyAtAdapter.notifyDataSetChanged();
            }
        });
    }


    @OnClick(R.id.tv_getmoney_save)
    public void onViewClicked() {
    }

    @Override
    public void returnData(int key, Object data) {

    }

    static class ViewHolder {
        @BindView(R.id.rl_addjob)
        RelativeLayout mRlAddjob;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
