package com.vnpt.hotel.manager.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.app.BaseFragment;
import com.vnpt.hotel.manager.app.utils.AppDef;
import com.vnpt.hotel.manager.domain.model.MessageEvent;
import com.vnpt.hotel.manager.domain.model.motel.MotelModel;
import com.vnpt.hotel.manager.domain.model.request.motel.ListMotelOverviewRequest;
import com.vnpt.hotel.manager.domain.model.response.motel.ListMotelOverViewResponse;
import com.vnpt.hotel.manager.domain.repository.AppState;
import com.vnpt.hotel.manager.ui.activity.CreateAndEditHotelActivity;
import com.vnpt.hotel.manager.ui.activity.CreateCustomerHotelActivity;
import com.vnpt.hotel.manager.ui.activity.ListHotelRoomActivity;
import com.vnpt.hotel.manager.ui.adapter.HotelAdapter;
import com.vnpt.hotel.manager.ui.presenter.motel.ListMotelOverviewPresenter;
import com.vnpt.hotel.manager.ui.presenter.motel.ListMotelOverviewPresenterImpl;
import com.vnpt.hotel.manager.ui.presenter.motel.MotelListPresenter;
import com.vnpt.hotel.manager.ui.view.motel.ListMotelOverviewView;
import com.vnpt.hotel.manager.ui.view.motel.ListMotelView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelFragment extends BaseFragment implements ListMotelView, ListMotelOverviewView {
  @BindView(R.id.recycler_view_hotel) RecyclerView recyclerView;
  @BindView(R.id.tv_no_data) TextView tvNoData;
  @Inject MotelListPresenter motelListPresenter;
  @Inject AppState appState;
  private List<Object> listHotel;
  HotelAdapter adapter;
  private int  userId;
  private Integer pageIndex = 0;
  private Integer pageSize = 10;

  @Inject ListMotelOverviewPresenter listMotelOverviewPresenter;
  private void initializeData() {
    listHotel = new ArrayList<>();
    pageIndex = 0;
    pageSize = 10;
    userId = appState.getUserId();
    //listHotel.add(new MotelModel("Nhà nghỉ lung linh", "123 Nguyễn Phong Sắc"));
    //listHotel.add(new MotelModel("Nhà nghỉ lung linh", "123 Nguyễn Phong Sắc"));
    //listHotel.add(new MotelModel("Nhà nghỉ lung linh", "123 Nguyễn Phong Sắc"));
  }

  public HotelFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    initializeData();
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    EventBus.getDefault().register(this);
    initRecyclerView();
//    motelListPresenter.setView(this);
//    motelListPresenter.onViewCreate();
//    motelListPresenter.getListMotel(userId, pageIndex, pageSize);
    showProgress();
    listMotelOverviewPresenter.setView(this);
    listMotelOverviewPresenter.onViewCreate();
    ListMotelOverviewRequest request = new ListMotelOverviewRequest(pageIndex, pageSize, AppDef.USER_ID);
    listMotelOverviewPresenter.getList(AppDef.TOKEN_USER, request);
  }

  private void initRecyclerView() {
    tvNoData.setVisibility(View.VISIBLE);
    recyclerView.setVisibility(View.GONE);
    LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(linearLayout);
    adapter = new HotelAdapter(listHotel, getActivity());
    recyclerView.setAdapter(adapter);
//    recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView,
//        new RecyclerItemClickListener.OnItemClickListener() {
//          @Override public void onItemClick(View view, int position) {
//            // do whatever
//            MessageEvent.SendHotelId hotelId = new MessageEvent.SendHotelId(
//                ((MotelModel.Hotels) listHotel.get(position)).getHotelId());
//            EventBus.getDefault().postSticky(hotelId);
//            gotoListRoomActivity();
//          }
//
//          @Override public void onLongItemClick(View view, int position) {
//            // do whatever
//          }
//        }));
  }

  @Override protected int getFragmentLayout() {
    return R.layout.fragment_hotel;
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_add_item, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_add:
        gotoCreateAndEditActivity();
        //addNewHotel();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  private void gotoListRoomActivity() {

    Intent intent = new Intent(getActivity(), ListHotelRoomActivity.class);
    startActivity(intent);
  }

  private void gotoCreateAndEditActivity() {
    if (appState.getCreateCustomer() > -1) {
      Intent intent = new Intent(getActivity(), CreateAndEditHotelActivity.class);
      startActivity(intent);
    } else {
      Intent intent = new Intent(getActivity(), CreateCustomerHotelActivity.class);
      startActivity(intent);
    }
  }

  @Subscribe(threadMode = ThreadMode.MAIN) public void onMessageEvent(MessageEvent event) {
    listHotel.add(event.getHotelModel());
    tvNoData.setVisibility(View.GONE);
    recyclerView.setVisibility(View.VISIBLE);
    adapter.notifyDataSetChanged();
    /* Do something */
  }

  @Override public void onStop() {
    super.onStop();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    EventBus.getDefault().unregister(this);
  }

  @Override public void onGetListMotelComplete(MotelModel listMotel) {
//    dismissProgress();
//    if (listMotel.getHotels() != null && listMotel.getHotels().size() > 0) {
//      listHotel.addAll(listMotel.getHotels());
//      tvNoData.setVisibility(View.GONE);
//      recyclerView.setVisibility(View.VISIBLE);
//      adapter.notifyDataSetChanged();
//    }
  }

  @Override public void onGetListMotelError() {
    dismissProgress();
  }

  @Override public void onError() {
    dismissProgress();
  }

  @Override
  public void onListMotelOverviewSuccess(ListMotelOverViewResponse response) {
    dismissProgress();
    if (response.getHotels() != null && response.getHotels().size() > 0) {
      listHotel.addAll(response.getHotels());
      tvNoData.setVisibility(View.GONE);
      recyclerView.setVisibility(View.VISIBLE);
      adapter.notifyDataSetChanged();
    }
  }

  @Override
  public void onListMotelOverviewFailed(String message) {
    dismissProgress();
    showDialog(message);
  }

  @Override
  public void onListMotelOverviewError(Throwable e) {
    dismissProgress();
  }
}

