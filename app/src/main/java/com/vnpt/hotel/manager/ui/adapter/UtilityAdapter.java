package com.vnpt.hotel.manager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.domain.model.roomtype.UtilityModel;
import java.util.List;

/**
 * Created by LiKaLi on 3/1/2018.
 */

public class UtilityAdapter extends RecyclerView.Adapter<UtilityAdapter.ViewHolder> {
  private Context context;
  private List<UtilityModel> utilitysList;
  private SparseBooleanArray itemStateArray = new SparseBooleanArray();

  public UtilityAdapter(Context context, List<UtilityModel> utilitysList) {
    this.context = context;
    this.utilitysList = utilitysList;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    return new ViewHolder(inflater.inflate(R.layout.item_recycler_utility_room, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.bind(position);
    holder.setIsRecyclable(false);
  }

  @Override public int getItemCount() {
    return utilitysList == null ? 0 : utilitysList.size();
  }

  protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public CheckedTextView mCheckedTextView;

    public ViewHolder(View itemView) {
      super(itemView);
      mCheckedTextView = (CheckedTextView) itemView.findViewById(R.id.checket_text_utility);
      itemView.setOnClickListener(this);
    }

    void bind(int position) {
      mCheckedTextView.setText(utilitysList.get(position).getNameUtility());
      if (!itemStateArray.get(position, false)) {
        mCheckedTextView.setChecked(false);
      } else {
        mCheckedTextView.setChecked(true);
      }
    }

    @Override public void onClick(View view) {
      int adapterPosition = getAdapterPosition();
      if (!itemStateArray.get(adapterPosition, false)) {
        mCheckedTextView.setChecked(true);
        itemStateArray.put(adapterPosition, true);
        utilitysList.get(adapterPosition).setCheckedUtility(true);
      } else {
        mCheckedTextView.setChecked(false);
        itemStateArray.put(adapterPosition, false);
        utilitysList.get(adapterPosition).setCheckedUtility(false);
      }
    }
  }
}
