package com.vnpt.hotel.manager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.common.Constants;
import com.vnpt.hotel.manager.domain.model.room.RoomModel;

import java.util.List;

/**
 * Created by LiKaLi on 1/26/2018.
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
  Context mContext;
  List<Object> listRoom;

  public RoomAdapter(List<Object> listRoom, Context context) {
    this.listRoom = listRoom;
    this.mContext = context;
  }

  @Override public RoomAdapter.RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_recycler_room, parent, false);
    RoomViewHolder hotelViewHolder = new RoomViewHolder(v);
    return hotelViewHolder;
  }

  @Override public void onBindViewHolder(RoomViewHolder holder, int position) {
    if ((listRoom.get(position) instanceof RoomModel.Room)) {
      holder.tv_room_number.setText(((RoomModel.Room) listRoom.get(position)).getRoomNumber());
      holder.tv_room_type.setText(((RoomModel.Room) listRoom.get(position)).getRoomTypeName());
      switch (((RoomModel.Room) listRoom.get(position)).getStatus()) {
        case Constants.ROOM_STATUS.EMPTY:
          holder.tv_room_number.setTextColor(mContext.getResources().getColor(R.color.md_green_500));
          holder.tv_room_status.setTextColor(mContext.getResources().getColor(R.color.md_green_500));
          holder.lineSpec.setBackgroundColor(mContext.getResources().getColor(R.color.md_green_500));
          holder.tv_room_status.setText("Còn trống");
          holder.btnCheckIn.setVisibility(View.VISIBLE);
          holder.btnCheckOut.setVisibility(View.GONE);
          holder.btnFood.setVisibility(View.GONE);
          holder.btnHelp.setVisibility(View.GONE);
          holder.btnActive.setVisibility(View.GONE);
          holder.btnReady.setVisibility(View.GONE);
          holder.btnStop.setVisibility(View.VISIBLE);
          break;
        case Constants.ROOM_STATUS.USING:
          holder.tv_room_status.setText("Đang sử dụng");
          holder.btnCheckIn.setVisibility(View.GONE);
          holder.btnCheckOut.setVisibility(View.VISIBLE);
          holder.btnFood.setVisibility(View.VISIBLE);
          holder.btnHelp.setVisibility(View.VISIBLE);
          holder.btnActive.setVisibility(View.GONE);
          holder.btnReady.setVisibility(View.GONE);
          holder.btnStop.setVisibility(View.GONE);
          break;
        case Constants.ROOM_STATUS.CLEANING:
          holder.tv_room_number.setTextColor(mContext.getResources().getColor(R.color.md_orange_500));
          holder.tv_room_status.setTextColor(mContext.getResources().getColor(R.color.md_orange_500));
          holder.lineSpec.setBackgroundColor(mContext.getResources().getColor(R.color.md_orange_500));
          holder.tv_room_status.setText("Đang dọn dẹp");
          holder.btnCheckIn.setVisibility(View.VISIBLE);
          holder.btnCheckOut.setVisibility(View.GONE);
          holder.btnFood.setVisibility(View.GONE);
          holder.btnHelp.setVisibility(View.GONE);
          holder.btnActive.setVisibility(View.GONE);
          holder.btnReady.setVisibility(View.VISIBLE);
          holder.btnStop.setVisibility(View.GONE);
          break;
        case Constants.ROOM_STATUS.REPAIRING:
          holder.tv_room_number.setTextColor(mContext.getResources().getColor(R.color.colorMenu));
          holder.tv_room_status.setTextColor(mContext.getResources().getColor(R.color.colorMenu));
          holder.tv_room_type.setTextColor(mContext.getResources().getColor(R.color.colorMenu));
          holder.lineSpec.setBackgroundColor(mContext.getResources().getColor(R.color.colorMenu));
          holder.tv_room_status.setText("Không hoạt động");
          holder.btnCheckIn.setVisibility(View.GONE);
          holder.btnCheckOut.setVisibility(View.GONE);
          holder.btnFood.setVisibility(View.GONE);
          holder.btnHelp.setVisibility(View.GONE);
          holder.btnActive.setVisibility(View.VISIBLE);
          holder.btnReady.setVisibility(View.GONE);
          holder.btnStop.setVisibility(View.GONE);
          break;
      }
    }
  }

  @Override public int getItemCount() {
    return listRoom != null ? listRoom.size() : 0;
  }

  public static class RoomViewHolder extends RecyclerView.ViewHolder {
    TextView tv_room_number, tv_room_status, tv_room_type, tong_thong_bao_food, tong_thong_bao_help;
    View lineSpec, linefooter;
    LinearLayout btnFood, btnHelp, thong_bao_food, lo_thong_bao_help, layoutFooter, btnCheckIn, btnCheckOut, btnActive, btnReady, btnStop;

    RoomViewHolder(View itemView) {
      super(itemView);
      tv_room_number = (TextView) itemView.findViewById(R.id.tv_room_number);
      tv_room_status = (TextView) itemView.findViewById(R.id.tv_room_status);
      tv_room_type = (TextView) itemView.findViewById(R.id.tv_room_type);
      tong_thong_bao_food = (TextView) itemView.findViewById(R.id.tong_thong_bao_food);
      tong_thong_bao_help = (TextView) itemView.findViewById(R.id.tong_thong_bao_help);
      btnFood = (LinearLayout) itemView.findViewById(R.id.btnFood);
      btnHelp = (LinearLayout) itemView.findViewById(R.id.btnHelp);
      thong_bao_food = (LinearLayout) itemView.findViewById(R.id.thong_bao_food);
      lo_thong_bao_help = (LinearLayout) itemView.findViewById(R.id.lo_thong_bao_help);
      layoutFooter = (LinearLayout) itemView.findViewById(R.id.layoutFooter);
      lineSpec = (View) itemView.findViewById(R.id.lineSpec);
      linefooter = (View) itemView.findViewById(R.id.linefooter);
      btnCheckIn = (LinearLayout) itemView.findViewById(R.id.btnCheckIn);
      btnCheckOut = (LinearLayout) itemView.findViewById(R.id.btnCheckOut);
      btnActive = (LinearLayout) itemView.findViewById(R.id.btnActive);
      btnReady = (LinearLayout) itemView.findViewById(R.id.btnReady);
      btnStop = (LinearLayout) itemView.findViewById(R.id.btnStop);
    }
  }
}
