package com.vnpt.hotel.manager.domain.model.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/2/2018.
 */

public class RegisterApiResult {

  @Expose @Setter @Getter @SerializedName("username") private String username;
  @Expose @Setter @Getter @SerializedName("userId") private int userId;
  @Expose @Setter @Getter @SerializedName("responseMessage") private String responseMessage;
  @Expose @Setter @Getter @SerializedName("responseCode") private int responseCode;
}
