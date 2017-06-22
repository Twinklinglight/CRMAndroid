package com.wtcrmandroid.fragment.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.AddressBookAdapter;
import com.wtcrmandroid.custompricing.QuickIndexView;
import com.wtcrmandroid.fragment.BaseFragmengt;

import butterknife.BindView;

/**
 * Created by 1363655717 on 2017-06-01.
 */

public class AddressBookFragment extends BaseFragmengt {
    @BindView(R.id.rv_view)
    RecyclerView rvView;
    @BindView(R.id.qiv_view)
    QuickIndexView qivView;
    private AddressBookAdapter adapter;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_address_book;
    }

    @Override
    protected void init() {
        rvView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvView.setAdapter(adapter = new AddressBookAdapter(getActivity()));

    }


    @Override
    public void returnData(int key, Object data) {

    }


}
