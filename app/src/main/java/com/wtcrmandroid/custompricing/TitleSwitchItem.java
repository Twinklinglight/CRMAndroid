package com.wtcrmandroid.custompricing;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/6/23.
 * 标题切换item
 */

public class TitleSwitchItem extends RelativeLayout {
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.v_line)
    View vLine;

    public TitleSwitchItem(Context context) {
        super(context);
        init(context);
    }

    public TitleSwitchItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleSwitchItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleSwitchItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.widget_title_switch_item, this);
        ButterKnife.bind(view);
    }
    public void changeState(boolean checkstate){
        if(checkstate){
            tvText.setTextColor(Color.alpha(R.color.colorPrimary));
        }else {
            tvText.setTextColor(Color.alpha(R.color.colorIconText));
            vLine.setVisibility(INVISIBLE);
        }

    }

}
