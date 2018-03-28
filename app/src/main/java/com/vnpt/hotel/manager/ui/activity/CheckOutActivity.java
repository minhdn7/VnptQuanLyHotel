package com.vnpt.hotel.manager.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageView;

import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.common.Constants;
import com.vnpt.hotel.manager.domain.model.room.RoomModel;
import com.vnpt.hotel.manager.ui.adapter.RoomCheckOutAdapter;
import com.vnpt.hotel.manager.ui.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckOutActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_hotel)
    ImageView imgHotel;
    @BindView(R.id.recycler_view_room)
    RecyclerView recyclerView;
    @BindView(R.id.btnCheckOut)
    Button btnCheckOut;
    private List<Object> listRoom;
    private RoomCheckOutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initData();
        GridLayoutManager linearLayout = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.addItemDecoration(new SpacesItemDecoration(2, dpToPx(5), true));
        adapter = new RoomCheckOutAdapter(listRoom, this);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        listRoom = new ArrayList<>();
        listRoom.add(new RoomModel.Room(1, "Phòng VIP", "101", "", Constants.ROOM_STATUS.EMPTY));
        listRoom.add(new RoomModel.Room(2, "Phòng bình dân", "102", "", Constants.ROOM_STATUS.CLEANING));
        listRoom.add(new RoomModel.Room(3, "Phòng giường đôi", "201", "", Constants.ROOM_STATUS.REPAIRING));
        listRoom.add(new RoomModel.Room(4, "Phòng giường đôi", "201", "", Constants.ROOM_STATUS.REPAIRING));
        listRoom.add(new RoomModel.Room(5, "Phòng giường đôi", "201", "", Constants.ROOM_STATUS.REPAIRING));
    }

    public int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @OnClick(R.id.btnCheckOut)
    public void onViewClicked() {
        startActivity(new Intent(this, DetailBookingActivity.class));
    }
}
