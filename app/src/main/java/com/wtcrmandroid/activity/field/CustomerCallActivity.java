package com.wtcrmandroid.activity.field;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.wtcrmandroid.BaseMapActivity;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.crm.MyClientLibrary;
import com.wtcrmandroid.adapter.recycleview.PhotoChooseAdapter;
import com.wtcrmandroid.model.requestdata.CustomerCallRQ;
import com.wtcrmandroid.presenter.activity.CustomerCallP;
import com.wtcrmandroid.utils.Base64;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.utils.iat.Iat;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wt-pc on 2017/6/16.
 * 客户拜访
 */

public class CustomerCallActivity extends BaseMapActivity<CustomerCallP, Object> implements TakePhoto.TakeResultListener, InvokeListener {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rv_view)
    RecyclerView rvView;
    @BindView(R.id.tv_customer_name)
    TextView tvCustomerName;
    @BindView(R.id.tv_remarks)
    EditText tvRemarks;
    @BindView(R.id.et_addrss_details)
    EditText etAddrssDetails;
    private PhotoChooseAdapter adapter;

    private List<String> photo_list = new ArrayList<>();
    List<CustomerCallRQ.ImageFile> img = new ArrayList<>();

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private Uri imageUri;


    private String customername;
    private String customerid;
    private String userID;
    private String address;
    private CustomerCallRQ customerCallRQ;

    @Override
    public void returnData(int key, Object data) {
        showShortToast("提交成功！");
        finish();

    }

    @Override
    protected int layout() {
        return R.layout.activity_customer_call;
    }

    @Override
    protected void initview() {
        presenter = new CustomerCallP(this, this);
        titlebar.setTitletext("客户拜访");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvView.setLayoutManager(new GridLayoutManager(this, 4));
        rvView.setAdapter(adapter = new PhotoChooseAdapter(this));
        adapter.setList(photo_list);
        customerCallRQ = new CustomerCallRQ();
        userID = MyApplication.application.getLoginData().getUserID() + "";
        customerCallRQ.setUserId(userID);
        adapter.setMyOnClickListner(new PhotoChooseAdapter.MyOnClickListner() {
            @Override
            public void selectPhoto(int position) {
                File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                imageUri = Uri.fromFile(file);
                CompressConfig config = new CompressConfig.Builder()
                        .setMaxSize(51200)
                        .setMaxPixel(800)
                        .enableReserveRaw(true)
                        .create();
                takePhoto.onEnableCompress(config, true);
                takePhoto.onPickFromCapture(imageUri);
            }

            @Override
            public void deletePhoto(int position) {
                photo_list.remove(position);
                adapter.setList(photo_list);
                img.remove(position);
            }
        });
    }

    @Override
    public void getBundle(Bundle savedInstanceState) {
        super.getBundle(savedInstanceState);
        getTakePhoto().onCreate(savedInstanceState);

    }

    @Override
    protected void getAddress(BDLocation location) {
        tvAddress.setText("当前位置： " + location.getLocationDescribe());
        customerCallRQ.setLng(location.getLatitude());
        customerCallRQ.setLat(location.getLongitude());
        address = location.getLocationDescribe();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (10 == requestCode && RESULT_OK == resultCode) {
            customername = data.getStringExtra("customername");
            customerid = data.getStringExtra("customerid");
            tvCustomerName.setText(customername);
            tvCustomerName.setTextColor(Color.parseColor("#2b2f33"));
            customerCallRQ.setCustomerId(customerid);
            customerCallRQ.setCustomerName(customername);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    @Override
    public void takeSuccess(TResult result) {
        photo_list.add(result.getImage().getCompressPath());
        adapter.setList(photo_list);
        CustomerCallRQ.ImageFile imageFile = new CustomerCallRQ.ImageFile();
        imageFile.setFilename(userID + System.currentTimeMillis() + ".jpg");
        try {
            imageFile.setBasecode(Base64.encodeBase64File(result.getImage().getCompressPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        img.add(imageFile);
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {
        showShortToast("取消当前操作！");

    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }


    @OnClick({R.id.ll_select_customer, R.id.iv_microphone, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_select_customer:
                startActivityForResult(new Intent(CustomerCallActivity.this, MyClientLibrary.class).putExtra("style", 1), 10);
                break;
            case R.id.iv_microphone:
                doVoice(tvRemarks);
                break;
            case R.id.bt_submit:
                if (customerCallRQ.getLat() == 0) {
                    showShortToast("请等待定位完成！");
                    return;
                }
                if (customerCallRQ.getCustomerName() == null) {
                    showShortToast("请选择拜访客户！");
                    return;
                }
                String addressdetails = etAddrssDetails.getText().toString();
                if (addressdetails.equals("")) {
                    showShortToast("请填写详细地址！");
                    return;
                }
                customerCallRQ.setAddressDetail(address + addressdetails);
                customerCallRQ.setRemarks(tvRemarks.getText().toString());
                customerCallRQ.setImg(img);
                presenter.sedPost(customerCallRQ, 0);

                break;
        }
    }

    public void doVoice(final EditText etText) {

        Iat iat = new Iat(this);
        iat.iatRecognize();
        iat.setSetRestult(new Iat.setResult() {
            @Override
            public void succeed(String result) {
                etText.setText(result);
            }

            @Override
            public void failed(String iatError) {
                L.e("出现了一个错误，请您重试");
            }
        });
    }


}
