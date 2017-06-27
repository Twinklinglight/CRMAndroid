package com.wtcrmandroid.adapter.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.utils.L;

/**
 * Created by wt-pc on 2017/6/20.
 */

public class FragmentAdapter {
    private BaseFragment[] fragments; // 一个tab页面对应一个Fragment
    private int fragmentContentId; // Activity中所要被替换的区域的id
    private AppCompatActivity fragmentActivity; // Fragment所属的Activity

    private int currentTab=0; // 当前Tab页面索引
    public FragmentAdapter(AppCompatActivity fragmentActivity, BaseFragment[] fragments, int fragmentContentId) {
        this.fragments = fragments;
        this.fragmentActivity = fragmentActivity;
        this.fragmentContentId=fragmentContentId;
        // 默认显示第一页
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        ft.add(fragmentContentId, fragments[0]);
        ft.commit();
    }
    public void Changed(int i){
        if(i==currentTab)
            return;
        L.e("zhixinsda");
        Fragment fragment = fragments[i];
        FragmentTransaction ft = obtainFragmentTransaction(i);

        getCurrentFragment().onPause(); // 暂停当前tab
        ft.hide( getCurrentFragment());
//                getCurrentFragment().onStop(); // 暂停当前tab

        if(fragment.isAdded()){

//                    fragment.onStart(); // 启动目标tab的onStart()
            fragment.onResume(); // 启动目标tab的onResume()
            ft.show(fragment);
        }else{

            ft.add(fragmentContentId, fragment);

        }
        ft.commit();
        currentTab = i; // 更新目标tab为当前tab
//        showTab(i); // 显示目标tab


    }
    /**
     * 切换tab
     * @param idx
     */
    private void showTab(int idx){
        FragmentTransaction ft = obtainFragmentTransaction(idx);
        for(int i = 0; i < fragments.length; i++){

            Fragment fragment = fragments[i];
            if(idx == i){
                ft.show(fragment);
            }else{
                ft.hide(fragment);
            }
            ft.commit();
        }
        currentTab = idx; // 更新目标tab为当前tab
    }
    public Fragment getCurrentFragment(){
        return fragments[currentTab];
    }
    /**
     * 获取一个带动画的FragmentTransaction
     * @param index
     * @return
     */
    private FragmentTransaction obtainFragmentTransaction(int index){
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        // 设置切换动画
        if(index > currentTab){
            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
        }else{
            ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
        }
        return ft;
    }
}
