package com.wtcrmandroid.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.wtcrmandroid.base.BaseFragment;
import com.wtcrmandroid.base.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.aboutmy.MycardActivity;
import com.wtcrmandroid.adapter.recycleview.AddressBookAdapter;
import com.wtcrmandroid.adapter.recycleview.HeaderRecyclerAndFooterWrapperAdapter;
import com.wtcrmandroid.adapter.recycleview.ViewHolder;
import com.wtcrmandroid.contacts.ContactSearchActivity;
import com.wtcrmandroid.contacts.DepartmentActivity;
import com.wtcrmandroid.model.reponsedata.ContactRP;
import com.wtcrmandroid.model.reponsedata.Headbean;
import com.wtcrmandroid.model.requestdata.MineRQ;
import com.wtcrmandroid.view.DividerItemDecoration;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 1363655717 on 2017-06-01.
 */

public class ContactsFragment extends BaseFragment<ContactPresenter,List<ContactRP>> implements AddressBookAdapter.ContactListener {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.indexBar)
    IndexBar indexBar;
    @BindView(R.id.tvSideBarHint)
    TextView tvSideBarHint;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    private LinearLayoutManager manager;
    private List<ContactRP> mlist;
    private SuspensionDecoration mDecoration;
    private AddressBookAdapter adapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;


    @Override
    protected int Rlayout() {
        return R.layout.fragment_address_book;
    }

    @Override
    public void init() {

        presenter = new ContactPresenter(this,getContext());
        MineRQ mineRQ = new MineRQ();
        mineRQ.setUserId(MyApplication.application.getLoginData().getUserID());
        presenter.postContactAll(mineRQ);

        //点击搜索
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mlist == null)return;
                Intent intent = new Intent(getContext(), ContactSearchActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ContactData",(Serializable) mlist);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }


    @Override
    public void returnData(int key, List<ContactRP> data) {
        mlist = data;
        manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        adapter = new AddressBookAdapter(getContext());
        adapter.setListener(this);
        adapter.setMlist(data);

        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(adapter){

            @Override
            protected void onBindHeaderHolder(ViewHolder holder, final int headerPos, int layoutId, Object o) {
                switch (layoutId){
                    case R.layout.item_address_book_head:
                        Headbean headbean = (Headbean)o;

                        //进入群组
                        holder.setOnClickListener(R.id.ll_group, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getContext(), "点击了群组"+headerPos, Toast.LENGTH_SHORT).show();
                            }
                        });

                        //进入部门
                        holder.setOnClickListener(R.id.ll_department, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getContext(), DepartmentActivity.class));
                            }
                        });
                        holder.setText(R.id.tv_dp,headbean.getTag());
                        break;
                }
            }
        };
        mHeaderAdapter.setHeaderView(0, R.layout.item_address_book_head, new Headbean("部门"));
        rv.setAdapter(mHeaderAdapter);

        rv.addItemDecoration(mDecoration = new SuspensionDecoration(getContext(), data).setHeaderViewCount(1));
        //如果add两个，那么按照先后顺序，依次渲染。
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));


        //indexbar初始化
        indexBar.setmPressedShowTextView(tvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(manager)//设置RecyclerView的LayoutManager
                .setHeaderViewCount(1);

        indexBar.setmSourceDatas(data)//设置数据
                .invalidate();

        mDecoration.setmDatas(data);
    }


    @Override
    public void itemClick(int position) {
        Intent intent = new Intent(getContext(), MycardActivity.class);
        ContactRP contactRP = mlist.get(position);
        intent.putExtra("username", contactRP.getUserName());
        intent.putExtra("rolename", contactRP.getRoleName());
        intent.putExtra("phonenumber", contactRP.getTelphone());
        intent.putExtra("imageUrl", contactRP.getHeaderimg());
        Log.i("imagUrl","Main imageUrl = "+contactRP.getHeaderimg());
        startActivity(intent);
    }
}
