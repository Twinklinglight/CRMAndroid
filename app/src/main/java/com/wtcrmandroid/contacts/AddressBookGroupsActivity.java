package com.wtcrmandroid.contacts;

import android.view.View;
import android.widget.ExpandableListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.adapter.expandablelistview.ExpandableListViewAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/21.
 * 通讯录群组界面
 */

public class AddressBookGroupsActivity extends BaseActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.elv_view)
    ExpandableListView elvView;

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_address_book_groups;
    }

    @Override
    protected void initview() {
        titlebar.setTitletext("群组");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        elvView.setAdapter(new ExpandableListViewAdapter(this));
    }


}
