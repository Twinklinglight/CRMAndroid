package com.wtcrmandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.utils.areaslection.Area;
import com.wtcrmandroid.utils.areaslection.AreaImpl;
import com.wtcrmandroid.utils.areaslection.IAreaModule;
import com.wtcrmandroid.utils.areaslection.SelectAreaRecyclerAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.util.List;

import butterknife.BindView;


/**
 * 使用StartActivityForResult回调省市县数据
 * Created by Mr-Zhang on 2016/4/6.
 */
public class SelectAreaActivity extends BaseActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    private ImageButton imbBack;
    private RecyclerView rvSelectArea;
    private SelectAreaRecyclerAdapter adapter;
    private List<Area> arrayList;
    private IAreaModule areaModule;
    private Area selectedArea;
    private TextView tvProvince;
    private TextView tvCity;
    private TextView tvTown;
    private RadioButton rbCity;
    private RadioButton rbTown;
    private List<Area> provinceList;
    private List<Area> cityList;
    private int fromWhere;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.l);
//        initView();
//        initDate();
//        initAdapter();
//    }

    @Override
    protected int layout() {
        return R.layout.activity_select_area;
    }

    @Override
    protected void initView() {
        titlebar.setTitletext("选择地址");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rvSelectArea = (RecyclerView) findViewById(R.id.rv_select_area);
        rvSelectArea.setLayoutManager(new GridLayoutManager(this, 4));
        tvCity = (TextView) findViewById(R.id.tv_select_area_city);
        tvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cityList != null) {
                    adapter.setWhichToShow(SelectAreaRecyclerAdapter.SHOW_CITY);
                    adapter.setAreas(cityList);
                    adapter.initStringList();
                    adapter.notifyDataSetChanged();
                    tvCity.setText("");
                    rbTown.setChecked(false);
                }
            }
        });
        tvProvince = (TextView) findViewById(R.id.tv_select_area_province);
        tvProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (provinceList != null) {
                    adapter.setWhichToShow(SelectAreaRecyclerAdapter.SHOW_PROVINCE);
                    adapter.setAreas(provinceList);
                    adapter.initStringList();
                    adapter.notifyDataSetChanged();
                    tvCity.setText("");
                    rbCity.setChecked(false);
                    rbTown.setChecked(false);
                }
            }
        });
        tvTown = (TextView) findViewById(R.id.tv_select_area_town);
        rbCity = (RadioButton) findViewById(R.id.rb_select_area_city);
        rbTown = (RadioButton) findViewById(R.id.rb_select_area_town);
//        imbBack = getView(R.id.im_back);
//        imbBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
        initDate();
        initAdapter();
    }

//    private void initView() {
//        tvTitle = (TextView) findViewById(R.id.tv_title);
//        rvSelectArea = (RecyclerView) findViewById(R.id.rv_select_area);
//        rvSelectArea.setLayoutManager(new GridLayoutManager(this, 4));
//        tvCity = (TextView) findViewById(R.id.tv_select_area_city);
//        tvCity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (cityList != null) {
//                    adapter.setWhichToShow(SelectAreaRecyclerAdapter.SHOW_CITY);
//                    adapter.setAreas(cityList);
//                    adapter.initStringList();
//                    adapter.notifyDataSetChanged();
//                    tvCity.setText("");
//                    rbTown.setChecked(false);
//                }
//            }
//        });
//        tvProvince = (TextView) findViewById(R.id.tv_select_area_province);
//        tvProvince.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (provinceList != null) {
//                    adapter.setWhichToShow(SelectAreaRecyclerAdapter.SHOW_PROVINCE);
//                    adapter.setAreas(provinceList);
//                    adapter.initStringList();
//                    adapter.notifyDataSetChanged();
//                    tvCity.setText("");
//                    rbCity.setChecked(false);
//                    rbTown.setChecked(false);
//                }
//            }
//        });
//        tvTown = (TextView) findViewById(R.id.tv_select_area_town);
//        rbCity = (RadioButton) findViewById(R.id.rb_select_area_city);
//        rbTown = (RadioButton) findViewById(R.id.rb_select_area_town);
//        imbBack = getView(R.id.im_back);
//        imbBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//    }

    private void initAdapter() {
        adapter = new SelectAreaRecyclerAdapter(arrayList, SelectAreaRecyclerAdapter.SHOW_PROVINCE, this);
        adapter.setOnAreaClickListener(new SelectAreaRecyclerAdapter.OnAreaClickListener() {
            @Override
            public void onAreaClick(Area area, int whichToShow) {
                //Toast.makeText(getApplicationContext(),"省:"+area.getSheng()+"市:"+area.getShi()+"县:"+area.getXian(),Toast.LENGTH_SHORT).show();
                switch (whichToShow) {
                    case SelectAreaRecyclerAdapter.SHOW_PROVINCE:
                        showCity(area);
                        break;
                    case SelectAreaRecyclerAdapter.SHOW_CITY:
                        if (fromWhere == 12) {
                            selectedArea = area;
                            setResult(RESULT_OK, new Intent().putExtra("selectedArea", new Gson().toJson(selectedArea)));
                            finish();
                        } else {
                            showTown(area);
                        }
                        break;
                    case SelectAreaRecyclerAdapter.SHOW_TOWN:
                        tvTown.setText(area.getXian());
                        selectedArea = area;
                        setResult(RESULT_OK, new Intent().putExtra("selectedArea", new Gson().toJson(selectedArea)));
                        finish();
                        break;
                    default:
                        break;
                }
            }
        });
        rvSelectArea.setAdapter(adapter);
    }

    private void initDate() {

        areaModule = new AreaImpl();
        //初始化省
        arrayList = areaModule.getAllProvince();
        provinceList = areaModule.getAllProvince();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fromWhere = bundle.getInt("fromWhere", 0);
        }
        if (fromWhere == 12) {
            rbTown.setVisibility(View.GONE);
            tvTown.setVisibility(View.GONE);
        }
    }

    private void showCity(Area area) {
        tvProvince.setText(area.getSheng());
        rbCity.setChecked(true);
        arrayList.clear();
        arrayList = areaModule.selectByProvince(area.getSheng());
        cityList = areaModule.selectByProvince(area.getSheng());
        adapter.setWhichToShow(SelectAreaRecyclerAdapter.SHOW_CITY);
        adapter.setAreas(arrayList);
        adapter.initStringList();
        adapter.notifyDataSetChanged();
    }

    private void showTown(Area area) {
        tvCity.setText(area.getShi());
        rbTown.setChecked(true);
        arrayList.clear();
        arrayList = areaModule.selectByCity(area.getSheng(), area.getShi());
        adapter.setWhichToShow(SelectAreaRecyclerAdapter.SHOW_TOWN);
        adapter.setAreas(arrayList);
        adapter.initStringList();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void returnData(int key, Object data) {

    }



}
