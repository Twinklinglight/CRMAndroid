package com.wtcrmandroid.activity.aboutdocument;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wtcrmandroid.base.BaseFragment;
import com.wtcrmandroid.R;
import com.wtcrmandroid.activity.aboutdocument.presenter.WaitMeDealPresenter;
import com.wtcrmandroid.adapter.recycleview.DocumentDealAdapter;
import com.wtcrmandroid.model.reponsedata.DocumentListRpData;
import com.wtcrmandroid.model.requestdata.WaitMedealRqData;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zxd on 2017/7/5.
 */

public class WaitMedealFragment extends BaseFragment<WaitMeDealPresenter,List<DocumentListRpData>> {

    @BindView(R.id.rcy_document_fragment)
    RecyclerView rcyDocumentFragment;
    private DocumentDealAdapter documentDealAdapter;

    @Override
    public void returnData(int key, List<DocumentListRpData> data) {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rcyDocumentFragment.setLayoutManager(llm);
        documentDealAdapter = new DocumentDealAdapter(getContext(),data);
        rcyDocumentFragment.setAdapter(documentDealAdapter);

    }

    @Override
    protected int Rlayout() {
        return R.layout.fragment_document_waitmedeal;
    }

    @Override
    public void init() {
        presenter = new WaitMeDealPresenter(this,getActivity());
        WaitMedealRqData waitMedealRqData = new WaitMedealRqData();
        waitMedealRqData.setUserId(1667);
        waitMedealRqData.setPageSize(1);
        presenter.PostRequest(waitMedealRqData);

    }
}
