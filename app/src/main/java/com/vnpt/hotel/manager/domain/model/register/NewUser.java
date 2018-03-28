package com.vnpt.hotel.manager.domain.model.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/2/2018.
 */

public class NewUser {
  @Expose @SerializedName("username") @Setter @Getter private String username;
  @Expose @SerializedName("roles") @Setter @Getter private List<String> roles;
  @Expose @SerializedName("password") @Setter @Getter private String password;
}
