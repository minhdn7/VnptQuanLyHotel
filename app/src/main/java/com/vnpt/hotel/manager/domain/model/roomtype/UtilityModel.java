package com.vnpt.hotel.manager.domain.model.roomtype;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 3/1/2018.
 */

public class UtilityModel {
  @Setter @Getter String nameUtility;
  @Setter @Getter boolean checkedUtility;

  public UtilityModel(String nameUtility) {
    this.nameUtility = nameUtility;
  }
  public UtilityModel(String nameUtility,boolean checkedUtility) {
    this.nameUtility = nameUtility;
    this.checkedUtility = checkedUtility;
  }
}
