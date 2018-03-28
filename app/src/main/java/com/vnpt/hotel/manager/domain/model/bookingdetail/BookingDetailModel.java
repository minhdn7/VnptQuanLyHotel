package com.vnpt.hotel.manager.domain.model.bookingdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 1/26/2018.
 */

public class BookingDetailModel {

  @Expose @Setter @Getter @SerializedName("responseMessage") private String responseMessage;
  @Expose @Setter @Getter @SerializedName("responseCode") private int responseCode;
  @Expose @Setter @Getter @SerializedName("bookingDetails") private List<BookingDetail> bookingDetails;

  @AllArgsConstructor(suppressConstructorProperties = true)
  public static class BookingDetail {
    @Expose @Setter @Getter @SerializedName("bookingDetailId") private int bookingDetailId;
    @Expose @Setter @Getter @SerializedName("roomNumber") private String roomNumber;
    @Expose @Setter @Getter @SerializedName("checkInDate") private String checkInDate;
  }
}
