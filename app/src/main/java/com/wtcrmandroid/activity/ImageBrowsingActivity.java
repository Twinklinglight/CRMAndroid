package com.wtcrmandroid.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.wtcrmandroid.R;
import com.wtcrmandroid.view.dragzoomimageview.DragImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wt-pc on 2017/7/20.
 */

public class ImageBrowsingActivity extends Activity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.vp_guide)
    ViewPager vpGuide;
    // 点的组
    @BindView(R.id.ll_guide_point_group)
    LinearLayout llGuidePointGroup;
    // 选中的点view对象
    @BindView(R.id.select_point)
    View selectPoint;
    // ViewPager的数据
    private List<ImageView> imageViewList;
    // 点之间的宽度
    private int pWidth=20;
    private int window_width, window_height;// 控件宽度
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去标题, 需要在setContentView方法之前调用
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image_browsing);
        ButterKnife.bind(this);
        initView();// 初始化控件
        /** 获取可見区域高度 **/
        WindowManager manager = getWindowManager();
        window_width = manager.getDefaultDisplay().getWidth();
        window_height = manager.getDefaultDisplay().getHeight();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        initData();// 初始化ViewPager数据
        GuideAdapter adapter = new GuideAdapter();
        vpGuide.setAdapter(adapter);
        vpGuide.addOnPageChangeListener(this);/*setOnPageChangeListener(this);*/// 设置监听器
        int position = getIntent().getIntExtra("position", 0);
        vpGuide.setCurrentItem(position);


    }

    /**
     * TODO：初始化ViewPager数据 void
     */
    private void initData() {
        List<String> path = getIntent().getStringArrayListExtra("path");
        imageViewList = new ArrayList<>();


        View view;// 点
        LayoutParams params; // 参数类

        for (int i = 0; i < path.size(); i++) {
            final DragImageView finalIv  = new DragImageView(this);
            Glide.with(this).load(path.get(i)).into(finalIv );
            /** 测量状态栏高度 **/
            ViewTreeObserver viewTreeObserver = finalIv .getViewTreeObserver();

            viewTreeObserver
                    .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                        @Override
                        public void onGlobalLayout() {

                                // 获取状况栏高度
                                Rect frame = new Rect();
                                getWindow().getDecorView()
                                        .getWindowVisibleDisplayFrame(frame);

                                finalIv.setScreen_H(window_height-frame.top);
                                finalIv.setScreen_W(window_width);


                        }
                    });
            imageViewList.add(finalIv );
            // 根据图片的个数, 每循环一次向LinearLayout中添加一个点
            view = new View(this);
            view.setBackgroundResource(R.drawable.point_normal);
            // 设置参数
            params = new LayoutParams(10, 10);
            if (i != 0) {
                params.leftMargin = 10;
            }
            view.setLayoutParams(params);// 添加参数
            llGuidePointGroup.addView(view);
        }

    }


    /**
     * 当页面正在滚动时 position 当前选中的是哪个页面 positionOffset 比例 positionOffsetPixels 偏移像素
     */
    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {

        Log.e("zejian", "positionOffset:-->" + positionOffset);
        Log.e("zejian", "position:-->" + position);
        //获取两个点间的距离,获取一次即可
        if (pWidth == 0 && imageViewList.size() > 1) {
            pWidth = llGuidePointGroup.getChildAt(1).getLeft()
                    - llGuidePointGroup.getChildAt(0).getLeft();
            Log.e("zejian", "pWidth:-->" + pWidth);
        }

        // 获取点要移动的距离
        int leftMargin = (int) (pWidth * (position + positionOffset));
        // 给红点设置参数
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) selectPoint
                .getLayoutParams();
        params.leftMargin = leftMargin;
        Log.e("zejian", "leftMargin:-->" + leftMargin);
        selectPoint.setLayoutParams(params);
    }

    /**
     * 当页面被选中
     */
    @Override
    public void onPageSelected(int position) {

    }

    /**
     * 当页面滚动状态改变
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /*
         * 删除元素
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = imageViewList.get(position);
            container.addView(iv);// 1. 向ViewPager中添加一个view对象
            return iv; // 2. 返回当前添加的view对象
        }
    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
       finish();
        //super.onBackPressed();
    }
}