package com.vnpt.hotel.manager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.domain.model.invoice.InvoiceModel;

import java.util.List;

/**
 * Created by LiKaLi on 1/26/2018.
 */

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder> {
  Context mContext;
  List<Object> listInvoice;

  public InvoiceAdapter(List<Object> listInvoice, Context context) {
    this.listInvoice = listInvoice;
    this.mContext = context;
  }

  @Override public InvoiceAdapter.InvoiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_recycler_invoice, parent, false);
    InvoiceViewHolder hotelViewHolder = new InvoiceViewHolder(v);
    return hotelViewHolder;
  }

  @Override public void onBindViewHolder(InvoiceViewHolder holder, int position) {
    if ((listInvoice.get(position) instanceof InvoiceModel.Invoice)) {
      holder.tv_room_number.setText(((InvoiceModel.Invoice) listInvoice.get(position)).getRoomNumber());
      holder.tv_amount.setText(((InvoiceModel.Invoice) listInvoice.get(position)).getAmount());
      holder.tv_editRoom.setText(((InvoiceModel.Invoice) listInvoice.get(position)).getAmountRoom());
      holder.tv_editService.setText(((InvoiceModel.Invoice) listInvoice.get(position)).getAmountService());
      holder.tv_checkInDate.setText(((InvoiceModel.Invoice) listInvoice.get(position)).getCheckInDate().split(" ")[0]);
      holder.tv_checkInHour.setText(((InvoiceModel.Invoice) listInvoice.get(position)).getCheckInDate().split(" ")[1]);
      holder.tv_checkOutDate.setText(((InvoiceModel.Invoice) listInvoice.get(position)).getCheckInDate().split(" ")[0]);
      holder.tv_checkOutHour.setText(((InvoiceModel.Invoice) listInvoice.get(position)).getCheckInDate().split(" ")[1]);
      InvoiceItemAdapter adapter = new InvoiceItemAdapter(((InvoiceModel.Invoice) listInvoice.get(position)).getInvoiceItems(), mContext);
      holder.layoutService.removeAllViewsInLayout();
      int adapterCount = adapter.getCount();
      for (int i = 0; i < adapterCount; i++) {
        View item = adapter.getView(i, null, null);
        holder.layoutService.addView(item);
      }
    }
  }

  @Override public int getItemCount() {
    return listInvoice != null ? listInvoice.size() : 0;
  }

  public static class InvoiceViewHolder extends RecyclerView.ViewHolder {
    TextView tv_amount, tv_room_number, tv_editRoom, tv_editService, tv_checkInHour, tv_checkOutHour, tv_checkInDate, tv_checkOutDate;
    LinearLayout layoutService;

    InvoiceViewHolder(View itemView) {
      super(itemView);
      layoutService = (LinearLayout) itemView.findViewById(R.id.layoutService);
      tv_amount = (TextView) itemView.findViewById(R.id.tv_amount);
      tv_room_number = (TextView) itemView.findViewById(R.id.tv_room_number);
      tv_checkInHour = (TextView) itemView.findViewById(R.id.tv_checkInHour);
      tv_checkInDate = (TextView) itemView.findViewById(R.id.tv_checkInDate);
      tv_checkOutHour = (TextView) itemView.findViewById(R.id.tv_checkOutHour);
      tv_checkOutDate = (TextView) itemView.findViewById(R.id.tv_checkOutDate);
      tv_editRoom = (TextView) itemView.findViewById(R.id.tv_editRoom);
      tv_editService = (TextView) itemView.findViewById(R.id.tv_editService);
    }
  }
}
