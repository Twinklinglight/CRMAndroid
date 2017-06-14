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
import com.wtcrmandroid.model.WriteDaysumData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxd on 2017/6/13
 */

public class WriteDaySumAdapter extends MySmallBaseAdapter<WriteDaysumData, WriteDaySumAdapter.ViewHolder> {

    public WriteDaySumAdapter(Activity activity, List<WriteDaysumData> list) {
        super(activity, list);
    }

    @Override
    protected void convert(ViewHolder holder, int position) {
        holder.mTvDaysumSort.setText(list.get(position).getWorkSort());
        holder.mTvDaysumContent.setText(list.get(position).getWorkContent());
        holder.mEtDaysumPerson.setText(list.get(position).getWorkPerson());
        holder.mTvDaysumComplete.setText(list.get(position).getWorkComplete());

        holder.mTvDaysumContent.addTextChangedListener(new MyTextWatch(list.get(position),position,0));
        holder.mEtDaysumPerson.addTextChangedListener(new MyTextWatch(list.get(position),position,1));

    }

    @Override
    protected View onCreateViewHolder() {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_write_daysum, null);
        ViewHolder viewHolder  = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_daysum_sort)
        TextView mTvDaysumSort;
        @BindView(R.id.iv_delete)
        ImageView mIvDelete;
        @BindView(R.id.tv_daysum_content)
        EditText mTvDaysumContent;
        @BindView(R.id.ib_daypsum_yy)
        ImageButton mIbDaypsumYy;
        @BindView(R.id.et_daysum_person)
        EditText mEtDaysumPerson;
        @BindView(R.id.tv_daysum_complete)
        TextView mTvDaysumComplete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class MyTextWatch implements TextWatcher{

        private WriteDaysumData mData;
        private int Type;               //输入框类型
        private int position;

        public MyTextWatch(WriteDaysumData data, int position,int type) {
            mData = data;
            Type = type;
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            switch (Type){
                case 0:
                    mData.setWorkContent(s.toString());
                    break;
                case 1:
                    mData.setWorkPerson(s.toString());
                    break;
                default:
                    break;
            }
            list.set(position,mData);
        }
    }
}
