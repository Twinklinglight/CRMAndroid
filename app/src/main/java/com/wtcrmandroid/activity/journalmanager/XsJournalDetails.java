package com.wtcrmandroid.activity.journalmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.fragment.FragmentTabAdapter;
import com.wtcrmandroid.fragment.journalmanager.DepartWeekjournalFragment;
import com.wtcrmandroid.fragment.journalmanager.DepartXsDayjournalFragment;

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
    private List<BaseFragment> mfragmentList;

    public int userid;
    private DepartXsDayjournalFragment xsDayjournalFragment;
    private DepartWeekjournalFragment weekjournalFragment;

    @Override
    protected int layout() {
        return R.layout.activity_xs_journal_details;
    }

    @Override
    protected void initView() {
        userid = getIntent().getIntExtra("userid", 0);
        Bundle bundle = new Bundle();
        bundle.putInt("userid", userid);
        mfragmentList = new ArrayList<>();
        xsDayjournalFragment = new DepartXsDayjournalFragment();
        weekjournalFragment = new DepartWeekjournalFragment();

        xsDayjournalFragment.setArguments(bundle);
        weekjournalFragment.setArguments(bundle);


        mfragmentList.add(xsDayjournalFragment);
        mfragmentList.add(weekjournalFragment);
        mFragmentTabAdapter = new FragmentTabAdapter(this, mfragmentList, R.id.frame_xs_journal, mRgDayweek);

    }

    @Override
    public void returnData(int key, Object data) {

    }

    @OnClick({R.id.iv_back, R.id.rb_week})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.rb_week:
                xsDayjournalFragment.cancleWindows();
                break;
        }
    }
}
