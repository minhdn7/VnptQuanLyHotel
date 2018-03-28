package com.vnpt.hotel.manager.domain.model.roomtype;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public class CreateRoomList {

  @Expose @Setter @Getter @SerializedName("userId") private int userId;
  @Expose @Setter @Getter @SerializedName("roomTypeId") private int roomTypeId;
  @Expose @Setter @Getter @SerializedName("roomList") private List<RoomList> roomList;
  @Expose @Setter @Getter @SerializedName("hotelId") private int hotelId;

  public static class RoomList {
    @Expose @Setter @Getter @SerializedName("roomNumber") private String roomNumber;
    @Expose @Setter @Getter @SerializedName("description") private String description;
  }
}
