package com.vnpt.hotel.manager.domain.model.motel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/9/2018.
 */

public class MotelFindByUser {

  @Expose @Setter @Getter @SerializedName("userId") private int userId;
  @Expose @Setter @Getter @SerializedName("pageSize") private int pageSize;
  @Expose @Setter @Getter @SerializedName("pageIndex") private int pageIndex;
}
