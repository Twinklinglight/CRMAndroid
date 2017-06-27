package com.wtcrmandroid.activity.field;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.adapter.recycleview.BaseRecycleAdapter;
import com.wtcrmandroid.adapter.recycleview.CustomerCallSituationAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/19.
 * 客户拜访情况
 */

public class CustomerCallSituationActivity extends BaseActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.rv_view)
    RecyclerView rvView;
    private BaseRecycleAdapter adapter;
    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_customer_call_situation;
    }

    @Override
    protected void initview() {
        titlebar.setTitletext("客户拜访情况");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvView.setLayoutManager(new LinearLayoutManager(this));
        rvView.setAdapter(adapter = new CustomerCallSituationAdapter(this));
    }


}
