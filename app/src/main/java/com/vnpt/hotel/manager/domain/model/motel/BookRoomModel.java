package com.vnpt.hotel.manager.domain.model.motel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by LiKaLi on 1/26/2018.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
public class BookRoomModel {
  @Setter @Getter
  private String name;
  @Setter @Getter
  private String phone;
  @Setter @Getter
  private String bookingDate;
  @Setter @Getter
  private String status;
  @Setter @Getter
  private int count;
  @Setter @Getter
  private String roomType;
}