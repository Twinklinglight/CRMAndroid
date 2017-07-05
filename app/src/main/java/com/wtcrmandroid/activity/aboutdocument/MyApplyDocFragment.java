package com.wtcrmandroid.activity.aboutdocument;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wtcrmandroid.BaseFragment;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.aboutdocument.presenter.MyApplyDocPresenter;
import com.wtcrmandroid.adapter.recycleview.DocumentDealAdapter;
import com.wtcrmandroid.model.reponsedata.DocumentListRpData;
import com.wtcrmandroid.model.requestdata.MyApplyDocRqData;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zxd on 2017/7/5.
 */

public class MyApplyDocFragment extends BaseFragment<MyApplyDocPresenter, List<DocumentListRpData>> {


    @BindView(R.id.rcy_document_fragment)
    RecyclerView rcyDocumentFragment;
    private DocumentDealAdapter documentDealAdapter;

    @Override
    protected int Rlayout() {
        return R.layout.fragment_document_waitmedeal;
    }

    @Override
    protected void init() {
        presenter = new MyApplyDocPresenter(this);
        MyApplyDocRqData myApplyDocRqData = new MyApplyDocRqData();
        myApplyDocRqData.setUserId(1066);
        myApplyDocRqData.setPageSize(1);
        myApplyDocRqData.setPageCount(6);
        presenter.postMyApply(myApplyDocRqData);

    }

    @Override
    public void returnData(int key, List<DocumentListRpData> data) {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rcyDocumentFragment.setLayoutManager(llm);
        documentDealAdapter = new DocumentDealAdapter(getContext(),data);
        rcyDocumentFragment.setAdapter(documentDealAdapter);

    }
}
