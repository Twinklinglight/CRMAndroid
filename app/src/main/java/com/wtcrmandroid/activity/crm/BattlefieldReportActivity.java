package com.wtcrmandroid.activity.crm;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.base.BaseFragment;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.fragment.FragmentAdapter;
import com.wtcrmandroid.adapter.recycleview.PoppupWindowTitleAdapter;
import com.wtcrmandroid.fragment.battlefieldreport.BattlefieldReportFragment;
import com.wtcrmandroid.fragment.battlefieldreport.TotalAchievementsFragment;
import com.wtcrmandroid.view.popupwindow.TitlePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wt-pc on 2017/6/20.
 * 战报
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
    @BindView(R.id.v_shadow)
    View vShadow;


    private boolean window;
    private TitlePopupWindow titlePopupWindow;
    private FragmentAdapter fragmentAdapter;
    private BaseFragment[] fragments = new BaseFragment[]{new BattlefieldReportFragment(), new TotalAchievementsFragment()};

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_battlefield_report;
    }

    @Override
    protected void initView() {
        fragmentAdapter = new FragmentAdapter(this, fragments, R.id.fl_view);


    }


    @OnClick({R.id.iv_left, R.id.ll_center})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.ll_center:
                if (titlePopupWindow == null) {
                    List list = new ArrayList();
                    list.add("销售战报");
                    list.add("总业绩");
                    titlePopupWindow = new TitlePopupWindow(this, rlTitle, list, 1, 0, new PoppupWindowTitleAdapter.oNclicklistner() {
                        @Override
                        public void oNclicklistner(String data, int position) {
                            tvCenter.setText(data);
                            titlePopupWindow.dismiss();
                            window = false;
                            fragmentAdapter.Changed(position);


                        }
                    });
                    titlePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            window = false;
                            vShadow.setVisibility(View.GONE);
                            ivCenter.setBackgroundResource(R.mipmap.ic_arrow_down_white);
                        }
                    });
                }
                if (!window) {
                    titlePopupWindow.show();
                    vShadow.setVisibility(View.VISIBLE);
                    ivCenter.setBackgroundResource(R.mipmap.ic_arrow_up_white);
                    window = true;
                } else {
                    titlePopupWindow.dismiss();
                }
//

                break;
        }
    }




}
