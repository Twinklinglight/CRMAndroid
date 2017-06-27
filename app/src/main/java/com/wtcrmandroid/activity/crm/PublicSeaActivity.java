package com.wtcrmandroid.activity.crm;

import android.support.v7.widget.RecyclerView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.adapter.recycleview.PoppupWindowTitleAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.custompricing.TopChooseMenuBar;
import com.wtcrmandroid.view.popupwindow.TitlePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/23.
 * 续单公海界面
 */

public class PublicSeaActivity extends BaseActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.tcmb_bar)
    TopChooseMenuBar tcmbBar;
    @BindView(R.id.rv_view)
    RecyclerView rvView;
    private TitlePopupWindow titleLeftPopupWindow;
    private TitlePopupWindow titleCenterPopupWindow;
    private TitlePopupWindow titleRightPopupWindow;

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_client_library;
    }

    @Override
    protected void initview() {
        titlebar.setTitletext("续单公海");
        tcmbBar.setStrings(new String[]{"会员类型", "主库状态", "区域"});
        tcmbBar.setOnCheckedChangedListener(new TopChooseMenuBar.OnCheckedChangedListener() {
            @Override
            public void isSelected(int i) {
                switch (i) {
                    case 1:
                        //左边弹窗
                        if (titleLeftPopupWindow == null) {
                            List list = new ArrayList();
                            list.add("全部");
                            list.add("国内物流公司");
                            list.add("车主");
                            list.add("配货信息部");
                            list.add("国际物流企业");
                            list.add("快递公司");
                            list.add("搬家公司");
                            list.add("发货企业或个人");
                            list.add("物流设备企业");
                            list.add("物流园区");
                            list.add("停车场");
                            titleLeftPopupWindow = new TitlePopupWindow(PublicSeaActivity.this, tcmbBar, list, 0, 0, new PoppupWindowTitleAdapter.oNclicklistner() {
                                @Override
                                public void oNclicklistner(String data, int position) {
//                            tvCenter.setText(data);
//                            titleLeftPopupWindow.dismiss();
//                            fragmentAdapter.Changed(position);
//                            L.e(position+"yemian");
                                    titleLeftPopupWindow.dismiss();
                                    tcmbBar.setLeftText(data);
                                    tcmbBar.NoCheckStyle(1);
                                }
                            });

                        }
                        titleLeftPopupWindow.show();
                        break;
                    case 2:
                        //右边弹窗
                        if (titleCenterPopupWindow == null) {
                            List list = new ArrayList();
                            list.add("全部");
                            list.add("公海");
                            list.add("销售库");
                            list.add("成单库");
                            list.add("国企物信通库");
                            titleCenterPopupWindow = new TitlePopupWindow(PublicSeaActivity.this, tcmbBar, list, 0, 0, new PoppupWindowTitleAdapter.oNclicklistner() {
                                @Override
                                public void oNclicklistner(String data, int position) {
                                    tcmbBar.setCenterText(data);
                                    titleCenterPopupWindow.dismiss();
                                    tcmbBar.NoCheckStyle(2);
                                }
                            });

                        }
                        titleCenterPopupWindow.show();
                        break;
                    case 3:

                        break;
                }

            }

            @Override
            public void isNoSelected(int i) {
                switch (i){
                    case 1:
                        titleLeftPopupWindow.dismiss();
                        break;
                    case 2:
                        titleCenterPopupWindow.dismiss();
                        break;
                    case 3:

                        break;
                }


            }
        });

    }


}
