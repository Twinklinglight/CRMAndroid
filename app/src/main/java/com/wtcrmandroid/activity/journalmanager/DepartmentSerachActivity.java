package com.wtcrmandroid.activity.journalmanager;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.BaseRecycleAdapter;
import com.wtcrmandroid.adapter.recycleview.SearchAdapter;
import com.wtcrmandroid.model.reponsedata.DepartmentRponseData;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.utils.iat.Iat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017-06-07.
 * 部门员工日志管理搜索页
 */

public class DepartmentSerachActivity extends BaseActivity implements SearchAdapter.ClickListener {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.rec_search)
    RecyclerView recSearch;
    private List<DepartmentRponseData> searchData;
    private List<DepartmentRponseData> chooseList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private SearchAdapter searchAdapter;

    private static final String TAG = "DepartmentSerachActivit";
    @Override
    protected int layout() {
        return R.layout.activity_department_search;
    }

    @Override
    protected void initView() {
        searchData = (List<DepartmentRponseData>) getIntent().getExtras().getSerializable("searchData");

        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linearLayoutManager = new LinearLayoutManager(this);
        recSearch.setLayoutManager(linearLayoutManager);

        searchAdapter = new SearchAdapter(DepartmentSerachActivity.this,DepartmentSerachActivity.this);
        searchAdapter.setList(chooseList);
        recSearch.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();

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
                chooseList.clear();
                if (text.length()== 0) return;
                for (int i = 0; i < searchData.size(); i++) {
                    if (searchData.get(i).getUsername().contains(text)){
                        chooseList.add(searchData.get(i));
                    }
                }
                searchAdapter.setList(chooseList);
                searchAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    public void itemClick(int rollcalss, int userId) {
        if (rollcalss == 0) {    //0是销售、其他是后台

            Intent intent = new Intent(DepartmentSerachActivity.this, XsJournalDetails.class);
            intent.putExtra("userid", userId);
            startActivity(intent);

        } else {

            Intent intent = new Intent(DepartmentSerachActivity.this,
                    HtJournalDetails.class);
            intent.putExtra("userid", userId);
            startActivity(intent);
        }
    }
}
