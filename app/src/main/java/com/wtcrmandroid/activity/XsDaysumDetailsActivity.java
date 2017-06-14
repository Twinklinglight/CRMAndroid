package com.wtcrmandroid.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.fragment.XsDayplanAdapter;
import com.wtcrmandroid.adapter.listview.CommentAdapter;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.fragment.DaysumAddCustomerFragment;
import com.wtcrmandroid.fragment.DaysumHkdzFragment;
import com.wtcrmandroid.fragment.DaysumSingleCustomerFragment;
import com.wtcrmandroid.fragment.DaysumWorkCountFragment;
import com.wtcrmandroid.fragment.DaysumWorkPlanFragment;
import com.wtcrmandroid.model.CommentData;
import com.wtcrmandroid.view.MyListView;
import com.wtcrmandroid.view.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

/**
 * 销售日总结详情页
 *
 * @author zxd
 * @date 2017/6/12
 */

public class XsDaysumDetailsActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.tv_journal_type)
    TextView mTvJournalType;                    //日总结标题
    @BindView(R.id.tab_daysum_details)
    TabLayout mTabDaysumDetails;                //tab
    @BindView(R.id.vp_dayplan_details)
    MyViewPager mVpDayplanDetails;              //vp
    @BindView(R.id.lv_comment)
    MyListView mLvComment;                      //评论列表
    private CommentAdapter mCommentAdapter;
    private XsDayplanAdapter mXsDayplanAdapter;

    private List<String> mStringList;
    private List<Fragment> mFragmentList;
    private List<CommentData> mCommentDatas;

    @Override
    protected int layout() {
        return R.layout.activity_xs_daysum_details;
    }

    @Override
    protected void initview() {
        mStringList = new ArrayList<>();
        mCommentDatas = new ArrayList<>();
        mFragmentList = new ArrayList<>();
        getData();
        mStringList.add("工作计划");
        mStringList.add("今日工作量");
        mStringList.add("回款到单");
        mStringList.add("预测到单客户踩中");
        mStringList.add("新增意向客户");

        mFragmentList.add(new DaysumWorkPlanFragment());
        mFragmentList.add(new DaysumWorkCountFragment());
        mFragmentList.add(new DaysumHkdzFragment());
        mFragmentList.add(new DaysumSingleCustomerFragment());
        mFragmentList.add(new DaysumAddCustomerFragment());
        mXsDayplanAdapter = new XsDayplanAdapter(getSupportFragmentManager(), mStringList, mFragmentList);
        mVpDayplanDetails.setAdapter(mXsDayplanAdapter);
        mCommentAdapter = new CommentAdapter(this, mCommentDatas);
        mLvComment.setAdapter(mCommentAdapter);
        View view = LayoutInflater.from(this).inflate(R.layout.item_comment_head, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mTvCommentCount.setText("评论("+mCommentDatas.size()+")");
        mLvComment.addHeaderView(view);
        mTabDaysumDetails.setupWithViewPager(mVpDayplanDetails);    //关联vp
        mTabDaysumDetails.setTabMode(MODE_SCROLLABLE);  //tab滚动模式
    }

    public void getData() {
        for (int i = 0; i < 8; i++) {
            CommentData commentData = new CommentData();
            commentData.setCommentPerson("总监");
            commentData.setCommentTime("2017-6-12");
            commentData.setCommentJob("弄着、弄那");
            commentData.setCommentContent("除了这你还弄啥类");
            mCommentDatas.add(commentData);
        }
    }

    @Override
    public void returnData(int key, Object data) {

    }

    static class ViewHolder {
        @BindView(R.id.tv_comment_count)
        TextView mTvCommentCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
