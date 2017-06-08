package com.wtcrmandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wtcrmandroid.R;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.http.data.BaseData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zxd
 * @date 2017/6/6
 */

public class JournalManagerActivity extends BaseActivity {


    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.rl_journalManager_myjournal)
    RelativeLayout mRlJournalManagerMyjournal;  //我的日志
    @BindView(R.id.ll_journalManager_write_daysum)
    LinearLayout mLlJournalManagerWriteDaysum;  //写日总结
    @BindView(R.id.ll_journalManager_write_dayplan)
    LinearLayout mLlJournalManagerWriteDayplan; //写日计划
    @BindView(R.id.ll_journalManager_write_weeksum)
    LinearLayout mLlJournalManagerWriteWeeksum; //写周总结
    @BindView(R.id.ll_journalManager_write_weekplan)
    LinearLayout mLlJournalManagerWriteWeekplan; //写周计划
    @BindView(R.id.rl_journalManager_bmygrz)
    RelativeLayout mRlJournalManagerBmygrz;     //部门员工日志

    @Override
    protected int layout() {
        return R.layout.activity_journal_manager;
    }

    @Override
    protected void initview() {
        mTitlebar.setTitletext("日志管理");
    }

    @Override
    public void returnData(int key, BaseData data) {

    }


    @OnClick(R.id.rl_journalManager_myjournal)
    public void onViewClicked() {
    }

    @OnClick({R.id.ll_journalManager_write_daysum, R.id.rl_journalManager_myjournal,
            R.id.ll_journalManager_write_dayplan, R.id.ll_journalManager_write_weeksum,
            R.id.ll_journalManager_write_weekplan, R.id.rl_journalManager_bmygrz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_journalManager_write_daysum:
                startActivity(new Intent(JournalManagerActivity.this,WriteDaySumActivity.class));
                break;
            case R.id.ll_journalManager_write_dayplan:
                startActivity(new Intent(JournalManagerActivity.this,WriteDayPlanActivity.class));
                break;
            case R.id.ll_journalManager_write_weeksum:
                break;
            case R.id.ll_journalManager_write_weekplan:
                startActivity(new Intent(JournalManagerActivity.this,WriteWeekPlanActivity.class));
                break;
            case R.id.rl_journalManager_myjournal:
                startActivity(new Intent(JournalManagerActivity.this, MyJournalActivity.class));
                break;
            case R.id.rl_journalManager_bmygrz:
                break;

        }
    }
}
