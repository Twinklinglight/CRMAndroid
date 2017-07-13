package com.wtcrmandroid.activity.aboutdocument;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.fragment.DocumentFragAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 可以发起、审批
 * @author Create by zxd on 2017/7/3
 */

public class DocumentProcessActivity extends BaseActivity {


    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.TabLayout)
    android.support.design.widget.TabLayout TabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private DocumentFragAdapter documentFragAdapter;

    private List<Fragment> fragmentList;
    private List<String> stringList;

    @Override
    protected int layout() {
        return R.layout.activity_document_process;
    }

    @Override
    protected void initView() {
        fragmentList = new ArrayList<>();
        stringList = new ArrayList<>();
        fragmentList.add(new WaitMedealFragment());
        fragmentList.add(new MyHavedealFragment());
        fragmentList.add(new MyApplyDocFragment());
        titlebar.setLeftText("公文审批");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentProcessActivity.this.finish();
            }
        });

        documentFragAdapter = new DocumentFragAdapter(getSupportFragmentManager(),this,fragmentList,stringList);
        viewpager.setAdapter(documentFragAdapter);
        TabLayout.setupWithViewPager(viewpager);
        viewpager.setCurrentItem(1);


    }

    @Override
    public void returnData(int key, Object data) {

    }

    /**
     * 发起审批按钮
     */
    @OnClick(R.id.circle_write)
    public void onClick() {
    }
}
