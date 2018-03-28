package com.vnpt.hotel.manager.domain.model.motel;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 1/26/2018.
 */

public class EmployeModel {
  @Setter @Getter
  String nameEmploy;
  @Setter @Getter
  String atWorkStation;
  @Setter @Getter
  String urlPhoto;

  public EmployeModel(String nameEmploy, String atWorkStation) {
    this.nameEmploy = nameEmploy;
    this.atWorkStation = atWorkStation;
  }
}
