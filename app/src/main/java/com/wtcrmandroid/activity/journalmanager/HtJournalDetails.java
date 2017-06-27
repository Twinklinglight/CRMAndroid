package com.wtcrmandroid.activity.journalmanager;

import android.view.View;
import android.widget.RadioGroup;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.adapter.fragment.FragmentTabAdapter;
import com.wtcrmandroid.fragment.BaseFragmengt;
import com.wtcrmandroid.fragment.journalmanager.DepartDayjournalFragment;
import com.wtcrmandroid.fragment.journalmanager.DepartWeekjournalFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 后台日志详情，计划、总结、评论；
 *
 * @author zxd
 * @date 2017/6/22
 */
public class HtJournalDetails extends BaseActivity {

    @BindView(R.id.rg_dayweek)
    RadioGroup mRgDayweek;

    public List<BaseFragmengt> mfragmentList;


    @Override
    protected int layout() {
        return R.layout.activity_ht_journal_details;
    }

    @Override
    protected void initview() {

        mfragmentList = new ArrayList<>();
        mfragmentList.add(new DepartDayjournalFragment());
        mfragmentList.add(new DepartWeekjournalFragment());
        FragmentTabAdapter fragmentTabAdapter = new FragmentTabAdapter(this,mfragmentList,
                R.id.frame_journal,mRgDayweek);

    }

    @Override
    public void returnData(int key, Object data) {

    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:          //返回
                this.finish();
                break;
        }
    }
}
