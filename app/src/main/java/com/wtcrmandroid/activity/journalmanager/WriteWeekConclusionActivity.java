package com.wtcrmandroid.activity.journalmanager;

import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseActivity;
import com.wtcrmandroid.adapter.listview.WriterWeekConclusionAdapter;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.model.WriterWeekPlaneData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 1363655717 on 2017-06-05.
 * 写周总结
 */

public class WriteWeekConclusionActivity extends BaseActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.lv_write_work_plan)
    ListView lvWriteWorkPlan;


    @Override
    protected int layout() {
        return R.layout.activity_write_week_plan;
    }

    @Override
    protected void initview() {
        titlebar.setTitletext("写周总结");
        WriterWeekPlaneData writerWeekPlaneData=new WriterWeekPlaneData();
        writerWeekPlaneData.setWorkNumber("本周总结");
        List<WriterWeekPlaneData> list =new ArrayList<>();
        list.add(writerWeekPlaneData);
        lvWriteWorkPlan.setAdapter(new WriterWeekConclusionAdapter(this,list));

    }

    @Override
    public void returnData(int key, Object data) {

    }
}
