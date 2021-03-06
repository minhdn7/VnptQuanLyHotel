package com.vnpt.hotel.manager.domain.model.motel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public class CreateMotelApiResult {

  @Expose @Setter @Getter @SerializedName("status") private String status;
  @Expose @Setter @Getter @SerializedName("responseMessage") private String responseMessage;
  @Expose @Setter @Getter @SerializedName("responseCode") private int responseCode;
  @Expose @Setter @Getter @SerializedName("pictures") private String pictures;
  @Expose @Setter @Getter @SerializedName("hotelType") private String hotelType;
  @Expose @Setter @Getter @SerializedName("hotelName") private String hotelName;
  @Expose @Setter @Getter @SerializedName("hotelId") private int hotelId;
  @Expose @Setter @Getter @SerializedName("description") private String description;
  @Expose @Setter @Getter @SerializedName("customerId") private int customerId;
  @Expose @Setter @Getter @SerializedName("createDate") private String createDate;
  @Expose @Setter @Getter @SerializedName("amenities") private String amenities;
  @Expose @Setter @Getter @SerializedName("address") private String address;
}
