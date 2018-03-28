package com.vnpt.hotel.manager.domain.model.roomtype;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public class CreateRoomTypeResult {
  @Expose @Setter @Getter @SerializedName("roomTypeId") private int roomTypeId;
  @Expose @Setter @Getter @SerializedName("responseMessage") private String responseMessage;
  @Expose @Setter @Getter @SerializedName("responseCode") private int responseCode;
  @Expose @Setter @Getter @SerializedName("pictures") private String pictures;
  @Expose @Setter @Getter @SerializedName("name") private String name;
  @Expose @Setter @Getter @SerializedName("maxPerson") private int maxPerson;
  @Expose @Setter @Getter @SerializedName("hotelId") private int hotelId;
  @Expose @Setter @Getter @SerializedName("description") private String description;
  @Expose @Setter @Getter @SerializedName("createDate") private String createDate;
  @Expose @Setter @Getter @SerializedName("amenities") private String amenities;
}
