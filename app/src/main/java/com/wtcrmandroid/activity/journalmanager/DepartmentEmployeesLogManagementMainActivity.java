package com.wtcrmandroid.activity.journalmanager;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wtcrmandroid.R;
import com.wtcrmandroid.BaseActivity;
import com.wtcrmandroid.adapter.listview.DepartmentJournalAdapter;
import com.wtcrmandroid.view.custompricing.TitleBar;
import com.wtcrmandroid.model.DepartmentData;
import com.wtcrmandroid.utils.L;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 1363655717 on 2017-06-07.
 * 部门员工日志管理首页
 */

public class DepartmentEmployeesLogManagementMainActivity extends BaseActivity {
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.lv_department)
    ListView listDepart;

    List<DepartmentData> mDataList;
    private DepartmentJournalAdapter mAdapter;

    @Override
    protected int layout() {
        return R.layout.activity_department_employees_log_management_main;
    }

    @Override
    protected void initView() {
        titlebar.setTitletext("部门员工日志");
        titlebar.setRightImageResource(R.mipmap.ic_search);
        titlebar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titlebar.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                // 按照键值排序
//                ArrayComparator comparator = new myArrayComparator();
//                Map sortMap= new TreeMap(comparator);
//                sortMap.put("中山", "a");
//                sortMap.put("广州", "b");
//                sortMap.put("潮州", "c");
//                //注意：每次对TreeMap进行put()时，TreeMap都会自动调用它的compare(key,Entry.key)
//                //按照key进行排序
//                Collection col = sortMap.keySet();
//                Iterator it = col.iterator();
//                while (it.hasNext()) {
//                    System.out.println(it.next());
//                }
                Comparator comp = Collator.getInstance();
                String[] newArray={"中山","汕头","广州","安庆","sadf","阳江","南京","武汉","北京","安阳","北方","yf","as"};
                Arrays.sort(newArray,comp);
                for(String i:newArray){
                    L.e(i);
                }
                startActivity(new Intent(DepartmentEmployeesLogManagementMainActivity.this,DepartmentEmployeesLogManagementSerachActivity.class));
            }
        });

        mDataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DepartmentData departmentData = new DepartmentData();
            departmentData.setPersonName("name"+i);
            departmentData.setCommentCount("count"+i);
            mDataList.add(departmentData);
        }

        mAdapter = new DepartmentJournalAdapter(this,mDataList);
        listDepart.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        listDepart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    startActivity(new Intent(DepartmentEmployeesLogManagementMainActivity.this,
                            HtJournalDetails.class));
                }else if (position == 1){
                    startActivity(new Intent(DepartmentEmployeesLogManagementMainActivity.this,
                            XsJournalDetails.class));
                }
            }
        });

    }

    @Override
    public void returnData(int key, Object data) {

    }
}
