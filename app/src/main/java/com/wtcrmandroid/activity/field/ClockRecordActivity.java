package com.wtcrmandroid.activity.field;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseActivity;
import com.wtcrmandroid.adapter.recycleview.ClockRecordAdapter;
import com.wtcrmandroid.custompricing.TitleBar;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/17.
 * 打卡记录
 */

public class ClockRecordActivity extends BaseActivity {
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
    protected void initview() {
        titlebar.setTitletext("打卡记录");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvView.setLayoutManager(new LinearLayoutManager(this));

        rvView.setAdapter(adapter = new ClockRecordAdapter(this));
    }



}
