package com.wtcrmandroid.activity;

import android.content.Intent;
import android.widget.RelativeLayout;

import com.wtcrmandroid.R;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.http.data.BaseData;

import butterknife.BindView;
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

        startActivity(new Intent(JournalManagerActivity.this,MyJournalActivity.class));
    }
}
