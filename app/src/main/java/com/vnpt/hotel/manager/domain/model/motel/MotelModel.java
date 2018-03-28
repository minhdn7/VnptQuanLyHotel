package com.vnpt.hotel.manager.domain.model.motel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 1/26/2018.
 */

public class MotelModel {

  @Expose @Setter @Getter @SerializedName("responseMessage") private String responseMessage;
  @Expose @Setter @Getter @SerializedName("responseCode") private int responseCode;
  @Expose @Setter @Getter @SerializedName("hotels") private List<Hotels> hotels;

  public static class Hotels {
    @Expose @Setter @Getter @SerializedName("userId") private int userId;
    @Expose @Setter @Getter @SerializedName("total") private int total;
    @Expose @Setter @Getter @SerializedName("status") private String status;
    @Expose @Setter @Getter @SerializedName("rating") private int rating;
    @Expose @Setter @Getter @SerializedName("rank") private int rank;
    @Expose @Setter @Getter @SerializedName("pictures") private String pictures;
    @Expose @Setter @Getter @SerializedName("lastUpdate") private String lastUpdate;
    @Expose @Setter @Getter @SerializedName("id") private int id;
    @Expose @Setter @Getter @SerializedName("hotelType") private String hotelType;
    @Expose @Setter @Getter @SerializedName("hotelName") private String hotelName;
    @Expose @Setter @Getter @SerializedName("hotelId") private int hotelId;
    @Expose @Setter @Getter @SerializedName("createDate") private String createDate;
    @Expose @Setter @Getter @SerializedName("address") private String address;
  }
}
