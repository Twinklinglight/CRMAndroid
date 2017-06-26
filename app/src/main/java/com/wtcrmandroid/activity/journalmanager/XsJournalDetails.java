package com.wtcrmandroid.activity.journalmanager;

import android.view.View;
import android.widget.RadioGroup;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseActivity;
import com.wtcrmandroid.adapter.fragment.FragmentTabAdapter;
import com.wtcrmandroid.fragment.BaseFragmengt;
import com.wtcrmandroid.fragment.journalmanager.DepartWeekjournalFragment;
import com.wtcrmandroid.fragment.journalmanager.DepartXsDayjournalFragment;
import com.wtcrmandroid.fragment.journalmanager.DepartXsWeekjournalFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 销售日志 详情
 *
 * @author zxd
 * @date 2017/6/23
 */

public class XsJournalDetails extends BaseActivity {

    @BindView(R.id.rg_dayweek)
    RadioGroup mRgDayweek;
    private FragmentTabAdapter mFragmentTabAdapter;
    private List<BaseFragmengt> mfragmentList;

    @Override
    protected int layout() {
        return R.layout.activity_xs_journal_details;
    }

    @Override
    protected void initview() {

        mfragmentList = new ArrayList<>();
        mfragmentList.add(new DepartXsDayjournalFragment());
        mfragmentList.add(new DepartWeekjournalFragment());

        mFragmentTabAdapter = new FragmentTabAdapter(this, mfragmentList, R.id.frame_xs_journal,mRgDayweek);

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
