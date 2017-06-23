package com.wtcrmandroid.activity.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseActivity;
import com.wtcrmandroid.adapter.listview.WriteDaySumAdapter;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.model.WriteDaysumData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 写日总结activity
 *
 * @author zxd
 * @date 2017/6/6
 */

public class WriteDaySumActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.lv_write_daysum)
    ListView mLvDaysum;
    private WriteDaySumAdapter mDaySumAdapter;
    private List<WriteDaysumData> mDataList;


    @Override
    protected int layout() {
        return R.layout.activity_write_day_sum;
    }

    @Override
    protected void initview() {

        mTitlebar.setTitletext("写日总结");
        mDataList = new ArrayList<>();
        WriteDaysumData writeDaysumData = new WriteDaysumData();
        writeDaysumData.setWorkSort("B类");
        mDataList.add(writeDaysumData);
        mDaySumAdapter = new WriteDaySumAdapter(this, mDataList);
        mLvDaysum.setAdapter(mDaySumAdapter);

        View view = LayoutInflater.from(this).inflate(R.layout.item_daysum_foot, null);
        ViewHolder viewHolder = new ViewHolder(view);
        mLvDaysum.addFooterView(view);
        viewHolder.mLlDaysumAddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteDaysumData writeDaysumData1 = new WriteDaysumData();
                writeDaysumData1.setWorkSort("C类");
                mDataList.add(writeDaysumData1);
                mDaySumAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void returnData(int key, Object data) {

    }

    static class ViewHolder {
        @BindView(R.id.ll_daysum_addjob)
        LinearLayout mLlDaysumAddjob;        //增加一条 按钮
        @BindView(R.id.et_daysum_sum)
        EditText mEtDaysumSum;              //心得体会输入框
        @BindView(R.id.ib_daysum_sumyuyin)
        ImageButton mIbDaysumSumyuyin;      //心得体会语音按钮

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
