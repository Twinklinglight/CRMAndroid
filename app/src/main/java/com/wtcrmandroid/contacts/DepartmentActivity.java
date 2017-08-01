package com.wtcrmandroid.contacts;

import android.view.View;
import android.widget.ExpandableListView;

import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.base.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.expandablelistview.GroupEListViewAdapter;
import com.wtcrmandroid.contacts.presenter.ContactDepartPresenter;
import com.wtcrmandroid.model.ContactsGroup;
import com.wtcrmandroid.model.reponsedata.ContactsDpmentRP;
import com.wtcrmandroid.model.requestdata.MineRQ;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/21.
 * 通讯录部门界面
 */

public class DepartmentActivity extends BaseActivity<ContactDepartPresenter,List<ContactsDpmentRP>> {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.elv)
    ExpandableListView elv;

    private List<ContactsGroup> mDataList;

    @Override
    public void returnData(int key, List<ContactsDpmentRP> data) {
        elv.setAdapter(new GroupEListViewAdapter(this, data));
    }

    @Override
    protected int layout() {
        return R.layout.activity_contacts_department;
    }

    @Override
    protected void initView() {
        presenter = new ContactDepartPresenter(this,this);
        MineRQ mineRQ = new MineRQ();
        mineRQ.setUserId(MyApplication.application.getLoginData().getUserID());
        presenter.postDepartment(mineRQ);

        titlebar.setTitletext("部门");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
