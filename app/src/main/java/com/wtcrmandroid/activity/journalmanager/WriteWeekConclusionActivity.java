package com.wtcrmandroid.activity.journalmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.present.WriteWeekSumPresenter;
import com.wtcrmandroid.adapter.listview.WriterWeekConclusionAdapter;
import com.wtcrmandroid.model.WriterWeekPlaneData;
import com.wtcrmandroid.model.WriterWeekSumData;
import com.wtcrmandroid.model.requestdata.WeekDetailsRequestData;
import com.wtcrmandroid.model.requestdata.WweekSumRequstData;
import com.wtcrmandroid.utils.DateUtils;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.utils.iat.Iat;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.dialog.WeekDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017-06-05.
 * 写周总结
 */

public class WriteWeekConclusionActivity extends BaseActivity<WriteWeekSumPresenter, Object> implements WeekDialog.WeekListener {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.lv_write_work_plan)
    ListView lvWriteWorksum;
    @BindView(R.id.tv_date_show)
    TextView tvDateShow;

    private int position = 2;
    private String nowWeek;
    List<WriterWeekSumData> list;
    DateUtils dateUtils;
    ViewHolder viewHolder;
    private String weekBegin = "";
    private String weekEnd = "";
    private String sumLearn = "";
    private WriterWeekConclusionAdapter adapter;

    @Override
    protected int layout() {
        return R.layout.activity_write_week_plan;
    }

    @Override
    protected void initView() {

        dateUtils = new DateUtils();
        nowWeek = dateUtils.getNowWeek();       //获取当前周日期 区间
        weekBegin = dateUtils.getWantDate(nowWeek.split("-")[0]);   //获取当前周起始日期
        weekEnd = dateUtils.getWantDate(nowWeek.split("-")[1]);     //获取当前周结束日期

        presenter = new WriteWeekSumPresenter(this, this);

        WeekDetailsRequestData weekDetailsRequestData = new WeekDetailsRequestData();
        weekDetailsRequestData.setUserId(MyApplication.application.getLoginData().getUserID());
        weekDetailsRequestData.setType("week");
        weekDetailsRequestData.setIsPlan("true");
        weekDetailsRequestData.setWeekBegin(weekBegin);
        weekDetailsRequestData.setWeekEnd(weekEnd);

        presenter.getWeekPlan(weekDetailsRequestData);  //先获取周计划详情

        titlebar.setTitletext("写周总结");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteWeekConclusionActivity.this.finish();
            }
        });

        tvDateShow.setText(nowWeek);


        final WriterWeekSumData writerWeekPlaneData = new WriterWeekSumData();
        writerWeekPlaneData.setWorkNumber("");
        list = new ArrayList<>();
        list.add(writerWeekPlaneData);
        View footview = LayoutInflater.from(this).inflate(R.layout.item_weeksum_foot, null);
        viewHolder = new ViewHolder(footview);
        footview.setTag(viewHolder);
        lvWriteWorksum.addFooterView(footview);

        //增加一条数据
        viewHolder.llWeeksumAddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriterWeekSumData writerWeekSumData1 = new WriterWeekSumData();
                list.add(writerWeekSumData1);
                adapter.notifyDataSetChanged();

            }
        });
        adapter = new WriterWeekConclusionAdapter(this, list);
        lvWriteWorksum.setAdapter(adapter);

        //语音
        viewHolder.ibWeeksumSumyuyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doVoice(viewHolder.etWeeksumSum);
            }
        });

    }

    @Override
    public void returnData(int key, Object data) {

        switch (key){
            case 1:
                Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
                this.finish();
                break;
            case 2:
                List<WriterWeekPlaneData> planData = (List<WriterWeekPlaneData>)data;
                break;
        }



    }

    @OnClick({R.id.ll_select_date, R.id.bt_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_select_date:
                new WeekDialog(this, this, position).show();
                break;
            case R.id.bt_submit:
                WweekSumRequstData wweekSumRequstData = new WweekSumRequstData();
                wweekSumRequstData.setWeekStart(weekBegin);
                wweekSumRequstData.setWeekEnd(weekEnd);
                wweekSumRequstData.setUserId(MyApplication.application.getLoginData().getUserID());
                wweekSumRequstData.setType("week");
                wweekSumRequstData.setPlan(false);
                wweekSumRequstData.setWork(list);
                wweekSumRequstData.setLearningAndReflection(viewHolder.etWeeksumSum.getText().toString());
                presenter.SubWeekSum(wweekSumRequstData);
                break;
        }
    }

    //日期选择回调
    @Override
    public void weekSelect(String weekText, int position) {

        this.position = position;
        tvDateShow.setText(weekText);
        weekBegin = dateUtils.getWantDate(weekText.split("-")[0]);
        weekBegin = dateUtils.getWantDate(weekText.split("-")[1]);

    }

    //语音接口
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
        @BindView(R.id.ll_weeksum_addjob)
        LinearLayout llWeeksumAddjob;
        @BindView(R.id.et_weeksum_sum)
        EditText etWeeksumSum;
        @BindView(R.id.ib_weeksum_sumyuyin)
        ImageButton ibWeeksumSumyuyin;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
