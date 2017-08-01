package com.wtcrmandroid.contacts;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.expandablelistview.GroupEListViewAdapter;
import com.wtcrmandroid.model.ContactsChild;
import com.wtcrmandroid.model.ContactsGroup;
import com.wtcrmandroid.utils.DensityUtils;
import com.wtcrmandroid.view.SMExpandListView.SMExpandableView;
import com.wtcrmandroid.view.SMExpandListView.SMRExpandView;
import com.wtcrmandroid.view.SMExpandListView.SwipeMenu;
import com.wtcrmandroid.view.SMExpandListView.SwipeMenuCreatorInterfaceUtil;
import com.wtcrmandroid.view.SMExpandListView.SwipeMenuItem;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/21.
 * 通讯录群组界面
 */

public class GroupsActivity extends BaseActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.elv_view)
    SMRExpandView mSMRExpandView;// 侧滑删除组ListView

    private List<ContactsGroup> mDataList;

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_address_book_groups;
    }

    @Override
    protected void initView() {
        titlebar.setTitletext("群组");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*mDataList = new ArrayList<>();
        List<ContactsChild> arrayList = new ArrayList<>();
        arrayList.add(new ContactsChild("", "小", "小"));
        mDataList.add(new ContactsGroup("大", arrayList));
        arrayList.add(new ContactsChild("", "小", "小"));
        mDataList.add(new ContactsGroup("大大", arrayList));
        arrayList.add(new ContactsChild("", "小", "小"));
        mDataList.add(new ContactsGroup("大大大", arrayList));
        mSMRExpandView.setAdapter(new GroupEListViewAdapter(this, mDataList));
        // 设置侧滑的选项
        SwipeMenuCreatorInterfaceUtil creator = new SwipeMenuCreatorInterfaceUtil() {
            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(DensityUtils.dp2px(GroupsActivity.this, 60));
                deleteItem.setTitle("解散");
                deleteItem.setTitleColor(Color.rgb(255, 255, 255));
                deleteItem.setTitleSize(15);
                menu.addMenuItem(deleteItem);
            }
        };
        mSMRExpandView.setMenuCreator(creator);

        // 侧滑的监听事件
        mSMRExpandView.setOnMenuItemClickListener(new SMExpandableView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int i, SwipeMenu swipeMenu, int i1) {
                mDataList.remove(i);
                mSMRExpandView.setAdapter(new GroupEListViewAdapter(GroupsActivity.this, mDataList));
                showShortToast("解散成功");
                return false;
            }
        });*/
    }


}
