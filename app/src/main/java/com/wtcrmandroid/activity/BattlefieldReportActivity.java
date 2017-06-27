package com.wtcrmandroid.activity;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.fragment.FragmentAdapter;
import com.wtcrmandroid.adapter.recycleview.PoppupWindowTitleAdapter;
import com.wtcrmandroid.view.popupwindow.TitlePopupWindow;
import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.fragment.battlefieldreport.BattlefieldReportFragment;
import com.wtcrmandroid.fragment.battlefieldreport.TotalAchievementsFragment;
import com.wtcrmandroid.utils.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wt-pc on 2017/6/20.
 */

public class BattlefieldReportActivity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.iv_center)
    ImageView ivCenter;
    @BindView(R.id.fl_view)
    FrameLayout flView;
    @BindView(R.id.ll_center)
    LinearLayout llCenter;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    private boolean window;
    private TitlePopupWindow titlePopupWindow;
    private FragmentAdapter fragmentAdapter;
    private BaseFragment[] fragments=new BaseFragment[]{new BattlefieldReportFragment(),new BattlefieldReportFragment(),new TotalAchievementsFragment()};
    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_battlefield_report;
    }

    @Override
    protected void initView() {
        fragmentAdapter= new FragmentAdapter(this,fragments,R.id.fl_view);

    }

    @OnClick(R.id.ll_center)
    public void onViewClicked() {
        if(titlePopupWindow==null) {
            List list = new ArrayList();
            list.add("销售战报");
            list.add("总业绩");
            titlePopupWindow = new TitlePopupWindow(this, rlTitle, list, 0, -1, new PoppupWindowTitleAdapter.oNclicklistner() {
                @Override
                public void oNclicklistner(String data, int position) {
                    tvCenter.setText(data);
                    titlePopupWindow.dismiss();
                    fragmentAdapter.Changed(position);
                    L.e(position+"yemian");

                }
            });
        }
        if(!window) {
            titlePopupWindow.show();
            window=true;
        }
        else {
            titlePopupWindow.dismiss();
            window=false;
        }

    }


}
