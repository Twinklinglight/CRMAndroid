package com.wtcrmandroid.activity.journalmanager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.base.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.present.WriteDaySumPresenter;
import com.wtcrmandroid.adapter.listview.WriteDaySumAdapter;
import com.wtcrmandroid.model.reponsedata.WriteDaysumData;
import com.wtcrmandroid.model.requestdata.WDaySumRequestData;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.utils.iat.Iat;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.dialog.CalendarDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 写日总结activity
 *
 * @author zxd
 * @date 2017/6/6
 */

public class WriteDaySumActivity extends BaseActivity<WriteDaySumPresenter,Object> implements CalendarDialog.CalendarListener {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.lv_write_daysum)
    ListView mLvDaysum;
    @BindView(R.id.tv_daysum_date)
    TextView tvDaySumDate;                 //显示所选日期

    private String SelectDate = "";
    private String subTime ="" ;
    SimpleDateFormat sdf;
    private String sumLearn = "";
    private WriteDaySumAdapter mDaySumAdapter;
    private List<WriteDaysumData> mDataList;


    @Override
    protected int layout() {
        return R.layout.activity_write_day_sum;
    }

    @Override
    protected void initView() {

        presenter = new WriteDaySumPresenter(this,this);
        mTitlebar.setTitletext("写日总结");
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteDaySumActivity.this.finish();
            }
        });
        Date time = Calendar.getInstance().getTime();
        SelectDate = new SimpleDateFormat("yyyy-MM-dd EEEE").format(time); //默认为当天
        sdf =new SimpleDateFormat("yyyy-MM-dd");
        subTime = sdf.format(time);
        SetDateText(SelectDate);

        mDataList = new ArrayList<>();
        WriteDaysumData writeDaysumData = new WriteDaysumData();
        writeDaysumData.setWorkSort("");
        mDataList.add(writeDaysumData);
        mDaySumAdapter = new WriteDaySumAdapter(this, mDataList);
        mLvDaysum.setAdapter(mDaySumAdapter);

        View view = LayoutInflater.from(this).inflate(R.layout.item_daysum_foot, null);
        final ViewHolder viewHolder = new ViewHolder(view);
        mLvDaysum.addFooterView(view);

        viewHolder.mIbDaysumSumyuyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doVoice(viewHolder.mEtDaysumSum);
            }
        });
        viewHolder.mEtDaysumSum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sumLearn = s.toString();
            }
        });
        viewHolder.mLlDaysumAddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteDaysumData writeDaysumData1 = new WriteDaysumData();
                writeDaysumData1.setWorkSort("");
                mDataList.add(writeDaysumData1);
                mDaySumAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 返回的日期格式为：2017-6-27 星期二
     * 字符串拆封 合并；
     * @param date
     */
    //设置选中日期
    private void SetDateText(String date) {
        String[] split = date.split("-");
        String DateText = split[0] + "年" + split[1] + "月" +
                split[2].split(" ")[0] + "日" + " " + split[2].split(" ")[1];

        tvDaySumDate.setText(DateText);
    }

    @Override
    public void returnData(int key, Object data) {

        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @OnClick({R.id.ib_daysum_calender, R.id.tv_daysum_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_daysum_calender:      //日历
                new CalendarDialog(this,this).show();
                break;
            case R.id.tv_daysum_submit:         //提交
                WDaySumRequestData wDaySumRequestData = new WDaySumRequestData();
                wDaySumRequestData.setWorkRecordTime(subTime);
                wDaySumRequestData.setType("day");
                wDaySumRequestData.setPlan(false);
                wDaySumRequestData.setUserId(MyApplication.application.getLoginData().getUserID());
                wDaySumRequestData.setWork(mDataList);
                wDaySumRequestData.setLearningAndReflection(sumLearn);
                presenter.SubDaySum(wDaySumRequestData);
                break;
        }
    }

    //日历选择的回调
    @Override
    public void CalendarSelcet(String datetext,Date date) {
        SelectDate = datetext;
        subTime = sdf.format(date);
        SetDateText(datetext);
    }


    public void doVoice(final EditText etText) {

        Iat iat = new Iat(this);
        iat.iatRecognize();
        iat.setSetRestult(new Iat.setResult() {
            @Override
            public void succeed(String result) {
                etText.setText(result);
            }

            @Override
            public void failed(String iatError) {
                L.e("出现了一个错误，请您重试");
            }
        });
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
