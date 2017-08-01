package com.wtcrmandroid.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.thinkcool.circletextimageview.CircleTextImageView;
import com.wtcrmandroid.Const;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.aboutmy.MycardActivity;
import com.wtcrmandroid.model.reponsedata.MineRpData;
import com.wtcrmandroid.model.requestdata.CustomerCallRQ;
import com.wtcrmandroid.model.requestdata.MineRQ;
import com.wtcrmandroid.model.requestdata.NumberRQ;
import com.wtcrmandroid.model.requestdata.UpdatePhotoRQ;
import com.wtcrmandroid.utils.Base64;
import com.wtcrmandroid.view.dialog.MyPhoneNumberDialog;
import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.view.dialog.PhotoDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.UUID;

import Decoder.BASE64Encoder;
import butterknife.BindView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by 1363655717 on 2017-06-01.
 */

public class MineFragment extends BaseFragment<MinePresenter,Object> implements MyPhoneNumberDialog.MyPhoneNumber, PhotoDialog.PhotoListener {
    @BindView(R.id.ib_head_picture)
    CircleTextImageView mIbHeadPicture;         //头像按钮
    @BindView(R.id.tv_name)
    TextView mTvName;                   //姓名
    @BindView(R.id.tv_motto_modify)
    TextView mTvMottoModify;            //座右铭
    @BindView(R.id.tv_my_phonenumber)
    TextView mTvMyPhonenumber;          //联系方式
    @BindView(R.id.tv_my_department)
    TextView mTvMyDepartment;           //所在部门
    @BindView(R.id.tv_my_job)
    TextView mTvMyJob;                  //现任职位
    @BindView(R.id.tv_my_card)
    TextView mTvMyCard;                 //我的名片
    @BindView(R.id.tv_my_attendance)
    TextView mTvMyAttendance;           //我的出勤

    private int PhoneNumber = 1;
    private int Motto = 2;

    private String username;
    private String rolename;
    private String phonenumber;
    private int userID;
    private MineRQ mineRQ;
    protected static Uri tempUri;
    private String fileName;
    private String filePath = Const.SDCARD_PATH +"picture";
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    private String ImageUrl;

    private static final String TAG = "MineFragment";

    @Override
    protected int Rlayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void init() {

        presenter = new MinePresenter(this,getContext());
        mineRQ = new MineRQ();
        userID = MyApplication.application.getLoginData().getUserID();
        mineRQ.setUserId(userID);
        presenter.postData(mineRQ);


        //点击我的头像
        mIbHeadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PhotoDialog(getContext(),MineFragment.this).show();
            }
        });

        /*//座右铭修改
        mTvMottoModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MyPhoneNumberDialog(getContext(),MineFragment.this,"签名",
                        mTvMottoModify.getText().toString(),Motto).show();
            }
        });*/

        //联系方式
        mTvMyPhonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyPhoneNumberDialog(getContext(),MineFragment.this,"联系方式",
                        mTvMyPhonenumber.getText().toString(),PhoneNumber).show();
            }
        });

        //我的名片
        mTvMyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MycardActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("rolename",rolename);
                intent.putExtra("phonenumber",phonenumber);
                intent.putExtra("imageUrl",ImageUrl);
                startActivity(intent);
            }
        });

        //我的考勤
        mTvMyAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "考勤接口暂无接入", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void returnData(int key, Object data) {
        switch (key){
            case 1:
                List<MineRpData> list = (List<MineRpData>)data;
                if (list.size()!= 0){
                    MineRpData mineRpData = list.get(0);

                    Log.i(TAG, "mineRpData: "+mineRpData.toString());

                    username = mineRpData.getUsername();
                    rolename = mineRpData.getRoleName();
                    phonenumber = mineRpData.getTelPhone();
                    ImageUrl = mineRpData.getHeaderImg();

                    mTvName.setText(username);
                    mTvMyDepartment.setText(mineRpData.getDepartmentName());
                    mTvMyJob.setText(rolename);
                    mTvMyPhonenumber.setText(phonenumber);
                    Log.i(TAG, "returnData: ImagUrl = "+ImageUrl);
                    Glide.with(getContext()).load(ImageUrl)
//                            .signature(new StringSignature(UUID.randomUUID().toString())) // 重点在这行
//                            .placeholder(R.mipmap.ic_photo_upload)
                            .error(R.mipmap.ic_photo_upload)
                            .into(mIbHeadPicture);
                    Log.i(TAG, "加载图片执行了啊 ");
                }
                break;
            case 2:
                presenter.postData(mineRQ); //修改成功之后刷新
                Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                presenter.postData(mineRQ); //上传成功之后刷新
                Toast.makeText(getContext(), "上传成功", Toast.LENGTH_SHORT).show();
                break;
        }


    }


    @Override
    public void getPhoneNumber(String number, int Type) {

        switch (Type){
            case 1:
                NumberRQ numberRQ = new NumberRQ();
                numberRQ.setUserId(userID);
                numberRQ.setOldPhone(phonenumber);
                numberRQ.setNewPhone(number);
                presenter.postNumber(numberRQ);

                break;
            case 2:
                mTvMottoModify.setText(number);
                Toast.makeText(getContext(), "本地修改完成", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PICTURE:
                Log.i("tag", "onActivityResult:take picture执行了 ");
                Log.i("tag", "tempUri = "+tempUri+"  data = "+data);
                startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                break;
            case CHOOSE_PICTURE:
                if (data == null) return;
                startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                break;
            case CROP_SMALL_PICTURE:
                if (data != null) {
                    setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                }
                break;
        }
    }

    @Override
    public void takePhoto() {
        Intent openCameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        fileName = "wutongHeadPicture" +".jpg";
        File file = new File(filePath, fileName);
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        tempUri = Uri.fromFile(file);
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    @Override
    public void chosePicture() {
        Intent openAlbumIntent = new Intent(
                Intent.ACTION_GET_CONTENT);
        openAlbumIntent.setType("image/*");
        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();

        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");    //裁剪后的bitmap对象

            UpdatePhotoRQ photoRQ = new UpdatePhotoRQ();
            CustomerCallRQ.ImageFile imageFile = new CustomerCallRQ.ImageFile();

            if (fileName == null){
                fileName = userID+"wutongHeadPicture" +".jpg";
            }
            imageFile.setFilename(userID+"wutongHeadPicture" +".jpg");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);      //转化为字节输出流
            byte[] datas = baos.toByteArray();                          //流转为字节数组
            Log.i("tag", "setImageToView: datas.size = "+datas.length);
            imageFile.setBasecode(new BASE64Encoder().encode(datas));

            photoRQ.setImg(imageFile);
            photoRQ.setUserId(userID);
            presenter.postPhoto(photoRQ);
        }
    }
}
