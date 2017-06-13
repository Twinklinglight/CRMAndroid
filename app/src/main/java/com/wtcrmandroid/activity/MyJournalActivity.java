package com.wtcrmandroid.activity;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.data.LoginData;
import com.wtcrmandroid.presenter.activity.MyJournalPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的日志Activity
 *
 * @author zxd
 * @date 2017/6/6
 */

public class MyJournalActivity extends BaseActivity<MyJournalPresenter,List<LoginData>> {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.ll_myjournal_type)
    LinearLayout mLlMyjournalType;  //类型筛选按钮
    @BindView(R.id.ll_myjournal_time)
    LinearLayout mLlMyjournalTime;  //时间筛选按钮
    @BindView(R.id.lv_myjournal)
    ListView mLvMyjournal;          //我的日志列表
    @BindView(R.id.ll_xiala_type)
    LinearLayout mLlXialaType;      //类型弹框
    @BindView(R.id.iv_type_arrow)
    ImageView mIvTypeArrow;
    @BindView(R.id.iv_time_arrow)
    ImageView mIvTimeArrow;

    @Override
    protected int layout() {
        return R.layout.activity_my_journal;
    }

    @Override
    protected void initview() {
        mTitlebar.setTitletext("我的日志");
        presenter=new MyJournalPresenter(this);
    }



    @OnClick(R.id.ll_myjournal_type)
    public void onViewClicked() {
        presenter.getData();

        if (mLlXialaType.getVisibility() == View.VISIBLE) {
            mLlXialaType.setVisibility(View.INVISIBLE);
            ObjectAnimator anim = ObjectAnimator.ofFloat(mIvTypeArrow, "rotation", 180f, 0f);
            anim.setDuration(200);
            anim.start();
        } else {
            mLlXialaType.setVisibility(View.VISIBLE);
            ObjectAnimator anim = ObjectAnimator.ofFloat(mIvTypeArrow, "rotation", 0f, 180f);
            anim.setDuration(200);
            anim.start();
        }

    }



    @Override
    public void returnData(int key, List<LoginData> data) {

    }
}
