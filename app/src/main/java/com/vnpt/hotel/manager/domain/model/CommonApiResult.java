package com.vnpt.hotel.manager.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LiKaLi on 3/6/2018.
 */

public class CommonApiResult {

  @Expose @SerializedName("responseMessage") private String responseMessage;
  @Expose @SerializedName("responseCode") private int responseCode;

  public String getResponseMessage() {
    return responseMessage;
  }

  public void setResponseMessage(String responseMessage) {
    this.responseMessage = responseMessage;
  }

  public int getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(int responseCode) {
    this.responseCode = responseCode;
  }
}
