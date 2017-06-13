package com.wtcrmandroid.fragment.main;

import android.content.Intent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.JournalManagerActivity;
import com.wtcrmandroid.activity.MainActivity;
import com.wtcrmandroid.custompricing.TitleBar;
import com.wtcrmandroid.fragment.BaseFragmengt;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.View.GONE;

/**
 * Created by 1363655717 on 2017-06-01.
 */

public class HomeFragment extends BaseFragmengt {
    @BindView(R.id.titlebar)
    TitleBar titlebar;


    private boolean window;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        titlebar.serLeftImageVisibility(GONE);
        titlebar.setLeftText("工作台");
        titlebar.setRightImageResource(R.mipmap.ico_plus);
        titlebar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                if (window) {

                    RotateAnimation operatingAnim = (RotateAnimation) AnimationUtils.loadAnimation(
                            getActivity(), R.anim.rotating_45);
                    LinearInterpolator lin = new LinearInterpolator();
                    operatingAnim.setInterpolator(lin);
                    titlebar.getRightImage().startAnimation(operatingAnim);
                    window = false;
                    activity.setTitleWindow(GONE);
                } else {


                    RotateAnimation operatingAnim = (RotateAnimation) AnimationUtils.loadAnimation(
                            getActivity(), R.anim.rotating45);
                    LinearInterpolator lin = new LinearInterpolator();
                    operatingAnim.setInterpolator(lin);
                    titlebar.getRightImage().startAnimation(operatingAnim);
                    activity.setTitleWindow(View.VISIBLE);
                    window = true;
                }
            }
        });


    }

    @OnClick(R.id.tv_log_management)
    public void onClick() {
        startActivity(new Intent(getActivity(), JournalManagerActivity.class));
    }



    @Override
    public void returnData(int key, Object data) {

    }
}
