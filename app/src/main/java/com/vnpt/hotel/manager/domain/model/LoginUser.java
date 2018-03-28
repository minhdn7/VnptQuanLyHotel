package com.vnpt.hotel.manager.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/2/2018.
 */

public class LoginUser {

  @Expose @SerializedName("username") @Setter @Getter private String username;
  @Expose @SerializedName("userId") @Setter @Getter private int userId;
  @Expose @SerializedName("tokenId") @Setter @Getter private String tokenId;
  @Expose @SerializedName("roles") @Setter @Getter private List<String> roles;
  @Expose @SerializedName("responseMessage") @Setter @Getter private String responseMessage;
  @Expose @SerializedName("responseCode") @Setter @Getter private int responseCode;
  @Expose @SerializedName("name") @Setter @Getter private String name;
}
