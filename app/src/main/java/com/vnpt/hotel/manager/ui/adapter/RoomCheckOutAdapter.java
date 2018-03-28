package com.vnpt.hotel.manager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.domain.model.room.RoomModel;

import java.util.List;

/**
 * Created by LiKaLi on 1/26/2018.
 */

public class RoomCheckOutAdapter extends RecyclerView.Adapter<RoomCheckOutAdapter.RoomViewHolder> {
  Context mContext;
  List<Object> listRoom;

  public RoomCheckOutAdapter(List<Object> listRoom, Context context) {
    this.listRoom = listRoom;
    this.mContext = context;
  }

  @Override public RoomCheckOutAdapter.RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_recycler_room_checkout, parent, false);
    RoomViewHolder hotelViewHolder = new RoomViewHolder(v);
    return hotelViewHolder;
  }

  @Override public void onBindViewHolder(RoomViewHolder holder, int position) {
    if ((listRoom.get(position) instanceof RoomModel.Room)) {
      holder.tv_room_number.setText(((RoomModel.Room) listRoom.get(position)).getRoomNumber());
      holder.tv_room_type.setText(((RoomModel.Room) listRoom.get(position)).getRoomTypeName());
    }
  }

  @Override public int getItemCount() {
    return listRoom != null ? listRoom.size() : 0;
  }

  public static class RoomViewHolder extends RecyclerView.ViewHolder {
    TextView tv_room_number, tv_room_type;
    LinearLayout layoutRoom;

    RoomViewHolder(View itemView) {
      super(itemView);
      tv_room_number = (TextView) itemView.findViewById(R.id.tv_room_number);
      layoutRoom = (LinearLayout) itemView.findViewById(R.id.layoutRoom);
      tv_room_type = (TextView) itemView.findViewById(R.id.tv_room_type);
    }
  }
}
