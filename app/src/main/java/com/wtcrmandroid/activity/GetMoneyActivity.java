package com.wtcrmandroid.activity;

import android.widget.ListView;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.http.retrofit2.data.BaseData;

import butterknife.BindView;
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

    @Override
    public void returnData(int key, BaseData data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_get_money;
    }

    @Override
    protected void initview() {
        mTitlebar.setTitletext("回款到单");
    }


    @OnClick(R.id.tv_getmoney_save)
    public void onViewClicked() {
    }
}
