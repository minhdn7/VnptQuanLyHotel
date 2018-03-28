package com.vnpt.hotel.manager.domain.repository.api.roomtype;

import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomList;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomListResult;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public interface CreateRoomByListApi {
  @POST("vnptline/rest/manager/room/create/bylist")
  Observable<CreateRoomListResult> createRoomByListApi(
      @Body CreateRoomList createRoomList);
}
