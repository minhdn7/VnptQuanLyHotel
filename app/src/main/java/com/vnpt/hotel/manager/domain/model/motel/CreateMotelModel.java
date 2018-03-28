package com.vnpt.hotel.manager.domain.model.motel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public class CreateMotelModel {
  @Expose @Setter @Getter @SerializedName("userId") private int userId;
  @Expose @Setter @Getter @SerializedName("hotelType") private String hotelType;
  @Expose @Setter @Getter @SerializedName("hotelName") private String hotelName;
  @Expose @Setter @Getter @SerializedName("description") private String description;
  @Expose @Setter @Getter @SerializedName("customerId") private int customerId;
  @Expose @Setter @Getter @SerializedName("address") private String address;
}
