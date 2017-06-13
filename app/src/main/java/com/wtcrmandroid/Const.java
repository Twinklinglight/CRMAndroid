package com.wtcrmandroid;

import android.os.Environment;

import java.io.File;

/**
 * 配置
 * Created by Mr-Zhang on 2016/3/12.
 */
public class Const {
    public final static String httpString = "http://";
    public final static String httpsString = "http://";
    //       public final static String wtServerString = "192.168.0.9:8091";//刘亚轲测试服务器地址
//     public final static String wtServerString="192.168.0.9:8099";//刘亚轲正式数据库的测试服务器
//	public final static String wtServerString = "122.115.40.15:2012";//测试数据库的测试服务器
//    public final static String wtServerString = "192.168.0.39:83";//代晓萍的测试服务器
//    public final static String wtServerString = "192.168.0.39:88";//代晓萍的测试服务器
    public final static String wtServerString = "android.chinawutong.com";
    public static final String ERROR_FILE_NAME = "erroData";// 存放错误信息的文件
    public static final String ERROR_TITLE = "erroTitle";// 错误引发项
    public static final String ERROR_MESSAGE = "erroMsg";// 错误信息
    public static final String ERROR_TIME = "erroTime";// 错误时间
    public static final String DOWN_KEY_URL = httpsString + "android.chinawutong.com/GetKey.ashx";// 下载密钥口令
    public static final String DOWNKEY = "WtAndroid123";
    // ///找车、找货、找配货、找物流、找专线
    public static final String SEARCH_URL = httpsString + "android.chinawutong.com/PostData.ashx";// 搜索
    public static final String GOODS_ORDER_URL = httpsString + wtServerString + "/ManageData.ashx";//货单推送
    public static final String QIANG_HUOYUAN_URL = httpsString + wtServerString + "/OrderForm.ashx";// 抢单、抛单、确认订单
    public static final String REQ_TOOL_URL = httpsString + wtServerString + "/ComServer.ashx?";// 工具箱（黑名单、身份证查询、GPS绑定或解绑）
    // 会员类型
    public static final int CHE_ZHU = 1;// 车主会员
    public static final int FA_HUO = 2;// 发货企业或个人
    public static final int GUO_NEI_WU_LIU = 3;// 国内物流
    public static final int PEI_HUO_ZHAN = 4;// 配货站
    public static final int GUO_JI_WU_LIU = 5;// 国际物流
    public static final int KUAI_DI_GONG_SI = 6;// 快递公司
    public static final int BAN_JIA_GONG_SI = 7;// 搬家公司
    public static final int WU_LIU_SHEA_BEI = 8;// 物流设备
    /*---------------开始写推送所复制的------------------------------*/
    // sharedpreference文件
    public static final String FILE_NAME = "FirstConfig";// 文件名称
    public static final String IS_POSTGPS = "IsPostGps";// 是否开启发送GPS
    public static final String IS_PUSHNOTIFICATION = "IsPushNotification";// 是否开启推送服务
    public static final String IMEI = "imei";
    public static final String USERID = "userId";// 用户Id
    public static final String PASSWORD = "password";// 用户Id

    // apk存储路径
    public static final String SDCARD_PATH = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + File.separatorChar + "wutong" + File.separatorChar + "agent" + File.separatorChar;
    public static final String PHOTO_PATH = SDCARD_PATH + "wtphoto" + File.separatorChar;// 拍照,调用相册,下载的缩略图
    public static final String UPPIC_PATH = SDCARD_PATH + "updatephoto" + File.separatorChar;// 形象展示
    public static final String NEW_APK = SDCARD_PATH + "wtdownload" + File.separatorChar;//apk下载地址
    public static final int NET_ERROR = 1234;//网络错误
    public static final int NO_DATA = 1235;//没有获取到数据

    // 信息类型
    public static final int CARLINE = 1;// 车源信息
    public static final int GOODSLINE = 2;// 货源信息
    public static final int PICKING = 3;// 配货站
    public static final int LOGISTICS_WEBSITE = 4;// 物流公司
    public static final int SPECIAL_LOGISTICS_LINE = 5;// 物流专线
    public static final int SPECIAL_PICKING_LINE = 6;// 配货专线
    public static final int YISHOUHUOYUAN = 7;// 一手货源
    public static final int YISHOUCHEYUAN = 8;// 一手车源
    public static final int PUSH = 9;// 货源、车源 来自推送
    public static final int CAR = 11;// 单条车源信息
    public static final int GOODS = 21;// 单条货源信息

    //推送
    /*---------------推送所复制结束------------------------------*/
    public static final String PUSH_URL = httpsString + "push.chinawutong.com/GetPushContent.ashx";// 真实 tcp://117.79.156.37:1883
    public static final String PUSH_NOTIFICATION_URL = httpsString + "android.chinawutong.com/push_UpdateRobOrder.ashx";// 统计点击Notification 次数
    public static final String CURRENT_ID_PUSH = "currentIdPush";
    public static final String PUSH_SERVICE = "pushService";
    public static String PUSH_MAIN_SWITCH = "push_main_switch";
    public static String IDENTIFICATION = "identification";

    /**
     * 在线投保
     */
//	public static final String BASE_URL = "http://192.168.0.9:8091/";//亚柯测试库
//    public final static String BASE_URL = "http://117.79.156.37:2012/";//测试数据库的测试服务器
    public static final String BASE_URL = httpsString + "www.chinawutong.com/";//正式库

    //    public static final String BASE_URL_H5 = "http://117.79.156.37:2012/";
    public static final String BASE_URL_H5 = httpsString + "android.chinawutong.com/";
    public static final String DOMAIN = ".ashx";

    /**
     * 1.保险试算 POST
     */
    public static final String INSURE_TRIAL = httpsString + "www.chinawutong.com/bx/InterfaceLayer/postProductPlanTrial" + DOMAIN;

    /**
     * 2、提交订单 POST
     */
    public static final String INSURE_SUBMIT = httpsString + "www.chinawutong.com/bx/InterfaceLayer/wtpostAddOrder" + DOMAIN;

    /**
     * 2、提交订单 阳光 生成订单号 POST
     */
//	public static final String INSURE_SUBMIT_SUN = "http://192.168.0.9:8091/" + "bx_ygpay" + DOMAIN;
    public static final String INSURE_SUBMIT_SUN = BASE_URL_H5 + "bx_ygpay" + DOMAIN;//测试数据库的测试服务器
//	public static final String INSURE_SUBMIT_SUN = BASE_URL + "bx_ygpay" + DOMAIN;

    /**
     * 3、请求保险订单列表 POST
     */

    public static final String INSURE_GETLIST_ORDER = httpsString + "www.chinawutong.com/bx/InterfaceLayer/getorderlist" + DOMAIN;

    /**
     * 4、请求保险保单列表 POST
     */

    public static final String INSURE_GETLIST_POLICY = httpsString + "www.chinawutong.com/bx/InterfaceLayer/getapplicationlist" + DOMAIN;

    public static final String INSURE_PAY_WLIUBI = BASE_URL_H5 + "ComServer.ashx";// 物流币支付在线投保
    // 工具箱请求类型
    public static final String TOOL_ALIPAY_IDCard = "alipayIDcard";
    public static final String TOOL_BXWULIUBI = "bxWliubi";

    /**
     * 阳光保险的 h5 页面
     */
    public static final String INSURE_H5 = BASE_URL_H5 + "bx/";
    //	public static final String INSURE_H5 = "http://117.79.156.37:2012/" + "bx/";
    public static final String INSURE_H5_DOMAIN = ".html";

    public static final String INSURE_PDF = BASE_URL + "bx/pdf/";//pdf文件
    public static final String INSURE_PDF_DOMAIN = ".pdf";

    public static final String INSURE_H5_YGBX_TBYD = INSURE_H5 + "ygbx-tbyd" + INSURE_H5_DOMAIN;//特别约定 阳光

    public static final String INSURE_H5_YGBX_MPSM = INSURE_H5 + "ygbx-mpsm" + INSURE_H5_DOMAIN;//免赔说明 阳光
    public static final String INSURE_H5_PABX_MPSM = INSURE_H5 + "pabx-mpsm" + INSURE_H5_DOMAIN;//免赔说明 平安
    public static final String INSURE_H5_RBBX_MPSM = INSURE_H5 + "rbbx-mpsm" + INSURE_H5_DOMAIN;//免赔说明 人保

    public static final String INSURE_H5_LPSM = INSURE_H5 + "lpsm" + INSURE_H5_DOMAIN;//理赔说明
    public static final String INSURE_H5_JBZS = INSURE_H5 + "jbzs" + INSURE_H5_DOMAIN;//基本知识
    public static final String INSURE_H5_SFBZ = INSURE_H5 + "sfbz" + INSURE_H5_DOMAIN;//收费标准

    public static final String POLICY_PATH = SDCARD_PATH + "policy";//保单路径
//	public static final String POLICY_PATH = SDCARD_PATH + "policy" + File.separatorChar;//保单路径

    //邀请好友列表  type="GetInvitelist"
    public static final String GET_INVITE_FRIENDS = httpsString + wtServerString + "/PostData.ashx";


}
