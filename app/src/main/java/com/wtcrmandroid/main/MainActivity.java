package com.wtcrmandroid.main;

import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.adapter.fragment.FragmentTabAdapter;
import com.wtcrmandroid.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 1363655717 on 2017-05-31.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.rg_bottom)
    RadioGroup rgBottom;
    public List<BaseFragment> fragments = new ArrayList<BaseFragment>();
    @BindView(R.id.title_window)
    LinearLayout titleWindow;

    //记录用户首次点击返回键的时间
    private long firstTime=0;


    @Override
    protected int layout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        fragments.add(new HomeFragment());
        fragments.add(new DiscoveryFragment());
        fragments.add(new ContactsFragment());
        fragments.add(new MineFragment());
        FragmentTabAdapter tabAdapter = new FragmentTabAdapter(this, fragments, R.id.fl_main, rgBottom);
    }


    /**
     * 首页窗口的显示与隐藏控制方法
     *
     * @param i
     */
    public void setTitleWindow(int i) {
        titleWindow.setVisibility(i);
    }

    @OnClick(R.id.cancle_onlick)
    public void onClick() {
//        new SelectionJobCategoriesDialog(this, R.style.Dialog).show();
//        titleWindow.setVisibility(View.GONE);
    }

    @Override
    public void returnData(int key, Object data) {

    }

    //双击退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis()-firstTime>2000){
                Toast.makeText(MainActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                firstTime=System.currentTimeMillis();
            }else{
                this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
