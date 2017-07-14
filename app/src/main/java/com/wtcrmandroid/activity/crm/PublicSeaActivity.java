package com.wtcrmandroid.activity.crm;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.PopupWindow;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.adapter.recycleview.ClientLibraryAdapter;
import com.wtcrmandroid.adapter.recycleview.PoppupWindowTitleAdapter;
import com.wtcrmandroid.utils.areaslection.Area;
import com.wtcrmandroid.utils.areaslection.AreaPopUpWindow;
import com.wtcrmandroid.model.reponsedata.SearchCustomerRP;
import com.wtcrmandroid.model.requestdata.AKeyPullInRQ;
import com.wtcrmandroid.model.requestdata.SearchCustomerRQ;
import com.wtcrmandroid.presenter.activity.MainClientLibraryPresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.view.RefreshHeaderView;
import com.wtcrmandroid.view.RefreshLoadMoreFooterView;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.custompricing.TopChooseMenuBar;
import com.wtcrmandroid.view.popupwindow.TitlePopupWindow;
import com.wtcrmandroid.view.pulltorefresh.OnLoadMoreListener;
import com.wtcrmandroid.view.pulltorefresh.OnRefreshListener;
import com.wtcrmandroid.view.pulltorefresh.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/23.
 * 续单公海界面
 */

public class PublicSeaActivity extends BaseActivity<MainClientLibraryPresenter, List<SearchCustomerRP>> implements OnLoadMoreListener, OnRefreshListener {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.tcmb_bar)
    TopChooseMenuBar tcmbBar;
    @BindView(R.id.swipe_target)
    RecyclerView rvView;

    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView mHeaderView;
    @BindView(R.id.swipe_load_more_footer)
    RefreshLoadMoreFooterView mFooterView;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;


    private TitlePopupWindow titleLeftPopupWindow;
    private TitlePopupWindow titleCenterPopupWindow;
    private AreaPopUpWindow toWindow;

    private ClientLibraryAdapter adapter;
    private SearchCustomerRQ data;

    private int page=1;
    @Override
    protected int layout() {
        return R.layout.activity_client_library;
    }

    @Override
    protected void initView() {
        titlebar.setTitletext("续单公海");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titlebar.setRightImageResource(R.mipmap.ic_white_search);
        titlebar.setrightLayoutClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PublicSeaActivity.this,SearchClientLibraryActivity.class).putExtra("kind",1));
            }
        });
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
                                public void oNclicklistner(String text, int position) {
                                    titleLeftPopupWindow.dismiss();
                                    tcmbBar.setLeftText(text);
                                    tcmbBar.NoCheckStyle(1);
                                    tcmbBar.setIsCheck_number(0);
                                    data.setCustomerKind(text);
                                    page=1;
                                    data.setPageSize(page);
                                    presenter.getData(data,0);

                                }
                            });

                        }
                        titleLeftPopupWindow.show();
                        titleLeftPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                tcmbBar.NoCheckStyle(1);
                                tcmbBar.setIsCheck_number(0);
                            }
                        });
                        break;
                    case 2:
                        //右边弹窗
                        if (titleCenterPopupWindow == null) {
                            List list = new ArrayList();
                            list.add("全部");
                            list.add("公海");
                            list.add("销售库");
                            list.add("成单库");
                            titleCenterPopupWindow = new TitlePopupWindow(PublicSeaActivity.this, tcmbBar, list, 0, 0, new PoppupWindowTitleAdapter.oNclicklistner() {
                                @Override
                                public void oNclicklistner(String text, int position) {
                                    tcmbBar.setCenterText(text);
                                    titleCenterPopupWindow.dismiss();
                                    tcmbBar.NoCheckStyle(2);
                                    tcmbBar.setIsCheck_number(0);
                                    data.setCurrentStatus(text);
                                    page=1;
                                    data.setPageSize(page);
                                    presenter.getData(data,0);
                                }
                            });

                        }
                        titleCenterPopupWindow.show();
                        titleCenterPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                tcmbBar.NoCheckStyle(2);
                                tcmbBar.setIsCheck_number(0);
                            }
                        });
                        break;
                    case 3:
                        if (toWindow == null) {
                            initToWindow(tcmbBar);
                        }
                        toWindow.showPopWindow(tcmbBar);
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
                        toWindow.disMissPopWindow();

                        break;
                }


            }
        });
        mSwipeToLoadLayout.setRefreshHeaderView(mHeaderView);
        mSwipeToLoadLayout.setLoadMoreFooterView(mFooterView);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        presenter = new MainClientLibraryPresenter(this,this);
        data=new SearchCustomerRQ();
        presenter.getData(data,0);
        rvView.setLayoutManager(new LinearLayoutManager(this));
        rvView.setAdapter(adapter = new ClientLibraryAdapter(this));
        adapter.setStyle(1);
        adapter.setaKeyPullIn(new ClientLibraryAdapter.AKeyPullIn() {
            @Override
            public void AKeyPullIn(AKeyPullInRQ aKeyPullInRQ) {
                presenter.aKeyPullIn(aKeyPullInRQ,2);
            }
        });
    }


    @Override
    public void onRefresh() {
        page=1;
        data.setPageSize(page);
        presenter.getData(data,0);
    }

    @Override
    public void onLoadMore() {
        page=page+1;
        data.setPageSize(page);
        presenter.getData(data,1);

    }

    @Override
    public void returnData(int key, List<SearchCustomerRP> data) {
        switch(key){
            //刷新返回数据
            case 0:
                adapter.addList(data);
                mSwipeToLoadLayout.setRefreshing(false);
                break;
            //加载更多返回数据
            case 1:
                List<SearchCustomerRP> list=adapter.getList();
                list.addAll(data);
                adapter.addList(list);
                mSwipeToLoadLayout.setLoadingMore(false);
                break;
            case 2:
                showShortToast("拉入成功！");
                break;

        }
    }
    private Area toArea;

    private void initToWindow(View view) {
        toWindow = new AreaPopUpWindow(this, view);
        toWindow.setSelectAreaListener(new AreaPopUpWindow.SelectAreaListener() {
            @Override
            public void selectAreaOk(Area area, View parentView) {
                L.e(area.toString());
                tcmbBar.NoCheckStyle(3);
                tcmbBar.setIsCheck_number(0);
                data.setProvince(area.getSheng());
                data.setCity(area.getShi());
                data.setArea(area.getXian());
                page=1;
                data.setPageSize(page);
                presenter.getData(data,0);
            }
        });
    }
    @Override
    public void showShortToast(String text) {
        super.showShortToast(text);
        mSwipeToLoadLayout.setLoadingMore(false);
        if(page==1) {
            mSwipeToLoadLayout.setRefreshing(false);
            adapter.addList(new ArrayList<SearchCustomerRP>());
        } else
            mSwipeToLoadLayout.setLoadingMore(false);

    }
}
