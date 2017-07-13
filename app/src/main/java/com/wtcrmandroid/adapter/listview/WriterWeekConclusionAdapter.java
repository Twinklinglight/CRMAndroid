package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.model.WriterWeekSumData;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.utils.iat.Iat;
import com.wtcrmandroid.view.dialog.CompleteConditionDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 1363655717 on 2017-06-06.
 * 写周计划适配器
 */

public class WriterWeekConclusionAdapter extends MySmallBaseAdapter<WriterWeekSumData, WriterWeekConclusionAdapter.ViewHolder> implements CompleteConditionDialog.CompeleteListener {

    public WriterWeekConclusionAdapter(Activity activity, List list) {
        super(activity, list);
    }

    @Override
    protected void convert(final ViewHolder holder, final int position) {
        final WriterWeekSumData data = list.get(position);
        holder.tvPlan.setText("本周总结"+(position+1));

        if (list.size() > 1) {
            holder.ivDelete.setVisibility(View.VISIBLE);
            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
        holder.etWorkPlane.setText(data.getWorkContent());
        holder.etPlaneTarget.setText(data.getWorkPlanning());
        holder.etProportion.setText(data.getWorkPercentage());
        if (data.getWorkUnfinishedReason()=="" || data.getWorkUnfinishedReason() == null){
            holder.tvComplete.setText(data.getWorkComplete());
        }else {
            holder.tvComplete.setText(data.getWorkComplete()+data.getWorkUnfinishedReason()+
                                        "下次完成时间"+data.getWorkNextFinishTime());
        }


        holder.llComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CompleteConditionDialog(activity,WriterWeekConclusionAdapter.this,position).show();
            }
        });


        holder.etWorkPlane.addTextChangedListener(new MyTextWatcher(data, position, 0));
        holder.etPlaneTarget.addTextChangedListener(new MyTextWatcher(data, position, 1));
        holder.etProportion.addTextChangedListener(new MyTextWatcher(data, position, 2));
        holder.ivMicrophone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doVoice(holder.etWorkPlane);
            }
        });
    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_write_week_conclusion,
                null);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);
        return view;
    }

    @Override
    protected View onCreateNullViewholder() {
        return null;
    }

    //完成情况
    @Override
    public void completeCondition(String text, int position) {
        list.get(position).setWorkUnfinishedReason("");
        list.get(position).setWorkNextFinishTime("");
        list.get(position).setWorkComplete("已完成 "+text);
        notifyDataSetChanged();
    }

    //未完成请况
    @Override
    public void unCompleteReason(String reason, String time, int position) {
        list.get(position).setWorkComplete("未完成 ");
        list.get(position).setWorkUnfinishedReason(reason+" ");
        list.get(position).setWorkNextFinishTime(" "+time);
        notifyDataSetChanged();
    }


    class MyTextWatcher implements TextWatcher {
        private WriterWeekSumData data;
        private int position;
        private int type;

        public MyTextWatcher(WriterWeekSumData data, int position, int type) {
            this.data = data;
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
            switch (type) {
                case 0:
                    data.setWorkContent(s.toString());
                    break;
                case 1:
                    data.setWorkPlanning(s.toString());
                    break;
                case 2:
                    data.setWorkPercentage(s.toString());
                    break;
                default:
                    break;
            }
            list.set(position, data);
        }
    }

    static class ViewHolder {
        @BindView(R.id.tv_sum)
        TextView tvPlan;
        @BindView(R.id.et_work_plane)
        EditText etWorkPlane;
        @BindView(R.id.et_plane_target)
        EditText etPlaneTarget;
        @BindView(R.id.et_proportion)
        EditText etProportion;
        @BindView(R.id.rl_add)
        RelativeLayout rlAdd;
        @BindView(R.id.iv_microphone)
        ImageView ivMicrophone;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;
        @BindView(R.id.tv_complete)
        TextView tvComplete;
        @BindView(R.id.ll_complete)
        LinearLayout llComplete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void doVoice(final EditText etWorkPlane) {

        Iat iat = new Iat(activity);
        iat.iatRecognize();
        iat.setSetRestult(new Iat.setResult() {
            @Override
            public void succeed(String result) {
                etWorkPlane.setText(result);

            }

            @Override
            public void failed(String iatError) {
                L.e("出现了一个错误，请您重试");
            }
        });
    }
}
