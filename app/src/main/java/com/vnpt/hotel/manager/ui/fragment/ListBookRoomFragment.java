package com.vnpt.hotel.manager.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.app.BaseFragment;
import com.vnpt.hotel.manager.domain.model.motel.BookRoomModel;
import com.vnpt.hotel.manager.ui.adapter.BookRoomAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListBookRoomFragment extends BaseFragment {
  @BindView(R.id.recycler_view_book_room) RecyclerView recyclerView;
  private List<Object> listBookRoom;
  BookRoomAdapter adapter;

  private void initializeData() {
    listBookRoom = new ArrayList<>();
    listBookRoom.add(new BookRoomModel("Nguyễn Hoàng Việt", "0918666096", "12/03/2018 09:45", "INIT", 2, "Phòng bình dân"));
    listBookRoom.add(new BookRoomModel("Đặng Nhật Minh","0979312873", "12/03/2018 08:40", "CONFIRM", 1, "Phòng VIP"));
    listBookRoom.add(new BookRoomModel("Nguyễn Tất Tiến","0987654321", "12/03/2018 09:30", "CHECKIN", 1, "Phòng giường đôi"));
    listBookRoom.add(new BookRoomModel("Phạm Xuân Thao","01234567890", "08/03/2018 09:00", "CHECKOUT", 2, "Phòng giường đơn"));
    listBookRoom.add(new BookRoomModel("Nguyễn Bá Lợi","0966667889", "12/03/2018 11:50", "CANCEL", 3, "Phòng bình dân"));
  }

  public ListBookRoomFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    initializeData();
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initRecyclerView();
  }

  private void initRecyclerView() {
    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
    adapter = new BookRoomAdapter(getContext(), listBookRoom);
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
    adapter.notifyDataSetChanged();
  }

  @Override protected int getFragmentLayout() {
    return R.layout.fragment_list_book_room;
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_add_item, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_add:
        Toast.makeText(getActivity(), "ListBookRoom Fragment menu", Toast.LENGTH_LONG).show();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
  private void addNewHotel() {
    listBookRoom.add(new BookRoomModel("Nguyễn Hoàng Việt", "0918666096", "12/03/2018 09:45", "INIT", 2, "Phòng bình dân"));
    listBookRoom.add(new BookRoomModel("Đặng Nhật Minh","0979312873", "12/03/2018 08:40", "CONFIRM", 1, "Phòng VIP"));
    listBookRoom.add(new BookRoomModel("Nguyễn Tất Tiến","0987654321", "12/03/2018 09:30", "CHECKIN", 1, "Phòng giường đôi"));
    listBookRoom.add(new BookRoomModel("Phạm Xuân Thao","01234567890", "08/03/2018 09:00", "CHECKOUT", 2, "Phòng giường đơn"));
    listBookRoom.add(new BookRoomModel("Nguyễn Bá Lợi","0966667889", "12/03/2018 11:50", "CANCEL", 3, "Phòng bình dân"));
    adapter.notifyItemInserted(listBookRoom.size() - 1);
  }
}
