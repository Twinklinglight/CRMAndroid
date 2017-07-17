package com.wtcrmandroid.activity.field;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.BaseRecycleAdapter;
import com.wtcrmandroid.adapter.recycleview.MyCallRecordAdapter;
import com.wtcrmandroid.model.reponsedata.PersonalAllRecordRP;
import com.wtcrmandroid.presenter.activity.DayVisitDetailsP;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/19.
 * 客户拜访详情
 */

public class CustomerVisitDetailsListActivity extends BaseActivity<DayVisitDetailsP,List<PersonalAllRecordRP>> {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.swipe_target)
    RecyclerView rvView;
    private BaseRecycleAdapter adapter;
    @Override
    public void returnData(int key, List<PersonalAllRecordRP> data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_call_record;
    }

    @Override
    protected void initView() {
        titlebar.setTitletext("客户拜访详情");
        titlebar.setRightText("地图模式");
        titlebar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerVisitDetailsListActivity.this,CustomerVisitDetailsMapActivity.class));
            }
        });
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvView.setLayoutManager(new LinearLayoutManager(this));
        rvView.setAdapter(adapter=new MyCallRecordAdapter(this));
        presenter=new DayVisitDetailsP(this,this);

    }


}
