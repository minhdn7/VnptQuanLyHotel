package com.vnpt.hotel.manager.domain.model.roomtype;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public class CreateRoomType {

  @Expose @Setter @Getter @SerializedName("name") private String name;
  @Expose @Setter @Getter @SerializedName("maxPerson") private int maxPerson;
  @Expose @Setter @Getter @SerializedName("hotelId") private int hotelId;
  @Expose @Setter @Getter @SerializedName("description") private String description;
}
