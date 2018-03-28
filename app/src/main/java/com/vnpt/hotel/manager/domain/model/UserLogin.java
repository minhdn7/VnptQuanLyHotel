package com.vnpt.hotel.manager.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public class UserLogin {

  @Expose  @Setter @Getter @SerializedName("username") private String username;
  @Expose @Setter @Getter @SerializedName("tokenFirebase") private String tokenFirebase;
  @Expose @Setter @Getter @SerializedName("password") private String password;
  @Expose @Setter @Getter @SerializedName("deviceId") private String deviceId = "Test";
}
