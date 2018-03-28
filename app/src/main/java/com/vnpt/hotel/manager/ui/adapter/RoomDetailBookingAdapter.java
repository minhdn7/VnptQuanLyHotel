package com.vnpt.hotel.manager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.domain.model.bookingdetail.BookingDetailModel;

import java.util.List;

/**
 * Created by LiKaLi on 1/26/2018.
 */

public class RoomDetailBookingAdapter extends RecyclerView.Adapter<RoomDetailBookingAdapter.BookingDetailViewHolder> {
  Context mContext;
  List<Object> listRoom;

  public RoomDetailBookingAdapter(List<Object> listRoom, Context context) {
    this.listRoom = listRoom;
    this.mContext = context;
  }

  @Override public RoomDetailBookingAdapter.BookingDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_recycler_room_booking_detail, parent, false);
    BookingDetailViewHolder hotelViewHolder = new BookingDetailViewHolder(v);
    return hotelViewHolder;
  }

  @Override public void onBindViewHolder(BookingDetailViewHolder holder, int position) {
    if ((listRoom.get(position) instanceof BookingDetailModel.BookingDetail)) {
      holder.tv_room_number.setText(((BookingDetailModel.BookingDetail) listRoom.get(position)).getRoomNumber());
      holder.tv_checkInHour.setText(((BookingDetailModel.BookingDetail) listRoom.get(position)).getCheckInDate().split(" ")[1]);
      holder.tv_checkInDate.setText(((BookingDetailModel.BookingDetail) listRoom.get(position)).getCheckInDate().split(" ")[0]);
    }
  }

  @Override public int getItemCount() {
    return listRoom != null ? listRoom.size() : 0;
  }

  public static class BookingDetailViewHolder extends RecyclerView.ViewHolder {
    TextView tv_checkInHour, tv_checkInDate, tv_room_number;
    RadioButton rd_checkOut;

    BookingDetailViewHolder(View itemView) {
      super(itemView);
      tv_checkInHour = (TextView) itemView.findViewById(R.id.tv_checkInHour);
      rd_checkOut = (RadioButton) itemView.findViewById(R.id.rd_checkOut);
      tv_checkInDate = (TextView) itemView.findViewById(R.id.tv_checkInDate);
      tv_room_number = (TextView) itemView.findViewById(R.id.tv_room_number);
    }
  }
}
