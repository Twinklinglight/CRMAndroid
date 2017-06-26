package com.wtcrmandroid.activity.salepullcustomer;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseActivity;
import com.wtcrmandroid.adapter.recycleview.FoodSelectAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SaleSelectActivity extends BaseActivity implements FoodSelectAdapter.SelectListener {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.rc_select)
    RecyclerView mRcSelect;

    private int mType;             //类型 1、会员类型 2、公司类型
    private int mpositon = 0;     //默认选中的位置

    private List<String> mDataList = new ArrayList<>();
    private FoodSelectAdapter mFoodSelectAdapter;

    @Override
    protected int layout() {
        return R.layout.activity_sale_select;
    }

    @Override
    protected void initview() {

        mType = getIntent().getIntExtra("TYPE", 0);
        mpositon = getIntent().getIntExtra("POSITION",0);

        if (this.mType == 1){

            mTitlebar.setTitletext("会员类型");
            mDataList.add("国内物流公司");
            mDataList.add("车主");
            mDataList.add("配货信息部");
            mDataList.add("快递公司");mDataList.add("搬家公司");mDataList.add("发货企业或个人");
            mDataList.add("物流设备企业");mDataList.add("物流园区");mDataList.add("停车场");

        }else if (this.mType == 2){

            mTitlebar.setTitletext("公司类型");
            mDataList.add("有限责任公司");
            mDataList.add("个体经营");
            mDataList.add("集体企业");mDataList.add("国有企业");
            mDataList.add("股份有限公司");mDataList.add("其他");

        }

        //返回按钮
        mTitlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRcSelect.setLayoutManager(llm);
        mFoodSelectAdapter = new FoodSelectAdapter(mDataList,this,mpositon,this,mType);
        //添加分割线
        mRcSelect.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        mRcSelect.setAdapter(mFoodSelectAdapter);

    }

    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    public void itemClick(int position, int type) {

        mpositon = position;
        mFoodSelectAdapter.notifyDataSetChanged();

        if (type == 1){

            String text = mDataList.get(position);
            Intent intent = new Intent();
            intent.putExtra("TEXT",text);           //回调选中文字
            intent.putExtra("POSITION",position);   //回调选中位置
            setResult(1,intent);
        }else if(type == 2){

            String text = mDataList.get(position);
            Intent intent = new Intent();
            intent.putExtra("TEXT",text);
            intent.putExtra("POSITION",position);
            setResult(2,intent);
        }

        this.finish();

    }
}
