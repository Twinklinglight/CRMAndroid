package com.wtcrmandroid.activity.foodpullcustomer;

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

public class FoodSelectActivity extends BaseActivity implements FoodSelectAdapter.SelectListener {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;
    @BindView(R.id.rc_select)
    RecyclerView mRcSelect;
    private int mType;           //类型 1、会员类型 2、产品类型
    private int mpositon = 0;     //默认选中的位置

    private List<String> mDataList = new ArrayList<>();
    private FoodSelectAdapter mFoodSelectAdapter;


    @Override
    protected int layout() {
        return R.layout.activity_food_select;
    }

    @Override
    protected void initview() {

        mType = getIntent().getIntExtra("TYPE", 0);
        mpositon = getIntent().getIntExtra("POSITION",0);

        if (this.mType == 1){
            mTitlebar.setTitletext("会员分类");
            mDataList.add("招商企业");
            mDataList.add("代理商");
            mDataList.add("商业服务");
            mDataList.add("原料供应");
        }else if (this.mType == 2){

            mTitlebar.setTitletext("产品分类");
            mDataList.add("酒类");
            mDataList.add("饮料");
            mDataList.add("冲调礼盒");mDataList.add("休闲食品");mDataList.add("方便食品");
            mDataList.add("低温食品");mDataList.add("罐头食品");mDataList.add("干鲜调味");
            mDataList.add("奶豆制品");mDataList.add("茶叶糖类");mDataList.add("粮油类");
            mDataList.add("水果蔬菜");mDataList.add("水产肉禽");mDataList.add("保健食品");
            mDataList.add("食品添加剂");mDataList.add("食品包装");mDataList.add("食品机械");
            mDataList.add("基础原料");mDataList.add("半成品原料");mDataList.add("进口食品");

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
    public void itemClick(int position,int type) {

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
