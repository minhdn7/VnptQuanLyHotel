package com.vnpt.hotel.manager.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public class TokenDevApiResult extends CommonApiResult {
  @Expose @Setter @Getter @SerializedName("token") private String token;
}
