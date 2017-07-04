package com.wtcrmandroid.activity.field;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.ClockRecordAdapter;
import com.wtcrmandroid.model.requestdata.ListPersonSignInRQ;
import com.wtcrmandroid.presenter.activity.ClockRecordPresenter;
import com.wtcrmandroid.view.custompricing.TitleBar;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/17.
 * 外勤统计详情
 */

public class StatisticsDetailsActivity extends BaseActivity<ClockRecordPresenter,Object> {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.rv_view)
    RecyclerView rvView;
    private ClockRecordAdapter adapter;

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_clock_record;
    }

    @Override
    protected void initView() {
        titlebar.setTitletext("外勤统计详情");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvView.setLayoutManager(new LinearLayoutManager(this));

        rvView.setAdapter(adapter = new ClockRecordAdapter(this));
        presenter = new ClockRecordPresenter(this,this);
        ListPersonSignInRQ data=new ListPersonSignInRQ();

        data.setUserId(MyApplication.application.getLoginData().getUserID());
        data.setPageSize(1);
        presenter.sedPost(data,0);
    }



}
