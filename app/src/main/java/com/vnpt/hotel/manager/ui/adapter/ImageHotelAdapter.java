package com.vnpt.hotel.manager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.vnpt.hotel.manager.R;
import java.io.File;
import java.util.List;

/**
 * Created by LiKaLi on 2/27/2018.
 */

public class ImageHotelAdapter extends RecyclerView.Adapter<ImageHotelAdapter.ViewHolder> {
  private Context context;
  private List<File> imagesFiles;
  private MyOnClickListener mOnClickListener;

  public ImageHotelAdapter(Context context, List<File> imagesFiles) {
    this.context = context;
    this.imagesFiles = imagesFiles;
  }

  public void setmOnClickListener(MyOnClickListener mOnClickListener) {
    this.mOnClickListener = mOnClickListener;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    return new ViewHolder(inflater.inflate(R.layout.item_recycler_image_room_hotel, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, final int position) {
    if (position < imagesFiles.size() - 1) {
      Picasso.with(context)
          .load(imagesFiles.get(position))
          .fit()
          .centerCrop()
          .into(holder.imageHotelView);
      holder.imageDeleteHotel.setVisibility(View.VISIBLE);
      holder.imageDeleteHotel.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          removeAt(position);
        }
      });
    } else {
      Picasso.with(context)
          .load(R.drawable.ic_add_a_photo_black_48dp)
          .fit()
          .centerCrop()
          .into(holder.imageHotelView);
      holder.imageDeleteHotel.setVisibility(View.GONE);
      holder.imageHotelView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          mOnClickListener.onClickItemView(position);
        }
      });
    }

  }

  @Override public int getItemCount() {
    return imagesFiles == null ? 0 : imagesFiles.size();
  }

  protected static class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageHotelView, imageDeleteHotel;

    public ViewHolder(View itemView) {
      super(itemView);
      imageHotelView = (ImageView) itemView.findViewById(R.id.iv_photo_hotel);
      imageDeleteHotel = (ImageView) itemView.findViewById(R.id.iv_delete_photo);
    }
  }

  public interface MyOnClickListener {
    void onClickItemView(int position);

    void onClickDeleteView(int position);
  }
  public void removeAt(int position) {
    imagesFiles.remove(position);
    notifyItemRemoved(position);
    notifyItemRangeChanged(position, getItemCount());
  }
}
