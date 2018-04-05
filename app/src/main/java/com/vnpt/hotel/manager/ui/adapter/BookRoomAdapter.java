package com.vnpt.hotel.manager.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.app.utils.AppDef;
import com.vnpt.hotel.manager.common.Constants;
import com.vnpt.hotel.manager.domain.model.motel.BookRoomModel;
import com.vnpt.hotel.manager.domain.model.response.motel.ListBookingModel;
import com.vnpt.hotel.manager.domain.model.response.motel.ListBookingResponse;
import com.vnpt.hotel.manager.ui.activity.DetailBookCheckInActivity;
import com.vnpt.hotel.manager.ui.fragment.ListBookRoomFragment;
import com.vnpt.hotel.manager.ui.presenter.motel.ListBookingPresenter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LiKaLi on 1/26/2018.
 */

public class BookRoomAdapter extends RecyclerView.Adapter<BookRoomAdapter.BookRoomViewHolder> {

    private Context mContext;
    private List<Object> listBookRoom;
    private ListBookRoomFragment listBookRoomFragment;


    public BookRoomAdapter(Context mContext, List<Object> listHotel) {
        this.mContext = mContext;
        this.listBookRoom = listHotel;
    }

    public BookRoomAdapter(Context mContext, List<Object> listHotel, ListBookRoomFragment listBookRoomFragment) {
        this.mContext = mContext;
        this.listBookRoom = listHotel;
        this.listBookRoomFragment = listBookRoomFragment;
    }
    @Override
    public BookRoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_book_room, parent, false);
        BookRoomViewHolder hotelViewHolder = new BookRoomViewHolder(v);
        return hotelViewHolder;
    }

    @Override
    public void onBindViewHolder(BookRoomViewHolder holder, final int position) {
        if ((listBookRoom.get(position) instanceof ListBookingModel)) {
            String time = ((ListBookingModel) listBookRoom.get(position)).getBookingDate();
            holder.tv_name.setText(((ListBookingModel) listBookRoom.get(position)).getCustomerName());
            holder.tv_bookingDate.setText(time.split(" ")[0]);
            holder.tv_bookingHour.setText(time.split(" ")[1]);
            holder.tv_roomType.setText(((ListBookingModel) listBookRoom.get(position)).getRoomTypeName());
            holder.tv_Amount.setText(String.valueOf(((ListBookingModel) listBookRoom.get(position)).getAmount()));
            switch (((ListBookingModel) listBookRoom.get(position)).getStatus()) {
                case Constants.BOOKING_STATUS.INIT:
                    holder.tv_room_status.setText("Mới đặt");
                    holder.tv_room_status.setTextColor(mContext.getResources().getColor(R.color.md_green_500));
                    //holder.tv_name.setTextColor(mContext.getResources().getColor(R.color.md_green_500));
                    holder.linefooter.setBackgroundColor(mContext.getResources().getColor(R.color.md_green_500));
                    holder.tv_img_status.setBackground(mContext.getResources().getDrawable(R.drawable.border_cicrle_green));
                    holder.tv_img_status.setCompoundDrawablesWithIntrinsicBounds(null, mContext.getResources().getDrawable(R.drawable.ic_booking_init), null, null);
                    break;
                case Constants.BOOKING_STATUS.CONFIRM:
                    holder.tv_room_status.setText("Đã xác nhận");
                    //holder.tv_name.setTextColor(mContext.getResources().getColor(R.color.md_orange_500));
                    holder.tv_room_status.setTextColor(mContext.getResources().getColor(R.color.md_orange_500));
                    holder.linefooter.setBackgroundColor(mContext.getResources().getColor(R.color.md_orange_500));
                    holder.tv_img_status.setBackground(mContext.getResources().getDrawable(R.drawable.border_cicrle_orange));
                    holder.tv_img_status.setCompoundDrawablesWithIntrinsicBounds(null, mContext.getResources().getDrawable(R.drawable.ic_booking_confirm), null, null);
                    holder.btnConfirm.setAlpha(0.3f);
                    break;
                case Constants.BOOKING_STATUS.CHECKIN:
                    holder.tv_room_status.setText("Đã CheckIn");
                    //holder.tv_name.setTextColor(mContext.getResources().getColor(R.color.md_red_500));
                    holder.tv_room_status.setTextColor(mContext.getResources().getColor(R.color.md_red_500));
                    holder.linefooter.setBackgroundColor(mContext.getResources().getColor(R.color.md_red_500));
                    holder.tv_img_status.setBackground(mContext.getResources().getDrawable(R.drawable.border_cicrle_red));
                    holder.tv_img_status.setCompoundDrawablesWithIntrinsicBounds(null, mContext.getResources().getDrawable(R.drawable.ic_booking_checkin), null, null);
                    holder.btnConfirm.setAlpha(0.3f);
                    holder.btnCancel.setAlpha(0.3f);
                    break;
                case Constants.BOOKING_STATUS.CHECKOUT:
                    holder.tv_room_status.setText("Đã CheckOut");
                    //holder.tv_name.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
                    holder.tv_room_status.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
                    holder.linefooter.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
                    holder.tv_img_status.setBackground(mContext.getResources().getDrawable(R.drawable.border_cicrle_blue));
                    holder.tv_img_status.setCompoundDrawablesWithIntrinsicBounds(null, mContext.getResources().getDrawable(R.drawable.ic_booking_checkout), null, null);
                    holder.btnConfirm.setAlpha(0.3f);
                    holder.btnCancel.setAlpha(0.3f);
                    break;
                case Constants.BOOKING_STATUS.CANCEL:
                    holder.tv_room_status.setText("Đã hủy");
                    //holder.tv_name.setTextColor(mContext.getResources().getColor(R.color.colorMenu));
                    holder.tv_room_status.setTextColor(mContext.getResources().getColor(R.color.colorMenu));
                    holder.linefooter.setBackgroundColor(mContext.getResources().getColor(R.color.colorMenu));
                    holder.tv_img_status.setBackground(mContext.getResources().getDrawable(R.drawable.border_cicrle_grey));
                    holder.tv_img_status.setCompoundDrawablesWithIntrinsicBounds(null, mContext.getResources().getDrawable(R.drawable.ic_booking_cancel), null, null);
                    holder.btnConfirm.setAlpha(0.3f);
                    holder.btnCancel.setAlpha(0.3f);
                    break;
            }
            holder.btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dilogCanCelBooking(view,
                            ((ListBookingModel) listBookRoom.get(position)).getBookingDate(),
                            ((ListBookingModel) listBookRoom.get(position)).getCustomerName(),
                            ((ListBookingModel) listBookRoom.get(position)).getPhone(),
                            ((ListBookingModel) listBookRoom.get(position)).getBookingId());
                }
            });

            holder.btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listBookRoomFragment != null){
                        listBookRoomFragment.updateStatusBooking(((ListBookingModel) listBookRoom.get(position)).getBookingId());
                    }
                }
            });

            holder.btnCall.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(listBookRoomFragment != null){
                        listBookRoomFragment.callPhone(((ListBookingModel) listBookRoom.get(position)).getPhone());
                    }
                }
            });
            holder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, DetailBookCheckInActivity.class);
                    intent.putExtra("BOOKING_DETAIL", (Serializable) listBookRoom.get(position));
                    mContext.startActivity(intent);
                }
            });
            if(listBookRoomFragment == null){
                holder.btnCancel.setVisibility(View.GONE);
                holder.btnConfirm.setVisibility(View.GONE);
                holder.btnCall.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return listBookRoom == null ? 0 : listBookRoom.size();
    }

    public static class BookRoomViewHolder extends RecyclerView.ViewHolder {
        CardView card_view;
        TextView tv_room_status, tv_bookingDate, tv_roomType, tv_Amount, tv_bookingHour, tv_name, tv_img_status;
        View linefooter;
        LinearLayout btnCall, btnCancel, btnConfirm;

        BookRoomViewHolder(View itemView) {
            super(itemView);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            tv_room_status = (TextView) itemView.findViewById(R.id.tv_room_status);
            tv_img_status = (TextView) itemView.findViewById(R.id.tv_img_status);
            tv_bookingDate = (TextView) itemView.findViewById(R.id.tv_bookingDate);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_roomType = (TextView) itemView.findViewById(R.id.tv_roomType);
            tv_Amount = (TextView) itemView.findViewById(R.id.tv_Amount);
            tv_bookingHour = (TextView) itemView.findViewById(R.id.tv_bookingHour);
            linefooter = (View) itemView.findViewById(R.id.linefooter);
            btnCall = (LinearLayout) itemView.findViewById(R.id.btnCall);
            btnCancel = (LinearLayout) itemView.findViewById(R.id.btnCancel);
            btnConfirm = (LinearLayout) itemView.findViewById(R.id.btnConfirm);
        }
    }

    private void dilogCanCelBooking(View view, String bookingDate, String custormerName, String phone, final Integer bookingId) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.dialog_cancel_booking, null);
        dialogBuilder.setView(dialogView);

        final AlertDialog b = dialogBuilder.create();
        b.setCanceledOnTouchOutside(false);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        b.show();
        // set width
        if(listBookRoomFragment != null){
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(b.getWindow().getAttributes());
            DisplayMetrics displayMetrics = new DisplayMetrics();
            listBookRoomFragment.getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            width = (int) (width * 0.8);
            lp.width = width;
            lp.gravity = Gravity.CENTER;
            b.getWindow().setAttributes(lp);
        }

        //
        TextView txtNoiDung = (TextView) b.findViewById(R.id.txtNoiDung);
        TextView txtBookingDate = (TextView) b.findViewById(R.id.txtBookingDate);
        TextView txtCustomerName = (TextView) b.findViewById(R.id.txtCustomerName);
        TextView txtPhone = (TextView) b.findViewById(R.id.txtPhone);
        Button btnOk = (Button) b.findViewById(R.id.btnOk);
        Button btnHuy = (Button) b.findViewById(R.id.btnHuy);
        //
//        txtNoiDung.setText(R.string.thongBaoLogout);
        txtBookingDate.setText(bookingDate);
        txtCustomerName.setText(custormerName);
        txtPhone.setText(phone);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.dismiss();

            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.dismiss();
                if(listBookRoomFragment != null){
                    listBookRoomFragment.deleteBooking(AppDef.USER_ID, bookingId);
                }
            }
        });


    }
}
