package com.vnpt.hotel.manager.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.domain.model.invoice.InvoiceItemModel;

import java.util.List;

/**
 * Created by LiKaLi on 1/26/2018.
 */

public class InvoiceItemAdapter extends ArrayAdapter<Object> {
  Context mContext;
  List<Object> listInvoiceItem;

  public InvoiceItemAdapter(List<Object> listInvoiceItem, Context context) {
    super(context, R.layout.item_recycler_item_invoice, listInvoiceItem);
    this.listInvoiceItem = listInvoiceItem;
    this.mContext = context;
  }


  public View getView(final int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View view = inflater.inflate(R.layout.item_recycler_item_invoice, null);
    TextView tv_Count = (TextView) view.findViewById(R.id.tv_Count);
    TextView tv_nameItem = (TextView) view.findViewById(R.id.tv_nameItem);
    TextView tv_Price = (TextView) view.findViewById(R.id.tv_Price);
    TextView tv_Amount = (TextView) view.findViewById(R.id.tv_Amount);
    if ((listInvoiceItem.get(position) instanceof InvoiceItemModel)) {
      tv_nameItem.setText(((InvoiceItemModel) listInvoiceItem.get(position)).getItemName());
      tv_Amount.setText(((InvoiceItemModel) listInvoiceItem.get(position)).getItemAmount());
      tv_Count.setText(((InvoiceItemModel) listInvoiceItem.get(position)).getItemCount());
      tv_Price.setText(((InvoiceItemModel) listInvoiceItem.get(position)).getItemPrice());
    }
    return view;
  }
}
