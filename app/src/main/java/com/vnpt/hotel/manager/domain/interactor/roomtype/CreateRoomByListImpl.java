package com.vnpt.hotel.manager.domain.interactor.roomtype;

import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomList;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomListResult;
import com.vnpt.hotel.manager.domain.repository.api.roomtype.CreateRoomByListApi;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public class CreateRoomByListImpl implements CreateRoomByList {
  @Inject CreateRoomByListApi createRoomByListApi;

  @Override public Observable<CreateRoomListResult> execute(CreateRoomList createRoomList) {
    return createRoomByListApi.createRoomByListApi(createRoomList);
  }
}
