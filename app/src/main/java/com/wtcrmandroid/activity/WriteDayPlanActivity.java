package com.wtcrmandroid.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.wtcrmandroid.R;
import com.wtcrmandroid.http.data.BaseData;
import com.wtcrmandroid.view.WorkSortDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WriteDayPlanActivity extends BaseActivity {

    @BindView(R.id.rl_dayplan_sort)
    RelativeLayout mRlDayplanSort;

    @Override
    protected int layout() {
        return R.layout.activity_write_day_plan;
    }

    @Override
    protected void initview() {

    }

    @Override
    public void returnData(int key, BaseData data) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.rl_dayplan_sort)
    public void onViewClicked() {

        WorkSortDialog workSortDialog = new WorkSortDialog(this);
        workSortDialog.show();
    }
}
