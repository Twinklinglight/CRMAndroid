package com.wtcrmandroid.main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.aboutdocument.DocumentProcessActivity;
import com.wtcrmandroid.activity.crm.BattlefieldReportActivity;
import com.wtcrmandroid.activity.crm.MainClientLibrary;
import com.wtcrmandroid.activity.crm.MyClientLibrary;
import com.wtcrmandroid.activity.crm.PublicSeaActivity;
import com.wtcrmandroid.activity.field.FieldActivity;
import com.wtcrmandroid.activity.foodpullcustomer.PullintoCustomerActivity;
import com.wtcrmandroid.activity.journalmanager.JournalManagerActivity;
import com.wtcrmandroid.activity.salepullcustomer.SalePullintoCustomerActivity;
import com.wtcrmandroid.view.custompricing.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;

/**
 * Created by 1363655717 on 2017-06-01.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.tv_pullintocustomer)
    TextView mTvPullintocustomer;       //录入客户

    private boolean isSale = true ; //是销售 或 食品

    private boolean window;
    private PopupWindow mPopWindow;

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
//                    activity.setTitleWindow(GONE);
                    mPopWindow.dismiss();
                } else {


                    RotateAnimation operatingAnim = (RotateAnimation) AnimationUtils.loadAnimation(
                            getActivity(), R.anim.rotating45);
                    LinearInterpolator lin = new LinearInterpolator();
                    operatingAnim.setInterpolator(lin);
                    titlebar.getRightImage().startAnimation(operatingAnim);
//                    activity.setTitleWindow(View.VISIBLE);
                    window = true;
                    showPopupWindow();
                }
            }
        });

    }


    @Override
    public void returnData(int key, Object data) {

    }

    @OnClick({R.id.tv_log_management, R.id.tv_field,R.id.rl_battlefield_report,R.id.tv_pullintocustomer,R.id.rl_main_client_library
            ,R.id.rl_my_client_library,R.id.rl_public_sea,R.id.tv_document_process})
    public void onClick(View view) {
        switch (view.getId()) {
            //日志管理点击事件
            case R.id.tv_log_management:
                startActivity(new Intent(getActivity(), JournalManagerActivity.class));
                break;
            //外勤点击事件
            case R.id.tv_field:
                startActivity(new Intent(getActivity(), FieldActivity.class));
                break;

            //战报点击事件
            case R.id.rl_battlefield_report:
//                startActivity(new Intent(getActivity(), FieldActivity.class));
                startActivity(new Intent(getActivity(), BattlefieldReportActivity.class));
                break;
            //录入客户
            case R.id.tv_pullintocustomer:
                if (!isSale){
                    startActivity(new Intent(getContext(), PullintoCustomerActivity.class));
                }else {
                    startActivity(new Intent(getContext(), SalePullintoCustomerActivity.class));
                }
                break;
            //主客户库
            case R.id.rl_main_client_library:
                    startActivity(new Intent(getContext(), MainClientLibrary.class));
                break;
            //主客户库
            case R.id.rl_my_client_library:
                startActivity(new Intent(getContext(), MyClientLibrary.class));
                break;
            //主客户库
            case R.id.rl_public_sea:
                startActivity(new Intent(getContext(), PublicSeaActivity.class));
                break;
            //公文审批
            case R.id.tv_document_process:
//                startActivity(new Intent(getContext(), DocumentProcessActivity.class));
                break;
        }
    }

    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_home, null);
        ViewHolder holder= new ViewHolder(contentView);
        holder.llControlFragmentOrderMetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "dasfasd", Toast.LENGTH_SHORT).show();
            }
        });
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
        mPopWindow.setContentView(contentView);
//        mPopWindow.setFocusable(false); // 设置PopupWindow可获得焦点
//        设置各个控件的点击响应
        mPopWindow.showAsDropDown(titlebar);
//        mPopWindow=new MyPopupWindow(contentView,
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false,titlebar);
//        mPopWindow=new MyPopupWindow(getContext(),titlebar);

    }

    static class ViewHolder {
        @BindView(R.id.ll_control_fragment_write_journal)
        LinearLayout llControlFragmentWriteJournal;
        @BindView(R.id.ll_control_fragment_send_approval)
        LinearLayout llControlFragmentSendApproval;
        @BindView(R.id.ll_control_fragment_send_notice)
        LinearLayout llControlFragmentSendNotice;
        @BindView(R.id.ll_control_fragment_write_customer)
        LinearLayout llControlFragmentWriteCustomer;
        @BindView(R.id.ll_control_fragment_order_metting)
        LinearLayout llControlFragmentOrderMetting;
        @BindView(R.id.ll_control_fragment_product_advice)
        LinearLayout llControlFragmentProductAdvice;
        @BindView(R.id.cancle_onlick)
        LinearLayout cancleOnlick;
        @BindView(R.id.title_window)
        LinearLayout titleWindow;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
