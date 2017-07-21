package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.utils.iat.Iat;
import com.wtcrmandroid.view.dialog.SelectionJobCategoriesDialog;
import com.wtcrmandroid.model.WriteDayplanData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/3.
 */

public class WriteDayPlanAdapter extends MySmallBaseAdapter<WriteDayplanData, WriteDayPlanAdapter.ViewHolder> implements SelectionJobCategoriesDialog.WorkLinstener {


    public WriteDayPlanAdapter(Activity activity, List<WriteDayplanData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(final ViewHolder holder, final int position) {

        if (list.size()>1){
            holder.mIvDelete.setVisibility(View.VISIBLE);
            holder.mIvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
        WriteDayplanData writeDayplanData = list.get(position);
        holder.mTvDayplanSort.setText(writeDayplanData.getWorkSort());
        holder.mTvDayplanSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workSort = list.get(position).getWorkSort();
                int tag = 0;
                switch (workSort){
                    case "A类 紧急又重要":
                        tag = 1;
                        break;
                    case "B类 较重要":
                        tag = 2;
                        break;
                    case "C类 重要":
                        tag = 3;
                        break;
                    case "D类 次重要":
                        tag = 4;
                        break;
                }
                new SelectionJobCategoriesDialog(activity,WriteDayPlanAdapter.this,position,tag).show();
            }
        });
        holder.mTvDayplanContent.setText(writeDayplanData.getWorkContent());
        holder.mTvDayplanPercent.setText(writeDayplanData.getWorkPercent());
        holder.mTvDayplanTime.setText(writeDayplanData.getWrokTime());
        holder.mEtDayplanPerson.setText(writeDayplanData.getWorkPerson());
        holder.mTvDayplanRemark.setText(writeDayplanData.getWrokBeizhu());

        holder.mIbDayplanYy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doVoice(holder.mTvDayplanContent);
            }
        });

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

    @Override
    protected View onCreateNullViewholder() {
        return null;
    }

    @Override
    public void WorkSelect(String workSort,int position) {
        list.get(position).setWorkSort(workSort);
        notifyDataSetChanged();
    }

    public void doVoice(final EditText etText) {

        Iat iat = new Iat(activity);
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

    class ViewHolder {
        @BindView(R.id.tv_dayplan_sort)
        TextView mTvDayplanSort;
        @BindView(R.id.iv_delete)
        ImageView mIvDelete;
        @BindView(R.id.tv_dayplan_content)
        EditText mTvDayplanContent;
        @BindView(R.id.ib_dayplan_yy)
        ImageButton mIbDayplanYy;               //语音按钮
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
