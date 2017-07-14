package com.wtcrmandroid.activity.journalmanager;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.adapter.fragment.FragmentTabAdapter;
import com.wtcrmandroid.BaseFragment;
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
    @BindView(R.id.rb_week)
    RadioButton Rbweek;
    DepartDayjournalFragment Dayfragment;
    DepartWeekjournalFragment Weekfragment;

    public List<BaseFragment> mfragmentList;
    public int userid = 0;


    @Override
    protected int layout() {
        return R.layout.activity_ht_journal_details;
    }

    @Override
    protected void initView() {
        userid = getIntent().getIntExtra("userid",0);
        mfragmentList = new ArrayList<>();

        Dayfragment = new DepartDayjournalFragment();
        Weekfragment = new DepartWeekjournalFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("userid",userid);
        Dayfragment.setArguments(bundle);
        Weekfragment.setArguments(bundle);
        mfragmentList.add(Dayfragment);
        mfragmentList.add(Weekfragment);

        Rbweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dayfragment.cancleWindows();
            }
        });

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
