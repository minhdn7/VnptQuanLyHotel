package com.vnpt.hotel.manager.domain.model.room;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 1/26/2018.
 */

public class RoomModel {

  @Expose @Setter @Getter @SerializedName("responseMessage") private String responseMessage;
  @Expose @Setter @Getter @SerializedName("responseCode") private int responseCode;
  @Expose @Setter @Getter @SerializedName("rooms") private List<Room> rooms;

  @AllArgsConstructor(suppressConstructorProperties = true)
  public static class Room {
    @Expose @Setter @Getter @SerializedName("roomId") private int roomId;
    @Expose @Setter @Getter @SerializedName("roomTypeName") private String roomTypeName;
    @Expose @Setter @Getter @SerializedName("roomNumber") private String roomNumber;
    @Expose @Setter @Getter @SerializedName("description") private String description;
    @Expose @Setter @Getter @SerializedName("status") private String status;
  }
}
