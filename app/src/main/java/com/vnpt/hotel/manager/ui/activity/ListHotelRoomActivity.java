package com.vnpt.hotel.manager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.app.BaseActivity;
import com.vnpt.hotel.manager.domain.model.MessageEvent;
import com.vnpt.hotel.manager.domain.model.motel.BookRoomModel;
import com.vnpt.hotel.manager.domain.model.motel.MotelModel;
import com.vnpt.hotel.manager.domain.model.roomtype.RoomTypeFindByHotel;
import com.vnpt.hotel.manager.domain.model.roomtype.RoomTypeFindByHotelResult;
import com.vnpt.hotel.manager.domain.repository.AppState;
import com.vnpt.hotel.manager.ui.adapter.BookRoomAdapter;
import com.vnpt.hotel.manager.ui.presenter.roomtype.RoomTypeFindByHotelPresenter;
import com.vnpt.hotel.manager.ui.view.BaseView;
import com.vnpt.hotel.manager.ui.view.roomtype.RoomTypeFindByHotelView;
import com.vnpt.hotel.manager.ui.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class ListHotelRoomActivity extends BaseActivity
    implements BaseView, PopupMenu.OnMenuItemClickListener, RoomTypeFindByHotelView {
  @BindView(R.id.recycler_detail_hotel) RecyclerView recyclerView;
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.menu_add) ImageView menuAddItem;
  @BindView(R.id.menu_more) ImageView menuMoreItem;
  @BindView(R.id.spinner_kind_of_room) Spinner spinKindOfRoom;
  @BindView(R.id.tv_toolbar_title) TextView tvToolbarTitle;

  private List<Object> listBookRoom;
  BookRoomAdapter adapter;
  Context mContext;

  @Inject RoomTypeFindByHotelPresenter roomTypeFindByHotelPresenter;
  @Inject AppState appState;
  List<String> listKindOfRoom = new ArrayList<>();
  MotelModel.Hotels hotels;
  private List<RoomTypeFindByHotelResult.RoomTypes> roomTypes;
  private int pageIndex;
  private int pageSize;

  private void initializeData() {
    listBookRoom = new ArrayList<>();
    listBookRoom.add(new BookRoomModel("Nguyễn Hoàng Việt", "0918666096", "12/03/2018 09:45", "INIT", 2, "Phòng bình dân"));
    listBookRoom.add(new BookRoomModel("Đặng Nhật Minh","0979312873", "12/03/2018 08:40", "CONFIRM", 1, "Phòng VIP"));
    listBookRoom.add(new BookRoomModel("Nguyễn Tất Tiến","0987654321", "12/03/2018 09:30", "CHECKIN", 1, "Phòng giường đôi"));
    listBookRoom.add(new BookRoomModel("Phạm Xuân Thao","01234567890", "08/03/2018 09:00", "CHECKOUT", 2, "Phòng giường đơn"));
    listBookRoom.add(new BookRoomModel("Nguyễn Bá Lợi","0966667889", "12/03/2018 11:50", "CANCEL", 3, "Phòng bình dân"));
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_hotel_room);

    mContext = ListHotelRoomActivity.this;
    initView();
    addControls();
    addEvents();
    roomTypeFindByHotelPresenter.setView(this);
    roomTypeFindByHotelPresenter.onViewCreate();
  }

  @Override protected void onStart() {
    super.onStart();
    if (!EventBus.getDefault().isRegistered(this)) {
      EventBus.getDefault().register(this);
    }
  }

  @Override public void initView() {
    initToolbar();
    initializeData();
    initSpinner();
    initRecycler();
  }

  private void initToolbar() {
    menuAddItem.setVisibility(View.VISIBLE);
    menuMoreItem.setVisibility(View.VISIBLE);
    setSupportActionBar(toolbar);
    // Remove default title text
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    // set icon
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    tvToolbarTitle.setText("Danh sách phòng nghỉ");
  }

  private void initRecycler() {
    GridLayoutManager linearLayout = new GridLayoutManager(this, 2);
    recyclerView.setLayoutManager(linearLayout);
    recyclerView.addItemDecoration(new SpacesItemDecoration(2, dpToPx(5), true));
    adapter = new BookRoomAdapter(this, listBookRoom);
    recyclerView.setAdapter(adapter);
  }

  @Override public void addControls() {
    pageIndex = 0;
    pageSize = 10;
  }

  @Override public void addEvents() {
    menuMoreItem.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        showPopup(menuMoreItem);
      }
    });
    menuAddItem.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        checkDieuKien();
      }
    });
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override protected void onStop() {
    super.onStop();
  }

  private void getRoomTypeByHotel(int hotelId) {
    showProgress();
    RoomTypeFindByHotel roomtype = new RoomTypeFindByHotel();
    roomtype.setHotelId(hotelId);
    roomtype.setPageSize(pageSize);
    roomtype.setPageIndex(pageIndex);
    roomTypeFindByHotelPresenter.roomTypeFindByHotel(roomtype);
  }

  public void initSpinner() {
    listKindOfRoom.add(getString(R.string.kind_of_room_defaut));
    if (roomTypes != null && roomTypes.size() > 0) {
      for (int i = 0; i < roomTypes.size(); i++) {
        listKindOfRoom.add(roomTypes.get(i).getName());
      }
    }
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListHotelRoomActivity.this,
        android.R.layout.simple_spinner_dropdown_item, listKindOfRoom);
    spinKindOfRoom.setAdapter(adapter);

    // Spinner on item click listener
    spinKindOfRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
        // TODO Auto-generated method stub
        if (position == listBookRoom.size() - 1) {
          //Them loai phong

        }
      }

      @Override public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

      }
    });
  }

  @Override public boolean onMenuItemClick(MenuItem menuItem) {
    switch (menuItem.getItemId()) {
      //Thêm phòng
      case R.id.add_room_type:
        gotoCreateRoomType();
        //checkDieuKien();
        break;
      //Thêm loại phòng
      case R.id.edit_hotel_detail:
        gotoCreateEditHotel();
        break;
    }
    return false;
  }

  @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
  public void onMessageEvent(MessageEvent event) {
    hotels = event.getHotelModel();
    if (hotels != null) {
      getRoomTypeByHotel(hotels.getHotelId());
    }
  }

  @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
  public void onMessageHotelId(MessageEvent.SendHotelId event) {
    getRoomTypeByHotel(event.getHotelId());
  }

  private void gotoCreateEditHotel() {
    Intent intent = new Intent(this, CreateAndEditHotelActivity.class);
    startActivity(intent);
  }

  private void checkDieuKien() {
    if (listKindOfRoom != null && listKindOfRoom.size() > 1) {
      gotoCreateRoomHotel();
    } else {
      gotoCreateRoomType();
    }
  }

  private void gotoCreateRoomType() {
    Intent intent = new Intent(this, CreateRoomTypeActivity.class);
    startActivity(intent);
  }

  private void gotoCreateRoomHotel() {
    Intent intent = new Intent(this, CreateRoomHotelActivity.class);
    startActivity(intent);
  }

  public void showPopup(View view) {
    PopupMenu popup = new PopupMenu(ListHotelRoomActivity.this, view);
    popup.getMenuInflater().inflate(R.menu.menu_list_hotel_room, popup.getMenu());
    popup.setOnMenuItemClickListener(this);
    popup.show();
  }

  @Override
  public void onRoomTypeByHotelSuccess(RoomTypeFindByHotelResult roomTypeFindByHotelResult) {
    dismissProgress();
    roomTypes = roomTypeFindByHotelResult.getRoomTypes();
    initSpinner();
  }

  @Override public void onRoomTypeByHotelFailed(String message) {
    dismissProgress();
    showToast(message);
  }

  @Override public void onError(Throwable e) {
    dismissProgress();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
    MessageEvent.SendHotelId stickyEvent = EventBus.getDefault().getStickyEvent(MessageEvent.SendHotelId.class);
    EventBus.getDefault().removeStickyEvent(stickyEvent);
  }
}
