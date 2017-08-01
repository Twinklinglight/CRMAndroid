package com.wtcrmandroid.activity.salepullcustomer;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wtcrmandroid.base.BaseActivity;
import com.wtcrmandroid.base.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.SelectAreaActivity;
import com.wtcrmandroid.utils.areaslection.Area;
import com.wtcrmandroid.model.requestdata.SaveCustomerRQ;
import com.wtcrmandroid.presenter.activity.SalePullintoCustomerPresenter;
import com.wtcrmandroid.utils.TextUtils;
import com.wtcrmandroid.view.custompricing.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 申中甲
 * 物通录入客户
 */

public class SalePullintoCustomerActivity extends BaseActivity<SalePullintoCustomerPresenter, String > {

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
    SaveCustomerRQ saveCustomerRQ;
    @Override
    protected int layout() {
        return R.layout.activity_sale_pullinto_customer;
    }

    @Override
    protected void initView() {

        mTitle.setTitletext("录入客户");
        mTitle.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        presenter=new SalePullintoCustomerPresenter(this,this);
        saveCustomerRQ=new SaveCustomerRQ();

    }

    @Override
    public void returnData(int key, String data) {
        finish();
        showShortToast("录入成功");

    }

    @OnClick({R.id.tv_submit, R.id.ll_member_sort, R.id.ll_company_address,
            R.id.ll_company_sort,R.id.ll_else})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                submit();
                break;
            case R.id.ll_member_sort:

                Intent intent = new Intent(this, SaleSelectActivity.class);
                intent.putExtra("TYPE",1);
                intent.putExtra("POSITION",memberPosition);
                startActivityForResult(intent,RequestCode);
                break;

            case R.id.ll_company_address:
                startActivityForResult(new Intent(this, SelectAreaActivity.class), 1);
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
        }else if(requestCode == 1){
            if (resultCode == RESULT_OK) {
                Area area = new Gson().fromJson(data.getStringExtra("selectedArea"), Area.class);
                mTvCompanyAddress.setText(area.getSheng() + "-" + area.getShi() + "-" + area.getXian());
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
    //提交数据
    private void submit() {
        String sort=mTvSort.getText().toString();
        saveCustomerRQ.setAttribution("WT");
        saveCustomerRQ.setUserId(MyApplication.application.getLoginData().getUserID());
        if(TextUtils.isNull(sort)){
            saveCustomerRQ.setDrpWTCustomerKind(sort);
        }else {
            Toast.makeText(this, "请选择会员类型！", Toast.LENGTH_SHORT).show();
            return;
        }
        String membersName =mEtMemberName.getText().toString();
        if(TextUtils.isNull(membersName)){
            saveCustomerRQ.setTbxUserName(membersName);
        }else {
            Toast.makeText(this, "请输入会员名称！", Toast.LENGTH_SHORT).show();
            return;
        }
        String companyName =mEtCompanyName.getText().toString();
        if(TextUtils.isNull(companyName)){
            saveCustomerRQ.setTbxCompanyName(companyName);
        }else {
            Toast.makeText(this, "请输入公司名称！", Toast.LENGTH_SHORT).show();
            return;
        }
        String phone =mEtPhoneNumber.getText().toString();
        if(TextUtils.isNull(phone)){
            saveCustomerRQ.setTbxCellNumber(phone);
        }else {
            Toast.makeText(this, "请输入手机号码！", Toast.LENGTH_SHORT).show();
            return;
        }
        String name =mEtCustomerName.getText().toString();
        if(TextUtils.isNull(name)){
            saveCustomerRQ.setTbxCustomerName(name);
        }else {
            Toast.makeText(this, "请输入客户姓名！", Toast.LENGTH_SHORT).show();
            return;
        }
        String companyAddress =mTvCompanyAddress.getText().toString();
        if(TextUtils.isNull(companyAddress)){
            saveCustomerRQ.setTxtCompAddress(companyAddress);
        }else {
            Toast.makeText(this, "请选择公司地址！", Toast.LENGTH_SHORT).show();
            return;
        }
        String detailsAddress =mEtAddrssDetails.getText().toString();
//        if(TextUtils.isNull(detailsAddress)){
            saveCustomerRQ.setTbxDetailAdress(detailsAddress);
//        }else {
//            Toast.makeText(this, "请输入公司详细地址！", Toast.LENGTH_SHORT).show();
//            return;
//        }
        String companyKind =mTvCompanySort.getText().toString();
//        if(TextUtils.isNull(companyKind)){
            saveCustomerRQ.setTbxCompanyKind(companyKind);
//        }else {
//            Toast.makeText(this, "请选择公司类型！", Toast.LENGTH_SHORT).show();
//            return;
//        }

        String customerPosition=mEtComstomerJob.getText().toString();
//        if(TextUtils.isNull(customerPosition)){
            saveCustomerRQ.setTbxCompanyKind(customerPosition);
//        }else {
//            Toast.makeText(this, "请输入客户职位！", Toast.LENGTH_SHORT).show();
//            return;
//        }
        String TellNumber=mEtLinePhone.getText().toString();
//        if(TextUtils.isNull(TellNumber)){
            saveCustomerRQ.setTbxTellNumber(TellNumber);
//        }else {
//            Toast.makeText(this, "请输入固定电话！", Toast.LENGTH_SHORT).show();
//            return;
//        }
        String emal=mEtEmail.getText().toString();
//        if(TextUtils.isNull(emal)){
            saveCustomerRQ.setTbxEmailNumber(emal);
//        }else {
//            Toast.makeText(this, "请输入电子邮箱！", Toast.LENGTH_SHORT).show();
//            return;
//        }
        String QQ=mEtQQ.getText().toString();
//        if(TextUtils.isNull(QQ)){
            saveCustomerRQ.setTbxQQNumber(QQ);
//        }else {
//            Toast.makeText(this, "请输入QQ号！", Toast.LENGTH_SHORT).show();
//            return;
//        }
        String FaxNumber=mEtMessage.getText().toString();
//        if(TextUtils.isNull(FaxNumber)){
            saveCustomerRQ.setTbxFaxNumber(FaxNumber);
//        }else {
//            Toast.makeText(this, "请输入传真号！", Toast.LENGTH_SHORT).show();
//            return;
//        }
        presenter.sedPost(saveCustomerRQ,0);
    }
}
