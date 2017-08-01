package com.wtcrmandroid.fragment.journalmanager;
import android.widget.TextView;
import com.wtcrmandroid.base.BaseFragment;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.XsDaysumDetailsActivity;
import com.wtcrmandroid.model.requestdata.WorkOrder;

import butterknife.BindView;

/**
 * 今日工作量fragment
 * Created by zxd on 2017/6/12
 */

public class DaysumWorkCountFragment extends BaseFragment {

    @BindView(R.id.tv_phone_count)
    TextView tvPhoneCount;
    @BindView(R.id.tv_shangmen_count)
    TextView tvShangmenCount;
    @BindView(R.id.tv_A_count)
    TextView tvACount;
    @BindView(R.id.tv_B_count)
    TextView tvBCount;
    @BindView(R.id.tv_addA_count)
    TextView tvAddACount;
    @BindView(R.id.tv_addB_count)
    TextView tvAddBCount;
    @BindView(R.id.tv_krl_count)
    TextView tvKrlCount;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_daysum_workcount;
    }

    @Override
    public void init() {
        XsDaysumDetailsActivity activity = (XsDaysumDetailsActivity) getActivity();
        WorkOrder workorder = activity.DaysumData.getWork().getWorkorder();
        if (workorder != null){
            tvACount.setText(workorder.getAStore());
            tvBCount.setText(workorder.getBStore());
            tvAddACount.setText(workorder.getNewAStore());
            tvAddBCount.setText(workorder.getNewBStore());
            tvShangmenCount.setText(workorder.getTrueVisit());
            tvPhoneCount.setText(workorder.getTrueCall());
            tvKrlCount.setText(workorder.getStroe());

        }
    }

    @Override
    public void returnData(int key, Object data) {

    }
}
