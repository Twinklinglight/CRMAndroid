package com.wtcrmandroid.activity.field;

import android.content.Intent;
import android.hardware.Sensor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.wtcrmandroid.BaseMapActivity;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.PhotoChooseAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wt-pc on 2017/6/16.
 * 客户拜访
 */

public class CustomerCallActivity extends BaseMapActivity implements TakePhoto.TakeResultListener,InvokeListener {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rv_view)
    RecyclerView rvView;
    private PhotoChooseAdapter adapter;

    private List<String> photo_list=new ArrayList<>();


    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    @Override
    public void returnData(int key, Object data) {

    }

    @Override
    protected int layout() {
        return R.layout.activity_customer_call;
    }

    @Override
    protected void initview() {
        titlebar.setTitletext("客户拜访");
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvView.setLayoutManager(new GridLayoutManager(this, 4));
        rvView.setAdapter(adapter = new PhotoChooseAdapter());
        adapter.setList(photo_list);
        adapter.setMyOnClickListner(new PhotoChooseAdapter.MyOnClickListner() {
            @Override
            public void selectPhoto(int position) {
                File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists())file.getParentFile().mkdirs();
                Uri imageUri = Uri.fromFile(file);
            }

            @Override
            public void deletePhoto(int position) {

            }
        });
    }

    @Override
    public void getBundle(Bundle savedInstanceState) {
        super.getBundle(savedInstanceState);
        getTakePhoto().onSaveInstanceState(savedInstanceState);

    }

    @Override
    protected void getAddress(BDLocation location) {
        tvAddress.setText("当前位置： "+location.getLocationDescribe());

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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }
    /**
     *  获取TakePhoto实例
     * @return
     */
    public TakePhoto getTakePhoto(){
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }
    @Override
    public void takeSuccess(TResult result) {

    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }
}
