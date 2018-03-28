package com.vnpt.hotel.manager.domain.model.roomtype;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public class CreateRoomTypePrice {

  @Expose @Setter @Getter @SerializedName("userId") public int userId;
  @Expose @Setter @Getter @SerializedName("roomTypeId") public int roomTypeId;
  @Expose @Setter @Getter @SerializedName("priceNight") public int priceNight;
  @Expose @Setter @Getter @SerializedName("priceHour") public int priceHour;
  @Expose @Setter @Getter @SerializedName("priceDay") public int priceDay;
  @Expose @Setter @Getter @SerializedName("overTimePriceNight") public int overTimePriceNight;
  @Expose @Setter @Getter @SerializedName("overTimePrice") public int overTimePrice;
  @Expose @Setter @Getter @SerializedName("earlyHours") public int earlyHours;
  @Expose @Setter @Getter @SerializedName("checkOutTime") public int checkOutTime;
}
