package com.wtcrmandroid.activity.field;

import android.hardware.Sensor;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.wtcrmandroid.BaseMapActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.PhotoChooseAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/16.
 * 客户拜访
 */

public class CustomerCallActivity extends BaseMapActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rv_view)
    RecyclerView rvView;
    private PhotoChooseAdapter adapter;

    private List<String> photo_list=new ArrayList<>();
    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_customer_call;
    }

    @Override
    protected void initview() {
        titlebar.setTitletext("客户拜访");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvView.setLayoutManager(new GridLayoutManager(this, 4));
        rvView.setAdapter(adapter = new PhotoChooseAdapter());
        photo_list.add("");
        adapter.setList(photo_list);
    }

    @Override
    protected void getAddress(BDLocation location) {
        tvAddress.setText("当前位置： "+location.getLocationDescribe());

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
