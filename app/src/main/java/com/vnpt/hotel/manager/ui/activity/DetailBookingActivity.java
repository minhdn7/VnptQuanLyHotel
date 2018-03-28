package com.vnpt.hotel.manager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.domain.model.bookingdetail.BookingDetailModel;
import com.vnpt.hotel.manager.ui.adapter.RoomDetailBookingAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailBookingActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_room)
    RecyclerView recyclerView;
    @BindView(R.id.btnCheckOut)
    Button btnCheckOut;
    private RoomDetailBookingAdapter adapter;
    private List<Object> listBookingDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_booking);
        ButterKnife.bind(this);
        initData();
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        adapter = new RoomDetailBookingAdapter(listBookingDetails, this);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        listBookingDetails = new ArrayList<>();
        listBookingDetails.add(new BookingDetailModel.BookingDetail(1, "101", "12-03-2018 09:55"));
        listBookingDetails.add(new BookingDetailModel.BookingDetail(2, "102", "12-03-2018 08:55"));
        listBookingDetails.add(new BookingDetailModel.BookingDetail(3, "201", "12-03-2018 12:55"));
    }

    @OnClick(R.id.btnCheckOut)
    public void onViewClicked() {
        startActivity(new Intent(this, InvoiceActivity.class));
    }
}
