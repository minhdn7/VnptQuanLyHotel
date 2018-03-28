package com.vnpt.hotel.manager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.domain.model.motel.EmployeModel;
import com.vnpt.hotel.manager.domain.model.motel.MotelModel;
import com.vnpt.hotel.manager.domain.model.response.motel.ListMotelOverViewResponse;
import com.vnpt.hotel.manager.domain.model.response.motel.MotelOverViewModel;
import com.vnpt.hotel.manager.ui.activity.CheckInActivity;
import com.vnpt.hotel.manager.ui.activity.CheckOutActivity;
import com.vnpt.hotel.manager.ui.fragment.ListBookRoomFragment;
import com.vnpt.hotel.manager.ui.fragment.ListRoomFragment;

import java.util.List;

/**
 * Created by LiKaLi on 1/26/2018.
 */

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {
  Context mContext;
  List<Object> listHotel;

  public HotelAdapter(List<Object> listHotel, Context context) {
    this.listHotel = listHotel;
    this.mContext = context;
  }

  @Override public HotelAdapter.HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_recycler_hotel, parent, false);
    HotelViewHolder hotelViewHolder = new HotelViewHolder(v);
    return hotelViewHolder;
  }

  @Override public void onBindViewHolder(HotelViewHolder holder, int position) {

    if ((listHotel.get(position) instanceof MotelOverViewModel)) {
      holder.hotelName.setText(((MotelOverViewModel) listHotel.get(position)).getHotelName());
      holder.tv_hotel_address.setText(((MotelOverViewModel) listHotel.get(position)).getAddress());
      try {
        if (((MotelOverViewModel) listHotel.get(position)).getPictures() != null) {
          Picasso.with(mContext)
              .load(mContext.getString(R.string.api_base_url_pictures)
                  + ((MotelModel.Hotels) listHotel.get(position)).getPictures().split(",")[0])
              .fit()
              .centerCrop().placeholder(mContext.getResources().getDrawable(R.drawable.nha_nghi_1))
              .into(holder.hotelPhoto);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else if ((listHotel.get(position) instanceof EmployeModel)) {
      holder.hotelName.setText(((EmployeModel) listHotel.get(position)).getNameEmploy());
    }
    holder.btnBooking.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Fragment fragment = null;
        Class fragmentClass = ListBookRoomFragment.class;
        try {
          fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
          e.printStackTrace();
        }
        FragmentManager fragmentManager = ((FragmentActivity) mContext).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout_content, fragment).commit();
      }
    });
    holder.layout_imgView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Fragment fragment = null;
        Class fragmentClass = ListRoomFragment.class;
        try {
          fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
          e.printStackTrace();
        }
        FragmentManager fragmentManager = ((FragmentActivity) mContext).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout_content, fragment).commit();
      }
    });
    holder.btnCheckIn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ((FragmentActivity) mContext).startActivity(new Intent(mContext, CheckInActivity.class));
      }
    });
    holder.btnCheckOut.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ((FragmentActivity) mContext).startActivity(new Intent(mContext, CheckOutActivity.class));
      }
    });
  }

  @Override public int getItemCount() {
    return listHotel != null ? listHotel.size() : 0;
  }

  public static class HotelViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    TextView hotelName, tv_hotel_address;
    ImageView hotelPhoto;
    LinearLayout btnBooking, layout_imgView, btnCheckIn, btnCheckOut;

    HotelViewHolder(View itemView) {
      super(itemView);
      cardView = (CardView) itemView.findViewById(R.id.card_view);
      hotelName = (TextView) itemView.findViewById(R.id.tv_hotel_name);
      hotelPhoto = (ImageView) itemView.findViewById(R.id.img_hotel);
      tv_hotel_address = (TextView) itemView.findViewById(R.id.tv_hotel_address);
      btnBooking = (LinearLayout) itemView.findViewById(R.id.btnBooking);
      layout_imgView = (LinearLayout) itemView.findViewById(R.id.layout_imgView);
      btnCheckIn = (LinearLayout) itemView.findViewById(R.id.btnCheckIn);
      btnCheckOut = (LinearLayout) itemView.findViewById(R.id.btnCheckOut);
    }
  }
}
