package com.wtcrmandroid.activity.field;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.model.reponsedata.StaffPositionRP;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017-06-14.
 * 员工位置搜索
 */

public class StaffPositionSearchActivity extends BaseActivity {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv_view)
    RecyclerView rvView;
    private List<StaffPositionRP> list;
    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_search_staff_position;
    }

    @Override
    protected void initView() {
        list = (List<StaffPositionRP>) getIntent().getExtras().getSerializable("list");
    }

    @OnClick({R.id.iv_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                if(etSearch.getText().toString()==null||etSearch.getText().toString().equals("")){
                    showShortToast("请输入员工姓名");
                }else
                startActivity(new Intent(StaffPositionSearchActivity.this,EmployeesTrajectoryActivity.class).putExtra("name", etSearch.getText().toString()));
                break;
        }
    }
}
