package com.vnpt.hotel.manager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.domain.model.staff.StaffModel;

import java.util.List;

/**
 * Created by LiKaLi on 1/26/2018.
 */

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewHolder> {
  Context mContext;
  List<Object> listStaff;

  public StaffAdapter(List<Object> listStaff, Context context) {
    this.listStaff = listStaff;
    this.mContext = context;
  }

  @Override public StaffAdapter.StaffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_recycler_staff, parent, false);
    StaffViewHolder hotelViewHolder = new StaffViewHolder(v);
    return hotelViewHolder;
  }

  @Override public void onBindViewHolder(StaffViewHolder holder, int position) {
    if ((listStaff.get(position) instanceof StaffModel.Staff)) {
      holder.tv_name.setText(((StaffModel.Staff) listStaff.get(position)).getStaffName());
      holder.tv_position.setText(((StaffModel.Staff) listStaff.get(position)).getStaffPosition());
    }
  }

  @Override public int getItemCount() {
    return listStaff != null ? listStaff.size() : 0;
  }

  public static class StaffViewHolder extends RecyclerView.ViewHolder {
    TextView tv_name, tv_position;
    LinearLayout btnMessage, btnCall, btnEdit, btnMotel, btnStop;

    StaffViewHolder(View itemView) {
      super(itemView);
      tv_name = (TextView) itemView.findViewById(R.id.tv_name);
      tv_position = (TextView) itemView.findViewById(R.id.tv_position);
      btnMessage = (LinearLayout) itemView.findViewById(R.id.btnMessage);
      btnCall = (LinearLayout) itemView.findViewById(R.id.btnCall);
      btnEdit = (LinearLayout) itemView.findViewById(R.id.btnEdit);
      btnMotel = (LinearLayout) itemView.findViewById(R.id.btnMotel);
      btnStop = (LinearLayout) itemView.findViewById(R.id.btnStop);
    }
  }
}
