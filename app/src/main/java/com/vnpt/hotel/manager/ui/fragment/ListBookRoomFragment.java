package com.vnpt.hotel.manager.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.app.BaseFragment;
import com.vnpt.hotel.manager.app.utils.AppDef;
import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import com.vnpt.hotel.manager.domain.model.motel.BookRoomModel;
import com.vnpt.hotel.manager.domain.model.request.motel.ListBookingRequest;
import com.vnpt.hotel.manager.domain.model.request.motel.UpdateBookingRequest;
import com.vnpt.hotel.manager.domain.model.response.motel.ListBookingResponse;
import com.vnpt.hotel.manager.domain.model.response.motel.UpdateBookingResponse;
import com.vnpt.hotel.manager.ui.adapter.BookRoomAdapter;
import com.vnpt.hotel.manager.ui.presenter.motel.CancelBookingPresenter;
import com.vnpt.hotel.manager.ui.presenter.motel.ListBookingPresenter;
import com.vnpt.hotel.manager.ui.presenter.motel.UpdateBookingPresenter;
import com.vnpt.hotel.manager.ui.view.motel.CancelBookingView;
import com.vnpt.hotel.manager.ui.view.motel.ListBookingView;
import com.vnpt.hotel.manager.ui.view.motel.UpdateBookingView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListBookRoomFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener, ListBookingView, CancelBookingView, UpdateBookingView {
    @BindView(R.id.recycler_view_book_room)
    RecyclerView recyclerView;
    @BindView(R.id.progressBarLoadMore)
    ProgressBar progressBarLoadMore;
    Unbinder unbinder;

    @BindView(R.id.txtFromDate)
    TextView txtFromDate;
    @BindView(R.id.txtToDate)
    TextView txtToDate;
    @BindView(R.id.viewSearch)
    LinearLayout viewSearch;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    private List<Object> listBookRoom = new ArrayList<>();
    BookRoomAdapter adapter;
    private Integer pageIndex = 0;
    private Integer pageSize = 10;
    private Integer idHotel = 0;
    private static final int REQUEST_PHONE_CALL = 1;
    // config loadmore
    private int lastVisibleItem, totalItemCount;
    private boolean isLoadingMore = false;
    private int visibleThreshold = 5;
    private String phone = "";
    private String fromDate = "";
    private String toDate = "";
    LinearLayoutManager linearLayoutManager;
    // end
    @Inject
    ListBookingPresenter listBookingPresenter;
    @Inject
    CancelBookingPresenter cancelBookingPresenter;
    @Inject
    UpdateBookingPresenter updateBookingPresenter;

    private void initializeData() {
        listBookRoom = new ArrayList<>();
        listBookRoom.add(new BookRoomModel("Nguyễn Hoàng Việt", "0918666096", "12/03/2018 09:45", "INIT", 2, "Phòng bình dân"));
        listBookRoom.add(new BookRoomModel("Đặng Nhật Minh", "0979312873", "12/03/2018 08:40", "CONFIRM", 1, "Phòng VIP"));
        listBookRoom.add(new BookRoomModel("Nguyễn Tất Tiến", "0987654321", "12/03/2018 09:30", "CHECKIN", 1, "Phòng giường đôi"));
        listBookRoom.add(new BookRoomModel("Phạm Xuân Thao", "01234567890", "08/03/2018 09:00", "CHECKOUT", 2, "Phòng giường đơn"));
        listBookRoom.add(new BookRoomModel("Nguyễn Bá Lợi", "0966667889", "12/03/2018 11:50", "CANCEL", 3, "Phòng bình dân"));
    }

    public ListBookRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
//    initializeData();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        addControls();
        addEvents();
    }

    private void addEvents() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (isLoadingMore && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    progressBarLoadMore.setVisibility(View.VISIBLE);
                    pageIndex += 1;

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
            }
        });
    }

    private void addControls() {
        idHotel = getArguments().getInt("HOTEL_ID");
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        showProgress();
        // set view
        listBookingPresenter.setView(this);
        listBookingPresenter.onViewCreate();
        cancelBookingPresenter.setView(this);
        cancelBookingPresenter.onViewCreate();
        updateBookingPresenter.setView(this);
        updateBookingPresenter.onViewCreate();
        resetLoading();
        // end
        ListBookingRequest request = new ListBookingRequest();
        request.setPage(pageIndex);
        request.setSize(pageSize);
        // get yesterday date
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calFromDate = Calendar.getInstance();
        Calendar calToDate = Calendar.getInstance();
        calToDate.add(Calendar.DATE, 0);
        calFromDate.add(Calendar.DATE, -1);
        toDate = dateFormat.format(calToDate.getTime());
        fromDate = dateFormat.format(calFromDate.getTime());
        txtFromDate.setText(fromDate);
        txtToDate.setText(toDate);
        request.setFromDate(fromDate);
        // end
        listBookingPresenter.getList(AppDef.TOKEN_USER, request);
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        adapter = new BookRoomAdapter(getContext(), listBookRoom, this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_list_book_room;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add_item, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Toast.makeText(getActivity(), "ListBookRoom Fragment menu", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_search:
                if (viewSearch.getVisibility() == View.VISIBLE) {
                    viewSearch.setVisibility(View.GONE);
                } else {
                    viewSearch.setVisibility(View.VISIBLE);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onListBookingSuccess(ListBookingResponse response) {
        dismissProgress();
        if (response.getBookingList() != null && response.getBookingList().size() > 0) {
            listBookRoom.addAll(response.getBookingList());
            tvNoData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            progressBarLoadMore.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
            if (response.getBookingList().size() >= pageSize) {
                isLoadingMore = true;
            } else {
                isLoadingMore = false;
            }
        } else {
            isLoadingMore = false;
            progressBarLoadMore.setVisibility(View.GONE);
        }
        if (listBookRoom != null && listBookRoom.size() <= 0) {
            tvNoData.setVisibility(View.VISIBLE);
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

    @Override
    public void onCancelBookingSuccess(CommonApiResult response) {
        dismissProgress();
        refreshList();
    }

    @Override
    public void onCancelBookingFailed(String message) {
        dismissProgress();
        showDialog(message);
    }

    @Override
    public void onCancelBookingError(Throwable e) {
        dismissProgress();
        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateBookingSuccess(UpdateBookingResponse response) {
        dismissProgress();
        showDialog(response.getResponseMessage());
        refreshList();
    }

    @Override
    public void onUpdateBookingFailed(String message) {
        dismissProgress();
        showDialog(message);
    }

    @Override
    public void onUpdateBookingError(Throwable e) {
        dismissProgress();
        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
    }


    public void updateStatusBooking(Integer bookingId) {
        showProgress();
        UpdateBookingRequest request = new UpdateBookingRequest();
        request.setBookingId(bookingId);
        request.setStatus("CONFIRM");
        updateBookingPresenter.updateBooking(AppDef.TOKEN_USER, request);
    }

    public void deleteBooking(Integer userId, Integer bookingId) {
        showProgress();
        cancelBookingPresenter.cancelBooking(AppDef.TOKEN_USER, userId, bookingId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void resetLoading() {
        try {
            pageIndex = 0;
            pageSize = 10;
            isLoadingMore = false;
            if (listBookRoom.size() > 0) {
                listBookRoom.clear();
            }
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshList() {
        resetLoading();
        // end
        ListBookingRequest request = new ListBookingRequest();
        request.setPage(pageIndex);
        request.setSize(pageSize);
        // get yesterday date
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calFromDate = Calendar.getInstance();
        Calendar calToDate = Calendar.getInstance();
        calToDate.add(Calendar.DATE, 0);
        calFromDate.add(Calendar.DATE, -1);
        toDate = dateFormat.format(calToDate.getTime());
        fromDate = dateFormat.format(calFromDate.getTime());

        request.setFromDate(fromDate);
        // end
        listBookingPresenter.getList(AppDef.TOKEN_USER, request);
    }

    public void callPhone(String phone) {
        this.phone = phone;
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
            }
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phone));
            //                    startActivity(callIntent);
            startActivity(Intent.createChooser(callIntent, "Chọn ứng dụng thực hiện cuộc gọi:"));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_CALL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + phone));
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling

                        return;
                    }
                    //                    startActivity(callIntent);
                    startActivity(Intent.createChooser(callIntent, "Chọn ứng dụng thực hiện cuộc gọi:"));
                } else {

                }
                return;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getActivity().getFragmentManager().findFragmentByTag("Datepickerdialog");
        if (dpd != null) {
            dpd.setOnDateSetListener(this);
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
        String month = "";
        if (monthOfYear < 9) {
            monthOfYear++;
            month += "0" + monthOfYear;
        }
        txtFromDate.setText(dayOfMonth + "/" + month + "/" + year);
        month = "";
        if (monthOfYearEnd < 9) {
            monthOfYearEnd++;
            month += "0" + monthOfYearEnd;
        }
        txtToDate.setText(dayOfMonthEnd + "/" + month + "/" + yearEnd);
    }


    @OnClick({R.id.btnDate, R.id.btnSearch, R.id.btnCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnDate:
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(ListBookRoomFragment.this, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
                break;
            case R.id.btnSearch:
                resetLoading();
                // end
                ListBookingRequest request = new ListBookingRequest();
                request.setPage(pageIndex);
                request.setSize(pageSize);

                request.setFromDate(txtFromDate.getText().toString().trim());
                request.setToDate(txtToDate.getText().toString().trim());
                // end
                listBookingPresenter.getList(AppDef.TOKEN_USER, request);
                break;
            case R.id.btnCancel:
                viewSearch.setVisibility(View.GONE);
                break;
        }
    }
}
