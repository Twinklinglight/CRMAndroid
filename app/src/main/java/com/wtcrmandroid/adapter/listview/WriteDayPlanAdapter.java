package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.view.dialog.WorkSortDialog;
import com.wtcrmandroid.model.WriteDayplanData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/3.
 */

public class WriteDayPlanAdapter extends MySmallBaseAdapter<WriteDayplanData, WriteDayPlanAdapter.ViewHolder> {


    public WriteDayPlanAdapter(Activity activity, List<WriteDayplanData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {
        WriteDayplanData writeDayplanData = list.get(position);
        holder.mTvDayplanSort.setText(writeDayplanData.getWorkSort());
        holder.mTvDayplanSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WorkSortDialog(activity).show();
            }
        });
        holder.mTvDayplanContent.setText(writeDayplanData.getWorkContent());
        holder.mTvDayplanPercent.setText(writeDayplanData.getWorkPercent());
        holder.mTvDayplanTime.setText(writeDayplanData.getWrokTime());
        holder.mEtDayplanPerson.setText(writeDayplanData.getWorkPerson());
        holder.mTvDayplanRemark.setText(writeDayplanData.getWrokBeizhu());

        holder.mTvDayplanContent.addTextChangedListener(new MyTextWatcher(writeDayplanData,position,0));
        holder.mEtDayplanPerson.addTextChangedListener(new MyTextWatcher(writeDayplanData,position,1));
        holder.mTvDayplanPercent.addTextChangedListener(new MyTextWatcher(writeDayplanData,position,2));
        holder.mTvDayplanTime.addTextChangedListener(new MyTextWatcher(writeDayplanData,position,3));
        holder.mTvDayplanRemark.addTextChangedListener(new MyTextWatcher(writeDayplanData,position,4));


    }


    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_write_dayplan, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    class ViewHolder {
        @BindView(R.id.tv_dayplan_sort)
        TextView mTvDayplanSort;
        @BindView(R.id.tv_dayplan_content)
        EditText mTvDayplanContent;
        @BindView(R.id.ib_dayplan_yy)
        ImageButton mIbDayplanYy;
        @BindView(R.id.et_dayplan_person)
        EditText mEtDayplanPerson;
        @BindView(R.id.tv_dayplan_percent)
        EditText mTvDayplanPercent;
        @BindView(R.id.tv_dayplan_time)
        EditText mTvDayplanTime;
        @BindView(R.id.tv_dayplan_remark)
        EditText mTvDayplanRemark;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 实现EditText的监听，根据类型赋值
     */
    class MyTextWatcher implements TextWatcher {

        private WriteDayplanData mDayplanData;
        private int position;
        private int type;

        public MyTextWatcher(WriteDayplanData dayplanData, int position, int type) {
            mDayplanData = dayplanData;
            this.position = position;
            this.type = type;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            switch (type){
                case 0:
                    mDayplanData.setWorkContent(s.toString());
                    break;
                case 1:
                    mDayplanData.setWorkPerson(s.toString());
                    break;
                case 2:
                    mDayplanData.setWorkPercent(s.toString());
                    break;
                case 3:
                    mDayplanData.setWrokTime(s.toString());
                    break;
                case 4:
                    mDayplanData.setWrokBeizhu(s.toString());
                    break;
                default:
                    break;
            }
            list.set(position,mDayplanData);
        }
    }


}
