package com.vnpt.hotel.manager.domain.model.customer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/7/2018.
 */

public class CustomerCheckApiResult extends CommonApiResult {

  @Expose @Setter @Getter @SerializedName("userId") public int userId;
  @Expose @Setter @Getter @SerializedName("phone") public String phone;
  @Expose @Setter @Getter @SerializedName("name") public String name;
  @Expose @Setter @Getter @SerializedName("identity") public String identity;
  @Expose @Setter @Getter @SerializedName("email") public String email;
  @Expose @Setter @Getter @SerializedName("customerId") public Integer customerId;
  @Expose @Setter @Getter @SerializedName("address") public String address;
}
