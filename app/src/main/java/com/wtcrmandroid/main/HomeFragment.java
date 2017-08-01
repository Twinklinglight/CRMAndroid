package com.wtcrmandroid.main;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.wtcrmandroid.base.BaseFragment;
import com.wtcrmandroid.R;
import com.wtcrmandroid.adapter.recycleview.HomeAdapter;
import com.wtcrmandroid.model.data.HomeItemD;
import com.wtcrmandroid.view.custompricing.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wtcrmandroid.utils.L.e;


/**
 * Created by 1363655717 on 2017-06-01.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.titlebar)
    TitleBar titlebar;

    @BindView(R.id.rv_view)
    RecyclerView rvView;

    private boolean window;
    private PopupWindow mPopWindow;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_home;
    }


    private int[] oa_url;
    private String[] oa_name;
    private int[] oa_click;
    private int[] crm_url;
    private String[] crm_name;
    private int[] crm_click;
    private int[] other_url;
    private String[] other_name;
    private int[] other_click;
    private List<HomeItemD> list = new ArrayList<>();
    private HomeAdapter crm_adapter;

    @Override
    public void init() {

        list.add(new HomeItemD(0, "OA办公", 0));
        oa_url = new int[]{R.mipmap.ic_home_log_management, R.mipmap.ic_home_notice_announcement, R.mipmap.ic_home_document_processing,
                R.mipmap.ic_home_field, R.mipmap.ic_home_meeting_room_reservation, R.mipmap.ic_home_employees_management};
        oa_name = new String[]{"日志管理", "通知公告", "公文审批", "外勤", "会议室预约", "员工管理"};
        oa_click = new int[]{1, 2, 3, 4, 5, 6};
        for (int i = 0; i < oa_url.length; i++) {
            list.add(new HomeItemD(oa_url[i], oa_name[i], oa_click[i]));
        }
        list.add(new HomeItemD(0, "CRM营销", 7));
        crm_url = new int[]{R.mipmap.ic_home_war_news, R.mipmap.ic_home_scan, R.mipmap.ic_home_enter_customer, R.mipmap.ic_home_main_customer_library
                , R.mipmap.ic_home_my_customer_library, R.mipmap.ic_home_continue_to_single_high_seas, R.mipmap.ic_home_continue_to_single_high_seas};
        crm_name = new String[]{"战报", "扫一扫", "录入客户", "主客户库", "我的客户库", "续单公海", "我的地推客户"};
        crm_click = new int[]{8, 9, 10, 11, 12, 13, 14};
        for (int i = 0; i < crm_url.length; i++) {
            list.add(new HomeItemD(crm_url[i], crm_name[i], crm_click[i]));
        }
        list.add(new HomeItemD(0, "其他", 15));
        other_url = new int[]{R.mipmap.ic_home_product_advice, R.mipmap.ic_home_article_secret};
        other_name = new String[]{"产品建议", "密条"};
        other_click = new int[]{16, 17};
        for (int i = 0; i < other_url.length; i++) {
            list.add(new HomeItemD(other_url[i], other_name[i], other_click[i]));
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        e(list.size()+"==="+list.toString());
        rvView.setLayoutManager(gridLayoutManager);
        rvView.setAdapter(crm_adapter = new HomeAdapter(getActivity(), list));
        titlebar.serLeftImageVisibility(View.GONE);
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



    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_home, null);
        ViewHolder holder = new ViewHolder(contentView);
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
