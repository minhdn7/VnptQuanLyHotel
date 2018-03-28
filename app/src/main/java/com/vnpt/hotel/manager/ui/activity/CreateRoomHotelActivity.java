package com.vnpt.hotel.manager.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.app.BaseActivity;
import com.vnpt.hotel.manager.ui.adapter.CreateRoomAdapter;
import com.vnpt.hotel.manager.ui.view.BaseView;

import java.util.ArrayList;
import java.util.List;

public class CreateRoomHotelActivity extends BaseActivity
    implements BaseView{
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.tv_toolbar_title) TextView tvToolbarTitle;
  @BindView(R.id.spinner_kind_of_room) Spinner spinnerKindOfRoom;
  @BindView(R.id.recycler_list_name_create_room) RecyclerView recyclerListNameCreateRoom;
  @BindView(R.id.btn_create_room) TextView btnCreateRoom;
  List<String> listRoom = new ArrayList<>();
  CreateRoomAdapter createRoomAdapter;
  List<String> listKindOfRoom = new ArrayList<>();


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_room_hotel);
    ButterKnife.bind(this);
    initView();
    addControls();
    addEvents();

  }

  @Override public void initView() {
    initToolbar();
    initRecycleView();
    //initSpinner();
  }

  @Override public void addControls() {

  }

  @Override public void addEvents() {

  }



  @OnClick(R.id.btn_create_room) void btnCreatRoom() {

    showProgress(getString(R.string.str_dang_tai_du_lieu));
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
      @Override public void run() {
        dismissProgress();
        showCallbacks();
      }
    }, 3000L);
  }





  private void initToolbar() {
    setSupportActionBar(toolbar);
    // Remove default title text
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    // set icon
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    tvToolbarTitle.setText("Tạo phòng nhà nghỉ");
  }

  private void initRecycleView() {
    listRoom.add("Empty");
    createRoomAdapter = new CreateRoomAdapter(this, listRoom);
    recyclerListNameCreateRoom.setLayoutManager(new GridLayoutManager(this, 3));
    recyclerListNameCreateRoom.setHasFixedSize(true);
    recyclerListNameCreateRoom.setAdapter(createRoomAdapter);
    createRoomAdapter.setCreateRoomListener(new CreateRoomAdapter.CreateRoomListener() {
      @Override public void addNewRoom() {
        showPopUpAddCreateRoom();
      }
    });
  }

  public void initSpinner() {
    listKindOfRoom.add(getResources().getString(R.string.kind_of_room_defaut));
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateRoomHotelActivity.this,
        android.R.layout.simple_spinner_dropdown_item, listKindOfRoom);
    spinnerKindOfRoom.setAdapter(adapter);

    // Spinner on item click listener
    spinnerKindOfRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
        // TODO Auto-generated method stub
      }

      @Override public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

      }
    });
  }

  private void showPopUpAddCreateRoom() {
    new MaterialDialog.Builder(this).title("Tạo phòng nghỉ")
        .content("Nhập tên phòng nghỉ : ")
        .inputType(InputType.TYPE_CLASS_TEXT
            | InputType.TYPE_TEXT_VARIATION_PERSON_NAME
            | InputType.TYPE_TEXT_FLAG_CAP_WORDS)
        .positiveText("Oke")
        .input("Ví dụ : P.101, P.102, ... ", "", false, new MaterialDialog.InputCallback() {
          @Override public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            listRoom.add(listRoom.size() - 1, input.toString());
            createRoomAdapter.notifyDataSetChanged();
          }
        })
        .show();
  }

  public void showCallbacks() {
    new MaterialDialog.Builder(this).title("Thông báo")
        .content(R.string.str_tao__phong, true)
        .positiveText("Oke")
        .onPositive(new MaterialDialog.SingleButtonCallback() {
          @Override public void onClick(MaterialDialog dialog, DialogAction which) {
            // TODO
            //Intent intent = new Intent(CreateRoomHotelActivity.this, ListHotelRoomActivity.class);
            //startActivity(intent);
            finish();
          }
        })
        .show();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == android.R.id.home) {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }


}
