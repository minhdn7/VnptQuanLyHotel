package com.vnpt.hotel.manager.domain.model.roomtype;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public class GetListAmenitiesResult extends CommonApiResult {

  @Expose @Setter @Getter @SerializedName("amenities") private List<String> amenities;
}
