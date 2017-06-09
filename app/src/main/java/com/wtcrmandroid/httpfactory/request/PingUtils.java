package com.wtcrmandroid.httpfactory.request;

import android.content.Context;

import com.netease.LDNetDiagnoService.LDNetDiagnoListener;
import com.netease.LDNetDiagnoService.LDNetDiagnoService;


/**
 * Created by ${Lee} on 2017/2/13
 */

public class PingUtils implements LDNetDiagnoListener {

    private static PingUtils pingUtils;
    private Context mContext;
    private String showInfo = "";
    private boolean isRunning = false;
    private LDNetDiagnoService _netDiagnoService;

    public static PingUtils getInstance() {
        if (pingUtils == null) {
            synchronized (PingUtils.class) {
                if (pingUtils == null) {
                    pingUtils = new PingUtils();
                }
            }
        }
        return pingUtils;
    }

    //注入全局上下文
    public void initializeContext(Context context) {
        mContext = context;
    }

    /**
     * WTUserManager.INSTANCE.getCurrentUser() 没有登录过为空
     *
     * @param domainName
     */
    public void startPing(String domainName) {
//        if (!isRunning) {
//            showInfo = "";
//            _netDiagnoService = new LDNetDiagnoService(mContext,
//                    "android-" + WtHeader.getVersionCode(mContext) + "", mContext.getString(R.string.app_name), WtHeader.getVersionName(mContext),
//                    WTUserManager.INSTANCE.getCurrentUser() == null ? "" : WTUserManager.INSTANCE.getCurrentUser().getUserName(),
//                    null, domainName, null, null,
//                    "", "", this);
//            // 设置是否使用JNIC 完成traceroute
//            _netDiagnoService.setIfUseJNICTrace(true);
//            //        _netDiagnoService.setIfUseJNICConn(true);
//            _netDiagnoService.execute();
//            LogUtils.LogEInfo("PingUtils", "Traceroute with max 30 hops...");
//        } else {
//            LogUtils.LogEInfo("PingUtils", "开始诊断");
//            _netDiagnoService.cancel(true);
//            _netDiagnoService = null;
//        }

        isRunning = !isRunning;
    }

    @Override
    public void OnNetDiagnoFinished(String log) {
//        LogUtils.LogEInfo("PingUtils", log);
//        LogUtils.LogEInfo("PingUtils", showInfo);
//        LogUtils.LogEInfo("PingUtils", "over");
        isRunning = false;
        new Thread(new Runnable() {//耗时操作要起线程...有几个新手都是这个问题
            @Override
            public void run() {
                sendEmail();
            }
        }).start();
    }

    @Override
    public void OnNetDiagnoUpdated(String log) {
        showInfo += log;
//        LogUtils.LogEInfo("PingUtils",showInfo);
    }

    private String mReceiveEmail = "2777362081@qq.com";
    private String mSendEmail = "networkdiagnosis@qq.com";
    private String mSendPassword = "pxbjdkwyskoedaac";
    private String mHost = "smtp.qq.com";
    private String mPort = "465";

    private void sendEmail() {
//        LogMail sender = new LogMail().setUser(mSendEmail).setPass(mSendPassword)
//                .setFrom(mSendEmail).setTo(mReceiveEmail).setHost(mHost).setPort(mPort)
//                .setSubject("网路诊断").setBody(showInfo);
//        sender.init();
//        try {
////            sender.addAttachment(file.getPath(), file.getName());
//            if (sender.send()) {
//                LogUtils.LogEInfo("PingUtils", "网络诊断报告已发送至" + mReceiveEmail);
//            }
////            file.delete();
//        } catch (Exception e) {
//            LogUtils.LogEInfo("SendUtil", "Exception--PingUtils>" + e.getMessage());
//            e.printStackTrace();
//        }
    }
}
