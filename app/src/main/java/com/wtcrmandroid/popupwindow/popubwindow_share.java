package com.wtcrmandroid.popupwindow;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.wtcrmandroid.Const;
import com.wtcrmandroid.R;
import com.wtcrmandroid.sharesdk.onekeyshare.OnekeyShare;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by zxd on 2017/6/17
 */

public class popubwindow_share extends PopupWindow {

    @BindView(R.id.linear_wechat)
    LinearLayout mLinearWechat;
    @BindView(R.id.linear_wechatmoments)
    LinearLayout mLinearWechatmoments;
    @BindView(R.id.linear_qq)
    LinearLayout mLinearQq;
    @BindView(R.id.linear_qzone)
    LinearLayout mLinearQzone;
    @BindView(R.id.linear_sinaweibo)
    LinearLayout mLinearSinaweibo;
    private View view;
    private Context mContext;

    public popubwindow_share(Context context, View parentView) {
        super(context);
        this.mContext = context;
        initView();
        setContentView(view);
        showAsDropDown(parentView);
    }

    private void initView() {
        view = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_share, null);
        ButterKnife.bind(this, view);

        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);

        setFocusable(true);
        setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable();
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);

    }


    @OnClick({R.id.linear_wechat, R.id.linear_wechatmoments, R.id.linear_qq, R.id.linear_qzone, R.id.linear_sinaweibo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear_wechat:            //分享到到微信好友
                showShare(Wechat.NAME);
//                show(Wechat.NAME);
                dismiss();
                break;
            case R.id.linear_wechatmoments:     //分享到微信朋友圈
                showShare(WechatMoments.NAME);
                dismiss();
                break;
            case R.id.linear_qq:                //分享到qq好友
                showShare(QQ.NAME);
                dismiss();
                break;
            case R.id.linear_qzone:             //分享到qq空间
                showShare(QZone.NAME);
                dismiss();
                break;
            case R.id.linear_sinaweibo:         //分享到新浪微博
                showShare(SinaWeibo.NAME);
                dismiss();
                break;
        }
    }

    private void show(String platform) {

        ShareSDK.initSDK(mContext);
        OnekeyShare oks = new OnekeyShare();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        oks.setPlatform(platform);
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setText("微信分享");
        oks.show(mContext);

    }

    /**
     * 分享到指定平台
     *
     * @param platform
     */
    private void showShare(String platform) {
        //分享点击跳转到h5注册页面
//        String URL_SHARE_CLICK = Const.httpString + Const.wtServerString +
//                "/AppShare/html/appShareRegister.html?inviteCode=" +
//                WTUserManager.INSTANCE.getCurrentUser().getUserName();

        String URL_SHARE_CLICK = "http://www.baidu.com";

        String path = Const.SDCARD_PATH + "share/image"; //分享需要用到的图片
        String iconName = "icon_wu_tong_logo.png";
        final String iconPath = path + File.separatorChar + iconName;
        copyImgToSD(path, iconName);
        ShareSDK.initSDK(mContext);
        OnekeyShare oks = new OnekeyShare();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        oks.setPlatform(platform);
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(mContext.getString(R.string.app_name));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用 仅在Linked-in,QQ
        oks.setTitleUrl(URL_SHARE_CLICK);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        File iconFile = new File(iconPath);
        if (iconFile.exists()) {//确保SDcard下面存在此张图片
            oks.setImagePath(iconPath);
        }
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我在用免费的配货软件，货源信息多，找货发车很方便，你可以试试: " + URL_SHARE_CLICK);
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(URL_SHARE_CLICK);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("北京物通时空网络科技开发有限公司");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(URL_SHARE_CLICK);
        // 启动分享GUI
        oks.show(mContext);
    }


    private void copyImgToSD(String sPath, String name) {
        AssetManager am = mContext.getAssets();
        File file = new File(sPath + "/" + name);
        if (!file.exists()) {
            File pathFile = new File(sPath);
            if (!pathFile.exists()) {
                pathFile.mkdirs();//创建多级文件夹
            }
            try {
                InputStream inputStream = am.open(name);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int count;
                while ((count = inputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, count);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
