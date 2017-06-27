package com.wtcrmandroid.activity.foodpullcustomer;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.view.custompricing.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;
/**
*   食品录入客户
*  @author zxd
*  @date 2017/6/20
*/

public class PullintoCustomerActivity extends BaseActivity {

    @BindView(R.id.title)
    TitleBar mTitle;
    @BindView(R.id.ll_else)
    LinearLayout mLlElse;           //其他按钮
    @BindView(R.id.ll_canshouqi)
    LinearLayout mLlCanshouqi;      //其他布局(加不加动画看心情）
    @BindView(R.id.tv_else)
    TextView mTvElse;               //textview 其他
    @BindView(R.id.iv_else)
    ImageView mIvElse;              //向下箭头
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.tv_sort)
    TextView mTvSort;
    @BindView(R.id.ll_member_sort)
    LinearLayout mLlMemberSort;
    @BindView(R.id.et_member_name)
    EditText mEtMemberName;
    @BindView(R.id.et_company_name)
    EditText mEtCompanyName;
    @BindView(R.id.et_phone_number)
    EditText mEtPhoneNumber;
    @BindView(R.id.et_customer_name)
    EditText mEtCustomerName;
    @BindView(R.id.et_company_profile)
    EditText mEtCompanyProfile;
    @BindView(R.id.iv_company_profile)
    ImageView mIvCompanyProfile;
    @BindView(R.id.tv_company_address)
    TextView mTvCompanyAddress;
    @BindView(R.id.ll_company_address)
    LinearLayout mLlCompanyAddress;
    @BindView(R.id.et_addrss_details)
    EditText mEtAddrssDetails;
    @BindView(R.id.tv_company_sort)
    TextView mTvCompanySort;
    @BindView(R.id.ll_company_sort)
    LinearLayout mLlCompanySort;
    @BindView(R.id.tv_company_product)
    TextView mTvCompanyProduct;
    @BindView(R.id.ll_company_product)
    LinearLayout mLlCompanyProduct;
    @BindView(R.id.et_comstomer_job)
    EditText mEtComstomerJob;
    @BindView(R.id.et_line_phone)
    EditText mEtLinePhone;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_QQ)
    EditText mEtQQ;
    @BindView(R.id.et_message)
    EditText mEtMessage;

    public static final int RequestCode = 0;
    private int memberPosition = 0;       //默认选择第一项
    private int productPositon = 0;

    @Override
    protected int layout() {
        return R.layout.activity_pullinto_customer;
    }

    @Override
    protected void initview() {

        mTitle.setTitletext("录入客户");

        // 点击其他按钮
        mLlElse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLlCanshouqi.getVisibility() != View.VISIBLE) {

                    mLlCanshouqi.setVisibility(View.VISIBLE);
                    mTvElse.setText("收起");
                    ObjectAnimator anim = ObjectAnimator.ofFloat(mIvElse, "rotation", 0f, 180f);
                    anim.setDuration(200);
                    anim.start();


                } else {

                    mLlCanshouqi.setVisibility(View.GONE);
                    mTvElse.setText("其他");
                    ObjectAnimator anim = ObjectAnimator.ofFloat(mIvElse, "rotation", 180f, 0f);
                    anim.setDuration(200);
                    anim.start();

                }
            }
        });
    }

    @Override
    public void returnData(int key, Object data) {

    }


    @OnClick({R.id.tv_submit, R.id.ll_member_sort, R.id.iv_company_profile, R.id.ll_company_address,
            R.id.ll_company_sort, R.id.ll_company_product})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:                //提交
                break;
            case R.id.ll_member_sort:           //会员分类

                Intent intent = new Intent(this, FoodSelectActivity.class);
                intent.putExtra("TYPE",1);
                intent.putExtra("POSITION",memberPosition);
                startActivityForResult(intent,RequestCode);

                break;
            case R.id.iv_company_profile:       //公司简介语音图标
                break;
            case R.id.ll_company_address:       //公司地址
                break;
            case R.id.ll_company_sort:          //公司分类

                break;
            case R.id.ll_company_product:       //产品分类

                Intent intentProduct = new Intent(this, FoodSelectActivity.class);
                intentProduct.putExtra("TYPE",2);
                intentProduct.putExtra("POSITION",productPositon);
                startActivityForResult(intentProduct,RequestCode);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RequestCode){
            switch (resultCode){
                case 1:
                    mTvSort.setText(data.getStringExtra("TEXT"));
                    memberPosition = data.getIntExtra("POSITION",0);
                    break;
                case 2:
                    mTvCompanyProduct.setText(data.getStringExtra("TEXT"));
                    productPositon = data.getIntExtra("POSITION",0);
                    break;
            }
        }
    }
}
