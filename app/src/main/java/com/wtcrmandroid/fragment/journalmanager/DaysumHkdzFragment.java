package com.wtcrmandroid.fragment.journalmanager;

import android.widget.ListView;

import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.journalmanager.XsDaysumDetailsActivity;
import com.wtcrmandroid.adapter.listview.WorkLoadFgAdapter;
import com.wtcrmandroid.model.reponsedata.GetMoneyData;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zxd on 2017/6/12
 */

public class DaysumHkdzFragment extends BaseFragment {
    @BindView(R.id.lvload)
    ListView lvload;
    private WorkLoadFgAdapter adapter;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_daysum_hkdd;
    }

    @Override
    public void init() {

        XsDaysumDetailsActivity activity = (XsDaysumDetailsActivity)getActivity();
        List<GetMoneyData> workload = activity.DaysumData.getWork().getWorkload();

        adapter = new WorkLoadFgAdapter(activity,workload);
        lvload.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void returnData(int key, Object data) {

    }
}
