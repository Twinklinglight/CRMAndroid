package com.wtcrmandroid.presenter.activity;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wtcrmandroid.Const;
import com.wtcrmandroid.MyApplication;
import com.wtcrmandroid.checkupdate.IOnInternetErrorModule;
import com.wtcrmandroid.checkupdate.LookingForUpdate;
import com.wtcrmandroid.model.reponsedata.LoginData;
import com.wtcrmandroid.model.requestdata.LoginRequestData;
import com.wtcrmandroid.presenter.BasePresenter;
import com.wtcrmandroid.utils.L;
import com.wtcrmandroid.utils.PreferenceUtils;
import com.wtcrmandroid.view.AllView;
import com.wtcrmandroid.view.dialog.SampleDialog;

import java.lang.reflect.Type;


/**
 * Created by 1363655717 on 2017-06-12.
 */

public class LoginPresenter extends BasePresenter {
    private LookingForUpdate lookingForUpdate;
    SampleDialog sampleDialog;

    public LoginPresenter(AllView view, Context context) {
        super(view, context);
    }

    @Override
    protected void returnData(int key, String response) {
        Type listType = new TypeToken<LoginData>() {
        }.getType();
        LoginData loginData = new Gson().fromJson(response, listType);
        L.e("成功" + loginData.toString());
        //保存token
        PreferenceUtils.setPrefString(context, Const.WT_CRM, Const.TOKEN, loginData.getToken());
        //保存token
        PreferenceUtils.setPrefString(context, Const.WT_CRM, Const.USERID, loginData.getUserID() + "");
        MyApplication.application.setLoginData(loginData);
        view.showShortToast("登录成功");
        view.returnData(key, loginData);
    }

    /**
     * 登录
     */
    public void login() {
        LoginRequestData loginRequestData = new LoginRequestData();

        /*loginRequestData.setUserName("jiashaowen");
        loginRequestData.setUserPass(MD5Utils.MD5("2746"));
        loginRequestData.setImei("612433e62ec71f96");
        MyApplication.application.setImei("612433e62ec71f96");*/
        loginRequestData.setUserName("jiaxinhe");

        loginRequestData.setImei("9209843230929988");
        //保存token
        PreferenceUtils.setPrefString(context, Const.WT_CRM, Const.IMEI, "9209843230929988");
        MyApplication.application.setImei("9209843230929988");
//        loginRequestData.setUserName("wt");
//        loginRequestData.setUserPass(MD5Utils.MD5("111111"));
//        loginRequestData.setImei("9209843230929999");
//        MyApplication.application.setImei("9209843230929999");
        /*loginRequestData.setUserName("shenzhongjia");
        loginRequestData.setUserPass(MD5Utils.MD5("shen123456"));
        loginRequestData.setImei("9209843230929971");
        MyApplication.application.setImei("9209843230929971");*/
//        loginRequestData.setUserName("fanqiayun");
//        loginRequestData.setUserPass("6ddedf54c788f57f");
//        loginRequestData.setImei("920984323092997");
        /*loginRequestData.setUserName("like");
        loginRequestData.setUserPass("766aed991c06fd48");
        loginRequestData.setImei("9209843230929972");*/
        L.e(loginRequestData.toString());
        post("Login/UserLogin", loginRequestData, 0);

    }

    /**
     * 检查更新
     */
    public void checkVersion() {
        lookingForUpdate = new LookingForUpdate(context);
        lookingForUpdate.setInternetErrorListener(new IOnInternetErrorModule.InternetErrorListener() {
            @Override
            public void netError() {

                showDialog("温馨提示", "网络不给力，请检查网络", 1, "取消", "重试", new SampleDialog.OnClickListener() {
                    @Override
                    public void onPositive() {
                        checkVersion();
                        dismissDialog();
                    }

                    @Override
                    public void onNegative() {
//                                WTActivityManager.INSTANCE.finishAllActivity();
                        dismissDialog();
                    }

                });
            }

            @Override
            public void noData() {

                showDialog("温馨提示", "网络不给力，请检查网络", 1, "取消", "重试", new SampleDialog.OnClickListener() {
                    @Override
                    public void onPositive() {
                        checkVersion();
                        dismissDialog();
                    }

                    @Override
                    public void onNegative() {
//                                WTActivityManager.INSTANCE.finishAllActivity();
                        dismissDialog();
                    }

                });
            }

            @Override
            public void timeOut() {

                showDialog("温馨提示", "网络不给力，请检查网络", 1, "取消", "重试", new SampleDialog.OnClickListener() {
                    @Override
                    public void onPositive() {
                        checkVersion();
                        dismissDialog();
                    }

                    @Override
                    public void onNegative() {
//                                WTActivityManager.INSTANCE.finishAllActivity();
                        dismissDialog();
                    }

                });
            }
        });
        lookingForUpdate.testUpdate(new LookingForUpdate.VersionUpdateListener() {
            @Override
            public void versionUpdate(boolean b) {
                //选择更新
                if (b) {

                    showDialog("升级提示", "已检测到新版本，是否升级？", 1, "否", "是", new SampleDialog.OnClickListener() {
                        @Override
                        public void onPositive() {
                            dismissDialog();
//                                    updateAPP();
                        }

                        @Override
                        public void onNegative() {
                            dismissDialog();
//                                    initKey();
                        }
                    });
                }


            }

            @Override
            public void haveToUpdate(boolean b) {
                //必须更新，不更新无法使用
                if (b) {

                    showDialog("升级提示", "检测到您的版本过低，请升级！", 1, "否", "是", new SampleDialog.OnClickListener() {
                        @Override
                        public void onPositive() {
                            dismissDialog();
//                            updateAPP();
                        }

                        @Override
                        public void onNegative() {
                            dismissDialog();
//                                    WTActivityManager.INSTANCE.finishAllActivity();
                        }
                    });
                }


            }

            @Override
            public void noUpdate(boolean b) {
                //不用更新
                if (b) {
//                            initKey();


                }
            }

//            @Override
//            public void loadingAdvert(LoadingAdvert loadingAdvert) {
//                advert = loadingAdvert;
//                mHandler.sendEmptyMessageDelayed(ADVERT_SHOW, 1000);
//            }
//        });
    });}

    public void showDialog(String title, String msg, int type, String leftBtn, String rightBtn, SampleDialog.OnClickListener onClickListener) {

        sampleDialog = new SampleDialog(context, title, msg, type);
        sampleDialog.setCanceledOnTouchOutside(false);
        sampleDialog.setCanceledOnTouchOutside(false);
        sampleDialog.setOnClickListener(onClickListener);
        sampleDialog.show();
        sampleDialog.setButtonText(leftBtn, rightBtn);
    }

    public void dismissDialog() {
        if (sampleDialog != null) {
            sampleDialog.dismiss();
            sampleDialog = null;
        }
    }
}

