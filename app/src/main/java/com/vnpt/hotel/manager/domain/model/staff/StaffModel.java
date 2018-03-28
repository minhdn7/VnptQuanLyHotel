package com.vnpt.hotel.manager.domain.model.staff;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 1/26/2018.
 */

public class StaffModel {

  @Expose @Setter @Getter @SerializedName("responseMessage") private String responseMessage;
  @Expose @Setter @Getter @SerializedName("responseCode") private int responseCode;
  @Expose @Setter @Getter @SerializedName("staffs") private List<Staff> staffs;

  @AllArgsConstructor(suppressConstructorProperties = true)
  public static class Staff {
    @Expose @Setter @Getter @SerializedName("staffId") private int staffId;
    @Expose @Setter @Getter @SerializedName("staffName") private String staffName;
    @Expose @Setter @Getter @SerializedName("staffPosition") private String staffPosition;
    @Expose @Setter @Getter @SerializedName("staffPhone") private String staffPhone;
  }
}
