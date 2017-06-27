package com.wtcrmandroid.contacts;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.adapter.recycleview.ExpandableRecycleViewAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/21.
 * 通讯录部门界面
 */

public class AddressBookDepartmentActivity extends BaseActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.rv_view)
    RecyclerView rvView;
    private ExpandableRecycleViewAdapter adapter;
    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_address_book_department;
    }

    @Override
    protected void initview() {
        titlebar.setTitletext("部门");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvView.setLayoutManager(new LinearLayoutManager(this));

        rvView.setAdapter(adapter = new ExpandableRecycleViewAdapter(this));
    }


}
