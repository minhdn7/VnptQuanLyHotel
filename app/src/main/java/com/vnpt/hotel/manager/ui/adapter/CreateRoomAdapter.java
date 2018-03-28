package com.vnpt.hotel.manager.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.vnpt.hotel.manager.R;
import java.util.List;

/**
 * Created by LiKaLi on 3/1/2018.
 */

public class CreateRoomAdapter extends RecyclerView.Adapter<CreateRoomAdapter.ViewHolder> {
  Context mContext;
  List<String> listRoom;
  CreateRoomListener createRoomListener;

  public void setCreateRoomListener(CreateRoomListener createRoomListener) {
    this.createRoomListener = createRoomListener;
  }

  public CreateRoomAdapter(Context mContext, List<String> listRoom) {
    this.mContext = mContext;
    this.listRoom = listRoom;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(mContext).inflate(R.layout.item_recycle_create_room, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.bind(position);
  }

  @Override public int getItemCount() {
    return listRoom == null ? 0 : listRoom.size();
  }

  protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView mTextView;
    public ImageView ivDeleteRoom;
    public LinearLayout frameCreateRoom;

    public ViewHolder(View itemView) {
      super(itemView);
      mTextView = (TextView) itemView.findViewById(R.id.tv_create_hotel);
      ivDeleteRoom = (ImageView) itemView.findViewById(R.id.iv_delete_room);
      frameCreateRoom = (LinearLayout) itemView.findViewById(R.id.ll_layout_create_room);
      ivDeleteRoom.setOnClickListener(this);
      itemView.setOnClickListener(this);
    }

    void bind(int position) {
      if (position < listRoom.size() - 1) {
        ivDeleteRoom.setVisibility(View.VISIBLE);
        mTextView.setText(listRoom.get(position));
        frameCreateRoom.setBackground(null);

      }
      else {
        ivDeleteRoom.setVisibility(View.GONE);
        mTextView.setText("");
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
          frameCreateRoom.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_library_add_blue_800_48dp) );
        } else {
          frameCreateRoom.setBackground(ContextCompat.getDrawable(mContext, R.drawable.ic_library_add_blue_800_48dp));
        }
      }
    }

    @Override public void onClick(View view) {
      int adapterPosition = getAdapterPosition();
      switch (view.getId()) {
        case R.id.iv_delete_room:
          removeAt(adapterPosition);
          break;
        default:
          if (adapterPosition == listRoom.size() - 1) {
            createRoomListener.addNewRoom();
          }
          break;
      }
    }
  }

  public interface CreateRoomListener {
    void addNewRoom();
  }

  public void removeAt(int position) {
    listRoom.remove(position);
    notifyItemRemoved(position);
    notifyItemRangeChanged(position, getItemCount());
  }
}
