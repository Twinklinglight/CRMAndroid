package com.wtcrmandroid.contacts;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.AddressBookAdapter;
import com.wtcrmandroid.model.reponsedata.ContactRP;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ContactSearchActivity extends BaseActivity {


    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rec_search)
    RecyclerView recSearch;

    private List<ContactRP> dataList;
    private List<ContactRP> selectData;
    private static final String TAG = "ContactSearchActivity";
    private AddressBookAdapter addressBookAdapter;

    @Override
    protected int layout() {
        return R.layout.activity_contact_search;
    }

    @Override
    protected void initView() {


        dataList = (List<ContactRP>) getIntent().getExtras().getSerializable("ContactData");
        recSearch.setLayoutManager(new LinearLayoutManager(this));
        addressBookAdapter = new AddressBookAdapter(this);
        selectData = new ArrayList<>();
        addressBookAdapter.setMlist(selectData);
        recSearch.setAdapter(addressBookAdapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                selectData.clear();
                if (text.length()== 0) return;
                for (int i = 0; i < dataList.size(); i++) {
                    if (dataList.get(i).getUserName().contains(text)){
                        selectData.add(dataList.get(i));
                    }
                }
                addressBookAdapter.setMlist(selectData);
                addressBookAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void returnData(int key, Object data) {

    }

    @OnClick(R.id.iv_left)
    public void onClick() {
        this.finish();
    }
}
