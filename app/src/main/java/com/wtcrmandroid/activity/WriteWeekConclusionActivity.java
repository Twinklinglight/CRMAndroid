package com.wtcrmandroid.activity;

import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.listview.WriterWeekPlaneAdapter;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.http.data.BaseData;
import com.wtcrmandroid.model.WriterWeekPlaneData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 1363655717 on 2017-06-05.
 * 写周总结
 */

public class WriteWeekConclusionActivity extends BaseActivity<BaseData> {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.lv_write_work_plan)
    ListView lvWriteWorkPlan;

    @Override
    public void returnData(int key, BaseData data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_write_week_plan;
    }

    @Override
    protected void initview() {
        titlebar.setTitletext("写周计划");
        WriterWeekPlaneData writerWeekPlaneData=new WriterWeekPlaneData();
        writerWeekPlaneData.setTvPlan("本周计划");
        List<WriterWeekPlaneData> list =new ArrayList<>();
        list.add(writerWeekPlaneData);
        lvWriteWorkPlan.setAdapter(new WriterWeekPlaneAdapter(this,list));

    }
}
