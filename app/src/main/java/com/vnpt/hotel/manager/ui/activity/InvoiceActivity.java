package com.vnpt.hotel.manager.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.domain.model.invoice.InvoiceItemModel;
import com.vnpt.hotel.manager.domain.model.invoice.InvoiceModel;
import com.vnpt.hotel.manager.ui.adapter.InvoiceAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InvoiceActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_TitleInvoiceInfo)
    TextView tv_TitleInvoiceInfo;
    @BindView(R.id.recycler_view_room)
    RecyclerView recyclerView;
    @BindView(R.id.tv_EditInvoiceInfo)
    TextView tvEditInvoiceInfo;
    @BindView(R.id.layoutInvoiceInfo)
    LinearLayout layoutInvoiceInfo;
    private List<Object> listInvoice;
    private InvoiceAdapter adapter;
    private boolean expTitleInvoiceInfo = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        ButterKnife.bind(this);
        initData();
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        adapter = new InvoiceAdapter(listInvoice, this);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        List<Object> listItem = new ArrayList<>();
        listItem.add(new InvoiceItemModel("C2", "2", "20 000", "40 000"));
        listItem.add(new InvoiceItemModel("Lavie", "3", "15 000", "45 000"));
        listInvoice = new ArrayList<>();
        listInvoice.add(new InvoiceModel.Invoice(1, "101", "1 545 000", listItem, "12-03-2018 09:45", "12-03-2018 15:55", "1 000 000", "545 000"));
        listInvoice.add(new InvoiceModel.Invoice(2, "102", "1 545 000", listItem, "12-03-2018 08:45", "12-03-2018 12:45", "1 000 000", "545 000"));
        listInvoice.add(new InvoiceModel.Invoice(3, "103", "1 545 000", listItem, "12-03-2018 10:45", "12-03-2018 18:20", "1 000 000", "545 000"));
    }


    @OnClick({R.id.tv_EditInvoiceInfo, R.id.tv_TitleInvoiceInfo})
    public void onViewClicked(android.view.View view) {
        switch (view.getId()) {
            case R.id.tv_TitleInvoiceInfo:
                if (expTitleInvoiceInfo) {
                    layoutInvoiceInfo.setVisibility(View.GONE);
                    expTitleInvoiceInfo = false;
                } else {
                    layoutInvoiceInfo.setVisibility(View.VISIBLE);
                    expTitleInvoiceInfo = true;
                }
                break;
        }
    }
}
