package com.vnpt.hotel.manager.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vnpt.hotel.manager.domain.model.motel.CreateMotelApiResult;
import com.vnpt.hotel.manager.domain.model.motel.MotelModel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/7/2018.
 */

public class MessageEvent {
 @Setter @Getter MotelModel.Hotels hotelModel;
 @Setter @Getter UserLogin userLogin;
 @Setter @Getter CreateMotelApiResult createMotelApiResult;


 public static class UserLogin {
  @Expose @Setter @Getter @SerializedName("password") private String passwordLogin;
  @Expose @Setter @Getter @SerializedName("username") private String usernameLogin;
 }
 // Event used to send message from activity to activity.
 public static class SendHotelId {
  private int hotelId;

  public SendHotelId() {
  }

  public SendHotelId(int hotelId) {
   this.hotelId = hotelId;
  }

  public int getHotelId() {
   return hotelId;
  }
 }
}
