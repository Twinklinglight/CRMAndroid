package com.wtcrmandroid.custompricing;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wtcrmandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 标题头view
 *
 * @author Administrator
 */
public class TitleBar extends RelativeLayout {
    @BindView(R.id.left_image)
    ImageView leftImage;
    @BindView(R.id.left_text)
    TextView leftText;
    @BindView(R.id.left_layout)
    RelativeLayout leftLayout;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.right_text)
    TextView rightText;
    @BindView(R.id.right_image)
    ImageView rightImage;
    @BindView(R.id.right_layout)
    RelativeLayout rightLayout;
    private Activity activity;




    public TitleBar(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public TitleBar(Context context) {
        super(context);
        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view=LayoutInflater.from(context).inflate(R.layout.widget_title_bar, this);
        ButterKnife.bind(view);




    }


    /**
     * 设置标题
     *
     * @param text
     */
    public void setTitletext(String text) {
        titleText.setText(text);
    }

    /**
     * 设置返回图标得显示情况
     * @param i
     */
    public void serLeftImageVisibility(int i){
        leftImage.setVisibility(i);
    }

    /**
     *
     * @param text
     */
    public void setLeftText(String text){
        leftText.setText(text);
    }

    public void setRightImageResource(int i){
        rightImage.setImageResource(i);
    }
    public void setLeftOnClickListener(OnClickListener onClickListener){
        leftLayout.setOnClickListener(onClickListener);
    }
    public void setRightOnClickListener(OnClickListener onClickListener){
        rightLayout.setOnClickListener(onClickListener);
    }

    public ImageView getRightImage() {
        return rightImage;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setleft( Activity activit,final int type){
        this.activity = activit;
        leftLayout.setVisibility(VISIBLE);
        leftLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (type) {
                    // 昵称编辑
                    case 0:
                        activity.finish();

                        break;
                    // 昵称编辑
                    case 1:
                        ((FragmentActivity) activity).getSupportFragmentManager().popBackStackImmediate();
                        break;
                    // 地址编辑

                    default:
                        break;
                }

            }
        });
    }

    /**
     * 设置右边按钮文字
     *
     * @param text
     */
    public void setrighttext(String text) {
        rightText.setVisibility(View.VISIBLE);
        rightText.setText(text);
    }
//
//    /**
//     * 设置右边按钮图标
//     *
//     * @param resId
//     */
//    public void setrightImg(int resId) {
//        rightImage.setVisibility(View.VISIBLE);
//        rightImage.setImageResource(resId);
//    }
//
//    /**
//     * 设置左边边按钮文�?
//     *
//     * @param text
//     */
//    public void setlefttext(String text) {
//
//        leftText.setText(text);
//    }
//
    /**
     * 设置右边按钮点击事件
     *
     * @param click
     */
    public void setrightLayoutClick(OnClickListener click) {
        rightLayout.setOnClickListener(click);
    }

    /**
     * 获取右边textview控件
     *
     * @return
     */

    public TextView getRightText(){
        return rightText;
    }
//
//    /**
//     * 设置左边按钮点击事件类型
//     */
//    public void setleftLayoutClickintint(int i) {
//        this.i = i;
//    }
//
//    public void setleftLayoutClickintgon() {
//        leftLayout.setVisibility(View.GONE);
//    }
//
//    public void setActivity(Activity activity) {
//        this.activity = activity;
//    }

}
