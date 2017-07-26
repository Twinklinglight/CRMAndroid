package com.wtcrmandroid.view.custompricing;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.utils.L;

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
    @BindView(R.id.v_center)
    View vCenter;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.iv_center)
    ImageView ivCenter;
    @BindView(R.id.rl_center)
    RelativeLayout rlCenter;
    //当前选中位置
    private int isCheck_number = 0;

    public void setIsCheck_number(int isCheck_number) {
        this.isCheck_number = isCheck_number;
    }

    private OnCheckedChangedListener onCheckedChangedListener;

    public TopChooseMenuBar(Context context) {
        super(context);
        init(context);
        L.e("1");
    }

    public TopChooseMenuBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        L.e("2");
    }

    public TopChooseMenuBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        L.e("3");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TopChooseMenuBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void setStrings(String[] strings) {
        switch (strings.length) {
            case 2:
                setText(tvLeft,strings[0]);
                setText(tvRight,strings[1]);
                break;
            case 3:
                setText(tvLeft,strings[0]);
                setText(tvCenter,strings[1]);
                setText(tvRight,strings[2]);
                rlCenter.setVisibility(VISIBLE);
                vCenter.setVisibility(VISIBLE);
                break;
        }

    }

    public void setLeftText(String text) {
        setText(tvLeft,text);

    }

    public void setCenterText(String text) {
        setText(tvCenter,text);

    }

    public void setRightText(String text) {
        setText(tvRight,text);

    }

    public void setRightAllText(String text){
        setAllText(tvRight,text);
    }
    public void setRightTextNo(String text) {
       tvRight.setText(text);

    }
    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.controls_top_choose_menu_bar, this);
        ButterKnife.bind(view);
    }

    /**
     * 选择监听
     *
     * @param onCheckedChangedListener
     */
    public void setOnCheckedChangedListener(OnCheckedChangedListener onCheckedChangedListener) {
        this.onCheckedChangedListener = onCheckedChangedListener;
    }

    @OnClick({R.id.rl_left, R.id.rl_center, R.id.rl_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_left:
                if (isCheck_number == 1) {
                    NoCheckStyle(1);
                    isCheck_number=0;
                }else {
                    IsCheckStyle(1);
                    NoCheckStyle(isCheck_number);
                    isCheck_number = 1;
                    onCheckedChangedListener.isSelected(1);
                }

                break;
            case R.id.rl_center:
                if (isCheck_number == 2) {
                    NoCheckStyle(2);
                    isCheck_number=0;
                }else {
                    IsCheckStyle(2);
                    NoCheckStyle(isCheck_number);
                    isCheck_number = 2;
                    onCheckedChangedListener.isSelected(2);
                }

                break;
            case R.id.rl_right:
                if (isCheck_number == 3) {
                    NoCheckStyle(3);
                    isCheck_number=0;
                }else {
                    IsCheckStyle(3);
                    NoCheckStyle(isCheck_number);
                    isCheck_number = 3;
                    onCheckedChangedListener.isSelected(3);
                }

                break;
        }
    }

    public void IsCheckStyle(int i) {
        switch (i) {
            case 1:
                tvLeft.setTextColor(Color.parseColor("#3b9cff"));
                ivLeft.setBackgroundResource(R.mipmap.ic_arrow_up);
                break;
            case 2:
                tvCenter.setTextColor(Color.parseColor("#3b9cff"));
                ivCenter.setBackgroundResource(R.mipmap.ic_arrow_up);
                break;
            case 3:
                tvRight.setTextColor(Color.parseColor("#3b9cff"));
                ivRight.setBackgroundResource(R.mipmap.ic_arrow_up);
                break;
            default:
                break;
        }


    }

    public void NoCheckStyle(int i) {
        switch (i) {
            case 0:

                break;
            case 1:
                tvLeft.setTextColor(Color.parseColor("#2b2f33"));
                ivLeft.setBackgroundResource(R.mipmap.ic_arrow_down);
                break;
            case 2:
                tvCenter.setTextColor(Color.parseColor("#2b2f33"));
                ivCenter.setBackgroundResource(R.mipmap.ic_arrow_down);
                break;
            case 3:
                tvRight.setTextColor(Color.parseColor("#2b2f33"));
                ivRight.setBackgroundResource(R.mipmap.ic_arrow_down);
                break;
            default:
                break;
        }
        onCheckedChangedListener.isNoSelected(i);

    }

    private void setText(TextView view, String text ){
        if(text.length()>4){
             text=text.substring(0,4);
            text=text+"...";
        }
        view.setText(text);

    }

    private void setAllText(TextView view,String text){
        view.setText(text);
    }
    public interface OnCheckedChangedListener {
        void isSelected(int i);

        void isNoSelected(int i);

    }
}
