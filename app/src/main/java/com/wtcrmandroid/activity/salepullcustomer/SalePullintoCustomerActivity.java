package com.wtcrmandroid.activity.salepullcustomer;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.BaseActivity;
import com.wtcrmandroid.custompricing.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

public class SalePullintoCustomerActivity extends BaseActivity {

    @BindView(R.id.title)
    TitleBar mTitle;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;                 //提交
    @BindView(R.id.tv_sort)
    TextView mTvSort;                   //会员类型
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
    @BindView(R.id.tv_company_address)
    TextView mTvCompanyAddress;
    @BindView(R.id.ll_company_address)
    LinearLayout mLlCompanyAddress;         //公司地址
    @BindView(R.id.et_addrss_details)
    EditText mEtAddrssDetails;
    @BindView(R.id.tv_company_sort)
    TextView mTvCompanySort;
    @BindView(R.id.ll_company_sort)
    LinearLayout mLlCompanySort;            //公司类型
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
    @BindView(R.id.ll_canshouqi)
    LinearLayout mLlCanshouqi;             //收起布局
    @BindView(R.id.tv_else)
    TextView mTvElse;
    @BindView(R.id.iv_else)
    ImageView mIvElse;                     //动画图标
    @BindView(R.id.ll_else)
    LinearLayout mLlElse;                  //收起按钮

    public static final int RequestCode = 0;
    private int memberPosition = 0;       //默认选择第一项
    private int companyPosition = 0;

    @Override
    protected int layout() {
        return R.layout.activity_sale_pullinto_customer;
    }

    @Override
    protected void initview() {

        mTitle.setTitletext("录入客户");

    }

    @Override
    public void returnData(int key, Object data) {

    }

    @OnClick({R.id.tv_submit, R.id.ll_member_sort, R.id.ll_company_address,
            R.id.ll_company_sort,R.id.ll_else})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                break;
            case R.id.ll_member_sort:

                Intent intent = new Intent(this, SaleSelectActivity.class);
                intent.putExtra("TYPE",1);
                intent.putExtra("POSITION",memberPosition);
                startActivityForResult(intent,RequestCode);
                break;

            case R.id.ll_company_address:
                break;
            case R.id.ll_company_sort:

                Intent companyIntent = new Intent(this, SaleSelectActivity.class);
                companyIntent.putExtra("TYPE",2);
                companyIntent.putExtra("POSITION",companyPosition);
                startActivityForResult(companyIntent,RequestCode);
                break;

            case R.id.ll_else:
                AnimationShouqi();
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
                    mTvCompanySort.setText(data.getStringExtra("TEXT"));
                    companyPosition = data.getIntExtra("POSITION",0);
                    break;
            }
        }
    }

    //点击收起布局
    private void AnimationShouqi() {

        if (mLlCanshouqi.getVisibility() != View.VISIBLE){

            mLlCanshouqi.setVisibility(View.VISIBLE);
            mTvElse.setText("收起");
            ObjectAnimator anim = ObjectAnimator.ofFloat(mIvElse, "rotation", 0f, 180f);
            anim.setDuration(200);
            anim.start();

        }else {

            mLlCanshouqi.setVisibility(View.GONE);
            mTvElse.setText("其他");
            ObjectAnimator anim = ObjectAnimator.ofFloat(mIvElse, "rotation", 180f, 0f);
            anim.setDuration(200);
            anim.start();

        }
    }
}
