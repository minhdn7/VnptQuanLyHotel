package com.vnpt.hotel.manager.domain.model.roomtype;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public class RoomTypeFindByHotel {

  @Expose @Setter @Getter @SerializedName("pageSize") public int pageSize;
  @Expose @Setter @Getter @SerializedName("pageIndex") public int pageIndex;
  @Expose @Setter @Getter @SerializedName("hotelId") public int hotelId;
}
