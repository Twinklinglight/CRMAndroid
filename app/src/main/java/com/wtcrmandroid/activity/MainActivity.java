package com.wtcrmandroid.activity;

import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.fragment.FragmentTabAdapter;
import com.wtcrmandroid.fragment.BaseFragmengt;
import com.wtcrmandroid.fragment.main.AddressBookFragment;
import com.wtcrmandroid.fragment.main.FoundFragment;
import com.wtcrmandroid.fragment.main.HomeFragment;
import com.wtcrmandroid.fragment.main.MyFragment;
import com.wtcrmandroid.http.data.BaseData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017-05-31.
 */

public class MainActivity extends BaseActivity<BaseData> {

    @BindView(R.id.rg_bottom)
    RadioGroup rgBottom;
    public List<BaseFragmengt> fragments = new ArrayList<BaseFragmengt>();
    @BindView(R.id.title_window)
    LinearLayout titleWindow;

    @Override
    public void returnData(int key, BaseData data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initview() {
        fragments.add(new HomeFragment());
        fragments.add(new FoundFragment());
        fragments.add(new AddressBookFragment());
        fragments.add(new MyFragment());
        FragmentTabAdapter tabAdapter = new FragmentTabAdapter(this, fragments, R.id.fl_main, rgBottom);
    }

    public void setTitleWindow(int i) {
        titleWindow.setVisibility(i);
    }

    @OnClick(R.id.cancle_onlick)
    public void onClick() {
//        titleWindow.setVisibility(View.GONE);
    }
}
