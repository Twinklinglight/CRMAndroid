package com.wtcrmandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.pulltorefresh.SwipeRefreshHeaderLayout;
import com.wtcrmandroid.utils.SharedPreferencesUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zxd on 2017/6/9
 */

public class RefreshHeaderView extends SwipeRefreshHeaderLayout {

    private ImageView ivArrow;

    private ImageView ivSuccess;

    private TextView tvRefresh;

    private ProgressBar progressBar;

    private TextView tvTime;

    private int mHeaderHeight;

    private Animation rotateUp;

    private Animation rotateDown;

    private boolean rotated = false;
    private String mSharePreStr;

    public RefreshHeaderView(Context context) {
        this(context, null);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height);
        rotateUp = AnimationUtils.loadAnimation(context, R.anim.rotate_up);
        rotateDown = AnimationUtils.loadAnimation(context, R.anim.rotate_down);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvRefresh = (TextView) findViewById(R.id.tvRefresh);
        ivArrow = (ImageView) findViewById(R.id.ivArrow);
        ivSuccess = (ImageView) findViewById(R.id.ivSuccess);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        tvTime = (TextView) findViewById(R.id.tvTime);
        mSharePreStr = SharedPreferencesUtil.getSharePreStr(getContext(),"whichsp","gettime");//获取上次刷新时间
        tvTime.setText(mSharePreStr);

    }

    @Override
    public void onRefresh() {
        mSharePreStr = SharedPreferencesUtil.getSharePreStr(getContext(),"whichsp","gettime");//获取上次刷新时间
//        Log.e("onRefresh","onRefresh执行了");
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
        tvRefresh.setText("刷新中...");

    }

    @Override
    public void onPrepare() {
//        Log.e("onPrepare","onPrepare");
        tvTime.setText("上次刷新时间"+mSharePreStr);
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            ivArrow.setVisibility(VISIBLE);
            progressBar.setVisibility(GONE);
            ivSuccess.setVisibility(GONE);
            if (y > mHeaderHeight) {
//                Log.e("onMove","mHeaderHeight<y执行了");
                tvRefresh.setText("松开刷新");
                if (!rotated) {
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateUp);
                    rotated = true;
                }
            } else if (y < mHeaderHeight) {
                if (rotated) {
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateDown);
                    rotated = false;
                }
//                Log.e("onMove","defaulty执行了");
                tvRefresh.setText("下拉刷新");

            }
        }
    }

    @Override
    public void onRelease() {
//        Log.e("onRelease","onRelease执行了");
    }

    @Override
    public void onComplete() {
//        Log.e("onComplete","onComplete执行了");
        rotated = false;
        ivSuccess.setVisibility(VISIBLE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        tvRefresh.setText("刷新完成");
        SharedPreferencesUtil.putSharePre(getContext(),"whichsp","gettime",getTime()); //保存上次刷新时间
    }

    @Override
    public void onReset() {
//        Log.e("onReset","onReset执行了");
        rotated = false;
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }

    /**
     * 获取系统时间
     * @return
     */
    public String getTime() {
        return new SimpleDateFormat("HH:mm:ss", Locale.CHINA).format(new Date());
    }

}
