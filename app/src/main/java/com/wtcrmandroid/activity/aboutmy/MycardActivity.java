package com.wtcrmandroid.activity.aboutmy;

import android.view.View;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.view.popupwindow.popubwindow_share;

import butterknife.BindView;
/**
*
* @author Create by zxd on 2017/6/21
*
*/

public class MycardActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitlebar;

    @Override
    protected int layout() {
        return R.layout.activity_mycard;
    }

    @Override
    protected void initView() {

        mTitlebar.setTitletext("我的名片");
        mTitlebar.setrighttext("分享");

        //分享按钮
        mTitlebar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new popubwindow_share(MycardActivity.this,mTitlebar);
            }
        });
    }


    @Override
    public void returnData(int key, Object data) {

    }

}
