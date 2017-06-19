package com.wtcrmandroid.custompricing;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wt-pc on 2017/6/17.
 */

public class TopChooseMenuBar extends LinearLayout {
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    /**
     * 左边按钮选择状态
     */
    private boolean isCheck_left = false;
    /**
     * 右边按钮选择状态
     */
    private boolean isCheck_right = false;


    private OnCheckedChangedListener onCheckedChangedListener;

    public TopChooseMenuBar(Context context) {
        super(context);
        init(context);
    }

    public TopChooseMenuBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TopChooseMenuBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TopChooseMenuBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.controls_top_choose_menu_bar, this);
        ButterKnife.bind(view);
    }

    /**
     * 选择监听
     * @param onCheckedChangedListener
     */
    public void setOnCheckedChangedListener(OnCheckedChangedListener onCheckedChangedListener) {
        this.onCheckedChangedListener = onCheckedChangedListener;
    }

    @OnClick({R.id.rl_left, R.id.rl_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_left:
                if(!isCheck_left) {
                    onCheckedChangedListener.isSelected(0);
                    isCheck_left=true;

                    changeStyle(tvLeft,ivLeft,true);
                    if(isCheck_right) {
                        changeStyle(tvRight, ivRight, false);
                        isCheck_right=false;
                    }
                }else {
                    changeStyle(tvLeft,ivLeft,false);
                    onCheckedChangedListener.isNoSelected(0);
                    isCheck_left=false;
                }

                break;
            case R.id.rl_right:
                if(!isCheck_right) {
                    onCheckedChangedListener.isSelected(1);
                    isCheck_right=true;
                    changeStyle(tvRight,ivRight,true);
                    if(isCheck_left) {
                        changeStyle(tvLeft, ivLeft, false);
                        isCheck_left=false;
                    }
                }else {
                    isCheck_right=false;
                    changeStyle(tvRight,ivRight,false);
                    onCheckedChangedListener.isNoSelected(1);
                }
                break;
        }
    }
    private void changeStyle(TextView tv,ImageView  iv,boolean isCheck){
        if(isCheck){
            tv.setTextColor(Color.parseColor("#3b9cff"));
            iv.setBackgroundResource(R.mipmap.ic_graytop_arrow);

        }else {
            tv.setTextColor(Color.parseColor("#2b2f33"));
            iv.setBackgroundResource(R.mipmap.ic_graydown_arrow);
        }

    }
    public   interface OnCheckedChangedListener{
        void isSelected(int i);
        void isNoSelected(int i);

    }
}
