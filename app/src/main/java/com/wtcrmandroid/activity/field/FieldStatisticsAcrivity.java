package com.wtcrmandroid.activity.field;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.adapter.recycleview.BaseRecycleAdapter;
import com.wtcrmandroid.adapter.recycleview.FieldStatisticaAdapter;
import com.wtcrmandroid.adapter.recycleview.PoppupWindowTitleAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.custompricing.TopChooseMenuBar;
import com.wtcrmandroid.view.popupwindow.TitlePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 1363655717 on 2017-06-14.
 * 外勤统计
 */

public class FieldStatisticsAcrivity extends BaseActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.tcmb_bar)
    TopChooseMenuBar tcmbBar;
    @BindView(R.id.rv_view)
    RecyclerView rvView;

    private BaseRecycleAdapter adapter;
    private TitlePopupWindow titleLeftPopupWindow;
    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_field_statistics;
    }

    @Override
    protected void initview() {
        titlebar.setTitletext("外勤统计");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tcmbBar.setStrings(new String[]{"部门","今天"});
        tcmbBar.setOnCheckedChangedListener(new TopChooseMenuBar.OnCheckedChangedListener() {
            @Override
            public void isSelected(int i) {
                switch (i) {
                    case 1:
                        //左边弹窗
                        if (titleLeftPopupWindow == null) {
                            List list = new ArrayList();
                            list.add("物通市场部");
                            titleLeftPopupWindow = new TitlePopupWindow(FieldStatisticsAcrivity.this, tcmbBar, list, 0, 0, new PoppupWindowTitleAdapter.oNclicklistner() {
                                @Override
                                public void oNclicklistner(String data, int position) {
                                    titleLeftPopupWindow.dismiss();
                                    tcmbBar.setLeftText(data);
                                    tcmbBar.NoCheckStyle(1);
                                }
                            });

                        }
                        titleLeftPopupWindow.show();
                        break;
                }

            }

            @Override
            public void isNoSelected(int i) {
                switch (i){
                    case 1:
                        titleLeftPopupWindow.dismiss();
                        break;

                }

            }
        });
        rvView.setLayoutManager(new LinearLayoutManager(this));
        rvView.setAdapter(adapter=new FieldStatisticaAdapter(this));
    }



}
