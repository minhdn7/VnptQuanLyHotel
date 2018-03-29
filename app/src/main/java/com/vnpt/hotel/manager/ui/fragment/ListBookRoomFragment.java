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
import com.vnpt.hotel.manager.app.utils.AppDef;
import com.vnpt.hotel.manager.domain.model.motel.BookRoomModel;
import com.vnpt.hotel.manager.domain.model.request.motel.ListBookingRequest;
import com.vnpt.hotel.manager.domain.model.response.motel.ListBookingResponse;
import com.vnpt.hotel.manager.ui.adapter.BookRoomAdapter;
import com.vnpt.hotel.manager.ui.presenter.motel.ListBookingPresenter;
import com.vnpt.hotel.manager.ui.presenter.motel.ListRoomPresenter;
import com.vnpt.hotel.manager.ui.view.motel.ListBookingView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListBookRoomFragment extends BaseFragment implements ListBookingView{
  @BindView(R.id.recycler_view_book_room) RecyclerView recyclerView;
  private List<Object> listBookRoom = new ArrayList<>();
  BookRoomAdapter adapter;
  private Integer pageIndex = 0;
  private Integer pageSize = 10;
  private Integer idHotel = 0;
  @Inject
  ListBookingPresenter listBookingPresenter;
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
//    initializeData();
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initRecyclerView();
    addControls();
  }

  private void addControls() {
    idHotel = getArguments().getInt("HOTEL_ID");
    showProgress();
    listBookingPresenter.setView(this);
    listBookingPresenter.onViewCreate();

    ListBookingRequest request = new ListBookingRequest();
    request.setPage(pageIndex);
    request.setSize(pageSize);
    // get yesterday date
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -1);
    String fromDate = dateFormat.format(cal.getTime());
    request.setFromDate(fromDate);
    // end
    listBookingPresenter.getList(AppDef.TOKEN_USER, request);
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

  @Override
  public void onListBookingSuccess(ListBookingResponse response) {
    dismissProgress();
    showDialog(response.getResponseMessage());
    if (response.getBookingList() != null && response.getBookingList().size() > 0) {
      listBookRoom.addAll(response.getBookingList());
//      tvNoData.setVisibility(View.GONE);
      recyclerView.setVisibility(View.VISIBLE);
      adapter.notifyDataSetChanged();
    }
  }

  @Override
  public void onListBookingFailed(String message) {
    dismissProgress();
    showDialog(message);
  }

  @Override
  public void onListBookingError(Throwable e) {
    dismissProgress();
    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
  }
}
