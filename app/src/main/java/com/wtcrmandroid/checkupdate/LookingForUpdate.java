package com.wtcrmandroid.checkupdate;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.wtcrmandroid.BuildConfig;
import com.wtcrmandroid.Const;
import com.wtcrmandroid.httpfactory.HttpRequest;
import com.wtcrmandroid.httpfactory.callback.FileCallBack;

import java.io.File;
import java.io.IOException;

/**
 * 版本升级
 * 1-必须升级；2-选择升级
 * Created by Mr-Zhang on 2016/6/13.
 */
public class LookingForUpdate implements IOnInternetErrorModule {
    private Context context;
    private DownLoadFinishListener downLoadFinishListener;
    private DownLoadInfoListener downLoadInfoListener;
    private File file;
    private InternetErrorListener internetErrorListener;

    @Override
    public void setInternetErrorListener(InternetErrorListener internetErrorListener) {
        this.internetErrorListener = internetErrorListener;
    }

    public void setDownLoadInfoListener(DownLoadInfoListener downLoadInfoListener) {
        this.downLoadInfoListener = downLoadInfoListener;
    }

    public void setDownLoadFinishListener(DownLoadFinishListener downLoadFinishListener) {
        this.downLoadFinishListener = downLoadFinishListener;
    }

    public LookingForUpdate(Context context) {
        this.context = context;
    }

    public interface VersionUpdateListener {
        //选择升级
        void versionUpdate(boolean b);

        //必须升级
        void haveToUpdate(boolean b);

        //不用升级
        void noUpdate(boolean b);


    }

    public interface DownLoadInfoListener {
        void totalSize(long totalSize);

        void currentProgress(float currentProgress);
    }

    public interface DownLoadFinishListener {
        void downLoadFinish();
    }

    /**
     * 检测是否需要更新
     */
    public void
    testUpdate(final VersionUpdateListener versionUpdateListener) {
//        String url = Const.httpsString + Const.wtServerString + "/update.ashx?type=android";
//        HashMap<String, String> params = new HashMap<>();
//        params.put("endTime", System.currentTimeMillis() + "");
//        params.put("version", WtHeader.getVersionCode(context) + "");
//        int densityDpi = PhoneUtils.getDensityDpi(context);
//        int deviceKind;
//        if (densityDpi < 480) {
//            deviceKind = 1;
//        } else if (densityDpi >= 480 && densityDpi <= 720) {
//            deviceKind = 2;
//        } else {//densityDpi > 1080
//            deviceKind = 3;
//        }
//        params.put("deviceKind", deviceKind + "");
//        HttpRequest.instance().sendGet(url, params, LookingForUpdate.class, new StringCallBack() {
//            @Override
//            public void onError(int errorRet, String errorMsg) {
//                if (errorRet == 1) {
//                    versionUpdateListener.versionUpdate(true);
//                    versionUpdateListener.haveToUpdate(false);
//                    versionUpdateListener.noUpdate(false);
//                } else if (errorRet == 3) {
//                    versionUpdateListener.versionUpdate(false);
//                    versionUpdateListener.haveToUpdate(true);
//                    versionUpdateListener.noUpdate(false);
//                } else if (errorRet == 4) {
//                    try {
//                        versionUpdateListener.loadingAdvert(LoadingAdvert.parseData(new JSONArray(errorMsg)
//                                .getJSONObject(0)));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    versionUpdateListener.versionUpdate(false);
//                    versionUpdateListener.haveToUpdate(false);
//                    versionUpdateListener.noUpdate(false);
//                } else {
//                    versionUpdateListener.versionUpdate(false);
//                    versionUpdateListener.haveToUpdate(false);
//                    versionUpdateListener.noUpdate(true);
//                }
//            }
//
//            @Override
//            public void onResponse(String response) {
//                versionUpdateListener.versionUpdate(false);
//                versionUpdateListener.haveToUpdate(false);
//                versionUpdateListener.noUpdate(true);
//            }
//
//            @Override
//            public void onNetError(Exception e) {
//                if (internetErrorListener != null) {
//                    internetErrorListener.netError();
//                }
//            }
//        });
    }

    /**
     * 下载所需文件
     */
    public void downFile() {
        String url = "http://android.chinawutong.com/wutong/wutonglogics.apk?r=" + Math.random() * 100000;
//        String apkName = "";
//        if (type.equals("1")) {//车主
//            url = Const.httpsString + Const.wtServerString + "/wutong/wutongdriver.apk?r=" + Math.random() * 100000;
//            apkName = "wutongdriver.apk";
//        }
//        if (type.equals("2")) {//货主
//            url = Const.httpsString + Const.wtServerString + "/wutong/wutonghuozhu.apk?r=" + Math.random() * 100000;
//            apkName = "wutonghuozhu.apk";
//        }
//        if (type.equals("3")) {//物流公司
//            url = Const.httpsString + Const.wtServerString + "/wutong/wutonglogics.apk?r=" + Math.random() * 100000;
//            apkName = "wutonglogics.apk";
//        }
//        if (type.equals("4")) {//配货站
//            url = Const.httpsString + Const.wtServerString + "/wutong/wutongphxxb.apk?r=" + Math.random() * 100000;
//            apkName = "wutongphxxb.apk";
//        }
        try {
            HttpRequest.instance().downFile(url, new FileCallBack(Const.NEW_APK, "wutongphxxb.apk") {
                @Override
                public void onError(int errorRet, String errorMsg) {

                }

                @Override
                public void onResponse(File response) {
                    if (downLoadFinishListener != null) {
                        downLoadFinishListener.downLoadFinish();
                        file = response;
                        startInstall();
                    }
                }

                @Override
                public void onNetError(Exception e) {

                }

                @Override
                public void inProgress(long totalLenth, float progress) {
                    if (downLoadInfoListener != null) {
                        downLoadInfoListener.totalSize(totalLenth);
                        downLoadInfoListener.currentProgress(progress);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行安装
     */
    public void startInstall() {
        if (file != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //判断是否是AndroidN以及更高的版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive");

            } else {
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
