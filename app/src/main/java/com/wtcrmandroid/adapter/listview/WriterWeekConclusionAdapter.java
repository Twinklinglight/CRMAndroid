package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.iat.Iat;
import com.wtcrmandroid.model.WriterWeekPlaneData;
import com.wtcrmandroid.utils.L;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 1363655717 on 2017-06-06.
 * 写周计划适配器
 */

public class WriterWeekConclusionAdapter extends MySmallBaseAdapter<WriterWeekPlaneData, WriterWeekConclusionAdapter.ViewHolder> {
    public WriterWeekConclusionAdapter(Activity activity, List list) {
        super(activity, list);
    }


    @Override
    protected void convert(final ViewHolder holder, int position) {
        final WriterWeekPlaneData data = list.get(position);
        holder.tvPlan.setText(data.getWorkNumber() + (position + 1));
        holder.etWorkPlane.setText(data.getWorkContent());
        holder.etPlaneTarget.setText(data.getWorkPlanning());
        holder.etProportion.setText(data.getWorkPercentage());
        holder.etWorkPlane.addTextChangedListener(new MyTextWatcher(data, position, 0));
        holder.etPlaneTarget.addTextChangedListener(new MyTextWatcher(data, position, 1));
        holder.etProportion.addTextChangedListener(new MyTextWatcher(data, position, 2));
        holder.ivMicrophone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doVoice(holder.etWorkPlane);
            }
        });
        if (list.size() == position + 1) {
            holder.rlAdd.setVisibility(View.VISIBLE);

            holder.rlAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    data.setEtWorkPlane(holder.etWorkPlane.getText().toString());
//                    data.setEtPlaneTarget(holder.etPlaneTarget.getText().toString());
//                    data.setEtProportion(holder.etProportion.getText().toString());
//                    list.set(position,data);
                    WriterWeekPlaneData writerWeekPlaneData = new WriterWeekPlaneData();
                    writerWeekPlaneData.setWorkNumber("本周总结");
                    list.add(writerWeekPlaneData);
                    L.e(list.size() + "" + list.get(0).getWorkContent());
                    WriterWeekConclusionAdapter.this.notifyDataSetChanged();
                }
            });
        } else {
            holder.rlAdd.setVisibility(View.GONE);
//            holder.etWorkPlane.setKeyListener(null);
//            holder.etPlaneTarget.setKeyListener(null);
//            holder.etProportion.setKeyListener(null);
        }
    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_write_week_conclusion,
                null);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);
        return view;
    }



    class MyTextWatcher implements TextWatcher {
        private WriterWeekPlaneData data;
        private int position;
        private int type;

        public MyTextWatcher(WriterWeekPlaneData data, int position, int type) {
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
        @BindView(R.id.tv_plan)
        TextView tvPlan;
        @BindView(R.id.et_work_plane)
        EditText etWorkPlane;
        @BindView(R.id.et_plane_target)
        EditText etPlaneTarget;
        @BindView(R.id.et_proportion)
        EditText etProportion;
        @BindView(R.id.et_result)
        EditText etResult;
        @BindView(R.id.rl_add)
        RelativeLayout rlAdd;
        @BindView(R.id.iv_microphone)
        ImageView ivMicrophone;
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
