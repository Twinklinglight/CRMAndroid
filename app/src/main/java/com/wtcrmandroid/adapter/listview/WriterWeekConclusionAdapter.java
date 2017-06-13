package com.wtcrmandroid.adapter.listview;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
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
        holder.tvPlan.setText(data.getTvPlan() + (position + 1));
        holder.etWorkPlane.setText(data.getEtWorkPlane());
        holder.etPlaneTarget.setText(data.getEtPlaneTarget());
        holder.etProportion.setText(data.getEtProportion());
        holder.etWorkPlane.addTextChangedListener(new MyTextWatcher(data, position, 0));
        holder.etPlaneTarget.addTextChangedListener(new MyTextWatcher(data, position, 1));
        holder.etProportion.addTextChangedListener(new MyTextWatcher(data, position, 2));

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
                    writerWeekPlaneData.setTvPlan("本周总结");
                    list.add(writerWeekPlaneData);
                    L.e(list.size() + "" + list.get(0).getEtWorkPlane());
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
                    data.setEtWorkPlane(s.toString());
                    break;
                case 1:
                    data.setEtPlaneTarget(s.toString());
                    break;
                case 2:
                    data.setEtProportion(s.toString());
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
