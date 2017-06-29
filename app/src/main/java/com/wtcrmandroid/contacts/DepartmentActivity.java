package com.wtcrmandroid.contacts;

import android.view.View;
import android.widget.ExpandableListView;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.expandablelistview.GroupEListViewAdapter;
import com.wtcrmandroid.model.ContactsChild;
import com.wtcrmandroid.model.ContactsGroup;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/21.
 * 通讯录部门界面
 */

public class DepartmentActivity extends BaseActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.elv)
    ExpandableListView elv;

    private List<ContactsGroup> mDataList;

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_contacts_department;
    }

    @Override
    protected void initView() {
        titlebar.setTitletext("部门");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDataList = new ArrayList<>();
        List<ContactsChild> arrayList = new ArrayList<>();
        arrayList.add(new ContactsChild("", "小", "小"));
        mDataList.add(new ContactsGroup("大", arrayList));
        arrayList.add(new ContactsChild("", "小", "小"));
        mDataList.add(new ContactsGroup("大大", arrayList));
        arrayList.add(new ContactsChild("", "小", "小"));
        mDataList.add(new ContactsGroup("大大大", arrayList));
        elv.setAdapter(new GroupEListViewAdapter(this, mDataList));
    }


}
